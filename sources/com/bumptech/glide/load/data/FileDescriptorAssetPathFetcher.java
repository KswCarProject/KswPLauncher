package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

public class FileDescriptorAssetPathFetcher extends AssetPathFetcher<ParcelFileDescriptor> {
    public FileDescriptorAssetPathFetcher(AssetManager assetManager, String assetPath) {
        super(assetManager, assetPath);
    }

    /* access modifiers changed from: protected */
    public ParcelFileDescriptor loadResource(AssetManager assetManager, String path) throws IOException {
        return assetManager.openFd(path).getParcelFileDescriptor();
    }

    /* access modifiers changed from: protected */
    public void close(ParcelFileDescriptor data) throws IOException {
        data.close();
    }

    public Class<ParcelFileDescriptor> getDataClass() {
        return ParcelFileDescriptor.class;
    }
}
