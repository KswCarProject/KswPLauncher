package io.reactivex.parallel;

/* loaded from: classes.dex */
public interface ParallelFlowableConverter<T, R> {
    R apply(ParallelFlowable<T> parallelFlowable);
}
