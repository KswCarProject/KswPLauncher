package com.bumptech.glide.load.model.stream;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;

public class HttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    public static final Option<Integer> TIMEOUT = Option.memory("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);
    @Nullable
    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    public HttpGlideUrlLoader() {
        this((ModelCache<GlideUrl, GlideUrl>) null);
    }

    public HttpGlideUrlLoader(@Nullable ModelCache<GlideUrl, GlideUrl> modelCache2) {
        this.modelCache = modelCache2;
    }

    public ModelLoader.LoadData<InputStream> buildLoadData(@NonNull GlideUrl model, int width, int height, @NonNull Options options) {
        GlideUrl url = model;
        if (this.modelCache != null && (url = this.modelCache.get(model, 0, 0)) == null) {
            this.modelCache.put(model, 0, 0, model);
            url = model;
        }
        return new ModelLoader.LoadData<>(url, new HttpUrlFetcher(url, ((Integer) options.get(TIMEOUT)).intValue()));
    }

    public boolean handles(@NonNull GlideUrl model) {
        return true;
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<>(500);

        @NonNull
        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new HttpGlideUrlLoader(this.modelCache);
        }

        public void teardown() {
        }
    }
}
