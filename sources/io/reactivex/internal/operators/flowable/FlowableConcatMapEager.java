package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscribers.InnerQueuedSubscriberSupport;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableConcatMapEager<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;

    public FlowableConcatMapEager(Flowable<T> source, Function<? super T, ? extends Publisher<? extends R>> mapper2, int maxConcurrency2, int prefetch2, ErrorMode errorMode2) {
        super(source);
        this.mapper = mapper2;
        this.maxConcurrency = maxConcurrency2;
        this.prefetch = prefetch2;
        this.errorMode = errorMode2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe(new ConcatMapEagerDelayErrorSubscriber(s, this.mapper, this.maxConcurrency, this.prefetch, this.errorMode));
    }

    static final class ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, InnerQueuedSubscriberSupport<R> {
        private static final long serialVersionUID = -4255299542215038287L;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final AtomicLong requested = new AtomicLong();
        final SpscLinkedArrayQueue<InnerQueuedSubscriber<R>> subscribers;
        Subscription upstream;

        ConcatMapEagerDelayErrorSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends Publisher<? extends R>> mapper2, int maxConcurrency2, int prefetch2, ErrorMode errorMode2) {
            this.downstream = actual;
            this.mapper = mapper2;
            this.maxConcurrency = maxConcurrency2;
            this.prefetch = prefetch2;
            this.errorMode = errorMode2;
            this.subscribers = new SpscLinkedArrayQueue<>(Math.min(prefetch2, maxConcurrency2));
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                s.request(i == Integer.MAX_VALUE ? LongCompanionObject.MAX_VALUE : (long) i);
            }
        }

        public void onNext(T t) {
            try {
                Publisher<? extends R> p = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                InnerQueuedSubscriber<R> inner = new InnerQueuedSubscriber<>(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(inner);
                    p.subscribe(inner);
                    if (this.cancelled) {
                        inner.cancel();
                        drainAndCancel();
                    }
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.upstream.cancel();
                onError(ex);
            }
        }

        public void onError(Throwable t) {
            if (this.errors.addThrowable(t)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                drainAndCancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            InnerQueuedSubscriber<R> inner = this.current;
            this.current = null;
            if (inner != null) {
                inner.cancel();
            }
            while (true) {
                InnerQueuedSubscriber<R> poll = this.subscribers.poll();
                InnerQueuedSubscriber<R> inner2 = poll;
                if (poll != null) {
                    inner2.cancel();
                } else {
                    return;
                }
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void innerNext(InnerQueuedSubscriber<R> inner, R value) {
            if (inner.queue().offer(value)) {
                drain();
                return;
            }
            inner.cancel();
            innerError(inner, new MissingBackpressureException());
        }

        public void innerError(InnerQueuedSubscriber<R> inner, Throwable e) {
            if (this.errors.addThrowable(e)) {
                inner.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void innerComplete(InnerQueuedSubscriber<R> inner) {
            inner.setDone();
            drain();
        }

        /* JADX WARNING: Removed duplicated region for block: B:79:0x0015 A[LOOP:0: B:4:0x0015->B:79:0x0015, LOOP_END, PHI: r2 
          PHI: (r2v3 'missed' int) = (r2v2 'missed' int), (r2v4 'missed' int) binds: [B:87:0x0015, B:88:0x0015] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x013b A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r18 = this;
                r1 = r18
                int r0 = r18.getAndIncrement()
                if (r0 == 0) goto L_0x0009
                return
            L_0x0009:
                r0 = 1
                io.reactivex.internal.subscribers.InnerQueuedSubscriber<R> r2 = r1.current
                org.reactivestreams.Subscriber<? super R> r3 = r1.downstream
                io.reactivex.internal.util.ErrorMode r4 = r1.errorMode
                r17 = r2
                r2 = r0
                r0 = r17
            L_0x0015:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                r7 = 0
                if (r0 != 0) goto L_0x005f
                io.reactivex.internal.util.ErrorMode r9 = io.reactivex.internal.util.ErrorMode.END
                if (r4 == r9) goto L_0x003a
                io.reactivex.internal.util.AtomicThrowable r9 = r1.errors
                java.lang.Object r9 = r9.get()
                java.lang.Throwable r9 = (java.lang.Throwable) r9
                if (r9 == 0) goto L_0x003a
                r18.cancelAll()
                io.reactivex.internal.util.AtomicThrowable r10 = r1.errors
                java.lang.Throwable r10 = r10.terminate()
                r3.onError(r10)
                return
            L_0x003a:
                boolean r9 = r1.done
                io.reactivex.internal.queue.SpscLinkedArrayQueue<io.reactivex.internal.subscribers.InnerQueuedSubscriber<R>> r10 = r1.subscribers
                java.lang.Object r10 = r10.poll()
                r0 = r10
                io.reactivex.internal.subscribers.InnerQueuedSubscriber r0 = (io.reactivex.internal.subscribers.InnerQueuedSubscriber) r0
                if (r9 == 0) goto L_0x0059
                if (r0 != 0) goto L_0x0059
                io.reactivex.internal.util.AtomicThrowable r10 = r1.errors
                java.lang.Throwable r10 = r10.terminate()
                if (r10 == 0) goto L_0x0055
                r3.onError(r10)
                goto L_0x0058
            L_0x0055:
                r3.onComplete()
            L_0x0058:
                return
            L_0x0059:
                if (r0 == 0) goto L_0x005d
                r1.current = r0
            L_0x005d:
                r9 = r0
                goto L_0x0060
            L_0x005f:
                r9 = r0
            L_0x0060:
                r10 = 0
                if (r9 == 0) goto L_0x0119
                io.reactivex.internal.fuseable.SimpleQueue r11 = r9.queue()
                if (r11 == 0) goto L_0x0119
            L_0x0069:
                int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                r12 = 1
                r14 = 0
                if (r0 == 0) goto L_0x00d4
                boolean r0 = r1.cancelled
                if (r0 == 0) goto L_0x0078
                r18.cancelAll()
                return
            L_0x0078:
                io.reactivex.internal.util.ErrorMode r0 = io.reactivex.internal.util.ErrorMode.IMMEDIATE
                if (r4 != r0) goto L_0x0098
                io.reactivex.internal.util.AtomicThrowable r0 = r1.errors
                java.lang.Object r0 = r0.get()
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                if (r0 == 0) goto L_0x0098
                r1.current = r14
                r9.cancel()
                r18.cancelAll()
                io.reactivex.internal.util.AtomicThrowable r12 = r1.errors
                java.lang.Throwable r12 = r12.terminate()
                r3.onError(r12)
                return
            L_0x0098:
                boolean r15 = r9.isDone()
                java.lang.Object r0 = r11.poll()     // Catch:{ all -> 0x00c1 }
                if (r0 != 0) goto L_0x00a6
                r16 = 1
                goto L_0x00a8
            L_0x00a6:
                r16 = 0
            L_0x00a8:
                if (r15 == 0) goto L_0x00b6
                if (r16 == 0) goto L_0x00b6
                r9 = 0
                r1.current = r14
                org.reactivestreams.Subscription r14 = r1.upstream
                r14.request(r12)
                r10 = 1
                goto L_0x00d4
            L_0x00b6:
                if (r16 == 0) goto L_0x00b9
                goto L_0x00d4
            L_0x00b9:
                r3.onNext(r0)
                long r7 = r7 + r12
                r9.requestOne()
                goto L_0x0069
            L_0x00c1:
                r0 = move-exception
                r12 = r0
                r0 = r12
                io.reactivex.exceptions.Exceptions.throwIfFatal(r0)
                r12 = 0
                r1.current = r12
                r9.cancel()
                r18.cancelAll()
                r3.onError(r0)
                return
            L_0x00d4:
                int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r0 != 0) goto L_0x0119
                boolean r0 = r1.cancelled
                if (r0 == 0) goto L_0x00e0
                r18.cancelAll()
                return
            L_0x00e0:
                io.reactivex.internal.util.ErrorMode r0 = io.reactivex.internal.util.ErrorMode.IMMEDIATE
                if (r4 != r0) goto L_0x0101
                io.reactivex.internal.util.AtomicThrowable r0 = r1.errors
                java.lang.Object r0 = r0.get()
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                if (r0 == 0) goto L_0x0101
                r12 = 0
                r1.current = r12
                r9.cancel()
                r18.cancelAll()
                io.reactivex.internal.util.AtomicThrowable r12 = r1.errors
                java.lang.Throwable r12 = r12.terminate()
                r3.onError(r12)
                return
            L_0x0101:
                boolean r0 = r9.isDone()
                boolean r14 = r11.isEmpty()
                if (r0 == 0) goto L_0x0119
                if (r14 == 0) goto L_0x0119
                r9 = 0
                r15 = 0
                r1.current = r15
                org.reactivestreams.Subscription r15 = r1.upstream
                r15.request(r12)
                r10 = 1
                r0 = r9
                goto L_0x011a
            L_0x0119:
                r0 = r9
            L_0x011a:
                r11 = 0
                int r9 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r9 == 0) goto L_0x012f
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
                if (r9 == 0) goto L_0x012f
                java.util.concurrent.atomic.AtomicLong r9 = r1.requested
                long r11 = -r7
                r9.addAndGet(r11)
            L_0x012f:
                if (r10 == 0) goto L_0x0133
                goto L_0x0015
            L_0x0133:
                int r9 = -r2
                int r2 = r1.addAndGet(r9)
                if (r2 != 0) goto L_0x013c
                return
            L_0x013c:
                goto L_0x0015
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableConcatMapEager.ConcatMapEagerDelayErrorSubscriber.drain():void");
        }
    }
}
