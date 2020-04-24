package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;

public interface ModelLoaderFactory<T, Y> {
    @NonNull
    ModelLoader<T, Y> build(@NonNull MultiModelLoaderFactory multiModelLoaderFactory);

    void teardown();
}
