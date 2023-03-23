package com.chad.library.adapter.base.util;

import android.util.SparseArray;
import com.chad.library.adapter.base.provider.BaseItemProvider;

public class ProviderDelegate {
    private SparseArray<BaseItemProvider> mItemProviders = new SparseArray<>();

    public void registerProvider(BaseItemProvider provider) {
        if (provider != null) {
            int viewType = provider.viewType();
            if (this.mItemProviders.get(viewType) == null) {
                this.mItemProviders.put(viewType, provider);
                return;
            }
            return;
        }
        throw new ItemProviderException("ItemProvider can not be null");
    }

    public SparseArray<BaseItemProvider> getItemProviders() {
        return this.mItemProviders;
    }
}
