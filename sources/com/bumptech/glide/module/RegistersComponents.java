package com.bumptech.glide.module;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;

@Deprecated
interface RegistersComponents {
    void registerComponents(Context context, Glide glide, Registry registry);
}
