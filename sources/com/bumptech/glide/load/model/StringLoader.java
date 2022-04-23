package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.io.InputStream;

public class StringLoader<Data> implements ModelLoader<String, Data> {
    private final ModelLoader<Uri, Data> uriLoader;

    public StringLoader(ModelLoader<Uri, Data> uriLoader2) {
        this.uriLoader = uriLoader2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(String model, int width, int height, Options options) {
        Uri uri = parseUri(model);
        if (uri == null || !this.uriLoader.handles(uri)) {
            return null;
        }
        return this.uriLoader.buildLoadData(uri, width, height, options);
    }

    public boolean handles(String model) {
        return true;
    }

    private static Uri parseUri(String model) {
        if (TextUtils.isEmpty(model)) {
            return null;
        }
        if (model.charAt(0) == '/') {
            return toFileUri(model);
        }
        Uri uri = Uri.parse(model);
        if (uri.getScheme() == null) {
            return toFileUri(model);
        }
        return uri;
    }

    private static Uri toFileUri(String path) {
        return Uri.fromFile(new File(path));
    }

    public static class StreamFactory implements ModelLoaderFactory<String, InputStream> {
        public ModelLoader<String, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new StringLoader(multiFactory.build(Uri.class, InputStream.class));
        }

        public void teardown() {
        }
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<String, ParcelFileDescriptor> {
        public ModelLoader<String, ParcelFileDescriptor> build(MultiModelLoaderFactory multiFactory) {
            return new StringLoader(multiFactory.build(Uri.class, ParcelFileDescriptor.class));
        }

        public void teardown() {
        }
    }

    public static final class AssetFileDescriptorFactory implements ModelLoaderFactory<String, AssetFileDescriptor> {
        public ModelLoader<String, AssetFileDescriptor> build(MultiModelLoaderFactory multiFactory) {
            return new StringLoader(multiFactory.build(Uri.class, AssetFileDescriptor.class));
        }

        public void teardown() {
        }
    }
}
