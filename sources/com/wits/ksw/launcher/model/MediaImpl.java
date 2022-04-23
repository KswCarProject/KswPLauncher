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
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.utils.BitmapUtil;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
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
            int postion = getMusicPostion();
            boolean isPlay = getMusicPlayState();
            Log.d(TAG, "initDAta isplay =" + isPlay + " isstop =" + isMusicStop());
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

    public boolean getMusicPlayState() throws Exception {
        return PowerManagerApp.getManager().getStatusBoolean("play");
    }

    private boolean isMusicStop() throws Exception {
        return PowerManagerApp.getManager().getStatusBoolean("music_stop");
    }

    public void handleMediaPlayState(boolean play, boolean stop) {
        MediaInfo mediaInfo2 = getInstance().getMediaInfo();
        if (stop) {
            mediaInfo2.setMusicPlay(stop);
        } else {
            mediaInfo2.setMusicPlay(play);
        }
    }

    private void updataNull() {
        this.mediaInfo.setMaxProgress(0);
        this.mediaInfo.setMusicName((String) null);
        this.mediaInfo.setMusicAlbum((String) null);
        this.mediaInfo.setMusicAtist((String) null);
        this.mediaInfo.setPic((BitmapDrawable) null);
        this.mediaInfo.setCurrentTime("0:00");
        this.mediaInfo.setTotalTime("0:00");
        this.mediaInfo.setMusicPlay(false);
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
                Log.i(str, "handleMediaInfo: initMediaMetadataRetriever null");
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
            if (!TextUtils.isEmpty(artist)) {
                mediaInfo2.songInfo.set(title + " - " + artist);
            } else {
                mediaInfo2.songInfo.set(title);
            }
            mediaInfo2.setPic(icon);
            if (icon == null) {
                LOGE.D("liuhaoMedia ____________________aaa BitmapUtil.isBenzMbux2021()&&icon==null__________________________________");
                mediaInfo2.setPicBg((BitmapDrawable) BitmapUtil.getDefaultMBUX2021BG_OTHER());
                mediaInfo2.setPicZoom((BitmapDrawable) null);
            } else {
                mediaInfo2.setPicZoom((BitmapDrawable) zoomDrawable(icon, 100, 100));
                Bitmap bmp = BitmapUtil.drawableToBitmap(icon);
                if (bmp == null) {
                    bmp = BitmapUtil.drawableToBitmap(BitmapUtil.getDefaultMBUX2021BG_OTHER());
                }
                mediaInfo2.setPicBg(new BitmapDrawable(bmp));
            }
            mediaInfo2.setPageIndex(mediaInfo2.pageIndex.get());
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
            Log.i(str, "handleMediaInfo: title:" + title + " album:" + album + " artist:" + artist + " totalTime:" + duration);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onChange:" + e.getMessage());
        }
    }

    public void handleMediaPlaySeekbarAndCurrentime(int currentTime) {
        MediaInfo mediaInfo2 = getInstance().getMediaInfo();
        mediaInfo2.setProgress(currentTime);
        mediaInfo2.setCurrentTime(KswUtils.formatMusicPlayTime((long) currentTime));
    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        float width = (float) bgimage.getWidth();
        float height = (float) bgimage.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) newWidth) / width, ((float) newHeight) / height);
        return Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
    }

    public static Bitmap getShapeBitmap(Bitmap bitmap) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        new ColorMatrix().set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 20.0f, 0.0f, 1.0f, 0.0f, 0.0f, 20.0f, 0.0f, 0.0f, 1.0f, 0.0f, 20.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        return bmp;
    }

    private Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        matrix.postScale(((float) w) / ((float) width), ((float) h) / ((float) height));
        return new BitmapDrawable((Resources) null, Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true));
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap.Config config;
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        if (drawable.getOpacity() != -1) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.RGB_565;
        }
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
