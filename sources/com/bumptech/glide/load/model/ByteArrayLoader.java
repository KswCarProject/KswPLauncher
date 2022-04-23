package com.bumptech.glide.load.model;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteArrayLoader<Data> implements ModelLoader<byte[], Data> {
    private final Converter<Data> converter;

    public interface Converter<Data> {
        Data convert(byte[] bArr);

        Class<Data> getDataClass();
    }

    public ByteArrayLoader(Converter<Data> converter2) {
        this.converter = converter2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(byte[] model, int width, int height, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new Fetcher(model, this.converter));
    }

    public boolean handles(byte[] model) {
        return true;
    }

    private static class Fetcher<Data> implements DataFetcher<Data> {
        private final Converter<Data> converter;
        private final byte[] model;

        Fetcher(byte[] model2, Converter<Data> converter2) {
            this.model = model2;
            this.converter = converter2;
        }

        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> callback) {
            callback.onDataReady(this.converter.convert(this.model));
        }

        public void cleanup() {
        }

        public void cancel() {
        }

        public Class<Data> getDataClass() {
            return this.converter.getDataClass();
        }

        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static class ByteBufferFactory implements ModelLoaderFactory<byte[], ByteBuffer> {
        public ModelLoader<byte[], ByteBuffer> build(MultiModelLoaderFactory multiFactory) {
            return new ByteArrayLoader(new Converter<ByteBuffer>() {
                public ByteBuffer convert(byte[] model) {
                    return ByteBuffer.wrap(model);
                }

                public Class<ByteBuffer> getDataClass() {
                    return ByteBuffer.class;
                }
            });
        }

        public void teardown() {
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<byte[], InputStream> {
        public ModelLoader<byte[], InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new ByteArrayLoader(new Converter<InputStream>() {
                public InputStream convert(byte[] model) {
                    return new ByteArrayInputStream(model);
                }

                public Class<InputStream> getDataClass() {
                    return InputStream.class;
                }
            });
        }

        public void teardown() {
        }
    }
}
