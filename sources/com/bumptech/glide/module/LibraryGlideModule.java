package com.bumptech.glide.module;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;

public abstract class LibraryGlideModule implements RegistersComponents {
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    }
}
