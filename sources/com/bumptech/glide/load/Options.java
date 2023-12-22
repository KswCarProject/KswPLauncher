package com.bumptech.glide.load;

import android.support.p001v4.util.ArrayMap;
import android.support.p001v4.util.SimpleArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> values = new CachedHashCodeArrayMap();

    public void putAll(Options other) {
        this.values.putAll((SimpleArrayMap<? extends Option<?>, ? extends Object>) other.values);
    }

    public <T> Options set(Option<T> option, T value) {
        this.values.put(option, value);
        return this;
    }

    public <T> T get(Option<T> option) {
        return this.values.containsKey(option) ? (T) this.values.get(option) : option.getDefaultValue();
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object o) {
        if (o instanceof Options) {
            Options other = (Options) o;
            return this.values.equals(other.values);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.values.hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (int i = 0; i < this.values.size(); i++) {
            Option<?> key = this.values.keyAt(i);
            Object value = this.values.valueAt(i);
            updateDiskCacheKey(key, value, messageDigest);
        }
    }

    public String toString() {
        return "Options{values=" + this.values + '}';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void updateDiskCacheKey(Option<T> option, Object value, MessageDigest md) {
        option.update(value, md);
    }
}
