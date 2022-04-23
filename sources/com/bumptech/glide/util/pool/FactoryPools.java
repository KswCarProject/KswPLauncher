package com.bumptech.glide.util.pool;

import android.support.v4.util.Pools;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class FactoryPools {
    private static final int DEFAULT_POOL_SIZE = 20;
    private static final Resetter<Object> EMPTY_RESETTER = new Resetter<Object>() {
        public void reset(Object object) {
        }
    };
    private static final String TAG = "FactoryPools";

    public interface Factory<T> {
        T create();
    }

    public interface Poolable {
        StateVerifier getVerifier();
    }

    public interface Resetter<T> {
        void reset(T t);
    }

    private FactoryPools() {
    }

    public static <T extends Poolable> Pools.Pool<T> simple(int size, Factory<T> factory) {
        return build(new Pools.SimplePool(size), factory);
    }

    public static <T extends Poolable> Pools.Pool<T> threadSafe(int size, Factory<T> factory) {
        return build(new Pools.SynchronizedPool(size), factory);
    }

    public static <T> Pools.Pool<List<T>> threadSafeList() {
        return threadSafeList(20);
    }

    public static <T> Pools.Pool<List<T>> threadSafeList(int size) {
        return build(new Pools.SynchronizedPool(size), new Factory<List<T>>() {
            public List<T> create() {
                return new ArrayList();
            }
        }, new Resetter<List<T>>() {
            public void reset(List<T> object) {
                object.clear();
            }
        });
    }

    private static <T extends Poolable> Pools.Pool<T> build(Pools.Pool<T> pool, Factory<T> factory) {
        return build(pool, factory, emptyResetter());
    }

    private static <T> Pools.Pool<T> build(Pools.Pool<T> pool, Factory<T> factory, Resetter<T> resetter) {
        return new FactoryPool(pool, factory, resetter);
    }

    private static <T> Resetter<T> emptyResetter() {
        return EMPTY_RESETTER;
    }

    private static final class FactoryPool<T> implements Pools.Pool<T> {
        private final Factory<T> factory;
        private final Pools.Pool<T> pool;
        private final Resetter<T> resetter;

        FactoryPool(Pools.Pool<T> pool2, Factory<T> factory2, Resetter<T> resetter2) {
            this.pool = pool2;
            this.factory = factory2;
            this.resetter = resetter2;
        }

        public T acquire() {
            T result = this.pool.acquire();
            if (result == null) {
                result = this.factory.create();
                if (Log.isLoggable(FactoryPools.TAG, 2)) {
                    Log.v(FactoryPools.TAG, "Created new " + result.getClass());
                }
            }
            if (result instanceof Poolable) {
                ((Poolable) result).getVerifier().setRecycled(false);
            }
            return result;
        }

        public boolean release(T instance) {
            if (instance instanceof Poolable) {
                ((Poolable) instance).getVerifier().setRecycled(true);
            }
            this.resetter.reset(instance);
            return this.pool.release(instance);
        }
    }
}
