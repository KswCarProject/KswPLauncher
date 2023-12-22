package io.reactivex;

/* loaded from: classes.dex */
public interface SingleConverter<T, R> {
    R apply(Single<T> single);
}
