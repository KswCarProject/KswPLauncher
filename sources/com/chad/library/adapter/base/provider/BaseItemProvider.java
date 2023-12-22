package com.chad.library.adapter.base.provider;

import android.content.Context;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseItemProvider<T, V extends BaseViewHolder> {
    public Context mContext;
    public List<T> mData;

    public abstract void convert(V v, T t, int i);

    public abstract int layout();

    public abstract int viewType();

    public void convertPayloads(V helper, T data, int position, List<Object> payloads) {
    }

    public void onClick(V helper, T data, int position) {
    }

    public boolean onLongClick(V helper, T data, int position) {
        return false;
    }
}
