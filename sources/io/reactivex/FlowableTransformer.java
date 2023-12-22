package io.reactivex;

import org.reactivestreams.Publisher;

/* loaded from: classes.dex */
public interface FlowableTransformer<Upstream, Downstream> {
    Publisher<Downstream> apply(Flowable<Upstream> flowable);
}
