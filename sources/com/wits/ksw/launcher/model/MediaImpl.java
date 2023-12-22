package com.wits.ksw.launcher.model;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Looper;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.utils.BitmapUtil;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.VideoStatus;

/* loaded from: classes9.dex */
public class MediaImpl {
    private static final String TAG = MediaImpl.class.getName();
    private static volatile MediaImpl singleton;
    private MediaMetadataRetriever metadataRetriever;
    private String musicPath;
    private String videoPath;
    private MediaInfo mediaInfo = new MediaInfo();
    public MutableLiveData<MediaInfo> mediaInfoMutableLiveData = new MutableLiveData<>();
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
            int postion = getMusicPostion();
            boolean isPlay = getMusicPlayState();
            boolean isStop = isMusicStop();
            Log.d(TAG, "initDAta isplay =" + isPlay + " isstop =" + isStop);
            if (!TextUtils.equals(path, this.musicPath)) {
                handleMediaPlaySeekbarAndCurrentime(postion);
                handleMediaInfo(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "initData: " + e.getMessage());
            updataNull();
        }
    }

    private int getMusicPostion() throws Exception {
        return PowerManagerApp.getManager().getStatusInt("position");
    }

    private String getMusicPathcString() throws Exception {
        return PowerManagerApp.getManager().getStatusString("path");
    }

    private String getVideoPathString() throws Exception {
        return PowerManagerApp.getManager().getStatusString("videoPath");
    }

    private boolean getVideoPlayState() throws Exception {
        return PowerManagerApp.getManager().getStatusBoolean("videoPlay");
    }

    public boolean getMusicPlayState() throws Exception {
        return PowerManagerApp.getManager().getStatusBoolean("play");
    }

    private boolean isMusicStop() throws Exception {
        return PowerManagerApp.getManager().getStatusBoolean("music_stop");
    }

    public void handleMediaPlayState(boolean play, boolean stop) {
        MediaInfo mediaInfo = getInstance().getMediaInfo();
        if (stop) {
            mediaInfo.setMusicPlay(stop);
        } else {
            mediaInfo.setMusicPlay(play);
        }
    }

    private void updataNull() {
        this.mediaInfo.setMaxProgress(0);
        this.mediaInfo.setMusicName(null);
        this.mediaInfo.setMusicAlbum(null);
        this.mediaInfo.setMusicAtist(null);
        this.mediaInfo.setPic(null);
        this.mediaInfo.setCurrentTime("0:00");
        this.mediaInfo.setTotalTime("0:00");
        this.mediaInfo.setMusicPlay(false);
        this.mediaInfo.setMaxProgressVideo(0);
        this.mediaInfo.setCurrentTimeVideo("0:00");
        this.mediaInfo.setTotalTimeVideo("0:00");
        this.mediaInfo.setVideoPlay(false);
        getInstance().setMediaInfo(this.mediaInfo);
    }

    private void releaseMetadata() {
        MediaMetadataRetriever mediaMetadataRetriever = this.metadataRetriever;
        if (mediaMetadataRetriever != null) {
            mediaMetadataRetriever.release();
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
        Log.i(TAG, "initMediaMetadataRetriever: " + this.metadataRetriever);
        this.metadataRetriever.setDataSource(path);
        return this.metadataRetriever;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
        this.mediaInfoMutableLiveData.setValue(mediaInfo);
    }

    public MediaInfo getMediaInfo() {
        return this.mediaInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0127 A[Catch: Exception -> 0x0180, TryCatch #0 {Exception -> 0x0180, blocks: (B:6:0x000b, B:8:0x0029, B:10:0x0032, B:12:0x004e, B:13:0x005a, B:15:0x0073, B:16:0x0078, B:18:0x0087, B:20:0x00a9, B:22:0x00b1, B:24:0x00b9, B:26:0x00c1, B:29:0x00ca, B:31:0x00cf, B:40:0x0114, B:42:0x0121, B:44:0x0127, B:46:0x0140, B:45:0x0131, B:32:0x00e6, B:34:0x00ee, B:36:0x00fd, B:38:0x0103, B:39:0x010c, B:35:0x00f2, B:41:0x011e, B:19:0x00a4), top: B:51:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0131 A[Catch: Exception -> 0x0180, TryCatch #0 {Exception -> 0x0180, blocks: (B:6:0x000b, B:8:0x0029, B:10:0x0032, B:12:0x004e, B:13:0x005a, B:15:0x0073, B:16:0x0078, B:18:0x0087, B:20:0x00a9, B:22:0x00b1, B:24:0x00b9, B:26:0x00c1, B:29:0x00ca, B:31:0x00cf, B:40:0x0114, B:42:0x0121, B:44:0x0127, B:46:0x0140, B:45:0x0131, B:32:0x00e6, B:34:0x00ee, B:36:0x00fd, B:38:0x0103, B:39:0x010c, B:35:0x00f2, B:41:0x011e, B:19:0x00a4), top: B:51:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void handleMediaInfo(String path) {
        if (path.equals(this.musicPath)) {
            return;
        }
        this.musicPath = path;
        try {
            String str = TAG;
            Log.i(str, "handleMediaInfo: path:" + path);
            MediaMetadataRetriever metadataRetriever = initMediaMetadataRetriever(path);
            if (metadataRetriever == null) {
                Log.i(str, "handleMediaInfo: initMediaMetadataRetriever null");
                updataNull();
                return;
            }
            BitmapDrawable icon = null;
            String title = metadataRetriever.extractMetadata(7);
            String album = metadataRetriever.extractMetadata(1);
            String artist = metadataRetriever.extractMetadata(2);
            String duration = metadataRetriever.extractMetadata(9);
            byte[] pictur = metadataRetriever.getEmbeddedPicture();
            if (pictur != null) {
                icon = new BitmapDrawable(BitmapFactory.decodeByteArray(pictur, 0, pictur.length));
            }
            final MediaInfo mediaInfo = getInstance().getMediaInfo();
            mediaInfo.setMaxProgress(Integer.valueOf(duration).intValue());
            if (TextUtils.isEmpty(title)) {
                title = KswUtils.splitPathMusicName(path);
            }
            mediaInfo.setMusicName(title);
            mediaInfo.setMusicAlbum(album);
            mediaInfo.setMusicAtist(artist);
            if (!TextUtils.isEmpty(artist)) {
                mediaInfo.songInfo.set(title + " - " + artist);
            } else {
                mediaInfo.songInfo.set(title);
            }
            if (!UiThemeUtils.isUI_MBUX_2021_KSW_1024(MainActivity.mainActivity) && !UiThemeUtils.isUI_MBUX_2021_KSW_1024_V2(MainActivity.mainActivity) && !UiThemeUtils.isBenz_MBUX_2021_KSW(MainActivity.mainActivity) && !UiThemeUtils.isBenz_MBUX_2021_KSW_V2(MainActivity.mainActivity)) {
                mediaInfo.setPic(icon);
                if (icon == null) {
                    LOGE.m44D("liuhaoMedia ____________________aaa BitmapUtil.isBenzMbux2021()&&icon==null__________________________________");
                    BitmapDrawable icon2 = (BitmapDrawable) BitmapUtil.getDefaultMBUX2021BG_OTHER();
                    mediaInfo.setPicBg(icon2);
                    mediaInfo.setPicZoom(null);
                    mediaInfo.setPic(null);
                } else {
                    if (UiThemeUtils.isBenz_MBUX_2021(MainActivity.mainActivity)) {
                        mediaInfo.setPicZoom(icon);
                    } else {
                        mediaInfo.setPicZoom((BitmapDrawable) zoomDrawable(icon, 100, 100));
                    }
                    Bitmap bmp = BitmapUtil.drawableToBitmap(icon);
                    if (bmp == null) {
                        bmp = BitmapUtil.drawableToBitmap(BitmapUtil.getDefaultMBUX2021BG_OTHER());
                    }
                    mediaInfo.setPicBg(new BitmapDrawable(bmp));
                }
                mediaInfo.setPageIndex(mediaInfo.pageIndex.get());
                if (!TextUtils.isEmpty(duration)) {
                    mediaInfo.setTotalTime(KswUtils.formatMusicPlayTime(0L));
                } else {
                    mediaInfo.setTotalTime(KswUtils.formatMusicPlayTime(Long.valueOf(duration).longValue()));
                }
                this.uiHandler.post(new Runnable() { // from class: com.wits.ksw.launcher.model.MediaImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaImpl.this.mediaInfoMutableLiveData.setValue(mediaInfo);
                    }
                });
                Log.i(str, "handleMediaInfo: title:" + title + " album:" + album + " artist:" + artist + " totalTime:" + duration);
            }
            mediaInfo.setPicZoom(icon);
            if (!TextUtils.isEmpty(duration)) {
            }
            this.uiHandler.post(new Runnable() { // from class: com.wits.ksw.launcher.model.MediaImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaImpl.this.mediaInfoMutableLiveData.setValue(mediaInfo);
                }
            });
            Log.i(str, "handleMediaInfo: title:" + title + " album:" + album + " artist:" + artist + " totalTime:" + duration);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onChange:" + e.getMessage());
        }
    }

    public void handleVideoInfoSetPlayStatus(VideoStatus videoStatus, String path) {
        if (videoStatus == null) {
            return;
        }
        Log.w(TAG, "handleVideoInfoSetPlayStatus:" + videoStatus.getTotalTime());
        MediaInfo mediaInfo = getInstance().getMediaInfo();
        mediaInfo.setVideoPlay(videoStatus.isPlay());
        mediaInfo.setMaxProgressVideo(videoStatus.getTotalTime());
        mediaInfo.setProgressVideo(videoStatus.getPosition());
        String title = KswUtils.splitPathMusicName(path);
        mediaInfo.setVideoName(title);
        Log.d("TAG", "VideoTitle:" + title);
        if (videoStatus.getTotalTime() <= 0) {
            mediaInfo.progressPercentVideo.set(0);
        } else {
            float percent = (videoStatus.getPosition() * 1.0f) / videoStatus.getTotalTime();
            mediaInfo.progressPercentVideo.set(Math.round(100.0f * percent));
        }
        mediaInfo.setTotalTimeVideo(KswUtils.formatMusicPlayTime(videoStatus.getTotalTime()));
        mediaInfo.setCurrentTimeVideo(KswUtils.formatMusicPlayTime(videoStatus.getPosition()));
    }

    public void handleMediaPlaySeekbarAndCurrentime(int currentTime) {
        try {
            MediaInfo mediaInfo = getInstance().getMediaInfo();
            mediaInfo.setProgress(currentTime);
            mediaInfo.setCurrentTime(KswUtils.formatMusicPlayTime(currentTime));
            setProgressPercent(mediaInfo);
            mediaInfo.setRemainTime("-" + KswUtils.formatMusicPlayTimeRemain(currentTime, mediaInfo.maxProgress.get()));
            String hightLrcRow = PowerManagerApp.getManager().getStatusString("LrcRow_HighLightContent");
            mediaInfo.setHightLrcRow(hightLrcRow);
            Log.e("lixin_test", "handleMediaPlaySeekbarAndCurrentime: hightLrcRow " + hightLrcRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProgressPercent(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return;
        }
        if (mediaInfo.maxProgress.get() <= 0) {
            mediaInfo.progressPercent.set(0);
            return;
        }
        float percent = (mediaInfo.progress.get() * 1.0f) / mediaInfo.maxProgress.get();
        mediaInfo.progressPercent.set(Math.round(100.0f * percent));
    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    public static Bitmap getShapeBitmap(Bitmap bitmap) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 20.0f, 0.0f, 1.0f, 0.0f, 0.0f, 20.0f, 0.0f, 0.0f, 1.0f, 0.0f, 20.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        return bmp;
    }

    private Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = w / width;
        float scaleHeight = h / height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
        return new BitmapDrawable((Resources) null, newbmp);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        blurScript.setRadius(25.0f);
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        allOut.copyTo(outBitmap);
        bitmap.recycle();
        rs.destroy();
        return outBitmap;
    }
}
