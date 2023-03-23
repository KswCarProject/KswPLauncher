package com.chad.library.adapter.base.listener;

import android.support.v7.widget.RecyclerView;

public interface OnItemDragListener {
    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i);

    void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2);

    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i);
}
