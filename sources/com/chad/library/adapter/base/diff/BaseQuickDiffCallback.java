package com.chad.library.adapter.base.diff;

import android.support.p004v7.util.DiffUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseQuickDiffCallback<T> extends DiffUtil.Callback {
    private List<T> newList;
    private List<T> oldList;

    protected abstract boolean areContentsTheSame(T t, T t2);

    protected abstract boolean areItemsTheSame(T t, T t2);

    public BaseQuickDiffCallback(List<T> newList) {
        this.newList = newList == null ? new ArrayList<>() : newList;
    }

    public List<T> getNewList() {
        return this.newList;
    }

    public List<T> getOldList() {
        return this.oldList;
    }

    public void setOldList(List<T> oldList) {
        this.oldList = oldList == null ? new ArrayList<>() : oldList;
    }

    @Override // android.support.p004v7.util.DiffUtil.Callback
    public int getOldListSize() {
        return this.oldList.size();
    }

    @Override // android.support.p004v7.util.DiffUtil.Callback
    public int getNewListSize() {
        return this.newList.size();
    }

    @Override // android.support.p004v7.util.DiffUtil.Callback
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    @Override // android.support.p004v7.util.DiffUtil.Callback
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    @Override // android.support.p004v7.util.DiffUtil.Callback
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return getChangePayload(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    protected Object getChangePayload(T oldItem, T newItem) {
        return null;
    }
}
