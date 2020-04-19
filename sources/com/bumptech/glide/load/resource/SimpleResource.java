package com.bumptech.glide.load.resource;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class SimpleResource<T> implements Resource<T> {
    protected final T data;

    public SimpleResource(@NonNull T data2) {
        this.data = Preconditions.checkNotNull(data2);
    }

    @NonNull
    public Class<T> getResourceClass() {
        return this.data.getClass();
    }

    @NonNull
    public final T get() {
        return this.data;
    }

    public final int getSize() {
        return 1;
    }

    public void recycle() {
    }
}
