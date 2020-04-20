package com.wits.ksw.settings.id7.bean;

import android.graphics.drawable.Drawable;

public class DevBean {
    private Drawable appicon;
    private boolean isCheck;
    private String name;
    private String packageName;

    public Drawable getAppicon() {
        return this.appicon;
    }

    public void setAppicon(Drawable appicon2) {
        this.appicon = appicon2;
    }

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
}
