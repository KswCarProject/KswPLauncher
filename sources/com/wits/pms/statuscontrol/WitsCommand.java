package com.wits.pms.statuscontrol;

import android.os.RemoteException;
import com.google.gson.Gson;

public class WitsCommand {
    public static final int BT_TYPE = 3;
    public static final int CAN_TYPE = 4;
    public static final int DVD_TYPE = 7;
    public static final int FM_TYPE = 8;
    public static final int KEY_TYPE = 6;
    public static final int MCU_TYPE = 5;
    public static final int MEDIA_TYPE = 2;
    public static final int SYSTEM_TYPE = 1;
    private int command;
    private String jsonArg;
    private int subCommand;

    public static final class BtSubCommand {
        public static final int AUTO_CONN = 106;
        public static final int CLOSE_BT = 105;
        public static final int MUSIC_NEXT = 101;
        public static final int MUSIC_PAUSE = 104;
        public static final int MUSIC_PLAY = 103;
        public static final int MUSIC_PLAYPAUSE = 102;
        public static final int MUSIC_PREVIOUS = 100;
        public static final int MUSIC_RELEASE = 113;
        public static final int MUSIC_UNRELEASE = 114;
        public static final int OPEN_BT = 107;
        public static final int PHONE_ACCEPT = 112;
        public static final int PHONE_CALL = 108;
        public static final int PHONE_HANDUP = 109;
        public static final int SET_VOLUME = 115;
        public static final int VOICE_TO_PHONE = 110;
        public static final int VOICE_TO_SYSTEM = 111;
    }

    public static final class CanSubCommand {
        public static final int ARM_TO_MCU_MESSAGE = 107;
        public static final int CLOSE_PANORAMIC = 106;
        public static final int LOOK_AHEAD = 104;
        public static final int LOOK_BEHIND = 105;
        public static final int LOOK_LEFT = 102;
        public static final int LOOK_RIGHT = 103;
        public static final int MCU_TO_ARM_MESSAGE = 108;
        public static final int OPEN_PANORAMIC = 101;
    }

    public static final class DVDCommand {
        public static final int ARM_TO_MCU_MESSAGE = 102;
        public static final int DVD_ANDIO = 123;
        public static final int DVD_DIRECTION = 127;
        public static final int DVD_DISP = 126;
        public static final int DVD_DTS_ANGLE = 114;
        public static final int DVD_DTS_SUB_T = 112;
        public static final int DVD_GOTO = 125;
        public static final int DVD_NEXT = 120;
        public static final int DVD_NUMBER = 124;
        public static final int DVD_OSD = 122;
        public static final int DVD_PBC = 121;
        public static final int DVD_PLAY_PAUSE = 110;
        public static final int DVD_PREV = 119;
        public static final int DVD_RDM = 118;
        public static final int DVD_RPT = 116;
        public static final int DVD_SLOW = 115;
        public static final int DVD_STOP = 113;
        public static final int DVD_TITLE = 111;
        public static final int DVD_ZOOM = 117;
        public static final int ENTER_EJECT_SWITCH = 101;
        public static final int MCU_TO_ARM_MESSAGE = 103;
        public static final int UPDATE_EXIST_STATUS = 104;
    }

    public static final class FmControlCommand {
        public static final int AF_CONTROL = 111;
        public static final int FREQ_CONTROL = 101;
        public static final int IR_NUMBER_CONTROL = 120;
        public static final int PTY_CONTROL = 113;
        public static final int SERACH_CONTROL_OFF = 103;
        public static final int SERACH_CONTROL_ON = 102;
        public static final int TA_CONTROL = 112;
        public static final int TA_OPEN = 105;
        public static final int TP_CONTROL = 114;
        public static final int TP_OPEN = 106;
    }

