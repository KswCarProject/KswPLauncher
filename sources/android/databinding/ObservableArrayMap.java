package android.databinding;

import android.databinding.ObservableMap;
import android.support.v4.util.ArrayMap;
import java.util.Collection;

public class ObservableArrayMap<K, V> extends ArrayMap<K, V> implements ObservableMap<K, V> {
    private transient MapChangeRegistry mListeners;

    public void addOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> listener) {
        if (this.mListeners == null) {
            this.mListeners = new MapChangeRegistry();
        }
        this.mListeners.add(listener);
    }

    public void removeOnMapChangedCallback(ObservableMap.OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> listener) {
        if (this.mListeners != null) {
            this.mListeners.remove(listener);
        }
    }

    public void clear() {
        if (!isEmpty()) {
            super.clear();
            notifyChange((Object) null);
        }
    }

    public V put(K k, V v) {
        Object put = super.put(k, v);
        notifyChange(k);
        return v;
    }

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

    public boolean retainAll(Collection<?> collection) {
        boolean removed = false;
        for (int i = size() - 1; i >= 0; i--) {
            if (!collection.contains(keyAt(i))) {
                removeAt(i);
                removed = true;
            }
        }
        return removed;
    }

    public V removeAt(int index) {
        K key = keyAt(index);
        V value = super.removeAt(index);
        if (value != null) {
            notifyChange(key);
        }
        return value;
    }

    public V setValueAt(int index, V value) {
        K key = keyAt(index);
        V oldValue = super.setValueAt(index, value);
        notifyChange(key);
        return oldValue;
    }

    private void notifyChange(Object key) {
        if (this.mListeners != null) {
            this.mListeners.notifyCallbacks(this, 0, key);
        }
    }
}
