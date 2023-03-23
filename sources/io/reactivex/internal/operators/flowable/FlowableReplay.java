package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.subscribers.SubscriberResourceWrapper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableReplay<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, ResettableConnectable {
    static final Callable DEFAULT_UNBOUNDED_FACTORY = new DefaultUnboundedFactory();
    final Callable<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Publisher<T> onSubscribe;
    final Flowable<T> source;

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerSubscription<T> innerSubscription);
    }

    public static <U, R> Flowable<R> multicastSelector(Callable<? extends ConnectableFlowable<U>> connectableFactory, Function<? super Flowable<U>, ? extends Publisher<R>> selector) {
        return new MulticastFlowable(connectableFactory, selector);
    }

    public static <T> ConnectableFlowable<T> observeOn(ConnectableFlowable<T> cf, Scheduler scheduler) {
        return RxJavaPlugins.onAssembly(new ConnectableFlowableReplay(cf, cf.observeOn(scheduler)));
    }

    public static <T> ConnectableFlowable<T> createFrom(Flowable<? extends T> source2) {
        return create(source2, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source2, int bufferSize) {
        if (bufferSize == Integer.MAX_VALUE) {
            return createFrom(source2);
        }
        return create(source2, new ReplayBufferTask(bufferSize));
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source2, long maxAge, TimeUnit unit, Scheduler scheduler) {
        return create(source2, maxAge, unit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source2, long maxAge, TimeUnit unit, Scheduler scheduler, int bufferSize) {
        return create(source2, new ScheduledReplayBufferTask(bufferSize, maxAge, unit, scheduler));
    }

    static <T> ConnectableFlowable<T> create(Flowable<T> source2, Callable<? extends ReplayBuffer<T>> bufferFactory2) {
        AtomicReference<ReplaySubscriber<T>> curr = new AtomicReference<>();
        return RxJavaPlugins.onAssembly(new FlowableReplay(new ReplayPublisher<>(curr, bufferFactory2), source2, curr, bufferFactory2));
    }

    private FlowableReplay(Publisher<T> onSubscribe2, Flowable<T> source2, AtomicReference<ReplaySubscriber<T>> current2, Callable<? extends ReplayBuffer<T>> bufferFactory2) {
        this.onSubscribe = onSubscribe2;
        this.source = source2;
        this.current = current2;
        this.bufferFactory = bufferFactory2;
    }

    public Publisher<T> source() {
        return this.source;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        this.onSubscribe.subscribe(s);
    }

    public void resetIf(Disposable connectionObject) {
        this.current.compareAndSet((ReplaySubscriber) connectionObject, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r7) {
        /*
            r6 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r0 = r6.current
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplaySubscriber) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0028
        L_0x0010:
            java.util.concurrent.Callable<? extends io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer<T>> r1 = r6.bufferFactory     // Catch:{ all -> 0x005a }
            java.lang.Object r1 = r1.call()     // Catch:{ all -> 0x005a }
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer r1 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplayBuffer) r1     // Catch:{ all -> 0x005a }
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r2 = new io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber
            r2.<init>(r1)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r3 = r6.current
            boolean r3 = r3.compareAndSet(r0, r2)
            if (r3 != 0) goto L_0x0027
            goto L_0x0000
        L_0x0027:
            r0 = r2
        L_0x0028:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x003c
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x003c
            r1 = r2
            goto L_0x003d
        L_0x003c:
            r1 = r3
        L_0x003d:
            r7.accept(r0)     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0049
            io.reactivex.Flowable<T> r2 = r6.source
            r2.subscribe(r0)
        L_0x0049:
            return
        L_0x004a:
            r4 = move-exception
            if (r1 == 0) goto L_0x0052
            java.util.concurrent.atomic.AtomicBoolean r5 = r0.shouldConnect
            r5.compareAndSet(r2, r3)
        L_0x0052:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
            java.lang.RuntimeException r2 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r4)
            throw r2
        L_0x005a:
            r1 = move-exception
            io.reactivex.exceptions.Exceptions.throwIfFatal(r1)
            java.lang.RuntimeException r2 = io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.connect(io.reactivex.functions.Consumer):void");
    }

    static final class ReplaySubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = 7224554242710036740L;
        final ReplayBuffer<T> buffer;
        boolean done;
        final AtomicInteger management = new AtomicInteger();
        long maxChildRequested;
        long maxUpstreamRequested;
        final AtomicBoolean shouldConnect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        ReplaySubscriber(ReplayBuffer<T> buffer2) {
            this.buffer = buffer2;
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerSubscription<T> producer) {
            InnerSubscription<T>[] c;
            InnerSubscription<T>[] u;
            if (producer != null) {
                do {
                    c = (InnerSubscription[]) this.subscribers.get();
                    if (c == TERMINATED) {
                        return false;
                    }
                    int len = c.length;
                    u = new InnerSubscription[(len + 1)];
                    System.arraycopy(c, 0, u, 0, len);
                    u[len] = producer;
                } while (!this.subscribers.compareAndSet(c, u));
                return true;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void remove(InnerSubscription<T> p) {
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
                        } else if (c[i].equals(p)) {
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

        public void onSubscribe(Subscription p) {
            if (SubscriptionHelper.setOnce(this, p)) {
                manageRequests();
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(rp);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(rp);
                }
            }
        }

        public void onError(Throwable e) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(e);
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(rp);
                }
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(rp);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void manageRequests() {
            if (this.management.getAndIncrement() == 0) {
                int missed = 1;
                while (!isDisposed()) {
                    InnerSubscription<T>[] a = (InnerSubscription[]) this.subscribers.get();
                    long ri = this.maxChildRequested;
                    long maxTotalRequests = ri;
                    for (InnerSubscription<T> rp : a) {
                        maxTotalRequests = Math.max(maxTotalRequests, rp.totalRequested.get());
                    }
                    long ur = this.maxUpstreamRequested;
                    Subscription p = (Subscription) get();
                    long diff = maxTotalRequests - ri;
                    if (diff != 0) {
                        this.maxChildRequested = maxTotalRequests;
                        if (p == null) {
                            long u = ur + diff;
                            if (u < 0) {
                                u = LongCompanionObject.MAX_VALUE;
                            }
                            this.maxUpstreamRequested = u;
                        } else if (ur != 0) {
                            this.maxUpstreamRequested = 0;
                            p.request(ur + diff);
                        } else {
                            p.request(diff);
                        }
                    } else if (!(ur == 0 || p == null)) {
                        this.maxUpstreamRequested = 0;
                        p.request(ur);
                    }
                    missed = this.management.addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class InnerSubscription<T> extends AtomicLong implements Subscription, Disposable {
        static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        InnerSubscription(ReplaySubscriber<T> parent2, Subscriber<? super T> child2) {
            this.parent = parent2;
            this.child = child2;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n) && BackpressureHelper.addCancel(this, n) != Long.MIN_VALUE) {
                BackpressureHelper.add(this.totalRequested, n);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long n) {
            return BackpressureHelper.producedCancel(this, n);
        }

        public boolean isDisposed() {
            return get() == Long.MIN_VALUE;
        }

        public void cancel() {
            dispose();
        }

        public void dispose() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests();
                this.index = null;
            }
        }

        /* access modifiers changed from: package-private */
        public <U> U index() {
            return this.index;
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int capacityHint) {
            super(capacityHint);
        }

        public void next(T value) {
            add(NotificationLite.next(value));
            this.size++;
        }

        public void error(Throwable e) {
            add(NotificationLite.error(e));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
            if (r15.isDisposed() == false) goto L_0x0016;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
            r1 = r14.size;
            r2 = (java.lang.Integer) r15.index();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
            if (r2 == null) goto L_0x0026;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
            r4 = r2.intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
            r4 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
            r5 = r15.get();
            r7 = r5;
            r9 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
            if (r5 == 0) goto L_0x0067;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0034, code lost:
            if (r4 >= r1) goto L_0x0067;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
            r11 = get(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
            if (io.reactivex.internal.util.NotificationLite.accept(r11, r0) == false) goto L_0x0041;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0040, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0046, code lost:
            if (r15.isDisposed() == false) goto L_0x0049;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0048, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0049, code lost:
            r4 = r4 + 1;
            r5 = r5 - 1;
            r9 = r9 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0050, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0051, code lost:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r3);
            r15.dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
            if (io.reactivex.internal.util.NotificationLite.isError(r11) != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0063, code lost:
            r0.onError(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0069, code lost:
            if (r9 == 0) goto L_0x007d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x006b, code lost:
            r15.index = java.lang.Integer.valueOf(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
            if (r7 == kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L_0x007d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x007a, code lost:
            r15.produced(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x007d, code lost:
            monitor-enter(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0080, code lost:
            if (r15.missed != false) goto L_0x0086;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0082, code lost:
            r15.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0084, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0085, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0086, code lost:
            r15.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0088, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
            r0 = r15.child;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r15) {
            /*
                r14 = this;
                monitor-enter(r15)
                boolean r0 = r15.emitting     // Catch:{ all -> 0x008d }
                r1 = 1
                if (r0 == 0) goto L_0x000a
                r15.missed = r1     // Catch:{ all -> 0x008d }
                monitor-exit(r15)     // Catch:{ all -> 0x008d }
                return
            L_0x000a:
                r15.emitting = r1     // Catch:{ all -> 0x008d }
                monitor-exit(r15)     // Catch:{ all -> 0x008d }
                org.reactivestreams.Subscriber<? super T> r0 = r15.child
            L_0x000f:
                boolean r1 = r15.isDisposed()
                if (r1 == 0) goto L_0x0016
                return
            L_0x0016:
                int r1 = r14.size
                java.lang.Object r2 = r15.index()
                java.lang.Integer r2 = (java.lang.Integer) r2
                r3 = 0
                if (r2 == 0) goto L_0x0026
                int r4 = r2.intValue()
                goto L_0x0027
            L_0x0026:
                r4 = r3
            L_0x0027:
                long r5 = r15.get()
                r7 = r5
                r9 = 0
            L_0x002e:
                r11 = 0
                int r13 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
                if (r13 == 0) goto L_0x0067
                if (r4 >= r1) goto L_0x0067
                java.lang.Object r11 = r14.get(r4)
                boolean r12 = io.reactivex.internal.util.NotificationLite.accept((java.lang.Object) r11, r0)     // Catch:{ all -> 0x0050 }
                if (r12 == 0) goto L_0x0041
                return
            L_0x0041:
                boolean r12 = r15.isDisposed()
                if (r12 == 0) goto L_0x0049
                return
            L_0x0049:
                int r4 = r4 + 1
                r12 = 1
                long r5 = r5 - r12
                long r9 = r9 + r12
                goto L_0x002e
            L_0x0050:
                r3 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r3)
                r15.dispose()
                boolean r12 = io.reactivex.internal.util.NotificationLite.isError(r11)
                if (r12 != 0) goto L_0x0066
                boolean r12 = io.reactivex.internal.util.NotificationLite.isComplete(r11)
                if (r12 != 0) goto L_0x0066
                r0.onError(r3)
            L_0x0066:
                return
            L_0x0067:
                int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r11 == 0) goto L_0x007d
                java.lang.Integer r11 = java.lang.Integer.valueOf(r4)
                r15.index = r11
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r11 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r11 == 0) goto L_0x007d
                r15.produced(r9)
            L_0x007d:
                monitor-enter(r15)
                boolean r11 = r15.missed     // Catch:{ all -> 0x008a }
                if (r11 != 0) goto L_0x0086
                r15.emitting = r3     // Catch:{ all -> 0x008a }
                monitor-exit(r15)     // Catch:{ all -> 0x008a }
                return
            L_0x0086:
                r15.missed = r3     // Catch:{ all -> 0x008a }
                monitor-exit(r15)     // Catch:{ all -> 0x008a }
                goto L_0x000f
            L_0x008a:
                r3 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x008a }
                throw r3
            L_0x008d:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x008d }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.UnboundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        Node(Object value2, long index2) {
            this.value = value2;
            this.index = index2;
        }
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        BoundedReplayBuffer() {
            Node n = new Node((Object) null, 0);
            this.tail = n;
            set(n);
        }

        /* access modifiers changed from: package-private */
        public final void addLast(Node n) {
            this.tail.set(n);
            this.tail = n;
            this.size++;
        }

        /* access modifiers changed from: package-private */
        public final void removeFirst() {
            Node next = (Node) ((Node) get()).get();
            if (next != null) {
                this.size--;
                setFirst(next);
                return;
            }
            throw new IllegalStateException("Empty list!");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: io.reactivex.internal.operators.flowable.FlowableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void removeSome(int r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r0
            L_0x0006:
                if (r3 <= 0) goto L_0x0018
                java.lang.Object r1 = r0.get()
                r0 = r1
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r0
                int r3 = r3 + -1
                int r1 = r2.size
                int r1 = r1 + -1
                r2.size = r1
                goto L_0x0006
            L_0x0018:
                r2.setFirst(r0)
                java.lang.Object r1 = r2.get()
                r0 = r1
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r0
                java.lang.Object r1 = r0.get()
                if (r1 != 0) goto L_0x002a
                r2.tail = r0
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer.removeSome(int):void");
        }

        /* access modifiers changed from: package-private */
        public final void setFirst(Node n) {
            set(n);
        }

        public final void next(T value) {
            Object o = enterTransform(NotificationLite.next(value));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncate();
        }

        public final void error(Throwable e) {
            Object o = enterTransform(NotificationLite.error(e));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        public final void complete() {
            Object o = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        /* access modifiers changed from: package-private */
        public final void trimHead() {
            Node head = (Node) get();
            if (head.value != null) {
                Node n = new Node((Object) null, 0);
                n.lazySet(head.get());
                set(n);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
            if (r15.isDisposed() == false) goto L_0x0017;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
            r15.index = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
            r3 = r15.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
            if (r3 != kotlin.jvm.internal.LongCompanionObject.MAX_VALUE) goto L_0x0027;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
            r0 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
            r0 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
            r6 = 0;
            r8 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r15.index();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
            if (r8 != null) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
            r8 = getHead();
            r15.index = r8;
            io.reactivex.internal.util.BackpressureHelper.add(r15.totalRequested, r8.index);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
            if (r3 == 0) goto L_0x0089;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
            r11 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r8.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
            if (r11 == null) goto L_0x0089;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
            r9 = leaveTransform(r11.value);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0059, code lost:
            if (io.reactivex.internal.util.NotificationLite.accept(r9, r15.child) == false) goto L_0x005e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
            r15.index = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
            r6 = r6 + 1;
            r3 = r3 - 1;
            r8 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
            if (r15.isDisposed() == false) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x006a, code lost:
            r15.index = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x006c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x006e, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x006f, code lost:
            io.reactivex.exceptions.Exceptions.throwIfFatal(r1);
            r15.index = null;
            r15.dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x007b, code lost:
            if (io.reactivex.internal.util.NotificationLite.isError(r9) != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0083, code lost:
            r15.child.onError(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x008b, code lost:
            if (r6 == 0) goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x008d, code lost:
            r15.index = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x008f, code lost:
            if (r0 != false) goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0091, code lost:
            r15.produced(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0094, code lost:
            monitor-enter(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
            if (r15.missed != false) goto L_0x009d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0099, code lost:
            r15.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x009b, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x009c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x009d, code lost:
            r15.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x009f, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r15) {
            /*
                r14 = this;
                monitor-enter(r15)
                boolean r0 = r15.emitting     // Catch:{ all -> 0x00a5 }
                r1 = 1
                if (r0 == 0) goto L_0x000a
                r15.missed = r1     // Catch:{ all -> 0x00a5 }
                monitor-exit(r15)     // Catch:{ all -> 0x00a5 }
                return
            L_0x000a:
                r15.emitting = r1     // Catch:{ all -> 0x00a5 }
                monitor-exit(r15)     // Catch:{ all -> 0x00a5 }
            L_0x000d:
                boolean r0 = r15.isDisposed()
                r2 = 0
                if (r0 == 0) goto L_0x0017
                r15.index = r2
                return
            L_0x0017:
                long r3 = r15.get()
                r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                r5 = 0
                if (r0 != 0) goto L_0x0027
                r0 = r1
                goto L_0x0028
            L_0x0027:
                r0 = r5
            L_0x0028:
                r6 = 0
                java.lang.Object r8 = r15.index()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r8 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r8
                if (r8 != 0) goto L_0x003f
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r8 = r14.getHead()
                r15.index = r8
                java.util.concurrent.atomic.AtomicLong r9 = r15.totalRequested
                long r10 = r8.index
                io.reactivex.internal.util.BackpressureHelper.add(r9, r10)
            L_0x003f:
                r9 = 0
                int r11 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
                if (r11 == 0) goto L_0x0089
                java.lang.Object r11 = r8.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r11 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r11
                if (r11 == 0) goto L_0x0089
                java.lang.Object r9 = r11.value
                java.lang.Object r9 = r14.leaveTransform(r9)
                org.reactivestreams.Subscriber<? super T> r10 = r15.child     // Catch:{ all -> 0x006e }
                boolean r10 = io.reactivex.internal.util.NotificationLite.accept((java.lang.Object) r9, r10)     // Catch:{ all -> 0x006e }
                if (r10 == 0) goto L_0x005e
                r15.index = r2     // Catch:{ all -> 0x006e }
                return
            L_0x005e:
                r12 = 1
                long r6 = r6 + r12
                long r3 = r3 - r12
                r8 = r11
                boolean r9 = r15.isDisposed()
                if (r9 == 0) goto L_0x006d
                r15.index = r2
                return
            L_0x006d:
                goto L_0x003f
            L_0x006e:
                r1 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r1)
                r15.index = r2
                r15.dispose()
                boolean r2 = io.reactivex.internal.util.NotificationLite.isError(r9)
                if (r2 != 0) goto L_0x0088
                boolean r2 = io.reactivex.internal.util.NotificationLite.isComplete(r9)
                if (r2 != 0) goto L_0x0088
                org.reactivestreams.Subscriber<? super T> r2 = r15.child
                r2.onError(r1)
            L_0x0088:
                return
            L_0x0089:
                int r2 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
                if (r2 == 0) goto L_0x0094
                r15.index = r8
                if (r0 != 0) goto L_0x0094
                r15.produced(r6)
            L_0x0094:
                monitor-enter(r15)
                boolean r2 = r15.missed     // Catch:{ all -> 0x00a2 }
                if (r2 != 0) goto L_0x009d
                r15.emitting = r5     // Catch:{ all -> 0x00a2 }
                monitor-exit(r15)     // Catch:{ all -> 0x00a2 }
                return
            L_0x009d:
                r15.missed = r5     // Catch:{ all -> 0x00a2 }
                monitor-exit(r15)     // Catch:{ all -> 0x00a2 }
                goto L_0x000d
            L_0x00a2:
                r1 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x00a2 }
                throw r1
            L_0x00a5:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x00a5 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }

        /* access modifiers changed from: package-private */
        public Object enterTransform(Object value) {
            return value;
        }

        /* access modifiers changed from: package-private */
        public Object leaveTransform(Object value) {
            return value;
        }

        /* access modifiers changed from: package-private */
        public void truncate() {
        }

        /* access modifiers changed from: package-private */
        public void truncateFinal() {
            trimHead();
        }

        /* access modifiers changed from: package-private */
        public final void collect(Collection<? super T> output) {
            Node n = getHead();
            while (true) {
                Node next = (Node) n.get();
                if (next != null) {
                    Object v = leaveTransform(next.value);
                    if (!NotificationLite.isComplete(v) && !NotificationLite.isError(v)) {
                        output.add(NotificationLite.getValue(v));
                        n = next;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: package-private */
        public boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: package-private */
        public Node getHead() {
            return (Node) get();
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        SizeBoundReplayBuffer(int limit2) {
            this.limit = limit2;
        }

        /* access modifiers changed from: package-private */
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final Scheduler scheduler;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int limit2, long maxAge2, TimeUnit unit2, Scheduler scheduler2) {
            this.scheduler = scheduler2;
            this.limit = limit2;
            this.maxAge = maxAge2;
            this.unit = unit2;
        }

        /* access modifiers changed from: package-private */
        public Object enterTransform(Object value) {
            return new Timed(value, this.scheduler.now(this.unit), this.unit);
        }

        /* access modifiers changed from: package-private */
        public Object leaveTransform(Object value) {
            return ((Timed) value).value();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: io.reactivex.internal.operators.flowable.FlowableReplay$Node} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: io.reactivex.internal.operators.flowable.FlowableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void truncate() {
            /*
                r10 = this;
                io.reactivex.Scheduler r0 = r10.scheduler
                java.util.concurrent.TimeUnit r1 = r10.unit
                long r0 = r0.now(r1)
                long r2 = r10.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r2 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
                r4 = 0
            L_0x0018:
                if (r3 == 0) goto L_0x0051
                int r5 = r10.size
                int r6 = r10.limit
                r7 = 1
                if (r5 <= r6) goto L_0x0035
                int r5 = r10.size
                if (r5 <= r7) goto L_0x0035
                int r4 = r4 + 1
                int r5 = r10.size
                int r5 = r5 - r7
                r10.size = r5
                r2 = r3
                java.lang.Object r5 = r3.get()
                r3 = r5
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
                goto L_0x0018
            L_0x0035:
                java.lang.Object r5 = r3.value
                io.reactivex.schedulers.Timed r5 = (io.reactivex.schedulers.Timed) r5
                long r8 = r5.time()
                int r6 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                if (r6 > 0) goto L_0x0051
                int r4 = r4 + 1
                int r6 = r10.size
                int r6 = r6 - r7
                r10.size = r6
                r2 = r3
                java.lang.Object r6 = r3.get()
                r3 = r6
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
                goto L_0x0018
            L_0x0051:
                if (r4 == 0) goto L_0x0056
                r10.setFirst(r2)
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.SizeAndTimeBoundReplayBuffer.truncate():void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: io.reactivex.internal.operators.flowable.FlowableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void truncateFinal() {
            /*
                r9 = this;
                io.reactivex.Scheduler r0 = r9.scheduler
                java.util.concurrent.TimeUnit r1 = r9.unit
                long r0 = r0.now(r1)
                long r2 = r9.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r9.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r2 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
                r4 = 0
            L_0x0018:
                if (r3 == 0) goto L_0x003b
                int r5 = r9.size
                r6 = 1
                if (r5 <= r6) goto L_0x003b
                java.lang.Object r5 = r3.value
                io.reactivex.schedulers.Timed r5 = (io.reactivex.schedulers.Timed) r5
                long r7 = r5.time()
                int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r7 > 0) goto L_0x003b
                int r4 = r4 + 1
                int r7 = r9.size
                int r7 = r7 - r6
                r9.size = r7
                r2 = r3
                java.lang.Object r6 = r3.get()
                r3 = r6
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
                goto L_0x0018
            L_0x003b:
                if (r4 == 0) goto L_0x0040
                r9.setFirst(r2)
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.SizeAndTimeBoundReplayBuffer.truncateFinal():void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: io.reactivex.internal.operators.flowable.FlowableReplay$Node} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public io.reactivex.internal.operators.flowable.FlowableReplay.Node getHead() {
            /*
                r7 = this;
                io.reactivex.Scheduler r0 = r7.scheduler
                java.util.concurrent.TimeUnit r1 = r7.unit
                long r0 = r0.now(r1)
                long r2 = r7.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r7.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r2 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
            L_0x0017:
                if (r3 != 0) goto L_0x001a
                goto L_0x0044
            L_0x001a:
                java.lang.Object r4 = r3.value
                io.reactivex.schedulers.Timed r4 = (io.reactivex.schedulers.Timed) r4
                java.lang.Object r5 = r4.value()
                boolean r5 = io.reactivex.internal.util.NotificationLite.isComplete(r5)
                if (r5 != 0) goto L_0x0044
                java.lang.Object r5 = r4.value()
                boolean r5 = io.reactivex.internal.util.NotificationLite.isError(r5)
                if (r5 == 0) goto L_0x0033
                goto L_0x0044
            L_0x0033:
                long r5 = r4.time()
                int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r5 > 0) goto L_0x0044
                r2 = r3
                java.lang.Object r5 = r3.get()
                r3 = r5
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3
                goto L_0x0017
            L_0x0044:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.SizeAndTimeBoundReplayBuffer.getHead():io.reactivex.internal.operators.flowable.FlowableReplay$Node");
        }
    }

    static final class MulticastFlowable<R, U> extends Flowable<R> {
        private final Callable<? extends ConnectableFlowable<U>> connectableFactory;
        private final Function<? super Flowable<U>, ? extends Publisher<R>> selector;

        MulticastFlowable(Callable<? extends ConnectableFlowable<U>> connectableFactory2, Function<? super Flowable<U>, ? extends Publisher<R>> selector2) {
            this.connectableFactory = connectableFactory2;
            this.selector = selector2;
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(Subscriber<? super R> child) {
            try {
                ConnectableFlowable<U> cf = (ConnectableFlowable) ObjectHelper.requireNonNull(this.connectableFactory.call(), "The connectableFactory returned null");
                try {
                    Publisher<R> observable = (Publisher) ObjectHelper.requireNonNull(this.selector.apply(cf), "The selector returned a null Publisher");
                    SubscriberResourceWrapper<R> srw = new SubscriberResourceWrapper<>(child);
                    observable.subscribe(srw);
                    cf.connect(new DisposableConsumer(srw));
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    EmptySubscription.error(e, child);
                }
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                EmptySubscription.error(e2, child);
            }
        }

        final class DisposableConsumer implements Consumer<Disposable> {
            private final SubscriberResourceWrapper<R> srw;

            DisposableConsumer(SubscriberResourceWrapper<R> srw2) {
                this.srw = srw2;
            }

            public void accept(Disposable r) {
                this.srw.setResource(r);
            }
        }
    }

    static final class ConnectableFlowableReplay<T> extends ConnectableFlowable<T> {
        private final ConnectableFlowable<T> cf;
        private final Flowable<T> flowable;

        ConnectableFlowableReplay(ConnectableFlowable<T> cf2, Flowable<T> flowable2) {
            this.cf = cf2;
            this.flowable = flowable2;
        }

        public void connect(Consumer<? super Disposable> connection) {
            this.cf.connect(connection);
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(Subscriber<? super T> s) {
            this.flowable.subscribe(s);
        }
    }

    static final class ReplayBufferTask<T> implements Callable<ReplayBuffer<T>> {
        private final int bufferSize;

        ReplayBufferTask(int bufferSize2) {
            this.bufferSize = bufferSize2;
        }

        public ReplayBuffer<T> call() {
            return new SizeBoundReplayBuffer(this.bufferSize);
        }
    }

    static final class ScheduledReplayBufferTask<T> implements Callable<ReplayBuffer<T>> {
        private final int bufferSize;
        private final long maxAge;
        private final Scheduler scheduler;
        private final TimeUnit unit;

        ScheduledReplayBufferTask(int bufferSize2, long maxAge2, TimeUnit unit2, Scheduler scheduler2) {
            this.bufferSize = bufferSize2;
            this.maxAge = maxAge2;
            this.unit = unit2;
            this.scheduler = scheduler2;
        }

        public ReplayBuffer<T> call() {
            return new SizeAndTimeBoundReplayBuffer(this.bufferSize, this.maxAge, this.unit, this.scheduler);
        }
    }

    static final class ReplayPublisher<T> implements Publisher<T> {
        private final Callable<? extends ReplayBuffer<T>> bufferFactory;
        private final AtomicReference<ReplaySubscriber<T>> curr;

        ReplayPublisher(AtomicReference<ReplaySubscriber<T>> curr2, Callable<? extends ReplayBuffer<T>> bufferFactory2) {
            this.curr = curr2;
            this.bufferFactory = bufferFactory2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void subscribe(org.reactivestreams.Subscriber<? super T> r6) {
            /*
                r5 = this;
            L_0x0000:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r0 = r5.curr
                java.lang.Object r0 = r0.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplaySubscriber) r0
                if (r0 != 0) goto L_0x002c
                java.util.concurrent.Callable<? extends io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer<T>> r1 = r5.bufferFactory     // Catch:{ all -> 0x0024 }
                java.lang.Object r1 = r1.call()     // Catch:{ all -> 0x0024 }
                io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer r1 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplayBuffer) r1     // Catch:{ all -> 0x0024 }
                io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r2 = new io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber
                r2.<init>(r1)
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r3 = r5.curr
                r4 = 0
                boolean r3 = r3.compareAndSet(r4, r2)
                if (r3 != 0) goto L_0x0022
                goto L_0x0000
            L_0x0022:
                r0 = r2
                goto L_0x002c
            L_0x0024:
                r1 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r1)
                io.reactivex.internal.subscriptions.EmptySubscription.error(r1, r6)
                return
            L_0x002c:
                io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription r1 = new io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription
                r1.<init>(r0, r6)
                r6.onSubscribe(r1)
                r0.add(r1)
                boolean r2 = r1.isDisposed()
                if (r2 == 0) goto L_0x0041
                r0.remove(r1)
                return
            L_0x0041:
                r0.manageRequests()
                io.reactivex.internal.operators.flowable.FlowableReplay$ReplayBuffer<T> r2 = r0.buffer
                r2.replay(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.ReplayPublisher.subscribe(org.reactivestreams.Subscriber):void");
        }
    }

    static final class DefaultUnboundedFactory implements Callable<Object> {
        DefaultUnboundedFactory() {
        }

        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }
}
