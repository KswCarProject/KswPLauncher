package com.wits.pms.statuscontrol;

/* loaded from: classes.dex */
public class PictureStatus {
    public static final int TYPE_PIC_STATUS = 23;
    private boolean mask;
    private String path;
    private boolean play;

    public PictureStatus(boolean play, String path, boolean mask) {
        this.play = play;
        this.path = path;
        this.mask = mask;
    }

    public boolean isPlay() {
        return this.play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isMask() {
        return this.mask;
    }

    public void setMask(boolean mask) {
        this.mask = mask;
    }
}
