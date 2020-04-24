package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class GlideBuilder {
    private GlideExecutor animationExecutor;
    private ArrayPool arrayPool;
    private BitmapPool bitmapPool;
    private ConnectivityMonitorFactory connectivityMonitorFactory;
    @Nullable
    private List<RequestListener<Object>> defaultRequestListeners;
    private RequestOptions defaultRequestOptions = new RequestOptions();
    private final Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions = new ArrayMap();
    private GlideExecutor diskCacheExecutor;
    private DiskCache.Factory diskCacheFactory;
    private Engine engine;
    private boolean isActiveResourceRetentionAllowed;
    private boolean isLoggingRequestOriginsEnabled;
    private int logLevel = 4;
    private MemoryCache memoryCache;
    private MemorySizeCalculator memorySizeCalculator;
    @Nullable
    private RequestManagerRetriever.RequestManagerFactory requestManagerFactory;
    private GlideExecutor sourceExecutor;

    @NonNull
    public GlideBuilder setBitmapPool(@Nullable BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
        return this;
    }

    @NonNull
    public GlideBuilder setArrayPool(@Nullable ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
        return this;
    }

    @NonNull
    public GlideBuilder setMemoryCache(@Nullable MemoryCache memoryCache2) {
        this.memoryCache = memoryCache2;
        return this;
    }

    @NonNull
    public GlideBuilder setDiskCache(@Nullable DiskCache.Factory diskCacheFactory2) {
        this.diskCacheFactory = diskCacheFactory2;
        return this;
    }

    @Deprecated
    public GlideBuilder setResizeExecutor(@Nullable GlideExecutor service) {
        return setSourceExecutor(service);
    }

    @NonNull
    public GlideBuilder setSourceExecutor(@Nullable GlideExecutor service) {
        this.sourceExecutor = service;
        return this;
    }

    @NonNull
    public GlideBuilder setDiskCacheExecutor(@Nullable GlideExecutor service) {
        this.diskCacheExecutor = service;
        return this;
    }

    @NonNull
    public GlideBuilder setAnimationExecutor(@Nullable GlideExecutor service) {
        this.animationExecutor = service;
        return this;
    }

    @NonNull
    public GlideBuilder setDefaultRequestOptions(@Nullable RequestOptions requestOptions) {
        this.defaultRequestOptions = requestOptions;
        return this;
    }

    @NonNull
    public <T> GlideBuilder setDefaultTransitionOptions(@NonNull Class<T> clazz, @Nullable TransitionOptions<?, T> options) {
        this.defaultTransitionOptions.put(clazz, options);
        return this;
    }

    @NonNull
    public GlideBuilder setMemorySizeCalculator(@NonNull MemorySizeCalculator.Builder builder) {
        return setMemorySizeCalculator(builder.build());
    }

    @NonNull
    public GlideBuilder setMemorySizeCalculator(@Nullable MemorySizeCalculator calculator) {
        this.memorySizeCalculator = calculator;
        return this;
    }

    @NonNull
    public GlideBuilder setConnectivityMonitorFactory(@Nullable ConnectivityMonitorFactory factory) {
        this.connectivityMonitorFactory = factory;
        return this;
    }

    @NonNull
    public GlideBuilder setLogLevel(int logLevel2) {
        if (logLevel2 < 2 || logLevel2 > 6) {
            throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
        }
        this.logLevel = logLevel2;
        return this;
    }

    @NonNull
    public GlideBuilder setIsActiveResourceRetentionAllowed(boolean isActiveResourceRetentionAllowed2) {
        this.isActiveResourceRetentionAllowed = isActiveResourceRetentionAllowed2;
        return this;
    }

    @NonNull
    public GlideBuilder addGlobalRequestListener(@NonNull RequestListener<Object> listener) {
        if (this.defaultRequestListeners == null) {
            this.defaultRequestListeners = new ArrayList();
        }
        this.defaultRequestListeners.add(listener);
        return this;
    }

    public GlideBuilder setLogRequestOrigins(boolean isEnabled) {
        this.isLoggingRequestOriginsEnabled = isEnabled;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setRequestManagerFactory(@Nullable RequestManagerRetriever.RequestManagerFactory factory) {
        this.requestManagerFactory = factory;
    }

    /* access modifiers changed from: package-private */
    public GlideBuilder setEngine(Engine engine2) {
        this.engine = engine2;
        return this;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public Glide build(@NonNull Context context) {
        if (this.sourceExecutor == null) {
            this.sourceExecutor = GlideExecutor.newSourceExecutor();
        }
        if (this.diskCacheExecutor == null) {
            this.diskCacheExecutor = GlideExecutor.newDiskCacheExecutor();
        }
        if (this.animationExecutor == null) {
            this.animationExecutor = GlideExecutor.newAnimationExecutor();
        }
        if (this.memorySizeCalculator == null) {
            this.memorySizeCalculator = new MemorySizeCalculator.Builder(context).build();
        }
        if (this.connectivityMonitorFactory == null) {
            this.connectivityMonitorFactory = new DefaultConnectivityMonitorFactory();
        }
        if (this.bitmapPool == null) {
            int size = this.memorySizeCalculator.getBitmapPoolSize();
            if (size > 0) {
                this.bitmapPool = new LruBitmapPool((long) size);
            } else {
                this.bitmapPool = new BitmapPoolAdapter();
            }
        }
        if (this.arrayPool == null) {
            this.arrayPool = new LruArrayPool(this.memorySizeCalculator.getArrayPoolSizeInBytes());
        }
        if (this.memoryCache == null) {
            this.memoryCache = new LruResourceCache((long) this.memorySizeCalculator.getMemoryCacheSize());
        }
        if (this.diskCacheFactory == null) {
            this.diskCacheFactory = new InternalCacheDiskCacheFactory(context);
        }
        if (this.engine == null) {
            this.engine = new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheExecutor, this.sourceExecutor, GlideExecutor.newUnlimitedSourceExecutor(), GlideExecutor.newAnimationExecutor(), this.isActiveResourceRetentionAllowed);
        }
        if (this.defaultRequestListeners == null) {
            this.defaultRequestListeners = Collections.emptyList();
        } else {
            this.defaultRequestListeners = Collections.unmodifiableList(this.defaultRequestListeners);
        }
        return new Glide(context, this.engine, this.memoryCache, this.bitmapPool, this.arrayPool, new RequestManagerRetriever(this.requestManagerFactory), this.connectivityMonitorFactory, this.logLevel, (RequestOptions) this.defaultRequestOptions.lock(), this.defaultTransitionOptions, this.defaultRequestListeners, this.isLoggingRequestOriginsEnabled);
    }
}
