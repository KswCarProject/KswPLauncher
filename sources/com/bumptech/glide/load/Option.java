package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public final class Option<T> {
    private static final CacheKeyUpdater<Object> EMPTY_UPDATER = new CacheKeyUpdater<Object>() {
        public void update(@NonNull byte[] keyBytes, @NonNull Object value, @NonNull MessageDigest messageDigest) {
        }
    };
    private final CacheKeyUpdater<T> cacheKeyUpdater;
    private final T defaultValue;
    private final String key;
    private volatile byte[] keyBytes;

    public interface CacheKeyUpdater<T> {
        void update(@NonNull byte[] bArr, @NonNull T t, @NonNull MessageDigest messageDigest);
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String key2) {
        return new Option<>(key2, (Object) null, emptyUpdater());
    }

    @NonNull
    public static <T> Option<T> memory(@NonNull String key2, @NonNull T defaultValue2) {
        return new Option<>(key2, defaultValue2, emptyUpdater());
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String key2, @NonNull CacheKeyUpdater<T> cacheKeyUpdater2) {
        return new Option<>(key2, (Object) null, cacheKeyUpdater2);
    }

    @NonNull
    public static <T> Option<T> disk(@NonNull String key2, @Nullable T defaultValue2, @NonNull CacheKeyUpdater<T> cacheKeyUpdater2) {
        return new Option<>(key2, defaultValue2, cacheKeyUpdater2);
    }

    private Option(@NonNull String key2, @Nullable T defaultValue2, @NonNull CacheKeyUpdater<T> cacheKeyUpdater2) {
        this.key = Preconditions.checkNotEmpty(key2);
        this.defaultValue = defaultValue2;
        this.cacheKeyUpdater = (CacheKeyUpdater) Preconditions.checkNotNull(cacheKeyUpdater2);
    }

    @Nullable
    public T getDefaultValue() {
        return this.defaultValue;
    }

    public void update(@NonNull T value, @NonNull MessageDigest messageDigest) {
        this.cacheKeyUpdater.update(getKeyBytes(), value, messageDigest);
    }

    @NonNull
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

    @NonNull
    private static <T> CacheKeyUpdater<T> emptyUpdater() {
        return EMPTY_UPDATER;
    }

    public String toString() {
        return "Option{key='" + this.key + '\'' + '}';
    }
}
