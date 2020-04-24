package com.wits.pms.statuscontrol;

import com.google.gson.Gson;

public class WitsStatus {
    public String jsonArg;
    public int type;

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public String getJsonArg() {
        return this.jsonArg;
    }

    public void setJsonArg(String jsonArg2) {
        this.jsonArg = jsonArg2;
    }

    public static WitsStatus getWitsStatusFormJson(String jsonArg2) {
        return (WitsStatus) new Gson().fromJson(jsonArg2, WitsStatus.class);
    }

    public WitsStatus(int type2, String jsonArg2) {
        this.type = type2;
        this.jsonArg = jsonArg2;
    }

    public static void sendOutBtStatus(BtPhoneStatus btPhoneStatus) {
        PowerManagerApp.sendStatus(new WitsStatus(3, new Gson().toJson((Object) btPhoneStatus)));
    }

    public static void sendOutMusicStatus(MusicStatus musicStatus) {
        PowerManagerApp.sendStatus(new WitsStatus(21, new Gson().toJson((Object) musicStatus)));
    }

    public static void sendOutVideoStatus(VideoStatus videoStatus) {
        PowerManagerApp.sendStatus(new WitsStatus(22, new Gson().toJson((Object) videoStatus)));
    }

    public static void sendOutPictureStatus(PictureStatus pictureStatus) {
        PowerManagerApp.sendStatus(new WitsStatus(23, new Gson().toJson((Object) pictureStatus)));
    }

    public static void sendOutSystemStatus(SystemStatus systemStatus) {
        PowerManagerApp.sendStatus(new WitsStatus(1, new Gson().toJson((Object) systemStatus)));
    }
}
