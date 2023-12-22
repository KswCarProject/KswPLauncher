package org.reactivestreams;

/* loaded from: classes.dex */
public interface Subscription {
    void cancel();

    void request(long j);
}
