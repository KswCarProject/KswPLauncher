package io.reactivex;

/* loaded from: classes.dex */
public interface MaybeConverter<T, R> {
    R apply(Maybe<T> maybe);
}
