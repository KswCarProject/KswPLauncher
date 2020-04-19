package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Collection;

public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression, @NonNull String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T arg) {
        return checkNotNull(arg, "Argument must not be null");
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T arg, @NonNull String message) {
        if (arg != null) {
            return arg;
        }
        throw new NullPointerException(message);
    }

    @NonNull
    public static String checkNotEmpty(@Nullable String string) {
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }

    @NonNull
    public static <T extends Collection<Y>, Y> T checkNotEmpty(@NonNull T collection) {
        if (!collection.isEmpty()) {
            return collection;
        }
        throw new IllegalArgumentException("Must not be empty.");
    }
}
