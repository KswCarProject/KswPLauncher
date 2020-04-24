package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

public class StreamAssetPathFetcher extends AssetPathFetcher<InputStream> {
    public StreamAssetPathFetcher(AssetManager assetManager, String assetPath) {
        super(assetManager, assetPath);
    }

    /* access modifiers changed from: protected */
    public InputStream loadResource(AssetManager assetManager, String path) throws IOException {
        return assetManager.open(path);
    }

    /* access modifiers changed from: protected */
    public void close(InputStream data) throws IOException {
        data.close();
    }

    @NonNull
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }
}
