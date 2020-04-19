package com.wits.pms.statuscontrol;

public class PictureStatus {
    public static final int TYPE_PIC_STATUS = 23;
    private boolean mask;
    private String path;
    private boolean play;

    public PictureStatus(boolean play2, String path2, boolean mask2) {
        this.play = play2;
        this.path = path2;
        this.mask = mask2;
    }

    public boolean isPlay() {
        return this.play;
    }

    public void setPlay(boolean play2) {
        this.play = play2;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path2) {
        this.path = path2;
    }

    public boolean isMask() {
        return this.mask;
    }

    public void setMask(boolean mask2) {
        this.mask = mask2;
    }
}
