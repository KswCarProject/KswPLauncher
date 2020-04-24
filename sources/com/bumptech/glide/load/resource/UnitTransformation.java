package com.bumptech.glide.load.resource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;

public final class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> TRANSFORMATION = new UnitTransformation();

    @NonNull
    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation) TRANSFORMATION;
    }

    private UnitTransformation() {
    }

    @NonNull
    public Resource<T> transform(@NonNull Context context, @NonNull Resource<T> resource, int outWidth, int outHeight) {
        return resource;
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }
}
