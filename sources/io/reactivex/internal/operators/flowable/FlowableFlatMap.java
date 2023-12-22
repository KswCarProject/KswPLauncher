package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableFlatMap<T, U> extends AbstractFlowableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends Publisher<? extends U>> mapper;
    final int maxConcurrency;

    public FlowableFlatMap(Flowable<T> source, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayErrors;
        this.maxConcurrency = maxConcurrency;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super U> s) {
        if (FlowableScalarXMap.tryScalarXMapSubscribe(this.source, s, this.mapper)) {
            return;
        }
        this.source.subscribe((FlowableSubscriber) subscribe(s, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
    }

    public static <T, U> FlowableSubscriber<T> subscribe(Subscriber<? super U> s, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        return new MergeSubscriber(s, mapper, delayErrors, maxConcurrency, bufferSize);
    }

    /* loaded from: classes.dex */
    static final class MergeSubscriber<T, U> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Subscriber<? super U> downstream;
        final AtomicThrowable errs = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends Publisher<? extends U>> mapper;
        final int maxConcurrency;
        volatile SimplePlainQueue<U> queue;
        final AtomicLong requested;
        int scalarEmitted;
        final int scalarLimit;
        final AtomicReference<InnerSubscriber<?, ?>[]> subscribers;
        long uniqueId;
        Subscription upstream;
        static final InnerSubscriber<?, ?>[] EMPTY = new InnerSubscriber[0];
        static final InnerSubscriber<?, ?>[] CANCELLED = new InnerSubscriber[0];

        MergeSubscriber(Subscriber<? super U> actual, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
            AtomicReference<InnerSubscriber<?, ?>[]> atomicReference = new AtomicReference<>();
            this.subscribers = atomicReference;
            this.requested = new AtomicLong();
            this.downstream = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
            this.bufferSize = bufferSize;
            this.scalarLimit = Math.max(1, maxConcurrency >> 1);
            atomicReference.lazySet(EMPTY);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                if (!this.cancelled) {
                    int i = this.maxConcurrency;
                    if (i == Integer.MAX_VALUE) {
                        s.request(LongCompanionObject.MAX_VALUE);
                    } else {
                        s.request(i);
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                Publisher<? extends U> p = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                if (p instanceof Callable) {
                    try {
                        Object call = ((Callable) p).call();
                        if (call != null) {
                            tryEmitScalar(call);
                            return;
                        } else if (this.maxConcurrency == Integer.MAX_VALUE || this.cancelled) {
                            return;
                        } else {
                            int i = this.scalarEmitted + 1;
                            this.scalarEmitted = i;
                            int i2 = this.scalarLimit;
                            if (i == i2) {
                                this.scalarEmitted = 0;
                                this.upstream.request(i2);
                                return;
                            }
                            return;
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.errs.addThrowable(ex);
                        drain();
                        return;
                    }
                }
                long j = this.uniqueId;
                this.uniqueId = 1 + j;
                InnerSubscriber<T, U> inner = new InnerSubscriber<>(this, j);
                if (addInner(inner)) {
                    p.subscribe(inner);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.upstream.cancel();
                onError(e);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        boolean addInner(InnerSubscriber<T, U> inner) {
            InnerSubscriber<?, ?>[] a;
            InnerSubscriber[] innerSubscriberArr;
            do {
                a = this.subscribers.get();
                if (a == CANCELLED) {
                    inner.dispose();
                    return false;
                }
                int n = a.length;
                innerSubscriberArr = new InnerSubscriber[n + 1];
                System.arraycopy(a, 0, innerSubscriberArr, 0, n);
                innerSubscriberArr[n] = inner;
            } while (!this.subscribers.compareAndSet(a, innerSubscriberArr));
            return true;
        }

        void removeInner(InnerSubscriber<T, U> inner) {
            InnerSubscriber<?, ?>[] a;
            InnerSubscriber<?, ?>[] b;
            do {
                a = this.subscribers.get();
                int n = a.length;
                if (n == 0) {
                    return;
                }
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (a[i] != inner) {
                        i++;
                    } else {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    b = EMPTY;
                } else {
                    InnerSubscriber<?, ?>[] b2 = new InnerSubscriber[n - 1];
                    System.arraycopy(a, 0, b2, 0, j);
                    System.arraycopy(a, j + 1, b2, j, (n - j) - 1);
                    b = b2;
                }
            } while (!this.subscribers.compareAndSet(a, b));
        }

        SimpleQueue<U> getMainQueue() {
            SimplePlainQueue<U> q = this.queue;
            if (q == null) {
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    q = new SpscLinkedArrayQueue(this.bufferSize);
                } else {
                    q = new SpscArrayQueue<>(this.maxConcurrency);
                }
                this.queue = q;
            }
            return q;
        }

        void tryEmitScalar(U value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long r = this.requested.get();
                SimpleQueue<U> q = this.queue;
                if (r != 0 && (q == null || q.isEmpty())) {
                    this.downstream.onNext(value);
                    if (r != LongCompanionObject.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                        int i = this.scalarEmitted + 1;
                        this.scalarEmitted = i;
                        int i2 = this.scalarLimit;
                        if (i == i2) {
                            this.scalarEmitted = 0;
                            this.upstream.request(i2);
                        }
                    }
                } else {
                    if (q == null) {
                        q = getMainQueue();
                    }
                    if (!q.offer(value)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(value)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SimpleQueue<U> getInnerQueue(InnerSubscriber<T, U> inner) {
            SimpleQueue<U> q = inner.queue;
            if (q == null) {
                SimpleQueue<U> q2 = new SpscArrayQueue<>(this.bufferSize);
                inner.queue = q2;
                return q2;
            }
            return q;
        }

        void tryEmit(U value, InnerSubscriber<T, U> inner) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long r = this.requested.get();
                SimpleQueue<U> q = inner.queue;
                if (r != 0 && (q == null || q.isEmpty())) {
                    this.downstream.onNext(value);
                    if (r != LongCompanionObject.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.requestMore(1L);
                } else {
                    if (q == null) {
                        q = getInnerQueue(inner);
                    }
                    if (!q.offer(value)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SimpleQueue<U> q2 = inner.queue;
                if (q2 == null) {
                    q2 = new SpscArrayQueue(this.bufferSize);
                    inner.queue = q2;
                }
                if (!q2.offer(value)) {
                    onError(new MissingBackpressureException("Inner queue full?!"));
                    return;
                } else if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            InnerSubscriber<?, ?>[] andSet;
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else if (this.errs.addThrowable(t)) {
                this.done = true;
                if (!this.delayErrors) {
                    for (InnerSubscriber<?, ?> a : this.subscribers.getAndSet(CANCELLED)) {
                        a.dispose();
                    }
                }
                drain();
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SimpleQueue<U> q;
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                disposeAll();
                if (getAndIncrement() == 0 && (q = this.queue) != null) {
                    q.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:129:0x01f3, code lost:
            r32.lastIndex = r5;
            r32.lastId = r11[r5].f287id;
         */
        /* JADX WARN: Removed duplicated region for block: B:162:0x01e7 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x00e5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void drainLoop() {
            boolean innerCompleted;
            long r;
            long replenishMain;
            boolean d;
            int index;
            int i;
            boolean unbounded;
            int i2;
            boolean d2;
            SimplePlainQueue<U> svq;
            int i3;
            long r2;
            Subscriber<? super U> child = this.downstream;
            int missed = 1;
            while (!checkTerminate()) {
                SimplePlainQueue<U> svq2 = this.queue;
                long r3 = this.requested.get();
                boolean unbounded2 = r3 == LongCompanionObject.MAX_VALUE;
                long replenishMain2 = 0;
                long j = 1;
                if (svq2 != null) {
                    while (true) {
                        long scalarEmission = 0;
                        Object obj = null;
                        while (true) {
                            if (r3 == 0) {
                                break;
                            }
                            U poll = svq2.poll();
                            if (checkTerminate()) {
                                return;
                            }
                            if (poll == null) {
                                obj = poll;
                                break;
                            }
                            child.onNext(poll);
                            replenishMain2 += j;
                            scalarEmission += j;
                            r3 -= j;
                            obj = poll;
                        }
                        if (scalarEmission != 0) {
                            if (unbounded2) {
                                r3 = LongCompanionObject.MAX_VALUE;
                            } else {
                                r3 = this.requested.addAndGet(-scalarEmission);
                            }
                        }
                        if (r3 == 0 || obj == null) {
                            break;
                        }
                        j = 1;
                    }
                }
                boolean d3 = this.done;
                SimplePlainQueue<U> svq3 = this.queue;
                InnerSubscriber<?, ?>[] inner = this.subscribers.get();
                int n = inner.length;
                if (d3 && ((svq3 == null || svq3.isEmpty()) && n == 0)) {
                    Throwable ex = this.errs.terminate();
                    if (ex != ExceptionHelper.TERMINATED) {
                        if (ex == null) {
                            child.onComplete();
                            return;
                        } else {
                            child.onError(ex);
                            return;
                        }
                    }
                    return;
                }
                boolean innerCompleted2 = false;
                if (n != 0) {
                    long startId = this.lastId;
                    int index2 = this.lastIndex;
                    if (n > index2) {
                        innerCompleted = false;
                        r = r3;
                        if (inner[index2].f287id == startId) {
                            replenishMain = replenishMain2;
                            index = index2;
                            d = d3;
                            int j2 = index;
                            i = 0;
                            int j3 = j2;
                            while (true) {
                                if (i < n) {
                                    innerCompleted2 = innerCompleted;
                                    replenishMain2 = replenishMain;
                                    break;
                                } else if (checkTerminate()) {
                                    return;
                                } else {
                                    InnerSubscriber<T, U> is = inner[j3];
                                    U u = null;
                                    while (!checkTerminate()) {
                                        SimpleQueue<U> q = is.queue;
                                        if (q == null) {
                                            unbounded = unbounded2;
                                            i2 = i;
                                            d2 = d;
                                            svq = svq3;
                                        } else {
                                            d2 = d;
                                            svq = svq3;
                                            long produced = 0;
                                            long r4 = r;
                                            Object obj2 = u;
                                            while (true) {
                                                SimpleQueue<U> q2 = q;
                                                if (r4 == 0) {
                                                    u = (U) obj2;
                                                    break;
                                                }
                                                try {
                                                    u = q2.poll();
                                                    if (u == null) {
                                                        break;
                                                    }
                                                    child.onNext(u);
                                                    if (checkTerminate()) {
                                                        return;
                                                    }
                                                    r4--;
                                                    produced++;
                                                    obj2 = u;
                                                    q = q2;
                                                } catch (Throwable ex2) {
                                                    Exceptions.throwIfFatal(ex2);
                                                    is.dispose();
                                                    this.errs.addThrowable(ex2);
                                                    if (!this.delayErrors) {
                                                        this.upstream.cancel();
                                                    }
                                                    if (checkTerminate()) {
                                                        return;
                                                    }
                                                    removeInner(is);
                                                    i3 = i + 1;
                                                    unbounded = unbounded2;
                                                    innerCompleted = true;
                                                    r = r4;
                                                }
                                            }
                                            if (produced == 0) {
                                                unbounded = unbounded2;
                                                i2 = i;
                                                r = r4;
                                            } else {
                                                if (!unbounded2) {
                                                    unbounded = unbounded2;
                                                    i2 = i;
                                                    r2 = this.requested.addAndGet(-produced);
                                                } else {
                                                    unbounded = unbounded2;
                                                    i2 = i;
                                                    r2 = LongCompanionObject.MAX_VALUE;
                                                }
                                                is.requestMore(produced);
                                                r = r2;
                                            }
                                            if (r != 0 && u != null) {
                                                unbounded2 = unbounded;
                                                svq3 = svq;
                                                d = d2;
                                                i = i2;
                                            }
                                        }
                                        boolean innerDone = is.done;
                                        SimpleQueue<U> innerQueue = is.queue;
                                        if (innerDone && (innerQueue == null || innerQueue.isEmpty())) {
                                            removeInner(is);
                                            if (checkTerminate()) {
                                                return;
                                            }
                                            replenishMain++;
                                            innerCompleted = true;
                                        }
                                        if (r == 0) {
                                            innerCompleted2 = innerCompleted;
                                            replenishMain2 = replenishMain;
                                            break;
                                        }
                                        j3++;
                                        if (j3 != n) {
                                            i3 = i2;
                                        } else {
                                            j3 = 0;
                                            i3 = i2;
                                        }
                                        i = i3 + 1;
                                        unbounded2 = unbounded;
                                        svq3 = svq;
                                        d = d2;
                                    }
                                    return;
                                }
                            }
                        }
                    } else {
                        innerCompleted = false;
                        r = r3;
                    }
                    if (n <= index2) {
                        index2 = 0;
                    }
                    int j4 = index2;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= n) {
                            replenishMain = replenishMain2;
                            d = d3;
                            break;
                        }
                        replenishMain = replenishMain2;
                        d = d3;
                        if (inner[j4].f287id == startId) {
                            break;
                        }
                        j4++;
                        if (j4 == n) {
                            j4 = 0;
                        }
                        i4++;
                        d3 = d;
                        replenishMain2 = replenishMain;
                    }
                    this.lastIndex = j4;
                    this.lastId = inner[j4].f287id;
                    index = j4;
                    int j22 = index;
                    i = 0;
                    int j32 = j22;
                    while (true) {
                        if (i < n) {
                        }
                        i = i3 + 1;
                        unbounded2 = unbounded;
                        svq3 = svq;
                        d = d2;
                    }
                }
                if (replenishMain2 != 0 && !this.cancelled) {
                    this.upstream.request(replenishMain2);
                }
                if (!innerCompleted2 && (missed = addAndGet(-missed)) == 0) {
                    return;
                }
            }
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            } else if (!this.delayErrors && this.errs.get() != null) {
                clearScalarQueue();
                Throwable ex = this.errs.terminate();
                if (ex != ExceptionHelper.TERMINATED) {
                    this.downstream.onError(ex);
                }
                return true;
            } else {
                return false;
            }
        }

        void clearScalarQueue() {
            SimpleQueue<U> q = this.queue;
            if (q != null) {
                q.clear();
            }
        }

        void disposeAll() {
            InnerSubscriber<?, ?>[] a = this.subscribers.get();
            InnerSubscriber<?, ?>[] innerSubscriberArr = CANCELLED;
            if (a != innerSubscriberArr) {
                InnerSubscriber<?, ?>[] a2 = this.subscribers.getAndSet(innerSubscriberArr);
                InnerSubscriber<?, ?>[] a3 = a2;
                if (a3 != innerSubscriberArr) {
                    for (InnerSubscriber<?, ?> inner : a3) {
                        inner.dispose();
                    }
                    Throwable ex = this.errs.terminate();
                    if (ex != null && ex != ExceptionHelper.TERMINATED) {
                        RxJavaPlugins.onError(ex);
                    }
                }
            }
        }

        void innerError(InnerSubscriber<T, U> inner, Throwable t) {
            InnerSubscriber<?, ?>[] andSet;
            if (this.errs.addThrowable(t)) {
                inner.done = true;
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    for (InnerSubscriber<?, ?> a : this.subscribers.getAndSet(CANCELLED)) {
                        a.dispose();
                    }
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }
    }

    /* loaded from: classes.dex */
    static final class InnerSubscriber<T, U> extends AtomicReference<Subscription> implements FlowableSubscriber<U>, Disposable {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;

        /* renamed from: id */
        final long f287id;
        final int limit;
        final MergeSubscriber<T, U> parent;
        long produced;
        volatile SimpleQueue<U> queue;

        InnerSubscriber(MergeSubscriber<T, U> parent, long id) {
            this.f287id = id;
            this.parent = parent;
            int i = parent.bufferSize;
            this.bufferSize = i;
            this.limit = i >> 2;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<U> qs = (QueueSubscription) s;
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
                    }
                }
                s.request(this.bufferSize);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(U t) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(t, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long n) {
            if (this.fusionMode != 1) {
                long p = this.produced + n;
                if (p >= this.limit) {
                    this.produced = 0L;
                    get().request(p);
                    return;
                }
                this.produced = p;
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
