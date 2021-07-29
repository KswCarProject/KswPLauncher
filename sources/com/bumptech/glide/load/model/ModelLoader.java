package com.bumptech.glide.load.model;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

public interface ModelLoader<Model, Data> {
    LoadData<Data> buildLoadData(Model model, int i, int i2, Options options);

    boolean handles(Model model);

    public static class LoadData<Data> {
        public final List<Key> alternateKeys;
        public final DataFetcher<Data> fetcher;
        public final Key sourceKey;

        public LoadData(Key sourceKey2, DataFetcher<Data> fetcher2) {
            this(sourceKey2, Collections.emptyList(), fetcher2);
        }

        public LoadData(Key sourceKey2, List<Key> alternateKeys2, DataFetcher<Data> fetcher2) {
            this.sourceKey = (Key) Preconditions.checkNotNull(sourceKey2);
            this.alternateKeys = (List) Preconditions.checkNotNull(alternateKeys2);
            this.fetcher = (DataFetcher) Preconditions.checkNotNull(fetcher2);
        }
    }
}
