package com.bumptech.glide.load.engine.cache;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.util.LruCache;

public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    @Nullable
    public /* bridge */ /* synthetic */ Resource put(@NonNull Key key, @Nullable Resource resource) {
        return (Resource) super.put(key, resource);
    }

    @Nullable
    public /* bridge */ /* synthetic */ Resource remove(@NonNull Key key) {
        return (Resource) super.remove(key);
    }

    public LruResourceCache(long size) {
        super(size);
    }

    public void setResourceRemovedListener(@NonNull MemoryCache.ResourceRemovedListener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void onItemEvicted(@NonNull Key key, @Nullable Resource<?> item) {
        if (this.listener != null && item != null) {
            this.listener.onResourceRemoved(item);
        }
    }

    /* access modifiers changed from: protected */
    public int getSize(@Nullable Resource<?> item) {
        if (item == null) {
            return super.getSize(null);
        }
        return item.getSize();
    }

    @SuppressLint({"InlinedApi"})
    public void trimMemory(int level) {
        if (level >= 40) {
            clearMemory();
        } else if (level >= 20 || level == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }
}
