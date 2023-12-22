package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class MaybeCache<T> extends Maybe<T> implements MaybeObserver<T> {
    static final CacheDisposable[] EMPTY = new CacheDisposable[0];
    static final CacheDisposable[] TERMINATED = new CacheDisposable[0];
    Throwable error;
    final AtomicReference<CacheDisposable<T>[]> observers = new AtomicReference<>(EMPTY);
    final AtomicReference<MaybeSource<T>> source;
    T value;

    public MaybeCache(MaybeSource<T> source) {
        this.source = new AtomicReference<>(source);
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(MaybeObserver<? super T> observer) {
        CacheDisposable<T> parent = new CacheDisposable<>(observer, this);
        observer.onSubscribe(parent);
        if (add(parent)) {
            if (parent.isDisposed()) {
                remove(parent);
                return;
            }
            MaybeSource<T> src = this.source.getAndSet(null);
            if (src != null) {
                src.subscribe(this);
            }
        } else if (!parent.isDisposed()) {
            Throwable ex = this.error;
            if (ex != null) {
                observer.onError(ex);
                return;
            }
            Object obj = (T) this.value;
            if (obj != null) {
                observer.onSuccess(obj);
            } else {
                observer.onComplete();
            }
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onSubscribe(Disposable d) {
    }

    @Override // io.reactivex.MaybeObserver
    public void onSuccess(T value) {
        CacheDisposable<T>[] andSet;
        this.value = value;
        for (CacheDisposable<T> inner : this.observers.getAndSet(TERMINATED)) {
            if (!inner.isDisposed()) {
                inner.downstream.onSuccess(value);
            }
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onError(Throwable e) {
        CacheDisposable<T>[] andSet;
        this.error = e;
        for (CacheDisposable<T> inner : this.observers.getAndSet(TERMINATED)) {
            if (!inner.isDisposed()) {
                inner.downstream.onError(e);
            }
        }
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        CacheDisposable<T>[] andSet;
        for (CacheDisposable<T> inner : this.observers.getAndSet(TERMINATED)) {
            if (!inner.isDisposed()) {
                inner.downstream.onComplete();
            }
        }
    }

    boolean add(CacheDisposable<T> inner) {
        CacheDisposable<T>[] a;
        CacheDisposable<T>[] b;
        do {
            a = this.observers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new CacheDisposable[n + 1];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = inner;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void remove(CacheDisposable<T> inner) {
        CacheDisposable<T>[] a;
        CacheDisposable<T>[] b;
        do {
            a = this.observers.get();
            int n = a.length;
            if (n == 0) {
                return;
            }
            int j = -1;
            int i = 0;
            while (true) {
                if (i >= n) {
                    break;
                } else if (a[i] != inner) {
                    i++;
                } else {
                    j = i;
                    break;
                }
            }
            if (j < 0) {
                return;
            }
            if (n == 1) {
                b = EMPTY;
            } else {
                CacheDisposable<T>[] b2 = new CacheDisposable[n - 1];
                System.arraycopy(a, 0, b2, 0, j);
                System.arraycopy(a, j + 1, b2, j, (n - j) - 1);
                b = b2;
            }
        } while (!this.observers.compareAndSet(a, b));
    }

    /* loaded from: classes.dex */
    static final class CacheDisposable<T> extends AtomicReference<MaybeCache<T>> implements Disposable {
        private static final long serialVersionUID = -5791853038359966195L;
        final MaybeObserver<? super T> downstream;

        CacheDisposable(MaybeObserver<? super T> actual, MaybeCache<T> parent) {
            super(parent);
            this.downstream = actual;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            MaybeCache<T> mc = getAndSet(null);
            if (mc != null) {
                mc.remove(this);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
