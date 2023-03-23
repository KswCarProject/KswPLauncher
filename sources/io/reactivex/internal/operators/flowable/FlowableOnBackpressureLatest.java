package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableOnBackpressureLatest<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableOnBackpressureLatest(Flowable<T> source) {
        super(source);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new BackpressureLatestSubscriber(s));
    }

    static final class BackpressureLatestSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 163080509307634843L;
        volatile boolean cancelled;
        final AtomicReference<T> current = new AtomicReference<>();
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        final AtomicLong requested = new AtomicLong();
        Subscription upstream;

        BackpressureLatestSubscriber(Subscriber<? super T> downstream2) {
            this.downstream = downstream2;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(LongCompanionObject.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.current.lazySet(t);
            drain();
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.current.lazySet((Object) null);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            boolean z;
            if (getAndIncrement() == 0) {
                Subscriber<? super T> a = this.downstream;
                int missed = 1;
                AtomicLong r = this.requested;
                AtomicReference<T> q = this.current;
                do {
                    long e = 0;
                    while (true) {
                        z = true;
                        if (e == r.get()) {
                            break;
                        }
                        boolean d = this.done;
                        T v = q.getAndSet((Object) null);
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, a, q)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(v);
                            e++;
                        } else {
                            return;
                        }
                    }
                    if (e == r.get()) {
                        boolean z2 = this.done;
                        if (q.get() != null) {
                            z = false;
                        }
                        if (checkTerminated(z2, z, a, q)) {
                            return;
                        }
                    }
                    if (e != 0) {
                        BackpressureHelper.produced(r, e);
                    }
                    missed = addAndGet(-missed);
                } while (missed != 0);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, AtomicReference<T> q) {
            if (this.cancelled) {
                q.lazySet((Object) null);
                return true;
            } else if (!d) {
                return false;
            } else {
                Throwable e = this.error;
                if (e != null) {
                    q.lazySet((Object) null);
                    a.onError(e);
                    return true;
                } else if (!empty) {
                    return false;
                } else {
                    a.onComplete();
                    return true;
                }
            }
        }
    }
}
