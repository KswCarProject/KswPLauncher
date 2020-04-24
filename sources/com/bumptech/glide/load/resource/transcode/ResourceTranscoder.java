package com.bumptech.glide.load.resource.transcode;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;

public interface ResourceTranscoder<Z, R> {
    @Nullable
    Resource<R> transcode(@NonNull Resource<Z> resource, @NonNull Options options);
}
