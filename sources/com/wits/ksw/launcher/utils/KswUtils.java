package com.wits.ksw.launcher.utils;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class KswUtils {
    public static final double RPH = 0.621d;
    private static final String TAG = ("KSWLauncher." + KswUtils.class.getSimpleName());
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

    public static boolean isAppInstalled(String uri) {
        try {
            KswApplication.appContext.getPackageManager().getPackageInfo(uri, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isSystemapp(String packageName) {
        try {
            if ((KswApplication.appContext.getPackageManager().getPackageInfo(packageName, 0).applicationInfo.flags & 1) > 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String formatMusicPlayTime(long time) {
        return new SimpleDateFormat(time >= 3600000 ? "H:m:ss" : "m:ss").format(new Date(time));
    }

    public static String formatMonth(Date date) {
        String m = new SimpleDateFormat("M").format(date);
        Log.i("KswUtils", "formatMonth: " + getLocalLanager());
        return KswApplication.appContext.getResources().getStringArray(R.array.ksw_id7_months)[Integer.valueOf(m).intValue() - 1];
    }

    public static String formatDay(Date date) {
        return new SimpleDateFormat(is24Hour() ? "dd" : "d").format(date);
    }

    public static boolean is24Hour() {
        return DateFormat.is24HourFormat(KswApplication.appContext);
    }

    public static String splitPathMusicName(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        String[] splits = path.split("/");
        return splits[splits.length - 1];
    }

    public static String getLocalLanager() {
        Locale locale = Locale.getDefault();
        String str = TAG;
        Log.i(str, "getLocalLanager: " + locale.getDisplayLanguage() + "_" + locale.getDisplayCountry());
        String str2 = TAG;
        Log.i(str2, "getLocalLanager: " + locale.getLanguage() + "_" + locale.getCountry());
        StringBuilder result = new StringBuilder(locale.getLanguage());
        if (locale.getCountry().length() > 0) {
            result.append("_");
            result.append(locale.getCountry());
        }
        return result.toString();
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        new Instrumentation().sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }

    public static int screenWidth(Context context) {
        new DisplayMetrics();
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int screenHight(Context context) {
        new DisplayMetrics();
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static boolean isDvr() {
        try {
            if (PowerManagerApp.getSettingsInt("DVR_Type") != 0) {
                return true;
            }
            return false;
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
        Animation translateAnimation = AnimationUtils.loadAnimation(view.getContext(), id);
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

    public static void saveLastViewId(Context context, View view) {
        if (view != null && context != null) {
            Settings.System.putInt(context.getContentResolver(), "view", view.getId());
            String str = TAG;
            Log.i(str, "saveLastViewId: resId=" + view.getId());
        }
    }

    public static int getLastViewId(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "view", -1);
    }

    public static int tempToF(float tmep) {
        return (int) (((9.0f * tmep) / 5.0f) + 32.0f);
    }
}
