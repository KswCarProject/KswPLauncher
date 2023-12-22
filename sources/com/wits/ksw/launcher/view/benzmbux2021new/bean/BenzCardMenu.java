package com.wits.ksw.launcher.view.benzmbux2021new.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/* loaded from: classes10.dex */
public class BenzCardMenu<T> implements MultiItemEntity {
    public static final int TYPE_LOCAL = 1;
    public static final int TYPE_THIRD = 2;
    private T benzCardItem;
    private int type;

    public BenzCardMenu(int type, T item) {
        this.type = type;
        this.benzCardItem = item;
    }

    public void setBenzCardItem(T benzCardItem) {
        this.benzCardItem = benzCardItem;
    }

    public T getBenzCardItem() {
        return this.benzCardItem;
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return this.type;
    }

    public String toString() {
        return "BenzCardMenu{type=" + this.type + ", benzCardItem=" + this.benzCardItem + '}';
    }
}
