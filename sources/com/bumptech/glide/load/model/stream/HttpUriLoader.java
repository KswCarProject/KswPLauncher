package com.bumptech.glide.load.model.stream;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HttpUriLoader implements ModelLoader<Uri, InputStream> {
    private static final Set<String> SCHEMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"http", "https"})));
    private final ModelLoader<GlideUrl, InputStream> urlLoader;

    public HttpUriLoader(ModelLoader<GlideUrl, InputStream> urlLoader2) {
        this.urlLoader = urlLoader2;
    }

    public ModelLoader.LoadData<InputStream> buildLoadData(@NonNull Uri model, int width, int height, @NonNull Options options) {
        return this.urlLoader.buildLoadData(new GlideUrl(model.toString()), width, height, options);
    }

    public boolean handles(@NonNull Uri model) {
        return SCHEMES.contains(model.getScheme());
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        @NonNull
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new HttpUriLoader(multiFactory.build(GlideUrl.class, InputStream.class));
        }

        public void teardown() {
        }
    }
}
