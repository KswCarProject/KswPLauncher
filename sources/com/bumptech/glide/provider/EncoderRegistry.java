package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import java.util.ArrayList;
import java.util.List;

public class EncoderRegistry {
    private final List<Entry<?>> encoders = new ArrayList();

    public synchronized <T> Encoder<T> getEncoder(Class<T> dataClass) {
        for (Entry<?> entry : this.encoders) {
            if (entry.handles(dataClass)) {
                return entry.encoder;
            }
        }
        return null;
    }

    public synchronized <T> void append(Class<T> dataClass, Encoder<T> encoder) {
        this.encoders.add(new Entry(dataClass, encoder));
    }

    public synchronized <T> void prepend(Class<T> dataClass, Encoder<T> encoder) {
        this.encoders.add(0, new Entry(dataClass, encoder));
    }

    private static final class Entry<T> {
        private final Class<T> dataClass;
        final Encoder<T> encoder;

        Entry(Class<T> dataClass2, Encoder<T> encoder2) {
            this.dataClass = dataClass2;
            this.encoder = encoder2;
        }

        /* access modifiers changed from: package-private */
        public boolean handles(Class<?> dataClass2) {
            return this.dataClass.isAssignableFrom(dataClass2);
        }
    }
}
