package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class ObservableFromFuture<T> extends Observable<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public ObservableFromFuture(Future<? extends T> future2, long timeout2, TimeUnit unit2) {
        this.future = future2;
        this.timeout = timeout2;
        this.unit = unit2;
    }

    public void subscribeActual(Observer<? super T> observer) {
        DeferredScalarDisposable<T> d = new DeferredScalarDisposable<>(observer);
        observer.onSubscribe(d);
        if (!d.isDisposed()) {
            try {
                TimeUnit timeUnit = this.unit;
                d.complete(ObjectHelper.requireNonNull(timeUnit != null ? this.future.get(this.timeout, timeUnit) : this.future.get(), "Future returned null"));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                if (!d.isDisposed()) {
                    observer.onError(ex);
                }
            }
        }
    }
}
