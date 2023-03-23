package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class MaybeAmb<T> extends Maybe<T> {
    private final MaybeSource<? extends T>[] sources;
    private final Iterable<? extends MaybeSource<? extends T>> sourcesIterable;

    public MaybeAmb(MaybeSource<? extends T>[] sources2, Iterable<? extends MaybeSource<? extends T>> sourcesIterable2) {
        this.sources = sources2;
        this.sourcesIterable = sourcesIterable2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> observer) {
        MaybeSource<? extends T>[] sources2 = this.sources;
        int count = 0;
        if (sources2 == null) {
            sources2 = new MaybeSource[8];
            try {
                for (MaybeSource<? extends T> element : this.sourcesIterable) {
                    if (element == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), (MaybeObserver<?>) observer);
                        return;
                    }
                    if (count == sources2.length) {
                        MaybeSource<? extends T>[] b = new MaybeSource[((count >> 2) + count)];
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
                        EmptyDisposable.error(e, (MaybeObserver<?>) observer);
                        return;
                    }
                }
            } catch (Throwable th2) {
                e = th2;
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, (MaybeObserver<?>) observer);
                return;
            }
        } else {
            count = sources2.length;
        }
        CompositeDisposable set = new CompositeDisposable();
        observer.onSubscribe(set);
        AtomicBoolean winner = new AtomicBoolean();
        int i2 = 0;
        while (i2 < count) {
            MaybeSource<? extends T> s = sources2[i2];
            if (!set.isDisposed()) {
                if (s == null) {
                    set.dispose();
                    NullPointerException ex = new NullPointerException("One of the MaybeSources is null");
                    if (winner.compareAndSet(false, true)) {
                        observer.onError(ex);
                        return;
                    } else {
                        RxJavaPlugins.onError(ex);
                        return;
                    }
                } else {
                    s.subscribe(new AmbMaybeObserver(observer, set, winner));
                    i2++;
                }
            } else {
                return;
            }
        }
        if (count == 0) {
            observer.onComplete();
        }
    }

    static final class AmbMaybeObserver<T> implements MaybeObserver<T> {
        final MaybeObserver<? super T> downstream;
        final CompositeDisposable set;
        Disposable upstream;
        final AtomicBoolean winner;

        AmbMaybeObserver(MaybeObserver<? super T> downstream2, CompositeDisposable set2, AtomicBoolean winner2) {
            this.downstream = downstream2;
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

        public void onComplete() {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onComplete();
            }
        }
    }
}
