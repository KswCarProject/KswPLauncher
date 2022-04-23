package com.wits.ksw.launcher.bean;

import android.graphics.drawable.Drawable;

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

    public void setAppLable(String appLable2) {
        this.appLable = appLable2;
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(Drawable appIcon2) {
        this.appIcon = appIcon2;
    }

    public String getApppkg() {
        return this.apppkg;
    }

    public void setApppkg(String apppkg2) {
        this.apppkg = apppkg2;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className2) {
        this.className = className2;
    }

    public String toString() {
        return "appInfo{appLable='" + this.appLable + '\'' + ", appIcon=" + this.appIcon + ", apppkg='" + this.apppkg + '\'' + ", className='" + this.className + '\'' + '}';
    }
}
