package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class SingleCache<T> extends Single<T> implements SingleObserver<T> {
    static final CacheDisposable[] EMPTY = new CacheDisposable[0];
    static final CacheDisposable[] TERMINATED = new CacheDisposable[0];
    Throwable error;
    final SingleSource<? extends T> source;
    T value;
    final AtomicInteger wip = new AtomicInteger();
    final AtomicReference<CacheDisposable<T>[]> observers = new AtomicReference<>(EMPTY);

    public SingleCache(SingleSource<? extends T> source) {
        this.source = source;
    }

    @Override // io.reactivex.Single
    protected void subscribeActual(SingleObserver<? super T> observer) {
        CacheDisposable<T> d = new CacheDisposable<>(observer, this);
        observer.onSubscribe(d);
        if (add(d)) {
            if (d.isDisposed()) {
                remove(d);
            }
            if (this.wip.getAndIncrement() == 0) {
                this.source.subscribe(this);
                return;
            }
            return;
        }
        Throwable ex = this.error;
        if (ex != null) {
            observer.onError(ex);
        } else {
            observer.onSuccess((T) this.value);
        }
    }

    boolean add(CacheDisposable<T> observer) {
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
            b[n] = observer;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void remove(CacheDisposable<T> observer) {
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
                } else if (a[i] != observer) {
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

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable d) {
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T value) {
        CacheDisposable<T>[] andSet;
        this.value = value;
        for (CacheDisposable<T> d : this.observers.getAndSet(TERMINATED)) {
            if (!d.isDisposed()) {
                d.downstream.onSuccess(value);
            }
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable e) {
        CacheDisposable<T>[] andSet;
        this.error = e;
        for (CacheDisposable<T> d : this.observers.getAndSet(TERMINATED)) {
            if (!d.isDisposed()) {
                d.downstream.onError(e);
            }
        }
    }

    /* loaded from: classes.dex */
    static final class CacheDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 7514387411091976596L;
        final SingleObserver<? super T> downstream;
        final SingleCache<T> parent;

        CacheDisposable(SingleObserver<? super T> actual, SingleCache<T> parent) {
            this.downstream = actual;
            this.parent = parent;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }
    }
}
