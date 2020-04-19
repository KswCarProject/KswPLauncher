package com.bumptech.glide.module;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;

public abstract class AppGlideModule extends LibraryGlideModule implements AppliesOptions {
    public boolean isManifestParsingEnabled() {
        return true;
    }

    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    }
}
