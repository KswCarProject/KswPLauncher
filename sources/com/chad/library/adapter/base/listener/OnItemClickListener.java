package com.chad.library.adapter.base.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemClickListener extends SimpleClickListener {
    public abstract void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        onSimpleItemClick(adapter, view, position);
    }

    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
