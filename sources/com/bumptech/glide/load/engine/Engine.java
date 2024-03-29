package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.Map;

public class Engine implements EngineJobListener, MemoryCache.ResourceRemovedListener, EngineResource.ResourceListener {
    private static final int JOB_POOL_SIZE = 150;
    private static final String TAG = "Engine";
    private static final boolean VERBOSE_IS_LOGGABLE = Log.isLoggable(TAG, 2);
    private final ActiveResources activeResources;
    private final MemoryCache cache;
    private final DecodeJobFactory decodeJobFactory;
    private final LazyDiskCacheProvider diskCacheProvider;
    private final EngineJobFactory engineJobFactory;
    private final Jobs jobs;
    private final EngineKeyFactory keyFactory;
    private final ResourceRecycler resourceRecycler;

    public Engine(MemoryCache memoryCache, DiskCache.Factory diskCacheFactory, GlideExecutor diskCacheExecutor, GlideExecutor sourceExecutor, GlideExecutor sourceUnlimitedExecutor, GlideExecutor animationExecutor, boolean isActiveResourceRetentionAllowed) {
        this(memoryCache, diskCacheFactory, diskCacheExecutor, sourceExecutor, sourceUnlimitedExecutor, animationExecutor, (Jobs) null, (EngineKeyFactory) null, (ActiveResources) null, (EngineJobFactory) null, (DecodeJobFactory) null, (ResourceRecycler) null, isActiveResourceRetentionAllowed);
    }

