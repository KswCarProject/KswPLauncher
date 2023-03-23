package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Notification;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.internal.operators.mixed.MaterializeSingleObserver;

public final class CompletableMaterialize<T> extends Single<Notification<T>> {
    final Completable source;

    public CompletableMaterialize(Completable source2) {
        this.source = source2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Notification<T>> observer) {
        this.source.subscribe((CompletableObserver) new MaterializeSingleObserver(observer));
    }
}
