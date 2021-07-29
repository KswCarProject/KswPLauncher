package com.bumptech.glide.provider;

import com.bumptech.glide.load.ResourceEncoder;
import java.util.ArrayList;
import java.util.List;

public class ResourceEncoderRegistry {
    private final List<Entry<?>> encoders = new ArrayList();

    public synchronized <Z> void append(Class<Z> resourceClass, ResourceEncoder<Z> encoder) {
        this.encoders.add(new Entry(resourceClass, encoder));
    }

    public synchronized <Z> void prepend(Class<Z> resourceClass, ResourceEncoder<Z> encoder) {
        this.encoders.add(0, new Entry(resourceClass, encoder));
    }

    public synchronized <Z> ResourceEncoder<Z> get(Class<Z> resourceClass) {
        int size = this.encoders.size();
        for (int i = 0; i < size; i++) {
            Entry<?> entry = this.encoders.get(i);
            if (entry.handles(resourceClass)) {
                return entry.encoder;
            }
        }
        return null;
    }

    private static final class Entry<T> {
        final ResourceEncoder<T> encoder;
        private final Class<T> resourceClass;

        Entry(Class<T> resourceClass2, ResourceEncoder<T> encoder2) {
            this.resourceClass = resourceClass2;
            this.encoder = encoder2;
        }

        /* access modifiers changed from: package-private */
        public boolean handles(Class<?> resourceClass2) {
            return this.resourceClass.isAssignableFrom(resourceClass2);
        }
    }
}
