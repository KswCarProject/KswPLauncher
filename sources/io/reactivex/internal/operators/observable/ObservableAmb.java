package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableAmb<T> extends Observable<T> {
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    public ObservableAmb(ObservableSource<? extends T>[] sources2, Iterable<? extends ObservableSource<? extends T>> sourcesIterable2) {
        this.sources = sources2;
        this.sourcesIterable = sourcesIterable2;
    }

    public void subscribeActual(Observer<? super T> observer) {
        ObservableSource<? extends T>[] sources2 = this.sources;
        int count = 0;
        if (sources2 == null) {
            sources2 = new ObservableSource[8];
            try {
                for (ObservableSource<? extends T> p : this.sourcesIterable) {
                    if (p == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), (Observer<?>) observer);
                        return;
                    }
                    if (count == sources2.length) {
                        ObservableSource<? extends T>[] b = new ObservableSource[((count >> 2) + count)];
                        System.arraycopy(sources2, 0, b, 0, count);
                        sources2 = b;
                    }
                    int count2 = count + 1;
                    try {
                        sources2[count] = p;
                        count = count2;
                    } catch (Throwable th) {
                        e = th;
                        int i = count2;
                        Exceptions.throwIfFatal(e);
                        EmptyDisposable.error(e, (Observer<?>) observer);
                        return;
                    }
                }
            } catch (Throwable th2) {
                e = th2;
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, (Observer<?>) observer);
                return;
            }
        } else {
            count = sources2.length;
        }
        if (count == 0) {
            EmptyDisposable.complete((Observer<?>) observer);
        } else if (count == 1) {
            sources2[0].subscribe(observer);
        } else {
            new AmbCoordinator<>(observer, count).subscribe(sources2);
        }
    }

    static final class AmbCoordinator<T> implements Disposable {
        final Observer<? super T> downstream;
        final AmbInnerObserver<T>[] observers;
        final AtomicInteger winner = new AtomicInteger();

        AmbCoordinator(Observer<? super T> actual, int count) {
            this.downstream = actual;
            this.observers = new AmbInnerObserver[count];
        }

        public void subscribe(ObservableSource<? extends T>[] sources) {
            AmbInnerObserver<T>[] as = this.observers;
            int len = as.length;
            for (int i = 0; i < len; i++) {
                as[i] = new AmbInnerObserver<>(this, i + 1, this.downstream);
            }
            this.winner.lazySet(0);
            this.downstream.onSubscribe(this);
            for (int i2 = 0; i2 < len && this.winner.get() == 0; i2++) {
                sources[i2].subscribe(as[i2]);
            }
        }

        public boolean win(int index) {
            int w = this.winner.get();
            if (w == 0) {
                if (!this.winner.compareAndSet(0, index)) {
                    return false;
                }
                AmbInnerObserver<T>[] a = this.observers;
                int n = a.length;
                for (int i = 0; i < n; i++) {
                    if (i + 1 != index) {
                        a[i].dispose();
                    }
                }
                return true;
            } else if (w == index) {
                return true;
            } else {
                return false;
            }
        }

        public void dispose() {
            if (this.winner.get() != -1) {
                this.winner.lazySet(-1);
                for (AmbInnerObserver<T> a : this.observers) {
                    a.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return this.winner.get() == -1;
        }
    }

    static final class AmbInnerObserver<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final Observer<? super T> downstream;
        final int index;
        final AmbCoordinator<T> parent;
        boolean won;

        AmbInnerObserver(AmbCoordinator<T> parent2, int index2, Observer<? super T> downstream2) {
            this.parent = parent2;
            this.index = index2;
            this.downstream = downstream2;
        }

        public void onSubscribe(Disposable d) {
            DisposableHelper.setOnce(this, d);
        }

        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                ((Disposable) get()).dispose();
            }
        }

        public void onError(Throwable t) {
            if (this.won) {
                this.downstream.onError(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.downstream.onError(t);
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
