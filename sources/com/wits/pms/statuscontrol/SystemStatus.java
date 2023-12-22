package com.wits.pms.statuscontrol;

import android.view.KeyEvent;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SystemStatus {
    public static final int TYPE_SYSTEM_STATUS = 1;
    public int ccd;
    public boolean dormant;
    public int epb;
    public KeyEvent event;
    public int ill;
    public int llight;
    public int rlight;
    public String topApp;
    public int screenSwitch = 2;
    public int acc = 2;
    public String realCurrentApp = "";
    public int lastMode = -1;

    /* loaded from: classes.dex */
    public static final class ACC {
        public static final int NORMAL = 2;
        public static final int OFF = 0;
        public static final int OFF_READY = -1;

        /* renamed from: ON */
        public static final int f249ON = 1;
    }

    /* loaded from: classes.dex */
    public static final class CCD {
        public static final int NORMAL = 0;
        public static final int REVER = 1;
    }

    /* loaded from: classes.dex */
    public static final class EPB {
        public static final int TURN_OFF = 0;
        public static final int TURN_ON = 1;
    }

    /* loaded from: classes.dex */
    public static final class ILL {
        public static final int TURN_OFF = 0;
        public static final int TURN_ON = 1;
    }

    /* loaded from: classes.dex */
    public static final class MODE {
        public static final int AUX = 4;
        public static final int BT_CALL = 7;
        public static final int BT_MUSIC = 6;
        public static final int DVD = 10;

        /* renamed from: FM */
        public static final int f250FM = 0;
        public static final int MUSIC = 1;
        public static final int NAVI = 5;
        public static final int PIC = 3;
        public static final int REVER = 8;

        /* renamed from: TV */
        public static final int f251TV = 9;
        public static final int VIDEO = 2;
    }

    /* loaded from: classes.dex */
    public static final class RLIGHT {
        public static final int NORMAL = 0;
        public static final int OPEN = 1;
    }

    /* loaded from: classes.dex */
    public static final class SCREEN {
        public static final int NORMAL = 2;
        public static final int OFF = 0;

        /* renamed from: ON */
        public static final int f252ON = 1;
    }

    public SystemStatus() {
        this.topApp = "";
        this.topApp = "";
    }

    public List<String> compare(SystemStatus systemStatus) {
        List<String> keys = new ArrayList<>();
        if (this.acc != systemStatus.acc) {
            keys.add("acc");
        }
        if (this.rlight != systemStatus.rlight) {
            keys.add("rlight");
        }
        if (this.llight != systemStatus.llight) {
            keys.add("llight");
        }
        if (this.screenSwitch != systemStatus.screenSwitch) {
            keys.add("screenSwitch");
        }
        if (this.ccd != systemStatus.ccd) {
            keys.add("ccd");
        }
        if (this.ill != systemStatus.ill) {
            keys.add("ill");
        }
        if (this.epb != systemStatus.epb) {
            keys.add("epb");
        }
        if (this.dormant != systemStatus.dormant) {
            keys.add("dormant");
        }
        if (this.lastMode != systemStatus.lastMode) {
            keys.add("lastMode");
        }
        String str = systemStatus.topApp;
        if (str != null && !str.equals(this.topApp)) {
            keys.add("topApp");
        }
        return keys;
    }

    public int getAcc() {
        return this.acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public KeyEvent getEvent() {
        return this.event;
    }

    public void setEvent(KeyEvent event) {
        this.event = event;
    }

    public String getTopApp() {
        return this.topApp;
    }

    public void setTopApp(String topApp) {
        this.topApp = topApp;
    }

    public boolean isDormant() {
        return this.dormant;
    }

    public void setDormant(boolean dormant) {
        this.dormant = dormant;
    }

    public int getLastMode() {
        return this.lastMode;
    }

    public void setLastMode(int lastMode) {
        this.lastMode = lastMode;
    }

    public void setScreenSwitch(int screenSwitch) {
        this.screenSwitch = screenSwitch;
    }

    public static SystemStatus getStatusFormJson(String jsonArg) {
        return (SystemStatus) new Gson().fromJson(jsonArg, (Class<Object>) SystemStatus.class);
    }

    public boolean isRlightOpen() {
        return this.rlight == 1;
    }

    public boolean isEpbOpen() {
        return this.epb == 1;
    }

    public boolean isIllOpen() {
        return this.ill == 1;
    }

    public boolean isRevering() {
        return this.ccd == 1;
    }

    public boolean getScreenSwitch() {
        return this.screenSwitch == 1;
    }

    public int getRlight() {
        return this.rlight;
    }

    public void setRlight(int rlight) {
        this.rlight = rlight;
    }

    public int getCcd() {
        return this.ccd;
    }

    public void setCcd(int ccd) {
        this.ccd = ccd;
    }

    public int getIll() {
        return this.ill;
    }

    public void setIll(int ill) {
        this.ill = ill;
    }

    public int getEpb() {
        return this.epb;
    }

    public void setEpb(int epb) {
        this.epb = epb;
    }

    public boolean isAcc() {
        int i = this.acc;
        return i == 2 || i == 1;
    }
}
