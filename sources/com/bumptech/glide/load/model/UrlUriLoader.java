package com.bumptech.glide.load.model;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UrlUriLoader<Data> implements ModelLoader<Uri, Data> {
    private static final Set<String> SCHEMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"http", "https"})));
    private final ModelLoader<GlideUrl, Data> urlLoader;

    public UrlUriLoader(ModelLoader<GlideUrl, Data> urlLoader2) {
        this.urlLoader = urlLoader2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(@NonNull Uri uri, int width, int height, @NonNull Options options) {
        return this.urlLoader.buildLoadData(new GlideUrl(uri.toString()), width, height, options);
    }

    public boolean handles(@NonNull Uri uri) {
        return SCHEMES.contains(uri.getScheme());
    }

    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream> {
        @NonNull
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new UrlUriLoader(multiFactory.build(GlideUrl.class, InputStream.class));
        }

        public void teardown() {
        }
    }
}
