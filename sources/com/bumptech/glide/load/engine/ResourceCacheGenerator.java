package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

class ResourceCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private ResourceCacheKey currentKey;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int resourceClassIndex = -1;
    private int sourceIdIndex;
    private Key sourceKey;

    ResourceCacheGenerator(DecodeHelper<?> helper2, DataFetcherGenerator.FetcherReadyCallback cb2) {
        this.helper = helper2;
        this.cb = cb2;
    }

    public boolean startNext() {
        List<Key> sourceIds = this.helper.getCacheKeys();
        boolean started = false;
        if (sourceIds.isEmpty()) {
            return false;
        }
        List<Class<?>> resourceClasses = this.helper.getRegisteredResourceClasses();
        if (!resourceClasses.isEmpty()) {
            while (true) {
                if (this.modelLoaders == null || !hasNextModelLoader()) {
                    this.resourceClassIndex++;
                    if (this.resourceClassIndex >= resourceClasses.size()) {
                        this.sourceIdIndex++;
                        if (this.sourceIdIndex >= sourceIds.size()) {
                            return started;
                        }
                        this.resourceClassIndex = started ? 1 : 0;
                    }
                    Key sourceId = sourceIds.get(this.sourceIdIndex);
                    Class<?> resourceClass = resourceClasses.get(this.resourceClassIndex);
                    ResourceCacheKey resourceCacheKey = r5;
                    ResourceCacheKey resourceCacheKey2 = new ResourceCacheKey(this.helper.getArrayPool(), sourceId, this.helper.getSignature(), this.helper.getWidth(), this.helper.getHeight(), this.helper.getTransformation(resourceClass), resourceClass, this.helper.getOptions());
                    this.currentKey = resourceCacheKey;
                    this.cacheFile = this.helper.getDiskCache().get(this.currentKey);
                    if (this.cacheFile != null) {
                        this.sourceKey = sourceId;
                        this.modelLoaders = this.helper.getModelLoaders(this.cacheFile);
                        started = false;
                        this.modelLoaderIndex = 0;
                    } else {
                        started = false;
                    }
                } else {
                    this.loadData = null;
                    while (!started && hasNextModelLoader()) {
                        List<ModelLoader<File, ?>> list = this.modelLoaders;
                        int i = this.modelLoaderIndex;
                        this.modelLoaderIndex = i + 1;
                        this.loadData = list.get(i).buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                        if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                            started = true;
                            this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                        }
                    }
                    return started;
                }
            }
        } else if (File.class.equals(this.helper.getTranscodeClass())) {
            return false;
        } else {
            throw new IllegalStateException("Failed to find any load path from " + this.helper.getModelClass() + " to " + this.helper.getTranscodeClass());
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
        this.cb.onDataFetcherReady(this.sourceKey, data, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE, this.currentKey);
    }

    public void onLoadFailed(@NonNull Exception e) {
        this.cb.onDataFetcherFailed(this.currentKey, e, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }
}
