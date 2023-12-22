package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class ObservableScalarXMap {
    private ObservableScalarXMap() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, R> boolean tryScalarXMapSubscribe(ObservableSource<T> source, Observer<? super R> observer, Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        if (source instanceof Callable) {
            try {
                Object obj = (Object) ((Callable) source).call();
                if (obj == 0) {
                    EmptyDisposable.complete(observer);
                    return true;
                }
                try {
                    ObservableSource<? extends R> r = (ObservableSource) ObjectHelper.requireNonNull(mapper.apply(obj), "The mapper returned a null ObservableSource");
                    if (r instanceof Callable) {
                        try {
                            Object call = ((Callable) r).call();
                            if (call == null) {
                                EmptyDisposable.complete(observer);
                                return true;
                            }
                            ScalarDisposable<R> sd = new ScalarDisposable<>(observer, call);
                            observer.onSubscribe(sd);
                            sd.run();
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            EmptyDisposable.error(ex, observer);
                            return true;
                        }
                    } else {
                        r.subscribe(observer);
                    }
                    return true;
                } catch (Throwable ex2) {
                    Exceptions.throwIfFatal(ex2);
                    EmptyDisposable.error(ex2, observer);
                    return true;
                }
            } catch (Throwable ex3) {
                Exceptions.throwIfFatal(ex3);
                EmptyDisposable.error(ex3, observer);
                return true;
            }
        }
        return false;
    }

    public static <T, U> Observable<U> scalarXMap(T value, Function<? super T, ? extends ObservableSource<? extends U>> mapper) {
        return RxJavaPlugins.onAssembly(new ScalarXMapObservable(value, mapper));
    }

    /* loaded from: classes.dex */
    static final class ScalarXMapObservable<T, R> extends Observable<R> {
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final T value;

        ScalarXMapObservable(T value, Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
            this.value = value;
            this.mapper = mapper;
        }

        @Override // io.reactivex.Observable
        public void subscribeActual(Observer<? super R> observer) {
            try {
                ObservableSource<? extends R> other = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply((T) this.value), "The mapper returned a null ObservableSource");
                if (other instanceof Callable) {
                    try {
                        Object call = ((Callable) other).call();
                        if (call == null) {
                            EmptyDisposable.complete(observer);
                            return;
                        }
                        ScalarDisposable<R> sd = new ScalarDisposable<>(observer, call);
                        observer.onSubscribe(sd);
                        sd.run();
                        return;
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        EmptyDisposable.error(ex, observer);
                        return;
                    }
                }
                other.subscribe(observer);
            } catch (Throwable e) {
                EmptyDisposable.error(e, observer);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class ScalarDisposable<T> extends AtomicInteger implements QueueDisposable<T>, Runnable {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final Observer<? super T> observer;
        final T value;

        public ScalarDisposable(Observer<? super T> observer, T value) {
            this.observer = observer;
            this.value = value;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean offer(T value) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean offer(T v1, T v2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public T poll() throws Exception {
            if (get() == 1) {
                lazySet(3);
                return this.value;
            }
            return null;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            lazySet(3);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            set(3);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == 3;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 1) != 0) {
                lazySet(1);
                return 1;
            }
            return 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext((T) this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }
    }
}
