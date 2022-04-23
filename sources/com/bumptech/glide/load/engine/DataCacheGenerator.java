package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

class DataCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final List<Key> cacheKeys;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int sourceIdIndex;
    private Key sourceKey;

    DataCacheGenerator(DecodeHelper<?> helper2, DataFetcherGenerator.FetcherReadyCallback cb2) {
        this(helper2.getCacheKeys(), helper2, cb2);
    }

    DataCacheGenerator(List<Key> cacheKeys2, DecodeHelper<?> helper2, DataFetcherGenerator.FetcherReadyCallback cb2) {
        this.sourceIdIndex = -1;
        this.cacheKeys = cacheKeys2;
        this.helper = helper2;
        this.cb = cb2;
    }

    public boolean startNext() {
        while (true) {
            if (this.modelLoaders == null || !hasNextModelLoader()) {
                int i = this.sourceIdIndex + 1;
                this.sourceIdIndex = i;
                if (i >= this.cacheKeys.size()) {
                    return false;
                }
                Key sourceId = this.cacheKeys.get(this.sourceIdIndex);
                File file = this.helper.getDiskCache().get(new DataCacheKey(sourceId, this.helper.getSignature()));
                this.cacheFile = file;
                if (file != null) {
                    this.sourceKey = sourceId;
                    this.modelLoaders = this.helper.getModelLoaders(file);
                    this.modelLoaderIndex = 0;
                }
            } else {
                this.loadData = null;
                boolean started = false;
                while (!started && hasNextModelLoader()) {
                    List<ModelLoader<File, ?>> list = this.modelLoaders;
                    int i2 = this.modelLoaderIndex;
                    this.modelLoaderIndex = i2 + 1;
                    this.loadData = list.get(i2).buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                    if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                        started = true;
                        this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                    }
                }
                return started;
            }
        }
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    public void cancel() {
        ModelLoader.LoadData<?> local = this.loadData;
        if (local != null) {
            local.fetcher.cancel();
        }
    }

    public void onDataReady(Object data) {
        this.cb.onDataFetcherReady(this.sourceKey, data, this.loadData.fetcher, DataSource.DATA_DISK_CACHE, this.sourceKey);
    }

    public void onLoadFailed(Exception e) {
        this.cb.onDataFetcherFailed(this.sourceKey, e, this.loadData.fetcher, DataSource.DATA_DISK_CACHE);
    }
}
