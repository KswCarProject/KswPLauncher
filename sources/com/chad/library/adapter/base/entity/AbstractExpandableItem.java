package com.chad.library.adapter.base.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AbstractExpandableItem<T> implements IExpandable<T> {
    protected boolean mExpandable = false;
    protected List<T> mSubItems;

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public boolean isExpanded() {
        return this.mExpandable;
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public void setExpanded(boolean expanded) {
        this.mExpandable = expanded;
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public List<T> getSubItems() {
        return this.mSubItems;
    }

    public boolean hasSubItem() {
        List<T> list = this.mSubItems;
        return list != null && list.size() > 0;
    }

    public void setSubItems(List<T> list) {
        this.mSubItems = list;
    }

    public T getSubItem(int position) {
        if (hasSubItem() && position < this.mSubItems.size()) {
            return this.mSubItems.get(position);
        }
        return null;
    }

    public int getSubItemPosition(T subItem) {
        List<T> list = this.mSubItems;
        if (list != null) {
            return list.indexOf(subItem);
        }
        return -1;
    }

    public void addSubItem(T subItem) {
        if (this.mSubItems == null) {
            this.mSubItems = new ArrayList();
        }
        this.mSubItems.add(subItem);
    }

    public void addSubItem(int position, T subItem) {
        List<T> list = this.mSubItems;
        if (list != null && position >= 0 && position < list.size()) {
            this.mSubItems.add(position, subItem);
        } else {
            addSubItem(subItem);
        }
    }

    public boolean contains(T subItem) {
        List<T> list = this.mSubItems;
        return list != null && list.contains(subItem);
    }

    public boolean removeSubItem(T subItem) {
        List<T> list = this.mSubItems;
        return list != null && list.remove(subItem);
    }

    public boolean removeSubItem(int position) {
        List<T> list = this.mSubItems;
        if (list != null && position >= 0 && position < list.size()) {
            this.mSubItems.remove(position);
            return true;
        }
        return false;
    }
}
