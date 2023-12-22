package android.databinding;

import android.databinding.ObservableMap;
import android.support.p001v4.util.ArrayMap;
import java.util.Collection;

/* loaded from: classes.dex */
public class ObservableArrayMap<K, V> extends ArrayMap<K, V> implements ObservableMap<K, V> {
    private transient MapChangeRegistry mListeners;

    @Override // android.databinding.ObservableMap
    public void addOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> listener) {
        if (this.mListeners == null) {
            this.mListeners = new MapChangeRegistry();
        }
        this.mListeners.add(listener);
    }

    @Override // android.databinding.ObservableMap
    public void removeOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> listener) {
        MapChangeRegistry mapChangeRegistry = this.mListeners;
        if (mapChangeRegistry != null) {
            mapChangeRegistry.remove(listener);
        }
    }

    @Override // android.support.p001v4.util.SimpleArrayMap, java.util.Map
    public void clear() {
        boolean wasEmpty = isEmpty();
        if (!wasEmpty) {
            super.clear();
            notifyChange(null);
        }
    }

    @Override // android.support.p001v4.util.SimpleArrayMap, java.util.Map
    public V put(K k, V v) {
        super.put(k, v);
        notifyChange(k);
        return v;
    }

    @Override // android.support.p001v4.util.ArrayMap
    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;
        for (Object key : collection) {
            int index = indexOfKey(key);
            if (index >= 0) {
                removed = true;
                removeAt(index);
            }
        }
        return removed;
    }

    @Override // android.support.p001v4.util.ArrayMap
    public boolean retainAll(Collection<?> collection) {
        boolean removed = false;
        for (int i = size() - 1; i >= 0; i--) {
            Object key = keyAt(i);
            if (!collection.contains(key)) {
                removeAt(i);
                removed = true;
            }
        }
        return removed;
    }

    @Override // android.support.p001v4.util.SimpleArrayMap
    public V removeAt(int index) {
        K key = keyAt(index);
        V value = (V) super.removeAt(index);
        if (value != null) {
            notifyChange(key);
        }
        return value;
    }

    @Override // android.support.p001v4.util.SimpleArrayMap
    public V setValueAt(int index, V value) {
        K key = keyAt(index);
        V oldValue = (V) super.setValueAt(index, value);
        notifyChange(key);
        return oldValue;
    }

    private void notifyChange(Object key) {
        MapChangeRegistry mapChangeRegistry = this.mListeners;
        if (mapChangeRegistry != null) {
            mapChangeRegistry.notifyCallbacks(this, 0, key);
        }
    }
}
