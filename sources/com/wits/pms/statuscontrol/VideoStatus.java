package com.wits.pms.statuscontrol;

import com.google.gson.Gson;

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

    public void setPath(String path2) {
        this.path = path2;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode2) {
        this.mode = mode2;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position2) {
        this.position = position2;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int totalTime2) {
        this.totalTime = totalTime2;
    }

    public boolean isPlay() {
        return this.play;
    }

    public void setPlay(boolean play2) {
        this.play = play2;
    }

    public boolean isMask() {
        return this.mask;
    }

    public void setMask(boolean mask2) {
        this.mask = mask2;
    }

    public static VideoStatus getStatusFromJson(String jsonArg) {
        return (VideoStatus) new Gson().fromJson(jsonArg, VideoStatus.class);
    }
}
