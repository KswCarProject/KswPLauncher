package com.wits.pms.statuscontrol;

import com.google.gson.Gson;

/* loaded from: classes.dex */
public class VideoStatus {
    public static final int TYPE_VIDEO_STATUS = 22;
    private boolean mask;
    private String mode;
    private String path;
    private boolean play;
    private int position;
    private int totalTime;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isPlay() {
        return this.play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isMask() {
        return this.mask;
    }

    public void setMask(boolean mask) {
        this.mask = mask;
    }

    public static VideoStatus getStatusFromJson(String jsonArg) {
        return (VideoStatus) new Gson().fromJson(jsonArg, (Class<Object>) VideoStatus.class);
    }
}
