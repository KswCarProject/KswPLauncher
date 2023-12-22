package com.wits.ksw.settings.id7.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes4.dex */
public class MapBean {
    private boolean isCheck;
    private Drawable mapicon;
    private String name;
    private String packageName;

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getMapicon() {
        return this.mapicon;
    }

    public void setMapicon(Drawable mapicon) {
        this.mapicon = mapicon;
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || !(otherObject instanceof MapBean)) {
            return false;
        }
        MapBean other = (MapBean) otherObject;
        if (this.name.equals(other.name) && this.packageName == other.packageName) {
            return true;
        }
        return false;
    }
}
