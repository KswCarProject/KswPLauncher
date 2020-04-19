package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import java.util.Arrays;

public class ViewPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback {
    private int[] size;
    private SizeViewTarget viewTarget;

    public ViewPreloadSizeProvider() {
    }

    public ViewPreloadSizeProvider(@NonNull View view) {
        this.viewTarget = new SizeViewTarget(view, this);
    }

    @Nullable
    public int[] getPreloadSize(@NonNull T t, int adapterPosition, int itemPosition) {
        if (this.size == null) {
            return null;
        }
        return Arrays.copyOf(this.size, this.size.length);
    }

    public void onSizeReady(int width, int height) {
        this.size = new int[]{width, height};
        this.viewTarget = null;
    }

    public void setView(@NonNull View view) {
        if (this.size == null && this.viewTarget == null) {
            this.viewTarget = new SizeViewTarget(view, this);
        }
    }

    private static final class SizeViewTarget extends ViewTarget<View, Object> {
        SizeViewTarget(@NonNull View view, @NonNull SizeReadyCallback callback) {
            super(view);
            getSize(callback);
        }

        public void onResourceReady(@NonNull Object resource, @Nullable Transition<? super Object> transition) {
        }
    }
}
