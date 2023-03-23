package io.reactivex.internal.operators.mixed;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableConcatMapSingle<T, R> extends Flowable<R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final int prefetch;
    final Flowable<T> source;

    public FlowableConcatMapSingle(Flowable<T> source2, Function<? super T, ? extends SingleSource<? extends R>> mapper2, ErrorMode errorMode2, int prefetch2) {
        this.source = source2;
        this.mapper = mapper2;
        this.errorMode = errorMode2;
        this.prefetch = prefetch2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe(new ConcatMapSingleSubscriber(s, this.mapper, this.prefetch, this.errorMode));
    }

    static final class ConcatMapSingleSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        long emitted;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapSingleObserver<R> inner = new ConcatMapSingleObserver<>(this);
        R item;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final int prefetch;
        final SimplePlainQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        volatile int state;
        Subscription upstream;

        ConcatMapSingleSubscriber(Subscriber<? super R> downstream2, Function<? super T, ? extends SingleSource<? extends R>> mapper2, int prefetch2, ErrorMode errorMode2) {
            this.downstream = downstream2;
            this.mapper = mapper2;
            this.prefetch = prefetch2;
            this.errorMode = errorMode2;
            this.queue = new SpscArrayQueue(prefetch2);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.queue.offer(t)) {
                this.upstream.cancel();
                onError(new MissingBackpressureException("queue full?!"));
                return;
            }
            drain();
        }

        public void onError(Throwable t) {
            if (this.errors.addThrowable(t)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.inner.dispose();
                }
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

        public void request(long n) {
            BackpressureHelper.add(this.requested, n);
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
                this.item = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void innerSuccess(R item2) {
            this.item = item2;
            this.state = 2;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable ex) {
            if (this.errors.addThrowable(ex)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                this.state = 0;
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> downstream2 = this.downstream;
                ErrorMode errorMode2 = this.errorMode;
                SimplePlainQueue<T> queue2 = this.queue;
                AtomicThrowable errors2 = this.errors;
                AtomicLong requested2 = this.requested;
                int i = this.prefetch;
                int limit = i - (i >> 1);
                int missed = 1;
                while (true) {
                    if (this.cancelled != 0) {
                        queue2.clear();
                        this.item = null;
                    } else {
                        int s = this.state;
                        if (errors2.get() == null || !(errorMode2 == ErrorMode.IMMEDIATE || (errorMode2 == ErrorMode.BOUNDARY && s == 0))) {
                            if (s == 0) {
                                boolean d = this.done;
                                T v = queue2.poll();
                                boolean empty = v == null;
                                if (d && empty) {
                                    Throwable ex = errors2.terminate();
                                    if (ex == null) {
                                        downstream2.onComplete();
                                        return;
                                    } else {
                                        downstream2.onError(ex);
                                        return;
                                    }
                                } else if (!empty) {
                                    int c = this.consumed + 1;
                                    if (c == limit) {
                                        this.consumed = 0;
                                        boolean z = empty;
                                        this.upstream.request((long) limit);
                                    } else {
                                        this.consumed = c;
                                    }
                                    try {
                                        SingleSource<? extends R> ss = (SingleSource) ObjectHelper.requireNonNull(this.mapper.apply(v), "The mapper returned a null SingleSource");
                                        this.state = 1;
                                        ss.subscribe(this.inner);
                                    } catch (Throwable ex2) {
                                        Exceptions.throwIfFatal(ex2);
                                        this.upstream.cancel();
                                        queue2.clear();
                                        errors2.addThrowable(ex2);
                                        downstream2.onError(errors2.terminate());
                                        return;
                                    }
                                }
                            } else if (s == 2) {
                                long e = this.emitted;
                                if (e != requested2.get()) {
                                    R w = this.item;
                                    this.item = null;
                                    downstream2.onNext(w);
                                    this.emitted = 1 + e;
                                    this.state = 0;
                                }
                            }
                        }
                    }
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
                queue2.clear();
                this.item = null;
                downstream2.onError(errors2.terminate());
            }
        }

        static final class ConcatMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapSingleSubscriber<?, R> parent;

            ConcatMapSingleObserver(ConcatMapSingleSubscriber<?, R> parent2) {
                this.parent = parent2;
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.replace(this, d);
            }

            public void onSuccess(R t) {
                this.parent.innerSuccess(t);
            }

            public void onError(Throwable e) {
                this.parent.innerError(e);
            }

            /* access modifiers changed from: package-private */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
