package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    static final int DISPOSED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    protected final Observer<? super T> downstream;
    protected T value;

    public DeferredScalarDisposable(Observer<? super T> downstream) {
        this.downstream = downstream;
    }

    @Override // io.reactivex.internal.fuseable.QueueFuseable
    public final int requestFusion(int mode) {
        if ((mode & 2) != 0) {
            lazySet(8);
            return 2;
        }
        return 0;
    }

    public final void complete(T value) {
        int state = get();
        if ((state & 54) != 0) {
            return;
        }
        Observer<? super T> a = this.downstream;
        if (state == 8) {
            this.value = value;
            lazySet(16);
            a.onNext(null);
        } else {
            lazySet(2);
            a.onNext(value);
        }
        if (get() != 4) {
            a.onComplete();
        }
    }

    public final void error(Throwable t) {
        int state = get();
        if ((state & 54) != 0) {
            RxJavaPlugins.onError(t);
            return;
        }
        lazySet(2);
        this.downstream.onError(t);
    }

    public final void complete() {
        int state = get();
        if ((state & 54) != 0) {
            return;
        }
        lazySet(2);
        this.downstream.onComplete();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public final T poll() throws Exception {
        if (get() == 16) {
            T v = this.value;
            this.value = null;
            lazySet(32);
            return v;
        }
        return null;
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        set(4);
        this.value = null;
    }

    public final boolean tryDispose() {
        return getAndSet(4) != 4;
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return get() == 4;
    }
}
