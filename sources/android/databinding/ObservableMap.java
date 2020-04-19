package android.databinding;

import java.util.Map;

public interface ObservableMap<K, V> extends Map<K, V> {

    public static abstract class OnMapChangedCallback<T extends ObservableMap<K, V>, K, V> {
        public abstract void onMapChanged(T t, K k);
    }

    void addOnMapChangedCallback(OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> onMapChangedCallback);

    void removeOnMapChangedCallback(OnMapChangedCallback<? extends ObservableMap<K, V>, K, V> onMapChangedCallback);
}
