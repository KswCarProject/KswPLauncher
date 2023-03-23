package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicFuseableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.Callable;

public final class ObservableDistinct<T, K> extends AbstractObservableWithUpstream<T, T> {
    final Callable<? extends Collection<? super K>> collectionSupplier;
    final Function<? super T, K> keySelector;

    public ObservableDistinct(ObservableSource<T> source, Function<? super T, K> keySelector2, Callable<? extends Collection<? super K>> collectionSupplier2) {
        super(source);
        this.keySelector = keySelector2;
        this.collectionSupplier = collectionSupplier2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        try {
            this.source.subscribe(new DistinctObserver(observer, this.keySelector, (Collection) ObjectHelper.requireNonNull(this.collectionSupplier.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptyDisposable.error(ex, (Observer<?>) observer);
        }
    }

    static final class DistinctObserver<T, K> extends BasicFuseableObserver<T, T> {
        final Collection<? super K> collection;
        final Function<? super T, K> keySelector;

        DistinctObserver(Observer<? super T> actual, Function<? super T, K> keySelector2, Collection<? super K> collection2) {
            super(actual);
            this.keySelector = keySelector2;
            this.collection = collection2;
        }

        public void onNext(T value) {
            if (!this.done) {
                if (this.sourceMode == 0) {
                    try {
                        if (this.collection.add(ObjectHelper.requireNonNull(this.keySelector.apply(value), "The keySelector returned a null key"))) {
                            this.downstream.onNext(value);
                        }
                    } catch (Throwable ex) {
                        fail(ex);
                    }
                } else {
                    this.downstream.onNext(null);
                }
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaPlugins.onError(e);
                return;
            }
            this.done = true;
            this.collection.clear();
            this.downstream.onError(e);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.collection.clear();
                this.downstream.onComplete();
            }
        }

        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public T poll() throws java.lang.Exception {
            /*
                r4 = this;
            L_0x0000:
                io.reactivex.internal.fuseable.QueueDisposable r0 = r4.qd
                java.lang.Object r0 = r0.poll()
                if (r0 == 0) goto L_0x001e
                java.util.Collection<? super K> r1 = r4.collection
                io.reactivex.functions.Function<? super T, K> r2 = r4.keySelector
                java.lang.Object r2 = r2.apply(r0)
                java.lang.String r3 = "The keySelector returned a null key"
                java.lang.Object r2 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r2, (java.lang.String) r3)
                boolean r1 = r1.add(r2)
                if (r1 == 0) goto L_0x001d
                goto L_0x001e
            L_0x001d:
                goto L_0x0000
            L_0x001e:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableDistinct.DistinctObserver.poll():java.lang.Object");
        }

        public void clear() {
            this.collection.clear();
            super.clear();
        }
    }
}
