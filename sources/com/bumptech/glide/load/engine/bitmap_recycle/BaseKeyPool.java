package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.bumptech.glide.util.Util;
import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable> {
    private static final int MAX_SIZE = 20;
    private final Queue<T> keyPool = Util.createQueue(20);

    /* access modifiers changed from: package-private */
    public abstract T create();

    BaseKeyPool() {
    }

    /* access modifiers changed from: package-private */
    public T get() {
        T result = (Poolable) this.keyPool.poll();
        if (result == null) {
            return create();
        }
        return result;
    }

    public void offer(T key) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(key);
        }
    }
}
