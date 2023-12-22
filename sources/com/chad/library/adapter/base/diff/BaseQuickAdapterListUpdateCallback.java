package com.chad.library.adapter.base.diff;

import android.support.p004v7.util.ListUpdateCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;

/* loaded from: classes.dex */
public final class BaseQuickAdapterListUpdateCallback implements ListUpdateCallback {
    private final BaseQuickAdapter mAdapter;

    public BaseQuickAdapterListUpdateCallback(BaseQuickAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // android.support.p004v7.util.ListUpdateCallback
    public void onInserted(int position, int count) {
        BaseQuickAdapter baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemRangeInserted(baseQuickAdapter.getHeaderLayoutCount() + position, count);
    }

    @Override // android.support.p004v7.util.ListUpdateCallback
    public void onRemoved(int position, int count) {
        BaseQuickAdapter baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemRangeRemoved(baseQuickAdapter.getHeaderLayoutCount() + position, count);
    }

    @Override // android.support.p004v7.util.ListUpdateCallback
    public void onMoved(int fromPosition, int toPosition) {
        BaseQuickAdapter baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemMoved(baseQuickAdapter.getHeaderLayoutCount() + fromPosition, this.mAdapter.getHeaderLayoutCount() + toPosition);
    }

    @Override // android.support.p004v7.util.ListUpdateCallback
    public void onChanged(int position, int count, Object payload) {
        BaseQuickAdapter baseQuickAdapter = this.mAdapter;
        baseQuickAdapter.notifyItemRangeChanged(baseQuickAdapter.getHeaderLayoutCount() + position, count, payload);
    }
}
