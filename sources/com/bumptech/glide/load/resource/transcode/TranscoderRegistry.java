package com.bumptech.glide.load.resource.transcode;

import java.util.ArrayList;
import java.util.List;

public class TranscoderRegistry {
    private final List<Entry<?, ?>> transcoders = new ArrayList();

    public synchronized <Z, R> void register(Class<Z> decodedClass, Class<R> transcodedClass, ResourceTranscoder<Z, R> transcoder) {
        this.transcoders.add(new Entry(decodedClass, transcodedClass, transcoder));
    }

    public synchronized <Z, R> ResourceTranscoder<Z, R> get(Class<Z> resourceClass, Class<R> transcodedClass) {
        if (transcodedClass.isAssignableFrom(resourceClass)) {
            return UnitTranscoder.get();
        }
        for (Entry<?, ?> entry : this.transcoders) {
            if (entry.handles(resourceClass, transcodedClass)) {
                return entry.transcoder;
            }
        }
        throw new IllegalArgumentException("No transcoder registered to transcode from " + resourceClass + " to " + transcodedClass);
    }

    public synchronized <Z, R> List<Class<R>> getTranscodeClasses(Class<Z> resourceClass, Class<R> transcodeClass) {
        List<Class<R>> transcodeClasses = new ArrayList<>();
        if (transcodeClass.isAssignableFrom(resourceClass)) {
            transcodeClasses.add(transcodeClass);
            return transcodeClasses;
        }
        for (Entry<?, ?> entry : this.transcoders) {
            if (entry.handles(resourceClass, transcodeClass)) {
                transcodeClasses.add(transcodeClass);
            }
        }
        return transcodeClasses;
    }

    private static final class Entry<Z, R> {
        private final Class<Z> fromClass;
        private final Class<R> toClass;
        final ResourceTranscoder<Z, R> transcoder;

        Entry(Class<Z> fromClass2, Class<R> toClass2, ResourceTranscoder<Z, R> transcoder2) {
            this.fromClass = fromClass2;
            this.toClass = toClass2;
            this.transcoder = transcoder2;
        }

        public boolean handles(Class<?> fromClass2, Class<?> toClass2) {
            return this.fromClass.isAssignableFrom(fromClass2) && toClass2.isAssignableFrom(this.toClass);
        }
    }
}
