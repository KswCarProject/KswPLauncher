package com.bumptech.glide.provider;

import android.support.v4.util.ArrayMap;
import com.bumptech.glide.util.MultiClassKey;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ModelToResourceClassCache {
    private final ArrayMap<MultiClassKey, List<Class<?>>> registeredResourceClassCache = new ArrayMap<>();
    private final AtomicReference<MultiClassKey> resourceClassKeyRef = new AtomicReference<>();

    public List<Class<?>> get(Class<?> modelClass, Class<?> resourceClass, Class<?> transcodeClass) {
        List<Class<?>> result;
        MultiClassKey key = this.resourceClassKeyRef.getAndSet((Object) null);
        if (key == null) {
            key = new MultiClassKey(modelClass, resourceClass, transcodeClass);
        } else {
            key.set(modelClass, resourceClass, transcodeClass);
        }
        synchronized (this.registeredResourceClassCache) {
            result = this.registeredResourceClassCache.get(key);
        }
        this.resourceClassKeyRef.set(key);
        return result;
    }

    public void put(Class<?> modelClass, Class<?> resourceClass, Class<?> transcodeClass, List<Class<?>> resourceClasses) {
        synchronized (this.registeredResourceClassCache) {
            this.registeredResourceClassCache.put(new MultiClassKey(modelClass, resourceClass, transcodeClass), resourceClasses);
        }
    }

    public void clear() {
        synchronized (this.registeredResourceClassCache) {
            this.registeredResourceClassCache.clear();
        }
    }
}
