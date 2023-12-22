package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowablePublishMulticast<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayError;
    final int prefetch;
    final Function<? super Flowable<T>, ? extends Publisher<? extends R>> selector;

    public FlowablePublishMulticast(Flowable<T> source, Function<? super Flowable<T>, ? extends Publisher<? extends R>> selector, int prefetch, boolean delayError) {
        super(source);
        this.selector = selector;
        this.prefetch = prefetch;
        this.delayError = delayError;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super R> s) {
        MulticastProcessor<T> mp = new MulticastProcessor<>(this.prefetch, this.delayError);
        try {
            Publisher<? extends R> other = (Publisher) ObjectHelper.requireNonNull(this.selector.apply(mp), "selector returned a null Publisher");
            OutputCanceller<R> out = new OutputCanceller<>(s, mp);
            other.subscribe(out);
            this.source.subscribe((FlowableSubscriber) mp);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptySubscription.error(ex, s);
        }
    }

    /* loaded from: classes.dex */
    static final class OutputCanceller<R> implements FlowableSubscriber<R>, Subscription {
        final Subscriber<? super R> downstream;
        final MulticastProcessor<?> processor;
        Subscription upstream;

        OutputCanceller(Subscriber<? super R> actual, MulticastProcessor<?> processor) {
            this.downstream = actual;
            this.processor = processor;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(R t) {
            this.downstream.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            this.downstream.onError(t);
            this.processor.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
            this.processor.dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            this.upstream.request(n);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
            this.processor.dispose();
        }
    }

    /* loaded from: classes.dex */
    static final class MulticastProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
        static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
        static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
        int consumed;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        volatile SimpleQueue<T> queue;
        int sourceMode;
        final AtomicInteger wip = new AtomicInteger();
        final AtomicReference<Subscription> upstream = new AtomicReference<>();
        final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        MulticastProcessor(int prefetch, boolean delayError) {
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
            this.delayError = delayError;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.upstream, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.done = true;
                        drain();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        QueueDrainHelper.request(s, this.prefetch);
                        return;
                    }
                }
                this.queue = QueueDrainHelper.createQueue(this.prefetch);
                QueueDrainHelper.request(s, this.prefetch);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            SimpleQueue<T> q;
            SubscriptionHelper.cancel(this.upstream);
            if (this.wip.getAndIncrement() == 0 && (q = this.queue) != null) {
                q.clear();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.get() == SubscriptionHelper.CANCELLED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                this.upstream.get().cancel();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
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
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        boolean add(MulticastSubscription<T> s) {
            MulticastSubscription<T>[] current;
            MulticastSubscription<T>[] next;
            do {
                current = this.subscribers.get();
                if (current == TERMINATED) {
                    return false;
                }
                int n = current.length;
                next = new MulticastSubscription[n + 1];
                System.arraycopy(current, 0, next, 0, n);
                next[n] = s;
            } while (!this.subscribers.compareAndSet(current, next));
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        void remove(MulticastSubscription<T> s) {
            MulticastSubscription<T>[] current;
            MulticastSubscription<T>[] next;
            do {
                current = this.subscribers.get();
                int n = current.length;
                if (n == 0) {
                    return;
                }
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (current[i] != s) {
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
                    next = EMPTY;
                } else {
                    MulticastSubscription<T>[] next2 = new MulticastSubscription[n - 1];
                    System.arraycopy(current, 0, next2, 0, j);
                    System.arraycopy(current, j + 1, next2, j, (n - j) - 1);
                    next = next2;
                }
            } while (!this.subscribers.compareAndSet(current, next));
        }

        @Override // io.reactivex.Flowable
        protected void subscribeActual(Subscriber<? super T> s) {
            MulticastSubscription<T> ms = new MulticastSubscription<>(s, this);
            s.onSubscribe(ms);
            if (add(ms)) {
                if (ms.isCancelled()) {
                    remove(ms);
                    return;
                } else {
                    drain();
                    return;
                }
            }
            Throwable ex = this.error;
            if (ex != null) {
                s.onError(ex);
            } else {
                s.onComplete();
            }
        }

        void drain() {
            AtomicReference<MulticastSubscription<T>[]> subs;
            int upstreamConsumed;
            Throwable ex;
            boolean d;
            boolean empty;
            Throwable ex2;
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            SimpleQueue<T> q = this.queue;
            int upstreamConsumed2 = this.consumed;
            int localLimit = this.limit;
            boolean canRequest = this.sourceMode != 1;
            AtomicReference<MulticastSubscription<T>[]> subs2 = this.subscribers;
            MulticastSubscription<T>[] array = subs2.get();
            int missed = 1;
            int missed2 = upstreamConsumed2;
            SimpleQueue<T> q2 = q;
            while (true) {
                int n = array.length;
                if (q2 == null || n == 0) {
                    subs = subs2;
                } else {
                    long r = LongCompanionObject.MAX_VALUE;
                    int length = array.length;
                    int i = 0;
                    while (i < length) {
                        MulticastSubscription<T> ms = array[i];
                        AtomicReference<MulticastSubscription<T>[]> subs3 = subs2;
                        long u = ms.get() - ms.emitted;
                        if (u != Long.MIN_VALUE) {
                            if (r > u) {
                                r = u;
                            }
                        } else {
                            n--;
                        }
                        i++;
                        subs2 = subs3;
                    }
                    subs = subs2;
                    if (n != 0) {
                        upstreamConsumed = missed2;
                    } else {
                        r = 0;
                        upstreamConsumed = missed2;
                    }
                    while (r != 0) {
                        if (isDisposed()) {
                            q2.clear();
                            return;
                        }
                        boolean d2 = this.done;
                        if (d2 && !this.delayError && (ex2 = this.error) != null) {
                            errorAll(ex2);
                            return;
                        }
                        try {
                            T v = q2.poll();
                            boolean empty2 = v == null;
                            if (d2 && empty2) {
                                Throwable ex3 = this.error;
                                if (ex3 != null) {
                                    errorAll(ex3);
                                    return;
                                } else {
                                    completeAll();
                                    return;
                                }
                            } else if (empty2) {
                                break;
                            } else {
                                boolean subscribersChange = false;
                                int length2 = array.length;
                                int i2 = 0;
                                while (i2 < length2) {
                                    int i3 = length2;
                                    MulticastSubscription<T> ms2 = array[i2];
                                    long msr = ms2.get();
                                    if (msr != Long.MIN_VALUE) {
                                        if (msr == LongCompanionObject.MAX_VALUE) {
                                            d = d2;
                                            empty = empty2;
                                        } else {
                                            d = d2;
                                            empty = empty2;
                                            ms2.emitted++;
                                        }
                                        ms2.downstream.onNext(v);
                                    } else {
                                        d = d2;
                                        empty = empty2;
                                        subscribersChange = true;
                                    }
                                    i2++;
                                    length2 = i3;
                                    d2 = d;
                                    empty2 = empty;
                                }
                                r--;
                                if (canRequest && (upstreamConsumed = upstreamConsumed + 1) == localLimit) {
                                    upstreamConsumed = 0;
                                    this.upstream.get().request(localLimit);
                                }
                                MulticastSubscription<T>[] freshArray = subs.get();
                                if (subscribersChange || freshArray != array) {
                                    array = freshArray;
                                    missed2 = upstreamConsumed;
                                    subs2 = subs;
                                    break;
                                }
                            }
                        } catch (Throwable ex4) {
                            Exceptions.throwIfFatal(ex4);
                            SubscriptionHelper.cancel(this.upstream);
                            errorAll(ex4);
                            return;
                        }
                    }
                    if (r == 0) {
                        if (isDisposed()) {
                            q2.clear();
                            return;
                        }
                        boolean d3 = this.done;
                        if (d3 && !this.delayError && (ex = this.error) != null) {
                            errorAll(ex);
                            return;
                        } else if (d3 && q2.isEmpty()) {
                            Throwable ex5 = this.error;
                            if (ex5 != null) {
                                errorAll(ex5);
                                return;
                            } else {
                                completeAll();
                                return;
                            }
                        }
                    }
                    missed2 = upstreamConsumed;
                }
                this.consumed = missed2;
                missed = this.wip.addAndGet(-missed);
                if (missed != 0) {
                    if (q2 == null) {
                        q2 = this.queue;
                    }
                    MulticastSubscription<T>[] array2 = subs.get();
                    array = array2;
                    subs2 = subs;
                } else {
                    return;
                }
            }
        }

        void errorAll(Throwable ex) {
            MulticastSubscription<T>[] andSet;
            for (MulticastSubscription<T> ms : this.subscribers.getAndSet(TERMINATED)) {
                if (ms.get() != Long.MIN_VALUE) {
                    ms.downstream.onError(ex);
                }
            }
        }

        void completeAll() {
            MulticastSubscription<T>[] andSet;
            for (MulticastSubscription<T> ms : this.subscribers.getAndSet(TERMINATED)) {
                if (ms.get() != Long.MIN_VALUE) {
                    ms.downstream.onComplete();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 8664815189257569791L;
        final Subscriber<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        MulticastSubscription(Subscriber<? super T> actual, MulticastProcessor<T> parent) {
            this.downstream = actual;
            this.parent = parent;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.addCancel(this, n);
                this.parent.drain();
            }
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
