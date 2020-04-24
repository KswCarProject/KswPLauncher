package com.bumptech.glide.request;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

public interface RequestListener<R> {
    boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<R> target, boolean z);

    boolean onResourceReady(R r, Object obj, Target<R> target, DataSource dataSource, boolean z);
}
