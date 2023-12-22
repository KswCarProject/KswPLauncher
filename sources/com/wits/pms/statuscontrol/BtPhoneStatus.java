package com.wits.pms.statuscontrol;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BtPhoneStatus {
    public static final int CALL_CALLOUT = 4;
    public static final int CALL_HANDUP = 7;
    public static final int CALL_INCOMING = 5;
    public static final int CALL_TALKING = 6;
    public static final int TYPE_BT_STATUS = 3;
    public static final int VOICE_CAR_SYSTEM_VOICE = 0;
    public static final int VOICE_CUSTOM_PHONE_VOICE = 1;
    public boolean btSwitch;
    public int callStatus;
    public String devAddr;
    public boolean isConnected;
    public boolean isPlayingMusic;
    public int voiceStatus;

    public boolean isConnected() {
        return this.isConnected;
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    public void setBtSwitch(boolean btSwitch) {
        this.btSwitch = btSwitch;
    }

    public boolean isBtSwitch() {
        return this.btSwitch;
    }

    public boolean isPlayingMusic() {
        return this.isPlayingMusic;
    }

    public void setPlayingMusic(boolean playingMusic) {
        this.isPlayingMusic = playingMusic;
    }

    public String getDevAddr() {
        return this.devAddr;
    }

    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
    }

    public int getCallStatus() {
        return this.callStatus;
    }

    public void setCallStatus(int callStatus) {
        this.callStatus = callStatus;
    }

    public int getVoiceStatus() {
        return this.voiceStatus;
    }

    public void setVoiceStatus(int voiceStatus) {
        this.voiceStatus = voiceStatus;
    }

    public BtPhoneStatus(boolean isConnected, boolean isPlayingMusic, String devAddr, int callStatus, int voiceStatus, boolean btSwitch) {
        this.isConnected = isConnected;
        this.isPlayingMusic = isPlayingMusic;
        this.devAddr = devAddr;
        this.callStatus = callStatus;
        this.voiceStatus = voiceStatus;
        this.btSwitch = btSwitch;
    }

    public BtPhoneStatus() {
    }

    public static BtPhoneStatus getStatusForJson(String jsonArg) {
        return (BtPhoneStatus) new Gson().fromJson(jsonArg, (Class<Object>) BtPhoneStatus.class);
    }

    public List<String> compare(BtPhoneStatus btPhoneStatus) {
        List<String> keys = new ArrayList<>();
        if (this.btSwitch != btPhoneStatus.btSwitch) {
            keys.add("btSwitch");
        }
        if (this.isConnected != btPhoneStatus.isConnected) {
            keys.add("isConnected");
        }
        if (this.isPlayingMusic != btPhoneStatus.isPlayingMusic) {
            keys.add("isPlayingMusic");
        }
        if (this.callStatus != btPhoneStatus.callStatus) {
            keys.add("callStatus");
        }
        String str = this.devAddr;
        if (str != null && !str.equals(btPhoneStatus.devAddr)) {
            keys.add("devAddr");
        }
        if (this.voiceStatus != btPhoneStatus.voiceStatus) {
            keys.add("voiceStatus");
        }
        return keys;
    }

    public static boolean isCalling(int callStatus) {
        return callStatus >= 4 && callStatus <= 6;
    }

    public static List<PhoneBookBean> getContactsByJson(String json) {
        try {
            List<PhoneBookBean> contacts = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                contacts.add(new PhoneBookBean(jsonObject.getString("name"), jsonObject.getString("number")));
            }
            return contacts;
        } catch (Exception e) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class PhoneBookBean {
        private String name;
        private String number;

        public PhoneBookBean(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
