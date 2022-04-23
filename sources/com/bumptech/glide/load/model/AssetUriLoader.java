package com.bumptech.glide.load.model;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class AssetUriLoader<Data> implements ModelLoader<Uri, Data> {
    private static final String ASSET_PATH_SEGMENT = "android_asset";
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final int ASSET_PREFIX_LENGTH = ASSET_PREFIX.length();
    private final AssetManager assetManager;
    private final AssetFetcherFactory<Data> factory;

    public interface AssetFetcherFactory<Data> {
        DataFetcher<Data> buildFetcher(AssetManager assetManager, String str);
    }

    public AssetUriLoader(AssetManager assetManager2, AssetFetcherFactory<Data> factory2) {
        this.assetManager = assetManager2;
        this.factory = factory2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(Uri model, int width, int height, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), this.factory.buildFetcher(this.assetManager, model.toString().substring(ASSET_PREFIX_LENGTH)));
    }

    public boolean handles(Uri model) {
        if (!"file".equals(model.getScheme()) || model.getPathSegments().isEmpty() || !ASSET_PATH_SEGMENT.equals(model.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }

    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream>, AssetFetcherFactory<InputStream> {
        private final AssetManager assetManager;

        public StreamFactory(AssetManager assetManager2) {
            this.assetManager = assetManager2;
        }

        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new AssetUriLoader(this.assetManager, this);
        }

        public void teardown() {
        }

        public DataFetcher<InputStream> buildFetcher(AssetManager assetManager2, String assetPath) {
            return new StreamAssetPathFetcher(assetManager2, assetPath);
        }
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<Uri, ParcelFileDescriptor>, AssetFetcherFactory<ParcelFileDescriptor> {
        private final AssetManager assetManager;

        public FileDescriptorFactory(AssetManager assetManager2) {
            this.assetManager = assetManager2;
        }

        public ModelLoader<Uri, ParcelFileDescriptor> build(MultiModelLoaderFactory multiFactory) {
            return new AssetUriLoader(this.assetManager, this);
        }

        public void teardown() {
        }

        public DataFetcher<ParcelFileDescriptor> buildFetcher(AssetManager assetManager2, String assetPath) {
            return new FileDescriptorAssetPathFetcher(assetManager2, assetPath);
        }
    }
}
