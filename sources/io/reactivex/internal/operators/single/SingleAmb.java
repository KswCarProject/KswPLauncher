package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleAmb<T> extends Single<T> {
    private final SingleSource<? extends T>[] sources;
    private final Iterable<? extends SingleSource<? extends T>> sourcesIterable;

    public SingleAmb(SingleSource<? extends T>[] sources2, Iterable<? extends SingleSource<? extends T>> sourcesIterable2) {
        this.sources = sources2;
        this.sourcesIterable = sourcesIterable2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> observer) {
        SingleSource<? extends T>[] sources2 = this.sources;
        int count = 0;
        if (sources2 == null) {
            sources2 = new SingleSource[8];
            try {
                for (SingleSource<? extends T> element : this.sourcesIterable) {
                    if (element == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), (SingleObserver<?>) observer);
                        return;
                    }
                    if (count == sources2.length) {
                        SingleSource<? extends T>[] b = new SingleSource[((count >> 2) + count)];
                        System.arraycopy(sources2, 0, b, 0, count);
                        sources2 = b;
                    }
                    int count2 = count + 1;
                    try {
                        sources2[count] = element;
                        count = count2;
                    } catch (Throwable th) {
                        e = th;
                        int i = count2;
                        Exceptions.throwIfFatal(e);
                        EmptyDisposable.error(e, (SingleObserver<?>) observer);
                        return;
                    }
                }
            } catch (Throwable th2) {
                e = th2;
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, (SingleObserver<?>) observer);
                return;
            }
        } else {
            count = sources2.length;
        }
        AtomicBoolean winner = new AtomicBoolean();
        CompositeDisposable set = new CompositeDisposable();
        observer.onSubscribe(set);
        int i2 = 0;
        while (i2 < count) {
            SingleSource<? extends T> s1 = sources2[i2];
            if (!set.isDisposed()) {
                if (s1 == null) {
                    set.dispose();
                    Throwable e = new NullPointerException("One of the sources is null");
                    if (winner.compareAndSet(false, true)) {
                        observer.onError(e);
                        return;
                    } else {
                        RxJavaPlugins.onError(e);
                        return;
                    }
                } else {
                    s1.subscribe(new AmbSingleObserver(observer, set, winner));
                    i2++;
                }
            } else {
                return;
            }
        }
    }

    static final class AmbSingleObserver<T> implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;
        final CompositeDisposable set;
        Disposable upstream;
        final AtomicBoolean winner;

        AmbSingleObserver(SingleObserver<? super T> observer, CompositeDisposable set2, AtomicBoolean winner2) {
            this.downstream = observer;
            this.set = set2;
            this.winner = winner2;
        }

        public void onSubscribe(Disposable d) {
            this.upstream = d;
            this.set.add(d);
        }

        public void onSuccess(T value) {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onSuccess(value);
            }
        }

        public void onError(Throwable e) {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }
    }
}
