package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;

public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> values = new CachedHashCodeArrayMap();

    public void putAll(@NonNull Options other) {
        this.values.putAll(other.values);
    }

    @NonNull
    public <T> Options set(@NonNull Option<T> option, @NonNull T value) {
        this.values.put(option, value);
        return this;
    }

    @Nullable
    public <T> T get(@NonNull Option<T> option) {
        return this.values.containsKey(option) ? this.values.get(option) : option.getDefaultValue();
    }

    public boolean equals(Object o) {
        if (o instanceof Options) {
            return this.values.equals(((Options) o).values);
        }
        return false;
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        for (int i = 0; i < this.values.size(); i++) {
            updateDiskCacheKey(this.values.keyAt(i), this.values.valueAt(i), messageDigest);
        }
    }

    public String toString() {
        return "Options{values=" + this.values + '}';
    }

    private static <T> void updateDiskCacheKey(@NonNull Option<T> option, @NonNull Object value, @NonNull MessageDigest md) {
        option.update(value, md);
    }
}
