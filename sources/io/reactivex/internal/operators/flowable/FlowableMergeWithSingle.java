package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableMergeWithSingle<T> extends AbstractFlowableWithUpstream<T, T> {
    final SingleSource<? extends T> other;

    public FlowableMergeWithSingle(Flowable<T> source, SingleSource<? extends T> other) {
        super(source);
        this.other = other;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        MergeWithObserver<T> parent = new MergeWithObserver<>(subscriber);
        subscriber.onSubscribe(parent);
        this.source.subscribe((FlowableSubscriber) parent);
        this.other.subscribe(parent.otherObserver);
    }

    /* loaded from: classes.dex */
    static final class MergeWithObserver<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean cancelled;
        int consumed;
        final Subscriber<? super T> downstream;
        long emitted;
        final int limit;
        volatile boolean mainDone;
        volatile int otherState;
        final int prefetch;
        volatile SimplePlainQueue<T> queue;
        T singleItem;
        final AtomicReference<Subscription> mainSubscription = new AtomicReference<>();
        final OtherObserver<T> otherObserver = new OtherObserver<>(this);
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();

        MergeWithObserver(Subscriber<? super T> downstream) {
            this.downstream = downstream;
            int bufferSize = Flowable.bufferSize();
            this.prefetch = bufferSize;
            this.limit = bufferSize - (bufferSize >> 2);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            SubscriptionHelper.setOnce(this.mainSubscription, s, this.prefetch);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                long e = this.emitted;
                if (this.requested.get() != e) {
                    SimplePlainQueue<T> q = this.queue;
                    if (q == null || q.isEmpty()) {
                        this.emitted = 1 + e;
                        this.downstream.onNext(t);
                        int c = this.consumed + 1;
                        if (c == this.limit) {
                            this.consumed = 0;
                            this.mainSubscription.get().request(c);
                        } else {
                            this.consumed = c;
                        }
                    } else {
                        q.offer(t);
                    }
                } else {
                    SimplePlainQueue<T> q2 = getOrCreateQueue();
                    q2.offer(t);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SimplePlainQueue<T> q3 = getOrCreateQueue();
                q3.offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable ex) {
            if (this.error.addThrowable(ex)) {
                DisposableHelper.dispose(this.otherObserver);
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            BackpressureHelper.add(this.requested, n);
            drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        void otherSuccess(T value) {
            if (compareAndSet(0, 1)) {
                long e = this.emitted;
                if (this.requested.get() != e) {
                    this.emitted = 1 + e;
                    this.downstream.onNext(value);
                    this.otherState = 2;
                } else {
                    this.singleItem = value;
                    this.otherState = 1;
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            } else {
                this.singleItem = value;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        void otherError(Throwable ex) {
            if (this.error.addThrowable(ex)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        SimplePlainQueue<T> getOrCreateQueue() {
            SimplePlainQueue<T> q = this.queue;
            if (q == null) {
                SimplePlainQueue<T> q2 = new SpscArrayQueue<>(Flowable.bufferSize());
                this.queue = q2;
                return q2;
            }
            return q;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            long e;
            Subscriber<? super T> actual = this.downstream;
            int missed = 1;
            long e2 = this.emitted;
            int c = this.consumed;
            int lim = this.limit;
            do {
                long r = this.requested.get();
                while (e2 != r) {
                    if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    } else if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        actual.onError(this.error.terminate());
                        return;
                    } else {
                        int os = this.otherState;
                        if (os == 1) {
                            this.singleItem = null;
                            this.otherState = 2;
                            actual.onNext((T) this.singleItem);
                            e2++;
                        } else {
                            boolean d = this.mainDone;
                            SimplePlainQueue<T> q = this.queue;
                            Object obj = (Object) (q != null ? q.poll() : null);
                            boolean empty = obj == 0;
                            if (d && empty && os == 2) {
                                this.queue = null;
                                actual.onComplete();
                                return;
                            } else if (empty) {
                                break;
                            } else {
                                actual.onNext(obj);
                                long e3 = e2 + 1;
                                c++;
                                if (c != lim) {
                                    e = e3;
                                } else {
                                    c = 0;
                                    e = e3;
                                    long e4 = lim;
                                    this.mainSubscription.get().request(e4);
                                }
                                e2 = e;
                            }
                        }
                    }
                }
                if (e2 == r) {
                    if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    } else if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        actual.onError(this.error.terminate());
                        return;
                    } else {
                        boolean d2 = this.mainDone;
                        SimplePlainQueue<T> q2 = this.queue;
                        boolean empty2 = q2 == null || q2.isEmpty();
                        if (d2 && empty2 && this.otherState == 2) {
                            this.queue = null;
                            actual.onComplete();
                            return;
                        }
                    }
                }
                this.emitted = e2;
                this.consumed = c;
                missed = addAndGet(-missed);
            } while (missed != 0);
        }

        /* loaded from: classes.dex */
        static final class OtherObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<T> parent;

            OtherObserver(MergeWithObserver<T> parent) {
                this.parent = parent;
            }

            @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable e) {
                this.parent.otherError(e);
            }
        }
    }
}
