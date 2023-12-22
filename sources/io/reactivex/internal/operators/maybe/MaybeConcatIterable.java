package io.reactivex.internal.operators.maybe;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class MaybeConcatIterable<T> extends Flowable<T> {
    final Iterable<? extends MaybeSource<? extends T>> sources;

    public MaybeConcatIterable(Iterable<? extends MaybeSource<? extends T>> sources) {
        this.sources = sources;
    }

    @Override // io.reactivex.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        try {
            Iterator<? extends MaybeSource<? extends T>> it = (Iterator) ObjectHelper.requireNonNull(this.sources.iterator(), "The sources Iterable returned a null Iterator");
            ConcatMaybeObserver<T> parent = new ConcatMaybeObserver<>(s, it);
            s.onSubscribe(parent);
            parent.drain();
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptySubscription.error(ex, s);
        }
    }

    /* loaded from: classes.dex */
    static final class ConcatMaybeObserver<T> extends AtomicInteger implements MaybeObserver<T>, Subscription {
        private static final long serialVersionUID = 3520831347801429610L;
        final Subscriber<? super T> downstream;
        long produced;
        final Iterator<? extends MaybeSource<? extends T>> sources;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable disposables = new SequentialDisposable();
        final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);

        ConcatMaybeObserver(Subscriber<? super T> actual, Iterator<? extends MaybeSource<? extends T>> sources) {
            this.downstream = actual;
            this.sources = sources;
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
            this.disposables.dispose();
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable d) {
            this.disposables.replace(d);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T value) {
            this.current.lazySet(value);
            drain();
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable e) {
            this.downstream.onError(e);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.current.lazySet(NotificationLite.COMPLETE);
            drain();
        }

        void drain() {
            boolean goNextSource;
            if (getAndIncrement() != 0) {
                return;
            }
            AtomicReference<Object> c = this.current;
            Subscriber<? super T> a = this.downstream;
            Disposable cancelled = this.disposables;
            while (!cancelled.isDisposed()) {
                Object o = c.get();
                if (o != null) {
                    if (o != NotificationLite.COMPLETE) {
                        long p = this.produced;
                        if (p != this.requested.get()) {
                            this.produced = 1 + p;
                            c.lazySet(null);
                            goNextSource = true;
                            a.onNext(o);
                        } else {
                            goNextSource = false;
                        }
                    } else {
                        c.lazySet(null);
                        goNextSource = true;
                    }
                    if (goNextSource && !cancelled.isDisposed()) {
                        try {
                            boolean b = this.sources.hasNext();
                            if (b) {
                                try {
                                    MaybeSource<? extends T> source = (MaybeSource) ObjectHelper.requireNonNull(this.sources.next(), "The source Iterator returned a null MaybeSource");
                                    source.subscribe(this);
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    a.onError(ex);
                                    return;
                                }
                            } else {
                                a.onComplete();
                            }
                        } catch (Throwable ex2) {
                            Exceptions.throwIfFatal(ex2);
                            a.onError(ex2);
                            return;
                        }
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            c.lazySet(null);
        }
    }
}
