package com.wits.ksw.launcher.bean;

import android.graphics.drawable.Drawable;

public class BcItem {
    public Drawable appIcon;
    public String appLable;
    public int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public String getAppLable() {
        return this.appLable;
    }

    public void setAppLable(String appLable2) {
        this.appLable = appLable2;
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(Drawable appIcon2) {
        this.appIcon = appIcon2;
    }
}
