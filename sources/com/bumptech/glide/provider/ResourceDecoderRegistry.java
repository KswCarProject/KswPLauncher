package com.bumptech.glide.provider;

import com.bumptech.glide.load.ResourceDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDecoderRegistry {
    private final List<String> bucketPriorityList = new ArrayList();
    private final Map<String, List<Entry<?, ?>>> decoders = new HashMap();

    public synchronized void setBucketPriorityList(List<String> buckets) {
        List<String> previousBuckets = new ArrayList<>(this.bucketPriorityList);
        this.bucketPriorityList.clear();
        this.bucketPriorityList.addAll(buckets);
        for (String previousBucket : previousBuckets) {
            if (!buckets.contains(previousBucket)) {
                this.bucketPriorityList.add(previousBucket);
            }
        }
    }

    public synchronized <T, R> List<ResourceDecoder<T, R>> getDecoders(Class<T> dataClass, Class<R> resourceClass) {
        List<ResourceDecoder<T, R>> result;
        result = new ArrayList<>();
        for (String bucket : this.bucketPriorityList) {
            List<Entry<?, ?>> entries = this.decoders.get(bucket);
            if (entries != null) {
                for (Entry<?, ?> entry : entries) {
                    if (entry.handles(dataClass, resourceClass)) {
                        result.add(entry.decoder);
                    }
                }
            }
        }
        return result;
    }

    public synchronized <T, R> List<Class<R>> getResourceClasses(Class<T> dataClass, Class<R> resourceClass) {
        List<Class<R>> result;
        result = new ArrayList<>();
        for (String bucket : this.bucketPriorityList) {
            List<Entry<?, ?>> entries = this.decoders.get(bucket);
            if (entries != null) {
                for (Entry<?, ?> entry : entries) {
                    if (entry.handles(dataClass, resourceClass) && !result.contains(entry.resourceClass)) {
                        result.add(entry.resourceClass);
                    }
                }
            }
        }
        return result;
    }

    public synchronized <T, R> void append(String bucket, ResourceDecoder<T, R> decoder, Class<T> dataClass, Class<R> resourceClass) {
        getOrAddEntryList(bucket).add(new Entry(dataClass, resourceClass, decoder));
    }

    public synchronized <T, R> void prepend(String bucket, ResourceDecoder<T, R> decoder, Class<T> dataClass, Class<R> resourceClass) {
        getOrAddEntryList(bucket).add(0, new Entry(dataClass, resourceClass, decoder));
    }

    private synchronized List<Entry<?, ?>> getOrAddEntryList(String bucket) {
        List<Entry<?, ?>> entries;
        if (!this.bucketPriorityList.contains(bucket)) {
            this.bucketPriorityList.add(bucket);
        }
        entries = this.decoders.get(bucket);
        if (entries == null) {
            entries = new ArrayList<>();
            this.decoders.put(bucket, entries);
        }
        return entries;
    }

    private static class Entry<T, R> {
        private final Class<T> dataClass;
        final ResourceDecoder<T, R> decoder;
        final Class<R> resourceClass;

        public Entry(Class<T> dataClass2, Class<R> resourceClass2, ResourceDecoder<T, R> decoder2) {
            this.dataClass = dataClass2;
            this.resourceClass = resourceClass2;
            this.decoder = decoder2;
        }

        public boolean handles(Class<?> dataClass2, Class<?> resourceClass2) {
            return this.dataClass.isAssignableFrom(dataClass2) && resourceClass2.isAssignableFrom(this.resourceClass);
        }
    }
}
