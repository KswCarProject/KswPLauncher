package com.wits.ksw.launcher.model;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.ibm.icu.text.ArabicShaping;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.adpater.AppsGridAdapter;
import com.wits.ksw.launcher.base.BaseViewModel;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.NaviInfo;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LauncherViewModel extends BaseViewModel {
    public static final String CLS_CHROME = "com.google.android.apps.chrome.Main";
    public static final int IMG_VIDEO_HEIGT = 52;
    public static final int IMG_VIDEO_WIDTH = 58;
    public static final String KEY_SHORTCUT_CLS_1 = "KEY_SHORTCUT_CLS_1";
    public static final String KEY_SHORTCUT_CLS_2 = "KEY_SHORTCUT_CLS_2";
    public static final String KEY_SHORTCUT_CLS_3 = "KEY_SHORTCUT_CLS_3";
    public static final String KEY_SHORTCUT_PKG_1 = "KEY_SHORTCUT_PKG_1";
    public static final String KEY_SHORTCUT_PKG_2 = "KEY_SHORTCUT_PKG_2";
    public static final String KEY_SHORTCUT_PKG_3 = "KEY_SHORTCUT_PKG_3";
    /* access modifiers changed from: protected */
    public static final String TAG = LauncherViewModel.class.getSimpleName();
    public static final int WIDTH_IMG_BIG = 64;
    public static final int WIDTH_IMG_SMALL = 45;
    private static HashMap<Integer, ObjectAnimator> animatorMaps = new HashMap<>();
    public static ObservableField<Boolean> bThirdMusic = new ObservableField<>();
    public static ObservableField<Boolean> bThirdVideo = new ObservableField<>();
    public static CarInfo carInfo = McuImpl.getInstance().getCarInfo();
    public static MediaInfo mediaInfo = MediaImpl.getInstance().getMediaInfo();
    public static ObservableField<Drawable> musicBG = new ObservableField<>();
    public static ObservableField<Integer> screenHeight = new ObservableField<>();
    public static ObservableField<String> screenPixels = new ObservableField<>();
    public static ObservableField<Integer> screenWidth = new ObservableField<>();
    public static ObservableField<Drawable> videoBG = new ObservableField<>();
    public static volatile View viewLastSel;
    public static int width = 0;
    public final ObservableBoolean acControl = new ObservableBoolean();
    public List<View> alsID7UIViewList = new ArrayList();
    public ObservableField<String> btState = new ObservableField<>();
    public View.OnFocusChangeListener carViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(2);
            }
        }
    };
    public final ObservableField<String> day = new ObservableField<>();
    public int dialogHeight;
    public int dialogWidth;
    public final ObservableField<Boolean> hicar = new ObservableField<>();
    public View lastViewFocused;
    public PopupWindow mAppsPopupWindow;
    public final ObservableField<String> month = new ObservableField<>();
    public View.OnFocusChangeListener musicViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: musicViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public NaviInfo naviInfo = new NaviInfo();
    protected BroadcastReceiver otherReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (NaviInfo.AUTONAVI_STANDARD_BROADCAST_SEND.equalsIgnoreCase(intent.getAction()) && LauncherViewModel.this.naviInfo.isGuideEnable()) {
                LauncherViewModel.this.refreshNaviInfo(intent);
            }
        }
    };
    public final ObservableInt phoneConState = new ObservableInt();
    public View.OnFocusChangeListener phoneViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(0);
                Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener");
            }
        }
    };
    public ObservableField<Drawable> shortCutIcon1 = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon2 = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon3 = new ObservableField<>();
    public ObservableField<String> shortCutName1 = new ObservableField<>();
    public ObservableField<String> shortCutName2 = new ObservableField<>();
    public ObservableField<String> shortCutName3 = new ObservableField<>();
    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LauncherViewModel.this.setMonthDay(new Date());
        }
    };
    public View.OnFocusChangeListener videoViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: videoViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };

    public static void setThirdMusic(boolean b) {
        bThirdMusic.set(Boolean.valueOf(b));
    }

    public static void setThirdVideo(boolean b) {
        bThirdVideo.set(Boolean.valueOf(b));
    }

    public void initThirdApps() {
        String clsMusic = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
        String clsVideo = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS);
        if (TextUtils.isEmpty(clsMusic) || KeyConfig.CLS_LOCAL_MUSIC.equals(clsMusic)) {
            bThirdMusic.set(false);
        } else {
            bThirdMusic.set(true);
        }
        if (TextUtils.isEmpty(clsVideo) || KeyConfig.CLS_LOCAL_VIDEO.equals(clsVideo)) {
            bThirdVideo.set(false);
        } else {
            bThirdVideo.set(true);
        }
    }

    public LauncherViewModel() {
        Log.i(TAG, "LauncherViewModel:");
    }

    public void resumeViewModel() {
        Log.d(TAG, "resumeViewModel");
        refreshLastViewFocused();
        refreshLexus();
        if (UiThemeUtils.isALS_ID7_UI(this.context)) {
            refreshMusicIconOrName();
        }
    }

    private void refreshMusicIconOrName() {
        String pkg;
        String cls;
        try {
            pkg = Settings.System.getString(this.context.getContentResolver(), KEY_SHORTCUT_PKG_1);
            cls = Settings.System.getString(this.context.getContentResolver(), KEY_SHORTCUT_CLS_1);
        } catch (Exception e) {
            e.printStackTrace();
            pkg = null;
            cls = null;
        }
        if (cls != null && pkg != null) {
            if (cls.equals(CLS_CHROME)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
                this.shortCutName1.set(this.context.getResources().getString(R.string.id7_browser));
            } else if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_music));
                this.shortCutName1.set(this.context.getResources().getString(R.string.ksw_id7_music));
            } else if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
                this.shortCutName1.set(this.context.getResources().getString(R.string.ksw_id7_hd_video));
            } else if (cls.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName1.set(this.activity.getResources().getString(R.string.ksw_id7_navi));
            } else {
                ResolveInfo info1 = AppInfoUtils.findAppByPkgAndCls(this.context, pkg, cls);
                ObservableField<Drawable> observableField = this.shortCutIcon1;
                Drawable loadIcon = info1.activityInfo.loadIcon(this.context.getPackageManager());
                float f = 64.0f;
                int dip2px = AppInfoUtils.dip2px(this.context, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f);
                Context context = this.context;
                if (screenWidth.get().intValue() <= 1280) {
                    f = 45.0f;
                }
                observableField.set(AppInfoUtils.zoomDrawable(loadIcon, dip2px, AppInfoUtils.dip2px(context, f)));
                this.shortCutName1.set((String) info1.activityInfo.loadLabel(this.context.getPackageManager()));
            }
        }
    }

    private void refreshLexus() {
        if (!UiThemeUtils.isLEXUS_UI(this.context)) {
            return;
        }
        if (Settings.System.getInt(this.context.getContentResolver(), KeyConfig.AC_CONTROL, 0) == 1) {
            this.acControl.set(true);
        } else {
            this.acControl.set(false);
        }
    }

    public void initData() {
        int bluetooth = Settings.System.getInt(this.contentResolver, "ksw_bluetooth", 0);
        Log.i(TAG, "initData: bluetooth:" + bluetooth);
        setPhoneConState(bluetooth);
        setBtState();
        setMonthDay(new Date());
        registerTimeReceiver();
        registerOtherReceiver();
        registerBtContentObserver();
        MediaImpl.getInstance().initData();
        McuImpl.getInstance().init();
        initThirdApps();
        width = this.context.getResources().getDisplayMetrics().widthPixels;
    }

    public void onItemClick(View view, String pkg, String cls) {
        addLastViewFocused(view);
        onItemClick(pkg, cls);
    }

    public void onItemClick(String pkg, String cls) {
        openApp(new ComponentName(pkg, cls));
        Log.i(TAG, "onItemClick: " + pkg + "/" + cls);
    }

    public void onItemClick(View view, String action) {
        addLastViewFocused(view);
        onItemClick(action);
    }

    public void onItemClick(String action) {
        openApp(new Intent(action));
    }

    public void openDvr(View view) {
        addLastViewFocused(view);
        if (KswUtils.getDvrType() == 1) {
            onSendCommand(1, WitsCommand.SystemCommand.OPEN_CVBSDVR);
            Log.i(TAG, "openDvr: onSendCommand:609");
        } else if (KswUtils.getDvrType() == 2) {
            String usbPkg = KswUtils.getUsbDvrPkg();
            Log.i(TAG, "openDvr: usbPkg:" + usbPkg);
            if (!TextUtils.isEmpty(usbPkg)) {
                if (KeyConfig.CLS_LOCAL_MUSIC.equals(usbPkg)) {
                    openApp(new Intent("com.wits.media.MUSIC"));
                } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(usbPkg)) {
                    openApp(new Intent("com.wits.media.VIDEO"));
                } else {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage(usbPkg));
                }
            }
        }
    }

    public void openShouJiHuLian(View view) {
        addLastViewFocused(view);
        if (!Build.DISPLAY.contains("8937")) {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
        } else if (Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1) == 2) {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.suding.speedplay"));
        } else {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
        }
    }

    public void openEq(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName("com.wits.csp.eq", "com.wits.csp.eq.view.MainActivity"));
    }

    public void openBrowser(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName("com.android.chrome", CLS_CHROME));
    }

    public void openAirControl(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName("com.wits.ksw.airc", "com.wits.ksw.airc.LexusAirControl"));
    }

    public void openFileManager(View view) {
        addLastViewFocused(view);
        openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop"));
    }

    public void openApps(View view) {
        openApp(new Intent("com.wits.ksw.ACTION_APPS"));
        addLastViewFocused(view);
        try {
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openAllApp(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.AppsActivity"));
        try {
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCar(View view) {
        onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
        addLastViewFocused(view);
    }

    public void openLexusCar(View view) {
        if (Settings.System.getInt(this.activity.getContentResolver(), KeyConfig.OEM_FM, 0) == 0) {
            onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
        } else {
            openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.lexus.OEMFMActivity"));
        }
        addLastViewFocused(view);
    }

    public void openSettings(View view) {
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity"));
        addLastViewFocused(view);
    }

    public void openChoseMusic(View view) {
        addLastViewFocused(view);
        if (UiThemeUtils.isBMW_EVO_ID7(this.context) || UiThemeUtils.isBMW_EVO_ID7_HiCar(this.context) || UiThemeUtils.isCommon_UI_GS_UG(this.context) || UiThemeUtils.isCommon_UI_GS_UG_1024(this.context) || UiThemeUtils.isAudi_mib3(this.context) || UiThemeUtils.isAudi_mib3_FY(this.context) || UiThemeUtils.isALS_ID7_UI(this.context) || ((UiThemeUtils.isBMW_EVO_ID6_CUSP(this.context) && !ClientManager.getInstance().isCUSP_210407()) || UiThemeUtils.isID7_ALS(this.context))) {
            String pkg = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG);
            String cls = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
            Log.i(TAG, "openMusic: pkg=" + pkg);
            if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls) || KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
                openApp(new Intent("com.wits.media.MUSIC"));
            } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
                openApp(new Intent("com.wits.media.VIDEO"));
            } else {
                Intent intent = this.context.getPackageManager().getLaunchIntentForPackage(pkg);
                if (intent == null) {
                    openApp(new Intent("com.wits.media.MUSIC"));
                    return;
                }
                openApp(intent);
                try {
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            String pkg2 = Settings.System.getString(this.contentResolver, "defPlayApp");
            Log.i(TAG, "openMusic: pkg=" + pkg2);
            if (TextUtils.isEmpty(pkg2)) {
                pkg2 = "com.wits.media.MUSIC";
            }
            if (pkg2.equals("com.wits.ksw.media")) {
                openApp(new Intent("com.wits.media.MUSIC"));
                return;
            }
            Intent intent2 = this.context.getPackageManager().getLaunchIntentForPackage(pkg2);
            if (intent2 == null) {
                openApp(new Intent("com.wits.media.MUSIC"));
                return;
            }
            openApp(intent2);
            try {
                WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void openMusic(View view) {
        addLastViewFocused(view);
        openApp(new Intent("com.wits.media.MUSIC"));
    }

    public void openMusicMulti(View view) {
        String pkg = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG);
        String cls = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
        addLastViewFocused(view);
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls) || KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
            openMusic(view);
        } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
            openVideo(view);
        } else {
            openAppByCls(new ComponentName(pkg, cls));
        }
    }

    public void openVideoMulti(View view) {
        String pkg = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG);
        String cls = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS);
        Log.d("openVideoMulti", "cls = " + cls);
        addLastViewFocused(view);
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls) || KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
            openVideo(view);
        } else if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
            openMusic(view);
        } else {
            openAppByCls(new ComponentName(pkg, cls));
        }
    }

    public void openAppByCls(ComponentName component) {
        try {
            Log.i(TAG + " openAppByCls ", "11111111111111111 openApp: " + component.toString());
            Intent intent = new Intent();
            intent.setComponent(component);
            intent.setFlags(268435456);
            this.activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.context, this.context.getString(R.string.uninstall), 0).show();
        }
    }

    public void openVideo(View view) {
        addLastViewFocused(view);
        openApp(new Intent("com.wits.media.VIDEO"));
    }

    public void openShortCutApp(View view, int index) {
        Log.d("openShortCutApp", index + "   ");
        String pkg1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_PKG_1);
        String cls1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_CLS_1);
        String pkg2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_PKG_2);
        String cls2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_CLS_2);
        String pkg3 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_PKG_3);
        String cls3 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_CLS_3);
        switch (index) {
            case 1:
                if (!TextUtils.isEmpty(pkg1) && !TextUtils.isEmpty(cls1)) {
                    Log.d("openShortCutApp1", pkg1 + "   " + cls1);
                    if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls1)) {
                        openMusic(view);
                        return;
                    } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls1)) {
                        openVideo(view);
                        return;
                    } else {
                        openAppByCls(new ComponentName(pkg1, cls1));
                        return;
                    }
                } else if (UiThemeUtils.isALS_ID7_UI(this.context)) {
                    openChoseMusic(view);
                    return;
                } else {
                    openBrowser(view);
                    return;
                }
            case 2:
                if (!TextUtils.isEmpty(pkg2) && !TextUtils.isEmpty(cls2)) {
                    Log.d("openShortCutApp2", pkg2 + "   " + cls2);
                    if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls2)) {
                        openMusic(view);
                        return;
                    } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls2)) {
                        openVideo(view);
                        return;
                    } else {
                        openAppByCls(new ComponentName(pkg2, cls2));
                        return;
                    }
                } else if (UiThemeUtils.isALS_ID7_UI(this.context)) {
                    openNaviApp(view);
                    return;
                } else {
                    openChoseMusic(view);
                    return;
                }
            case 3:
                if (TextUtils.isEmpty(pkg3) || TextUtils.isEmpty(cls3)) {
                    openVideo(view);
                    return;
                }
                Log.d("openShortCutApp3", pkg3 + "   " + cls3);
                if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls3)) {
                    openMusic(view);
                    return;
                } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls3)) {
                    openVideo(view);
                    return;
                } else {
                    openAppByCls(new ComponentName(pkg3, cls3));
                    return;
                }
            default:
                return;
        }
    }

    public void refreshShortCutInfo() {
        String cls3;
        String pkg1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_PKG_1);
        String cls1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_CLS_1);
        String pkg2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_PKG_2);
        String cls2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_CLS_2);
        String pkg3 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_PKG_3);
        String cls32 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), KEY_SHORTCUT_CLS_3);
        if (TextUtils.isEmpty(pkg1) || TextUtils.isEmpty(cls1)) {
            String str = cls1;
            cls3 = cls32;
        } else {
            Log.e("refreshShortCutInfo", "pk1 = " + pkg1 + " cls1 = " + cls1);
            if (cls1.equals(CLS_CHROME)) {
                ResolveInfo info1 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg1, cls1);
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
                this.shortCutName1.set((String) info1.activityInfo.loadLabel(this.activity.getPackageManager()));
                String str2 = pkg1;
                String str3 = cls1;
                cls3 = cls32;
            } else if (cls1.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                if (UiThemeUtils.isALS_ID7_UI(this.activity)) {
                    this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_music));
                } else {
                    this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.id7_main_music_n));
                }
                this.shortCutName1.set(this.activity.getResources().getString(R.string.ksw_id7_music));
                String str4 = pkg1;
                String str5 = cls1;
                cls3 = cls32;
            } else if (cls1.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
                this.shortCutName1.set(this.activity.getResources().getString(R.string.ksw_id7_hd_video));
                String str6 = pkg1;
                String str7 = cls1;
                cls3 = cls32;
            } else if (cls1.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName1.set(this.activity.getResources().getString(R.string.ksw_id7_navi));
                String str8 = pkg1;
                String str9 = cls1;
                cls3 = cls32;
            } else {
                ResolveInfo info12 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg1, cls1);
                String str10 = pkg1;
                String str11 = cls1;
                cls3 = cls32;
                this.shortCutIcon1.set(AppInfoUtils.zoomDrawable(info12.activityInfo.loadIcon(this.activity.getPackageManager()), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                this.shortCutName1.set((String) info12.activityInfo.loadLabel(this.activity.getPackageManager()));
            }
        }
        if (!TextUtils.isEmpty(pkg2) && !TextUtils.isEmpty(cls2)) {
            Log.e("refreshShortCutInfo", "pk2 = " + pkg2 + " cls2 = " + cls2);
            if (cls2.equals(CLS_CHROME)) {
                ResolveInfo info2 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg2, cls2);
                this.shortCutIcon2.set(this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
                this.shortCutName2.set((String) info2.activityInfo.loadLabel(this.activity.getPackageManager()));
            } else if (cls2.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                if (UiThemeUtils.isALS_ID7_UI(this.activity)) {
                    this.shortCutIcon2.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_music));
                } else {
                    this.shortCutIcon2.set(this.activity.getResources().getDrawable(R.drawable.id7_main_music_n));
                }
                this.shortCutName2.set(this.activity.getResources().getString(R.string.ksw_id7_music));
            } else if (cls2.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon2.set(this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
                this.shortCutName2.set(this.activity.getResources().getString(R.string.ksw_id7_hd_video));
            } else if (cls2.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon2.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName2.set(this.activity.getResources().getString(R.string.ksw_id7_navi));
            } else {
                ResolveInfo info22 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg2, cls2);
                this.shortCutIcon2.set(AppInfoUtils.zoomDrawable(info22.activityInfo.loadIcon(this.activity.getPackageManager()), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                this.shortCutName2.set((String) info22.activityInfo.loadLabel(this.activity.getPackageManager()));
            }
        }
        if (TextUtils.isEmpty(pkg3) || TextUtils.isEmpty(cls3)) {
            return;
        }
        String cls33 = cls3;
        Log.e("refreshShortCutInfo", "pk3 = " + pkg3 + " cls3 = " + cls33);
        if (cls33.equals(CLS_CHROME)) {
            ResolveInfo info3 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg3, cls33);
            this.shortCutIcon3.set(this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
            this.shortCutName3.set((String) info3.activityInfo.loadLabel(this.activity.getPackageManager()));
        } else if (cls33.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
            this.shortCutIcon3.set(this.activity.getResources().getDrawable(R.drawable.id7_main_music_n));
            this.shortCutName3.set(this.activity.getResources().getString(R.string.ksw_id7_music));
        } else if (cls33.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
            this.shortCutIcon3.set(this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
            this.shortCutName3.set(this.activity.getResources().getString(R.string.ksw_id7_hd_video));
        } else if (cls33.equals("com.google.android.maps.MapsActivity")) {
            this.shortCutIcon3.set(this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
            this.shortCutName3.set(this.activity.getResources().getString(R.string.ksw_id7_navi));
        } else {
            ResolveInfo info32 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg3, cls33);
            this.shortCutIcon3.set(AppInfoUtils.zoomDrawable(info32.activityInfo.loadIcon(this.activity.getPackageManager()), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
            this.shortCutName3.set((String) info32.activityInfo.loadLabel(this.activity.getPackageManager()));
        }
    }

    public void initPopWinowGridApps(View view, final int indexMenu) {
        List<LexusLsAppSelBean> list;
        if (this.mAppsPopupWindow != null) {
            this.mAppsPopupWindow = null;
        }
        View layout = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.pemp_id7_pop_layout, (ViewGroup) null);
        GridView gridView = (GridView) layout.findViewById(R.id.gridView);
        this.dialogWidth = ScreenUtil.dip2px(480.0f);
        Log.i(TAG, "diwidth =" + this.dialogWidth);
        this.dialogHeight = ScreenUtil.dip2px(400.0f);
        new ArrayList();
        if (UiThemeUtils.isID7_ALS(this.context) && ClientManager.getInstance().isAls6208Client()) {
            list = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ID7_ALS_DISMISS_DESK, 0, this.context);
        } else if (UiThemeUtils.isALS_ID7_UI(this.context)) {
            list = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ALS_ID7_UI_ATYS_DISMISS_DESK, 0, this.context);
        } else {
            list = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_DESK, 0, this.context);
        }
        gridView.setAdapter(new AppsGridAdapter(list, this.activity, R.layout.dialog_grid_app_item));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LexusLsAppSelBean bean = (LexusLsAppSelBean) parent.getItemAtPosition(position);
                Log.d(LauncherViewModel.TAG, "appinfo =" + bean.toString());
                int i = indexMenu;
                if (i == 1) {
                    Settings.System.putString(LauncherViewModel.this.contentResolver, LauncherViewModel.KEY_SHORTCUT_PKG_1, bean.getAppPkg());
                    Settings.System.putString(LauncherViewModel.this.contentResolver, LauncherViewModel.KEY_SHORTCUT_CLS_1, bean.getAppMainAty());
                    LauncherViewModel.this.shortCutName1.set(bean.getAppName());
                    if (bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                        LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        if (UiThemeUtils.isALS_ID7_UI(LauncherViewModel.this.activity)) {
                            LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_music));
                        } else {
                            LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_music_n));
                        }
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
                    } else if (bean.getAppMainAty().equals("com.google.android.maps.MapsActivity")) {
                        LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
                        LauncherViewModel.this.shortCutName1.set(LauncherViewModel.this.activity.getResources().getString(R.string.ksw_id7_navi));
                    } else {
                        LauncherViewModel.this.shortCutIcon1.set(AppInfoUtils.zoomDrawable(bean.getAppIcon(), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                    }
                } else if (i == 2) {
                    Settings.System.putString(LauncherViewModel.this.contentResolver, LauncherViewModel.KEY_SHORTCUT_PKG_2, bean.getAppPkg());
                    Settings.System.putString(LauncherViewModel.this.contentResolver, LauncherViewModel.KEY_SHORTCUT_CLS_2, bean.getAppMainAty());
                    LauncherViewModel.this.shortCutName2.set(bean.getAppName());
                    if (bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                        LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        if (UiThemeUtils.isALS_ID7_UI(LauncherViewModel.this.activity)) {
                            LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_music));
                        } else {
                            LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_music_n));
                        }
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
                    } else if (bean.getAppMainAty().equals("com.google.android.maps.MapsActivity")) {
                        LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
                        LauncherViewModel.this.shortCutName2.set(LauncherViewModel.this.activity.getResources().getString(R.string.ksw_id7_navi));
                    } else {
                        LauncherViewModel.this.shortCutIcon2.set(AppInfoUtils.zoomDrawable(bean.getAppIcon(), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                    }
                } else if (i == 3) {
                    Settings.System.putString(LauncherViewModel.this.contentResolver, LauncherViewModel.KEY_SHORTCUT_PKG_3, bean.getAppPkg());
                    Settings.System.putString(LauncherViewModel.this.contentResolver, LauncherViewModel.KEY_SHORTCUT_CLS_3, bean.getAppMainAty());
                    LauncherViewModel.this.shortCutName3.set(bean.getAppName());
                    if (bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_browser_n));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_music_n));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.id7_main_video_n));
                    } else if (bean.getAppMainAty().equals("com.google.android.maps.MapsActivity")) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(R.drawable.als_sp_id7_main_left_icon_navi));
                        LauncherViewModel.this.shortCutName3.set(LauncherViewModel.this.activity.getResources().getString(R.string.ksw_id7_navi));
                    } else {
                        LauncherViewModel.this.shortCutIcon3.set(AppInfoUtils.zoomDrawable(bean.getAppIcon(), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                    }
                }
                if (LauncherViewModel.this.mAppsPopupWindow != null) {
                    LauncherViewModel.this.mAppsPopupWindow.dismiss();
                    LauncherViewModel.this.mAppsPopupWindow = null;
                }
            }
        });
        PopupWindow popupWindow = new PopupWindow(layout, this.dialogWidth, this.dialogHeight, true);
        this.mAppsPopupWindow = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mAppsPopupWindow.setOutsideTouchable(true);
        showPopWindow(view);
    }

    private void showPopWindow(View view) {
        int height = view.getHeight();
        int width2 = view.getWidth();
        Log.i(TAG, String.format("width = %s ;height= %s;", new Object[]{Integer.valueOf(width2), Integer.valueOf(height)}));
        this.mAppsPopupWindow.showAtLocation(view, 19, 180, -60);
    }

    public void openDashboard(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity"));
    }

    public void onSendCommand(int command, int subCommand) {
        Log.i(TAG, "onSendCommand: command:" + command + " subCommand:" + subCommand);
        try {
            WitsCommand.sendCommand(command, subCommand, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onSendCommand: " + e.getMessage());
        }
    }

    public void openNaviApp(View view) {
        addLastViewFocused(view);
        openNaviApp();
    }

    public void openNaviApp() {
        String naiPackge = Settings.System.getString(this.contentResolver, KeyConfig.NAVI_DEFUAL);
        openApp(this.context.getPackageManager().getLaunchIntentForPackage(naiPackge));
        Log.i(TAG, "openNaviApp: " + naiPackge);
    }

    public void openBtApp(View view) {
        addLastViewFocused(view);
        openBtApp();
    }

    public void openBtApp() {
        onSendCommand(1, WitsCommand.SystemCommand.OPEN_BT);
        Log.i(TAG, "openBtApp: ");
    }

    public void openHicar(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName("com.huawei.hicar.demoapp", "com.huawei.hicar.demoapp.HiCarDemoActivity"));
    }

    public void addLastViewFocused(View view) {
        if (UiThemeUtils.isLEXUS_UI(this.context)) {
            setSelected(view);
        }
        this.lastViewFocused = view;
        if (UiThemeUtils.isALS_ID7_UI(this.context)) {
            this.alsID7UIViewList.add(this.lastViewFocused);
        }
        KswUtils.saveLastViewId(this.context, view);
    }

    private void setSelected(View view) {
        View view2 = this.lastViewFocused;
        if (view2 != null) {
            view2.setSelected(false);
        }
        Log.d(TAG, "setSelected " + view.isPressed());
        LinearLayout linearLayout = (LinearLayout) view.getParent().getParent();
        view.setSelected(true);
        linearLayout.setDescendantFocusability(ArabicShaping.TASHKEEL_END);
        linearLayout.setFocusable(true);
        linearLayout.requestFocus();
        linearLayout.setFocusable(false);
        linearLayout.setDescendantFocusability(262144);
    }

    public void setClearViewState() {
        List<View> list = this.alsID7UIViewList;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.alsID7UIViewList.size(); i++) {
                for (int j = this.alsID7UIViewList.size() - 1; j > i; j--) {
                    if (this.alsID7UIViewList.get(i) == this.alsID7UIViewList.get(j)) {
                        this.alsID7UIViewList.remove(j);
                    }
                }
                if (this.alsID7UIViewList.get(i) != null) {
                    this.alsID7UIViewList.get(i).setSelected(false);
                }
            }
            Log.d(TAG, "setClearViewState: alsID7UIViewList的长度" + this.alsID7UIViewList.size());
        }
    }

    public void refreshLastViewFocused() {
        if (!UiThemeUtils.isALS_ID7_UI(this.context)) {
            View view = this.lastViewFocused;
            if (view == null || view.isSelected()) {
                Log.i(TAG, "refreshLastViewFocused: lastViewFocused null  ");
                return;
            }
            this.lastViewFocused.setFocusableInTouchMode(true);
            this.lastViewFocused.requestFocus();
            this.lastViewFocused.setFocusableInTouchMode(false);
            Log.i(TAG, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
        } else if (this.lastViewFocused != null && MainActivity.alsId7UiMainBinding != null) {
            if (this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton1.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton2.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton3.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton4.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton5.getId()) {
                setClearViewState();
                this.lastViewFocused.setSelected(true);
            } else if (!this.lastViewFocused.isSelected()) {
                this.lastViewFocused.setFocusableInTouchMode(true);
                this.lastViewFocused.requestFocus();
                this.lastViewFocused.setFocusableInTouchMode(false);
                Log.i(TAG, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
            } else {
                Log.i(TAG, "refreshLastViewFocused: lastViewFocused null  ");
            }
        }
    }

    public void refreshLastSel() {
        if (viewLastSel != null) {
            Log.d("refreshLastSel", viewLastSel.getId() + "");
            viewLastSel.setSelected(true);
        }
    }

    public void clearLastSel() {
        if (viewLastSel != null) {
            Log.d("clearLastSel", viewLastSel.getId() + "");
            viewLastSel.setSelected(false);
        }
    }

    public void refreshViewFocused(View view) {
        if (view != null) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setFocusableInTouchMode(false);
            Log.i(TAG, "refreshViewFocused: lastViewFocused=" + view.getId());
            return;
        }
        Log.i(TAG, "refreshViewFocused: lastViewFocused null  ");
    }

    private void registerBtContentObserver() {
        this.contentResolver.registerContentObserver(Settings.System.getUriFor(KeyConfig.Android_Bt_Switch), false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange, Uri uri) {
                LauncherViewModel.this.setBtState();
            }
        });
        this.contentResolver.registerContentObserver(Settings.System.getUriFor("ksw_bluetooth"), false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange, Uri uri) {
                LauncherViewModel.this.setPhoneConState(Settings.System.getInt(LauncherViewModel.this.contentResolver, "ksw_bluetooth", 0));
                LauncherViewModel.this.setBtState();
            }
        });
    }

    private void registerTimeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.12_24");
        this.context.registerReceiver(this.timeReceiver, intentFilter);
    }

    private void registerOtherReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NaviInfo.AUTONAVI_STANDARD_BROADCAST_SEND);
        this.context.registerReceiver(this.otherReceiver, intentFilter);
    }

    public void setPhoneConState(int state) {
        this.phoneConState.set(state);
    }

    public void setBtState() {
        String btOff = Settings.System.getString(this.contentResolver, KeyConfig.Android_Bt_Switch);
        String str = TAG;
        Log.d(str, "setBtState:android_Bt_Switch===" + btOff);
        if (TextUtils.equals(btOff, "0")) {
            this.btState.set(this.context.getString(R.string.bt_text_bt_closed));
            return;
        }
        int state = Settings.System.getInt(this.contentResolver, "ksw_bluetooth", 0);
        Log.d(str, "setBtState:ksw_bluetooth===" + state);
        if (state == 1) {
            this.btState.set(this.context.getString(R.string.ksw_id7_connected_phone));
        } else {
            this.btState.set(this.context.getString(R.string.ksw_id7_not_connected_phone));
        }
    }

    public void setMonthDay(Date date) {
        String month2 = KswUtils.formatMonth(date);
        String day2 = KswUtils.formatDay(date);
        this.month.set(month2);
        this.day.set(day2);
        Log.i(TAG, "setMonthDay: times:-------------->>>" + new SimpleDateFormat(KswUtils.is24Hour() ? "yyyy/MM/dd hh:mm:ss" : "yyyy/M/d h:m:s").format(date));
    }

    public void refreshNaviInfo(Intent intent) {
        int iKey_type = intent.getIntExtra("KEY_TYPE", 0);
        int iExtra_state = intent.getIntExtra("EXTRA_STATE", 0);
        String str = TAG;
        Log.w(str, "refreshNaviInfo: iKey_type=" + iKey_type + " iExtra_state=" + iExtra_state);
        Bundle bundle = intent.getExtras();
        if (iKey_type == 10001) {
            if (bundle != null) {
                int type = bundle.getInt(NaviInfo.TYPE);
                Log.i(str, "refreshNaviInfo: type=" + type);
                if (type == 1 || type == 0) {
                    this.naviInfo.next_road_name.set(bundle.getString(NaviInfo.NEXT_ROAD_NAME));
                    this.naviInfo.icon.set(Integer.valueOf(NaviInfo.formatIcon(bundle.getInt(NaviInfo.ICON))));
                    this.naviInfo.route_remain_dis.set(NaviInfo.formatDistance(bundle.getInt(NaviInfo.ROUTE_REMAIN_DIS)));
                    this.naviInfo.route_remain_time.set(NaviInfo.formatTime(bundle.getInt(NaviInfo.ROUTE_REMAIN_TIME)));
                    this.naviInfo.seg_remain_dis.set(NaviInfo.formatDistance(bundle.getInt(NaviInfo.SEG_REMAIN_DIS)));
                    this.naviInfo.camera_speed.set(Integer.valueOf(bundle.getInt(NaviInfo.CAMERA_SPEED)));
                    this.naviInfo.camera_type.set(Integer.valueOf(bundle.getInt(NaviInfo.CAMERA_TYPE)));
                    Log.i(str, "refreshNaviInfo: next_road_name=" + this.naviInfo.next_road_name.get());
                    Log.i(str, "refreshNaviInfo: icon=" + bundle.getInt(NaviInfo.ICON));
                    Log.i(str, "refreshNaviInfo: route_remain_dis=" + this.naviInfo.route_remain_dis.get());
                    Log.i(str, "refreshNaviInfo: route_remain_time=" + this.naviInfo.route_remain_time.get());
                    Log.i(str, "refreshNaviInfo: seg_remain_dis=" + this.naviInfo.seg_remain_dis.get());
                    Log.i(str, "refreshNaviInfo: camera_speed=" + this.naviInfo.camera_speed.get());
                    Log.i(str, "refreshNaviInfo: camera_type=" + this.naviInfo.camera_type.get());
                }
                this.naviInfo.showGuideView.set(0);
            }
        } else if (iKey_type != 10019) {
        } else {
            if (iExtra_state == 2 || iExtra_state == 9 || iExtra_state == 12) {
                this.naviInfo.showGuideView.set(8);
            }
        }
    }

    public void btnMusicPrev() {
        try {
            if (PowerManagerApp.getStatusInt("lastMode") == 1) {
                KswUtils.sendKeyDownUpSync(88);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void btnMusicPlayPause() {
        try {
            if (PowerManagerApp.getStatusInt("lastMode") == 1) {
                KswUtils.sendKeyDownUpSync(85);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void btnMusicNext() {
        try {
            if (PowerManagerApp.getStatusInt("lastMode") == 1) {
                KswUtils.sendKeyDownUpSync(87);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setRotation(ImageView imageView, int rota) {
        setSpeedRotationBet(imageView, (float) rota);
    }

    public static void setSpeedRotation(ImageView imageView, int rota) {
        setSpeedRotationBet(imageView, (float) rota);
    }

    public static void setSpeedRotationBet(ImageView imageView, float rota) {
        int delay = McuImpl.getInstance().carInfo.delay.get().intValue();
        ObjectAnimator objectAnimator = animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", new float[]{rota});
            animatorMaps.put(Integer.valueOf(imageView.getId()), objectAnimator);
        }
        int duration = 150;
        if (delay != 0 && delay <= 150) {
            duration = delay;
        }
        if (objectAnimator.isStarted()) {
            objectAnimator.cancel();
        }
        imageView.setRotation(rota);
        if (duration <= 55) {
            imageView.setRotation(rota);
            return;
        }
        objectAnimator.setDuration((long) delay);
        objectAnimator.setFloatValues(new float[]{rota});
        objectAnimator.start();
    }

    public static void setRotationBySpeed(ImageView imageView, float tmpSpeed) {
        float base_angle;
        int base_offset;
        Log.d("liuhao", "setRotationBySpeed tmpSpeed 1 = " + tmpSpeed);
        if (carInfo.unit.get() != 1) {
            if (tmpSpeed >= 280.0f) {
                tmpSpeed = 280.0f;
            }
            base_angle = 15.0f;
            base_offset = Math.round((100.0f * tmpSpeed) / 35.0f);
        } else {
            if (tmpSpeed >= 160.0f) {
                tmpSpeed = 160.0f;
            }
            base_angle = 15.0f;
            base_offset = Math.round((float) Math.round((100.0f * tmpSpeed) / 20.0f));
        }
        int delay = McuImpl.getInstance().carInfo.delay.get().intValue();
        ObjectAnimator objectAnimator = animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", new float[]{tmpSpeed});
            animatorMaps.put(Integer.valueOf(imageView.getId()), objectAnimator);
        }
        if (delay != 0 && delay <= 150) {
            int i = delay;
        }
        if (objectAnimator.isStarted()) {
            objectAnimator.cancel();
        }
        float tmpSpeed2 = (float) Math.round((float) (Math.round(((float) base_offset) * base_angle) / 100));
        Log.d("liuhao", "setRotationBySpeed tmpSpeed 2 = " + tmpSpeed2);
        imageView.setRotation(0.2f + tmpSpeed2);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
    }
}
