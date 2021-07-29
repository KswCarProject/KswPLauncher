package android.databinding.adapters;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListenerUtil {
    private static final SparseArray<WeakHashMap<View, WeakReference<?>>> sListeners = new SparseArray<>();

    public static <T> T trackListener(View view, T listener, int listenerResourceId) {
        WeakReference<T> oldValue;
        if (Build.VERSION.SDK_INT >= 14) {
            T oldValue2 = view.getTag(listenerResourceId);
            view.setTag(listenerResourceId, listener);
            return oldValue2;
        }
        T oldValue3 = sListeners;
        synchronized (oldValue3) {
            WeakHashMap<View, WeakReference<?>> listeners = oldValue3.get(listenerResourceId);
            if (listeners == null) {
                listeners = new WeakHashMap<>();
                oldValue3.put(listenerResourceId, listeners);
            }
            if (listener == null) {
                oldValue = listeners.remove(view);
            } else {
                oldValue = listeners.put(view, new WeakReference(listener));
            }
            if (oldValue == null) {
                return null;
            }
            T t = oldValue.get();
            return t;
        }
    }

    public static <T> T getListener(View view, int listenerResourceId) {
        if (Build.VERSION.SDK_INT >= 14) {
            return view.getTag(listenerResourceId);
        }
        SparseArray<WeakHashMap<View, WeakReference<?>>> sparseArray = sListeners;
        synchronized (sparseArray) {
            WeakHashMap<View, WeakReference<?>> listeners = sparseArray.get(listenerResourceId);
            if (listeners == null) {
                return null;
            }
            WeakReference<T> oldValue = listeners.get(view);
            if (oldValue == null) {
                return null;
            }
            T t = oldValue.get();
            return t;
        }
    }
}
