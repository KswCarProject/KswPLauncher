package com.wits.pms.statuscontrol;

import android.text.TextUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MusicStatus {
    public static final int TYPE_MUSIC_STATUS = 21;
    public String mode;
    public String path;
    public boolean play;
    public int position;

    public MusicStatus(String path2, String mode2, boolean play2, int position2) {
        this.path = path2;
        this.mode = mode2;
        this.play = play2;
        this.position = position2;
    }

    public MusicStatus() {
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

    public static MusicStatus getStatusFromJson(String jsonArg) {
        return (MusicStatus) new Gson().fromJson(jsonArg, MusicStatus.class);
    }

    public List<String> compare(MusicStatus musicStatus) {
        List<String> keys = new ArrayList<>();
        if (!TextUtils.isEmpty(this.path) && !this.path.equals(musicStatus.path)) {
            keys.add("path");
        }
        if (!TextUtils.isEmpty(this.mode) && !this.path.equals(musicStatus.mode)) {
            keys.add("mode");
        }
        if (this.play != musicStatus.play) {
            keys.add("play");
        }
        if (this.position != musicStatus.position) {
            keys.add("position");
        }
        return keys;
    }
}
