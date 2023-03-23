package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class PublishSubject<T> extends Subject<T> {
    static final PublishDisposable[] EMPTY = new PublishDisposable[0];
    static final PublishDisposable[] TERMINATED = new PublishDisposable[0];
    Throwable error;
    final AtomicReference<PublishDisposable<T>[]> subscribers = new AtomicReference<>(EMPTY);

    @CheckReturnValue
    public static <T> PublishSubject<T> create() {
        return new PublishSubject<>();
    }

    PublishSubject() {
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> t) {
        PublishDisposable<T> ps = new PublishDisposable<>(t, this);
        t.onSubscribe(ps);
        if (!add(ps)) {
            Throwable ex = this.error;
            if (ex != null) {
                t.onError(ex);
            } else {
                t.onComplete();
            }
        } else if (ps.isDisposed()) {
            remove(ps);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean add(PublishDisposable<T> ps) {
        PublishDisposable<T>[] a;
        PublishDisposable<T>[] b;
        do {
            a = (PublishDisposable[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new PublishDisposable[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = ps;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void remove(PublishDisposable<T> ps) {
        PublishDisposable<T>[] a;
        PublishDisposable<T>[] b;
        do {
            a = (PublishDisposable[]) this.subscribers.get();
            if (a != TERMINATED && a != EMPTY) {
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (a[i] == ps) {
                        j = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (j >= 0) {
                    if (n == 1) {
                        b = EMPTY;
                    } else {
                        PublishDisposable<T>[] b2 = new PublishDisposable[(n - 1)];
                        System.arraycopy(a, 0, b2, 0, j);
                        System.arraycopy(a, j + 1, b2, j, (n - j) - 1);
                        b = b2;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(a, b));
    }

    public void onSubscribe(Disposable d) {
        if (this.subscribers.get() == TERMINATED) {
            d.dispose();
        }
    }

    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (PublishDisposable<T> pd : (PublishDisposable[]) this.subscribers.get()) {
            pd.onNext(t);
        }
    }

    public void onError(Throwable t) {
        ObjectHelper.requireNonNull(t, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        PublishDisposable<T>[] publishDisposableArr = this.subscribers.get();
        PublishDisposable<T>[] publishDisposableArr2 = TERMINATED;
        if (publishDisposableArr == publishDisposableArr2) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.error = t;
        for (PublishDisposable<T> pd : (PublishDisposable[]) this.subscribers.getAndSet(publishDisposableArr2)) {
            pd.onError(t);
        }
    }

    public void onComplete() {
        PublishDisposable<T>[] publishDisposableArr = this.subscribers.get();
        PublishDisposable<T>[] publishDisposableArr2 = TERMINATED;
        if (publishDisposableArr != publishDisposableArr2) {
            for (PublishDisposable<T> pd : (PublishDisposable[]) this.subscribers.getAndSet(publishDisposableArr2)) {
                pd.onComplete();
            }
        }
    }

    public boolean hasObservers() {
        return ((PublishDisposable[]) this.subscribers.get()).length != 0;
    }

    public Throwable getThrowable() {
        if (this.subscribers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.subscribers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.subscribers.get() == TERMINATED && this.error == null;
    }

    static final class PublishDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 3562861878281475070L;
        final Observer<? super T> downstream;
        final PublishSubject<T> parent;

        PublishDisposable(Observer<? super T> actual, PublishSubject<T> parent2) {
            this.downstream = actual;
            this.parent = parent2;
        }

        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable t) {
            if (get()) {
                RxJavaPlugins.onError(t);
            } else {
                this.downstream.onError(t);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }

        public boolean isDisposed() {
            return get();
        }
    }
}
