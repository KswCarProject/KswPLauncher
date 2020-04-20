package com.wits.ksw.launcher.bean;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.BitmapDrawable;

public class MediaInfo {
    public ObservableField<String> currentTime = new ObservableField<>();
    public ObservableInt maxProgress = new ObservableInt();
    public ObservableField<String> musicAlbum = new ObservableField<>();
    public ObservableField<String> musicAtist = new ObservableField<>();
    public ObservableField<String> musicName = new ObservableField<>();
    public ObservableField<BitmapDrawable> pic = new ObservableField<>();
    public ObservableInt progress = new ObservableInt();
    public ObservableField<String> totalTime = new ObservableField<>();

    public void setCurrentTime(String value) {
        this.currentTime.set(value);
    }

    public void setMusicAlbum(String value) {
        this.musicAlbum.set(value);
    }

    public void setMusicAtist(String value) {
        this.musicAtist.set(value);
    }

    public void setTotalTime(String value) {
        this.totalTime.set(value);
    }

    public void setMusicName(String value) {
        this.musicName.set(value);
    }

    public void setMaxProgress(int maxProgress2) {
        this.maxProgress.set(maxProgress2);
    }

    public void setProgress(int progress2) {
        this.progress.set(progress2);
    }

    public void setPic(BitmapDrawable data) {
        this.pic.set(data);
    }
}
