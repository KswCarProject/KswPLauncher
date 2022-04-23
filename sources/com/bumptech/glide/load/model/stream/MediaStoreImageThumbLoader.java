package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.data.mediastore.ThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class MediaStoreImageThumbLoader implements ModelLoader<Uri, InputStream> {
    private final Context context;

    public MediaStoreImageThumbLoader(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public ModelLoader.LoadData<InputStream> buildLoadData(Uri model, int width, int height, Options options) {
        if (MediaStoreUtil.isThumbnailSize(width, height)) {
            return new ModelLoader.LoadData<>(new ObjectKey(model), ThumbFetcher.buildImageFetcher(this.context, model));
        }
        return null;
    }

    public boolean handles(Uri model) {
        return MediaStoreUtil.isMediaStoreImageUri(model);
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        private final Context context;

        public Factory(Context context2) {
            this.context = context2;
        }

        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new MediaStoreImageThumbLoader(this.context);
        }

        public void teardown() {
        }
    }
}
