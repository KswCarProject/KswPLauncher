package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.maybe.MaybeMap;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeZipArray<T, R> extends Maybe<R> {
    final MaybeSource<? extends T>[] sources;
    final Function<? super Object[], ? extends R> zipper;

    public MaybeZipArray(MaybeSource<? extends T>[] sources2, Function<? super Object[], ? extends R> zipper2) {
        this.sources = sources2;
        this.zipper = zipper2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super R> observer) {
        MaybeSource<? extends T>[] sources2 = this.sources;
        int n = sources2.length;
        if (n == 1) {
            sources2[0].subscribe(new MaybeMap.MapMaybeObserver(observer, new SingletonArrayFunc()));
            return;
        }
        ZipCoordinator<T, R> parent = new ZipCoordinator<>(observer, n, this.zipper);
        observer.onSubscribe(parent);
        int i = 0;
        while (i < n && !parent.isDisposed()) {
            MaybeSource<? extends T> source = sources2[i];
            if (source == null) {
                parent.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                source.subscribe(parent.observers[i]);
                i++;
            }
        }
    }

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -5556924161382950569L;
        final MaybeObserver<? super R> downstream;
        final ZipMaybeObserver<T>[] observers;
        final Object[] values;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(MaybeObserver<? super R> observer, int n, Function<? super Object[], ? extends R> zipper2) {
            super(n);
            this.downstream = observer;
            this.zipper = zipper2;
            ZipMaybeObserver<T>[] o = new ZipMaybeObserver[n];
            for (int i = 0; i < n; i++) {
                o[i] = new ZipMaybeObserver<>(this, i);
            }
            this.observers = o;
            this.values = new Object[n];
        }

        public boolean isDisposed() {
            return get() <= 0;
        }

        public void dispose() {
            if (getAndSet(0) > 0) {
                for (ZipMaybeObserver<T> dispose : this.observers) {
                    dispose.dispose();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void innerSuccess(T value, int index) {
            this.values[index] = value;
            if (decrementAndGet() == 0) {
                try {
                    this.downstream.onSuccess(ObjectHelper.requireNonNull(this.zipper.apply(this.values), "The zipper returned a null value"));
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.downstream.onError(ex);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void disposeExcept(int index) {
            ZipMaybeObserver<T>[] observers2 = this.observers;
            int n = observers2.length;
            for (int i = 0; i < index; i++) {
                observers2[i].dispose();
            }
            for (int i2 = index + 1; i2 < n; i2++) {
                observers2[i2].dispose();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable ex, int index) {
            if (getAndSet(0) > 0) {
                disposeExcept(index);
                this.downstream.onError(ex);
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete(int index) {
            if (getAndSet(0) > 0) {
                disposeExcept(index);
                this.downstream.onComplete();
            }
        }
    }

    static final class ZipMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final ZipCoordinator<T, ?> parent;

        ZipMaybeObserver(ZipCoordinator<T, ?> parent2, int index2) {
            this.parent = parent2;
            this.index = index2;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onSuccess(T value) {
            this.parent.innerSuccess(value, this.index);
        }

        public void onError(Throwable e) {
            this.parent.innerError(e, this.index);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }
    }

    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return ObjectHelper.requireNonNull(MaybeZipArray.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
