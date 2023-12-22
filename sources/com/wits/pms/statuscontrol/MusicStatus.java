package com.wits.pms.statuscontrol;

import android.text.TextUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MusicStatus {
    public static final int TYPE_MUSIC_STATUS = 21;
    public String mode;
    public String path;
    public boolean play;
    public int position;

    public MusicStatus(String path, String mode, boolean play, int position) {
        this.path = path;
        this.mode = mode;
        this.play = play;
        this.position = position;
    }

    public MusicStatus() {
    }

    public boolean isPlay() {
        return this.play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

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

    public static MusicStatus getStatusFromJson(String jsonArg) {
        return (MusicStatus) new Gson().fromJson(jsonArg, (Class<Object>) MusicStatus.class);
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
