package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowablePublish<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, FlowablePublishClassic<T> {
    static final long CANCELLED = Long.MIN_VALUE;
    final int bufferSize;
    final AtomicReference<PublishSubscriber<T>> current;
    final Publisher<T> onSubscribe;
    final Flowable<T> source;

    public static <T> ConnectableFlowable<T> create(Flowable<T> source2, int bufferSize2) {
        AtomicReference<PublishSubscriber<T>> curr = new AtomicReference<>();
        return RxJavaPlugins.onAssembly(new FlowablePublish(new FlowablePublisher<>(curr, bufferSize2), source2, curr, bufferSize2));
    }

    private FlowablePublish(Publisher<T> onSubscribe2, Flowable<T> source2, AtomicReference<PublishSubscriber<T>> current2, int bufferSize2) {
        this.onSubscribe = onSubscribe2;
        this.source = source2;
        this.current = current2;
        this.bufferSize = bufferSize2;
    }

    public Publisher<T> source() {
        return this.source;
    }

    public int publishBufferSize() {
        return this.bufferSize;
    }

    public Publisher<T> publishSource() {
        return this.source;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        this.onSubscribe.subscribe(s);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber<T>> r0 = r4.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber r0 = (io.reactivex.internal.operators.flowable.FlowablePublish.PublishSubscriber) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0023
        L_0x0010:
            io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber r1 = new io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber<T>> r2 = r4.current
            int r3 = r4.bufferSize
            r1.<init>(r2, r3)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber<T>> r2 = r4.current
            boolean r2 = r2.compareAndSet(r0, r1)
            if (r2 != 0) goto L_0x0022
            goto L_0x0000
        L_0x0022:
            r0 = r1
        L_0x0023:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0036
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r2 = r3
        L_0x0037:
            r1 = r2
            r5.accept(r0)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0044
            io.reactivex.Flowable<T> r2 = r4.source
            r2.subscribe(r0)
        L_0x0044:
            return
        L_0x0045:
            r2 = move-exception
            io.reactivex.exceptions.Exceptions.throwIfFatal(r2)
            java.lang.RuntimeException r3 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublish.connect(io.reactivex.functions.Consumer):void");
    }

    static final class PublishSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscriber[] EMPTY = new InnerSubscriber[0];
        static final InnerSubscriber[] TERMINATED = new InnerSubscriber[0];
        private static final long serialVersionUID = -202316842419149694L;
        final int bufferSize;
        final AtomicReference<PublishSubscriber<T>> current;
        volatile SimpleQueue<T> queue;
        final AtomicBoolean shouldConnect;
        int sourceMode;
        final AtomicReference<InnerSubscriber<T>[]> subscribers = new AtomicReference<>(EMPTY);
        volatile Object terminalEvent;
        final AtomicReference<Subscription> upstream = new AtomicReference<>();

        PublishSubscriber(AtomicReference<PublishSubscriber<T>> current2, int bufferSize2) {
            this.current = current2;
            this.shouldConnect = new AtomicBoolean();
            this.bufferSize = bufferSize2;
        }

        public void dispose() {
            InnerSubscriber[] innerSubscriberArr = this.subscribers.get();
            InnerSubscriber[] innerSubscriberArr2 = TERMINATED;
            if (innerSubscriberArr != innerSubscriberArr2 && this.subscribers.getAndSet(innerSubscriberArr2) != innerSubscriberArr2) {
                this.current.compareAndSet(this, (Object) null);
                SubscriptionHelper.cancel(this.upstream);
            }
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.upstream, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(7);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.terminalEvent = NotificationLite.complete();
                        dispatch();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        s.request((long) this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                s.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                dispatch();
            } else {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            }
        }

        public void onError(Throwable e) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(e);
                dispatch();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.complete();
                dispatch();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerSubscriber<T> producer) {
            InnerSubscriber<T>[] c;
            InnerSubscriber<T>[] u;
            do {
                c = (InnerSubscriber[]) this.subscribers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerSubscriber[(len + 1)];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!this.subscribers.compareAndSet(c, u));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void remove(InnerSubscriber<T> producer) {
            InnerSubscriber<T>[] c;
            InnerSubscriber<T>[] u;
            do {
                c = (InnerSubscriber[]) this.subscribers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= len) {
                            break;
                        } else if (c[i].equals(producer)) {
                            j = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (j >= 0) {
                        if (len == 1) {
                            u = EMPTY;
                        } else {
                            InnerSubscriber<T>[] u2 = new InnerSubscriber[(len - 1)];
                            System.arraycopy(c, 0, u2, 0, j);
                            System.arraycopy(c, j + 1, u2, j, (len - j) - 1);
                            u = u2;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(c, u));
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(Object term, boolean empty) {
            int i = 0;
            if (term != null) {
                if (!NotificationLite.isComplete(term)) {
                    Throwable t = NotificationLite.getError(term);
                    this.current.compareAndSet(this, (Object) null);
                    InnerSubscriber<?>[] a = (InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED);
                    if (a.length != 0) {
                        int length = a.length;
                        while (i < length) {
                            a[i].child.onError(t);
                            i++;
                        }
                    } else {
                        RxJavaPlugins.onError(t);
                    }
                    return true;
                } else if (empty) {
                    this.current.compareAndSet(this, (Object) null);
                    InnerSubscriber<?>[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED);
                    int length2 = innerSubscriberArr.length;
                    while (i < length2) {
                        innerSubscriberArr[i].child.onComplete();
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0164, code lost:
            if (r8 == 0) goto L_0x0177;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x0169, code lost:
            if (r1.sourceMode == 1) goto L_0x0177;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x016b, code lost:
            r1.upstream.get().request((long) r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x017b, code lost:
            if (r10 == 0) goto L_0x0181;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x017d, code lost:
            if (r19 != false) goto L_0x0181;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
            r8 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x0014, code lost:
            continue;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dispatch() {
            /*
                r27 = this;
                r1 = r27
                int r0 = r27.getAndIncrement()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                r0 = 1
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$InnerSubscriber<T>[]> r2 = r1.subscribers
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.flowable.FlowablePublish$InnerSubscriber[] r3 = (io.reactivex.internal.operators.flowable.FlowablePublish.InnerSubscriber[]) r3
                r4 = r3
                r3 = r0
            L_0x0014:
                java.lang.Object r0 = r1.terminalEvent
                io.reactivex.internal.fuseable.SimpleQueue<T> r5 = r1.queue
                if (r5 == 0) goto L_0x0023
                boolean r8 = r5.isEmpty()
                if (r8 == 0) goto L_0x0021
                goto L_0x0023
            L_0x0021:
                r8 = 0
                goto L_0x0024
            L_0x0023:
                r8 = 1
            L_0x0024:
                boolean r9 = r1.checkTerminated(r0, r8)
                if (r9 == 0) goto L_0x002b
                return
            L_0x002b:
                if (r8 != 0) goto L_0x0184
                int r9 = r4.length
                r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r12 = 0
                int r13 = r4.length
                r14 = 0
            L_0x0036:
                r15 = -9223372036854775808
                if (r14 >= r13) goto L_0x0059
                r6 = r4[r14]
                long r17 = r6.get()
                int r15 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
                if (r15 == 0) goto L_0x0050
                r19 = r8
                long r7 = r6.emitted
                long r7 = r17 - r7
                long r7 = java.lang.Math.min(r10, r7)
                r10 = r7
                goto L_0x0054
            L_0x0050:
                r19 = r8
                int r12 = r12 + 1
            L_0x0054:
                int r14 = r14 + 1
                r8 = r19
                goto L_0x0036
            L_0x0059:
                r19 = r8
                r6 = 1
                if (r9 != r12) goto L_0x009d
                java.lang.Object r8 = r1.terminalEvent
                java.lang.Object r0 = r5.poll()     // Catch:{ all -> 0x0066 }
                goto L_0x007f
            L_0x0066:
                r0 = move-exception
                r13 = r0
                r0 = r13
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r13 = r1.upstream
                java.lang.Object r13 = r13.get()
                org.reactivestreams.Subscription r13 = (org.reactivestreams.Subscription) r13
                r13.cancel()
                java.lang.Object r8 = io.reactivex.internal.util.NotificationLite.error(r0)
                r1.terminalEvent = r8
                r13 = 0
                r0 = r13
            L_0x007f:
                if (r0 != 0) goto L_0x0083
                r13 = 1
                goto L_0x0084
            L_0x0083:
                r13 = 0
            L_0x0084:
                boolean r13 = r1.checkTerminated(r8, r13)
                if (r13 == 0) goto L_0x008b
                return
            L_0x008b:
                int r13 = r1.sourceMode
                r14 = 1
                if (r13 == r14) goto L_0x0014
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r13 = r1.upstream
                java.lang.Object r13 = r13.get()
                org.reactivestreams.Subscription r13 = (org.reactivestreams.Subscription) r13
                r13.request(r6)
                goto L_0x0014
            L_0x009d:
                r8 = 0
            L_0x009e:
                long r13 = (long) r8
                int r13 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
                if (r13 >= 0) goto L_0x0160
                java.lang.Object r13 = r1.terminalEvent
                java.lang.Object r0 = r5.poll()     // Catch:{ all -> 0x00aa }
                goto L_0x00c3
            L_0x00aa:
                r0 = move-exception
                r14 = r0
                r0 = r14
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r14 = r1.upstream
                java.lang.Object r14 = r14.get()
                org.reactivestreams.Subscription r14 = (org.reactivestreams.Subscription) r14
                r14.cancel()
                java.lang.Object r13 = io.reactivex.internal.util.NotificationLite.error(r0)
                r1.terminalEvent = r13
                r14 = 0
                r0 = r14
            L_0x00c3:
                if (r0 != 0) goto L_0x00c7
                r14 = 1
                goto L_0x00c8
            L_0x00c7:
                r14 = 0
            L_0x00c8:
                boolean r17 = r1.checkTerminated(r13, r14)
                if (r17 == 0) goto L_0x00cf
                return
            L_0x00cf:
                if (r14 == 0) goto L_0x00da
                r22 = r5
                r21 = r12
                r0 = r13
                r19 = r14
                goto L_0x0164
            L_0x00da:
                java.lang.Object r6 = io.reactivex.internal.util.NotificationLite.getValue(r0)
                r7 = 0
                int r15 = r4.length
                r16 = r0
                r0 = 0
            L_0x00e3:
                if (r0 >= r15) goto L_0x0125
                r22 = r5
                r5 = r4[r0]
                long r23 = r5.get()
                r19 = -9223372036854775808
                int r21 = (r23 > r19 ? 1 : (r23 == r19 ? 0 : -1))
                if (r21 == 0) goto L_0x0115
                r25 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r21 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1))
                if (r21 == 0) goto L_0x0109
                r21 = r12
                r25 = r13
                long r12 = r5.emitted
                r17 = 1
                long r12 = r12 + r17
                r5.emitted = r12
                goto L_0x010f
            L_0x0109:
                r21 = r12
                r25 = r13
                r17 = 1
            L_0x010f:
                org.reactivestreams.Subscriber<? super T> r12 = r5.child
                r12.onNext(r6)
                goto L_0x011c
            L_0x0115:
                r21 = r12
                r25 = r13
                r17 = 1
                r7 = 1
            L_0x011c:
                int r0 = r0 + 1
                r12 = r21
                r5 = r22
                r13 = r25
                goto L_0x00e3
            L_0x0125:
                r22 = r5
                r21 = r12
                r25 = r13
                r17 = 1
                r19 = -9223372036854775808
                int r8 = r8 + 1
                java.lang.Object r0 = r2.get()
                io.reactivex.internal.operators.flowable.FlowablePublish$InnerSubscriber[] r0 = (io.reactivex.internal.operators.flowable.FlowablePublish.InnerSubscriber[]) r0
                if (r7 != 0) goto L_0x014a
                if (r0 == r4) goto L_0x013c
                goto L_0x014a
            L_0x013c:
                r6 = r17
                r15 = r19
                r12 = r21
                r5 = r22
                r0 = r25
                r19 = r14
                goto L_0x009e
            L_0x014a:
                r4 = r0
                if (r8 == 0) goto L_0x0014
                int r5 = r1.sourceMode
                r12 = 1
                if (r5 == r12) goto L_0x0014
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r5 = r1.upstream
                java.lang.Object r5 = r5.get()
                org.reactivestreams.Subscription r5 = (org.reactivestreams.Subscription) r5
                long r12 = (long) r8
                r5.request(r12)
                goto L_0x0014
            L_0x0160:
                r22 = r5
                r21 = r12
            L_0x0164:
                if (r8 == 0) goto L_0x0177
                int r5 = r1.sourceMode
                r6 = 1
                if (r5 == r6) goto L_0x0177
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r5 = r1.upstream
                java.lang.Object r5 = r5.get()
                org.reactivestreams.Subscription r5 = (org.reactivestreams.Subscription) r5
                long r6 = (long) r8
                r5.request(r6)
            L_0x0177:
                r5 = 0
                int r5 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                if (r5 == 0) goto L_0x0181
                if (r19 != 0) goto L_0x0181
                goto L_0x0014
            L_0x0181:
                r8 = r19
                goto L_0x0188
            L_0x0184:
                r22 = r5
                r19 = r8
            L_0x0188:
                int r5 = -r3
                int r3 = r1.addAndGet(r5)
                if (r3 != 0) goto L_0x0191
                return
            L_0x0191:
                java.lang.Object r5 = r2.get()
                r4 = r5
                io.reactivex.internal.operators.flowable.FlowablePublish$InnerSubscriber[] r4 = (io.reactivex.internal.operators.flowable.FlowablePublish.InnerSubscriber[]) r4
                goto L_0x0014
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublish.PublishSubscriber.dispatch():void");
        }
    }

    static final class InnerSubscriber<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        long emitted;
        volatile PublishSubscriber<T> parent;

        InnerSubscriber(Subscriber<? super T> child2) {
            this.child = child2;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.addCancel(this, n);
                PublishSubscriber<T> p = this.parent;
                if (p != null) {
                    p.dispatch();
                }
            }
        }

        public void cancel() {
            PublishSubscriber<T> p;
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE && (p = this.parent) != null) {
                p.remove(this);
                p.dispatch();
            }
        }
    }

    static final class FlowablePublisher<T> implements Publisher<T> {
        private final int bufferSize;
        private final AtomicReference<PublishSubscriber<T>> curr;

        FlowablePublisher(AtomicReference<PublishSubscriber<T>> curr2, int bufferSize2) {
            this.curr = curr2;
            this.bufferSize = bufferSize2;
        }

        public void subscribe(Subscriber<? super T> child) {
            PublishSubscriber<T> r;
            InnerSubscriber<T> inner = new InnerSubscriber<>(child);
            child.onSubscribe(inner);
            while (true) {
                r = this.curr.get();
                if (r == null || r.isDisposed()) {
                    PublishSubscriber<T> u = new PublishSubscriber<>(this.curr, this.bufferSize);
                    if (!this.curr.compareAndSet(r, u)) {
                        continue;
                    } else {
                        r = u;
                    }
                }
                if (r.add(inner)) {
                    break;
                }
            }
            if (inner.get() == Long.MIN_VALUE) {
                r.remove(inner);
            } else {
                inner.parent = r;
            }
            r.dispatch();
        }
    }
}
