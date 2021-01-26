package com.wits.ksw.settings.id7.bean;

public class FunctionBean {
    private String display;
    private int icon;
    private boolean ischeck;
    private String title;

    public boolean isIscheck() {
        return this.ischeck;
    }

    public void setIscheck(boolean ischeck2) {
        this.ischeck = ischeck2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public void setDisplay(String display2) {
        this.display = display2;
    }

    public String getDisplay() {
        return this.display;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon2) {
        this.icon = icon2;
    }
}
