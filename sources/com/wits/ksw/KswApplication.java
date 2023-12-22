package com.wits.ksw;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.wits.ksw.launcher.bmw_id8_ui.GSID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.PempID8LauncherConstants;
import com.wits.ksw.launcher.model.AppsLoaderTask;
import com.wits.ksw.launcher.utils.SpfUtils;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/* loaded from: classes17.dex */
public class KswApplication extends Application {
    public static final String SKIN_NAME = "SkinName";
    public static final String TAG = "KswApplication";
    public static Context appContext;
    public static boolean factoryUsbHost = true;
    static SkinCompatManager.SkinLoaderListener listener = new SkinCompatManager.SkinLoaderListener() { // from class: com.wits.ksw.KswApplication.2
        @Override // skin.support.SkinCompatManager.SkinLoaderListener
        public void onStart() {
            Log.w(KswApplication.TAG, "onStart: ");
        }

        @Override // skin.support.SkinCompatManager.SkinLoaderListener
        public void onSuccess() {
            Log.w(KswApplication.TAG, "onSuccess: ");
        }

        @Override // skin.support.SkinCompatManager.SkinLoaderListener
        public void onFailed(String errMsg) {
            Log.w(KswApplication.TAG, "onFailed: " + errMsg);
        }
    };
    public ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.KswApplication.3
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            Log.d(KswApplication.TAG, "onChange: 11111111111");
            KswApplication.getSkinNameAndRefresh();
        }
    };
    private KswBroadcastReceiver mKswBroadcastReceiver;

    @Override // android.app.Application
    public void onCreate() {
        appContext = this;
        Log.i(TAG, "App onCreate  name=1.0");
        SpfUtils.init(this);
        PowerManagerApp.init(appContext);
        registerKswBroadcastReceiver();
        loadAppsData();
        ID8LauncherConstants.init(appContext);
        GSID8LauncherConstants.init(appContext);
        PempID8LauncherConstants.init(appContext);
        if (UiThemeUtils.isUiChanged(this) && (UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this) || UiThemeUtils.isUI_PEMP_ID8(this))) {
            Settings.System.putString(appContext.getContentResolver(), ID8LauncherConstants.ID8_SKIN, ID8LauncherConstants.ID8_SKIN_PERSONAL);
        }
        if (UiThemeUtils.isALS_ID7_UI(this) || UiThemeUtils.isBMW_ID8_UI(this) || UiThemeUtils.isUI_GS_ID8(this) || UiThemeUtils.isUI_PEMP_ID8(this)) {
            initSkin();
            getSkinNameAndRefresh();
            registerSkinObserver();
        }
        registerTopAppObserver();
        super.onCreate();
    }

    private void registerTopAppObserver() {
        IContentObserver topAppObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.KswApplication.1
            @Override // com.wits.pms.IContentObserver
            public void onChange() throws RemoteException {
                try {
                    String topApp = PowerManagerApp.getStatusString("topApp");
                    Log.i(KswApplication.TAG, "topAppObserver: " + topApp);
                    if (!TextUtils.isEmpty(topApp) && topApp.contains("com.kugou.android")) {
                        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        PowerManagerApp.registerIContentObserver("topApp", topAppObserver);
    }

    public static void getSkinNameAndRefresh() {
        String skinName = null;
        if (UiThemeUtils.isALS_ID7_UI(appContext)) {
            skinName = Settings.System.getString(appContext.getContentResolver(), SKIN_NAME);
            if (TextUtils.isEmpty(skinName)) {
                skinName = ID8LauncherConstants.ID8_SKIN_EFFICIENT;
            }
        }
        if (UiThemeUtils.isBMW_ID8_UI(appContext) || UiThemeUtils.isUI_GS_ID8(appContext) || UiThemeUtils.isUI_PEMP_ID8(appContext)) {
            skinName = ID8LauncherConstants.loadCurrentSkin();
            if (TextUtils.isEmpty(skinName)) {
                skinName = ID8LauncherConstants.ID8_SKIN_PERSONAL;
            }
        }
        Log.d(TAG, "getSkinName:  skinName= " + skinName);
        if (TextUtils.isEmpty(skinName)) {
            return;
        }
        if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            SkinCompatManager.getInstance().loadSkin("red.skin", listener, 0);
        } else if (ID8LauncherConstants.ID8_SKIN_PERSONAL.equals(skinName)) {
            SkinCompatManager.getInstance().loadSkin("yellow.skin", listener, 0);
        } else {
            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
    }

    private void registerSkinObserver() {
        getContentResolver().registerContentObserver(Settings.System.getUriFor(SKIN_NAME), true, this.contentObserver);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        Log.i(TAG, "onLowMemory: ");
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory: level=" + level);
    }

    public void initSkin() {
        SkinCompatManager.withoutActivity(this).addInflater(new SkinMaterialViewInflater()).addInflater(new SkinConstraintViewInflater()).addInflater(new SkinCardViewInflater()).setSkinStatusBarColorEnable(false).setSkinWindowBackgroundEnable(false);
    }

    private void registerKswBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ksw.mcu.send");
        KswBroadcastReceiver kswBroadcastReceiver = new KswBroadcastReceiver();
        this.mKswBroadcastReceiver = kswBroadcastReceiver;
        registerReceiver(kswBroadcastReceiver, intentFilter);
    }

    /* loaded from: classes17.dex */
    public class KswBroadcastReceiver extends BroadcastReceiver {
        private static final String MCU_ACTION = "readver";
        private static final String MCU_FACTORY_CONFIG = "update.factory_config";
        private static final String MCU_START = "update.start";
        private static final String MCU_STATUS = "update.ready";
        private DialogViews dialogViews;

        public KswBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            this.dialogViews = new DialogViews(context);
            Intent intent_recv = new Intent("com.ksw.mcu.recv");
            if (intent.getAction().equals("com.ksw.mcu.send")) {
                if (!intent.getStringExtra("action").equals(MCU_ACTION)) {
                    if (intent.getStringExtra("action").equals(MCU_START)) {
                        Intent intent1 = new Intent(context, McuDialogActivity.class);
                        intent1.setFlags(268435456);
                        context.startActivity(intent1);
                        return;
                    } else if (intent.getStringExtra("action").equals(MCU_FACTORY_CONFIG)) {
                        this.dialogViews.updateDefXml();
                        return;
                    } else {
                        return;
                    }
                }
                String mvformat = McuUtil.getMcuVersion();
                intent_recv.putExtra("action", "mcuver");
                intent_recv.putExtra("value", mvformat);
                context.sendBroadcast(intent_recv);
            }
        }
    }

    private void loadAppsData() {
        Log.i(TAG, "loadAppsData: ");
        AppsLoaderTask.getInstance().queryAllApps();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        loadAppsData();
    }
}
