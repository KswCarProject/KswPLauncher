package com.bumptech.glide.load;

import android.support.v4.util.ArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;

public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> values = new CachedHashCodeArrayMap();

    public void putAll(Options other) {
        this.values.putAll(other.values);
    }

    public <T> Options set(Option<T> option, T value) {
        this.values.put(option, value);
        return this;
    }

    public <T> T get(Option<T> option) {
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

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (int i = 0; i < this.values.size(); i++) {
            updateDiskCacheKey(this.values.keyAt(i), this.values.valueAt(i), messageDigest);
        }
    }

    public String toString() {
        return "Options{values=" + this.values + '}';
    }

    private static <T> void updateDiskCacheKey(Option<T> option, Object value, MessageDigest md) {
        option.update(value, md);
    }
}
