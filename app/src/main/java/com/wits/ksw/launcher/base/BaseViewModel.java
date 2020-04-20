package com.wits.ksw.launcher.base;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.util.Log;
import android.widget.Toast;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.AppsActivity;

@BindingMethods({@BindingMethod(attribute = "setOnClickListener", method = "onClickListener", type = BaseViewModel.class), @BindingMethod(attribute = "setOnCheckedChangeListener", method = "onCheckedChangeListener", type = BaseViewModel.class), @BindingMethod(attribute = "setOnFocusChangeListener", method = "onFocusChangeListener", type = BaseViewModel.class), @BindingMethod(attribute = "setOnItemClickListener", method = "onItemClickListener", type = AppsActivity.class), @BindingMethod(attribute = "setOnItemLongClickListener", method = "onItemLongClickListener", type = AppsActivity.class), @BindingMethod(attribute = "setOnItemChangeListener", method = "onItemChangerListener", type = AppsActivity.class), @BindingMethod(attribute = "setOnSeekBarChangeListener", method = "onSeekBarChangeListener", type = BaseViewModel.class)})
public abstract class BaseViewModel extends ViewModel {
    protected static final String TAG = "KSWLauncher";
    private Activity activity;
    /* access modifiers changed from: protected */
    public ContentResolver contentResolver;
    /* access modifiers changed from: protected */
    public Context context = KswApplication.appContext;
    protected PackageManager mPackageManager;
    protected String unknow;

    public abstract void resumeViewModel();

    public BaseViewModel() {
        Log.i("KSWLauncher", "BaseViewModel: ");
        if (this.unknow == null) {
            this.unknow = this.context.getString(17039374);
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
            Log.i("KSWLauncher", "openApp: " + component.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.context, this.context.getString(R.string.uninstall), 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void openApp(Intent intent) {
        try {
            this.activity.startActivity(intent);
            Log.i("KSWLauncher", "openApp: " + intent.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.context, this.context.getString(R.string.uninstall), 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i("KSWLauncher", "onCleared: ");
    }
}
