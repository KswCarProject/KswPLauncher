package com.bumptech.glide.module;

import android.content.Context;
import com.bumptech.glide.GlideBuilder;

@Deprecated
interface AppliesOptions {
    void applyOptions(Context context, GlideBuilder glideBuilder);
}
