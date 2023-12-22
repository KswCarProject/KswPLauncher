package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes.dex */
public final class ScheduledRunnable extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, Disposable {
    static final int FUTURE_INDEX = 1;
    static final int PARENT_INDEX = 0;
    static final int THREAD_INDEX = 2;
    private static final long serialVersionUID = -6120223772001106981L;
    final Runnable actual;
    static final Object PARENT_DISPOSED = new Object();
    static final Object SYNC_DISPOSED = new Object();
    static final Object ASYNC_DISPOSED = new Object();
    static final Object DONE = new Object();

    public ScheduledRunnable(Runnable actual, DisposableContainer parent) {
        super(3);
        this.actual = actual;
        lazySet(0, parent);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object o;
        Object obj;
        Object obj2;
        boolean compareAndSet;
        Object o2;
        Object o3;
        lazySet(2, Thread.currentThread());
        try {
            this.actual.run();
        } finally {
            try {
                lazySet(2, null);
                o2 = get(0);
                if (o2 != PARENT_DISPOSED) {
                    ((DisposableContainer) o2).delete(this);
                }
                do {
                    o3 = get(1);
                    if (o3 != SYNC_DISPOSED) {
                        return;
                    }
                    return;
                } while (!compareAndSet(1, o3, DONE));
            } catch (Throwable th) {
                do {
                    if (o == obj) {
                        break;
                    } else if (o == obj2) {
                        break;
                    }
                } while (!compareAndSet);
            }
        }
        lazySet(2, null);
        o2 = get(0);
        if (o2 != PARENT_DISPOSED && compareAndSet(0, o2, DONE) && o2 != null) {
            ((DisposableContainer) o2).delete(this);
        }
        do {
            o3 = get(1);
            if (o3 != SYNC_DISPOSED || o3 == ASYNC_DISPOSED) {
                return;
            }
        } while (!compareAndSet(1, o3, DONE));
    }

    public void setFuture(Future<?> f) {
        Object o;
        do {
            o = get(1);
            if (o == DONE) {
                return;
            }
            if (o == SYNC_DISPOSED) {
                f.cancel(false);
                return;
            } else if (o == ASYNC_DISPOSED) {
                f.cancel(true);
                return;
            }
        } while (!compareAndSet(1, o, f));
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        Object o;
        Object obj;
        Object obj2;
        Object obj3;
        while (true) {
            Object o2 = get(1);
            if (o2 == DONE || o2 == (obj2 = SYNC_DISPOSED) || o2 == (obj3 = ASYNC_DISPOSED)) {
                break;
            }
            boolean async = get(2) != Thread.currentThread();
            if (async) {
                obj2 = obj3;
            }
            if (compareAndSet(1, o2, obj2)) {
                if (o2 != null) {
                    ((Future) o2).cancel(async);
                }
            }
        }
        do {
            o = get(0);
            if (o == DONE || o == (obj = PARENT_DISPOSED) || o == null) {
                return;
            }
        } while (!compareAndSet(0, o, obj));
        ((DisposableContainer) o).delete(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        Object o = get(0);
        return o == PARENT_DISPOSED || o == DONE;
    }
}
