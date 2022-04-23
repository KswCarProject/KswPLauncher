package com.bumptech.glide.load;

import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public final class Option<T> {
    private static final CacheKeyUpdater<Object> EMPTY_UPDATER = new CacheKeyUpdater<Object>() {
        public void update(byte[] keyBytes, Object value, MessageDigest messageDigest) {
        }
    };
    private final CacheKeyUpdater<T> cacheKeyUpdater;
    private final T defaultValue;
    private final String key;
    private volatile byte[] keyBytes;

    public interface CacheKeyUpdater<T> {
        void update(byte[] bArr, T t, MessageDigest messageDigest);
    }

    public static <T> Option<T> memory(String key2) {
        return new Option<>(key2, (Object) null, emptyUpdater());
    }

    public static <T> Option<T> memory(String key2, T defaultValue2) {
        return new Option<>(key2, defaultValue2, emptyUpdater());
    }

    public static <T> Option<T> disk(String key2, CacheKeyUpdater<T> cacheKeyUpdater2) {
        return new Option<>(key2, (Object) null, cacheKeyUpdater2);
    }

    public static <T> Option<T> disk(String key2, T defaultValue2, CacheKeyUpdater<T> cacheKeyUpdater2) {
        return new Option<>(key2, defaultValue2, cacheKeyUpdater2);
    }

    private Option(String key2, T defaultValue2, CacheKeyUpdater<T> cacheKeyUpdater2) {
        this.key = Preconditions.checkNotEmpty(key2);
        this.defaultValue = defaultValue2;
        this.cacheKeyUpdater = (CacheKeyUpdater) Preconditions.checkNotNull(cacheKeyUpdater2);
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void update(T value, MessageDigest messageDigest) {
        this.cacheKeyUpdater.update(getKeyBytes(), value, messageDigest);
    }

    private byte[] getKeyBytes() {
        if (this.keyBytes == null) {
            this.keyBytes = this.key.getBytes(Key.CHARSET);
        }
        return this.keyBytes;
    }

    public boolean equals(Object o) {
        if (o instanceof Option) {
            return this.key.equals(((Option) o).key);
        }
        return false;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    private static <T> CacheKeyUpdater<T> emptyUpdater() {
        return EMPTY_UPDATER;
    }

    public String toString() {
        return "Option{key='" + this.key + '\'' + '}';
    }
}
