package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.util.Log;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppInfoUtils {
    public static final String[] ATYS_DISMISS = {"com.autonavi.auto.remote.fill.UsbFillActivity", "com.wits.ksw.bt.StartActivity", "com.android.deskclock.DeskClock", "com.iflytek.inputmethod.LauncherActivity", "com.wits.ksw.MainActivity", "com.wits.ksw.media.StartActivity", "com.wits.screencast.myapplication.MainActivity"};
    public static final String[] PKGS_DISMISS = {"com.autonavi.amapauto", "com.wits.ksw.bt", "com.android.deskclock", "com.iflytek.inputmethod.google", BuildConfig.APPLICATION_ID, "com.wits.ksw.media"};

    public static boolean isContainApp(String pkg) {
        int i = 0;
        while (true) {
            String[] strArr = ATYS_DISMISS;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(pkg)) {
                return true;
            }
            i++;
        }
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
}
