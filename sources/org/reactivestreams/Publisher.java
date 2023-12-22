package org.reactivestreams;

/* loaded from: classes.dex */
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
