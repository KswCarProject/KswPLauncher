package com.bumptech.glide.load.model;

import android.support.v4.util.Pools;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MultiModelLoader<Model, Data> implements ModelLoader<Model, Data> {
    private final Pools.Pool<List<Throwable>> exceptionListPool;
    private final List<ModelLoader<Model, Data>> modelLoaders;

    MultiModelLoader(List<ModelLoader<Model, Data>> modelLoaders2, Pools.Pool<List<Throwable>> exceptionListPool2) {
        this.modelLoaders = modelLoaders2;
        this.exceptionListPool = exceptionListPool2;
    }

    public ModelLoader.LoadData<Data> buildLoadData(Model model, int width, int height, Options options) {
        ModelLoader.LoadData<Data> loadData;
        Key sourceKey = null;
        int size = this.modelLoaders.size();
        List<DataFetcher<Data>> fetchers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ModelLoader<Model, Data> modelLoader = this.modelLoaders.get(i);
            if (modelLoader.handles(model) && (loadData = modelLoader.buildLoadData(model, width, height, options)) != null) {
                sourceKey = loadData.sourceKey;
                fetchers.add(loadData.fetcher);
            }
        }
        if (fetchers.isEmpty() != 0 || sourceKey == null) {
            return null;
        }
        return new ModelLoader.LoadData<>(sourceKey, new MultiFetcher(fetchers, this.exceptionListPool));
    }

    public boolean handles(Model model) {
        for (ModelLoader<Model, Data> modelLoader : this.modelLoaders) {
            if (modelLoader.handles(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.modelLoaders.toArray()) + '}';
    }

    static class MultiFetcher<Data> implements DataFetcher<Data>, DataFetcher.DataCallback<Data> {
        private DataFetcher.DataCallback<? super Data> callback;
        private int currentIndex = 0;
        private List<Throwable> exceptions;
        private final List<DataFetcher<Data>> fetchers;
        private boolean isCancelled;
        private Priority priority;
        private final Pools.Pool<List<Throwable>> throwableListPool;

        MultiFetcher(List<DataFetcher<Data>> fetchers2, Pools.Pool<List<Throwable>> throwableListPool2) {
            this.throwableListPool = throwableListPool2;
            Preconditions.checkNotEmpty(fetchers2);
            this.fetchers = fetchers2;
        }

        public void loadData(Priority priority2, DataFetcher.DataCallback<? super Data> callback2) {
            this.priority = priority2;
            this.callback = callback2;
            this.exceptions = this.throwableListPool.acquire();
            this.fetchers.get(this.currentIndex).loadData(priority2, this);
            if (this.isCancelled) {
                cancel();
            }
        }

        public void cleanup() {
            List<Throwable> list = this.exceptions;
            if (list != null) {
                this.throwableListPool.release(list);
            }
            this.exceptions = null;
            for (DataFetcher<Data> fetcher : this.fetchers) {
                fetcher.cleanup();
            }
        }

        public void cancel() {
            this.isCancelled = true;
            for (DataFetcher<Data> fetcher : this.fetchers) {
                fetcher.cancel();
            }
        }

        public Class<Data> getDataClass() {
            return this.fetchers.get(0).getDataClass();
        }

        public DataSource getDataSource() {
            return this.fetchers.get(0).getDataSource();
        }

        public void onDataReady(Data data) {
            if (data != null) {
                this.callback.onDataReady(data);
            } else {
                startNextOrFail();
            }
        }

        public void onLoadFailed(Exception e) {
            ((List) Preconditions.checkNotNull(this.exceptions)).add(e);
            startNextOrFail();
        }

        private void startNextOrFail() {
            if (!this.isCancelled) {
                if (this.currentIndex < this.fetchers.size() - 1) {
                    this.currentIndex++;
                    loadData(this.priority, this.callback);
                    return;
                }
                Preconditions.checkNotNull(this.exceptions);
                this.callback.onLoadFailed(new GlideException("Fetch failed", (List<Throwable>) new ArrayList(this.exceptions)));
            }
        }
    }
}
