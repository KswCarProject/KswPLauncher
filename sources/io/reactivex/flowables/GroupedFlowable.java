package io.reactivex.flowables;

import io.reactivex.Flowable;

public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;

    protected GroupedFlowable(K key2) {
        this.key = key2;
    }

    public K getKey() {
        return this.key;
    }
}
