package com.bumptech.glide.load.engine;

import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class ActiveResources {
    @VisibleForTesting
    final Map<Key, ResourceWeakReference> activeEngineResources;
    @Nullable
    private volatile DequeuedResourceCallback cb;
    private final boolean isActiveResourceRetentionAllowed;
    private volatile boolean isShutdown;
    private EngineResource.ResourceListener listener;
    private final Executor monitorClearedResourcesExecutor;
    private final ReferenceQueue<EngineResource<?>> resourceReferenceQueue;

    @VisibleForTesting
    interface DequeuedResourceCallback {
        void onResourceDequeued();
    }

    ActiveResources(boolean isActiveResourceRetentionAllowed2) {
        this(isActiveResourceRetentionAllowed2, Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(@NonNull final Runnable r) {
                return new Thread(new Runnable() {
                    public void run() {
                        Process.setThreadPriority(10);
                        r.run();
                    }
                }, "glide-active-resources");
            }
        }));
    }

    @VisibleForTesting
    ActiveResources(boolean isActiveResourceRetentionAllowed2, Executor monitorClearedResourcesExecutor2) {
        this.activeEngineResources = new HashMap();
        this.resourceReferenceQueue = new ReferenceQueue<>();
        this.isActiveResourceRetentionAllowed = isActiveResourceRetentionAllowed2;
        this.monitorClearedResourcesExecutor = monitorClearedResourcesExecutor2;
        monitorClearedResourcesExecutor2.execute(new Runnable() {
            public void run() {
                ActiveResources.this.cleanReferenceQueue();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void setListener(EngineResource.ResourceListener listener2) {
        synchronized (listener2) {
            synchronized (this) {
                this.listener = listener2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void activate(Key key, EngineResource<?> resource) {
        ResourceWeakReference removed = this.activeEngineResources.put(key, new ResourceWeakReference(key, resource, this.resourceReferenceQueue, this.isActiveResourceRetentionAllowed));
        if (removed != null) {
            removed.reset();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void deactivate(Key key) {
        ResourceWeakReference removed = this.activeEngineResources.remove(key);
        if (removed != null) {
            removed.reset();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        return r1;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.bumptech.glide.load.engine.EngineResource<?> get(com.bumptech.glide.load.Key r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.Map<com.bumptech.glide.load.Key, com.bumptech.glide.load.engine.ActiveResources$ResourceWeakReference> r0 = r2.activeEngineResources     // Catch:{ all -> 0x001b }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x001b }
            com.bumptech.glide.load.engine.ActiveResources$ResourceWeakReference r0 = (com.bumptech.glide.load.engine.ActiveResources.ResourceWeakReference) r0     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x000e
            r1 = 0
            monitor-exit(r2)
            return r1
        L_0x000e:
            java.lang.Object r1 = r0.get()     // Catch:{ all -> 0x001b }
            com.bumptech.glide.load.engine.EngineResource r1 = (com.bumptech.glide.load.engine.EngineResource) r1     // Catch:{ all -> 0x001b }
            if (r1 != 0) goto L_0x0019
            r2.cleanupActiveReference(r0)     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r2)
            return r1
        L_0x001b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.ActiveResources.get(com.bumptech.glide.load.Key):com.bumptech.glide.load.engine.EngineResource");
    }

    /* access modifiers changed from: package-private */
    public void cleanupActiveReference(@NonNull ResourceWeakReference ref) {
        synchronized (this.listener) {
            synchronized (this) {
                this.activeEngineResources.remove(ref.key);
                if (ref.isCacheable) {
                    if (ref.resource != null) {
                        EngineResource<?> newResource = new EngineResource<>(ref.resource, true, false);
                        newResource.setResourceListener(ref.key, this.listener);
                        this.listener.onResourceReleased(ref.key, newResource);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanReferenceQueue() {
        while (!this.isShutdown) {
            try {
                cleanupActiveReference((ResourceWeakReference) this.resourceReferenceQueue.remove());
                DequeuedResourceCallback current = this.cb;
                if (current != null) {
                    current.onResourceDequeued();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setDequeuedResourceCallback(DequeuedResourceCallback cb2) {
        this.cb = cb2;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void shutdown() {
        this.isShutdown = true;
        if (this.monitorClearedResourcesExecutor instanceof ExecutorService) {
            com.bumptech.glide.util.Executors.shutdownAndAwaitTermination((ExecutorService) this.monitorClearedResourcesExecutor);
        }
    }

    @VisibleForTesting
    static final class ResourceWeakReference extends WeakReference<EngineResource<?>> {
        final boolean isCacheable;
        final Key key;
        @Nullable
        Resource<?> resource;

        ResourceWeakReference(@NonNull Key key2, @NonNull EngineResource<?> referent, @NonNull ReferenceQueue<? super EngineResource<?>> queue, boolean isActiveResourceRetentionAllowed) {
            super(referent, queue);
            this.key = (Key) Preconditions.checkNotNull(key2);
            this.resource = (!referent.isCacheable() || !isActiveResourceRetentionAllowed) ? null : (Resource) Preconditions.checkNotNull(referent.getResource());
            this.isCacheable = referent.isCacheable();
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.resource = null;
            clear();
        }
    }
}
