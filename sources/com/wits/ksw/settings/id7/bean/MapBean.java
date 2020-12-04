package com.wits.ksw.settings.id7.bean;

import android.graphics.drawable.Drawable;

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

    public void setName(String name2) {
        this.name = name2;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName2) {
        this.packageName = packageName2;
    }

    public Drawable getMapicon() {
        return this.mapicon;
    }

    public void setMapicon(Drawable mapicon2) {
        this.mapicon = mapicon2;
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || !(otherObject instanceof MapBean)) {
            return false;
        }
        MapBean other = (MapBean) otherObject;
        if (!this.name.equals(other.name) || this.packageName != other.packageName) {
            return false;
        }
        return true;
    }
}
