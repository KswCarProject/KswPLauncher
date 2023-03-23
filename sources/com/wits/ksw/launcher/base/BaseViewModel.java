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
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;

public abstract class BaseViewModel extends ViewModel {
    private static final String TAG = BaseViewModel.class.getSimpleName();
    /* access modifiers changed from: protected */
    public Activity activity;
    /* access modifiers changed from: protected */
    public ContentResolver contentResolver;
    /* access modifiers changed from: protected */
    public Context context;
    protected PackageManager mPackageManager;
    protected String unknow;

    public abstract void resumeViewModel();

    public BaseViewModel() {
        Log.i(TAG, "BaseViewModel: ");
        Context context2 = KswApplication.appContext;
        this.context = context2;
        if (this.unknow == null) {
            this.unknow = context2.getString(17039374);
        }
        if (this.contentResolver == null) {
            this.contentResolver = this.context.getContentResolver();
        }
        if (this.mPackageManager == null) {
            this.mPackageManager = this.context.getPackageManager();
        }
    }

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    /* access modifiers changed from: protected */
    public void openApp(ComponentName component) {
        try {
            Intent intent = new Intent();
            intent.setComponent(component);
            this.activity.startActivity(intent);
            Log.i(TAG, "openApp: " + component.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Context context2 = this.context;
            Toast.makeText(context2, context2.getString(R.string.uninstall), 0).show();
        }
    }

    public void openAppTask(ComponentName component) {
        boolean isVersion12 = Build.VERSION.RELEASE.contains("12");
        String str = TAG;
        Log.w(str, "isVersion12 :" + isVersion12);
        if (!isVersion12 || !AppInfoUtils.isContainFreedomMap(component.getPackageName())) {
            try {
                Intent intent = new Intent();
                intent.setComponent(component);
                intent.setFlags(268435456);
                this.activity.startActivity(intent);
                Log.i(str, "openApp: " + component.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Context context2 = this.context;
                Toast.makeText(context2, context2.getString(R.string.uninstall), 0).show();
            }
        } else {
            launchApp(component.getPackageName(), 1);
        }
    }

    public void launchApp(String packageName, int windowsMode) {
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
            Context context2 = this.context;
            Toast.makeText(context2, context2.getString(R.string.uninstall), 0).show();
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
                        Context context2 = this.context;
                        Toast.makeText(context2, context2.getString(R.string.uninstall), 0).show();
                    }
                } else {
                    Context context3 = this.context;
                    Toast.makeText(context3, context3.getString(R.string.uninstall), 0).show();
                }
            } catch (RemoteException e1) {
                e1.printStackTrace();
                Context context4 = this.context;
                Toast.makeText(context4, context4.getString(R.string.uninstall), 0).show();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
    }
}
