package android.support.p004v7.recyclerview.extensions;

import android.os.Handler;
import android.os.Looper;
import android.support.p004v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.p004v7.util.AdapterListUpdateCallback;
import android.support.p004v7.util.DiffUtil;
import android.support.p004v7.util.ListUpdateCallback;
import android.support.p004v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: android.support.v7.recyclerview.extensions.AsyncListDiffer */
/* loaded from: classes.dex */
public class AsyncListDiffer<T> {
    private static final Executor sMainThreadExecutor = new MainThreadExecutor();
    final AsyncDifferConfig<T> mConfig;
    private List<T> mList;
    final Executor mMainThreadExecutor;
    int mMaxScheduledGeneration;
    private List<T> mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;

    /* renamed from: android.support.v7.recyclerview.extensions.AsyncListDiffer$MainThreadExecutor */
    /* loaded from: classes.dex */
    private static class MainThreadExecutor implements Executor {
        final Handler mHandler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            this.mHandler.post(command);
        }
    }

    public AsyncListDiffer(RecyclerView.Adapter adapter, DiffUtil.ItemCallback<T> diffCallback) {
        this(new AdapterListUpdateCallback(adapter), new AsyncDifferConfig.Builder(diffCallback).build());
    }

    public AsyncListDiffer(ListUpdateCallback listUpdateCallback, AsyncDifferConfig<T> config) {
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = config;
        if (config.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = config.getMainThreadExecutor();
        } else {
            this.mMainThreadExecutor = sMainThreadExecutor;
        }
    }

    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void submitList(final List<T> newList) {
        final int runGeneration = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = runGeneration;
        List<T> list = this.mList;
        if (newList == list) {
            return;
        }
        if (newList == null) {
            int countRemoved = list.size();
            this.mList = null;
            this.mReadOnlyList = Collections.emptyList();
            this.mUpdateCallback.onRemoved(0, countRemoved);
        } else if (list == null) {
            this.mList = newList;
            this.mReadOnlyList = Collections.unmodifiableList(newList);
            this.mUpdateCallback.onInserted(0, newList.size());
        } else {
            final List<T> oldList = this.mList;
            this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1
                @Override // java.lang.Runnable
                public void run() {
                    final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1.1
                        @Override // android.support.p004v7.util.DiffUtil.Callback
                        public int getOldListSize() {
                            return oldList.size();
                        }

                        @Override // android.support.p004v7.util.DiffUtil.Callback
                        public int getNewListSize() {
                            return newList.size();
                        }

                        @Override // android.support.p004v7.util.DiffUtil.Callback
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            Object obj = oldList.get(oldItemPosition);
                            Object obj2 = newList.get(newItemPosition);
                            if (obj == null || obj2 == null) {
                                return obj == null && obj2 == null;
                            }
                            return AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(obj, obj2);
                        }

                        @Override // android.support.p004v7.util.DiffUtil.Callback
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            Object obj = oldList.get(oldItemPosition);
                            Object obj2 = newList.get(newItemPosition);
                            if (obj != null && obj2 != null) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(obj, obj2);
                            }
                            if (obj == null && obj2 == null) {
                                return true;
                            }
                            throw new AssertionError();
                        }

                        @Override // android.support.p004v7.util.DiffUtil.Callback
                        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                            Object obj = oldList.get(oldItemPosition);
                            Object obj2 = newList.get(newItemPosition);
                            if (obj != null && obj2 != null) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(obj, obj2);
                            }
                            throw new AssertionError();
                        }
                    });
                    AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (AsyncListDiffer.this.mMaxScheduledGeneration == runGeneration) {
                                AsyncListDiffer.this.latchList(newList, result);
                            }
                        }
                    });
                }
            });
        }
    }

    void latchList(List<T> newList, DiffUtil.DiffResult diffResult) {
        this.mList = newList;
        this.mReadOnlyList = Collections.unmodifiableList(newList);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
    }
}
