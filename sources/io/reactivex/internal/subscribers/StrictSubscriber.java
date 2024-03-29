package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class StrictSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -4945028590049415624L;
    volatile boolean done;
    final Subscriber<? super T> downstream;
    final AtomicThrowable error = new AtomicThrowable();
    final AtomicBoolean once = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<Subscription> upstream = new AtomicReference<>();

    public StrictSubscriber(Subscriber<? super T> downstream2) {
        this.downstream = downstream2;
    }

    public void request(long n) {
        if (n <= 0) {
            cancel();
            onError(new IllegalArgumentException("§3.9 violated: positive request amount required but it was " + n));
            return;
        }
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, n);
    }

    public void cancel() {
        if (!this.done) {
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    public void onSubscribe(Subscription s) {
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onSubscribe(this);
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, s);
            return;
        }
        s.cancel();
        cancel();
        onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
    }

    public void onNext(T t) {
        HalfSerializer.onNext(this.downstream, t, (AtomicInteger) this, this.error);
    }

    public void onError(Throwable t) {
        this.done = true;
        HalfSerializer.onError((Subscriber<?>) this.downstream, t, (AtomicInteger) this, this.error);
    }

    public void onComplete() {
        this.done = true;
        HalfSerializer.onComplete((Subscriber<?>) this.downstream, (AtomicInteger) this, this.error);
    }
}
