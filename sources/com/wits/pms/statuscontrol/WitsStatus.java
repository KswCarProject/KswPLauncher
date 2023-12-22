package com.wits.pms.statuscontrol;

import com.google.gson.Gson;

/* loaded from: classes.dex */
public class WitsStatus {
    public String jsonArg;
    public int type;

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getJsonArg() {
        return this.jsonArg;
    }

    public void setJsonArg(String jsonArg) {
        this.jsonArg = jsonArg;
    }

    public static WitsStatus getWitsStatusFormJson(String jsonArg) {
        return (WitsStatus) new Gson().fromJson(jsonArg, (Class<Object>) WitsStatus.class);
    }

    public WitsStatus(int type, String jsonArg) {
        this.type = type;
        this.jsonArg = jsonArg;
    }

    public static void sendOutBtStatus(BtPhoneStatus btPhoneStatus) {
        WitsStatus witsStatus = new WitsStatus(3, new Gson().toJson(btPhoneStatus));
        PowerManagerApp.sendStatus(witsStatus);
    }

    public static void sendOutMusicStatus(MusicStatus musicStatus) {
        WitsStatus witsStatus = new WitsStatus(21, new Gson().toJson(musicStatus));
        PowerManagerApp.sendStatus(witsStatus);
    }

    public static void sendOutVideoStatus(VideoStatus videoStatus) {
        WitsStatus witsStatus = new WitsStatus(22, new Gson().toJson(videoStatus));
        PowerManagerApp.sendStatus(witsStatus);
    }

    public static void sendOutPictureStatus(PictureStatus pictureStatus) {
        WitsStatus witsStatus = new WitsStatus(23, new Gson().toJson(pictureStatus));
        PowerManagerApp.sendStatus(witsStatus);
    }

    public static void sendOutSystemStatus(SystemStatus systemStatus) {
        WitsStatus witsStatus = new WitsStatus(1, new Gson().toJson(systemStatus));
        PowerManagerApp.sendStatus(witsStatus);
    }
}
