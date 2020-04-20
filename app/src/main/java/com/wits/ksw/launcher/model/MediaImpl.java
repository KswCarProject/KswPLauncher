package com.wits.ksw.launcher.model;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class MediaImpl {
    private static final String TAG = MediaImpl.class.getName();
    private static volatile MediaImpl singleton;
    private MediaInfo mediaInfo = new MediaInfo();
    public MutableLiveData<MediaInfo> mediaInfoMutableLiveData = new MutableLiveData<>();
    private MediaMetadataRetriever metadataRetriever;
    private String musicPath;
    private Handler uiHandler = new Handler(Looper.getMainLooper());

    public static MediaImpl getInstance() {
        if (singleton == null) {
            synchronized (MediaImpl.class) {
                if (singleton == null) {
                    singleton = new MediaImpl();
                }
            }
        }
        return singleton;
    }

    public void initData() {
        try {
            String path = getMusicPathcString();
            if (!TextUtils.equals(path, this.musicPath)) {
                handleMediaPlaySeekbarAndCurrentime(getMusicPostion());
                handleMediaInfo(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "initData: " + e.getMessage());
            updataNull();
        }
    }

    private int getMusicPostion() throws Exception {
        return PowerManagerApp.getManager().getStatusInt("position");
    }

    private String getMusicPathcString() throws Exception {
        return PowerManagerApp.getManager().getStatusString("path");
    }

    private void updataNull() {
        this.mediaInfo.setMaxProgress(0);
        this.mediaInfo.setMusicName((String) null);
        this.mediaInfo.setMusicAlbum((String) null);
        this.mediaInfo.setMusicAtist((String) null);
        this.mediaInfo.setPic((BitmapDrawable) null);
        this.mediaInfo.setCurrentTime("0:00");
        this.mediaInfo.setTotalTime("0:00");
        getInstance().setMediaInfo(this.mediaInfo);
    }

    private void releaseMetadata() {
        if (this.metadataRetriever != null) {
            this.metadataRetriever.release();
            this.metadataRetriever = null;
        }
    }

    public MediaMetadataRetriever initMediaMetadataRetriever(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        releaseMetadata();
        if (this.metadataRetriever == null) {
            this.metadataRetriever = new MediaMetadataRetriever();
        }
        String str = TAG;
        Log.i(str, "initMediaMetadataRetriever: " + this.metadataRetriever);
        this.metadataRetriever.setDataSource(path);
        return this.metadataRetriever;
    }

    public void setMediaInfo(MediaInfo mediaInfo2) {
        this.mediaInfo = mediaInfo2;
        this.mediaInfoMutableLiveData.setValue(mediaInfo2);
    }

    public MediaInfo getMediaInfo() {
        return this.mediaInfo;
    }

    public void handleMediaInfo(String path) {
        this.musicPath = path;
        try {
            String str = TAG;
            Log.i(str, "handleMediaInfo: path:" + path);
            MediaMetadataRetriever metadataRetriever2 = initMediaMetadataRetriever(path);
            if (metadataRetriever2 == null) {
                Log.i(TAG, "handleMediaInfo: initMediaMetadataRetriever null");
                updataNull();
                return;
            }
            BitmapDrawable icon = null;
            String title = metadataRetriever2.extractMetadata(7);
            String album = metadataRetriever2.extractMetadata(1);
            String artist = metadataRetriever2.extractMetadata(2);
            String duration = metadataRetriever2.extractMetadata(9);
            byte[] pictur = metadataRetriever2.getEmbeddedPicture();
            if (pictur != null) {
                icon = new BitmapDrawable(BitmapFactory.decodeByteArray(pictur, 0, pictur.length));
            }
            final MediaInfo mediaInfo2 = getInstance().getMediaInfo();
            mediaInfo2.setMaxProgress(Integer.valueOf(duration).intValue());
            if (TextUtils.isEmpty(title)) {
                title = KswUtils.splitPathMusicName(path);
            }
            mediaInfo2.setMusicName(title);
            mediaInfo2.setMusicAlbum(album);
            mediaInfo2.setMusicAtist(artist);
            mediaInfo2.setPic(icon);
            if (TextUtils.isEmpty(duration)) {
                mediaInfo2.setTotalTime(KswUtils.formatMusicPlayTime(0));
            } else {
                mediaInfo2.setTotalTime(KswUtils.formatMusicPlayTime(Long.valueOf(duration).longValue()));
            }
            this.uiHandler.post(new Runnable() {
                public void run() {
                    MediaImpl.this.mediaInfoMutableLiveData.setValue(mediaInfo2);
                }
            });
            String str2 = TAG;
            Log.i(str2, "handleMediaInfo: title:" + title + " album:" + album + " artist:" + artist + " totalTime:" + duration);
        } catch (Exception e) {
            e.printStackTrace();
            String str3 = TAG;
            Log.e(str3, "onChange:" + e.getMessage());
        }
    }

    public void handleMediaPlaySeekbarAndCurrentime(int currentTime) {
        MediaInfo mediaInfo2 = getInstance().getMediaInfo();
        mediaInfo2.setProgress(currentTime);
        mediaInfo2.setCurrentTime(KswUtils.formatMusicPlayTime((long) currentTime));
    }
}