    public static final class KeyControlCommand {
        public static final int ENTER_LEARN_TP_KEY = 103;
        public static final int EXIT_LEARN_TP_KEY = 104;
        public static final int LEARNED_TP_KEY = 109;
        public static final int LEARNED_TP_KEY_REQUEST = 110;
        public static final String LEARNING_TP_KEY = "vendor.tp.learn.state";
        public static final int LEARNING_TP_KEY_TO_APP = 105;
        public static final int LEARNING_TP_KEY_TO_SERVICE = 106;
        public static final int LEARN_FK_KEY = 102;
        public static final int LEARN_TP_KEY = 101;
        public static final int LEARN_TP_KEY_RESET = 107;
        public static final int LEARN_TP_KEY_SAVE = 108;
    }

    public static final class MediaSubCommand {
        public static final int CLOSE_MUSIC = 106;
        public static final int CLOSE_PIP = 118;
        public static final int CLOSE_VIDEO = 112;
        public static final int MUSIC_LIST_CLOSE = 125;
        public static final int MUSIC_LIST_OPEN = 124;
        public static final int MUSIC_LOOP_ALL = 121;
        public static final int MUSIC_LOOP_NEXT = 126;
        public static final int MUSIC_LOOP_RANDOM = 123;
        public static final int MUSIC_LOOP_SINGLE = 122;
        public static final int MUSIC_NEXT = 101;
        public static final int MUSIC_PAUSE = 105;
        public static final int MUSIC_PLAY = 104;
        public static final int MUSIC_PLAYPAUSE = 103;
        public static final int MUSIC_PREVIOUS = 100;
        public static final int MUSIC_RANDOM = 120;
        public static final int PIP_NEXT = 114;
        public static final int PIP_PAUSE = 117;
        public static final int PIP_PLAY = 116;
        public static final int PIP_PLAYPAUSE = 115;
        public static final int PIP_PREVIOUS = 113;
        public static final int VIDEO_LOOP_NEXT = 127;
        public static final int VIDEO_NEXI = 108;
        public static final int VIDEO_PAUSE = 111;
        public static final int VIDEO_PLAY = 110;
        public static final int VIDEO_PLAYPAUSE = 109;
        public static final int VIDEO_PREVIOUS = 107;
        public static final int VIDEO_RANDOM = 128;
    }

