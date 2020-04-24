package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MultiClassKey {
    private Class<?> first;
    private Class<?> second;
    private Class<?> third;

    public MultiClassKey() {
    }

    public MultiClassKey(@NonNull Class<?> first2, @NonNull Class<?> second2) {
        set(first2, second2);
    }

    public MultiClassKey(@NonNull Class<?> first2, @NonNull Class<?> second2, @Nullable Class<?> third2) {
        set(first2, second2, third2);
    }

    public void set(@NonNull Class<?> first2, @NonNull Class<?> second2) {
        set(first2, second2, (Class<?>) null);
    }

    public void set(@NonNull Class<?> first2, @NonNull Class<?> second2, @Nullable Class<?> third2) {
        this.first = first2;
        this.second = second2;
        this.third = third2;
    }

    public String toString() {
        return "MultiClassKey{first=" + this.first + ", second=" + this.second + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MultiClassKey that = (MultiClassKey) o;
        if (this.first.equals(that.first) && this.second.equals(that.second) && Util.bothNullOrEqual(this.third, that.third)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.first.hashCode() * 31) + this.second.hashCode()) * 31) + (this.third != null ? this.third.hashCode() : 0);
    }
}
