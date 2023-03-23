package com.chad.library.adapter.base.entity;

import java.io.Serializable;

public abstract class SectionMultiEntity<T> implements Serializable, MultiItemEntity {
    public String header;
    public boolean isHeader;
    public T t;

    public SectionMultiEntity(boolean isHeader2, String header2) {
        this.isHeader = isHeader2;
        this.header = header2;
        this.t = null;
    }

    public SectionMultiEntity(T t2) {
        this.isHeader = false;
        this.header = null;
        this.t = t2;
    }
}
