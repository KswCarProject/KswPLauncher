package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscribers.BoundedSubscriber;
import io.reactivex.internal.subscribers.LambdaSubscriber;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.BlockingIgnoringReceiver;
import io.reactivex.internal.util.ExceptionHelper;
import org.reactivestreams.Publisher;

public final class FlowableBlockingSubscribe {
    private FlowableBlockingSubscribe() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0013 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0014 A[Catch:{ InterruptedException -> 0x003d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> void subscribe(org.reactivestreams.Publisher<? extends T> r4, org.reactivestreams.Subscriber<? super T> r5) {
        /*
            java.util.concurrent.LinkedBlockingQueue r0 = new java.util.concurrent.LinkedBlockingQueue
            r0.<init>()
            io.reactivex.internal.subscribers.BlockingSubscriber r1 = new io.reactivex.internal.subscribers.BlockingSubscriber
            r1.<init>(r0)
            r4.subscribe(r1)
        L_0x000d:
            boolean r2 = r1.isCancelled()     // Catch:{ InterruptedException -> 0x003d }
            if (r2 == 0) goto L_0x0014
            goto L_0x003c
        L_0x0014:
            java.lang.Object r2 = r0.poll()     // Catch:{ InterruptedException -> 0x003d }
            if (r2 != 0) goto L_0x0029
            boolean r3 = r1.isCancelled()     // Catch:{ InterruptedException -> 0x003d }
            if (r3 == 0) goto L_0x0021
            goto L_0x003c
        L_0x0021:
            io.reactivex.internal.util.BlockingHelper.verifyNonBlocking()     // Catch:{ InterruptedException -> 0x003d }
            java.lang.Object r3 = r0.take()     // Catch:{ InterruptedException -> 0x003d }
            r2 = r3
        L_0x0029:
            boolean r3 = r1.isCancelled()     // Catch:{ InterruptedException -> 0x003d }
            if (r3 == 0) goto L_0x0030
            goto L_0x003c
        L_0x0030:
            java.lang.Object r3 = io.reactivex.internal.subscribers.BlockingSubscriber.TERMINATED     // Catch:{ InterruptedException -> 0x003d }
            if (r2 == r3) goto L_0x003c
            boolean r3 = io.reactivex.internal.util.NotificationLite.acceptFull((java.lang.Object) r2, r5)     // Catch:{ InterruptedException -> 0x003d }
            if (r3 == 0) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            goto L_0x000d
        L_0x003c:
            goto L_0x0044
        L_0x003d:
            r2 = move-exception
            r1.cancel()
            r5.onError(r2)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBlockingSubscribe.subscribe(org.reactivestreams.Publisher, org.reactivestreams.Subscriber):void");
    }

    public static <T> void subscribe(Publisher<? extends T> o) {
        BlockingIgnoringReceiver callback = new BlockingIgnoringReceiver();
        LambdaSubscriber<T> ls = new LambdaSubscriber<>(Functions.emptyConsumer(), callback, callback, Functions.REQUEST_MAX);
        o.subscribe(ls);
        BlockingHelper.awaitForComplete(callback, ls);
        Throwable e = callback.error;
        if (e != null) {
            throw ExceptionHelper.wrapOrThrow(e);
        }
    }

    public static <T> void subscribe(Publisher<? extends T> o, Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        subscribe(o, new LambdaSubscriber(onNext, onError, onComplete, Functions.REQUEST_MAX));
    }

    public static <T> void subscribe(Publisher<? extends T> o, Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, int bufferSize) {
        ObjectHelper.requireNonNull(onNext, "onNext is null");
        ObjectHelper.requireNonNull(onError, "onError is null");
        ObjectHelper.requireNonNull(onComplete, "onComplete is null");
        ObjectHelper.verifyPositive(bufferSize, "number > 0 required");
        subscribe(o, new BoundedSubscriber(onNext, onError, onComplete, Functions.boundedConsumer(bufferSize), bufferSize));
    }
}
