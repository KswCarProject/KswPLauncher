package com.bumptech.glide.load.engine;

import android.os.Process;
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

/* loaded from: classes.dex */
final class ActiveResources {
    final Map<Key, ResourceWeakReference> activeEngineResources;

    /* renamed from: cb */
    private volatile DequeuedResourceCallback f76cb;
    private final boolean isActiveResourceRetentionAllowed;
    private volatile boolean isShutdown;
    private EngineResource.ResourceListener listener;
    private final Executor monitorClearedResourcesExecutor;
    private final ReferenceQueue<EngineResource<?>> resourceReferenceQueue;

    /* loaded from: classes.dex */
    interface DequeuedResourceCallback {
        void onResourceDequeued();
    }

    ActiveResources(boolean isActiveResourceRetentionAllowed) {
        this(isActiveResourceRetentionAllowed, Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.bumptech.glide.load.engine.ActiveResources.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() { // from class: com.bumptech.glide.load.engine.ActiveResources.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Process.setThreadPriority(10);
                        r.run();
                    }
                }, "glide-active-resources");
            }
        }));
    }

    ActiveResources(boolean isActiveResourceRetentionAllowed, Executor monitorClearedResourcesExecutor) {
        this.activeEngineResources = new HashMap();
        this.resourceReferenceQueue = new ReferenceQueue<>();
        this.isActiveResourceRetentionAllowed = isActiveResourceRetentionAllowed;
        this.monitorClearedResourcesExecutor = monitorClearedResourcesExecutor;
        monitorClearedResourcesExecutor.execute(new Runnable() { // from class: com.bumptech.glide.load.engine.ActiveResources.2
            @Override // java.lang.Runnable
            public void run() {
                ActiveResources.this.cleanReferenceQueue();
            }
        });
    }

    void setListener(EngineResource.ResourceListener listener) {
        synchronized (listener) {
            synchronized (this) {
                this.listener = listener;
            }
        }
    }

    synchronized void activate(Key key, EngineResource<?> resource) {
        ResourceWeakReference toPut = new ResourceWeakReference(key, resource, this.resourceReferenceQueue, this.isActiveResourceRetentionAllowed);
        ResourceWeakReference removed = this.activeEngineResources.put(key, toPut);
        if (removed != null) {
            removed.reset();
        }
    }

    synchronized void deactivate(Key key) {
        ResourceWeakReference removed = this.activeEngineResources.remove(key);
        if (removed != null) {
            removed.reset();
        }
    }

    synchronized EngineResource<?> get(Key key) {
        ResourceWeakReference activeRef = this.activeEngineResources.get(key);
        if (activeRef == null) {
            return null;
        }
        EngineResource<?> active = (EngineResource) activeRef.get();
        if (active == null) {
            cleanupActiveReference(activeRef);
        }
        return active;
    }

    void cleanupActiveReference(ResourceWeakReference ref) {
        synchronized (this.listener) {
            synchronized (this) {
                this.activeEngineResources.remove(ref.key);
                if (ref.isCacheable && ref.resource != null) {
                    EngineResource<?> newResource = new EngineResource<>(ref.resource, true, false);
                    newResource.setResourceListener(ref.key, this.listener);
                    this.listener.onResourceReleased(ref.key, newResource);
                }
            }
        }
    }

    void cleanReferenceQueue() {
        while (!this.isShutdown) {
            try {
                ResourceWeakReference ref = (ResourceWeakReference) this.resourceReferenceQueue.remove();
                cleanupActiveReference(ref);
                DequeuedResourceCallback current = this.f76cb;
                if (current != null) {
                    current.onResourceDequeued();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void setDequeuedResourceCallback(DequeuedResourceCallback cb) {
        this.f76cb = cb;
    }

    void shutdown() {
        this.isShutdown = true;
        Executor executor = this.monitorClearedResourcesExecutor;
        if (executor instanceof ExecutorService) {
            ExecutorService service = (ExecutorService) executor;
            com.bumptech.glide.util.Executors.shutdownAndAwaitTermination(service);
        }
    }

    /* loaded from: classes.dex */
    static final class ResourceWeakReference extends WeakReference<EngineResource<?>> {
        final boolean isCacheable;
        final Key key;
        Resource<?> resource;

        ResourceWeakReference(Key key, EngineResource<?> referent, ReferenceQueue<? super EngineResource<?>> queue, boolean isActiveResourceRetentionAllowed) {
            super(referent, queue);
            this.key = (Key) Preconditions.checkNotNull(key);
            this.resource = (referent.isCacheable() && isActiveResourceRetentionAllowed) ? (Resource) Preconditions.checkNotNull(referent.getResource()) : null;
            this.isCacheable = referent.isCacheable();
        }

        void reset() {
            this.resource = null;
            clear();
        }
    }
}
