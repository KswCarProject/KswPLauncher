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
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseListAdpater;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.dabebase.AppInfoRoomDatabase;
import com.wits.ksw.launcher.dabebase.AppList;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.IconUtils;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.view.DragGridView;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.ArrayList;
import java.util.List;

public final class AppViewModel extends LauncherViewModel {
    public static final String AUX_TYPE = "AUX_Type";
    private static final String DESKCLOCK_PKG = "com.android.deskclock";
    public static final String DTV_TYPE = "DTV_Type";
    public static final String DVR_TYPE = "DVR_Type";
    public static final String F_CAM_Type = "Front_view_camera";
    private static final String GAODE_MAP_PKG = "com.autonavi.amapauto";
    private static final String GOOGLE_SEARCH_PKG = "com.google.android.googlequicksearchbox";
    private static final String IFLYTEK_PKG = "com.iflytek.inputmethod.google";
    private static final String SPEEDPLAY_PKG = "com.suding.speedplay";
    private static final String ZLINK_PKG = "com.zjinnova.zlink";
    /* access modifiers changed from: private */
    public static Drawable auxIcon = KswApplication.appContext.getDrawable(R.drawable.ic_aux);
    /* access modifiers changed from: private */
    public static String auxLable = KswApplication.appContext.getString(R.string.app_aux);
    /* access modifiers changed from: private */
    public static Drawable dtvIcon = KswApplication.appContext.getDrawable(R.drawable.ic_dtv);
    /* access modifiers changed from: private */
    public static String dtvLable = KswApplication.appContext.getString(R.string.app_dtv);
    /* access modifiers changed from: private */
    public static Drawable dvrIcon = KswApplication.appContext.getDrawable(R.drawable.ic_dvr);
    /* access modifiers changed from: private */
    public static String dvrLable = KswApplication.appContext.getString(R.string.app_dvr);
    /* access modifiers changed from: private */
    public static Drawable fcamIcon = KswApplication.appContext.getDrawable(R.drawable.icon_fcam);
    /* access modifiers changed from: private */
    public static String fcamLable = KswApplication.appContext.getString(R.string.app_fcam);
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            AppViewModel.this.queryApps();
        }
    };
    public ObservableField<BaseListAdpater<AppInfo>> listAdpater = new ObservableField<>();
    List<AppList> mAppLists = new ArrayList();
    List<AppInfo> mListLiveData = new ArrayList();
    public DragGridView.onItemChangerListener onItemChangerListener = new DragGridView.onItemChangerListener() {
        public void onChange(GridView gridView, int from, int to) {
            Log.i(LauncherViewModel.TAG, "onChange: ");
            AppViewModel.this.mListLiveData.set(from, AppViewModel.this.mListLiveData.get(to));
            AppViewModel.this.mListLiveData.set(to, AppViewModel.this.mListLiveData.get(from));
            AppViewModel.this.updateItem(gridView, to);
            AppViewModel.this.updateItem(gridView, from);
            AppViewModel.this.SaveAppList();
        }

        public void onStartMoving() {
            Log.i(LauncherViewModel.TAG, "onStartMoving: ");
            try {
                String topApp = PowerManagerApp.getManager().getStatusString("topApp");
                String str = LauncherViewModel.TAG;
                Log.i(str, "onStartMoving: topApp=" + topApp);
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
    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
            if (TextUtils.equals(appInfo.getApppkg(), "AUX_Type")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_AUX);
            } else if (TextUtils.equals(appInfo.getApppkg(), "DTV_Type")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_DTV);
            } else if (TextUtils.equals(appInfo.getApppkg(), "DVR_Type")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_CVBSDVR);
            } else if (TextUtils.equals(appInfo.getApppkg(), "Front_view_camera")) {
                AppViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.OPEN_F_CAM);
            } else {
                AppViewModel.this.openAppTask(new ComponentName(appInfo.getApppkg(), appInfo.getClassName()));
            }
        }
    };
    public AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
            Log.i("AppViewModel", "onItemLongClick: " + appInfo.toString());
            if (TextUtils.equals(appInfo.getApppkg(), "AUX_Type") || TextUtils.equals(appInfo.getApppkg(), "DTV_Type") || TextUtils.equals(appInfo.getApppkg(), "DVR_Type") || KswUtils.isSystemapp(appInfo.getApppkg()) || TextUtils.equals(appInfo.getApppkg(), "Front_view_camera")) {
                Toast.makeText(view.getContext(), view.getContext().getString(R.string.ksw_id7_system_app_not_uninstall), 0).show();
                return true;
            }
            AppViewModel.this.uninstallAppIntent(appInfo.getApppkg(), view.getContext());
            return true;
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

    /* access modifiers changed from: private */
    public void uninstallAppIntent(String pkg, Context context) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + pkg));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

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
                    if (!AppViewModel.this.filterAppDisplay(packageName)) {
                        AppInfo appInfo = new AppInfo();
                        Drawable iconDrawable = resolveInfo.loadIcon(pm);
                        appInfo.setAppIcon(iconDrawable);
                        String label = resolveInfo.loadLabel(pm).toString();
                        appInfo.setAppLable(label);
                        appInfo.setApppkg(packageName);
                        appInfo.setClassName(resolveInfo.activityInfo.name);
                        Log.d("AAAAA", packageName + "  " + label);
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

        /* access modifiers changed from: protected */
        public List<AppInfo> doInBackground(Void... voids) {
            return queryApps();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<AppInfo> listLiveData) {
            super.onPostExecute(listLiveData);
            AppViewModel.this.addCustomApp(listLiveData, "AUX_Type", AppViewModel.auxIcon, AppViewModel.auxLable);
            AppViewModel.this.addCustomApp(listLiveData, "DTV_Type", AppViewModel.dtvIcon, AppViewModel.dtvLable);
            AppViewModel.this.addCustomApp(listLiveData, "DVR_Type", AppViewModel.dvrIcon, AppViewModel.dvrLable);
            AppViewModel.this.addCustomApp(listLiveData, "Front_view_camera", AppViewModel.fcamIcon, AppViewModel.fcamLable);
            AppViewModel.this.mListLiveData.clear();
            for (int i = 0; i < AppViewModel.this.mAppLists.size(); i++) {
                int j = 0;
                while (true) {
                    if (j >= listLiveData.size()) {
                        break;
                    } else if (AppViewModel.this.mAppLists.get(i).getClassName().equals(listLiveData.get(j).getClassName())) {
                        AppViewModel.this.mListLiveData.add(listLiveData.get(j));
                        listLiveData.remove(j);
                        break;
                    } else {
                        j++;
                    }
                }
            }
            for (AppInfo appInfo : listLiveData) {
                AppViewModel.this.mListLiveData.add(appInfo);
            }
            AppViewModel.this.SaveAppList();
            if (!ClientManager.getInstance().isAls6208Client() || !IconUtils.getInstance().isRoundStyle()) {
                AppViewModel.this.listAdpater.set(new BaseListAdpater(AppViewModel.this.mListLiveData, R.layout.id7_app_item));
            } else {
                AppViewModel.this.listAdpater.set(new BaseListAdpater(AppViewModel.this.mListLiveData, R.layout.round_app_item));
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadRoundRectIcon(String packageName, AppInfo appInfo, Drawable drawable, String label) {
        appInfo.setAppIcon(IconUtils.getInstance().getIcon(packageName, appInfo, drawable, label));
    }

    /* access modifiers changed from: private */
    public boolean filterAppDisplay(String packageName) {
        if (packageName.contains(KswApplication.appContext.getPackageName()) || packageName.contains(getClass().getPackage().toString()) || packageName.contains(IFLYTEK_PKG) || packageName.contains(DESKCLOCK_PKG)) {
            return true;
        }
        int speed_play_switch = Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1);
        if (speed_play_switch != 2 && packageName.contains(SPEEDPLAY_PKG)) {
            return true;
        }
        if (speed_play_switch != 2 || !packageName.contains(ZLINK_PKG)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void addCustomApp(List<AppInfo> listLiveData, String type, Drawable auxIcon2, String auxLable2) {
        try {
            if (PowerManagerApp.getSettingsInt(type) == 1) {
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

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        KswApplication.appContext.unregisterReceiver(this.broadcastReceiver);
    }

    /* access modifiers changed from: private */
    public synchronized void SaveAppList() {
        final List<AppList> newAppList = new ArrayList<>();
        for (AppInfo appInfo : this.mListLiveData) {
            AppList appList = new AppList();
            appList.setClassName(appInfo.getClassName());
            newAppList.add(appList);
            if (TextUtils.isEmpty(appInfo.getClassName())) {
                String str = TAG;
                Log.e(str, "SaveAppList: appinfo ClassName isEmpty AppLable = " + appInfo.getAppLable());
                return;
            }
        }
        new Thread(new Runnable() {
            public void run() {
                AppInfoRoomDatabase.getDatabase(KswApplication.appContext).getAppInfoDao().deleteAll();
                AppInfoRoomDatabase.getDatabase(KswApplication.appContext).getAppInfoDao().insert(newAppList);
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void updateItem(GridView gridView, int position) {
        int firstVisiblePosition = gridView.getFirstVisiblePosition();
        int lastVisiblePosition = gridView.getLastVisiblePosition();
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            this.listAdpater.get().getView(position, gridView.getChildAt(position - firstVisiblePosition), gridView);
        }
    }
}
