package com.wits.ksw;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class KswApplication extends Application {
    public static final String TAG = "KSWLauncher";
    public static Context appContext;

    public void onCreate() {
        appContext = this;
        Log.i(TAG, "App onCreate");
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
}
