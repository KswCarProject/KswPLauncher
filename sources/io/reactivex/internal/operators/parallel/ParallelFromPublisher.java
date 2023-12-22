package io.reactivex.internal.operators.parallel;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class ParallelFromPublisher<T> extends ParallelFlowable<T> {
    final int parallelism;
    final int prefetch;
    final Publisher<? extends T> source;

    public ParallelFromPublisher(Publisher<? extends T> source, int parallelism, int prefetch) {
        this.source = source;
        this.parallelism = parallelism;
        this.prefetch = prefetch;
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public int parallelism() {
        return this.parallelism;
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscribers) {
        if (!validate(subscribers)) {
            return;
        }
        this.source.subscribe(new ParallelDispatcher(subscribers, this.prefetch));
    }

    /* loaded from: classes.dex */
    static final class ParallelDispatcher<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -4470634016609963609L;
        volatile boolean cancelled;
        volatile boolean done;
        final long[] emissions;
        Throwable error;
        int index;
        final int limit;
        final int prefetch;
        int produced;
        SimpleQueue<T> queue;
        final AtomicLongArray requests;
        int sourceMode;
        final AtomicInteger subscriberCount = new AtomicInteger();
        final Subscriber<? super T>[] subscribers;
        Subscription upstream;

        ParallelDispatcher(Subscriber<? super T>[] subscribers, int prefetch) {
            this.subscribers = subscribers;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
            int m = subscribers.length;
            AtomicLongArray atomicLongArray = new AtomicLongArray(m + m + 1);
            this.requests = atomicLongArray;
            atomicLongArray.lazySet(m + m, m);
            this.emissions = new long[m];
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(7);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.done = true;
                        setupSubscribers();
                        drain();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        setupSubscribers();
                        s.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                setupSubscribers();
                s.request(this.prefetch);
            }
        }

        void setupSubscribers() {
            Subscriber<? super T>[] subs = this.subscribers;
            int m = subs.length;
            for (int i = 0; i < m && !this.cancelled; i++) {
                this.subscriberCount.lazySet(i + 1);
                subs[i].onSubscribe(new RailSubscription(i, m));
            }
        }

        /* loaded from: classes.dex */
        final class RailSubscription implements Subscription {

            /* renamed from: j */
            final int f334j;

            /* renamed from: m */
            final int f335m;

            RailSubscription(int j, int m) {
                this.f334j = j;
                this.f335m = m;
            }

            @Override // org.reactivestreams.Subscription
            public void request(long n) {
                long r;
                long u;
                if (SubscriptionHelper.validate(n)) {
                    AtomicLongArray ra = ParallelDispatcher.this.requests;
                    do {
                        r = ra.get(this.f334j);
                        if (r == LongCompanionObject.MAX_VALUE) {
                            return;
                        }
                        u = BackpressureHelper.addCap(r, n);
                    } while (!ra.compareAndSet(this.f334j, r, u));
                    if (ParallelDispatcher.this.subscriberCount.get() == this.f335m) {
                        ParallelDispatcher.this.drain();
                    }
                }
            }

            @Override // org.reactivestreams.Subscription
            public void cancel() {
                if (ParallelDispatcher.this.requests.compareAndSet(this.f334j + this.f335m, 0L, 1L)) {
                    ParallelDispatcher parallelDispatcher = ParallelDispatcher.this;
                    int i = this.f335m;
                    parallelDispatcher.cancel(i + i);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                this.upstream.cancel();
                onError(new MissingBackpressureException("Queue is full?"));
                return;
            }
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        void cancel(int m) {
            if (this.requests.decrementAndGet(m) == 0) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x00cd  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00dc  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drainAsync() {
            AtomicLongArray r;
            long[] e;
            int w;
            int missed;
            Throwable ex;
            SimpleQueue<T> q = this.queue;
            Subscriber<? super T>[] a = this.subscribers;
            AtomicLongArray r2 = this.requests;
            long[] e2 = this.emissions;
            int n = e2.length;
            int idx = this.index;
            int idx2 = this.produced;
            int missed2 = 1;
            int idx3 = idx;
            while (true) {
                int notReady = 0;
                int consumed = idx2;
                int idx4 = idx3;
                while (!this.cancelled) {
                    boolean d = this.done;
                    int i = 0;
                    if (d && (ex = this.error) != null) {
                        q.clear();
                        int length = a.length;
                        while (i < length) {
                            Subscriber<? super T> s = a[i];
                            s.onError(ex);
                            i++;
                        }
                        return;
                    }
                    boolean empty = q.isEmpty();
                    if (d && empty) {
                        int length2 = a.length;
                        while (i < length2) {
                            Subscriber<? super T> s2 = a[i];
                            s2.onComplete();
                            i++;
                        }
                        return;
                    }
                    if (!empty) {
                        long requestAtIndex = r2.get(idx4);
                        long emissionAtIndex = e2[idx4];
                        if (requestAtIndex == emissionAtIndex || r2.get(n + idx4) != 0) {
                            r = r2;
                            e = e2;
                            notReady++;
                        } else {
                            try {
                                T v = q.poll();
                                if (v != null) {
                                    a[idx4].onNext(v);
                                    e2[idx4] = emissionAtIndex + 1;
                                    consumed++;
                                    if (consumed != this.limit) {
                                        r = r2;
                                        e = e2;
                                    } else {
                                        consumed = 0;
                                        r = r2;
                                        e = e2;
                                        this.upstream.request(consumed);
                                    }
                                    notReady = 0;
                                }
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                this.upstream.cancel();
                                int length3 = a.length;
                                while (i < length3) {
                                    Subscriber<? super T> s3 = a[i];
                                    s3.onError(th);
                                    i++;
                                }
                                return;
                            }
                        }
                        idx4++;
                        if (idx4 == n) {
                            idx4 = 0;
                        }
                        if (notReady == n) {
                            idx3 = idx4;
                            idx2 = consumed;
                            w = get();
                            if (w != missed2) {
                                this.index = idx3;
                                this.produced = idx2;
                                missed = addAndGet(-missed2);
                                if (missed == 0) {
                                    return;
                                }
                            } else {
                                missed = w;
                            }
                            missed2 = missed;
                            r2 = r;
                            e2 = e;
                        } else {
                            r2 = r;
                            e2 = e;
                        }
                    }
                    r = r2;
                    e = e2;
                    idx3 = idx4;
                    idx2 = consumed;
                    w = get();
                    if (w != missed2) {
                    }
                    missed2 = missed;
                    r2 = r;
                    e2 = e;
                }
                q.clear();
                return;
            }
        }

        void drainSync() {
            SimpleQueue<T> q;
            int missed;
            SimpleQueue<T> q2 = this.queue;
            Subscriber<? super T>[] a = this.subscribers;
            AtomicLongArray r = this.requests;
            long[] e = this.emissions;
            int n = e.length;
            int missed2 = 1;
            int w = this.index;
            while (true) {
                int notReady = 0;
                int idx = w;
                while (!this.cancelled) {
                    boolean empty = q2.isEmpty();
                    if (empty) {
                        for (Subscriber<? super T> s : a) {
                            s.onComplete();
                        }
                        return;
                    }
                    long requestAtIndex = r.get(idx);
                    long emissionAtIndex = e[idx];
                    if (requestAtIndex == emissionAtIndex || r.get(n + idx) != 0) {
                        q = q2;
                        notReady++;
                    } else {
                        try {
                            T v = q2.poll();
                            if (v == null) {
                                for (Subscriber<? super T> s2 : a) {
                                    s2.onComplete();
                                }
                                return;
                            }
                            q = q2;
                            a[idx].onNext(v);
                            e[idx] = emissionAtIndex + 1;
                            notReady = 0;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.upstream.cancel();
                            int length = a.length;
                            int i = 0;
                            while (i < length) {
                                int i2 = length;
                                Subscriber<? super T> s3 = a[i];
                                s3.onError(th);
                                i++;
                                length = i2;
                            }
                            return;
                        }
                    }
                    idx++;
                    if (idx == n) {
                        idx = 0;
                    }
                    if (notReady != n) {
                        q2 = q;
                    } else {
                        int w2 = get();
                        if (w2 == missed2) {
                            this.index = idx;
                            missed = addAndGet(-missed2);
                            if (missed == 0) {
                                return;
                            }
                        } else {
                            missed = w2;
                        }
                        missed2 = missed;
                        w = idx;
                        q2 = q;
                    }
                }
                q2.clear();
                return;
            }
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            if (this.sourceMode == 1) {
                drainSync();
            } else {
                drainAsync();
            }
        }
    }
}
