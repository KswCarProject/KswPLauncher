package com.chad.library.adapter.base.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemChildLongClickListener extends SimpleClickListener {
    public abstract void onSimpleItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemChildLongClick(adapter, view, position);
    }
}
