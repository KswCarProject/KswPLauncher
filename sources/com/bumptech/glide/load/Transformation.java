package com.bumptech.glide.load;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;

public interface Transformation<T> extends Key {
    @NonNull
    Resource<T> transform(@NonNull Context context, @NonNull Resource<T> resource, int i, int i2);
}
