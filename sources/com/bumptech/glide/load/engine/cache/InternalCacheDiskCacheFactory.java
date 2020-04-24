package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import java.io.File;

public final class InternalCacheDiskCacheFactory extends DiskLruCacheFactory {
    public InternalCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, 262144000);
    }

    public InternalCacheDiskCacheFactory(Context context, long diskCacheSize) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, diskCacheSize);
    }

    public InternalCacheDiskCacheFactory(final Context context, final String diskCacheName, long diskCacheSize) {
        super((DiskLruCacheFactory.CacheDirectoryGetter) new DiskLruCacheFactory.CacheDirectoryGetter() {
            public File getCacheDirectory() {
                File cacheDirectory = context.getCacheDir();
                if (cacheDirectory == null) {
                    return null;
                }
                if (diskCacheName != null) {
                    return new File(cacheDirectory, diskCacheName);
                }
                return cacheDirectory;
            }
        }, diskCacheSize);
    }
}
