package io.reactivex.internal.fuseable;

import io.reactivex.SingleSource;

/* loaded from: classes.dex */
public interface HasUpstreamSingleSource<T> {
    SingleSource<T> source();
}
