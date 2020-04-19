package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;

public class MemoryCacheAdapter implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    public long getCurrentSize() {
        return 0;
    }

    public long getMaxSize() {
        return 0;
    }

    public void setSizeMultiplier(float multiplier) {
    }

    @Nullable
    public Resource<?> remove(@NonNull Key key) {
        return null;
    }

    @Nullable
    public Resource<?> put(@NonNull Key key, @Nullable Resource<?> resource) {
        if (resource == null) {
            return null;
        }
        this.listener.onResourceRemoved(resource);
        return null;
    }

    public void setResourceRemovedListener(@NonNull MemoryCache.ResourceRemovedListener listener2) {
        this.listener = listener2;
    }

    public void clearMemory() {
    }

    public void trimMemory(int level) {
    }
}
