package com.wits.ksw;

import android.app.Application;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.bmw_id8_ui.GSID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.utils.SpfUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.pms.statuscontrol.PowerManagerApp;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

public class KswApplication extends Application {
    public static final String SKIN_NAME = "SkinName";
    public static final String TAG = "KswApplication";
    public static Context appContext;
    public static boolean factoryUsbHost = true;
    static SkinCompatManager.SkinLoaderListener listener = new SkinCompatManager.SkinLoaderListener() {
        public void onStart() {
            Log.w(KswApplication.TAG, "onStart: ");
        }

        public void onSuccess() {
            Log.w(KswApplication.TAG, "onSuccess: ");
        }

        public void onFailed(String errMsg) {
            Log.w(KswApplication.TAG, "onFailed: " + errMsg);
        }
    };
    public ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange, Uri uri) {
            Log.d(KswApplication.TAG, "onChange: 11111111111");
            KswApplication.getSkinNameAndRefresh();
        }
    };

    public void onCreate() {
        appContext = this;
        Log.i(TAG, "App onCreate  name=1.0");
        SpfUtils.init(this);
        PowerManagerApp.init(appContext);
        ID8LauncherConstants.init(appContext);
        GSID8LauncherConstants.init(appContext);
        if (UiThemeUtils.isALS_ID7_UI(this) || UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this)) {
            initSkin();
            getSkinNameAndRefresh();
            registerSkinObserver();
        }
        super.onCreate();
    }

    public static void getSkinNameAndRefresh() {
        String skinName = null;
        if (UiThemeUtils.isALS_ID7_UI(appContext)) {
            skinName = Settings.System.getString(appContext.getContentResolver(), SKIN_NAME);
            if (TextUtils.isEmpty(skinName)) {
                skinName = ID8LauncherConstants.ID8_SKIN_EFFICIENT;
            }
        }
        if (UiThemeUtils.isBMW_ID8_UI(appContext) || UiThemeUtils.isUI_GS_ID8(appContext)) {
            skinName = ID8LauncherConstants.loadCurrentSkin();
            if (TextUtils.isEmpty(skinName)) {
                skinName = ID8LauncherConstants.ID8_SKIN_PERSONAL;
            }
        }
        Log.d(TAG, "getSkinName:  skinName= " + skinName);
        if (!TextUtils.isEmpty(skinName)) {
            if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
                SkinCompatManager.getInstance().loadSkin("red.skin", listener, 0);
            } else if (ID8LauncherConstants.ID8_SKIN_PERSONAL.equals(skinName)) {
                SkinCompatManager.getInstance().loadSkin("yellow.skin", listener, 0);
            } else {
                SkinCompatManager.getInstance().restoreDefaultTheme();
            }
        }
    }

    private void registerSkinObserver() {
        getContentResolver().registerContentObserver(Settings.System.getUriFor(SKIN_NAME), true, this.contentObserver);
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
        SkinCompatManager.withoutActivity(this).addInflater(new SkinMaterialViewInflater()).addInflater(new SkinConstraintViewInflater()).addInflater(new SkinCardViewInflater()).setSkinStatusBarColorEnable(false).setSkinWindowBackgroundEnable(false);
    }
}
