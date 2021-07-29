package com.bumptech.glide.load.data;

import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataRewinderRegistry {
    private static final DataRewinder.Factory<?> DEFAULT_FACTORY = new DataRewinder.Factory<Object>() {
        public DataRewinder<Object> build(Object data) {
            return new DefaultRewinder(data);
        }

        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, DataRewinder.Factory<?>> rewinders = new HashMap();

    public synchronized void register(DataRewinder.Factory<?> factory) {
        this.rewinders.put(factory.getDataClass(), factory);
    }

    public synchronized <T> DataRewinder<T> build(T data) {
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

        DefaultRewinder(Object data2) {
            this.data = data2;
        }

        public Object rewindAndGet() {
            return this.data;
        }

        public void cleanup() {
        }
    }
}
