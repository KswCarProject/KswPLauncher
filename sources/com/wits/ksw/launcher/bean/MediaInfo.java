package com.wits.ksw.launcher.bean;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.wits.ksw.launcher.utils.BitmapUtil;

/* loaded from: classes9.dex */
public class MediaInfo {
    public ObservableInt maxProgress = new ObservableInt();
    public ObservableInt maxProgressVideo = new ObservableInt();
    public ObservableInt progress = new ObservableInt();
    public ObservableInt progressVideo = new ObservableInt();
    public ObservableInt progressPercent = new ObservableInt();
    public ObservableInt progressPercentVideo = new ObservableInt();
    public ObservableInt pageIndex = new ObservableInt();
    public ObservableField<BitmapDrawable> pic = new ObservableField<>();
    public ObservableField<BitmapDrawable> picZoom = new ObservableField<>();
    public ObservableField<BitmapDrawable> picBg = new ObservableField<>();
    public ObservableField<BitmapDrawable> picOne = new ObservableField<>();
    public ObservableField<BitmapDrawable> picOther = new ObservableField<>();
    public ObservableField<String> musicName = new ObservableField<>();
    public ObservableField<String> musicAtist = new ObservableField<>();
    public ObservableField<String> musicAlbum = new ObservableField<>();
    public ObservableField<String> currentTime = new ObservableField<>();
    public ObservableField<String> currentTimeVideo = new ObservableField<>();
    public ObservableField<String> remainTime = new ObservableField<>();
    public ObservableField<String> songInfo = new ObservableField<>();

    /* renamed from: BG */
    public ObservableField<Drawable> f191BG = new ObservableField<>();
    public ObservableField<String> totalTime = new ObservableField<>();
    public ObservableField<String> totalTimeVideo = new ObservableField<>();
    public ObservableField<Boolean> musicPlay = new ObservableField<>();
    public ObservableField<Boolean> musicStop = new ObservableField<>();
    public ObservableField<BitmapDrawable> videoPic = new ObservableField<>();
    public ObservableField<Boolean> videoPlay = new ObservableField<>();
    public ObservableField<String> videoName = new ObservableField<>();
    public ObservableField<BitmapDrawable> videoEditorPic = new ObservableField<>();
    public ObservableField<Boolean> videoPIP = new ObservableField<>();
    public ObservableField<String> hightLrcRow = new ObservableField<>();

    public void setHightLrcRow(String lrcRow) {
        this.hightLrcRow.set(lrcRow);
    }

    public void setVideoPIP(boolean videoPip) {
        this.videoPIP.set(Boolean.valueOf(videoPip));
    }

    public void setVideoPlay(boolean videoPlay) {
        this.videoPlay.set(Boolean.valueOf(videoPlay));
    }

    public void setVideoName(String fileName) {
        this.videoName.set(fileName);
    }

    public void setVideoPic(BitmapDrawable videoPic) {
        this.videoPic.set(videoPic);
    }

    public void setVideoEditorPic(BitmapDrawable videoPic) {
        this.videoEditorPic.set(videoPic);
    }

    public void setMusicStop(boolean value) {
        this.musicStop.set(Boolean.valueOf(value));
    }

    public void setMusicPlay(boolean value) {
        this.musicPlay.set(Boolean.valueOf(value));
    }

    public void setCurrentTime(String value) {
        this.currentTime.set(value);
    }

    public void setCurrentTimeVideo(String value) {
        this.currentTimeVideo.set(value);
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

    public void setTotalTimeVideo(String value) {
        this.totalTimeVideo.set(value);
    }

    public void setMusicName(String value) {
        this.musicName.set(value);
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress.set(maxProgress);
    }

    public void setMaxProgressVideo(int maxProgress) {
        this.maxProgressVideo.set(maxProgress);
    }

    public void setProgress(int progress) {
        this.progress.set(progress);
    }

    public void setProgressVideo(int progress) {
        this.progressVideo.set(progress);
    }

    public void setPic(BitmapDrawable data) {
        this.pic.set(data);
    }

    public void setPicZoom(BitmapDrawable data) {
        this.picZoom.set(data);
    }

    public void setPicBg(BitmapDrawable data) {
        this.picBg.set(data);
    }

    public void setPicOne(BitmapDrawable data) {
        this.picOne.set(data);
    }

    public void setPicOther(BitmapDrawable data) {
        this.picOther.set(data);
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex.set(pageIndex);
        if (pageIndex == 1) {
            if (this.pic.get() != null) {
                this.f191BG.set(this.picBg.get() == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : this.picBg.get());
            } else {
                this.f191BG.set(BitmapUtil.getDefaultMBUX2021BG_OTHER());
            }
        } else if (pageIndex == 0) {
            this.f191BG.set(this.picOne.get() == null ? BitmapUtil.getDefaultMBUX2021BG_ONE() : this.picOne.get());
        } else {
            this.f191BG.set(this.picOther.get() == null ? BitmapUtil.getDefaultMBUX2021BG_OTHER() : this.picOther.get());
        }
    }

    public void setRemainTime(String remainTime) {
        this.remainTime.set(remainTime);
    }
}
