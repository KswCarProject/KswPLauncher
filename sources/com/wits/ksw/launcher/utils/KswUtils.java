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

    public static int calculateTranslate(int top, int h, int i, Context context) {
        Log.d("calculateTranslate", " top: " + top + " h " + h + " index " + i);
        int hh = h - dip2px(context, 71.3f);
        int result = (hh - Math.abs(hh - top)) / 6;
        if (i == 1) {
            result = (top * 20) / 107;
        } else if (i == 2) {
            result = (((top - 107) * 18) / 107) + 20;
        } else if (i == 3) {
            result = (((top - 214) * 14) / 107) + 38;
        } else if (i == 4) {
            result = (((top - 321) * 8) / 107) + 52;
        } else if (i == 5 || i == 6) {
            result = (((top - 428) * 5) / 107) + 60;
        }
        Log.d("calculateTranslate", " result " + result);
        return result;
    }

    public static int calculateTranslate2(int top, int h, int i, Context context) {
        Log.d("calculateTranslate", " top: " + top + " h " + h + " index " + i);
        int hh = h - dip2px(context, 71.3f);
        int result = (hh - Math.abs(hh - top)) / 6;
        if (i != 0) {
            if (i == 1) {
                result = (top * 23) / 107;
            } else if (i == 2) {
                result = (((top - 107) * 16) / 107) + 23;
            } else if (i == 3) {
                result = (((top - 214) * 17) / 107) + 39;
            } else if (i == 4) {
                result = (((top - 321) * 11) / 107) + 56;
            } else if (i == 5) {
                result = (((top - 428) * 5) / 107) + 67;
            } else {
                result = (((top - 428) * 5) / 107) + 67;
            }
        }
        Log.d("calculateTranslate", " result " + result);
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
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

    public static int getBenzpaneVersion() {
        String str;
        StringBuilder sb;
        int benzpane = 0;
        try {
            benzpane = PowerManagerApp.getSettingsInt(KeyConfig.BENZPANE);
            str = TAG;
            sb = new StringBuilder();
        } catch (Exception e) {
            e.printStackTrace();
            String str2 = TAG;
            Log.e(str2, "getBenzpaneVersion: RemoteException=" + e.getMessage());
            str = TAG;
            sb = new StringBuilder();
        } catch (Throwable th) {
            String str3 = TAG;
            Log.d(str3, "getBenzpaneVersion: benzpane=" + 0);
            throw th;
        }
        sb.append("getBenzpaneVersion: benzpane=");
        sb.append(benzpane);
        Log.d(str, sb.toString());
        return benzpane;
    }
}
