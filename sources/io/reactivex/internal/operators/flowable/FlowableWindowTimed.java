package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableWindowTimed<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {
    final int bufferSize;
    final long maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    public FlowableWindowTimed(Flowable<T> source, long timespan2, long timeskip2, TimeUnit unit2, Scheduler scheduler2, long maxSize2, int bufferSize2, boolean restartTimerOnMaxSize2) {
        super(source);
        this.timespan = timespan2;
        this.timeskip = timeskip2;
        this.unit = unit2;
        this.scheduler = scheduler2;
        this.maxSize = maxSize2;
        this.bufferSize = bufferSize2;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super Flowable<T>> s) {
        SerializedSubscriber<Flowable<T>> actual = new SerializedSubscriber<>(s);
        if (this.timespan != this.timeskip) {
            this.source.subscribe(new WindowSkipSubscriber(actual, this.timespan, this.timeskip, this.unit, this.scheduler.createWorker(), this.bufferSize));
        } else if (this.maxSize == LongCompanionObject.MAX_VALUE) {
            this.source.subscribe(new WindowExactUnboundedSubscriber(actual, this.timespan, this.unit, this.scheduler, this.bufferSize));
        } else {
            this.source.subscribe(new WindowExactBoundedSubscriber(actual, this.timespan, this.unit, this.scheduler, this.bufferSize, this.maxSize, this.restartTimerOnMaxSize));
        }
    }

    static final class WindowExactUnboundedSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements FlowableSubscriber<T>, Subscription, Runnable {
        static final Object NEXT = new Object();
        final int bufferSize;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer = new SequentialDisposable();
        final long timespan;
        final TimeUnit unit;
        Subscription upstream;
        UnicastProcessor<T> window;

        WindowExactUnboundedSubscriber(Subscriber<? super Flowable<T>> actual, long timespan2, TimeUnit unit2, Scheduler scheduler2, int bufferSize2) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan2;
            this.unit = unit2;
            this.scheduler = scheduler2;
            this.bufferSize = bufferSize2;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.window = UnicastProcessor.create(this.bufferSize);
                Subscriber<? super Flowable<T>> a = this.downstream;
                a.onSubscribe(this);
                long r = requested();
                if (r != 0) {
                    a.onNext(this.window);
                    if (r != LongCompanionObject.MAX_VALUE) {
                        produced(1);
                    }
                    if (!this.cancelled) {
                        SequentialDisposable sequentialDisposable = this.timer;
                        Scheduler scheduler2 = this.scheduler;
                        long j = this.timespan;
                        if (sequentialDisposable.replace(scheduler2.schedulePeriodicallyDirect(this, j, j, this.unit))) {
                            s.request(LongCompanionObject.MAX_VALUE);
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.cancelled = true;
                s.cancel();
                a.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
            }
        }

        public void onNext(T t) {
            if (!this.terminated) {
                if (fastEnter()) {
                    this.window.onNext(t);
                    if (leave(-1) == 0) {
                        return;
                    }
                } else {
                    this.queue.offer(NotificationLite.next(t));
                    if (!enter()) {
                        return;
                    }
                }
                drainLoop();
            }
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.downstream.onError(t);
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.downstream.onComplete();
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.cancelled = true;
        }

        public void run() {
            if (this.cancelled) {
                this.terminated = true;
            }
            this.queue.offer(NEXT);
            if (enter()) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            SimplePlainQueue<Object> q = this.queue;
            Subscriber<? super Flowable<T>> a = this.downstream;
            UnicastProcessor<T> w = this.window;
            int missed = 1;
            while (true) {
                boolean term = this.terminated;
                boolean d = this.done;
                Object o = q.poll();
                if (!d || !(o == null || o == NEXT)) {
                    if (o == null) {
                        missed = leave(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else if (o == NEXT) {
                        w.onComplete();
                        if (!term) {
                            w = UnicastProcessor.create(this.bufferSize);
                            this.window = w;
                            long r = requested();
                            if (r != 0) {
                                a.onNext(w);
                                if (r != LongCompanionObject.MAX_VALUE) {
                                    produced(1);
                                }
                            } else {
                                this.window = null;
                                this.queue.clear();
                                this.upstream.cancel();
                                a.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                                this.timer.dispose();
                                return;
                            }
                        } else {
                            this.upstream.cancel();
                        }
                    } else {
                        w.onNext(NotificationLite.getValue(o));
                    }
                }
            }
            this.window = null;
            q.clear();
            Throwable err = this.error;
            if (err != null) {
                w.onError(err);
            } else {
                w.onComplete();
            }
            this.timer.dispose();
        }
    }

    static final class WindowExactBoundedSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription {
        final int bufferSize;
        long count;
        final long maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer = new SequentialDisposable();
        final long timespan;
        final TimeUnit unit;
        Subscription upstream;
        UnicastProcessor<T> window;
        final Scheduler.Worker worker;

        WindowExactBoundedSubscriber(Subscriber<? super Flowable<T>> actual, long timespan2, TimeUnit unit2, Scheduler scheduler2, int bufferSize2, long maxSize2, boolean restartTimerOnMaxSize2) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan2;
            this.unit = unit2;
            this.scheduler = scheduler2;
            this.bufferSize = bufferSize2;
            this.maxSize = maxSize2;
            this.restartTimerOnMaxSize = restartTimerOnMaxSize2;
            if (restartTimerOnMaxSize2) {
                this.worker = scheduler2.createWorker();
            } else {
                this.worker = null;
            }
        }

        public void onSubscribe(Subscription s) {
            Disposable task;
            Subscription subscription = s;
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                Subscriber<? super Flowable<T>> a = this.downstream;
                a.onSubscribe(this);
                if (!this.cancelled) {
                    UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
                    this.window = w;
                    long r = requested();
                    if (r != 0) {
                        a.onNext(w);
                        if (r != LongCompanionObject.MAX_VALUE) {
                            produced(1);
                        }
                        ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                        if (this.restartTimerOnMaxSize) {
                            Scheduler.Worker worker2 = this.worker;
                            long j = this.timespan;
                            task = worker2.schedulePeriodically(consumerIndexHolder, j, j, this.unit);
                        } else {
                            Scheduler scheduler2 = this.scheduler;
                            long j2 = this.timespan;
                            task = scheduler2.schedulePeriodicallyDirect(consumerIndexHolder, j2, j2, this.unit);
                        }
                        if (this.timer.replace(task)) {
                            subscription.request(LongCompanionObject.MAX_VALUE);
                            return;
                        }
                        return;
                    }
                    this.cancelled = true;
                    s.cancel();
                    a.onError(new MissingBackpressureException("Could not deliver initial window due to lack of requests."));
                }
            }
        }

        public void onNext(T t) {
            if (!this.terminated) {
                if (fastEnter()) {
                    UnicastProcessor<T> w = this.window;
                    w.onNext(t);
                    long c = this.count + 1;
                    if (c >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0;
                        w.onComplete();
                        long r = requested();
                        if (r != 0) {
                            UnicastProcessor<T> w2 = UnicastProcessor.create(this.bufferSize);
                            this.window = w2;
                            this.downstream.onNext(w2);
                            if (r != LongCompanionObject.MAX_VALUE) {
                                produced(1);
                            }
                            if (this.restartTimerOnMaxSize) {
                                ((Disposable) this.timer.get()).dispose();
                                Scheduler.Worker worker2 = this.worker;
                                ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                                long j = this.timespan;
                                this.timer.replace(worker2.schedulePeriodically(consumerIndexHolder, j, j, this.unit));
                            }
                        } else {
                            this.window = null;
                            this.upstream.cancel();
                            this.downstream.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            disposeTimer();
                            return;
                        }
                    } else {
                        this.count = c;
                    }
                    if (leave(-1) == 0) {
                        return;
                    }
                } else {
                    T t2 = t;
                    this.queue.offer(NotificationLite.next(t));
                    if (!enter()) {
                        return;
                    }
                }
                drainLoop();
            }
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.downstream.onError(t);
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.downstream.onComplete();
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.cancelled = true;
        }

        public void disposeTimer() {
            this.timer.dispose();
            Scheduler.Worker w = this.worker;
            if (w != null) {
                w.dispose();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            Subscriber<? super Flowable<T>> a;
            SimplePlainQueue<Object> q;
            UnicastProcessor<T> w;
            SimplePlainQueue<Object> q2 = this.queue;
            Subscriber<? super Flowable<T>> a2 = this.downstream;
            UnicastProcessor<T> w2 = this.window;
            int missed = 1;
            while (!this.terminated) {
                boolean d = this.done;
                Object o = q2.poll();
                boolean empty = o == null;
                boolean isHolder = o instanceof ConsumerIndexHolder;
                if (d && (empty || isHolder)) {
                    this.window = null;
                    q2.clear();
                    Throwable err = this.error;
                    if (err != null) {
                        w2.onError(err);
                    } else {
                        w2.onComplete();
                    }
                    disposeTimer();
                    return;
                } else if (empty) {
                    missed = leave(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else if (isHolder) {
                    ConsumerIndexHolder consumerIndexHolder = (ConsumerIndexHolder) o;
                    if (!this.restartTimerOnMaxSize || this.producerIndex == consumerIndexHolder.index) {
                        w2.onComplete();
                        this.count = 0;
                        w2 = UnicastProcessor.create(this.bufferSize);
                        this.window = w2;
                        long r = requested();
                        if (r != 0) {
                            a2.onNext(w2);
                            if (r != LongCompanionObject.MAX_VALUE) {
                                produced(1);
                            }
                        } else {
                            this.window = null;
                            this.queue.clear();
                            this.upstream.cancel();
                            a2.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                            disposeTimer();
                            return;
                        }
                    }
                } else {
                    w2.onNext(NotificationLite.getValue(o));
                    long c = this.count + 1;
                    if (c >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0;
                        w2.onComplete();
                        long r2 = requested();
                        if (r2 != 0) {
                            UnicastProcessor<T> w3 = UnicastProcessor.create(this.bufferSize);
                            this.window = w3;
                            this.downstream.onNext(w3);
                            if (r2 != LongCompanionObject.MAX_VALUE) {
                                produced(1);
                            }
                            if (this.restartTimerOnMaxSize) {
                                ((Disposable) this.timer.get()).dispose();
                                Scheduler.Worker worker2 = this.worker;
                                q = q2;
                                a = a2;
                                ConsumerIndexHolder consumerIndexHolder2 = new ConsumerIndexHolder(this.producerIndex, this);
                                long j = this.timespan;
                                w = w3;
                                this.timer.replace(worker2.schedulePeriodically(consumerIndexHolder2, j, j, this.unit));
                            } else {
                                q = q2;
                                a = a2;
                                w = w3;
                            }
                            w2 = w;
                        } else {
                            Subscriber<? super Flowable<T>> subscriber = a2;
                            this.window = null;
                            this.upstream.cancel();
                            this.downstream.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            disposeTimer();
                            return;
                        }
                    } else {
                        q = q2;
                        a = a2;
                        this.count = c;
                    }
                    q2 = q;
                    a2 = a;
                }
            }
            this.upstream.cancel();
            q2.clear();
            disposeTimer();
        }

        static final class ConsumerIndexHolder implements Runnable {
            final long index;
            final WindowExactBoundedSubscriber<?> parent;

            ConsumerIndexHolder(long index2, WindowExactBoundedSubscriber<?> parent2) {
                this.index = index2;
                this.parent = parent2;
            }

            public void run() {
                WindowExactBoundedSubscriber<?> p = this.parent;
                if (!p.cancelled) {
                    p.queue.offer(this);
                } else {
                    p.terminated = true;
                }
                if (p.enter()) {
                    p.drainLoop();
                }
            }
        }
    }

    static final class WindowSkipSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription, Runnable {
        final int bufferSize;
        volatile boolean terminated;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        Subscription upstream;
        final List<UnicastProcessor<T>> windows = new LinkedList();
        final Scheduler.Worker worker;

        WindowSkipSubscriber(Subscriber<? super Flowable<T>> actual, long timespan2, long timeskip2, TimeUnit unit2, Scheduler.Worker worker2, int bufferSize2) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan2;
            this.timeskip = timeskip2;
            this.unit = unit2;
            this.worker = worker2;
            this.bufferSize = bufferSize2;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                if (!this.cancelled) {
                    long r = requested();
                    if (r != 0) {
                        UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
                        this.windows.add(w);
                        this.downstream.onNext(w);
                        if (r != LongCompanionObject.MAX_VALUE) {
                            produced(1);
                        }
                        this.worker.schedule(new Completion(w), this.timespan, this.unit);
                        Scheduler.Worker worker2 = this.worker;
                        long j = this.timeskip;
                        worker2.schedulePeriodically(this, j, j, this.unit);
                        s.request(LongCompanionObject.MAX_VALUE);
                        return;
                    }
                    s.cancel();
                    this.downstream.onError(new MissingBackpressureException("Could not emit the first window due to lack of requests"));
                }
            }
        }

        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastProcessor<T> w : this.windows) {
                    w.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(t);
                if (!enter()) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.downstream.onError(t);
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.downstream.onComplete();
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.cancelled = true;
        }

        /* access modifiers changed from: package-private */
        public void complete(UnicastProcessor<T> w) {
            this.queue.offer(new SubjectWork(w, false));
            if (enter()) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            SimplePlainQueue<Object> q;
            int missed;
            SimplePlainQueue<Object> q2 = this.queue;
            Subscriber<? super Flowable<T>> a = this.downstream;
            List<UnicastProcessor<T>> ws = this.windows;
            int missed2 = 1;
            while (!this.terminated) {
                boolean d = this.done;
                Object v = q2.poll();
                boolean empty = v == null;
                boolean sw = v instanceof SubjectWork;
                if (d && (empty || sw)) {
                    q2.clear();
                    Throwable e = this.error;
                    if (e != null) {
                        for (UnicastProcessor<T> w : ws) {
                            w.onError(e);
                        }
                    } else {
                        for (UnicastProcessor<T> w2 : ws) {
                            w2.onComplete();
                        }
                    }
                    ws.clear();
                    this.worker.dispose();
                    return;
                } else if (empty) {
                    missed2 = leave(-missed2);
                    if (missed2 == 0) {
                        return;
                    }
                } else {
                    if (sw) {
                        SubjectWork<T> work = (SubjectWork) v;
                        if (!work.open) {
                            q = q2;
                            missed = missed2;
                            boolean z = d;
                            ws.remove(work.w);
                            work.w.onComplete();
                            if (ws.isEmpty() && this.cancelled) {
                                this.terminated = true;
                            }
                        } else if (this.cancelled) {
                            q = q2;
                            missed = missed2;
                        } else {
                            long r = requested();
                            if (r != 0) {
                                UnicastProcessor<T> w3 = UnicastProcessor.create(this.bufferSize);
                                ws.add(w3);
                                a.onNext(w3);
                                if (r != LongCompanionObject.MAX_VALUE) {
                                    produced(1);
                                }
                                missed = missed2;
                                boolean z2 = d;
                                q = q2;
                                this.worker.schedule(new Completion(w3), this.timespan, this.unit);
                            } else {
                                q = q2;
                                missed = missed2;
                                boolean z3 = d;
                                a.onError(new MissingBackpressureException("Can't emit window due to lack of requests"));
                            }
                        }
                    } else {
                        q = q2;
                        missed = missed2;
                        boolean z4 = d;
                        for (UnicastProcessor<T> w4 : ws) {
                            w4.onNext(v);
                        }
                    }
                    missed2 = missed;
                    q2 = q;
                }
            }
            this.upstream.cancel();
            q2.clear();
            ws.clear();
            this.worker.dispose();
        }

        public void run() {
            SubjectWork<T> sw = new SubjectWork<>(UnicastProcessor.create(this.bufferSize), true);
            if (!this.cancelled) {
                this.queue.offer(sw);
            }
            if (enter()) {
                drainLoop();
            }
        }

        static final class SubjectWork<T> {
            final boolean open;
            final UnicastProcessor<T> w;

            SubjectWork(UnicastProcessor<T> w2, boolean open2) {
                this.w = w2;
                this.open = open2;
            }
        }

        final class Completion implements Runnable {
            private final UnicastProcessor<T> processor;

            Completion(UnicastProcessor<T> processor2) {
                this.processor = processor2;
            }

            public void run() {
                WindowSkipSubscriber.this.complete(this.processor);
            }
        }
    }
}
