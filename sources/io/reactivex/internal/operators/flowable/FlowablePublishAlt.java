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

/* loaded from: classes.dex */
public final class FlowablePublishAlt<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, ResettableConnectable {
    final int bufferSize;
    final AtomicReference<PublishConnection<T>> current = new AtomicReference<>();
    final Publisher<T> source;

    public FlowablePublishAlt(Publisher<T> source, int bufferSize) {
        this.source = source;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.source;
    }

    public int publishBufferSize() {
        return this.bufferSize;
    }

    @Override // io.reactivex.flowables.ConnectableFlowable
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

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        PublishConnection<T> conn;
        while (true) {
            conn = this.current.get();
            if (conn != null) {
                break;
            }
            PublishConnection<T> fresh = new PublishConnection<>(this.current, this.bufferSize);
            if (this.current.compareAndSet(conn, fresh)) {
                conn = fresh;
                break;
            }
        }
        InnerSubscription<T> inner = new InnerSubscription<>(s, conn);
        s.onSubscribe(inner);
        if (conn.add(inner)) {
            if (inner.isCancelled()) {
                conn.remove(inner);
                return;
            } else {
                conn.drain();
                return;
            }
        }
        Throwable ex = conn.error;
        if (ex != null) {
            s.onError(ex);
        } else {
            s.onComplete();
        }
    }

    @Override // io.reactivex.internal.disposables.ResettableConnectable
    public void resetIf(Disposable connection) {
        this.current.compareAndSet((PublishConnection) connection, null);
    }

    /* loaded from: classes.dex */
    static final class PublishConnection<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = -1672047311619175801L;
        final int bufferSize;
        int consumed;
        final AtomicReference<PublishConnection<T>> current;
        volatile boolean done;
        Throwable error;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        final AtomicReference<Subscription> upstream = new AtomicReference<>();
        final AtomicBoolean connect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        PublishConnection(AtomicReference<PublishConnection<T>> current, int bufferSize) {
            this.current = current;
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.subscribers.getAndSet(TERMINATED);
            this.current.compareAndSet(this, null);
            SubscriptionHelper.cancel(this.upstream);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
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
                        s.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                s.request(this.bufferSize);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            } else {
                drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            int consumed;
            boolean d;
            boolean d2;
            int i;
            if (getAndIncrement() != 0) {
                return;
            }
            SimpleQueue<T> queue = this.queue;
            int consumed2 = this.consumed;
            int i2 = this.bufferSize;
            int limit = i2 - (i2 >> 2);
            boolean async = this.sourceMode != 1;
            int missed = 1;
            int missed2 = consumed2;
            SimpleQueue<T> queue2 = queue;
            while (true) {
                if (queue2 != null) {
                    long minDemand = LongCompanionObject.MAX_VALUE;
                    boolean hasDemand = false;
                    InnerSubscription<T>[] innerSubscriptions = this.subscribers.get();
                    for (InnerSubscription<T> inner : innerSubscriptions) {
                        long request = inner.get();
                        if (request != Long.MIN_VALUE) {
                            hasDemand = true;
                            minDemand = Math.min(request - inner.emitted, minDemand);
                        }
                    }
                    if (hasDemand) {
                        consumed = missed2;
                    } else {
                        minDemand = 0;
                        consumed = missed2;
                    }
                    while (minDemand != 0) {
                        boolean empty = this.done;
                        try {
                            T v = queue2.poll();
                            boolean empty2 = v == null;
                            if (checkTerminated(empty, empty2)) {
                                return;
                            }
                            if (empty2) {
                                break;
                            }
                            int length = innerSubscriptions.length;
                            int i3 = 0;
                            while (i3 < length) {
                                InnerSubscription<T> inner2 = innerSubscriptions[i3];
                                if (inner2.isCancelled()) {
                                    d = empty;
                                    d2 = empty2;
                                    i = length;
                                } else {
                                    d = empty;
                                    inner2.downstream.onNext(v);
                                    d2 = empty2;
                                    i = length;
                                    inner2.emitted++;
                                }
                                i3++;
                                empty2 = d2;
                                empty = d;
                                length = i;
                            }
                            if (async && (consumed = consumed + 1) == limit) {
                                consumed = 0;
                                this.upstream.get().request(limit);
                            }
                            minDemand--;
                            if (innerSubscriptions != this.subscribers.get()) {
                                missed2 = consumed;
                                break;
                            }
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            this.upstream.get().cancel();
                            queue2.clear();
                            this.done = true;
                            signalError(ex);
                            return;
                        }
                    }
                    if (!checkTerminated(this.done, queue2.isEmpty())) {
                        missed2 = consumed;
                    } else {
                        return;
                    }
                }
                this.consumed = missed2;
                missed = addAndGet(-missed);
                if (missed != 0) {
                    if (queue2 == null) {
                        queue2 = this.queue;
                    }
                } else {
                    return;
                }
            }
        }

        boolean checkTerminated(boolean isDone, boolean isEmpty) {
            InnerSubscription<T>[] andSet;
            if (!isDone || !isEmpty) {
                return false;
            }
            Throwable ex = this.error;
            if (ex != null) {
                signalError(ex);
                return true;
            }
            for (InnerSubscription<T> inner : this.subscribers.getAndSet(TERMINATED)) {
                if (!inner.isCancelled()) {
                    inner.downstream.onComplete();
                }
            }
            return true;
        }

        void signalError(Throwable ex) {
            InnerSubscription<T>[] andSet;
            for (InnerSubscription<T> inner : this.subscribers.getAndSet(TERMINATED)) {
                if (!inner.isCancelled()) {
                    inner.downstream.onError(ex);
                }
            }
        }

        boolean add(InnerSubscription<T> inner) {
            InnerSubscription<T>[] c;
            InnerSubscription<T>[] u;
            do {
                c = this.subscribers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerSubscription[len + 1];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = inner;
            } while (!this.subscribers.compareAndSet(c, u));
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void remove(InnerSubscription<T> inner) {
            InnerSubscription<T>[] c;
            InnerSubscription<T>[] u;
            do {
                c = this.subscribers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    int i = 0;
                    while (true) {
                        if (i >= len) {
                            break;
                        } else if (c[i] != inner) {
                            i++;
                        } else {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (len == 1) {
                        u = EMPTY;
                    } else {
                        InnerSubscription<T>[] u2 = new InnerSubscription[len - 1];
                        System.arraycopy(c, 0, u2, 0, j);
                        System.arraycopy(c, j + 1, u2, j, (len - j) - 1);
                        u = u2;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(c, u));
        }
    }

    /* loaded from: classes.dex */
    static final class InnerSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 2845000326761540265L;
        final Subscriber<? super T> downstream;
        long emitted;
        final PublishConnection<T> parent;

        InnerSubscription(Subscriber<? super T> downstream, PublishConnection<T> parent) {
            this.downstream = downstream;
            this.parent = parent;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            BackpressureHelper.addCancel(this, n);
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscription
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
