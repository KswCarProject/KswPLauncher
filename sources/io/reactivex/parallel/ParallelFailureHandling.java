package io.reactivex.parallel;

import io.reactivex.functions.BiFunction;

/* loaded from: classes.dex */
public enum ParallelFailureHandling implements BiFunction<Long, Throwable, ParallelFailureHandling> {
    STOP,
    ERROR,
    SKIP,
    RETRY;

    @Override // io.reactivex.functions.BiFunction
    public ParallelFailureHandling apply(Long t1, Throwable t2) {
        return this;
    }
}
