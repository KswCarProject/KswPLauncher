package com.wits.ksw.launcher.base;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import com.wits.ksw.BlackActivity;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.model.AppsLoaderTask;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class BaseViewModel extends ViewModel implements AppsLoaderTask.AppsLoaderTaskListener {
    private static final String TAG = BaseViewModel.class.getSimpleName();
    protected Activity activity;
    protected ContentResolver contentResolver;
    protected Context context;
    protected PackageManager mPackageManager;
    protected String unknow;
    public AppsLoaderTask appsLoaderTask = AppsLoaderTask.getInstance();
    private boolean igoFree = false;
    private boolean igoToFull = false;

    public abstract void resumeViewModel();

    public BaseViewModel() {
        Log.i(TAG, "BaseViewModel: ");
        Context context = KswApplication.appContext;
        this.context = context;
        if (this.unknow == null) {
            this.unknow = context.getString(17039374);
        }
        if (this.contentResolver == null) {
            this.contentResolver = this.context.getContentResolver();
        }
        if (this.mPackageManager == null) {
            this.mPackageManager = this.context.getPackageManager();
        }
        this.appsLoaderTask.setLoaderTaskListener(this);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    protected void openApp(ComponentName component) {
        try {
            Intent intent = new Intent();
            intent.setComponent(component);
            this.activity.startActivity(intent);
            Log.i(TAG, "openApp: " + component.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Context context = this.context;
            Toast.makeText(context, context.getString(C0899R.string.uninstall), 0).show();
        }
    }

    public void openAppTask(ComponentName component) {
        boolean isVersion12 = Build.VERSION.RELEASE.contains("12") || Build.VERSION.RELEASE.contains("13");
        String str = TAG;
        Log.w(str, "isVersion12 :" + isVersion12);
        if (component.getPackageName().contains("com.locnall.KimGiSa")) {
            this.context.getPackageManager().setApplicationEnabledSetting("com.google.android.gms", 3, 0);
            this.context.getPackageManager().setApplicationEnabledSetting("com.google.android.gms", 0, 0);
        }
        if (isVersion12 && AppInfoUtils.isContainFreedomMap(component.getPackageName()) && UiThemeUtils.isUI_GS_ID8(this.activity)) {
            launchApp(component.getPackageName(), 1);
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setComponent(component);
            intent.setFlags(268435456);
            this.activity.startActivity(intent);
            Log.i(str, "openApp: " + component.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Context context = this.context;
            Toast.makeText(context, context.getString(C0899R.string.uninstall), 0).show();
        }
    }

    public void launchApp(String packageName, int windowsMode) {
        if (packageName.contains("com.nng.igo.primong.palestine")) {
            handleIgoNavi(packageName, windowsMode);
        } else {
            launchAppByWindowMode(packageName, windowsMode);
        }
    }

    private void handleIgoNavi(final String packageName, final int windowsMode) {
        if (windowsMode == 5) {
            new Handler().postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.base.BaseViewModel.1
                @Override // java.lang.Runnable
                public void run() {
                    BaseViewModel.this.igoFree = true;
                    BaseViewModel.this.igoToFull = false;
                    BaseViewModel.this.launchAppByWindowMode(packageName, windowsMode);
                    new Handler().postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.base.BaseViewModel.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BaseViewModel.this.igoFree = false;
                            if (BaseViewModel.this.igoToFull) {
                                BaseViewModel.this.gotoFullByBlack(packageName, 1);
                            }
                        }
                    }, 2000L);
                }
            }, 200L);
        } else if (this.igoFree) {
            this.igoToFull = true;
        } else {
            this.igoToFull = false;
            gotoFullByBlack(packageName, windowsMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoFullByBlack(String packageName, int windowsMode) {
        if (windowsMode == 1) {
            this.activity.startActivity(new Intent(this.activity, BlackActivity.class));
        }
        launchAppByWindowMode(packageName, windowsMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchAppByWindowMode(String packageName, int windowsMode) {
        WitsCommand.sendCommand(20, 100, packageName + "," + windowsMode);
    }

    private Rect getDisplayRect(int windowsMode) {
        DisplayMetrics metric = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int screenWidth = metric.widthPixels;
        int screenHeight = metric.heightPixels;
        if (windowsMode == 5) {
            return new Rect(180, 120, 870, 648);
        }
        return new Rect(0, 0, screenWidth, screenHeight);
    }

    public void openApp(Intent intent) {
        try {
            this.activity.startActivity(intent);
            Log.i(TAG, "openApp: " + intent.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Context context = this.context;
            Toast.makeText(context, context.getString(C0899R.string.uninstall), 0).show();
        }
    }

    public void openMapApp(Intent intent) {
        try {
            this.activity.startActivity(intent);
            Log.i(TAG, "openApp: " + intent.toString());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                int googleApp = PowerManagerApp.getSettingsInt(KeyConfig.GOOGLE_APP);
                Log.d(TAG, "openApp: googleApp=" + googleApp);
                if (googleApp == 0) {
                    try {
                        this.activity.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.autonavi.amapauto"));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Context context = this.context;
                        Toast.makeText(context, context.getString(C0899R.string.uninstall), 0).show();
                    }
                } else {
                    Context context2 = this.context;
                    Toast.makeText(context2, context2.getString(C0899R.string.uninstall), 0).show();
                }
            } catch (RemoteException e1) {
                e1.printStackTrace();
                Context context3 = this.context;
                Toast.makeText(context3, context3.getString(C0899R.string.uninstall), 0).show();
            }
        }
    }

    @Override // com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void allAppsLoaded(List<AppInfo> appInfos) {
    }

    @Override // com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void updateShortcuts(List<AppInfo> shortcuts) {
    }

    @Override // com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void shortcutsLoaded(List<AppInfo> shortcuts) {
    }

    @Override // com.wits.ksw.launcher.model.AppsLoaderTask.AppsLoaderTaskListener
    public void dialogAppsListLoaded(List<AppInfo> dialoglist) {
    }

    @Override // android.arch.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
    }
}
