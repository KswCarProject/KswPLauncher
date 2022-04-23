package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppInfoUtils {
    public static final String[] ALS_ID7_UI_ATYS_DISMISS_DESK = {"com.wits.ksw.bt.StartActivity", "com.android.deskclock.DeskClock", "com.iflytek.inputmethod.LauncherActivity", "com.wits.ksw.MainActivity", "com.wits.screencast.myapplication.MainActivity", "com.android.shell.ui.MainActivity", "com.wits.log.MainActivity"};
    public static final String[] ATYS_DISMISS = {"com.autonavi.auto.remote.fill.UsbFillActivity", "com.wits.ksw.bt.StartActivity", "com.android.deskclock.DeskClock", "com.iflytek.inputmethod.LauncherActivity", "com.wits.ksw.MainActivity", "com.wits.ksw.media.StartActivity", "com.wits.screencast.myapplication.MainActivity", "com.wits.log.MainActivity"};
    public static final String[] ATYS_DISMISS_DESK = {"com.autonavi.auto.remote.fill.UsbFillActivity", "com.wits.ksw.bt.StartActivity", "com.android.deskclock.DeskClock", "com.iflytek.inputmethod.LauncherActivity", "com.wits.ksw.MainActivity", "com.wits.screencast.myapplication.MainActivity", "com.android.shell.ui.MainActivity", "com.wits.log.MainActivity"};
    public static final String[] ATYS_DISMISS_MUSIC = {"com.autonavi.auto.remote.fill.UsbFillActivity", "com.wits.ksw.bt.StartActivity", "com.android.deskclock.DeskClock", "com.iflytek.inputmethod.LauncherActivity", "com.wits.ksw.MainActivity", "com.wits.screencast.myapplication.MainActivity", "com.android.shell.ui.MainActivity", "com.android.vending.AssetBrowserActivity", "com.wits.ksw.MainActivity", "com.navngo.igo.javaclient.MainActivity", "com.estrongs.android.pop.view.FileExplorerActivity", "com.google.android.maps.MapsActivity", "com.autonavi.auto.remote.fill.UsbFillActivity", "com.android.settings.Settings", "com.wits.csp.eq.view.StartActivity", "com.wits.apk.MainActivity", LauncherViewModel.CLS_CHROME, "com.wits.log.MainActivity"};
    public static final String[] ID7_ALS_DISMISS_DESK = {"com.autonavi.auto.remote.fill.UsbFillActivity", "com.wits.ksw.bt.StartActivity", "com.android.deskclock.DeskClock", "com.iflytek.inputmethod.LauncherActivity", "com.wits.ksw.MainActivity", "com.wits.screencast.myapplication.MainActivity", "com.android.shell.ui.MainActivity", "com.suding.speedplay.ui.MainActivity", "com.wits.log.MainActivity"};
    public static final String[] PKGS_DISMISS = {"com.autonavi.amapauto", "com.wits.ksw.bt", "com.android.deskclock", "com.iflytek.inputmethod.google", BuildConfig.APPLICATION_ID, "com.wits.ksw.media"};

    public static boolean isContainApp(String cls) {
        int i = 0;
        while (true) {
            String[] strArr = ATYS_DISMISS;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(cls)) {
                return true;
            }
            i++;
        }
    }

    public static boolean isContainCls(String[] strings, String cls) {
        if (strings == null || strings.length <= 0) {
            return false;
        }
        for (int i = 0; i < strings.length; i++) {
            Log.i("liuhao AAAA ", strings[i]);
            if (strings[i].equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public static List<LexusLsAppSelBean> findLexusLsAllApp(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = getAllApps(context);
        List<LexusLsAppSelBean> beans = new ArrayList<>();
        int tmpAppIndex = 0;
        for (ResolveInfo info : infos) {
            LexusLsAppSelBean bean = new LexusLsAppSelBean();
            bean.setAppIcon(info.activityInfo.loadIcon(pm));
            bean.setAppName((String) info.activityInfo.loadLabel(pm));
            bean.setAppPkg(info.activityInfo.packageName);
            bean.setAppMainAty(info.activityInfo.name);
            bean.setItemPos(tmpAppIndex);
            beans.add(bean);
            tmpAppIndex++;
        }
        return beans;
    }

    public static List<LexusLsAppSelBean> findLexusLsAllAppDeleteDesk(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = getAllApps(context);
        List<LexusLsAppSelBean> beans = new ArrayList<>();
        int tmpAppIndex = 0;
        for (ResolveInfo info : infos) {
            Log.e("liuhao", "findSelApps..name " + info.activityInfo.loadLabel(pm) + "  " + info.activityInfo.packageName + " mainAty:" + info.activityInfo.name);
            if (!isContainApp(info.activityInfo.name)) {
                LexusLsAppSelBean bean = new LexusLsAppSelBean();
                bean.setAppIcon(info.activityInfo.loadIcon(pm));
                bean.setAppName((String) info.activityInfo.loadLabel(pm));
                bean.setAppPkg(info.activityInfo.packageName);
                bean.setAppMainAty(info.activityInfo.name);
                bean.setItemPos(tmpAppIndex);
                beans.add(bean);
                tmpAppIndex++;
            }
        }
        return beans;
    }

    public static List<LexusLsAppSelBean> findAllAppsByExclude(String[] cls, int TYPE, Context context) {
        Iterator<ResolveInfo> it;
        List<ResolveInfo> infos;
        int i = TYPE;
        Log.i("AppInfoUtils", "TYPE = " + i);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos2 = getAllApps(context);
        List<LexusLsAppSelBean> beans = new ArrayList<>();
        LexusLsAppSelBean bean_music = new LexusLsAppSelBean();
        LexusLsAppSelBean bean_video = new LexusLsAppSelBean();
        int tmpAppIndex = 0;
        Iterator<ResolveInfo> it2 = infos2.iterator();
        while (it2.hasNext()) {
            ResolveInfo info = it2.next();
            Log.e("liuhao", "findAllAppExclude..name " + info.activityInfo.loadLabel(pm) + "  " + info.activityInfo.packageName + " mainAty:" + info.activityInfo.name);
            LexusLsAppSelBean bean = new LexusLsAppSelBean();
            if (!isContainCls(cls, info.activityInfo.name)) {
                if ("com.wits.ksw.media.StartActivity".equals(info.activityInfo.name)) {
                    bean_music.setAppIcon(context.getResources().getDrawable(R.drawable.id7_main_music_n));
                    bean_music.setAppName(context.getResources().getString(R.string.ksw_id7_music));
                    bean_music.setAppPkg(KeyConfig.CLS_LOCAL_MUSIC);
                    bean_music.setAppMainAty(KeyConfig.CLS_LOCAL_MUSIC);
                    bean_music.setItemPos(tmpAppIndex);
                    infos = infos2;
                    bean_video.setAppIcon(context.getResources().getDrawable(R.drawable.id7_main_video_n));
                    bean_video.setAppName(context.getResources().getString(R.string.ksw_id7_hd_video));
                    bean_video.setAppPkg(KeyConfig.CLS_LOCAL_VIDEO);
                    bean_video.setAppMainAty(KeyConfig.CLS_LOCAL_VIDEO);
                    bean_video.setItemPos(tmpAppIndex + 1);
                    if (i == 1) {
                        String appCls1 = Settings.System.getString(context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
                        if (TextUtils.isEmpty(appCls1) || !appCls1.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                            it = it2;
                            if (TextUtils.isEmpty(appCls1) || !KeyConfig.CLS_LOCAL_VIDEO.equals(appCls1)) {
                                bean_music.setChecked(false);
                            } else {
                                bean_video.setChecked(true);
                            }
                        } else {
                            it = it2;
                            Log.i("AppInfoUtils", "findAllAppsByExclude app music cls1 =" + appCls1);
                            bean_music.setChecked(true);
                        }
                    } else {
                        it = it2;
                    }
                    if (i == 2) {
                        String appCls2 = Settings.System.getString(context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS);
                        if (!TextUtils.isEmpty(appCls2) && appCls2.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                            Log.i("AppInfoUtils", "findAllAppsByExclude app video cls2 =" + appCls2);
                            bean_video.setChecked(true);
                        } else if (TextUtils.isEmpty(appCls2) || !KeyConfig.CLS_LOCAL_MUSIC.equals(appCls2)) {
                            bean_video.setChecked(false);
                        } else {
                            bean_music.setChecked(true);
                        }
                    }
                    beans.add(bean_music);
                    beans.add(bean_video);
                    tmpAppIndex = 2;
                } else {
                    infos = infos2;
                    it = it2;
                    bean.setAppIcon(info.activityInfo.loadIcon(pm));
                    bean.setAppName((String) info.activityInfo.loadLabel(pm));
                    bean.setAppPkg(info.activityInfo.packageName);
                    bean.setAppMainAty(info.activityInfo.name);
                    bean.setItemPos(tmpAppIndex);
                    if (i == 1) {
                        String appCls12 = Settings.System.getString(context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
                        if (TextUtils.isEmpty(appCls12) || !appCls12.equals(info.activityInfo.name)) {
                            bean.setChecked(false);
                        } else {
                            Log.i("AppInfoUtils", "TYPE == 1 findAllAppsByExclude app pkg =" + appCls12);
                            bean.setChecked(true);
                        }
                    } else if (i == 2) {
                        String appCls22 = Settings.System.getString(context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS);
                        if (TextUtils.isEmpty(appCls22) || !appCls22.equals(info.activityInfo.name)) {
                            bean.setChecked(false);
                        } else {
                            Log.i("AppInfoUtils", "TYPE == 2 findAllAppsByExclude app pkg =" + appCls22);
                            bean.setChecked(true);
                        }
                    }
                    beans.add(bean);
                    tmpAppIndex++;
                }
                infos2 = infos;
                it2 = it;
            }
        }
        return beans;
    }

    public static ResolveInfo findAppByPackageName(Context context, String packageName) {
        new ArrayList();
        PackageManager pm = context.getPackageManager();
        Intent mainIntent = new Intent("android.intent.action.MAIN", (Uri) null);
        mainIntent.addCategory("android.intent.category.LAUNCHER");
        mainIntent.setPackage(packageName);
        List<ResolveInfo> tmpInfos = pm.queryIntentActivities(mainIntent, 0);
        if (tmpInfos.size() <= 0) {
            return null;
        }
        return tmpInfos.get(0);
    }

    public static ResolveInfo findAppByPkgAndCls(Context context, String packageName, String clsName) {
        new ArrayList();
        PackageManager pm = context.getPackageManager();
        Intent mainIntent = new Intent("android.intent.action.MAIN", (Uri) null);
        mainIntent.addCategory("android.intent.category.LAUNCHER");
        mainIntent.setClassName(packageName, clsName);
        List<ResolveInfo> tmpInfos = pm.queryIntentActivities(mainIntent, 0);
        if (tmpInfos.size() <= 0) {
            return null;
        }
        return tmpInfos.get(0);
    }

    public static ArrayList<String> getAppsPackage(List<ResolveInfo> all) {
        ArrayList<String> pkgs = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            pkgs.add(all.get(i).activityInfo.packageName);
        }
        return pkgs;
    }

    public static List<ResolveInfo> getAllApps(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        new ArrayList();
        List<ResolveInfo> allApps = context.getPackageManager().queryIntentActivities(intent, 0);
        Collections.sort(allApps, new ResolveInfo.DisplayNameComparator(context.getPackageManager()));
        return allApps;
    }

    public static List<PackageInfo> getAllPkgs(Context context) {
        List<PackageInfo> apps = new ArrayList<>();
        List<PackageInfo> packlist = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packlist.size(); i++) {
            PackageInfo pak = packlist.get(i);
            apps.add(pak);
            int i2 = pak.applicationInfo.flags;
            ApplicationInfo applicationInfo = pak.applicationInfo;
            if ((i2 & 1) <= 0) {
                apps.add(pak);
            }
        }
        return apps;
    }

    public static String getStoragePath(Context mContext, boolean is_removale) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService("storage");
        try {
            Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method getPath = storageVolumeClazz.getMethod("getPath", new Class[0]);
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable", new Class[0]);
            Object result = getVolumeList.invoke(mStorageManager, new Object[0]);
            int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement, new Object[0]);
                if (is_removale == ((Boolean) isRemovable.invoke(storageVolumeElement, new Object[0])).booleanValue()) {
                    return path;
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static boolean isAppPakExist(Context context, String packageName) {
        boolean isExist = false;
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("liuhao", "isAppPakExist: app not installed, packageName" + packageName);
        }
        if (applicationInfo != null) {
            isExist = true;
        }
        Log.i("liuhao", "isAppPakExist: isExist = " + isExist);
        return isExist;
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        matrix.postScale(((float) w) / ((float) width), ((float) h) / ((float) height));
        return new BitmapDrawable((Resources) null, Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true));
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap.Config config;
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        if (drawable.getOpacity() != -1) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.RGB_565;
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
