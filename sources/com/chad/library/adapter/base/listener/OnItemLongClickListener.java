package com.chad.library.adapter.base.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

/* loaded from: classes.dex */
public abstract class OnItemLongClickListener extends SimpleClickListener {
    public abstract void onSimpleItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    @Override // com.chad.library.adapter.base.listener.SimpleClickListener
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override // com.chad.library.adapter.base.listener.SimpleClickListener
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemLongClick(adapter, view, position);
    }

    @Override // com.chad.library.adapter.base.listener.SimpleClickListener
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override // com.chad.library.adapter.base.listener.SimpleClickListener
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}