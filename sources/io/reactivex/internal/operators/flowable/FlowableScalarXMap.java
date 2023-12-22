package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.ScalarSubscription;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableScalarXMap {
    private FlowableScalarXMap() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, R> boolean tryScalarXMapSubscribe(Publisher<T> source, Subscriber<? super R> subscriber, Function<? super T, ? extends Publisher<? extends R>> mapper) {
        if (source instanceof Callable) {
            try {
                Object obj = (Object) ((Callable) source).call();
                if (obj == 0) {
                    EmptySubscription.complete(subscriber);
                    return true;
                }
                try {
                    Publisher<? extends R> r = (Publisher) ObjectHelper.requireNonNull(mapper.apply(obj), "The mapper returned a null Publisher");
                    if (r instanceof Callable) {
                        try {
                            Object call = ((Callable) r).call();
                            if (call == null) {
                                EmptySubscription.complete(subscriber);
                                return true;
                            }
                            subscriber.onSubscribe(new ScalarSubscription(subscriber, call));
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            EmptySubscription.error(ex, subscriber);
                            return true;
                        }
                    } else {
                        r.subscribe(subscriber);
                    }
                    return true;
                } catch (Throwable ex2) {
                    Exceptions.throwIfFatal(ex2);
                    EmptySubscription.error(ex2, subscriber);
                    return true;
                }
            } catch (Throwable ex3) {
                Exceptions.throwIfFatal(ex3);
                EmptySubscription.error(ex3, subscriber);
                return true;
            }
        }
        return false;
    }

    public static <T, U> Flowable<U> scalarXMap(T value, Function<? super T, ? extends Publisher<? extends U>> mapper) {
        return RxJavaPlugins.onAssembly(new ScalarXMapFlowable(value, mapper));
    }

    /* loaded from: classes.dex */
    static final class ScalarXMapFlowable<T, R> extends Flowable<R> {
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final T value;

        ScalarXMapFlowable(T value, Function<? super T, ? extends Publisher<? extends R>> mapper) {
            this.value = value;
            this.mapper = mapper;
        }

        @Override // io.reactivex.Flowable
        public void subscribeActual(Subscriber<? super R> s) {
            try {
                Publisher<? extends R> other = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply((T) this.value), "The mapper returned a null Publisher");
                if (other instanceof Callable) {
                    try {
                        Object call = ((Callable) other).call();
                        if (call == null) {
                            EmptySubscription.complete(s);
                            return;
                        } else {
                            s.onSubscribe(new ScalarSubscription(s, call));
                            return;
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        EmptySubscription.error(ex, s);
                        return;
                    }
                }
                other.subscribe(s);
            } catch (Throwable e) {
                EmptySubscription.error(e, s);
            }
        }
    }
}
