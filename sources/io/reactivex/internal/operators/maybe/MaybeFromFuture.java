package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.exceptions.Exceptions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class MaybeFromFuture<T> extends Maybe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public MaybeFromFuture(Future<? extends T> future2, long timeout2, TimeUnit unit2) {
        this.future = future2;
        this.timeout = timeout2;
        this.unit = unit2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> observer) {
        T v;
        Disposable d = Disposables.empty();
        observer.onSubscribe(d);
        if (!d.isDisposed()) {
            try {
                long j = this.timeout;
                if (j <= 0) {
                    v = this.future.get();
                } else {
                    v = this.future.get(j, this.unit);
                }
                if (d.isDisposed()) {
                    return;
                }
                if (v == null) {
                    observer.onComplete();
                } else {
                    observer.onSuccess(v);
                }
            } catch (Throwable th) {
                ex = th;
                if (ex instanceof ExecutionException) {
                    ex = ex.getCause();
                }
                Exceptions.throwIfFatal(ex);
                if (!d.isDisposed()) {
                    observer.onError(ex);
                }
            }
        }
    }
}
