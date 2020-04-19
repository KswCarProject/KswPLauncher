package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.Pools;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

class EngineJob<R> implements DecodeJob.Callback<R>, FactoryPools.Poolable {
    private static final EngineResourceFactory DEFAULT_FACTORY = new EngineResourceFactory();
    private final GlideExecutor animationExecutor;
    final ResourceCallbacksAndExecutors cbs;
    DataSource dataSource;
    private DecodeJob<R> decodeJob;
    private final GlideExecutor diskCacheExecutor;
    EngineResource<?> engineResource;
    private final EngineResourceFactory engineResourceFactory;
    GlideException exception;
    private boolean hasLoadFailed;
    private boolean hasResource;
    private boolean isCacheable;
    private volatile boolean isCancelled;
    private Key key;
    private final EngineJobListener listener;
    private boolean onlyRetrieveFromCache;
    private final AtomicInteger pendingCallbacks;
    private final Pools.Pool<EngineJob<?>> pool;
    private Resource<?> resource;
    private final GlideExecutor sourceExecutor;
    private final GlideExecutor sourceUnlimitedExecutor;
    private final StateVerifier stateVerifier;
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorPool;

    EngineJob(GlideExecutor diskCacheExecutor2, GlideExecutor sourceExecutor2, GlideExecutor sourceUnlimitedExecutor2, GlideExecutor animationExecutor2, EngineJobListener listener2, Pools.Pool<EngineJob<?>> pool2) {
        this(diskCacheExecutor2, sourceExecutor2, sourceUnlimitedExecutor2, animationExecutor2, listener2, pool2, DEFAULT_FACTORY);
    }

