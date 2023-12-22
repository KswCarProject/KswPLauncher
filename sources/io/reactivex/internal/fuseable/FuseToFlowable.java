package io.reactivex.internal.fuseable;

import io.reactivex.Flowable;

/* loaded from: classes.dex */
public interface FuseToFlowable<T> {
    Flowable<T> fuseToFlowable();
}
