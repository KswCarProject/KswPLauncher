package com.bumptech.glide.load.model.stream;

import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class BaseGlideUrlLoader<Model> implements ModelLoader<Model, InputStream> {
    private final ModelLoader<GlideUrl, InputStream> concreteLoader;
    private final ModelCache<Model, GlideUrl> modelCache;

    /* access modifiers changed from: protected */
    public abstract String getUrl(Model model, int i, int i2, Options options);

    protected BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader2) {
        this(concreteLoader2, (ModelCache) null);
    }

    protected BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader2, ModelCache<Model, GlideUrl> modelCache2) {
        this.concreteLoader = concreteLoader2;
        this.modelCache = modelCache2;
    }

    public ModelLoader.LoadData<InputStream> buildLoadData(Model model, int width, int height, Options options) {
        GlideUrl result = null;
        ModelCache<Model, GlideUrl> modelCache2 = this.modelCache;
        if (modelCache2 != null) {
            result = modelCache2.get(model, width, height);
        }
        if (result == null) {
            String stringURL = getUrl(model, width, height, options);
            if (TextUtils.isEmpty(stringURL)) {
                return null;
            }
            result = new GlideUrl(stringURL, getHeaders(model, width, height, options));
            ModelCache<Model, GlideUrl> modelCache3 = this.modelCache;
            if (modelCache3 != null) {
                modelCache3.put(model, width, height, result);
            }
        }
        List<String> alternateUrls = getAlternateUrls(model, width, height, options);
        ModelLoader.LoadData<InputStream> concreteLoaderData = this.concreteLoader.buildLoadData(result, width, height, options);
        if (concreteLoaderData == null || alternateUrls.isEmpty()) {
            return concreteLoaderData;
        }
        return new ModelLoader.LoadData<>(concreteLoaderData.sourceKey, getAlternateKeys(alternateUrls), concreteLoaderData.fetcher);
    }

    private static List<Key> getAlternateKeys(Collection<String> alternateUrls) {
        List<Key> result = new ArrayList<>(alternateUrls.size());
        for (String alternate : alternateUrls) {
            result.add(new GlideUrl(alternate));
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public List<String> getAlternateUrls(Model model, int width, int height, Options options) {
        return Collections.emptyList();
    }

    /* access modifiers changed from: protected */
    public Headers getHeaders(Model model, int width, int height, Options options) {
        return Headers.DEFAULT;
    }
}
