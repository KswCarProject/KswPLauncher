package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSkipLastTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    final int bufferSize;
    final boolean delayError;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public FlowableSkipLastTimed(Flowable<T> source, long time2, TimeUnit unit2, Scheduler scheduler2, int bufferSize2, boolean delayError2) {
        super(source);
        this.time = time2;
        this.unit = unit2;
        this.scheduler = scheduler2;
        this.bufferSize = bufferSize2;
        this.delayError = delayError2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SkipLastTimedSubscriber(s, this.time, this.unit, this.scheduler, this.bufferSize, this.delayError));
    }

    static final class SkipLastTimedSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;
        Subscription upstream;

        SkipLastTimedSubscriber(Subscriber<? super T> actual, long time2, TimeUnit unit2, Scheduler scheduler2, int bufferSize, boolean delayError2) {
            this.downstream = actual;
            this.time = time2;
            this.unit = unit2;
            this.scheduler = scheduler2;
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
            this.delayError = delayError2;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(LongCompanionObject.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.queue.offer(Long.valueOf(this.scheduler.now(this.unit)), t);
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
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            boolean delayError2;
            if (getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.downstream;
                SpscLinkedArrayQueue<Object> q = this.queue;
                boolean delayError3 = this.delayError;
                TimeUnit unit2 = this.unit;
                Scheduler scheduler2 = this.scheduler;
                long time2 = this.time;
                while (true) {
                    long r = this.requested.get();
                    long e = 0;
                    while (true) {
                        if (e == r) {
                            delayError2 = delayError3;
                            break;
                        }
                        boolean d = this.done;
                        Long ts = (Long) q.peek();
                        boolean empty = ts == null;
                        long now = scheduler2.now(unit2);
                        if (!empty && ts.longValue() > now - time2) {
                            empty = true;
                        }
                        if (!checkTerminated(d, empty, a, delayError3)) {
                            if (empty) {
                                delayError2 = delayError3;
                                break;
                            }
                            q.poll();
                            a.onNext(q.poll());
                            e++;
                            delayError3 = delayError3;
                        } else {
                            return;
                        }
                    }
                    if (e != 0) {
                        BackpressureHelper.produced(this.requested, e);
                    }
                    missed = addAndGet(-missed);
                    if (missed != 0) {
                        delayError3 = delayError2;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean d, boolean empty, Subscriber<? super T> a, boolean delayError2) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            } else if (!d) {
                return false;
            } else {
                if (!delayError2) {
                    Throwable e = this.error;
                    if (e != null) {
                        this.queue.clear();
                        a.onError(e);
                        return true;
                    } else if (!empty) {
                        return false;
                    } else {
                        a.onComplete();
                        return true;
                    }
                } else if (!empty) {
                    return false;
                } else {
                    Throwable e2 = this.error;
                    if (e2 != null) {
                        a.onError(e2);
                    } else {
                        a.onComplete();
                    }
                    return true;
                }
            }
        }
    }
}
