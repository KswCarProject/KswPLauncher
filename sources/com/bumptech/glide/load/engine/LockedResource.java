package com.bumptech.glide.load.engine;

import android.support.p001v4.util.Pools;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

/* loaded from: classes.dex */
final class LockedResource<Z> implements Resource<Z>, FactoryPools.Poolable {
    private static final Pools.Pool<LockedResource<?>> POOL = FactoryPools.threadSafe(20, new FactoryPools.Factory<LockedResource<?>>() { // from class: com.bumptech.glide.load.engine.LockedResource.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        public LockedResource<?> create() {
            return new LockedResource<>();
        }
    });
    private boolean isLocked;
    private boolean isRecycled;
    private final StateVerifier stateVerifier = StateVerifier.newInstance();
    private Resource<Z> toWrap;

    static <Z> LockedResource<Z> obtain(Resource<Z> resource) {
        LockedResource<Z> result = (LockedResource) Preconditions.checkNotNull(POOL.acquire());
        result.init(resource);
        return result;
    }

    LockedResource() {
    }

    private void init(Resource<Z> toWrap) {
        this.isRecycled = false;
        this.isLocked = true;
        this.toWrap = toWrap;
    }

    private void release() {
        this.toWrap = null;
        POOL.release(this);
    }

    synchronized void unlock() {
        this.stateVerifier.throwIfRecycled();
        if (!this.isLocked) {
            throw new IllegalStateException("Already unlocked");
        }
        this.isLocked = false;
        if (this.isRecycled) {
            recycle();
        }
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public Class<Z> getResourceClass() {
        return this.toWrap.getResourceClass();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public Z get() {
        return this.toWrap.get();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.toWrap.getSize();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public synchronized void recycle() {
        this.stateVerifier.throwIfRecycled();
        this.isRecycled = true;
        if (!this.isLocked) {
            this.toWrap.recycle();
            release();
        }
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }
}
