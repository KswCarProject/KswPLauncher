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
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class IconUtils {
    public static String ACROBAT = "com.adobe.reader";
    public static String ADAS = "com.ankai.cardvr";
    public static String APK_INSTALL = "com.wits.apk";
    public static String AUTOPLAY = "cn.manstep.phonemirrorBox";
    public static String AUTO_MAP = "com.autonavi.amapauto";
    public static String CHAO_ZHU_YIN = "tw.chaozhuyin.paid";
    public static String CHROME = "com.android.chrome";
    public static String DAB = "com.zoulou.dab";
    public static String EASYCONN = "net.easyconn";
    public static String EQ = "com.wits.csp.eq";
    public static String ESFILE = "com.estrongs.android.pop";
    public static String FACEBOOK = "com.facebook.katana";
    public static String FOUR_TV = "tv.fourgtv.fourgtv";
    public static String GOOGLE = "com.google.android.googlequicksearchbox";
    public static String GOOGLE_MAP = "com.google.android.apps.maps";
    public static String GOOGLE_MUSIC = "com.google.android.music";
    public static String GOOGLE_PLAY = "com.android.vending";
    public static String IQIYI_HD = "com.qiyi.video.pad";
    public static String KKBOX = "com.skysoft.kkbox.android";
    public static String KUWO = "cn.kuwo";
    public static String MIXER_BOX = "mbinc12.mb32";
    public static String MX_PLAYER = "com.mxtech.videoplayer.ad";
    public static String NAVIKING = "com.kingwaytek";
    public static String PURE_NAVI = "com.papago.s1OBU";
    public static String SETTING = "com.android.settings";
    public static String SHELL = "com.android.shell";
    public static String TAG = "IconUtils";
    public static String VOICE_SETTING = "com.txznet.adapter";
    public static String WAZE = "com.waze";
    public static String YOUTUBE = "com.google.android.youtube";
    public static String ZLINK = "com.zjinnova.zlink";
    private static IconUtils instance;

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
        if (packName.equals(EQ)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_equalizer);
        }
        if (packName.equals(SETTING)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_settings);
        }
        if (packName.equals(GOOGLE_PLAY)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_play_store);
        }
        if (packName.equals(GOOGLE_MAP)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_maps);
        }
        if (packName.equals(CHROME)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_chrome);
        }
        if (packName.equals(SHELL)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_user_feedback);
        }
        if (packName.equals(GOOGLE)) {
            if (label.equals("Google")) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_google);
            }
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_voice_search);
        } else if (packName.equals(APK_INSTALL)) {
            return KswApplication.appContext.getResources().getDrawable(R.drawable.com_apk_installer);
        } else {
            if (packName.equals(ADAS)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_adas);
            }
            if (packName.equals(AUTO_MAP)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_autonavi);
            }
            if (packName.equals(ESFILE)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_es_file_explorer);
            }
            if (packName.equals(ESFILE)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_es_file_explorer);
            }
            if (packName.equals(MX_PLAYER)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_mx_player);
            }
            if (packName.equals(ZLINK)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_zlink);
            }
            if (packName.equals(DAB)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_dab_z);
            }
            if (packName.equals(EASYCONN)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_easyconnection);
            }
            if (packName.equals(VOICE_SETTING)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_voice_settings);
            }
            if (packName.startsWith(KUWO)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_kuwo);
            }
            if (packName.startsWith(NAVIKING)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_naviking3d);
            }
            if (packName.equals(IQIYI_HD)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_iqiyi_hd);
            }
            if (packName.equals(GOOGLE_MUSIC)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_music);
            }
            if (packName.equals(FOUR_TV)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_fourgtv);
            }
            if (packName.equals(CHAO_ZHU_YIN)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_chaozhuyin_paid);
            }
            if (packName.equals(WAZE)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_waze);
            }
            if (packName.equals(FACEBOOK)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_facebook);
            }
            if (packName.equals(CHAO_ZHU_YIN)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_chaozhuyin_paid);
            }
            if (packName.equals(YOUTUBE)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_youtube);
            }
            if (packName.equals(AUTOPLAY)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_autoplay);
            }
            if (packName.equals(ACROBAT)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_acrobat_launcher);
            }
            if (packName.equals(KKBOX)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_kkbox);
            }
            if (packName.equals(PURE_NAVI)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_purenavi_s1);
            }
            if (packName.equals(MIXER_BOX)) {
                return KswApplication.appContext.getResources().getDrawable(R.drawable.com_mixer_box);
            }
            return compositeDrawable(drawable);
        }
    }

    public int getIconSelect() {
        try {
            return PowerManagerApp.getSettingsInt("AppsIcon_Select");
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            Log.e(str, "getClient: " + e.getMessage());
            return 0;
        }
    }

    public boolean isRoundStyle() {
        if (getIconSelect() == 1) {
            return true;
        }
        return false;
    }

    public Drawable compositeDrawable(Drawable in) {
        Bitmap secondBitmap = scaleBitmap(DrawableToBitmap(in), dip2px(KswApplication.appContext, 100.0f), dip2px(KswApplication.appContext, 100.0f));
        Bitmap firstBitmap = BitmapFactory.decodeResource(KswApplication.appContext.getResources(), R.drawable.share);
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(), firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.drawBitmap(firstBitmap, new Matrix(), (Paint) null);
        canvas.drawBitmap(secondBitmap, (float) ((firstBitmap.getWidth() - secondBitmap.getWidth()) / 2), (float) ((firstBitmap.getWidth() - secondBitmap.getHeight()) / 2), (Paint) null);
        return new BitmapDrawable(KswApplication.appContext.getResources(), bitmap);
    }

    public int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) newWidth) / ((float) width), ((float) newHeight) / ((float) height));
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
        Bitmap bitmap = Bitmap.createBitmap(width, heigh, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        drawable.draw(new Canvas(bitmap));
        return bitmap;
    }
}
