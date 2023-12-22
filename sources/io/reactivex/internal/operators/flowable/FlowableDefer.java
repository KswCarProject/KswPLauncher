package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import java.util.concurrent.Callable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableDefer<T> extends Flowable<T> {
    final Callable<? extends Publisher<? extends T>> supplier;

    public FlowableDefer(Callable<? extends Publisher<? extends T>> supplier) {
        this.supplier = supplier;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> s) {
        try {
            Publisher<? extends T> pub = (Publisher) ObjectHelper.requireNonNull(this.supplier.call(), "The publisher supplied is null");
            pub.subscribe(s);
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            EmptySubscription.error(t, s);
        }
    }
}
