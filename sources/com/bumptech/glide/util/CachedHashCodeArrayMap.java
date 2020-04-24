package com.bumptech.glide.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;

public final class CachedHashCodeArrayMap<K, V> extends ArrayMap<K, V> {
    private int hashCode;

    public void clear() {
        this.hashCode = 0;
        super.clear();
    }

    public V setValueAt(int index, V value) {
        this.hashCode = 0;
        return super.setValueAt(index, value);
    }

    public V put(K key, V value) {
        this.hashCode = 0;
        return super.put(key, value);
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this.hashCode = 0;
        super.putAll(simpleArrayMap);
    }

    public V removeAt(int index) {
        this.hashCode = 0;
        return super.removeAt(index);
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = super.hashCode();
        }
        return this.hashCode;
    }
}
