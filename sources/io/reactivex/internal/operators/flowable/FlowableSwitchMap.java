package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSwitchMap<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;

    public FlowableSwitchMap(Flowable<T> source, Function<? super T, ? extends Publisher<? extends R>> mapper2, int bufferSize2, boolean delayErrors2) {
        super(source);
        this.mapper = mapper2;
        this.bufferSize = bufferSize2;
        this.delayErrors = delayErrors2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> s) {
        if (!FlowableScalarXMap.tryScalarXMapSubscribe(this.source, s, this.mapper)) {
            this.source.subscribe(new SwitchMapSubscriber(s, this.mapper, this.bufferSize, this.delayErrors));
        }
    }

    static final class SwitchMapSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        static final SwitchMapInnerSubscriber<Object, Object> CANCELLED;
        private static final long serialVersionUID = -3491074160481096299L;
        final AtomicReference<SwitchMapInnerSubscriber<T, R>> active = new AtomicReference<>();
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final AtomicThrowable error;
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final AtomicLong requested = new AtomicLong();
        volatile long unique;
        Subscription upstream;

        static {
            SwitchMapInnerSubscriber<Object, Object> switchMapInnerSubscriber = new SwitchMapInnerSubscriber<>((SwitchMapSubscriber) null, -1, 1);
            CANCELLED = switchMapInnerSubscriber;
            switchMapInnerSubscriber.cancel();
        }

        SwitchMapSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends Publisher<? extends R>> mapper2, int bufferSize2, boolean delayErrors2) {
            this.downstream = actual;
            this.mapper = mapper2;
            this.bufferSize = bufferSize2;
            this.delayErrors = delayErrors2;
            this.error = new AtomicThrowable();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            SwitchMapInnerSubscriber<T, R> inner;
            if (!this.done) {
                long c = this.unique + 1;
                this.unique = c;
                SwitchMapInnerSubscriber<T, R> inner2 = this.active.get();
                if (inner2 != null) {
                    inner2.cancel();
                }
                try {
                    Publisher<? extends R> p = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The publisher returned is null");
                    SwitchMapInnerSubscriber<T, R> nextInner = new SwitchMapInnerSubscriber<>(this, c, this.bufferSize);
                    do {
                        inner = this.active.get();
                        if (inner == CANCELLED) {
                            return;
                        }
                    } while (!this.active.compareAndSet(inner, nextInner));
                    p.subscribe(nextInner);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.upstream.cancel();
                    onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done || !this.error.addThrowable(t)) {
                RxJavaPlugins.onError(t);
                return;
            }
            if (!this.delayErrors) {
                disposeInner();
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                if (this.unique == 0) {
                    this.upstream.request(LongCompanionObject.MAX_VALUE);
                } else {
                    drain();
                }
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                disposeInner();
            }
        }

        /* access modifiers changed from: package-private */
        public void disposeInner() {
            SwitchMapInnerSubscriber<T, R> a;
            SwitchMapInnerSubscriber<T, R> a2 = this.active.get();
            SwitchMapInnerSubscriber<Object, Object> switchMapInnerSubscriber = CANCELLED;
            if (a2 != switchMapInnerSubscriber && (a = this.active.getAndSet(switchMapInnerSubscriber)) != switchMapInnerSubscriber && a != null) {
                a.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            Throwable ex;
            if (getAndIncrement() == 0) {
                Subscriber<? super R> a = this.downstream;
                int missing = 1;
                while (!this.cancelled) {
                    if (this.done) {
                        if (this.delayErrors) {
                            if (this.active.get() == null) {
                                if (((Throwable) this.error.get()) != null) {
                                    a.onError(this.error.terminate());
                                    return;
                                } else {
                                    a.onComplete();
                                    return;
                                }
                            }
                        } else if (((Throwable) this.error.get()) != null) {
                            disposeInner();
                            a.onError(this.error.terminate());
                            return;
                        } else if (this.active.get() == null) {
                            a.onComplete();
                            return;
                        }
                    }
                    SwitchMapInnerSubscriber<T, R> inner = this.active.get();
                    SimpleQueue<R> q = inner != null ? inner.queue : null;
                    if (q != null) {
                        if (inner.done) {
                            if (!this.delayErrors) {
                                if (((Throwable) this.error.get()) != null) {
                                    disposeInner();
                                    a.onError(this.error.terminate());
                                    return;
                                } else if (q.isEmpty()) {
                                    this.active.compareAndSet(inner, (Object) null);
                                }
                            } else if (q.isEmpty()) {
                                this.active.compareAndSet(inner, (Object) null);
                            }
                        }
                        long r = this.requested.get();
                        long e = 0;
                        boolean retry = false;
                        while (true) {
                            if (e == r) {
                                break;
                            } else if (!this.cancelled) {
                                boolean d = inner.done;
                                try {
                                    ex = q.poll();
                                } catch (Throwable ex2) {
                                    Exceptions.throwIfFatal(ex2);
                                    inner.cancel();
                                    this.error.addThrowable(ex2);
                                    d = true;
                                    ex = null;
                                }
                                boolean empty = ex == null;
                                if (inner != this.active.get()) {
                                    retry = true;
                                    break;
                                }
                                if (d) {
                                    if (this.delayErrors) {
                                        if (empty) {
                                            this.active.compareAndSet(inner, (Object) null);
                                            retry = true;
                                            break;
                                        }
                                    } else if (((Throwable) this.error.get()) == null) {
                                        if (empty) {
                                            this.active.compareAndSet(inner, (Object) null);
                                            retry = true;
                                            break;
                                        }
                                    } else {
                                        a.onError(this.error.terminate());
                                        return;
                                    }
                                }
                                if (empty) {
                                    break;
                                }
                                a.onNext(ex);
                                e++;
                            } else {
                                return;
                            }
                        }
                        if (e != 0 && !this.cancelled) {
                            if (r != LongCompanionObject.MAX_VALUE) {
                                this.requested.addAndGet(-e);
                            }
                            inner.request(e);
                        }
                        if (retry) {
                            continue;
                        }
                    }
                    missing = addAndGet(-missing);
                    if (missing == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class SwitchMapInnerSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long index;
        final SwitchMapSubscriber<T, R> parent;
        volatile SimpleQueue<R> queue;

        SwitchMapInnerSubscriber(SwitchMapSubscriber<T, R> parent2, long index2, int bufferSize2) {
            this.parent = parent2;
            this.index = index2;
            this.bufferSize = bufferSize2;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<R> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(7);
                    if (m == 1) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (m == 2) {
                        this.fusionMode = m;
                        this.queue = qs;
                        s.request((long) this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                s.request((long) this.bufferSize);
            }
        }

        public void onNext(R t) {
            SwitchMapSubscriber<T, R> p = this.parent;
            if (this.index != p.unique) {
                return;
            }
            if (this.fusionMode != 0 || this.queue.offer(t)) {
                p.drain();
            } else {
                onError(new MissingBackpressureException("Queue full?!"));
            }
        }

        public void onError(Throwable t) {
            SwitchMapSubscriber<T, R> p = this.parent;
            if (this.index != p.unique || !p.error.addThrowable(t)) {
                RxJavaPlugins.onError(t);
                return;
            }
            if (!p.delayErrors) {
                p.upstream.cancel();
                p.done = true;
            }
            this.done = true;
            p.drain();
        }

        public void onComplete() {
            SwitchMapSubscriber<T, R> p = this.parent;
            if (this.index == p.unique) {
                this.done = true;
                p.drain();
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void request(long n) {
            if (this.fusionMode != 1) {
                ((Subscription) get()).request(n);
            }
        }
    }
}
