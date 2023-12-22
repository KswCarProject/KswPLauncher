package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableWindow<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {
    final int bufferSize;
    final long size;
    final long skip;

    public FlowableWindow(Flowable<T> source, long size, long skip, int bufferSize) {
        super(source);
        this.size = size;
        this.skip = skip;
        this.bufferSize = bufferSize;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Flowable<T>> s) {
        long j = this.skip;
        long j2 = this.size;
        if (j == j2) {
            this.source.subscribe((FlowableSubscriber) new WindowExactSubscriber(s, this.size, this.bufferSize));
        } else if (j > j2) {
            this.source.subscribe((FlowableSubscriber) new WindowSkipSubscriber(s, this.size, this.skip, this.bufferSize));
        } else {
            this.source.subscribe((FlowableSubscriber) new WindowOverlapSubscriber(s, this.size, this.skip, this.bufferSize));
        }
    }

    /* loaded from: classes.dex */
    static final class WindowExactSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = -2365647875069161133L;
        final int bufferSize;
        final Subscriber<? super Flowable<T>> downstream;
        long index;
        final AtomicBoolean once;
        final long size;
        Subscription upstream;
        UnicastProcessor<T> window;

        WindowExactSubscriber(Subscriber<? super Flowable<T>> actual, long size, int bufferSize) {
            super(1);
            this.downstream = actual;
            this.size = size;
            this.once = new AtomicBoolean();
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long i = this.index;
            UnicastProcessor<T> w = this.window;
            if (i == 0) {
                getAndIncrement();
                w = UnicastProcessor.create(this.bufferSize, this);
                this.window = w;
                this.downstream.onNext(w);
            }
            long i2 = i + 1;
            w.onNext(t);
            if (i2 == this.size) {
                this.index = 0L;
                this.window = null;
                w.onComplete();
                return;
            }
            this.index = i2;
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            Processor<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onError(t);
            }
            this.downstream.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            Processor<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                long u = BackpressureHelper.multiplyCap(this.size, n);
                this.upstream.request(u);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }

    /* loaded from: classes.dex */
    static final class WindowSkipSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = -8792836352386833856L;
        final int bufferSize;
        final Subscriber<? super Flowable<T>> downstream;
        final AtomicBoolean firstRequest;
        long index;
        final AtomicBoolean once;
        final long size;
        final long skip;
        Subscription upstream;
        UnicastProcessor<T> window;

        WindowSkipSubscriber(Subscriber<? super Flowable<T>> actual, long size, long skip, int bufferSize) {
            super(1);
            this.downstream = actual;
            this.size = size;
            this.skip = skip;
            this.once = new AtomicBoolean();
            this.firstRequest = new AtomicBoolean();
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long i = this.index;
            UnicastProcessor<T> w = this.window;
            if (i == 0) {
                getAndIncrement();
                w = UnicastProcessor.create(this.bufferSize, this);
                this.window = w;
                this.downstream.onNext(w);
            }
            long i2 = i + 1;
            if (w != null) {
                w.onNext(t);
            }
            if (i2 == this.size) {
                this.window = null;
                w.onComplete();
            }
            if (i2 == this.skip) {
                this.index = 0L;
            } else {
                this.index = i2;
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            Processor<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onError(t);
            }
            this.downstream.onError(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            Processor<T, T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                if (!this.firstRequest.get() && this.firstRequest.compareAndSet(false, true)) {
                    long u = BackpressureHelper.multiplyCap(this.size, n);
                    long v = BackpressureHelper.multiplyCap(this.skip - this.size, n - 1);
                    long w = BackpressureHelper.addCap(u, v);
                    this.upstream.request(w);
                    return;
                }
                long u2 = BackpressureHelper.multiplyCap(this.skip, n);
                this.upstream.request(u2);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }

    /* loaded from: classes.dex */
    static final class WindowOverlapSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = 2428527070996323976L;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        final Subscriber<? super Flowable<T>> downstream;
        Throwable error;
        final AtomicBoolean firstRequest;
        long index;
        final AtomicBoolean once;
        long produced;
        final SpscLinkedArrayQueue<UnicastProcessor<T>> queue;
        final AtomicLong requested;
        final long size;
        final long skip;
        Subscription upstream;
        final ArrayDeque<UnicastProcessor<T>> windows;
        final AtomicInteger wip;

        WindowOverlapSubscriber(Subscriber<? super Flowable<T>> actual, long size, long skip, int bufferSize) {
            super(1);
            this.downstream = actual;
            this.size = size;
            this.skip = skip;
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
            this.windows = new ArrayDeque<>();
            this.once = new AtomicBoolean();
            this.firstRequest = new AtomicBoolean();
            this.requested = new AtomicLong();
            this.wip = new AtomicInteger();
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            long i = this.index;
            if (i == 0 && !this.cancelled) {
                getAndIncrement();
                UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize, this);
                this.windows.offer(w);
                this.queue.offer(w);
                drain();
            }
            long i2 = i + 1;
            Iterator<UnicastProcessor<T>> it = this.windows.iterator();
            while (it.hasNext()) {
                it.next().onNext(t);
            }
            long p = this.produced + 1;
            if (p == this.size) {
                this.produced = p - this.skip;
                Processor<T, T> w2 = this.windows.poll();
                if (w2 != null) {
                    w2.onComplete();
                }
            } else {
                this.produced = p;
            }
            if (i2 == this.skip) {
                this.index = 0L;
            } else {
                this.index = i2;
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            Iterator<UnicastProcessor<T>> it = this.windows.iterator();
            while (it.hasNext()) {
                Processor<T, T> w = it.next();
                w.onError(t);
            }
            this.windows.clear();
            this.error = t;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            Iterator<UnicastProcessor<T>> it = this.windows.iterator();
            while (it.hasNext()) {
                Processor<T, T> w = it.next();
                w.onComplete();
            }
            this.windows.clear();
            this.done = true;
            drain();
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super Flowable<T>> a = this.downstream;
            SpscLinkedArrayQueue<UnicastProcessor<T>> q = this.queue;
            int missed = 1;
            do {
                long r = this.requested.get();
                long e = 0;
                while (e != r) {
                    boolean d = this.done;
                    UnicastProcessor<T> t = q.poll();
                    boolean empty = t == null;
                    if (checkTerminated(d, empty, a, q)) {
                        return;
                    }
                    if (empty) {
                        break;
                    }
                    a.onNext(t);
                    e++;
                }
                if (e == r && checkTerminated(this.done, q.isEmpty(), a, q)) {
                    return;
                }
                if (e != 0 && r != LongCompanionObject.MAX_VALUE) {
                    this.requested.addAndGet(-e);
                }
                missed = this.wip.addAndGet(-missed);
            } while (missed != 0);
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, SpscLinkedArrayQueue<?> q) {
            if (this.cancelled) {
                q.clear();
                return true;
            } else if (d) {
                Throwable e = this.error;
                if (e != null) {
                    q.clear();
                    a.onError(e);
                    return true;
                } else if (empty) {
                    a.onComplete();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                if (!this.firstRequest.get() && this.firstRequest.compareAndSet(false, true)) {
                    long u = BackpressureHelper.multiplyCap(this.skip, n - 1);
                    long v = BackpressureHelper.addCap(this.size, u);
                    this.upstream.request(v);
                } else {
                    long u2 = BackpressureHelper.multiplyCap(this.skip, n);
                    this.upstream.request(u2);
                }
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }
}
