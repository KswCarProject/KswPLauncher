package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

public class UnitModelLoader<Model> implements ModelLoader<Model, Model> {
    private static final UnitModelLoader<?> INSTANCE = new UnitModelLoader<>();

    public static <T> UnitModelLoader<T> getInstance() {
        return INSTANCE;
    }

    public ModelLoader.LoadData<Model> buildLoadData(@NonNull Model model, int width, int height, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new UnitFetcher(model));
    }

    public boolean handles(@NonNull Model model) {
        return true;
    }

    private static class UnitFetcher<Model> implements DataFetcher<Model> {
        private final Model resource;

        UnitFetcher(Model resource2) {
            this.resource = resource2;
        }

        public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Model> callback) {
            callback.onDataReady(this.resource);
        }

        public void cleanup() {
        }

        public void cancel() {
        }

        @NonNull
        public Class<Model> getDataClass() {
            return this.resource.getClass();
        }

        @NonNull
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    public static class Factory<Model> implements ModelLoaderFactory<Model, Model> {
        private static final Factory<?> FACTORY = new Factory<>();

        public static <T> Factory<T> getInstance() {
            return FACTORY;
        }

        @NonNull
        public ModelLoader<Model, Model> build(MultiModelLoaderFactory multiFactory) {
            return UnitModelLoader.getInstance();
        }

        public void teardown() {
        }
    }
}
