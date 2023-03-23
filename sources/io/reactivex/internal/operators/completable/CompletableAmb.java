package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CompletableAmb extends Completable {
    private final CompletableSource[] sources;
    private final Iterable<? extends CompletableSource> sourcesIterable;

    public CompletableAmb(CompletableSource[] sources2, Iterable<? extends CompletableSource> sourcesIterable2) {
        this.sources = sources2;
        this.sourcesIterable = sourcesIterable2;
    }

    public void subscribeActual(CompletableObserver observer) {
        CompletableSource[] sources2 = this.sources;
        int count = 0;
        if (sources2 == null) {
            sources2 = new CompletableSource[8];
            try {
                for (CompletableSource element : this.sourcesIterable) {
                    if (element == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), observer);
                        return;
                    }
                    if (count == sources2.length) {
                        CompletableSource[] b = new CompletableSource[((count >> 2) + count)];
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
                        EmptyDisposable.error(e, observer);
                        return;
                    }
                }
            } catch (Throwable th2) {
                e = th2;
                Exceptions.throwIfFatal(e);
                EmptyDisposable.error(e, observer);
                return;
            }
        } else {
            count = sources2.length;
        }
        CompositeDisposable set = new CompositeDisposable();
        observer.onSubscribe(set);
        AtomicBoolean once = new AtomicBoolean();
        int i2 = 0;
        while (i2 < count) {
            CompletableSource c = sources2[i2];
            if (!set.isDisposed()) {
                if (c == null) {
                    NullPointerException npe = new NullPointerException("One of the sources is null");
                    if (once.compareAndSet(false, true)) {
                        set.dispose();
                        observer.onError(npe);
                        return;
                    }
                    RxJavaPlugins.onError(npe);
                    return;
                }
                c.subscribe(new Amb(once, set, observer));
                i2++;
            } else {
                return;
            }
        }
        if (count == 0) {
            observer.onComplete();
        }
    }

    static final class Amb implements CompletableObserver {
        final CompletableObserver downstream;
        final AtomicBoolean once;
        final CompositeDisposable set;
        Disposable upstream;

        Amb(AtomicBoolean once2, CompositeDisposable set2, CompletableObserver observer) {
            this.once = once2;
            this.set = set2;
            this.downstream = observer;
        }

        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onComplete();
            }
        }

        public void onError(Throwable e) {
            if (this.once.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onSubscribe(Disposable d) {
            this.upstream = d;
            this.set.add(d);
        }
    }
}
