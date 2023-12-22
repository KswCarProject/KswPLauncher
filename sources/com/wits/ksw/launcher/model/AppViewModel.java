package com.wits.ksw.launcher.model;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.base.BaseListAdpater;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.bmw_id8_ui.GSID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.PempID8LauncherConstants;
import com.wits.ksw.launcher.dabebase.AppInfoRoomDatabase;
import com.wits.ksw.launcher.dabebase.AppList;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.DragGridView;
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public final class AppViewModel extends LauncherViewModel {
    public static final String AUX_TYPE = "AUX_Type";
    private static final String DESKCLOCK_PKG = "com.android.deskclock";
    private static final String DOCUMENTS_UI = "com.android.documentsui";
    public static final String DTV_TYPE = "DTV_Type";
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
    private static final String SIM_APK = "com.android.stk";
    private static final String SOUGOU_PKG = "com.sohu.inputmethod.sogou";
    private static final String SPEEDPLAY_PKG = "com.suding.speedplay";
    private static final String TS_TXZ = "com.txznet.aipal";
    private static final String WEATHER_PKG = "com.txznet.weather";
    private static final String WITS_LOG_PKG = "com.wits.log";
    private static final String ZLINK_PKG = "com.zjinnova.zlink";
    private static Drawable auxIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.ic_aux);
    private static Drawable dtvIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.ic_dtv);
    private static Drawable dvrIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.ic_dvr);
    private static Drawable fcamIcon = KswApplication.appContext.getDrawable(C0899R.C0900drawable.icon_fcam);
    private static String auxLable = KswApplication.appContext.getString(C0899R.string.app_aux);
    private static String dtvLable = KswApplication.appContext.getString(C0899R.string.app_dtv);
    private static String dvrLable = KswApplication.appContext.getString(C0899R.string.app_dvr);
    private static String fcamLable = KswApplication.appContext.getString(C0899R.string.app_fcam);
    public ObservableField<BaseListAdpater<AppInfo>> listAdpater = new ObservableField<>();
    List<AppInfo> mListLiveData = new ArrayList();
    List<AppList> mAppLists = new ArrayList();
    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.wits.ksw.launcher.model.AppViewModel.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            boolean isEdit = AppViewModel.this.activity.getIntent().getBooleanExtra("isEdit", false);
            AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
            if (TextUtils.equals(appInfo.getApppkg(), "AUX_Type")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_AUX);
            } else if (TextUtils.equals(appInfo.getApppkg(), "DTV_Type")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_DTV);
            } else if (TextUtils.equals(appInfo.getApppkg(), "DVR_Type")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_CVBSDVR);
            } else if (TextUtils.equals(appInfo.getApppkg(), "Front_view_camera")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_F_CAM);
            } else if (isEdit) {
                String pkg = appInfo.getApppkg();
                String cls = appInfo.getClassName();
                Log.w(LauncherViewModel.TAG, "onItemClick: pkg :" + pkg);
                Log.w(LauncherViewModel.TAG, "onItemClick: cls :" + cls);
                if (!"com.android.settings".equals(pkg)) {
                    if (!UiThemeUtils.isBMW_ID8_UI(AppViewModel.this.context)) {
                        if (!UiThemeUtils.isUI_PEMP_ID8(AppViewModel.this.context)) {
                            if (UiThemeUtils.isBenz_MBUX_2021_KSW(AppViewModel.this.context) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(AppViewModel.this.context) || ((UiThemeUtils.isBenz_NTG6_FY(AppViewModel.this.context) && ClientManager.getInstance().isAls6208Client()) || (UiThemeUtils.isUI_NTG6_FY_V2(AppViewModel.this.context) && ClientManager.getInstance().isAls6208Client()))) {
                                BenzUtils.addTrdApp(pkg, cls);
                                AppViewModel.this.activity.setResult(120);
                            } else {
                                GSID8LauncherConstants.addTrdApp(pkg, cls);
                            }
                        } else {
                            PempID8LauncherConstants.addTrdApp(pkg, cls);
                        }
                    } else {
                        ID8LauncherConstants.addTrdApp(pkg, cls);
                    }
                    AppViewModel.this.activity.finish();
                }
            } else {
                AppViewModel.this.openAppTask(new ComponentName(appInfo.getApppkg(), appInfo.getClassName()));
            }
        }
    };
    public AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.wits.ksw.launcher.model.AppViewModel.2
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
            Log.i("AppViewModel", "onItemLongClick: " + appInfo.toString());
            if (!TextUtils.equals(appInfo.getApppkg(), "AUX_Type") && !TextUtils.equals(appInfo.getApppkg(), "DTV_Type") && !TextUtils.equals(appInfo.getApppkg(), "DVR_Type") && !KswUtils.isSystemapp(appInfo.getApppkg()) && !TextUtils.equals(appInfo.getApppkg(), "Front_view_camera")) {
                AppViewModel.this.uninstallAppIntent(appInfo.getApppkg(), view.getContext());
                return true;
            }
            Toast.makeText(view.getContext(), view.getContext().getString(C0899R.string.ksw_id7_system_app_not_uninstall), 0).show();
            return true;
        }
    };
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.wits.ksw.launcher.model.AppViewModel.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppViewModel.this.queryApps();
        }
    };
    public DragGridView.onItemChangerListener onItemChangerListener = new DragGridView.onItemChangerListener() { // from class: com.wits.ksw.launcher.model.AppViewModel.4
        @Override // com.wits.ksw.launcher.view.DragGridView.onItemChangerListener
        public void onChange(GridView gridView, int from, int to) {
            Log.i(LauncherViewModel.TAG, "onChange: ");
            try {
                AppInfo tempAppInfo = AppsLoaderTask.getInstance().getmListLiveData().get(from);
                AppsLoaderTask.getInstance().getmListLiveData().set(from, AppsLoaderTask.getInstance().getmListLiveData().get(to));
                AppsLoaderTask.getInstance().getmListLiveData().set(to, tempAppInfo);
                AppViewModel.this.updateItem(gridView, to);
                AppViewModel.this.updateItem(gridView, from);
                AppViewModel.this.SaveAppList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.wits.ksw.launcher.view.DragGridView.onItemChangerListener
        public void onStartMoving() {
            Log.i(LauncherViewModel.TAG, "onStartMoving: ");
            try {
                String topApp = PowerManagerApp.getManager().getStatusString("topApp");
                Log.i(LauncherViewModel.TAG, "onStartMoving: topApp=" + topApp);
                if (!"com.google.android.packageinstaller".equals(topApp)) {
                    Log.i(LauncherViewModel.TAG, "onStartMoving: return");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.w(LauncherViewModel.TAG, "sendKeyDownUpSync: KEYCODE_ESCAPE");
            KswUtils.sendKeyDownUpSync(111);
        }
    };

    public AppViewModel() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        KswApplication.appContext.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    public void queryApps() {
        new QueryAppsAsyncTask().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uninstallAppIntent(String pkg, Context context) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + pkg));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* loaded from: classes9.dex */
    private class QueryAppsAsyncTask extends AsyncTask<Void, Void, List<AppInfo>> {
        private QueryAppsAsyncTask() {
        }

        public List<AppInfo> queryApps() {
            Log.i("AppViewModel", "queryApps: ");
            AppViewModel.this.mAppLists = AppInfoRoomDatabase.getDatabase(KswApplication.appContext).getAppInfoDao().queryAll();
            List<ResolveInfo> resolveInfoList = getResolveInfos();
            PackageManager pm = KswApplication.appContext.getPackageManager();
            List<AppInfo> appInfoList = new ArrayList<>();
            if (resolveInfoList != null || !resolveInfoList.isEmpty()) {
                for (ResolveInfo resolveInfo : resolveInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    Log.d("AppViewModel", ((Object) resolveInfo.activityInfo.loadLabel(AppViewModel.this.activity.getPackageManager())) + "  " + packageName + "  " + resolveInfo.activityInfo.name);
                    if (!AppViewModel.this.isOutTXZ(packageName) && !AppViewModel.this.isFilterAllGoogleAPP(packageName) && !AppViewModel.this.isFilterSimCardAPP(packageName) && !AppViewModel.this.isOutECAR(packageName) && !AppViewModel.this.isOutEQ(packageName) && !AppViewModel.this.isOutWeather(packageName) && !AppViewModel.this.filterAppDisplay(packageName)) {
                        AppInfo appInfo = new AppInfo();
                        Drawable iconDrawable = resolveInfo.loadIcon(pm);
                        appInfo.setAppIcon(iconDrawable);
                        String label = resolveInfo.loadLabel(pm).toString();
                        appInfo.setAppLable(label);
                        appInfo.setApppkg(packageName);
                        appInfo.setClassName(resolveInfo.activityInfo.name);
                        if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
                            AppViewModel.this.loadRoundRectIcon(packageName, appInfo, iconDrawable, label);
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
            AppViewModel.this.addCustomApp(listLiveData, "AUX_Type", AppViewModel.auxIcon, AppViewModel.auxLable);
            AppViewModel.this.addCustomApp(listLiveData, "DTV_Type", AppViewModel.dtvIcon, AppViewModel.dtvLable);
            AppViewModel.this.addCustomApp(listLiveData, "DVR_Type", AppViewModel.dvrIcon, AppViewModel.dvrLable);
            AppViewModel.this.addCustomApp(listLiveData, "Front_view_camera", AppViewModel.fcamIcon, AppViewModel.fcamLable);
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
        if (packageName.contains(KswApplication.appContext.getPackageName()) || packageName.contains(getClass().getPackage().toString()) || packageName.contains(IFLYTEK_PKG) || packageName.contains(DESKCLOCK_PKG) || packageName.contains(WITS_LOG_PKG) || packageName.contains(DOCUMENTS_UI) || packageName.contains(SOUGOU_PKG)) {
            return true;
        }
        int speed_play_switch = Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1);
        if (speed_play_switch == 2 || !packageName.contains(SPEEDPLAY_PKG)) {
            if (speed_play_switch == 2 && packageName.contains(ZLINK_PKG)) {
                return true;
            }
            String[] excludeClassArray = this.context.getResources().getStringArray(C0899R.array.exclude_class_list);
            if (Arrays.asList(excludeClassArray).contains(packageName)) {
                Log.d(TAG, "filterAppDisplay: excludeClassArray packagename =" + packageName);
                return true;
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCustomApp(List<AppInfo> listLiveData, String type, Drawable auxIcon2, String auxLable2) {
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

    @Override // com.wits.ksw.launcher.model.LauncherViewModel, com.wits.ksw.launcher.base.BaseViewModel, android.arch.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        KswApplication.appContext.unregisterReceiver(this.broadcastReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void SaveAppList() {
        try {
            final List<AppList> newAppList = new ArrayList<>();
            for (AppInfo appInfo : AppsLoaderTask.getInstance().getmListLiveData()) {
                AppList appList = new AppList();
                appList.setClassName(appInfo.getClassName());
                newAppList.add(appList);
                if (TextUtils.isEmpty(appInfo.getClassName())) {
                    Log.e(TAG, "SaveAppList: appinfo ClassName isEmpty AppLable = " + appInfo.getAppLable());
                    return;
                }
            }
            new Thread(new Runnable() { // from class: com.wits.ksw.launcher.model.AppViewModel.5
                @Override // java.lang.Runnable
                public void run() {
                    AppInfoRoomDatabase.getDatabase(KswApplication.appContext).getAppInfoDao().deleteAll();
                    AppInfoRoomDatabase.getDatabase(KswApplication.appContext).getAppInfoDao().insert(newAppList);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateItem(GridView gridView, int position) {
        int firstVisiblePosition = gridView.getFirstVisiblePosition();
        int lastVisiblePosition = gridView.getLastVisiblePosition();
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            View view = gridView.getChildAt(position - firstVisiblePosition);
            this.listAdpater.get().getView(position, view, gridView);
        }
    }

    @Override // com.wits.ksw.launcher.base.BaseViewModel, com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void allAppsLoaded(List<AppInfo> appInfos) {
        Log.i(TAG, "allAppsLoaded: ");
        refreshList();
    }

    @Override // com.wits.ksw.launcher.base.BaseViewModel, com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void updateShortcuts(List<AppInfo> shortcuts) {
    }

    @Override // com.wits.ksw.launcher.base.BaseViewModel, com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void shortcutsLoaded(List<AppInfo> shortcuts) {
    }

    @Override // com.wits.ksw.launcher.base.BaseViewModel, com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void dialogAppsListLoaded(List<AppInfo> dialoglist) {
    }

    public void refreshList() {
        Log.i(TAG, "refreshList: ");
        try {
            if (this.listAdpater != null && AppsLoaderTask.getInstance().getmListLiveData() != null) {
                if (ClientManager.getInstance().isAls6208Client() && IconUtils.getInstance().isRoundStyle()) {
                    this.listAdpater.set(new BaseListAdpater<>(AppsLoaderTask.getInstance().getmListLiveData(), C0899R.C0902layout.round_app_item));
                } else {
                    this.listAdpater.set(new BaseListAdpater<>(AppsLoaderTask.getInstance().getmListLiveData(), C0899R.C0902layout.id7_app_item));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