    public static final class SystemCommand {
        public static final int ACCEPT_PHONE = 116;
        public static final int AIRCON_CONTROL = 612;
        public static final int AIR_DATA_REQ = 613;
        public static final int ANDROID_MODE = 602;
        public static final int AS_FM = 126;
        public static final int BACK = 115;
        public static final int BAND_FM = 128;
        public static final int BENZ_CONTROL = 801;
        public static final int CALL_BUTTON = 123;
        public static final int CAR_MODE = 601;
        public static final int CHECK_CAN_BOX = 611;
        public static final int CLOSE_FM = 124;
        public static final int DISMISS_POWER_VOLTAGE_DIALOG = 205;
        public static final int DORMANT = 118;
        public static final int EXPORT_CONFIG = 300;
        public static final int FM_KEY = 127;
        public static final int HANDLE_VOLD_SINGAL = 1002;
        public static final int HANDUP_PHONE = 117;
        public static final int HOME = 114;
        public static final int IMPORT_CONFIG = 220;
        public static final int INTERCEPT_KEY = 130;
        public static final int KEY_VOLTAGE = 702;
        public static final int LIGHT_CONTROL_COLOR = 701;
        public static final int LOC_SWITCH = 136;
        public static final int LOW_OR_HIGH_POWER_VOLTAGE_SHUTDOWN = 206;
        public static final int LOW_POWER_VOLTAGE_WARNING = 204;
        public static final int MCU_UPDATE = 700;
        public static final int MEDIA_NEXT = 104;
        public static final int MEDIA_PAUSE = 106;
        public static final int MEDIA_PLAY = 105;
        public static final int MEDIA_PLAY_PAUSE = 121;
        public static final int MEDIA_PREVIOUS = 103;
        public static final int MUTE = 100;
        public static final int MUTE_NAVI = 900;
        public static final int NEXT_DOT_SEEK_FM = 133;
        public static final int NEXT_FM = 120;
        public static final int NEXT_SEEK_FM = 131;
        public static final int OPEN_AUX = 605;
        public static final int OPEN_BT = 607;
        public static final int OPEN_CVBSDVR = 609;
        public static final int OPEN_DTV = 606;
        public static final int OPEN_FM = 110;
        public static final int OPEN_F_CAM = 610;
        public static final int OPEN_MODE = 604;
        public static final int OPEN_NAVI = 108;
        public static final int OPEN_SETTINGS = 111;
        public static final int OPEN_SPEECH = 109;
        public static final int OUT_MODE = 603;
        public static final int PREV_FM = 119;
        public static final int PRE_DOT_SEEK_FM = 134;
        public static final int PRE_SEEK_FM = 132;
        public static final int PS_FM = 125;
        public static final int REBOOT_GOCSDK = 1001;
        public static final int REBOOT_MACHINE = 207;
        public static final int SAVE_TP_ALIGN_DATA = 209;
        public static final int SCREEN_OFF = 113;
        public static final int SCREEN_ON = 112;
        public static final int SCREEN_SWITCH = 129;
        public static final int SET_PWM_DUTY_RATIO = 208;
        public static final int SOURCE_CHANGE = 107;
        public static final int ST_SWITCH = 135;
        public static final int SYSTEM_READY = 99;
        public static final int UPDATE_CONFIG = 200;
        public static final int UPDATE_FK_CONFIG = 210;
        public static final int UPDATE_MCU = 202;
        public static final int UPDATE_POWER_VOLTAGE = 203;
        public static final int UPDATE_SYSTEM = 201;
        public static final int USB_HOST = 122;
        public static final int USING_NAVI = 608;
        public static final int VOLUME_DOWN = 102;
        public static final int VOLUME_UP = 101;
    }

    public int getCommand() {
        return this.command;
    }

    public void setCommand(int command2) {
        this.command = command2;
    }

    public int getSubCommand() {
        return this.subCommand;
    }

    public void setSubCommand(int subCommand2) {
        this.subCommand = subCommand2;
    }

    public String getJsonArg() {
        return this.jsonArg;
    }

    public void setJsonArg(String jsonArg2) {
        this.jsonArg = jsonArg2;
    }

    public static WitsCommand getWitsCommandFormJson(String jsonArg2) {
        return (WitsCommand) new Gson().fromJson(jsonArg2, WitsCommand.class);
    }

    public WitsCommand(int command2, int subCommand2, String jsonArg2) {
        this.command = command2;
        this.subCommand = subCommand2;
        this.jsonArg = jsonArg2;
    }

    public WitsCommand(int command2, int subCommand2) {
        this.command = command2;
        this.subCommand = subCommand2;
    }

    public static void sendCommand(int command2, int subCommand2, String arg) {
        try {
            PowerManagerApp.getManager().sendCommand(new Gson().toJson((Object) new WitsCommand(command2, subCommand2, arg)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void sendCommandDelay(int command2, int subCommand2, String arg, long delayTime) {
        final long j = delayTime;
        final int i = command2;
        final int i2 = subCommand2;
        final String str = arg;
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(j);
                } catch (InterruptedException e) {
                }
                try {
                    PowerManagerApp.getManager().sendCommand(new Gson().toJson((Object) new WitsCommand(i, i2, str)));
                } catch (RemoteException e2) {
                }
            }
        }.start();
    }

    public static boolean sendCommandWithBack(int command2, int subCommand2, String arg) {
        try {
            PowerManagerApp.getManager().sendCommand(new Gson().toJson((Object) new WitsCommand(command2, subCommand2, arg)));
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void sendCommand(int command2, int subCommand2) {
        try {
            PowerManagerApp.getManager().sendCommand(new Gson().toJson((Object) new WitsCommand(command2, subCommand2, "")));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
