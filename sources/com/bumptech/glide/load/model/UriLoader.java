package com.bumptech.glide.load.model;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.AssetFileDescriptorLocalUriFetcher;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorLocalUriFetcher;
import com.bumptech.glide.load.data.StreamLocalUriFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UriLoader<Data> implements ModelLoader<Uri, Data> {
    private static final Set<String> SCHEMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"file", "android.resource", "content"})));
    private final LocalUriFetcherFactory<Data> factory;

    public interface LocalUriFetcherFactory<Data> {
        DataFetcher<Data> build(Uri uri);
    }

    public UriLoader(LocalUriFetcherFactory<Data> factory2) {
        this.factory = factory2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(@NonNull Uri model, int width, int height, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), this.factory.build(model));
    }

    public boolean handles(@NonNull Uri model) {
        return SCHEMES.contains(model.getScheme());
    }

    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream>, LocalUriFetcherFactory<InputStream> {
        private final ContentResolver contentResolver;

        public StreamFactory(ContentResolver contentResolver2) {
            this.contentResolver = contentResolver2;
        }

        public DataFetcher<InputStream> build(Uri uri) {
            return new StreamLocalUriFetcher(this.contentResolver, uri);
        }

        @NonNull
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new UriLoader(this);
        }

        public void teardown() {
        }
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<Uri, ParcelFileDescriptor>, LocalUriFetcherFactory<ParcelFileDescriptor> {
        private final ContentResolver contentResolver;

        public FileDescriptorFactory(ContentResolver contentResolver2) {
            this.contentResolver = contentResolver2;
        }

        public DataFetcher<ParcelFileDescriptor> build(Uri uri) {
            return new FileDescriptorLocalUriFetcher(this.contentResolver, uri);
        }

        @NonNull
        public ModelLoader<Uri, ParcelFileDescriptor> build(MultiModelLoaderFactory multiFactory) {
            return new UriLoader(this);
        }

        public void teardown() {
        }
    }

    public static final class AssetFileDescriptorFactory implements ModelLoaderFactory<Uri, AssetFileDescriptor>, LocalUriFetcherFactory<AssetFileDescriptor> {
        private final ContentResolver contentResolver;

        public AssetFileDescriptorFactory(ContentResolver contentResolver2) {
            this.contentResolver = contentResolver2;
        }

        public ModelLoader<Uri, AssetFileDescriptor> build(MultiModelLoaderFactory multiFactory) {
            return new UriLoader(this);
        }

        public void teardown() {
        }

        public DataFetcher<AssetFileDescriptor> build(Uri uri) {
            return new AssetFileDescriptorLocalUriFetcher(this.contentResolver, uri);
        }
    }
}
