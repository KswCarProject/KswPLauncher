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

public final class ObservableScalarXMap {
    private ObservableScalarXMap() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, R> boolean tryScalarXMapSubscribe(ObservableSource<T> source, Observer<? super R> observer, Function<? super T, ? extends ObservableSource<? extends R>> mapper) {
        if (!(source instanceof Callable)) {
            return false;
        }
        try {
            T t = ((Callable) source).call();
            if (t == null) {
                EmptyDisposable.complete((Observer<?>) observer);
                return true;
            }
            try {
                ObservableSource<? extends R> r = (ObservableSource) ObjectHelper.requireNonNull(mapper.apply(t), "The mapper returned a null ObservableSource");
                if (r instanceof Callable) {
                    try {
                        R u = ((Callable) r).call();
                        if (u == null) {
                            EmptyDisposable.complete((Observer<?>) observer);
                            return true;
                        }
                        ScalarDisposable<R> sd = new ScalarDisposable<>(observer, u);
                        observer.onSubscribe(sd);
                        sd.run();
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        EmptyDisposable.error(ex, (Observer<?>) observer);
                        return true;
                    }
                } else {
                    r.subscribe(observer);
                }
                return true;
            } catch (Throwable ex2) {
                Exceptions.throwIfFatal(ex2);
                EmptyDisposable.error(ex2, (Observer<?>) observer);
                return true;
            }
        } catch (Throwable ex3) {
            Exceptions.throwIfFatal(ex3);
            EmptyDisposable.error(ex3, (Observer<?>) observer);
            return true;
        }
    }

    public static <T, U> Observable<U> scalarXMap(T value, Function<? super T, ? extends ObservableSource<? extends U>> mapper) {
        return RxJavaPlugins.onAssembly(new ScalarXMapObservable(value, mapper));
    }

    static final class ScalarXMapObservable<T, R> extends Observable<R> {
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final T value;

        ScalarXMapObservable(T value2, Function<? super T, ? extends ObservableSource<? extends R>> mapper2) {
            this.value = value2;
            this.mapper = mapper2;
        }

        public void subscribeActual(Observer<? super R> observer) {
            try {
                ObservableSource<? extends R> other = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(this.value), "The mapper returned a null ObservableSource");
                if (other instanceof Callable) {
                    try {
                        R u = ((Callable) other).call();
                        if (u == null) {
                            EmptyDisposable.complete((Observer<?>) observer);
                            return;
                        }
                        ScalarDisposable<R> sd = new ScalarDisposable<>(observer, u);
                        observer.onSubscribe(sd);
                        sd.run();
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        EmptyDisposable.error(ex, (Observer<?>) observer);
                    }
                } else {
                    other.subscribe(observer);
                }
            } catch (Throwable e) {
                EmptyDisposable.error(e, (Observer<?>) observer);
            }
        }
    }

    public static final class ScalarDisposable<T> extends AtomicInteger implements QueueDisposable<T>, Runnable {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final Observer<? super T> observer;
        final T value;

        public ScalarDisposable(Observer<? super T> observer2, T value2) {
            this.observer = observer2;
            this.value = value2;
        }

        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        public boolean isEmpty() {
            return get() != 1;
        }

        public void clear() {
            lazySet(3);
        }

        public void dispose() {
            set(3);
        }

        public boolean isDisposed() {
            return get() == 3;
        }

        public int requestFusion(int mode) {
            if ((mode & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext(this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }
    }
}
