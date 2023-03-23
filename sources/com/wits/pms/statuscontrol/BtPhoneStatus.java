package com.wits.pms.statuscontrol;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public void setBtSwitch(boolean btSwitch2) {
        this.btSwitch = btSwitch2;
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

    public void setDevAddr(String devAddr2) {
        this.devAddr = devAddr2;
    }

    public int getCallStatus() {
        return this.callStatus;
    }

    public void setCallStatus(int callStatus2) {
        this.callStatus = callStatus2;
    }

    public int getVoiceStatus() {
        return this.voiceStatus;
    }

    public void setVoiceStatus(int voiceStatus2) {
        this.voiceStatus = voiceStatus2;
    }

    public BtPhoneStatus(boolean isConnected2, boolean isPlayingMusic2, String devAddr2, int callStatus2, int voiceStatus2, boolean btSwitch2) {
        this.isConnected = isConnected2;
        this.isPlayingMusic = isPlayingMusic2;
        this.devAddr = devAddr2;
        this.callStatus = callStatus2;
        this.voiceStatus = voiceStatus2;
        this.btSwitch = btSwitch2;
    }

    public BtPhoneStatus() {
    }

    public static BtPhoneStatus getStatusForJson(String jsonArg) {
        return (BtPhoneStatus) new Gson().fromJson(jsonArg, BtPhoneStatus.class);
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

    public static boolean isCalling(int callStatus2) {
        return callStatus2 >= 4 && callStatus2 <= 6;
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

    public static class PhoneBookBean {
        private String name;
        private String number;

        public PhoneBookBean(String name2, String number2) {
            this.name = name2;
            this.number = number2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name2) {
            this.name = name2;
        }

        public String getNumber() {
            return this.number;
        }

        public void setNumber(String number2) {
            this.number = number2;
        }
    }
}
