package com.wits.ksw.launcher.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes9.dex */
public final class AppInfo {
    public Drawable appIcon;
    public String appLable;
    public String apppkg;
    public String className;
    private boolean isCheck;

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public String getAppLable() {
        return this.appLable;
    }

    public void setAppLable(String appLable) {
        this.appLable = appLable;
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getApppkg() {
        return this.apppkg;
    }

    public void setApppkg(String apppkg) {
        this.apppkg = apppkg;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String toString() {
        return "appInfo{appLable='" + this.appLable + "', appIcon=" + this.appIcon + ", apppkg='" + this.apppkg + "', className='" + this.className + "'}";
    }
}
