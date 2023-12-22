package io.reactivex.internal.fuseable;

import io.reactivex.ObservableSource;

/* loaded from: classes.dex */
public interface HasUpstreamObservableSource<T> {
    ObservableSource<T> source();
}
