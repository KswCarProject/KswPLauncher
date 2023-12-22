package com.wits.ksw.launcher.bean.lexusls;

import android.graphics.drawable.Drawable;

/* loaded from: classes12.dex */
public class LexusLsAppSelBean {
    private Drawable appIcon;
    private String appMainAty;
    private String appName;
    private String appPkg;
    private boolean isChecked;
    private boolean deletable = true;
    private int itemPos = -1;
    private String action = "";

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getItemPos() {
        return this.itemPos;
    }

    public void setItemPos(int itemPos) {
        this.itemPos = itemPos;
    }

    public boolean isDeletable() {
        return this.deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public Drawable getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPkg() {
        return this.appPkg;
    }

    public void setAppPkg(String appPkg) {
        this.appPkg = appPkg;
    }

    public String getAppMainAty() {
        return this.appMainAty;
    }

    public void setAppMainAty(String appMainAty) {
        this.appMainAty = appMainAty;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
