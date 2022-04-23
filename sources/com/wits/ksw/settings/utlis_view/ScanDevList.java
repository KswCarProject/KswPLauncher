package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.settings.id7.bean.DevBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScanDevList {
    private String devDefual;
    /* access modifiers changed from: private */
    public List<DevBean> devList;
    /* access modifiers changed from: private */
    public boolean isScanDev;
    /* access modifiers changed from: private */
    public boolean isScanPlay;
    /* access modifiers changed from: private */
    public List<DevBean> playAppList;

    private ScanDevList() {
        this.devDefual = "";
        this.isScanDev = false;
        this.isScanPlay = false;
    }

    private static class SingletonInstance {
        /* access modifiers changed from: private */
        public static final ScanDevList INSTANCE = new ScanDevList();

        private SingletonInstance() {
        }
    }

    public static ScanDevList getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public List<DevBean> scanList(final List<String> naviList, String devDefual2, final Context context) {
        this.devList = new ArrayList();
        this.devDefual = devDefual2;
        new Thread(new Runnable() {
            public void run() {
                if (!ScanDevList.this.isScanDev) {
                    boolean unused = ScanDevList.this.isScanDev = true;
                    for (String paca : naviList) {
                        ScanDevList scanDevList = ScanDevList.this;
                        scanDevList.isAvilible(context, paca, scanDevList.devList);
                    }
                }
                boolean unused2 = ScanDevList.this.isScanDev = false;
            }
        }).start();
        return this.devList;
    }

    public List<DevBean> playAppList(final Context context, final String defPlayApp) {
        this.playAppList = new ArrayList();
        new Thread(new Runnable() {
            public void run() {
                if (!ScanDevList.this.isScanPlay) {
                    boolean unused = ScanDevList.this.isScanPlay = true;
                    ScanDevList scanDevList = ScanDevList.this;
                    scanDevList.scanPlayApp(context, defPlayApp, scanDevList.playAppList);
                }
                boolean unused2 = ScanDevList.this.isScanPlay = false;
            }
        }).start();
        return this.playAppList;
    }

    /* access modifiers changed from: private */
    public void isAvilible(final Context context, String packageName, List<DevBean> mbList) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List apps = context.getPackageManager().queryIntentActivities(intent, 0);
        Collections.sort(apps, new Comparator<ResolveInfo>() {
            public int compare(ResolveInfo a, ResolveInfo b) {
                return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(context.getPackageManager()).toString(), b.loadLabel(context.getPackageManager()).toString());
            }
        });
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packName = info.activityInfo.packageName;
            CharSequence appName = info.activityInfo.loadLabel(context.getPackageManager());
            Drawable iconbm = info.activityInfo.loadIcon(context.getPackageManager());
            if (packageName.equals(packName)) {
                DevBean devBean = new DevBean();
                devBean.setPackageName(packName);
                devBean.setName(appName.toString());
                devBean.setAppicon(iconbm);
                if (packName.equals(this.devDefual)) {
                    devBean.setCheck(true);
                }
                mbList.add(devBean);
            }
        }
    }

    /* access modifiers changed from: private */
    public void scanPlayApp(final Context context, String packageName, List<DevBean> mbList) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List apps = context.getPackageManager().queryIntentActivities(intent, 0);
        Collections.sort(apps, new Comparator<ResolveInfo>() {
            public int compare(ResolveInfo a, ResolveInfo b) {
                return String.CASE_INSENSITIVE_ORDER.compare(a.loadLabel(context.getPackageManager()).toString(), b.loadLabel(context.getPackageManager()).toString());
            }
        });
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getPackageInfo("com.wits.ksw.media", 0).applicationInfo;
            DevBean devBean = new DevBean();
            devBean.setPackageName("com.wits.ksw.media");
            devBean.setName(appInfo.loadLabel(pm).toString());
            devBean.setAppicon(appInfo.loadIcon(pm));
            if (packageName.equals("com.wits.ksw.media")) {
                devBean.setCheck(true);
            }
            mbList.add(devBean);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packName = info.activityInfo.packageName;
            CharSequence appName = info.activityInfo.loadLabel(context.getPackageManager());
            Drawable iconbm = info.activityInfo.loadIcon(context.getPackageManager());
            if (packName.lastIndexOf("wits") < 0 && !TextUtils.equals(packName, "com.estrongs.android.pop") && !TextUtils.equals(packName, "com.cyanogenmod.filemanager") && !TextUtils.equals(packName, "com.iflytek.inputmethod.google") && !TextUtils.equals(packName, "com.google.android.googlequicksearchbox") && !TextUtils.equals(packName, "com.android.vending") && !TextUtils.equals(packName, "com.ankai.cardvr") && !TextUtils.equals(packName, "com.android.chrome") && !TextUtils.equals(packName, "com.google.android.apps.maps") && !TextUtils.equals(packName, "com.android.shell") && !TextUtils.equals(packName, "com.android.settings") && !TextUtils.equals(packName, "com.autonavi.amapauto")) {
                Log.d("ScanPlayApp", "packname:" + packName);
                DevBean devBean2 = new DevBean();
                devBean2.setPackageName(packName);
                devBean2.setName(appName.toString());
                devBean2.setAppicon(iconbm);
                if (packName.equals(packageName)) {
                    devBean2.setCheck(true);
                }
                mbList.add(devBean2);
            }
        }
    }
}
