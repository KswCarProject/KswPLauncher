package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* loaded from: classes.dex */
public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observable) {
        this.observable = observable;
    }

    @Override // io.reactivex.Completable
    protected void subscribeActual(CompletableObserver observer) {
        this.observable.subscribe(new CompletableFromObservableObserver(observer));
    }

    /* loaded from: classes.dex */
    static final class CompletableFromObservableObserver<T> implements Observer<T> {

        /* renamed from: co */
        final CompletableObserver f276co;

        CompletableFromObservableObserver(CompletableObserver co) {
            this.f276co = co;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            this.f276co.onSubscribe(d);
        }

        @Override // io.reactivex.Observer
        public void onNext(T value) {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable e) {
            this.f276co.onError(e);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.f276co.onComplete();
        }
    }
}
