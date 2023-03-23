package com.chad.library.adapter.base.diff;

import android.support.v7.util.DiffUtil;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuickDiffCallback<T> extends DiffUtil.Callback {
    private List<T> newList;
    private List<T> oldList;

    /* access modifiers changed from: protected */
    public abstract boolean areContentsTheSame(T t, T t2);

    /* access modifiers changed from: protected */
    public abstract boolean areItemsTheSame(T t, T t2);

    public BaseQuickDiffCallback(List<T> newList2) {
        this.newList = newList2 == null ? new ArrayList<>() : newList2;
    }

    public List<T> getNewList() {
        return this.newList;
    }

    public List<T> getOldList() {
        return this.oldList;
    }

    public void setOldList(List<T> oldList2) {
        this.oldList = oldList2 == null ? new ArrayList<>() : oldList2;
    }

    public int getOldListSize() {
        return this.oldList.size();
    }

    public int getNewListSize() {
        return this.newList.size();
    }

    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return getChangePayload(this.oldList.get(oldItemPosition), this.newList.get(newItemPosition));
    }

    /* access modifiers changed from: protected */
    public Object getChangePayload(T t, T t2) {
        return null;
    }
}
