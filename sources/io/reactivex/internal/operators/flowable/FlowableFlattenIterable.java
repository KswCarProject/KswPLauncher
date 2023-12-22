package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableFlattenIterable<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;

    public FlowableFlattenIterable(Flowable<T> source, Function<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
        super(source);
        this.mapper = mapper;
        this.prefetch = prefetch;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> s) {
        if (this.source instanceof Callable) {
            try {
                Object call = ((Callable) this.source).call();
                if (call == null) {
                    EmptySubscription.complete(s);
                    return;
                }
                try {
                    Iterable<? extends R> iterable = this.mapper.apply(call);
                    Iterator<? extends R> it = iterable.iterator();
                    FlowableFromIterable.subscribe(s, it);
                    return;
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    EmptySubscription.error(ex, s);
                    return;
                }
            } catch (Throwable ex2) {
                Exceptions.throwIfFatal(ex2);
                EmptySubscription.error(ex2, s);
                return;
            }
        }
        this.source.subscribe((FlowableSubscriber) new FlattenIterableSubscriber(s, this.mapper, this.prefetch));
    }

    /* loaded from: classes.dex */
    static final class FlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final Subscriber<? super R> downstream;
        int fusionMode;
        final int limit;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        Subscription upstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        FlattenIterableSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
            this.downstream = actual;
            this.mapper = mapper;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (m == 2) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.downstream.onSubscribe(this);
                        s.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                s.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.fusionMode == 0 && !this.queue.offer(t)) {
                onError(new MissingBackpressureException("Queue is full?!"));
            } else {
                drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
            if (!this.done && ExceptionHelper.addThrowable(this.error, t)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
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
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> a = this.downstream;
            SimpleQueue<T> q = this.queue;
            boolean replenish = this.fusionMode != 1;
            int missed = 1;
            Iterator<? extends R> it = this.current;
            while (true) {
                if (it == null) {
                    boolean d = this.done;
                    try {
                        T t = q.poll();
                        boolean empty = t == null;
                        if (checkTerminated(d, empty, a, q)) {
                            return;
                        }
                        if (t != null) {
                            try {
                                Iterable<? extends R> iterable = this.mapper.apply(t);
                                it = iterable.iterator();
                                boolean b = it.hasNext();
                                if (!b) {
                                    it = null;
                                    consumedOne(replenish);
                                } else {
                                    this.current = it;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                this.upstream.cancel();
                                ExceptionHelper.addThrowable(this.error, ex);
                                a.onError(ExceptionHelper.terminate(this.error));
                                return;
                            }
                        }
                    } catch (Throwable ex2) {
                        Exceptions.throwIfFatal(ex2);
                        this.upstream.cancel();
                        ExceptionHelper.addThrowable(this.error, ex2);
                        Throwable ex3 = ExceptionHelper.terminate(this.error);
                        this.current = null;
                        q.clear();
                        a.onError(ex3);
                        return;
                    }
                }
                if (it != null) {
                    long r = this.requested.get();
                    long e = 0;
                    while (true) {
                        if (e == r) {
                            break;
                        } else if (checkTerminated(this.done, false, a, q)) {
                            return;
                        } else {
                            try {
                                a.onNext((Object) ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value"));
                                if (checkTerminated(this.done, false, a, q)) {
                                    return;
                                }
                                e++;
                                try {
                                    boolean b2 = it.hasNext();
                                    if (!b2) {
                                        consumedOne(replenish);
                                        it = null;
                                        this.current = null;
                                        break;
                                    }
                                } catch (Throwable ex4) {
                                    Exceptions.throwIfFatal(ex4);
                                    this.current = null;
                                    this.upstream.cancel();
                                    ExceptionHelper.addThrowable(this.error, ex4);
                                    a.onError(ExceptionHelper.terminate(this.error));
                                    return;
                                }
                            } catch (Throwable ex5) {
                                Exceptions.throwIfFatal(ex5);
                                this.current = null;
                                this.upstream.cancel();
                                ExceptionHelper.addThrowable(this.error, ex5);
                                a.onError(ExceptionHelper.terminate(this.error));
                                return;
                            }
                        }
                    }
                    if (e == r) {
                        boolean d2 = this.done;
                        boolean empty2 = q.isEmpty() && it == null;
                        if (checkTerminated(d2, empty2, a, q)) {
                            return;
                        }
                    }
                    if (e != 0 && r != LongCompanionObject.MAX_VALUE) {
                        this.requested.addAndGet(-e);
                    }
                    if (it == null) {
                        continue;
                    }
                }
                missed = addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
        }

        void consumedOne(boolean enabled) {
            if (enabled) {
                int c = this.consumed + 1;
                if (c == this.limit) {
                    this.consumed = 0;
                    this.upstream.request(c);
                    return;
                }
                this.consumed = c;
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, SimpleQueue<?> q) {
            if (this.cancelled) {
                this.current = null;
                q.clear();
                return true;
            } else if (d) {
                Throwable ex = this.error.get();
                if (ex != null) {
                    Throwable ex2 = ExceptionHelper.terminate(this.error);
                    this.current = null;
                    q.clear();
                    a.onError(ex2);
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

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.current == null && this.queue.isEmpty();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public R poll() throws Exception {
            Iterator<? extends R> it = this.current;
            while (true) {
                if (it == null) {
                    T v = this.queue.poll();
                    if (v == null) {
                        return null;
                    }
                    it = this.mapper.apply(v).iterator();
                    if (!it.hasNext()) {
                        it = null;
                    } else {
                        this.current = it;
                        break;
                    }
                } else {
                    break;
                }
            }
            R r = (R) ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.current = null;
            }
            return r;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int requestedMode) {
            return ((requestedMode & 1) == 0 || this.fusionMode != 1) ? 0 : 1;
        }
    }
}
