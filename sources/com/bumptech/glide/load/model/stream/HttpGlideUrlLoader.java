package com.bumptech.glide.load.model.stream;

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
    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    public HttpGlideUrlLoader() {
        this((ModelCache<GlideUrl, GlideUrl>) null);
    }

    public HttpGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache2) {
        this.modelCache = modelCache2;
    }

    public ModelLoader.LoadData<InputStream> buildLoadData(GlideUrl model, int width, int height, Options options) {
        GlideUrl url = model;
        ModelCache<GlideUrl, GlideUrl> modelCache2 = this.modelCache;
        if (modelCache2 != null && (url = modelCache2.get(model, 0, 0)) == null) {
            this.modelCache.put(model, 0, 0, model);
            url = model;
        }
        return new ModelLoader.LoadData<>(url, new HttpUrlFetcher(url, ((Integer) options.get(TIMEOUT)).intValue()));
    }

    public boolean handles(GlideUrl model) {
        return true;
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<>(500);

        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new HttpGlideUrlLoader(this.modelCache);
        }

        public void teardown() {
        }
    }
}
