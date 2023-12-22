package com.wits.ksw.launcher.view.benzmbux2021new.bean;

import android.graphics.drawable.Drawable;

/* loaded from: classes10.dex */
public class BenzCardItem {
    private String content;
    private int iconLeft;
    private int iconRight;
    private int imgBg;
    private Drawable imgSrc;
    private boolean isThird;
    private String name;
    private int number;
    private String title;

    public BenzCardItem() {
    }

    public BenzCardItem(int number, String name, String title, String content, int imgBg, Drawable imgSrc, int iconLeft, int iconRight, boolean isThird) {
        this.number = number;
        this.name = name;
        this.title = title;
        this.content = content;
        this.imgBg = imgBg;
        this.imgSrc = imgSrc;
        this.iconLeft = iconLeft;
        this.iconRight = iconRight;
        this.isThird = isThird;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setIconLeft(int iconLeft) {
        this.iconLeft = iconLeft;
    }

    public int getIconLeft() {
        return this.iconLeft;
    }

    public void setIconRight(int iconRight) {
        this.iconRight = iconRight;
    }

    public int getIconRight() {
        return this.iconRight;
    }

    public void setThird(boolean third) {
        this.isThird = third;
    }

    public boolean isThird() {
        return this.isThird;
    }

    public void setImgBg(int imgBg) {
        this.imgBg = imgBg;
    }

    public int getImgBg() {
        return this.imgBg;
    }

    public void setImgSrc(Drawable imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Drawable getImgSrc() {
        return this.imgSrc;
    }

    public String toString() {
        return "BenzCardItem{number=" + this.number + ", name='" + this.name + "', title='" + this.title + "', content='" + this.content + "', imgBg=" + this.imgBg + ", imgSrc=" + this.imgSrc + ", iconLeft=" + this.iconLeft + ", iconRight=" + this.iconRight + ", isThird=" + this.isThird + '}';
    }
}
