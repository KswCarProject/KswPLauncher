package io.reactivex.observables;

import io.reactivex.Observable;

public abstract class GroupedObservable<K, T> extends Observable<T> {
    final K key;

    protected GroupedObservable(K key2) {
        this.key = key2;
    }

    public K getKey() {
        return this.key;
    }
}
