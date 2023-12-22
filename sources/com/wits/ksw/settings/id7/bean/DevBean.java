package com.wits.ksw.settings.id7.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes4.dex */
public class DevBean {
    private Drawable appicon;
    private boolean isCheck;
    private String name;
    private String packageName;

    public Drawable getAppicon() {
        return this.appicon;
    }

    public void setAppicon(Drawable appicon) {
        this.appicon = appicon;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
