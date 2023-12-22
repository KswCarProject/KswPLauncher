package io.reactivex.internal.operators.mixed;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class CompletableAndThenObservable<R> extends Observable<R> {
    final ObservableSource<? extends R> other;
    final CompletableSource source;

    public CompletableAndThenObservable(CompletableSource source, ObservableSource<? extends R> other) {
        this.source = source;
        this.other = other;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super R> observer) {
        AndThenObservableObserver<R> parent = new AndThenObservableObserver<>(observer, this.other);
        observer.onSubscribe(parent);
        this.source.subscribe(parent);
    }

    /* loaded from: classes.dex */
    static final class AndThenObservableObserver<R> extends AtomicReference<Disposable> implements Observer<R>, CompletableObserver, Disposable {
        private static final long serialVersionUID = -8948264376121066672L;
        final Observer<? super R> downstream;
        ObservableSource<? extends R> other;

        AndThenObservableObserver(Observer<? super R> downstream, ObservableSource<? extends R> other) {
            this.other = other;
            this.downstream = downstream;
        }

        @Override // io.reactivex.Observer
        public void onNext(R t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable t) {
            this.downstream.onError(t);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            ObservableSource<? extends R> o = this.other;
            if (o == null) {
                this.downstream.onComplete();
                return;
            }
            this.other = null;
            o.subscribe(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable d) {
            DisposableHelper.replace(this, d);
        }
    }
}
