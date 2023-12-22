package com.wits.ksw.launcher.utils;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import com.ibm.icu.text.DateFormat;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes11.dex */
public class KswUtils {
    public static final double RPH = 0.621d;
    private static final String TAG = "KswApplication." + KswUtils.class.getSimpleName();
    private static volatile KswUtils singleton;

    private KswUtils() {
    }

    public static KswUtils getInstance() {
        if (singleton == null) {
            synchronized (KswUtils.class) {
                if (singleton == null) {
                    singleton = new KswUtils();
                }
            }
        }
        return singleton;
    }

    public static int calculateTranslate(int top, int h, int i, Context context) {
        Log.d("calculateTranslate", " top: " + top + " h " + h + " index " + i);
        int h2 = h - dip2px(context, 71.3f);
        int result = Math.abs(h2 - top);
        int result2 = (h2 - result) / 6;
        if (i == 1) {
            result2 = (top * 20) / 107;
        } else if (i == 2) {
            result2 = (((top - 107) * 18) / 107) + 20;
        } else if (i == 3) {
            result2 = (((top - 214) * 14) / 107) + 38;
        } else if (i == 4) {
            result2 = (((top - 321) * 8) / 107) + 52;
        } else if (i == 5 || i == 6) {
            result2 = (((top - 428) * 5) / 107) + 60;
        }
        Log.d("calculateTranslate", " result " + result2);
        return result2;
    }

    public static int calculateTranslate2(int top, int h, int i, Context context) {
        Log.d("calculateTranslate", " top: " + top + " h " + h + " index " + i);
        int h2 = h - dip2px(context, 71.3f);
        int result = Math.abs(h2 - top);
        int result2 = (h2 - result) / 6;
        if (i != 0) {
            if (i == 1) {
                result2 = (top * 23) / 107;
            } else if (i == 2) {
                result2 = (((top - 107) * 16) / 107) + 23;
            } else if (i == 3) {
                result2 = (((top - 214) * 17) / 107) + 39;
            } else if (i == 4) {
                result2 = (((top - 321) * 11) / 107) + 56;
            } else {
                result2 = i == 5 ? (((top - 428) * 5) / 107) + 67 : (((top - 428) * 5) / 107) + 67;
            }
        }
        Log.d("calculateTranslate", " result " + result2);
        return result2;
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    public static boolean isAppInstalled(String uri) {
        PackageManager pm = KswApplication.appContext.getPackageManager();
        try {
            pm.getPackageInfo(uri, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isAppInstalled(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean isSystemapp(String packageName) {
        Context context = KswApplication.appContext;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            return (packageInfo.applicationInfo.flags & 1) > 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String formatMusicPlayTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time >= 3600000 ? "H:m:ss" : "m:ss");
        return simpleDateFormat.format(new Date(time));
    }

    public static String formatMusicPlayTimeRemain(int time, int max) {
        int value;
        if (max - time < 0) {
            value = 0;
        } else {
            value = max - time;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(value >= 3600000 ? "H:m:ss" : "m:ss");
        return simpleDateFormat.format(new Date(value));
    }

    public static String formatMonth(Date date) {
        String[] months;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.NUM_MONTH);
        String m = simpleDateFormat.format(date);
        Log.i("KswUtils", "formatMonth: " + getLocalLanager());
        if (UiThemeUtils.isUI_PEMP_ID8(KswApplication.appContext)) {
            months = KswApplication.appContext.getResources().getStringArray(C0899R.array.all_months);
        } else {
            months = KswApplication.appContext.getResources().getStringArray(C0899R.array.ksw_id7_months);
        }
        int mm = Integer.valueOf(m).intValue();
        return months[mm - 1];
    }

    public static String formatDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(is24Hour() ? "dd" : DateFormat.DAY);
        return simpleDateFormat.format(date);
    }

    public static boolean is24Hour() {
        return android.text.format.DateFormat.is24HourFormat(KswApplication.appContext);
    }

    public static String splitPathMusicName(String path) {
        if (!TextUtils.isEmpty(path)) {
            String[] splits = path.split("/");
            return splits[splits.length - 1];
        }
        return null;
    }

    public static String getLocalLanager() {
        Locale locale = Locale.getDefault();
        String str = TAG;
        Log.i(str, "getLocalLanager: " + locale.getDisplayLanguage() + "_" + locale.getDisplayCountry());
        Log.i(str, "getLocalLanager: " + locale.getLanguage() + "_" + locale.getCountry());
        StringBuilder result = new StringBuilder(locale.getLanguage());
        if (locale.getCountry().length() > 0) {
            result.append("_").append(locale.getCountry());
        }
        return result.toString();
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() { // from class: com.wits.ksw.launcher.utils.KswUtils.1
            @Override // java.lang.Runnable
            public void run() {
                new Thread(new Runnable() { // from class: com.wits.ksw.launcher.utils.KswUtils.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Instrumentation instrumentation = new Instrumentation();
                        instrumentation.sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }

    public static int screenWidth(Context context) {
        new DisplayMetrics();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int screenHight(Context context) {
        new DisplayMetrics();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static boolean isDvr() {
        try {
            int dvr = PowerManagerApp.getSettingsInt("DVR_Type");
            return dvr != 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getDvrType() {
        try {
            return PowerManagerApp.getSettingsInt("DVR_Type");
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getUsbDvrPkg() {
        try {
            return PowerManagerApp.getSettingsString(KeyConfig.DEF_DVRAPK);
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void translate(View view, int id) {
        Animation translateAnimation = android.view.animation.AnimationUtils.loadAnimation(view.getContext(), id);
        view.setAnimation(translateAnimation);
        view.startAnimation(translateAnimation);
    }

    public static void setFull(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    public static boolean ismph() {
        try {
            return PowerManagerApp.getSettingsInt(KeyConfig.DASHBOARDUNIT) == 1;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveLexusLsLastPosition(Context context, int position) {
        if (context == null) {
            return;
        }
        Settings.System.putInt(context.getContentResolver(), "LexusLsPosition", position);
        Log.i(TAG, "saveLastPosition: LexusLsPosition=" + position);
    }

    public static int getLexusLsLastPosition(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "LexusLsPosition", -1);
    }

    public static void saveLastViewId(Context context, View view) {
        if (view == null || context == null) {
            return;
        }
        Settings.System.putInt(context.getContentResolver(), "view", view.getId());
        Log.i(TAG, "saveLastViewId: resId=" + view.getId());
    }

    public static int getLastViewId(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "view", -1);
    }

    public static int tempToF(float tmep) {
        return (int) (((9.0f * tmep) / 5.0f) + 32.0f);
    }

    public static int getBenzpaneVersion() {
        String str = "getBenzpaneVersion: benzpane=";
        int benzpane = 0;
        try {
            try {
                benzpane = PowerManagerApp.getSettingsInt(KeyConfig.BENZPANE);
                str = "getBenzpaneVersion: benzpane=" + benzpane;
                Log.d(TAG, str);
                return benzpane;
            } catch (Exception e) {
                e.printStackTrace();
                String str2 = TAG;
                Log.e(str2, "getBenzpaneVersion: RemoteException=" + e.getMessage());
                Log.d(str2, "getBenzpaneVersion: benzpane=0");
                return 0;
            }
        } catch (Throwable th) {
            Log.d(TAG, str + benzpane);
            throw th;
        }
    }
}
