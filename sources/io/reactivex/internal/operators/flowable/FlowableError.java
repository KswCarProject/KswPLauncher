package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscriber;

public final class FlowableError<T> extends Flowable<T> {
    final Callable<? extends Throwable> errorSupplier;

    public FlowableError(Callable<? extends Throwable> errorSupplier2) {
        this.errorSupplier = errorSupplier2;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        try {
            t = (Throwable) ObjectHelper.requireNonNull(this.errorSupplier.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th) {
            t = th;
            Exceptions.throwIfFatal(t);
            Throwable th2 = t;
        }
        EmptySubscription.error(t, s);
    }
}
