package com.wits.ksw.launcher.bean.lexusls;

import android.graphics.drawable.Drawable;

public class LexusLsAppSelBean {
    private String action = "";
    private Drawable appIcon;
    private String appMainAty;
    private String appName;
    private String appPkg;
    private boolean deletable = true;
    private boolean isChecked;
    private int itemPos = -1;

    public String getAction() {
        return this.action;
    }

    public void setAction(String action2) {
        this.action = action2;
    }

    public int getItemPos() {
        return this.itemPos;
    }

    public void setItemPos(int itemPos2) {
        this.itemPos = itemPos2;
    }

    public boolean isDeletable() {
        return this.deletable;
    }

    public void setDeletable(boolean deletable2) {
        this.deletable = deletable2;
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(Drawable appIcon2) {
        this.appIcon = appIcon2;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName2) {
        this.appName = appName2;
    }

    public String getAppPkg() {
        return this.appPkg;
    }

    public void setAppPkg(String appPkg2) {
        this.appPkg = appPkg2;
    }

    public String getAppMainAty() {
        return this.appMainAty;
    }

    public void setAppMainAty(String appMainAty2) {
        this.appMainAty = appMainAty2;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
