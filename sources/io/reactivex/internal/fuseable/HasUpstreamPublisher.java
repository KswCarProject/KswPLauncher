package io.reactivex.internal.fuseable;

import org.reactivestreams.Publisher;

/* loaded from: classes.dex */
public interface HasUpstreamPublisher<T> {
    Publisher<T> source();
}
