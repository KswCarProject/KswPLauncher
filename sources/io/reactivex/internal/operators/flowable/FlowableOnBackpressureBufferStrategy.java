package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Action;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableOnBackpressureBufferStrategy<T> extends AbstractFlowableWithUpstream<T, T> {
    final long bufferSize;
    final Action onOverflow;
    final BackpressureOverflowStrategy strategy;

    public FlowableOnBackpressureBufferStrategy(Flowable<T> source, long bufferSize, Action onOverflow, BackpressureOverflowStrategy strategy) {
        super(source);
        this.bufferSize = bufferSize;
        this.onOverflow = onOverflow;
        this.strategy = strategy;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new OnBackpressureBufferStrategySubscriber(s, this.onOverflow, this.strategy, this.bufferSize));
    }

    /* loaded from: classes.dex */
    static final class OnBackpressureBufferStrategySubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 3240706908776709697L;
        final long bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        final Action onOverflow;
        final BackpressureOverflowStrategy strategy;
        Subscription upstream;
        final AtomicLong requested = new AtomicLong();
        final Deque<T> deque = new ArrayDeque();

        OnBackpressureBufferStrategySubscriber(Subscriber<? super T> actual, Action onOverflow, BackpressureOverflowStrategy strategy, long bufferSize) {
            this.downstream = actual;
            this.onOverflow = onOverflow;
            this.strategy = strategy;
            this.bufferSize = bufferSize;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.upstream, s)) {
                this.upstream = s;
                this.downstream.onSubscribe(this);
                s.request(LongCompanionObject.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            boolean callOnOverflow = false;
            boolean callError = false;
            Deque<T> dq = this.deque;
            synchronized (dq) {
                if (dq.size() == this.bufferSize) {
                    switch (C18821.$SwitchMap$io$reactivex$BackpressureOverflowStrategy[this.strategy.ordinal()]) {
                        case 1:
                            dq.pollLast();
                            dq.offer(t);
                            callOnOverflow = true;
                            break;
                        case 2:
                            dq.poll();
                            dq.offer(t);
                            callOnOverflow = true;
                            break;
                        default:
                            callError = true;
                            break;
                    }
                } else {
                    dq.offer(t);
                }
            }
            if (callOnOverflow) {
                Action action = this.onOverflow;
                if (action != null) {
                    try {
                        action.run();
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.upstream.cancel();
                        onError(ex);
                    }
                }
            } else if (callError) {
                this.upstream.cancel();
                onError(new MissingBackpressureException());
            } else {
                drain();
            }
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
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() == 0) {
                clear(this.deque);
            }
        }

        void clear(Deque<T> dq) {
            synchronized (dq) {
                dq.clear();
            }
        }

        void drain() {
            boolean empty;
            T v;
            if (getAndIncrement() != 0) {
                return;
            }
            int missed = 1;
            Deque<T> dq = this.deque;
            Subscriber<? super T> a = this.downstream;
            do {
                long r = this.requested.get();
                long e = 0;
                while (e != r) {
                    if (this.cancelled) {
                        clear(dq);
                        return;
                    }
                    boolean d = this.done;
                    synchronized (dq) {
                        v = dq.poll();
                    }
                    boolean empty2 = v == null;
                    if (d) {
                        Throwable ex = this.error;
                        if (ex != null) {
                            clear(dq);
                            a.onError(ex);
                            return;
                        } else if (empty2) {
                            a.onComplete();
                            return;
                        }
                    }
                    if (empty2) {
                        break;
                    }
                    a.onNext(v);
                    e++;
                }
                if (e == r) {
                    if (this.cancelled) {
                        clear(dq);
                        return;
                    }
                    boolean d2 = this.done;
                    synchronized (dq) {
                        empty = dq.isEmpty();
                    }
                    if (d2) {
                        Throwable ex2 = this.error;
                        if (ex2 != null) {
                            clear(dq);
                            a.onError(ex2);
                            return;
                        } else if (empty) {
                            a.onComplete();
                            return;
                        }
                    }
                }
                if (e != 0) {
                    BackpressureHelper.produced(this.requested, e);
                }
                missed = addAndGet(-missed);
            } while (missed != 0);
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableOnBackpressureBufferStrategy$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C18821 {
        static final /* synthetic */ int[] $SwitchMap$io$reactivex$BackpressureOverflowStrategy;

        static {
            int[] iArr = new int[BackpressureOverflowStrategy.values().length];
            $SwitchMap$io$reactivex$BackpressureOverflowStrategy = iArr;
            try {
                iArr[BackpressureOverflowStrategy.DROP_LATEST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$reactivex$BackpressureOverflowStrategy[BackpressureOverflowStrategy.DROP_OLDEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
