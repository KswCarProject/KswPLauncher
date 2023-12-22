package io.reactivex;

import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public interface FlowableOperator<Downstream, Upstream> {
    Subscriber<? super Upstream> apply(Subscriber<? super Downstream> subscriber) throws Exception;
}
