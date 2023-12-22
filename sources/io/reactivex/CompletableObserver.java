package io.reactivex;

import io.reactivex.disposables.Disposable;

/* loaded from: classes.dex */
public interface CompletableObserver {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(Disposable disposable);
}
