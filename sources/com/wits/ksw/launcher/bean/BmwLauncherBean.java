package com.wits.ksw.launcher.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes9.dex */
public class BmwLauncherBean {
    private Drawable drawable;
    private String secondTile;
    private String title;

    public BmwLauncherBean(String title, String secondTile, Drawable drawable) {
        this.title = title;
        this.secondTile = secondTile;
        this.drawable = drawable;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTile() {
        return this.secondTile;
    }

    public void setSecondTile(String secondTile) {
        this.secondTile = secondTile;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
