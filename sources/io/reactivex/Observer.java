package io.reactivex;

import io.reactivex.disposables.Disposable;

/* loaded from: classes.dex */
public interface Observer<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(Disposable disposable);
}
