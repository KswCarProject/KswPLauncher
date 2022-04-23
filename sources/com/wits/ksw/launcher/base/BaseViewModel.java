package com.wits.ksw.launcher.base;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;

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

    /* access modifiers changed from: protected */
    public void openAppTask(ComponentName component) {
        try {
            Intent intent = new Intent();
            intent.setComponent(component);
            intent.setFlags(268435456);
            this.activity.startActivity(intent);
            Log.i(TAG, "openApp: " + component.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Context context2 = this.context;
            Toast.makeText(context2, context2.getString(R.string.uninstall), 0).show();
        }
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

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
    }
}
