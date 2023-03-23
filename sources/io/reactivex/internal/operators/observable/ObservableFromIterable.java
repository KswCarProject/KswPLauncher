package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;
import java.util.Iterator;

public final class ObservableFromIterable<T> extends Observable<T> {
    final Iterable<? extends T> source;

    public ObservableFromIterable(Iterable<? extends T> source2) {
        this.source = source2;
    }

    public void subscribeActual(Observer<? super T> observer) {
        try {
            Iterator<? extends T> it = this.source.iterator();
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete((Observer<?>) observer);
                    return;
                }
                FromIterableDisposable<T> d = new FromIterableDisposable<>(observer, it);
                observer.onSubscribe(d);
                if (!d.fusionMode) {
                    d.run();
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, (Observer<?>) observer);
            }
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            EmptyDisposable.error(e2, (Observer<?>) observer);
        }
    }

    static final class FromIterableDisposable<T> extends BasicQueueDisposable<T> {
        boolean checkNext;
        volatile boolean disposed;
        boolean done;
        final Observer<? super T> downstream;
        boolean fusionMode;
        final Iterator<? extends T> it;

        FromIterableDisposable(Observer<? super T> actual, Iterator<? extends T> it2) {
            this.downstream = actual;
            this.it = it2;
        }

        /* access modifiers changed from: package-private */
        public void run() {
            while (!isDisposed()) {
                try {
                    this.downstream.onNext(ObjectHelper.requireNonNull(this.it.next(), "The iterator returned a null value"));
                    if (!isDisposed()) {
                        try {
                            if (!this.it.hasNext()) {
                                if (!isDisposed()) {
                                    this.downstream.onComplete();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable e) {
                            Exceptions.throwIfFatal(e);
                            this.downstream.onError(e);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable e2) {
                    Exceptions.throwIfFatal(e2);
                    this.downstream.onError(e2);
                    return;
                }
            }
        }

        public int requestFusion(int mode) {
            if ((mode & 1) == 0) {
                return 0;
            }
            this.fusionMode = true;
            return 1;
        }

        public T poll() {
            if (this.done) {
                return null;
            }
            if (!this.checkNext) {
                this.checkNext = true;
            } else if (!this.it.hasNext()) {
                this.done = true;
                return null;
            }
            return ObjectHelper.requireNonNull(this.it.next(), "The iterator returned a null value");
        }

        public boolean isEmpty() {
            return this.done;
        }

        public void clear() {
            this.done = true;
        }

        public void dispose() {
            this.disposed = true;
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
