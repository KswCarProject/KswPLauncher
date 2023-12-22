package com.wits.ksw.launcher.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.base.BaseListAdpater;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.dabebase.AppInfoRoomDatabase;
import com.wits.ksw.launcher.dabebase.AppList;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public class AppsLoaderTask {
    public static final String AUX_TYPE = "AUX_Type";
    private static final String DESKCLOCK_PKG = "com.android.deskclock";
    private static final String DOCUMENTS_UI = "com.android.documentsui";
    public static final String DTV_TYPE = "DTV_Type";
    public static final String DVR = "com.ankai.cardvr";
    public static final String DVR_TYPE = "DVR_Type";
    private static final String EQ_PKG = "com.wits.csp.eq";
    private static final String E_CAR = "com.ecar.assistantnew";
    public static final String F_CAM_Type = "Front_view_camera";
    private static final String GAODE_MAP_PKG = "com.autonavi.amapauto";
    private static final String GN_TXZ = "com.txznet.adapter";
    private static final String GOOGLE_ASSISTANT_PKG = "com.google.android.apps.googleassistant";
    private static final String GOOGLE_MAP = "com.google.android.apps.maps";
    private static final String GOOGLE_PLAY = "com.android.vending";
    private static final String GOOGLE_SEARCH_PKG = "com.google.android.googlequicksearchbox";
    private static final String HY_TXZ = "com.txznet.smartadapter";
    private static final String IFLYTEK_PKG = "com.iflytek.inputmethod.google";
    private static final String PRE_SET_APP = "preset_app";
    private static final String SIM_APK = "com.android.stk";
    private static final String SOUGOU_PKG = "com.sohu.inputmethod.sogou";
    private static final String SPEEDPLAY_PKG = "com.suding.speedplay";
    private static final String TAG = "AppsLoaderTask";
    private static final String TS_TXZ = "com.txznet.aipal";
    private static final String WEATHER_PKG = "com.txznet.weather";
    private static final String WITS_LOG_PKG = "com.wits.log";
    private static final String ZLINK_PKG = "com.zjinnova.zlink";
    public static Context mContext;
    public static List<AppsLoaderTaskListener> mLoaderTaskListenerList;
    public static AppsLoaderTask singleton;
    public ObservableField<BaseListAdpater<AppInfo>> listAdpater = new ObservableField<>();
    private static Drawable auxIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.ic_aux);
    private static Drawable dtvIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.ic_dtv);
    private static Drawable dvrIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.ic_dvr);
    private static Drawable fcamIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.icon_fcam);
    private static String auxLable = KswApplication.appContext.getString(C0899R.string.app_aux);
    private static String dtvLable = KswApplication.appContext.getString(C0899R.string.app_dtv);
    private static String dvrLable = KswApplication.appContext.getString(C0899R.string.app_dvr);
    private static String fcamLable = KswApplication.appContext.getString(C0899R.string.app_fcam);
    public static List<AppInfo> mListLiveData = new ArrayList();
    public static List<AppList> mAppLists = new ArrayList();

    /* loaded from: classes9.dex */
    public interface AppsLoaderTaskListener {
        void allAppsLoaded(List<AppInfo> appInfos);

        void dialogAppsListLoaded(List<AppInfo> dialoglist);

        void shortcutsLoaded(List<AppInfo> shortcuts);

        void updateShortcuts(List<AppInfo> shortcuts);
    }

    private AppsLoaderTask() {
        mContext = KswApplication.appContext;
        if (mLoaderTaskListenerList == null) {
            mLoaderTaskListenerList = new ArrayList();
        }
    }

    public static AppsLoaderTask getInstance() {
        if (singleton == null) {
            synchronized (AppsLoaderTask.class) {
                if (singleton == null) {
                    singleton = new AppsLoaderTask();
                }
            }
        }
        return singleton;
    }

    public void setLoaderTaskListener(AppsLoaderTaskListener listener) {
        mLoaderTaskListenerList.add(listener);
    }

    public List<AppInfo> getmListLiveData() {
        return mListLiveData;
    }

    public void queryAllApps() {
        Log.i(TAG, "queryAllApps: ");
        new QueryAppsAsyncTask().execute(new Void[0]);
    }

    /* loaded from: classes9.dex */
    private class QueryAppsAsyncTask extends AsyncTask<Void, Void, List<AppInfo>> {
        private QueryAppsAsyncTask() {
        }

        public List<AppInfo> queryApps() {
            Log.i("AppViewModel", "queryApps: ");
            AppsLoaderTask.mAppLists = AppInfoRoomDatabase.getDatabase(KswApplication.appContext).getAppInfoDao().queryAll();
            List<ResolveInfo> resolveInfoList = getResolveInfos();
            PackageManager pm = KswApplication.appContext.getPackageManager();
            List<AppInfo> appInfoList = new ArrayList<>();
            if (resolveInfoList != null || !resolveInfoList.isEmpty()) {
                for (ResolveInfo resolveInfo : resolveInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    Log.d("AppViewModel", ((Object) resolveInfo.activityInfo.loadLabel(AppsLoaderTask.mContext.getPackageManager())) + "  " + packageName + "  " + resolveInfo.activityInfo.name);
                    if (!AppsLoaderTask.this.isOutTXZ(packageName) && !AppsLoaderTask.this.isFilterAllGoogleAPP(packageName) && !AppsLoaderTask.this.isFilterSimCardAPP(packageName) && !AppsLoaderTask.this.isOutECAR(packageName) && !AppsLoaderTask.this.isOutEQ(packageName) && !AppsLoaderTask.this.isOutWeather(packageName) && !AppsLoaderTask.this.filterAppDisplay(packageName)) {
                        AppInfo appInfo = new AppInfo();
                        Drawable iconDrawable = resolveInfo.loadIcon(pm);
                        appInfo.setAppIcon(iconDrawable);
                        String label = resolveInfo.loadLabel(pm).toString();
                        appInfo.setAppLable(label);
                        appInfo.setApppkg(packageName);
                        appInfo.setClassName(resolveInfo.activityInfo.name);
                        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
                            AppsLoaderTask.this.loadRoundRectIcon(packageName, appInfo, iconDrawable, label);
                        }
                        appInfoList.add(appInfo);
                    }
                }
            }
            return appInfoList;
        }

        public List<ResolveInfo> getResolveInfos() {
            PackageManager pm = KswApplication.appContext.getPackageManager();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            return pm.queryIntentActivities(intent, 131072);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<AppInfo> doInBackground(Void... voids) {
            return queryApps();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(List<AppInfo> listLiveData) {
            super.onPostExecute((QueryAppsAsyncTask) listLiveData);
            AppsLoaderTask.addCustomApp(listLiveData, "AUX_Type", AppsLoaderTask.auxIcon, AppsLoaderTask.auxLable);
            AppsLoaderTask.addCustomApp(listLiveData, "DTV_Type", AppsLoaderTask.dtvIcon, AppsLoaderTask.dtvLable);
            AppsLoaderTask.addCustomApp(listLiveData, "DVR_Type", AppsLoaderTask.dvrIcon, AppsLoaderTask.dvrLable);
            AppsLoaderTask.addCustomApp(listLiveData, "Front_view_camera", AppsLoaderTask.fcamIcon, AppsLoaderTask.fcamLable);
            AppsLoaderTask.mListLiveData.clear();
            for (int i = 0; i < AppsLoaderTask.mAppLists.size(); i++) {
                int j = 0;
                while (true) {
                    if (j < listLiveData.size()) {
                        if (!AppsLoaderTask.mAppLists.get(i).getClassName().equals(listLiveData.get(j).getClassName())) {
                            j++;
                        } else {
                            AppsLoaderTask.mListLiveData.add(listLiveData.get(j));
                            listLiveData.remove(j);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            for (AppInfo appInfo : listLiveData) {
                AppsLoaderTask.mListLiveData.add(appInfo);
            }
            AppsLoaderTask.SaveAppList();
            if (AppsLoaderTask.mLoaderTaskListenerList != null && AppsLoaderTask.mLoaderTaskListenerList.size() > 0) {
                for (AppsLoaderTaskListener listener : AppsLoaderTask.mLoaderTaskListenerList) {
                    listener.allAppsLoaded(AppsLoaderTask.mListLiveData);
                }
            }
            if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
                AppsLoaderTask.this.listAdpater.set(new BaseListAdpater<>(AppsLoaderTask.mListLiveData, C0899R.C0902layout.round_app_item));
            } else {
                AppsLoaderTask.this.listAdpater.set(new BaseListAdpater<>(AppsLoaderTask.mListLiveData, C0899R.C0902layout.id7_app_item));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRoundRectIcon(String packageName, AppInfo appInfo, Drawable drawable, String label) {
        Drawable roundIcon = IconUtils.getInstance().getIcon(packageName, appInfo, drawable, label);
        appInfo.setAppIcon(roundIcon);
    }

    public boolean isOutTXZ(String packageName) {
        try {
            int isSee = PowerManagerApp.getSettingsInt(KeyConfig.TXZ);
            if (isSee != 0) {
                return false;
            }
            if (packageName.contains(HY_TXZ) || packageName.contains(GN_TXZ)) {
                return true;
            }
            if (!packageName.contains(TS_TXZ)) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isOutECAR(String packageName) {
        try {
            int isSee = PowerManagerApp.getSettingsInt(KeyConfig.E_CAR);
            if (isSee != 0) {
                return false;
            }
            if (!packageName.contains(E_CAR)) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isOutEQ(String packageName) {
        try {
            int isSee = PowerManagerApp.getSettingsInt(KeyConfig.EQ_APP);
            if (isSee != 0) {
                return false;
            }
            if (!packageName.contains("com.wits.csp.eq")) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isOutWeather(String packageName) {
        try {
            int isSee = PowerManagerApp.getSettingsInt(KeyConfig.GLOBAL_WEATHER_APP);
            if (isSee != 0) {
                return false;
            }
            if (!packageName.contains("com.txznet.weather")) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFilterAllGoogleAPP(String packageName) {
        try {
            int isSee = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
            if (isSee != 0) {
                return false;
            }
            if (packageName.contains("com.android.vending") || packageName.contains("com.google.android.apps.maps") || packageName.contains("com.google.android.googlequicksearchbox")) {
                return true;
            }
            if (!packageName.contains("com.google.android.apps.googleassistant")) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFilterSimCardAPP(String packageName) {
        return packageName.contains(SIM_APK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean filterAppDisplay(String packageName) {
        if (packageName.contains(KswApplication.appContext.getPackageName()) || packageName.contains(mContext.getClass().getPackage().toString()) || packageName.contains(IFLYTEK_PKG) || packageName.contains(DESKCLOCK_PKG) || packageName.contains(WITS_LOG_PKG) || packageName.contains(DOCUMENTS_UI) || packageName.contains(SOUGOU_PKG)) {
            return true;
        }
        int speed_play_switch = Settings.System.getInt(mContext.getContentResolver(), "speed_play_switch", 1);
        if (speed_play_switch == 2 || !packageName.contains(SPEEDPLAY_PKG)) {
            if (speed_play_switch == 2 && packageName.contains(ZLINK_PKG)) {
                return true;
            }
            String[] excludeClassArray = mContext.getResources().getStringArray(C0899R.array.exclude_class_list);
            if (Arrays.asList(excludeClassArray).contains(packageName)) {
                Log.d(TAG, "filterAppDisplay: excludeClassArray packagename =" + packageName);
                return true;
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addCustomApp(List<AppInfo> listLiveData, String type, Drawable auxIcon2, String auxLable2) {
        try {
            int value = PowerManagerApp.getSettingsInt(type);
            if (value == 1) {
                AppInfo appInfo = new AppInfo();
                appInfo.setAppIcon(auxIcon2);
                appInfo.setAppLable(auxLable2);
                appInfo.setApppkg(type);
                appInfo.setClassName(type);
                listLiveData.add(appInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void SaveAppList() {
        synchronized (AppsLoaderTask.class) {
            final List<AppList> newAppList = new ArrayList<>();
            for (AppInfo appInfo : mListLiveData) {
                AppList appList = new AppList();
                appList.setClassName(appInfo.getClassName());
                newAppList.add(appList);
                if (TextUtils.isEmpty(appInfo.getClassName())) {
                    Log.e(TAG, "SaveAppList: appinfo ClassName isEmpty AppLable = " + appInfo.getAppLable());
                    return;
                }
            }
            new Thread(new Runnable() { // from class: com.wits.ksw.launcher.model.AppsLoaderTask.1
                @Override // java.lang.Runnable
                public void run() {
                    AppInfoRoomDatabase.getDatabase(AppsLoaderTask.mContext).getAppInfoDao().deleteAll();
                    AppInfoRoomDatabase.getDatabase(AppsLoaderTask.mContext).getAppInfoDao().insert(newAppList);
                }
            }).start();
        }
    }
}
