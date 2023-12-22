package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EncoderRegistry {
    private final List<Entry<?>> encoders = new ArrayList();

    public synchronized <T> Encoder<T> getEncoder(Class<T> dataClass) {
        for (Entry<?> entry : this.encoders) {
            if (entry.handles(dataClass)) {
                return (Encoder<T>) entry.encoder;
            }
        }
        return null;
    }

    public synchronized <T> void append(Class<T> dataClass, Encoder<T> encoder) {
        this.encoders.add(new Entry<>(dataClass, encoder));
    }

    public synchronized <T> void prepend(Class<T> dataClass, Encoder<T> encoder) {
        this.encoders.add(0, new Entry<>(dataClass, encoder));
    }

    /* loaded from: classes.dex */
    private static final class Entry<T> {
        private final Class<T> dataClass;
        final Encoder<T> encoder;

        Entry(Class<T> dataClass, Encoder<T> encoder) {
            this.dataClass = dataClass;
            this.encoder = encoder;
        }

        boolean handles(Class<?> dataClass) {
            return this.dataClass.isAssignableFrom(dataClass);
        }
    }
}
