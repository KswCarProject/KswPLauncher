package com.wits.ksw;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.wits.pms.statuscontrol.PowerManagerApp;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

public class KswApplication extends Application {
    public static final String TAG = "KSWLauncher";
    public static Context appContext;

    public void onCreate() {
        appContext = this;
        Log.i(TAG, "App onCreate  name=1.0");
        initSkin();
        PowerManagerApp.init(appContext);
        super.onCreate();
    }

    public void onLowMemory() {
        super.onLowMemory();
        Log.i(TAG, "onLowMemory: ");
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory: level=" + level);
    }

    public void initSkin() {
        SkinCompatManager.withoutActivity(this).addInflater(new SkinMaterialViewInflater()).addInflater(new SkinConstraintViewInflater()).addInflater(new SkinCardViewInflater()).setSkinStatusBarColorEnable(false).setSkinWindowBackgroundEnable(false).loadSkin();
    }
}
