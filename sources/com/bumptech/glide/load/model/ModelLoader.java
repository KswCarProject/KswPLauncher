package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.util.Collections;
import java.util.List;

public interface ModelLoader<Model, Data> {
    @Nullable
    LoadData<Data> buildLoadData(@NonNull Model model, int i, int i2, @NonNull Options options);

    boolean handles(@NonNull Model model);

    public static class LoadData<Data> {
        public final List<Key> alternateKeys;
        public final DataFetcher<Data> fetcher;
        public final Key sourceKey;

        public LoadData(@NonNull Key sourceKey2, @NonNull DataFetcher<Data> fetcher2) {
            this(sourceKey2, Collections.emptyList(), fetcher2);
        }

        public LoadData(@NonNull Key sourceKey2, @NonNull List<Key> alternateKeys2, @NonNull DataFetcher<Data> fetcher2) {
            this.sourceKey = (Key) Preconditions.checkNotNull(sourceKey2);
            this.alternateKeys = (List) Preconditions.checkNotNull(alternateKeys2);
            this.fetcher = (DataFetcher) Preconditions.checkNotNull(fetcher2);
        }
    }
}
