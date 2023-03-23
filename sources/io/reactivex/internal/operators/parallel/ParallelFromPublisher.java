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

public final class ParallelFromPublisher<T> extends ParallelFlowable<T> {
    final int parallelism;
    final int prefetch;
    final Publisher<? extends T> source;

    public ParallelFromPublisher(Publisher<? extends T> source2, int parallelism2, int prefetch2) {
        this.source = source2;
        this.parallelism = parallelism2;
        this.prefetch = prefetch2;
    }

    public int parallelism() {
        return this.parallelism;
    }

    public void subscribe(Subscriber<? super T>[] subscribers) {
        if (validate(subscribers)) {
            this.source.subscribe(new ParallelDispatcher(subscribers, this.prefetch));
        }
    }

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

        ParallelDispatcher(Subscriber<? super T>[] subscribers2, int prefetch2) {
            this.subscribers = subscribers2;
            this.prefetch = prefetch2;
            this.limit = prefetch2 - (prefetch2 >> 2);
            int m = subscribers2.length;
            AtomicLongArray atomicLongArray = new AtomicLongArray(m + m + 1);
            this.requests = atomicLongArray;
            atomicLongArray.lazySet(m + m, (long) m);
            this.emissions = new long[m];
        }

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
                        s.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                setupSubscribers();
                s.request((long) this.prefetch);
            }
        }

        /* access modifiers changed from: package-private */
        public void setupSubscribers() {
            Subscriber<? super T>[] subs = this.subscribers;
            int m = subs.length;
            for (int i = 0; i < m && !this.cancelled; i++) {
                this.subscriberCount.lazySet(i + 1);
                subs[i].onSubscribe(new RailSubscription(i, m));
            }
        }

        final class RailSubscription implements Subscription {
            final int j;
            final int m;

            RailSubscription(int j2, int m2) {
                this.j = j2;
                this.m = m2;
            }

            public void request(long n) {
                long r;
                if (SubscriptionHelper.validate(n)) {
                    AtomicLongArray ra = ParallelDispatcher.this.requests;
                    do {
                        r = ra.get(this.j);
                        if (r != LongCompanionObject.MAX_VALUE) {
                        } else {
                            return;
                        }
                    } while (!ra.compareAndSet(this.j, r, BackpressureHelper.addCap(r, n)));
                    if (ParallelDispatcher.this.subscriberCount.get() == this.m) {
                        ParallelDispatcher.this.drain();
                    }
                }
            }

            public void cancel() {
                if (ParallelDispatcher.this.requests.compareAndSet(this.j + this.m, 0, 1)) {
                    ParallelDispatcher parallelDispatcher = ParallelDispatcher.this;
                    int i = this.m;
                    parallelDispatcher.cancel(i + i);
                }
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.upstream.cancel();
            onError(new MissingBackpressureException("Queue is full?"));
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

        /* access modifiers changed from: package-private */
        public void cancel(int m) {
            if (this.requests.decrementAndGet(m) == 0) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x00cd  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x00dc  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainAsync() {
            /*
                r23 = this;
                r1 = r23
                r0 = 1
                io.reactivex.internal.fuseable.SimpleQueue<T> r2 = r1.queue
                org.reactivestreams.Subscriber<? super T>[] r3 = r1.subscribers
                java.util.concurrent.atomic.AtomicLongArray r4 = r1.requests
                long[] r5 = r1.emissions
                int r6 = r5.length
                int r7 = r1.index
                int r8 = r1.produced
                r22 = r7
                r7 = r0
                r0 = r22
            L_0x0015:
                r9 = 0
                r10 = r9
                r9 = r8
                r8 = r0
            L_0x0019:
                boolean r0 = r1.cancelled
                if (r0 == 0) goto L_0x0021
                r2.clear()
                return
            L_0x0021:
                boolean r11 = r1.done
                r12 = 0
                if (r11 == 0) goto L_0x0039
                java.lang.Throwable r0 = r1.error
                if (r0 == 0) goto L_0x0039
                r2.clear()
                int r13 = r3.length
            L_0x002e:
                if (r12 >= r13) goto L_0x0038
                r14 = r3[r12]
                r14.onError(r0)
                int r12 = r12 + 1
                goto L_0x002e
            L_0x0038:
                return
            L_0x0039:
                boolean r13 = r2.isEmpty()
                if (r11 == 0) goto L_0x004d
                if (r13 == 0) goto L_0x004d
                int r0 = r3.length
            L_0x0042:
                if (r12 >= r0) goto L_0x004c
                r14 = r3[r12]
                r14.onComplete()
                int r12 = r12 + 1
                goto L_0x0042
            L_0x004c:
                return
            L_0x004d:
                if (r13 == 0) goto L_0x0050
                goto L_0x006e
            L_0x0050:
                long r14 = r4.get(r8)
                r16 = r5[r8]
                int r0 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
                if (r0 == 0) goto L_0x00b7
                int r0 = r6 + r8
                long r18 = r4.get(r0)
                r20 = 0
                int r0 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
                if (r0 != 0) goto L_0x00b7
                java.lang.Object r0 = r2.poll()     // Catch:{ all -> 0x009c }
                if (r0 != 0) goto L_0x0075
            L_0x006e:
                r19 = r4
                r20 = r5
                r0 = r8
                r8 = r9
                goto L_0x00c7
            L_0x0075:
                r12 = r3[r8]
                r12.onNext(r0)
                r18 = 1
                long r18 = r16 + r18
                r5[r8] = r18
                int r9 = r9 + 1
                r12 = r9
                r18 = r0
                int r0 = r1.limit
                if (r12 != r0) goto L_0x0095
                r9 = 0
                org.reactivestreams.Subscription r0 = r1.upstream
                r19 = r4
                r20 = r5
                long r4 = (long) r12
                r0.request(r4)
                goto L_0x0099
            L_0x0095:
                r19 = r4
                r20 = r5
            L_0x0099:
                r0 = 0
                r10 = r0
                goto L_0x00bd
            L_0x009c:
                r0 = move-exception
                r19 = r4
                r20 = r5
                r4 = r0
                r0 = r4
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                org.reactivestreams.Subscription r4 = r1.upstream
                r4.cancel()
                int r4 = r3.length
            L_0x00ac:
                if (r12 >= r4) goto L_0x00b6
                r5 = r3[r12]
                r5.onError(r0)
                int r12 = r12 + 1
                goto L_0x00ac
            L_0x00b6:
                return
            L_0x00b7:
                r19 = r4
                r20 = r5
                int r10 = r10 + 1
            L_0x00bd:
                int r8 = r8 + 1
                if (r8 != r6) goto L_0x00c3
                r0 = 0
                r8 = r0
            L_0x00c3:
                if (r10 != r6) goto L_0x00e4
                r0 = r8
                r8 = r9
            L_0x00c7:
                int r4 = r23.get()
                if (r4 != r7) goto L_0x00dc
                r1.index = r0
                r1.produced = r8
                int r5 = -r7
                int r5 = r1.addAndGet(r5)
                if (r5 != 0) goto L_0x00da
                return
            L_0x00da:
                r7 = r5
                goto L_0x00de
            L_0x00dc:
                r5 = r4
                r7 = r5
            L_0x00de:
                r4 = r19
                r5 = r20
                goto L_0x0015
            L_0x00e4:
                r4 = r19
                r5 = r20
                goto L_0x0019
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFromPublisher.ParallelDispatcher.drainAsync():void");
        }

        /* access modifiers changed from: package-private */
        public void drainSync() {
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
                while (this.cancelled == 0) {
                    if (q2.isEmpty()) {
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
                                SimpleQueue<T> simpleQueue = q2;
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
                            SimpleQueue<T> simpleQueue2 = q2;
                            Throwable ex = th;
                            Exceptions.throwIfFatal(ex);
                            this.upstream.cancel();
                            int i = 0;
                            for (int length = a.length; i < length; length = length) {
                                a[i].onError(ex);
                                i++;
                            }
                            return;
                        }
                    }
                    idx++;
                    if (idx == n) {
                        idx = 0;
                    }
                    if (notReady == n) {
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
                    } else {
                        q2 = q;
                    }
                }
                q2.clear();
                return;
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                if (this.sourceMode == 1) {
                    drainSync();
                } else {
                    drainAsync();
                }
            }
        }
    }
}
