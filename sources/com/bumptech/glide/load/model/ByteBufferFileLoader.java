package com.bumptech.glide.load.model;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ByteBufferFileLoader implements ModelLoader<File, ByteBuffer> {
    private static final String TAG = "ByteBufferFileLoader";

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<ByteBuffer> buildLoadData(File file, int width, int height, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new ByteBufferFetcher(file));
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(File file) {
        return true;
    }

    /* loaded from: classes.dex */
    public static class Factory implements ModelLoaderFactory<File, ByteBuffer> {
        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public ModelLoader<File, ByteBuffer> build(MultiModelLoaderFactory multiFactory) {
            return new ByteBufferFileLoader();
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }
    }

    /* loaded from: classes.dex */
    private static final class ByteBufferFetcher implements DataFetcher<ByteBuffer> {
        private final File file;

        ByteBufferFetcher(File file) {
            this.file = file;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super ByteBuffer> callback) {
            try {
                ByteBuffer result = ByteBufferUtil.fromFile(this.file);
                callback.onDataReady(result);
            } catch (IOException e) {
                if (Log.isLoggable(ByteBufferFileLoader.TAG, 3)) {
                    Log.d(ByteBufferFileLoader.TAG, "Failed to obtain ByteBuffer for file", e);
                }
                callback.onLoadFailed(e);
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }
}
