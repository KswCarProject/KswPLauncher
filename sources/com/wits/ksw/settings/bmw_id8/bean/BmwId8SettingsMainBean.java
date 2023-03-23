package com.wits.ksw.settings.bmw_id8.bean;

public class BmwId8SettingsMainBean {
    private int mBgId;
    private String mContent;
    private int mIconId;
    private String mTitle;

    public BmwId8SettingsMainBean(String title, String content, int bgId, int iconId) {
        this.mTitle = title;
        this.mContent = content;
        this.mBgId = bgId;
        this.mIconId = iconId;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public void setBgId(int bgId) {
        this.mBgId = bgId;
    }

    public int getBgId() {
        return this.mBgId;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public String toString() {
        return "BmwId8SettingsMainBean{mTitle='" + this.mTitle + '\'' + ", mContent='" + this.mContent + '\'' + ", mBgId=" + this.mBgId + ", mIconId=" + this.mIconId + '}';
    }
}