    Engine(MemoryCache cache2, DiskCache.Factory diskCacheFactory, GlideExecutor diskCacheExecutor, GlideExecutor sourceExecutor, GlideExecutor sourceUnlimitedExecutor, GlideExecutor animationExecutor, Jobs jobs2, EngineKeyFactory keyFactory2, ActiveResources activeResources2, EngineJobFactory engineJobFactory2, DecodeJobFactory decodeJobFactory2, ResourceRecycler resourceRecycler2, boolean isActiveResourceRetentionAllowed) {
        ActiveResources activeResources3;
        EngineKeyFactory keyFactory3;
        Jobs jobs3;
        EngineJobFactory engineJobFactory3;
        DecodeJobFactory decodeJobFactory3;
        ResourceRecycler resourceRecycler3;
        MemoryCache memoryCache = cache2;
        this.cache = memoryCache;
        LazyDiskCacheProvider lazyDiskCacheProvider = new LazyDiskCacheProvider(diskCacheFactory);
        this.diskCacheProvider = lazyDiskCacheProvider;
        if (activeResources2 == null) {
            activeResources3 = new ActiveResources(isActiveResourceRetentionAllowed);
        } else {
            boolean z = isActiveResourceRetentionAllowed;
            activeResources3 = activeResources2;
        }
        this.activeResources = activeResources3;
        activeResources3.setListener(this);
        if (keyFactory2 == null) {
            keyFactory3 = new EngineKeyFactory();
        } else {
            keyFactory3 = keyFactory2;
        }
        this.keyFactory = keyFactory3;
        if (jobs2 == null) {
            jobs3 = new Jobs();
        } else {
            jobs3 = jobs2;
        }
        this.jobs = jobs3;
        if (engineJobFactory2 == null) {
            engineJobFactory3 = new EngineJobFactory(diskCacheExecutor, sourceExecutor, sourceUnlimitedExecutor, animationExecutor, this);
        } else {
            engineJobFactory3 = engineJobFactory2;
        }
        this.engineJobFactory = engineJobFactory3;
        if (decodeJobFactory2 == null) {
            decodeJobFactory3 = new DecodeJobFactory(lazyDiskCacheProvider);
        } else {
            decodeJobFactory3 = decodeJobFactory2;
        }
        this.decodeJobFactory = decodeJobFactory3;
        if (resourceRecycler2 == null) {
            resourceRecycler3 = new ResourceRecycler();
        } else {
            resourceRecycler3 = resourceRecycler2;
        }
        this.resourceRecycler = resourceRecycler3;
        memoryCache.setResourceRemovedListener(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <R> com.bumptech.glide.load.engine.Engine.LoadStatus load(com.bumptech.glide.GlideContext r32, java.lang.Object r33, com.bumptech.glide.load.Key r34, int r35, int r36, java.lang.Class<?> r37, java.lang.Class<R> r38, com.bumptech.glide.Priority r39, com.bumptech.glide.load.engine.DiskCacheStrategy r40, java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r41, boolean r42, boolean r43, com.bumptech.glide.load.Options r44, boolean r45, boolean r46, boolean r47, boolean r48, com.bumptech.glide.request.ResourceCallback r49, java.util.concurrent.Executor r50) {
        /*
            r31 = this;
            r1 = r31
            r0 = r45
            r8 = r49
            r9 = r50
            monitor-enter(r31)
            boolean r10 = VERBOSE_IS_LOGGABLE     // Catch:{ all -> 0x00c7 }
            if (r10 == 0) goto L_0x0012
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x00c7 }
            goto L_0x0014
        L_0x0012:
            r2 = 0
        L_0x0014:
            r11 = r2
            com.bumptech.glide.load.engine.EngineKeyFactory r13 = r1.keyFactory     // Catch:{ all -> 0x00c7 }
            r14 = r33
            r15 = r34
            r16 = r35
            r17 = r36
            r18 = r41
            r19 = r37
            r20 = r38
            r21 = r44
            com.bumptech.glide.load.engine.EngineKey r2 = r13.buildKey(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ all -> 0x00c7 }
            r15 = r2
            com.bumptech.glide.load.engine.EngineResource r2 = r1.loadFromActiveResources(r15, r0)     // Catch:{ all -> 0x00c7 }
            r14 = r2
            r2 = 0
            if (r14 == 0) goto L_0x0042
            com.bumptech.glide.load.DataSource r3 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00c7 }
            r8.onResourceReady(r14, r3)     // Catch:{ all -> 0x00c7 }
            if (r10 == 0) goto L_0x0040
            java.lang.String r3 = "Loaded resource from active resources"
            logWithTimeAndKey(r3, r11, r15)     // Catch:{ all -> 0x00c7 }
        L_0x0040:
            monitor-exit(r31)
            return r2
        L_0x0042:
            com.bumptech.glide.load.engine.EngineResource r3 = r1.loadFromCache(r15, r0)     // Catch:{ all -> 0x00c7 }
            r13 = r3
            if (r13 == 0) goto L_0x0057
            com.bumptech.glide.load.DataSource r3 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00c7 }
            r8.onResourceReady(r13, r3)     // Catch:{ all -> 0x00c7 }
            if (r10 == 0) goto L_0x0055
            java.lang.String r3 = "Loaded resource from cache"
            logWithTimeAndKey(r3, r11, r15)     // Catch:{ all -> 0x00c7 }
        L_0x0055:
            monitor-exit(r31)
            return r2
        L_0x0057:
            com.bumptech.glide.load.engine.Jobs r2 = r1.jobs     // Catch:{ all -> 0x00c7 }
            r7 = r48
            com.bumptech.glide.load.engine.EngineJob r2 = r2.get(r15, r7)     // Catch:{ all -> 0x00c7 }
            r6 = r2
            if (r6 == 0) goto L_0x0073
            r6.addCallback(r8, r9)     // Catch:{ all -> 0x00c7 }
            if (r10 == 0) goto L_0x006c
            java.lang.String r2 = "Added to existing load"
            logWithTimeAndKey(r2, r11, r15)     // Catch:{ all -> 0x00c7 }
        L_0x006c:
            com.bumptech.glide.load.engine.Engine$LoadStatus r2 = new com.bumptech.glide.load.engine.Engine$LoadStatus     // Catch:{ all -> 0x00c7 }
            r2.<init>(r8, r6)     // Catch:{ all -> 0x00c7 }
            monitor-exit(r31)
            return r2
        L_0x0073:
            com.bumptech.glide.load.engine.Engine$EngineJobFactory r2 = r1.engineJobFactory     // Catch:{ all -> 0x00c7 }
            r3 = r15
            r4 = r45
            r5 = r46
            r30 = r6
            r6 = r47
            r7 = r48
            com.bumptech.glide.load.engine.EngineJob r2 = r2.build(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00c7 }
            com.bumptech.glide.load.engine.Engine$DecodeJobFactory r3 = r1.decodeJobFactory     // Catch:{ all -> 0x00c7 }
            r4 = r13
            r13 = r3
            r3 = r14
            r14 = r32
            r5 = r15
            r15 = r33
            r16 = r5
            r17 = r34
            r18 = r35
            r19 = r36
            r20 = r37
            r21 = r38
            r22 = r39
            r23 = r40
            r24 = r41
            r25 = r42
            r26 = r43
            r27 = r48
            r28 = r44
            r29 = r2
            com.bumptech.glide.load.engine.DecodeJob r6 = r13.build(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)     // Catch:{ all -> 0x00c7 }
            com.bumptech.glide.load.engine.Jobs r7 = r1.jobs     // Catch:{ all -> 0x00c7 }
            r7.put(r5, r2)     // Catch:{ all -> 0x00c7 }
            r2.addCallback(r8, r9)     // Catch:{ all -> 0x00c7 }
            r2.start(r6)     // Catch:{ all -> 0x00c7 }
            if (r10 == 0) goto L_0x00c0
            java.lang.String r7 = "Started new load"
            logWithTimeAndKey(r7, r11, r5)     // Catch:{ all -> 0x00c7 }
        L_0x00c0:
            com.bumptech.glide.load.engine.Engine$LoadStatus r7 = new com.bumptech.glide.load.engine.Engine$LoadStatus     // Catch:{ all -> 0x00c7 }
            r7.<init>(r8, r2)     // Catch:{ all -> 0x00c7 }
            monitor-exit(r31)
            return r7
        L_0x00c7:
            r0 = move-exception
            monitor-exit(r31)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.Engine.load(com.bumptech.glide.GlideContext, java.lang.Object, com.bumptech.glide.load.Key, int, int, java.lang.Class, java.lang.Class, com.bumptech.glide.Priority, com.bumptech.glide.load.engine.DiskCacheStrategy, java.util.Map, boolean, boolean, com.bumptech.glide.load.Options, boolean, boolean, boolean, boolean, com.bumptech.glide.request.ResourceCallback, java.util.concurrent.Executor):com.bumptech.glide.load.engine.Engine$LoadStatus");
    }

    private static void logWithTimeAndKey(String log, long startTime, Key key) {
        Log.v(TAG, log + " in " + LogTime.getElapsedMillis(startTime) + "ms, key: " + key);
    }

    private EngineResource<?> loadFromActiveResources(Key key, boolean isMemoryCacheable) {
        if (!isMemoryCacheable) {
            return null;
        }
        EngineResource<?> active = this.activeResources.get(key);
        if (active != null) {
            active.acquire();
        }
        return active;
    }

    private EngineResource<?> loadFromCache(Key key, boolean isMemoryCacheable) {
        if (!isMemoryCacheable) {
            return null;
        }
        EngineResource<?> cached = getEngineResourceFromCache(key);
        if (cached != null) {
            cached.acquire();
            this.activeResources.activate(key, cached);
        }
        return cached;
    }

    private EngineResource<?> getEngineResourceFromCache(Key key) {
        Resource<?> cached = this.cache.remove(key);
        if (cached == null) {
            return null;
        }
        if (cached instanceof EngineResource) {
            return (EngineResource) cached;
        }
        return new EngineResource<>(cached, true, true);
    }

    public void release(Resource<?> resource) {
        if (resource instanceof EngineResource) {
            ((EngineResource) resource).release();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public synchronized void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> resource) {
        if (resource != null) {
            resource.setResourceListener(key, this);
            if (resource.isCacheable()) {
                this.activeResources.activate(key, resource);
            }
        }
        this.jobs.removeIfCurrent(key, engineJob);
    }

    public synchronized void onEngineJobCancelled(EngineJob<?> engineJob, Key key) {
        this.jobs.removeIfCurrent(key, engineJob);
    }

    public void onResourceRemoved(Resource<?> resource) {
        this.resourceRecycler.recycle(resource);
    }

    public synchronized void onResourceReleased(Key cacheKey, EngineResource<?> resource) {
        this.activeResources.deactivate(cacheKey);
        if (resource.isCacheable()) {
            this.cache.put(cacheKey, resource);
        } else {
            this.resourceRecycler.recycle(resource);
        }
    }

    public void clearDiskCache() {
        this.diskCacheProvider.getDiskCache().clear();
    }

    public void shutdown() {
        this.engineJobFactory.shutdown();
        this.diskCacheProvider.clearDiskCacheIfCreated();
        this.activeResources.shutdown();
    }

    public class LoadStatus {
        private final ResourceCallback cb;
        private final EngineJob<?> engineJob;

        LoadStatus(ResourceCallback cb2, EngineJob<?> engineJob2) {
            this.cb = cb2;
            this.engineJob = engineJob2;
        }

        public void cancel() {
            synchronized (Engine.this) {
                this.engineJob.removeCallback(this.cb);
            }
        }
    }

    private static class LazyDiskCacheProvider implements DecodeJob.DiskCacheProvider {
        private volatile DiskCache diskCache;
        private final DiskCache.Factory factory;

        LazyDiskCacheProvider(DiskCache.Factory factory2) {
            this.factory = factory2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void clearDiskCacheIfCreated() {
            if (this.diskCache != null) {
                this.diskCache.clear();
            }
        }

        public DiskCache getDiskCache() {
            if (this.diskCache == null) {
                synchronized (this) {
                    if (this.diskCache == null) {
                        this.diskCache = this.factory.build();
                    }
                    if (this.diskCache == null) {
                        this.diskCache = new DiskCacheAdapter();
                    }
                }
            }
            return this.diskCache;
        }
    }

    static class DecodeJobFactory {
        private int creationOrder;
        final DecodeJob.DiskCacheProvider diskCacheProvider;
        final Pools.Pool<DecodeJob<?>> pool = FactoryPools.threadSafe(Engine.JOB_POOL_SIZE, new FactoryPools.Factory<DecodeJob<?>>() {
            public DecodeJob<?> create() {
                return new DecodeJob<>(DecodeJobFactory.this.diskCacheProvider, DecodeJobFactory.this.pool);
            }
        });

        DecodeJobFactory(DecodeJob.DiskCacheProvider diskCacheProvider2) {
            this.diskCacheProvider = diskCacheProvider2;
        }

        /* access modifiers changed from: package-private */
        public <R> DecodeJob<R> build(GlideContext glideContext, Object model, EngineKey loadKey, Key signature, int width, int height, Class<?> resourceClass, Class<R> transcodeClass, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> transformations, boolean isTransformationRequired, boolean isScaleOnlyOrNoTransform, boolean onlyRetrieveFromCache, Options options, DecodeJob.Callback<R> callback) {
            DecodeJob<R> decodeJob = (DecodeJob) Preconditions.checkNotNull(this.pool.acquire());
            int i = this.creationOrder;
            int i2 = i;
            this.creationOrder = i + 1;
            return decodeJob.init(glideContext, model, loadKey, signature, width, height, resourceClass, transcodeClass, priority, diskCacheStrategy, transformations, isTransformationRequired, isScaleOnlyOrNoTransform, onlyRetrieveFromCache, options, callback, i2);
        }
    }

    static class EngineJobFactory {
        final GlideExecutor animationExecutor;
        final GlideExecutor diskCacheExecutor;
        final EngineJobListener listener;
        final Pools.Pool<EngineJob<?>> pool = FactoryPools.threadSafe(Engine.JOB_POOL_SIZE, new FactoryPools.Factory<EngineJob<?>>() {
            public EngineJob<?> create() {
                return new EngineJob(EngineJobFactory.this.diskCacheExecutor, EngineJobFactory.this.sourceExecutor, EngineJobFactory.this.sourceUnlimitedExecutor, EngineJobFactory.this.animationExecutor, EngineJobFactory.this.listener, EngineJobFactory.this.pool);
            }
        });
        final GlideExecutor sourceExecutor;
        final GlideExecutor sourceUnlimitedExecutor;

        EngineJobFactory(GlideExecutor diskCacheExecutor2, GlideExecutor sourceExecutor2, GlideExecutor sourceUnlimitedExecutor2, GlideExecutor animationExecutor2, EngineJobListener listener2) {
            this.diskCacheExecutor = diskCacheExecutor2;
            this.sourceExecutor = sourceExecutor2;
            this.sourceUnlimitedExecutor = sourceUnlimitedExecutor2;
            this.animationExecutor = animationExecutor2;
            this.listener = listener2;
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            Executors.shutdownAndAwaitTermination(this.diskCacheExecutor);
            Executors.shutdownAndAwaitTermination(this.sourceExecutor);
            Executors.shutdownAndAwaitTermination(this.sourceUnlimitedExecutor);
            Executors.shutdownAndAwaitTermination(this.animationExecutor);
        }

        /* access modifiers changed from: package-private */
        public <R> EngineJob<R> build(Key key, boolean isMemoryCacheable, boolean useUnlimitedSourceGeneratorPool, boolean useAnimationPool, boolean onlyRetrieveFromCache) {
            return ((EngineJob) Preconditions.checkNotNull(this.pool.acquire())).init(key, isMemoryCacheable, useUnlimitedSourceGeneratorPool, useAnimationPool, onlyRetrieveFromCache);
        }
    }
}
