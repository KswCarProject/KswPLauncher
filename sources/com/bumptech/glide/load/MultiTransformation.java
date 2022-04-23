package com.bumptech.glide.load;

import android.content.Context;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;

public class MultiTransformation<T> implements Transformation<T> {
    private final Collection<? extends Transformation<T>> transformations;

    @SafeVarargs
    public MultiTransformation(Transformation<T>... transformations2) {
        if (transformations2.length != 0) {
            this.transformations = Arrays.asList(transformations2);
            return;
        }
        throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }

    public MultiTransformation(Collection<? extends Transformation<T>> transformationList) {
        if (!transformationList.isEmpty()) {
            this.transformations = transformationList;
            return;
        }
        throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }

    public Resource<T> transform(Context context, Resource<T> resource, int outWidth, int outHeight) {
        Resource<T> previous = resource;
        for (Transformation<T> transformation : this.transformations) {
            Resource<T> transformed = transformation.transform(context, previous, outWidth, outHeight);
            if (previous != null && !previous.equals(resource) && !previous.equals(transformed)) {
                previous.recycle();
            }
            previous = transformed;
        }
        return previous;
    }

    public boolean equals(Object o) {
        if (o instanceof MultiTransformation) {
            return this.transformations.equals(((MultiTransformation) o).transformations);
        }
        return false;
    }

    public int hashCode() {
        return this.transformations.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (Transformation<T> transformation : this.transformations) {
            transformation.updateDiskCacheKey(messageDigest);
        }
    }
}
