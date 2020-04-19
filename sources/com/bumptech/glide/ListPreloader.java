package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.AbsListView;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private boolean isIncreasing = true;
    private int lastEnd;
    private int lastFirstVisible = -1;
    private int lastStart;
    private final int maxPreload;
    private final PreloadSizeProvider<T> preloadDimensionProvider;
    private final PreloadModelProvider<T> preloadModelProvider;
    private final PreloadTargetQueue preloadTargetQueue;
    private final RequestManager requestManager;
    private int totalItemCount;

    public interface PreloadModelProvider<U> {
        @NonNull
        List<U> getPreloadItems(int i);

        @Nullable
        RequestBuilder<?> getPreloadRequestBuilder(@NonNull U u);
    }

    public interface PreloadSizeProvider<T> {
        @Nullable
        int[] getPreloadSize(@NonNull T t, int i, int i2);
    }

    public ListPreloader(@NonNull RequestManager requestManager2, @NonNull PreloadModelProvider<T> preloadModelProvider2, @NonNull PreloadSizeProvider<T> preloadDimensionProvider2, int maxPreload2) {
        this.requestManager = requestManager2;
        this.preloadModelProvider = preloadModelProvider2;
        this.preloadDimensionProvider = preloadDimensionProvider2;
        this.maxPreload = maxPreload2;
        this.preloadTargetQueue = new PreloadTargetQueue(maxPreload2 + 1);
    }

    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
    }

    public void onScroll(AbsListView absListView, int firstVisible, int visibleCount, int totalCount) {
        this.totalItemCount = totalCount;
        if (firstVisible > this.lastFirstVisible) {
            preload(firstVisible + visibleCount, true);
        } else if (firstVisible < this.lastFirstVisible) {
            preload(firstVisible, false);
        }
        this.lastFirstVisible = firstVisible;
    }

    private void preload(int start, boolean increasing) {
        if (this.isIncreasing != increasing) {
            this.isIncreasing = increasing;
            cancelAll();
        }
        preload(start, (increasing ? this.maxPreload : -this.maxPreload) + start);
    }

    private void preload(int from, int to) {
        int end;
        int start;
        if (from < to) {
            start = Math.max(this.lastEnd, from);
            end = to;
        } else {
            start = to;
            end = Math.min(this.lastStart, from);
        }
        int end2 = Math.min(this.totalItemCount, end);
        int start2 = Math.min(this.totalItemCount, Math.max(0, start));
        if (from < to) {
            for (int i = start2; i < end2; i++) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i), i, true);
            }
        } else {
            for (int i2 = end2 - 1; i2 >= start2; i2--) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i2), i2, false);
            }
        }
        this.lastStart = start2;
        this.lastEnd = end2;
    }

    private void preloadAdapterPosition(List<T> items, int position, boolean isIncreasing2) {
        int numItems = items.size();
        if (isIncreasing2) {
            for (int i = 0; i < numItems; i++) {
                preloadItem(items.get(i), position, i);
            }
            return;
        }
        for (int i2 = numItems - 1; i2 >= 0; i2--) {
            preloadItem(items.get(i2), position, i2);
        }
    }

    private void preloadItem(@Nullable T item, int position, int perItemPosition) {
        int[] dimensions;
        RequestBuilder<?> preloadRequestBuilder;
        if (item != null && (dimensions = this.preloadDimensionProvider.getPreloadSize(item, position, perItemPosition)) != null && (preloadRequestBuilder = this.preloadModelProvider.getPreloadRequestBuilder(item)) != null) {
            preloadRequestBuilder.into(this.preloadTargetQueue.next(dimensions[0], dimensions[1]));
        }
    }

    private void cancelAll() {
        for (int i = 0; i < this.maxPreload; i++) {
            this.requestManager.clear((Target<?>) this.preloadTargetQueue.next(0, 0));
        }
    }

    private static final class PreloadTargetQueue {
        private final Queue<PreloadTarget> queue;

        PreloadTargetQueue(int size) {
            this.queue = Util.createQueue(size);
            for (int i = 0; i < size; i++) {
                this.queue.offer(new PreloadTarget());
            }
        }

        public PreloadTarget next(int width, int height) {
            PreloadTarget result = this.queue.poll();
            this.queue.offer(result);
            result.photoWidth = width;
            result.photoHeight = height;
            return result;
        }
    }

    private static final class PreloadTarget extends BaseTarget<Object> {
        int photoHeight;
        int photoWidth;

        PreloadTarget() {
        }

        public void onResourceReady(@NonNull Object resource, @Nullable Transition<? super Object> transition) {
        }

        public void getSize(@NonNull SizeReadyCallback cb) {
            cb.onSizeReady(this.photoWidth, this.photoHeight);
        }

        public void removeCallback(@NonNull SizeReadyCallback cb) {
        }
    }
}
