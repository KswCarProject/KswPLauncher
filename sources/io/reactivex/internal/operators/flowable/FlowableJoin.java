package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.operators.flowable.FlowableGroupJoin;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AbstractFlowableWithUpstream<TLeft, R> {
    final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
    final Publisher<? extends TRight> other;
    final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
    final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;

    public FlowableJoin(Flowable<TLeft> source, Publisher<? extends TRight> other2, Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd2, Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd2, BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector2) {
        super(source);
        this.other = other2;
        this.leftEnd = leftEnd2;
        this.rightEnd = rightEnd2;
        this.resultSelector = resultSelector2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> s) {
        JoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> parent = new JoinSubscription<>(s, this.leftEnd, this.rightEnd, this.resultSelector);
        s.onSubscribe(parent);
        FlowableGroupJoin.LeftRightSubscriber left = new FlowableGroupJoin.LeftRightSubscriber(parent, true);
        parent.disposables.add(left);
        FlowableGroupJoin.LeftRightSubscriber right = new FlowableGroupJoin.LeftRightSubscriber(parent, false);
        parent.disposables.add(right);
        this.source.subscribe(left);
        this.other.subscribe(right);
    }

    static final class JoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Subscription, FlowableGroupJoin.JoinSupport {
        static final Integer LEFT_CLOSE = 3;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_CLOSE = 4;
        static final Integer RIGHT_VALUE = 2;
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final CompositeDisposable disposables = new CompositeDisposable();
        final Subscriber<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
        final AtomicLong requested = new AtomicLong();
        final BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinSubscription(Subscriber<? super R> actual, Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd2, Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd2, BiFunction<? super TLeft, ? super TRight, ? extends R> resultSelector2) {
            this.downstream = actual;
            this.leftEnd = leftEnd2;
            this.rightEnd = rightEnd2;
            this.resultSelector = resultSelector2;
            this.active = new AtomicInteger(2);
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            this.disposables.dispose();
        }

        /* access modifiers changed from: package-private */
        public void errorAll(Subscriber<?> a) {
            Throwable ex = ExceptionHelper.terminate(this.error);
            this.lefts.clear();
            this.rights.clear();
            a.onError(ex);
        }

        /* access modifiers changed from: package-private */
        public void fail(Throwable exc, Subscriber<?> a, SimpleQueue<?> q) {
            Exceptions.throwIfFatal(exc);
            ExceptionHelper.addThrowable(this.error, exc);
            q.clear();
            cancelAll();
            errorAll(a);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            int missed;
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue<Object> q = this.queue;
                Subscriber<? super R> a = this.downstream;
                int missed2 = 1;
                while (this.cancelled == 0) {
                    if (this.error.get() != null) {
                        q.clear();
                        cancelAll();
                        errorAll(a);
                        return;
                    }
                    boolean d = this.active.get() == 0;
                    Integer mode = (Integer) q.poll();
                    boolean empty = mode == null;
                    if (d && empty) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        a.onComplete();
                        return;
                    } else if (empty) {
                        missed2 = addAndGet(-missed2);
                        if (missed2 == 0) {
                            return;
                        }
                    } else {
                        TRight val = q.poll();
                        if (mode == LEFT_VALUE) {
                            TRight tright = val;
                            int i = this.leftIndex;
                            this.leftIndex = i + 1;
                            int idx = i;
                            this.lefts.put(Integer.valueOf(idx), tright);
                            try {
                                Publisher publisher = (Publisher) ObjectHelper.requireNonNull(this.leftEnd.apply(tright), "The leftEnd returned a null Publisher");
                                TRight right = new FlowableGroupJoin.LeftRightEndSubscriber(this, true, idx);
                                this.disposables.add(right);
                                publisher.subscribe(right);
                                Throwable ex = this.error.get();
                                if (ex != null) {
                                    q.clear();
                                    cancelAll();
                                    errorAll(a);
                                    return;
                                }
                                long r = this.requested.get();
                                Iterator<TRight> it = this.rights.values().iterator();
                                missed = missed2;
                                Throwable th = ex;
                                long e = 0;
                                while (it.hasNext()) {
                                    TRight end = right;
                                    Iterator<TRight> it2 = it;
                                    try {
                                        R w = ObjectHelper.requireNonNull(this.resultSelector.apply(tright, it.next()), "The resultSelector returned a null value");
                                        if (e != r) {
                                            a.onNext(w);
                                            e++;
                                            right = end;
                                            it = it2;
                                        } else {
                                            R r2 = w;
                                            ExceptionHelper.addThrowable(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            q.clear();
                                            cancelAll();
                                            errorAll(a);
                                            return;
                                        }
                                    } catch (Throwable exc) {
                                        fail(exc, a, q);
                                        return;
                                    }
                                }
                                TRight end2 = right;
                                if (e != 0) {
                                    BackpressureHelper.produced(this.requested, e);
                                }
                            } catch (Throwable exc2) {
                                int i2 = missed2;
                                fail(exc2, a, q);
                                return;
                            }
                        } else {
                            missed = missed2;
                            if (mode == RIGHT_VALUE) {
                                TRight right2 = val;
                                int i3 = this.rightIndex;
                                this.rightIndex = i3 + 1;
                                int idx2 = i3;
                                this.rights.put(Integer.valueOf(idx2), right2);
                                try {
                                    Publisher publisher2 = (Publisher) ObjectHelper.requireNonNull(this.rightEnd.apply(right2), "The rightEnd returned a null Publisher");
                                    FlowableGroupJoin.LeftRightEndSubscriber end3 = new FlowableGroupJoin.LeftRightEndSubscriber(this, false, idx2);
                                    this.disposables.add(end3);
                                    publisher2.subscribe(end3);
                                    Throwable ex2 = this.error.get();
                                    if (ex2 != null) {
                                        q.clear();
                                        cancelAll();
                                        errorAll(a);
                                        return;
                                    }
                                    long r3 = this.requested.get();
                                    Iterator<TLeft> it3 = this.lefts.values().iterator();
                                    Throwable th2 = ex2;
                                    FlowableGroupJoin.LeftRightEndSubscriber leftRightEndSubscriber = end3;
                                    long e2 = 0;
                                    while (it3.hasNext()) {
                                        int idx3 = idx2;
                                        Iterator<TLeft> it4 = it3;
                                        try {
                                            R w2 = ObjectHelper.requireNonNull(this.resultSelector.apply(it3.next(), right2), "The resultSelector returned a null value");
                                            if (e2 != r3) {
                                                a.onNext(w2);
                                                e2++;
                                                idx2 = idx3;
                                                it3 = it4;
                                            } else {
                                                R r4 = w2;
                                                ExceptionHelper.addThrowable(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                                q.clear();
                                                cancelAll();
                                                errorAll(a);
                                                return;
                                            }
                                        } catch (Throwable exc3) {
                                            fail(exc3, a, q);
                                            return;
                                        }
                                    }
                                    if (e2 != 0) {
                                        BackpressureHelper.produced(this.requested, e2);
                                    }
                                } catch (Throwable exc4) {
                                    int i4 = idx2;
                                    fail(exc4, a, q);
                                    return;
                                }
                            } else if (mode == LEFT_CLOSE) {
                                FlowableGroupJoin.LeftRightEndSubscriber end4 = (FlowableGroupJoin.LeftRightEndSubscriber) val;
                                this.lefts.remove(Integer.valueOf(end4.index));
                                this.disposables.remove(end4);
                            } else if (mode == RIGHT_CLOSE) {
                                FlowableGroupJoin.LeftRightEndSubscriber end5 = (FlowableGroupJoin.LeftRightEndSubscriber) val;
                                this.rights.remove(Integer.valueOf(end5.index));
                                this.disposables.remove(end5);
                            }
                        }
                        missed2 = missed;
                    }
                }
                q.clear();
            }
        }

        public void innerError(Throwable ex) {
            if (ExceptionHelper.addThrowable(this.error, ex)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }

        public void innerComplete(FlowableGroupJoin.LeftRightSubscriber sender) {
            this.disposables.delete(sender);
            this.active.decrementAndGet();
            drain();
        }

        public void innerValue(boolean isLeft, Object o) {
            synchronized (this) {
                this.queue.offer(isLeft ? LEFT_VALUE : RIGHT_VALUE, o);
            }
            drain();
        }

        public void innerClose(boolean isLeft, FlowableGroupJoin.LeftRightEndSubscriber index) {
            synchronized (this) {
                this.queue.offer(isLeft ? LEFT_CLOSE : RIGHT_CLOSE, index);
            }
            drain();
        }

        public void innerCloseError(Throwable ex) {
            if (ExceptionHelper.addThrowable(this.error, ex)) {
                drain();
            } else {
                RxJavaPlugins.onError(ex);
            }
        }
    }
}
