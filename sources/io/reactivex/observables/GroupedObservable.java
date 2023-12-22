package io.reactivex.observables;

import io.reactivex.Observable;

/* loaded from: classes.dex */
public abstract class GroupedObservable<K, T> extends Observable<T> {
    final K key;

    protected GroupedObservable(K key) {
        this.key = key;
    }

    public K getKey() {
        return this.key;
    }
}
