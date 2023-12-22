package io.reactivex;

/* loaded from: classes.dex */
public interface FlowableConverter<T, R> {
    R apply(Flowable<T> flowable);
}