    @VisibleForTesting
    EngineJob(GlideExecutor diskCacheExecutor2, GlideExecutor sourceExecutor2, GlideExecutor sourceUnlimitedExecutor2, GlideExecutor animationExecutor2, EngineJobListener listener2, Pools.Pool<EngineJob<?>> pool2, EngineResourceFactory engineResourceFactory2) {
        this.cbs = new ResourceCallbacksAndExecutors();
        this.stateVerifier = StateVerifier.newInstance();
        this.pendingCallbacks = new AtomicInteger();
        this.diskCacheExecutor = diskCacheExecutor2;
        this.sourceExecutor = sourceExecutor2;
        this.sourceUnlimitedExecutor = sourceUnlimitedExecutor2;
        this.animationExecutor = animationExecutor2;
        this.listener = listener2;
        this.pool = pool2;
        this.engineResourceFactory = engineResourceFactory2;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized EngineJob<R> init(Key key2, boolean isCacheable2, boolean useUnlimitedSourceGeneratorPool2, boolean useAnimationPool2, boolean onlyRetrieveFromCache2) {
        this.key = key2;
        this.isCacheable = isCacheable2;
        this.useUnlimitedSourceGeneratorPool = useUnlimitedSourceGeneratorPool2;
        this.useAnimationPool = useAnimationPool2;
        this.onlyRetrieveFromCache = onlyRetrieveFromCache2;
        return this;
    }

    public synchronized void start(DecodeJob<R> decodeJob2) {
        GlideExecutor executor;
        this.decodeJob = decodeJob2;
        if (decodeJob2.willDecodeFromCache()) {
            executor = this.diskCacheExecutor;
        } else {
            executor = getActiveSourceExecutor();
        }
        executor.execute(decodeJob2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void addCallback(ResourceCallback cb, Executor callbackExecutor) {
        this.stateVerifier.throwIfRecycled();
        this.cbs.add(cb, callbackExecutor);
        if (this.hasResource) {
            incrementPendingCallbacks(1);
            callbackExecutor.execute(new CallResourceReady(cb));
        } else if (this.hasLoadFailed) {
            incrementPendingCallbacks(1);
            callbackExecutor.execute(new CallLoadFailed(cb));
        } else {
            Preconditions.checkArgument(!this.isCancelled, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void callCallbackOnResourceReady(ResourceCallback cb) {
        try {
            cb.onResourceReady(this.engineResource, this.dataSource);
        } catch (Throwable t) {
            throw new CallbackException(t);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void callCallbackOnLoadFailed(ResourceCallback cb) {
        try {
            cb.onLoadFailed(this.exception);
        } catch (Throwable t) {
            throw new CallbackException(t);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeCallback(ResourceCallback cb) {
        boolean isFinishedRunning;
        this.stateVerifier.throwIfRecycled();
        this.cbs.remove(cb);
        if (this.cbs.isEmpty()) {
            cancel();
            if (!this.hasResource) {
                if (!this.hasLoadFailed) {
                    isFinishedRunning = false;
                    if (isFinishedRunning && this.pendingCallbacks.get() == 0) {
                        release();
                    }
                }
            }
            isFinishedRunning = true;
            release();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }

    private GlideExecutor getActiveSourceExecutor() {
        if (this.useUnlimitedSourceGeneratorPool) {
            return this.sourceUnlimitedExecutor;
        }
        return this.useAnimationPool ? this.animationExecutor : this.sourceExecutor;
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        if (!isDone()) {
            this.isCancelled = true;
            this.decodeJob.cancel();
            this.listener.onEngineJobCancelled(this, this.key);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isCancelled() {
        return this.isCancelled;
    }

    private boolean isDone() {
        return this.hasLoadFailed || this.hasResource || this.isCancelled;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r8.listener.onEngineJobComplete(r8, r0, r2);
        r3 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        if (r3.hasNext() == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        r4 = r3.next();
        r4.executor.execute(new com.bumptech.glide.load.engine.EngineJob.CallResourceReady(r8, r4.cb));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0064, code lost:
        decrementPendingCallbacks();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyCallbacksOfResult() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.bumptech.glide.util.pool.StateVerifier r0 = r8.stateVerifier     // Catch:{ all -> 0x0078 }
            r0.throwIfRecycled()     // Catch:{ all -> 0x0078 }
            boolean r0 = r8.isCancelled     // Catch:{ all -> 0x0078 }
            if (r0 == 0) goto L_0x0014
            com.bumptech.glide.load.engine.Resource<?> r0 = r8.resource     // Catch:{ all -> 0x0078 }
            r0.recycle()     // Catch:{ all -> 0x0078 }
            r8.release()     // Catch:{ all -> 0x0078 }
            monitor-exit(r8)     // Catch:{ all -> 0x0078 }
            return
        L_0x0014:
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r0 = r8.cbs     // Catch:{ all -> 0x0078 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x0070
            boolean r0 = r8.hasResource     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x0068
            com.bumptech.glide.load.engine.EngineJob$EngineResourceFactory r0 = r8.engineResourceFactory     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.Resource<?> r1 = r8.resource     // Catch:{ all -> 0x0078 }
            boolean r2 = r8.isCacheable     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineResource r0 = r0.build(r1, r2)     // Catch:{ all -> 0x0078 }
            r8.engineResource = r0     // Catch:{ all -> 0x0078 }
            r0 = 1
            r8.hasResource = r0     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r1 = r8.cbs     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r1 = r1.copy()     // Catch:{ all -> 0x0078 }
            int r2 = r1.size()     // Catch:{ all -> 0x0078 }
            int r2 = r2 + r0
            r8.incrementPendingCallbacks(r2)     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.Key r0 = r8.key     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineResource<?> r2 = r8.engineResource     // Catch:{ all -> 0x0078 }
            monitor-exit(r8)     // Catch:{ all -> 0x0078 }
            com.bumptech.glide.load.engine.EngineJobListener r3 = r8.listener
            r3.onEngineJobComplete(r8, r0, r2)
            java.util.Iterator r3 = r1.iterator()
        L_0x004b:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0064
            java.lang.Object r4 = r3.next()
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbackAndExecutor r4 = (com.bumptech.glide.load.engine.EngineJob.ResourceCallbackAndExecutor) r4
            java.util.concurrent.Executor r5 = r4.executor
            com.bumptech.glide.load.engine.EngineJob$CallResourceReady r6 = new com.bumptech.glide.load.engine.EngineJob$CallResourceReady
            com.bumptech.glide.request.ResourceCallback r7 = r4.cb
            r6.<init>(r7)
            r5.execute(r6)
            goto L_0x004b
        L_0x0064:
            r8.decrementPendingCallbacks()
            return
        L_0x0068:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = "Already have resource"
            r0.<init>(r1)     // Catch:{ all -> 0x0078 }
            throw r0     // Catch:{ all -> 0x0078 }
        L_0x0070:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = "Received a resource without any callbacks to notify"
            r0.<init>(r1)     // Catch:{ all -> 0x0078 }
            throw r0     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0078 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.EngineJob.notifyCallbacksOfResult():void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void incrementPendingCallbacks(int count) {
        Preconditions.checkArgument(isDone(), "Not yet complete!");
        if (this.pendingCallbacks.getAndAdd(count) == 0 && this.engineResource != null) {
            this.engineResource.acquire();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void decrementPendingCallbacks() {
        this.stateVerifier.throwIfRecycled();
        Preconditions.checkArgument(isDone(), "Not yet complete!");
        int decremented = this.pendingCallbacks.decrementAndGet();
        Preconditions.checkArgument(decremented >= 0, "Can't decrement below 0");
        if (decremented == 0) {
            if (this.engineResource != null) {
                this.engineResource.release();
            }
            release();
        }
    }

    private synchronized void release() {
        if (this.key != null) {
            this.cbs.clear();
            this.key = null;
            this.engineResource = null;
            this.resource = null;
            this.hasLoadFailed = false;
            this.isCancelled = false;
            this.hasResource = false;
            this.decodeJob.release(false);
            this.decodeJob = null;
            this.exception = null;
            this.dataSource = null;
            this.pool.release(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void onResourceReady(Resource<R> resource2, DataSource dataSource2) {
        synchronized (this) {
            this.resource = resource2;
            this.dataSource = dataSource2;
        }
        notifyCallbacksOfResult();
    }

    public void onLoadFailed(GlideException e) {
        synchronized (this) {
            this.exception = e;
        }
        notifyCallbacksOfException();
    }

    public void reschedule(DecodeJob<?> job) {
        getActiveSourceExecutor().execute(job);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        r7.listener.onEngineJobComplete(r7, r1, (com.bumptech.glide.load.engine.EngineResource<?>) null);
        r0 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r0.hasNext() == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r3 = r0.next();
        r3.executor.execute(new com.bumptech.glide.load.engine.EngineJob.CallLoadFailed(r7, r3.cb));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        decrementPendingCallbacks();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyCallbacksOfException() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.bumptech.glide.util.pool.StateVerifier r0 = r7.stateVerifier     // Catch:{ all -> 0x0066 }
            r0.throwIfRecycled()     // Catch:{ all -> 0x0066 }
            boolean r0 = r7.isCancelled     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x000f
            r7.release()     // Catch:{ all -> 0x0066 }
            monitor-exit(r7)     // Catch:{ all -> 0x0066 }
            return
        L_0x000f:
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r0 = r7.cbs     // Catch:{ all -> 0x0066 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x005e
            boolean r0 = r7.hasLoadFailed     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0056
            r0 = 1
            r7.hasLoadFailed = r0     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.Key r1 = r7.key     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r2 = r7.cbs     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbacksAndExecutors r2 = r2.copy()     // Catch:{ all -> 0x0066 }
            int r3 = r2.size()     // Catch:{ all -> 0x0066 }
            int r3 = r3 + r0
            r7.incrementPendingCallbacks(r3)     // Catch:{ all -> 0x0066 }
            monitor-exit(r7)     // Catch:{ all -> 0x0066 }
            com.bumptech.glide.load.engine.EngineJobListener r0 = r7.listener
            r3 = 0
            r0.onEngineJobComplete(r7, r1, r3)
            java.util.Iterator r0 = r2.iterator()
        L_0x0039:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0052
            java.lang.Object r3 = r0.next()
            com.bumptech.glide.load.engine.EngineJob$ResourceCallbackAndExecutor r3 = (com.bumptech.glide.load.engine.EngineJob.ResourceCallbackAndExecutor) r3
            java.util.concurrent.Executor r4 = r3.executor
            com.bumptech.glide.load.engine.EngineJob$CallLoadFailed r5 = new com.bumptech.glide.load.engine.EngineJob$CallLoadFailed
            com.bumptech.glide.request.ResourceCallback r6 = r3.cb
            r5.<init>(r6)
            r4.execute(r5)
            goto L_0x0039
        L_0x0052:
            r7.decrementPendingCallbacks()
            return
        L_0x0056:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0066 }
            java.lang.String r1 = "Already failed once"
            r0.<init>(r1)     // Catch:{ all -> 0x0066 }
            throw r0     // Catch:{ all -> 0x0066 }
        L_0x005e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0066 }
            java.lang.String r1 = "Received an exception without any callbacks to notify"
            r0.<init>(r1)     // Catch:{ all -> 0x0066 }
            throw r0     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0066 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.EngineJob.notifyCallbacksOfException():void");
    }

    @NonNull
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    private class CallLoadFailed implements Runnable {
        private final ResourceCallback cb;

        CallLoadFailed(ResourceCallback cb2) {
            this.cb = cb2;
        }

        public void run() {
            synchronized (EngineJob.this) {
                if (EngineJob.this.cbs.contains(this.cb)) {
                    EngineJob.this.callCallbackOnLoadFailed(this.cb);
                }
                EngineJob.this.decrementPendingCallbacks();
            }
        }
    }

    private class CallResourceReady implements Runnable {
        private final ResourceCallback cb;

        CallResourceReady(ResourceCallback cb2) {
            this.cb = cb2;
        }

        public void run() {
            synchronized (EngineJob.this) {
                if (EngineJob.this.cbs.contains(this.cb)) {
                    EngineJob.this.engineResource.acquire();
                    EngineJob.this.callCallbackOnResourceReady(this.cb);
                    EngineJob.this.removeCallback(this.cb);
                }
                EngineJob.this.decrementPendingCallbacks();
            }
        }
    }

    static final class ResourceCallbacksAndExecutors implements Iterable<ResourceCallbackAndExecutor> {
        private final List<ResourceCallbackAndExecutor> callbacksAndExecutors;

        ResourceCallbacksAndExecutors() {
            this(new ArrayList(2));
        }

        ResourceCallbacksAndExecutors(List<ResourceCallbackAndExecutor> callbacksAndExecutors2) {
            this.callbacksAndExecutors = callbacksAndExecutors2;
        }

        /* access modifiers changed from: package-private */
        public void add(ResourceCallback cb, Executor executor) {
            this.callbacksAndExecutors.add(new ResourceCallbackAndExecutor(cb, executor));
        }

        /* access modifiers changed from: package-private */
        public void remove(ResourceCallback cb) {
            this.callbacksAndExecutors.remove(defaultCallbackAndExecutor(cb));
        }

        /* access modifiers changed from: package-private */
        public boolean contains(ResourceCallback cb) {
            return this.callbacksAndExecutors.contains(defaultCallbackAndExecutor(cb));
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.callbacksAndExecutors.isEmpty();
        }

        /* access modifiers changed from: package-private */
        public int size() {
            return this.callbacksAndExecutors.size();
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.callbacksAndExecutors.clear();
        }

        /* access modifiers changed from: package-private */
        public ResourceCallbacksAndExecutors copy() {
            return new ResourceCallbacksAndExecutors(new ArrayList(this.callbacksAndExecutors));
        }

        private static ResourceCallbackAndExecutor defaultCallbackAndExecutor(ResourceCallback cb) {
            return new ResourceCallbackAndExecutor(cb, Executors.directExecutor());
        }

        @NonNull
        public Iterator<ResourceCallbackAndExecutor> iterator() {
            return this.callbacksAndExecutors.iterator();
        }
    }

    static final class ResourceCallbackAndExecutor {
        final ResourceCallback cb;
        final Executor executor;

        ResourceCallbackAndExecutor(ResourceCallback cb2, Executor executor2) {
            this.cb = cb2;
            this.executor = executor2;
        }

        public boolean equals(Object o) {
            if (o instanceof ResourceCallbackAndExecutor) {
                return this.cb.equals(((ResourceCallbackAndExecutor) o).cb);
            }
            return false;
        }

        public int hashCode() {
            return this.cb.hashCode();
        }
    }

    @VisibleForTesting
    static class EngineResourceFactory {
        EngineResourceFactory() {
        }

        public <R> EngineResource<R> build(Resource<R> resource, boolean isMemoryCacheable) {
            return new EngineResource<>(resource, isMemoryCacheable, true);
        }
    }
}
