package com.wits.ksw.launcher.bean;

import android.graphics.drawable.Drawable;

public class BmwLauncherBean {
    private Drawable drawable;
    private String secondTile;
    private String title;

    public BmwLauncherBean(String title2, String secondTile2, Drawable drawable2) {
        this.title = title2;
        this.secondTile = secondTile2;
        this.drawable = drawable2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getSecondTile() {
        return this.secondTile;
    }

    public void setSecondTile(String secondTile2) {
        this.secondTile = secondTile2;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable2) {
        this.drawable = drawable2;
    }
}
