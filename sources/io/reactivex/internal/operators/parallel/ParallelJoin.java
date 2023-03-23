package io.reactivex.internal.operators.parallel;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelJoin<T> extends Flowable<T> {
    final boolean delayErrors;
    final int prefetch;
    final ParallelFlowable<? extends T> source;

    public ParallelJoin(ParallelFlowable<? extends T> source2, int prefetch2, boolean delayErrors2) {
        this.source = source2;
        this.prefetch = prefetch2;
        this.delayErrors = delayErrors2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        JoinSubscriptionBase<T> parent;
        if (this.delayErrors) {
            parent = new JoinSubscriptionDelayError<>(s, this.source.parallelism(), this.prefetch);
        } else {
            parent = new JoinSubscription<>(s, this.source.parallelism(), this.prefetch);
        }
        s.onSubscribe(parent);
        this.source.subscribe(parent.subscribers);
    }

    static abstract class JoinSubscriptionBase<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3100232009247827843L;
        volatile boolean cancelled;
        final AtomicInteger done = new AtomicInteger();
        final Subscriber<? super T> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final JoinInnerSubscriber<T>[] subscribers;

        /* access modifiers changed from: package-private */
        public abstract void drain();

        /* access modifiers changed from: package-private */
        public abstract void onComplete();

        /* access modifiers changed from: package-private */
        public abstract void onError(Throwable th);

        /* access modifiers changed from: package-private */
        public abstract void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t);

        JoinSubscriptionBase(Subscriber<? super T> actual, int n, int prefetch) {
            this.downstream = actual;
            JoinInnerSubscriber<T>[] a = new JoinInnerSubscriber[n];
            for (int i = 0; i < n; i++) {
                a[i] = new JoinInnerSubscriber<>(this, prefetch);
            }
            this.subscribers = a;
            this.done.lazySet(n);
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    cleanup();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            for (JoinInnerSubscriber<T> s : this.subscribers) {
                s.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void cleanup() {
            for (JoinInnerSubscriber<T> s : this.subscribers) {
                s.queue = null;
            }
        }
    }

    static final class JoinSubscription<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = 6312374661811000451L;

        JoinSubscription(Subscriber<? super T> actual, int n, int prefetch) {
            super(actual, n, prefetch);
        }

        public void onNext(JoinInnerSubscriber<T> inner, T value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.downstream.onNext(value);
                    if (this.requested.get() != LongCompanionObject.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.request(1);
                } else if (!inner.getQueue().offer(value)) {
                    cancelAll();
                    Throwable mbe = new MissingBackpressureException("Queue full?!");
                    if (this.errors.compareAndSet((Object) null, mbe)) {
                        this.downstream.onError(mbe);
                        return;
                    } else {
                        RxJavaPlugins.onError(mbe);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!inner.getQueue().offer(value)) {
                cancelAll();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable e) {
            if (this.errors.compareAndSet((Object) null, e)) {
                cancelAll();
                drain();
            } else if (e != this.errors.get()) {
                RxJavaPlugins.onError(e);
            }
        }

        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
            if (r12 == false) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0060, code lost:
            if (r13 == false) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0062, code lost:
            r4.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0066, code lost:
            if (r13 == false) goto L_0x0010;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainLoop() {
            /*
                r18 = this;
                r0 = r18
                r1 = 1
                io.reactivex.internal.operators.parallel.ParallelJoin$JoinInnerSubscriber[] r2 = r0.subscribers
                int r3 = r2.length
                org.reactivestreams.Subscriber r4 = r0.downstream
            L_0x0008:
                java.util.concurrent.atomic.AtomicLong r5 = r0.requested
                long r5 = r5.get()
                r7 = 0
            L_0x0010:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 == 0) goto L_0x006a
                boolean r9 = r0.cancelled
                if (r9 == 0) goto L_0x001c
                r18.cleanup()
                return
            L_0x001c:
                io.reactivex.internal.util.AtomicThrowable r9 = r0.errors
                java.lang.Object r9 = r9.get()
                java.lang.Throwable r9 = (java.lang.Throwable) r9
                if (r9 == 0) goto L_0x002d
                r18.cleanup()
                r4.onError(r9)
                return
            L_0x002d:
                java.util.concurrent.atomic.AtomicInteger r12 = r0.done
                int r12 = r12.get()
                if (r12 != 0) goto L_0x0037
                r12 = 1
                goto L_0x0038
            L_0x0037:
                r12 = 0
            L_0x0038:
                r13 = 1
                r14 = 0
            L_0x003a:
                int r15 = r2.length
                if (r14 >= r15) goto L_0x005e
                r15 = r2[r14]
                io.reactivex.internal.fuseable.SimplePlainQueue<T> r10 = r15.queue
                if (r10 == 0) goto L_0x005b
                java.lang.Object r11 = r10.poll()
                if (r11 == 0) goto L_0x005b
                r13 = 0
                r4.onNext(r11)
                r15.requestOne()
                r16 = 1
                long r16 = r7 + r16
                r7 = r16
                int r16 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
                if (r16 != 0) goto L_0x005b
                goto L_0x006a
            L_0x005b:
                int r14 = r14 + 1
                goto L_0x003a
            L_0x005e:
                if (r12 == 0) goto L_0x0066
                if (r13 == 0) goto L_0x0066
                r4.onComplete()
                return
            L_0x0066:
                if (r13 == 0) goto L_0x0069
                goto L_0x006a
            L_0x0069:
                goto L_0x0010
            L_0x006a:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 != 0) goto L_0x00af
                boolean r9 = r0.cancelled
                if (r9 == 0) goto L_0x0076
                r18.cleanup()
                return
            L_0x0076:
                io.reactivex.internal.util.AtomicThrowable r9 = r0.errors
                java.lang.Object r9 = r9.get()
                java.lang.Throwable r9 = (java.lang.Throwable) r9
                if (r9 == 0) goto L_0x0087
                r18.cleanup()
                r4.onError(r9)
                return
            L_0x0087:
                java.util.concurrent.atomic.AtomicInteger r10 = r0.done
                int r10 = r10.get()
                if (r10 != 0) goto L_0x0091
                r10 = 1
                goto L_0x0092
            L_0x0091:
                r10 = 0
            L_0x0092:
                r11 = 1
                r12 = 0
            L_0x0094:
                if (r12 >= r3) goto L_0x00a7
                r13 = r2[r12]
                io.reactivex.internal.fuseable.SimplePlainQueue<T> r14 = r13.queue
                if (r14 == 0) goto L_0x00a4
                boolean r15 = r14.isEmpty()
                if (r15 != 0) goto L_0x00a4
                r11 = 0
                goto L_0x00a7
            L_0x00a4:
                int r12 = r12 + 1
                goto L_0x0094
            L_0x00a7:
                if (r10 == 0) goto L_0x00af
                if (r11 == 0) goto L_0x00af
                r4.onComplete()
                return
            L_0x00af:
                r9 = 0
                int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r9 == 0) goto L_0x00c4
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
                if (r9 == 0) goto L_0x00c4
                java.util.concurrent.atomic.AtomicLong r9 = r0.requested
                long r10 = -r7
                r9.addAndGet(r10)
            L_0x00c4:
                int r9 = r18.get()
                if (r9 != r1) goto L_0x00d3
                int r10 = -r1
                int r1 = r0.addAndGet(r10)
                if (r1 != 0) goto L_0x00d4
                return
            L_0x00d3:
                r1 = r9
            L_0x00d4:
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelJoin.JoinSubscription.drainLoop():void");
        }
    }

    static final class JoinSubscriptionDelayError<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = -5737965195918321883L;

        JoinSubscriptionDelayError(Subscriber<? super T> actual, int n, int prefetch) {
            super(actual, n, prefetch);
        }

        /* access modifiers changed from: package-private */
        public void onNext(JoinInnerSubscriber<T> inner, T value) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                if (!inner.getQueue().offer(value) && inner.cancel()) {
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                if (this.requested.get() != 0) {
                    this.downstream.onNext(value);
                    if (this.requested.get() != LongCompanionObject.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.request(1);
                } else if (!inner.getQueue().offer(value)) {
                    inner.cancel();
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                    drainLoop();
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        /* access modifiers changed from: package-private */
        public void onError(Throwable e) {
            this.errors.addThrowable(e);
            this.done.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: package-private */
        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x004d, code lost:
            if (r9 == false) goto L_0x0069;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
            if (r12 == false) goto L_0x0069;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
            if (((java.lang.Throwable) r0.errors.get()) == null) goto L_0x0065;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005b, code lost:
            r4.onError(r0.errors.terminate());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0065, code lost:
            r4.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0069, code lost:
            if (r12 == false) goto L_0x0010;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainLoop() {
            /*
                r18 = this;
                r0 = r18
                r1 = 1
                io.reactivex.internal.operators.parallel.ParallelJoin$JoinInnerSubscriber[] r2 = r0.subscribers
                int r3 = r2.length
                org.reactivestreams.Subscriber r4 = r0.downstream
            L_0x0008:
                java.util.concurrent.atomic.AtomicLong r5 = r0.requested
                long r5 = r5.get()
                r7 = 0
            L_0x0010:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                r11 = 1
                if (r9 == 0) goto L_0x006d
                boolean r9 = r0.cancelled
                if (r9 == 0) goto L_0x001d
                r18.cleanup()
                return
            L_0x001d:
                java.util.concurrent.atomic.AtomicInteger r9 = r0.done
                int r9 = r9.get()
                if (r9 != 0) goto L_0x0027
                r9 = r11
                goto L_0x0028
            L_0x0027:
                r9 = 0
            L_0x0028:
                r12 = 1
                r13 = 0
            L_0x002a:
                if (r13 >= r3) goto L_0x004d
                r14 = r2[r13]
                io.reactivex.internal.fuseable.SimplePlainQueue<T> r15 = r14.queue
                if (r15 == 0) goto L_0x004a
                java.lang.Object r10 = r15.poll()
                if (r10 == 0) goto L_0x004a
                r12 = 0
                r4.onNext(r10)
                r14.requestOne()
                r16 = 1
                long r16 = r7 + r16
                r7 = r16
                int r16 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
                if (r16 != 0) goto L_0x004a
                goto L_0x006d
            L_0x004a:
                int r13 = r13 + 1
                goto L_0x002a
            L_0x004d:
                if (r9 == 0) goto L_0x0069
                if (r12 == 0) goto L_0x0069
                io.reactivex.internal.util.AtomicThrowable r10 = r0.errors
                java.lang.Object r10 = r10.get()
                java.lang.Throwable r10 = (java.lang.Throwable) r10
                if (r10 == 0) goto L_0x0065
                io.reactivex.internal.util.AtomicThrowable r11 = r0.errors
                java.lang.Throwable r11 = r11.terminate()
                r4.onError(r11)
                goto L_0x0068
            L_0x0065:
                r4.onComplete()
            L_0x0068:
                return
            L_0x0069:
                if (r12 == 0) goto L_0x006c
                goto L_0x006d
            L_0x006c:
                goto L_0x0010
            L_0x006d:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 != 0) goto L_0x00b6
                boolean r9 = r0.cancelled
                if (r9 == 0) goto L_0x0079
                r18.cleanup()
                return
            L_0x0079:
                java.util.concurrent.atomic.AtomicInteger r9 = r0.done
                int r9 = r9.get()
                if (r9 != 0) goto L_0x0083
                r10 = r11
                goto L_0x0084
            L_0x0083:
                r10 = 0
            L_0x0084:
                r9 = r10
                r10 = 1
                r11 = 0
            L_0x0087:
                if (r11 >= r3) goto L_0x009a
                r12 = r2[r11]
                io.reactivex.internal.fuseable.SimplePlainQueue<T> r13 = r12.queue
                if (r13 == 0) goto L_0x0097
                boolean r14 = r13.isEmpty()
                if (r14 != 0) goto L_0x0097
                r10 = 0
                goto L_0x009a
            L_0x0097:
                int r11 = r11 + 1
                goto L_0x0087
            L_0x009a:
                if (r9 == 0) goto L_0x00b6
                if (r10 == 0) goto L_0x00b6
                io.reactivex.internal.util.AtomicThrowable r11 = r0.errors
                java.lang.Object r11 = r11.get()
                java.lang.Throwable r11 = (java.lang.Throwable) r11
                if (r11 == 0) goto L_0x00b2
                io.reactivex.internal.util.AtomicThrowable r12 = r0.errors
                java.lang.Throwable r12 = r12.terminate()
                r4.onError(r12)
                goto L_0x00b5
            L_0x00b2:
                r4.onComplete()
            L_0x00b5:
                return
            L_0x00b6:
                r9 = 0
                int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r9 == 0) goto L_0x00cb
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
                if (r9 == 0) goto L_0x00cb
                java.util.concurrent.atomic.AtomicLong r9 = r0.requested
                long r10 = -r7
                r9.addAndGet(r10)
            L_0x00cb:
                int r9 = r18.get()
                if (r9 != r1) goto L_0x00da
                int r10 = -r1
                int r1 = r0.addAndGet(r10)
                if (r1 != 0) goto L_0x00db
                return
            L_0x00da:
                r1 = r9
            L_0x00db:
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelJoin.JoinSubscriptionDelayError.drainLoop():void");
        }
    }

    static final class JoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 8410034718427740355L;
        final int limit;
        final JoinSubscriptionBase<T> parent;
        final int prefetch;
        long produced;
        volatile SimplePlainQueue<T> queue;

        JoinInnerSubscriber(JoinSubscriptionBase<T> parent2, int prefetch2) {
            this.parent = parent2;
            this.prefetch = prefetch2;
            this.limit = prefetch2 - (prefetch2 >> 2);
        }

        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this, s, (long) this.prefetch);
        }

        public void onNext(T t) {
            this.parent.onNext(this, t);
        }

        public void onError(Throwable t) {
            this.parent.onError(t);
        }

        public void onComplete() {
            this.parent.onComplete();
        }

        public void requestOne() {
            long p = this.produced + 1;
            if (p == ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(p);
                return;
            }
            this.produced = p;
        }

        public void request(long n) {
            long p = this.produced + n;
            if (p >= ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(p);
                return;
            }
            this.produced = p;
        }

        public boolean cancel() {
            return SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: package-private */
        public SimplePlainQueue<T> getQueue() {
            SimplePlainQueue<T> q = this.queue;
            if (q != null) {
                return q;
            }
            SimplePlainQueue<T> q2 = new SpscArrayQueue<>(this.prefetch);
            this.queue = q2;
            return q2;
        }
    }
}
