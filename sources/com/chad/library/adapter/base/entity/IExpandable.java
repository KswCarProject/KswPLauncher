package com.chad.library.adapter.base.entity;

import java.util.List;

public interface IExpandable<T> {
    int getLevel();

    List<T> getSubItems();

    boolean isExpanded();

    void setExpanded(boolean z);
}
