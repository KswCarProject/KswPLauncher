package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.model.AppsLoaderTask;
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes11.dex */
public class IconUtils {
    private static IconUtils instance;
    public static String TAG = "IconUtils";

    /* renamed from: EQ */
    public static String f192EQ = BenzUtils.EQ_PKG;
    public static String SETTING = "com.android.settings";
    public static String GOOGLE_PLAY = BenzUtils.GOOGLE_PLAY;
    public static String GOOGLE_MAP = BenzUtils.GOOGLE_MAP;
    public static String CHROME = "com.android.chrome";
    public static String SHELL = "com.android.shell";
    public static String GOOGLE = BenzUtils.GOOGLE_SEARCH_PKG;
    public static String APK_INSTALL = "com.wits.apk";
    public static String ADAS = AppsLoaderTask.DVR;
    public static String AUTO_MAP = "com.autonavi.amapauto";
    public static String ESFILE = "com.estrongs.android.pop";
    public static String MX_PLAYER = "com.mxtech.videoplayer.ad";
    public static String ZLINK = "com.zjinnova.zlink";
    public static String DAB = "com.zoulou.dab";
    public static String EASYCONN = "net.easyconn";
    public static String VOICE_SETTING = "com.txznet.adapter";
    public static String IQIYI_HD = "com.qiyi.video.pad";
    public static String KUWO = "cn.kuwo";
    public static String NAVIKING = "com.kingwaytek";
    public static String GOOGLE_MUSIC = "com.google.android.music";
    public static String FOUR_TV = "tv.fourgtv.fourgtv";
    public static String CHAO_ZHU_YIN = "tw.chaozhuyin.paid";
    public static String WAZE = "com.waze";
    public static String FACEBOOK = "com.facebook.katana";
    public static String YOUTUBE = "com.google.android.youtube";
    public static String AUTOPLAY = "cn.manstep.phonemirrorBox";
    public static String ACROBAT = "com.adobe.reader";
    public static String KKBOX = "com.skysoft.kkbox.android";
    public static String MIXER_BOX = "mbinc12.mb32";
    public static String PURE_NAVI = "com.papago.s1OBU";

    private IconUtils() {
    }

    public static synchronized IconUtils getInstance() {
        IconUtils iconUtils;
        synchronized (IconUtils.class) {
            if (instance == null) {
                instance = new IconUtils();
            }
            iconUtils = instance;
        }
        return iconUtils;
    }

    public Drawable getIcon(String packName, AppInfo appInfo, Drawable drawable, String label) {
        if (packName.equals(f192EQ)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_equalizer);
        }
        if (packName.equals(SETTING)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_settings);
        }
        if (packName.equals(GOOGLE_PLAY)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_play_store);
        }
        if (packName.equals(GOOGLE_MAP)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_maps);
        }
        if (packName.equals(CHROME)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_chrome);
        }
        if (packName.equals(SHELL)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_user_feedback);
        }
        if (packName.equals(GOOGLE)) {
            if (label.equals("Google")) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_google);
            }
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_voice_search);
        } else if (packName.equals(APK_INSTALL)) {
            return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_apk_installer);
        } else {
            if (packName.equals(ADAS)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_adas);
            }
            if (packName.equals(AUTO_MAP)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_autonavi);
            }
            if (packName.equals(ESFILE)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_es_file_explorer);
            }
            if (packName.equals(ESFILE)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_es_file_explorer);
            }
            if (packName.equals(MX_PLAYER)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_mx_player);
            }
            if (packName.equals(ZLINK)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_zlink);
            }
            if (packName.equals(DAB)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_dab_z);
            }
            if (packName.equals(EASYCONN)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_easyconnection);
            }
            if (packName.equals(VOICE_SETTING)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_voice_settings);
            }
            if (packName.startsWith(KUWO)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_kuwo);
            }
            if (packName.startsWith(NAVIKING)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_naviking3d);
            }
            if (packName.equals(IQIYI_HD)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_iqiyi_hd);
            }
            if (packName.equals(GOOGLE_MUSIC)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_music);
            }
            if (packName.equals(FOUR_TV)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_fourgtv);
            }
            if (packName.equals(CHAO_ZHU_YIN)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_chaozhuyin_paid);
            }
            if (packName.equals(WAZE)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_waze);
            }
            if (packName.equals(FACEBOOK)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_facebook);
            }
            if (packName.equals(CHAO_ZHU_YIN)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_chaozhuyin_paid);
            }
            if (packName.equals(YOUTUBE)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_youtube);
            }
            if (packName.equals(AUTOPLAY)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_autoplay);
            }
            if (packName.equals(ACROBAT)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_acrobat_launcher);
            }
            if (packName.equals(KKBOX)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_kkbox);
            }
            if (packName.equals(PURE_NAVI)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_purenavi_s1);
            }
            if (packName.equals(MIXER_BOX)) {
                return KswApplication.appContext.getResources().getDrawable(C0899R.C0900drawable.com_mixer_box);
            }
            return compositeDrawable(drawable);
        }
    }

    public int getIconSelect() {
        try {
            return PowerManagerApp.getSettingsInt("AppsIcon_Select");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "getClient: " + e.getMessage());
            return 0;
        }
    }

    public boolean isRoundStyle() {
        return getIconSelect() == 1;
    }

    public Drawable compositeDrawable(Drawable in) {
        Bitmap secondBitmap = scaleBitmap(DrawableToBitmap(in), dip2px(KswApplication.appContext, 100.0f), dip2px(KswApplication.appContext, 100.0f));
        Bitmap firstBitmap = BitmapFactory.decodeResource(KswApplication.appContext.getResources(), C0899R.C0900drawable.share);
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(), firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, (firstBitmap.getWidth() - secondBitmap.getWidth()) / 2, (firstBitmap.getWidth() - secondBitmap.getHeight()) / 2, (Paint) null);
        BitmapDrawable drawable = new BitmapDrawable(KswApplication.appContext.getResources(), bitmap);
        return drawable;
    }

    public int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, true);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    public static Bitmap DrawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int heigh = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, width, heigh);
        Bitmap.Config config = drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, heigh, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }
}
