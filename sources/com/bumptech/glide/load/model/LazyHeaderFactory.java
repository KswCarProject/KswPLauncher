package com.bumptech.glide.load.model;

import android.support.annotation.Nullable;

public interface LazyHeaderFactory {
    @Nullable
    String buildHeader();
}
