package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataRewinderRegistry {
    private static final DataRewinder.Factory<?> DEFAULT_FACTORY = new DataRewinder.Factory<Object>() {
        @NonNull
        public DataRewinder<Object> build(@NonNull Object data) {
            return new DefaultRewinder(data);
        }

        @NonNull
        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, DataRewinder.Factory<?>> rewinders = new HashMap();

    public synchronized void register(@NonNull DataRewinder.Factory<?> factory) {
        this.rewinders.put(factory.getDataClass(), factory);
    }

    @NonNull
    public synchronized <T> DataRewinder<T> build(@NonNull T data) {
        DataRewinder.Factory<?> factory;
        Preconditions.checkNotNull(data);
        factory = this.rewinders.get(data.getClass());
        if (factory == null) {
            Iterator<DataRewinder.Factory<?>> it = this.rewinders.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DataRewinder.Factory<?> registeredFactory = it.next();
                if (registeredFactory.getDataClass().isAssignableFrom(data.getClass())) {
                    factory = registeredFactory;
                    break;
                }
            }
        }
        if (factory == null) {
            factory = DEFAULT_FACTORY;
        }
        return factory.build(data);
    }

    private static final class DefaultRewinder implements DataRewinder<Object> {
        private final Object data;

        DefaultRewinder(@NonNull Object data2) {
            this.data = data2;
        }

        @NonNull
        public Object rewindAndGet() {
            return this.data;
        }

        public void cleanup() {
        }
    }
}
