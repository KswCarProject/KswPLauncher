package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowablePublishAlt<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, ResettableConnectable {
    final int bufferSize;
    final AtomicReference<PublishConnection<T>> current = new AtomicReference<>();
    final Publisher<T> source;

    public FlowablePublishAlt(Publisher<T> source2, int bufferSize2) {
        this.source = source2;
        this.bufferSize = bufferSize2;
    }

    public Publisher<T> source() {
        return this.source;
    }

    public int publishBufferSize() {
        return this.bufferSize;
    }

    public void connect(Consumer<? super Disposable> connection) {
        PublishConnection<T> conn;
        while (true) {
            conn = this.current.get();
            if (conn != null && !conn.isDisposed()) {
                break;
            }
            PublishConnection<T> fresh = new PublishConnection<>(this.current, this.bufferSize);
            if (this.current.compareAndSet(conn, fresh)) {
                conn = fresh;
                break;
            }
        }
        boolean z = true;
        if (conn.connect.get() || !conn.connect.compareAndSet(false, true)) {
            z = false;
        }
        boolean doConnect = z;
        try {
            connection.accept(conn);
            if (doConnect) {
                this.source.subscribe(conn);
            }
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribeActual(org.reactivestreams.Subscriber<? super T> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublishAlt$PublishConnection<T>> r0 = r4.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.flowable.FlowablePublishAlt$PublishConnection r0 = (io.reactivex.internal.operators.flowable.FlowablePublishAlt.PublishConnection) r0
            if (r0 != 0) goto L_0x001d
            io.reactivex.internal.operators.flowable.FlowablePublishAlt$PublishConnection r1 = new io.reactivex.internal.operators.flowable.FlowablePublishAlt$PublishConnection
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublishAlt$PublishConnection<T>> r2 = r4.current
            int r3 = r4.bufferSize
            r1.<init>(r2, r3)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublishAlt$PublishConnection<T>> r2 = r4.current
            boolean r2 = r2.compareAndSet(r0, r1)
            if (r2 != 0) goto L_0x001c
            goto L_0x0000
        L_0x001c:
            r0 = r1
        L_0x001d:
            io.reactivex.internal.operators.flowable.FlowablePublishAlt$InnerSubscription r1 = new io.reactivex.internal.operators.flowable.FlowablePublishAlt$InnerSubscription
            r1.<init>(r5, r0)
            r5.onSubscribe(r1)
            boolean r2 = r0.add(r1)
            if (r2 == 0) goto L_0x0039
            boolean r2 = r1.isCancelled()
            if (r2 == 0) goto L_0x0035
            r0.remove(r1)
            goto L_0x0038
        L_0x0035:
            r0.drain()
        L_0x0038:
            return
        L_0x0039:
            java.lang.Throwable r2 = r0.error
            if (r2 == 0) goto L_0x0041
            r5.onError(r2)
            goto L_0x0044
        L_0x0041:
            r5.onComplete()
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublishAlt.subscribeActual(org.reactivestreams.Subscriber):void");
    }

    public void resetIf(Disposable connection) {
        this.current.compareAndSet((PublishConnection) connection, (Object) null);
    }

    static final class PublishConnection<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = -1672047311619175801L;
        final int bufferSize;
        final AtomicBoolean connect = new AtomicBoolean();
        int consumed;
        final AtomicReference<PublishConnection<T>> current;
        volatile boolean done;
        Throwable error;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        final AtomicReference<InnerSubscription<T>[]> subscribers;
        final AtomicReference<Subscription> upstream = new AtomicReference<>();

        PublishConnection(AtomicReference<PublishConnection<T>> current2, int bufferSize2) {
            this.current = current2;
            this.bufferSize = bufferSize2;
            this.subscribers = new AtomicReference<>(EMPTY);
        }

        public void dispose() {
            this.subscribers.getAndSet(TERMINATED);
            this.current.compareAndSet(this, (Object) null);
            SubscriptionHelper.cancel(this.upstream);
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
                        this.done = true;
                        drain();
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
                drain();
            } else {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            int consumed2;
            int i;
            boolean d;
            boolean d2;
            if (getAndIncrement() == 0) {
                SimpleQueue<T> queue2 = this.queue;
                int consumed3 = this.consumed;
                int i2 = this.bufferSize;
                int limit = i2 - (i2 >> 2);
                boolean async = this.sourceMode != 1;
                SimpleQueue<T> simpleQueue = queue2;
                int missed = 1;
                int consumed4 = consumed3;
                SimpleQueue<T> queue3 = simpleQueue;
                while (true) {
                    if (queue3 != null) {
                        long minDemand = LongCompanionObject.MAX_VALUE;
                        boolean hasDemand = false;
                        InnerSubscription<T>[] innerSubscriptions = (InnerSubscription[]) this.subscribers.get();
                        for (InnerSubscription<T> inner : innerSubscriptions) {
                            long request = inner.get();
                            if (request != Long.MIN_VALUE) {
                                hasDemand = true;
                                minDemand = Math.min(request - inner.emitted, minDemand);
                            }
                        }
                        if (!hasDemand) {
                            minDemand = 0;
                            consumed2 = consumed4;
                        } else {
                            consumed2 = consumed4;
                        }
                        do {
                            if (minDemand != 0) {
                                boolean empty = this.done;
                                try {
                                    T v = queue3.poll();
                                    boolean empty2 = v == null;
                                    if (!checkTerminated(empty, empty2)) {
                                        if (!empty2) {
                                            int length = innerSubscriptions.length;
                                            int i3 = 0;
                                            while (i3 < length) {
                                                InnerSubscription<T> inner2 = innerSubscriptions[i3];
                                                if (!inner2.isCancelled()) {
                                                    d = empty;
                                                    inner2.downstream.onNext(v);
                                                    d2 = empty2;
                                                    i = length;
                                                    inner2.emitted++;
                                                } else {
                                                    d = empty;
                                                    d2 = empty2;
                                                    i = length;
                                                }
                                                i3++;
                                                empty2 = d2;
                                                empty = d;
                                                length = i;
                                            }
                                            boolean d3 = empty;
                                            boolean d4 = empty2;
                                            if (async && (consumed2 = consumed2 + 1) == limit) {
                                                consumed2 = 0;
                                                this.upstream.get().request((long) limit);
                                            }
                                            minDemand--;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th) {
                                    boolean z = empty;
                                    Throwable ex = th;
                                    Exceptions.throwIfFatal(ex);
                                    this.upstream.get().cancel();
                                    queue3.clear();
                                    this.done = true;
                                    signalError(ex);
                                    return;
                                }
                            }
                            if (!checkTerminated(this.done, queue3.isEmpty())) {
                                consumed4 = consumed2;
                            } else {
                                return;
                            }
                        } while (innerSubscriptions == this.subscribers.get());
                        consumed4 = consumed2;
                    }
                    this.consumed = consumed4;
                    missed = addAndGet(-missed);
                    if (missed != 0) {
                        if (queue3 == null) {
                            queue3 = this.queue;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean isDone, boolean isEmpty) {
            if (!isDone || !isEmpty) {
                return false;
            }
            Throwable ex = this.error;
            if (ex != null) {
                signalError(ex);
                return true;
            }
            for (InnerSubscription<T> inner : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (!inner.isCancelled()) {
                    inner.downstream.onComplete();
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void signalError(Throwable ex) {
            for (InnerSubscription<T> inner : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (!inner.isCancelled()) {
                    inner.downstream.onError(ex);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerSubscription<T> inner) {
            InnerSubscription<T>[] c;
            InnerSubscription<T>[] u;
            do {
                c = (InnerSubscription[]) this.subscribers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerSubscription[(len + 1)];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = inner;
            } while (!this.subscribers.compareAndSet(c, u));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void remove(InnerSubscription<T> inner) {
            InnerSubscription<T>[] c;
            InnerSubscription<T>[] u;
            do {
                c = (InnerSubscription[]) this.subscribers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= len) {
                            break;
                        } else if (c[i] == inner) {
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
                            InnerSubscription<T>[] u2 = new InnerSubscription[(len - 1)];
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
    }

    static final class InnerSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 2845000326761540265L;
        final Subscriber<? super T> downstream;
        long emitted;
        final PublishConnection<T> parent;

        InnerSubscription(Subscriber<? super T> downstream2, PublishConnection<T> parent2) {
            this.downstream = downstream2;
            this.parent = parent2;
        }

        public void request(long n) {
            BackpressureHelper.addCancel(this, n);
            this.parent.drain();
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }
}
