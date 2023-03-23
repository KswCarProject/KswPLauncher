package com.wits.ksw.launcher.model;

import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.ibm.icu.text.ArabicShaping;
import com.txznet.weatherquery.TXZWeather;
import com.txznet.weatherquery.WeatherQueryManager;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.adpater.AppsGridAdapter;
import com.wits.ksw.launcher.base.BaseViewModel;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.bean.WeatherInfo;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsModusActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8ModusActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.OnID8SkinChangeListener;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.NaviInfo;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.AppsActivity;
import com.wits.ksw.launcher.view.Ntg630ControlView;
import com.wits.ksw.launcher.view.Ntg6ControlView;
import com.wits.ksw.launcher.view.ug.WiewFocusUtils;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import skin.support.content.res.SkinCompatResources;

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
    private static final long LONG_CLICK_TIME = 1000;
    /* access modifiers changed from: protected */
    public static final String TAG = LauncherViewModel.class.getSimpleName();
    public static final int WIDTH_IMG_BIG = 64;
    public static final int WIDTH_IMG_SMALL = 45;
    private static HashMap<Integer, ObjectAnimator> animatorMaps = new HashMap<>();
    public static ObservableField<Boolean> bThirdMusic = new ObservableField<>();
    public static ObservableField<Boolean> bThirdVideo = new ObservableField<>();
    public static CarInfo carInfo = McuImpl.getInstance().getCarInfo();
    private static long mCurrentTime = 0;
    public static MediaInfo mediaInfo = MediaImpl.getInstance().getMediaInfo();
    public static ObservableField<Drawable> musicBG = new ObservableField<>();
    public static ObservableField<Integer> screenHeight = new ObservableField<>();
    public static ObservableField<String> screenPixels = new ObservableField<>();
    public static ObservableField<Integer> screenWidth = new ObservableField<>();
    public static ObservableField<Drawable> videoBG = new ObservableField<>();
    public static volatile View viewLastSel;
    public static WeatherInfo weatherInfo = new WeatherInfo();
    public static int width = 0;
    public final ObservableBoolean acControl = new ObservableBoolean();
    public List<View> alsID7UIViewList = new ArrayList();
    public McuStatus.BenzData benzData;
    public ObservableField<String> btState = new ObservableField<>();
    public View.OnFocusChangeListener carViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(2);
            }
        }
    };
    public ControlBean controlBean = new ControlBean(this.context);
    public ObservableField<Boolean> dashBoardMusicShow = new ObservableField<>();
    public final ObservableField<String> day = new ObservableField<>();
    public int dialogHeight;
    public int dialogWidth;
    private BitmapDrawable efficientBitmapDrawable;
    public final ObservableField<Boolean> hicar = new ObservableField<>();
    public ObservableField<BitmapDrawable> id8ModusDrawable = new ObservableField<>();
    public ObservableField<Integer> id8TextColor = new ObservableField<>();
    public ObservableField<Boolean> isChangeModusStatusID8 = new ObservableField<>();
    public ObservableField<Boolean> isEfficientModus = new ObservableField<>();
    public ObservableField<Boolean> isPersonalModus = new ObservableField<>();
    public ObservableField<Boolean> isSportModus = new ObservableField<>();
    public View.OnFocusChangeListener kswId7SetCardFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener hasFocus：" + hasFocus);
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(0);
            }
        }
    };
    public View.OnFocusChangeListener kswId7VideoCardFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            Log.i(LauncherViewModel.TAG, "onFocusChange: kswId7VideoCardFocusChangeListener hasFocus：" + hasFocus);
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(1);
            }
        }
    };
    public View lastViewFocused;
    public final CompoundButton.OnCheckedChangeListener leftOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            LauncherViewModel.this.controlBean.leftBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), LauncherViewModel.this.benzData, LauncherViewModel.this);
            }
        }
    };
    public PopupWindow mAppsPopupWindow;
    public final ObservableField<String> month = new ObservableField<>();
    private boolean musicId;
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
    private OnID8SkinChangeListener onID8SkinChangeListener;
    protected BroadcastReceiver otherReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (NaviInfo.AUTONAVI_STANDARD_BROADCAST_SEND.equalsIgnoreCase(intent.getAction()) && LauncherViewModel.this.naviInfo.isGuideEnable()) {
                LauncherViewModel.this.refreshNaviInfo(intent);
            }
        }
    };
    private BitmapDrawable personalBitmapDrawable;
    public final ObservableInt phoneConState = new ObservableInt();
    public View.OnFocusChangeListener phoneViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(0);
                Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener");
            }
        }
    };
    public View.OnFocusChangeListener phoneViewFocusChangeListenerV2 = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(1);
                Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener");
            }
        }
    };
    public final CompoundButton.OnCheckedChangeListener rightOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            LauncherViewModel.this.controlBean.rightBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), LauncherViewModel.this.benzData, LauncherViewModel.this);
            }
        }
    };
    public ObservableField<Drawable> shortCutIcon1 = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon2 = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon3 = new ObservableField<>();
    public ObservableField<String> shortCutName1 = new ObservableField<>();
    public ObservableField<String> shortCutName2 = new ObservableField<>();
    public ObservableField<String> shortCutName3 = new ObservableField<>();
    private BitmapDrawable sportBitmapDrawable;
    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LauncherViewModel.this.setMonthDay(new Date());
        }
    };
    private boolean videoId;
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
    public View.OnFocusChangeListener videoViewFocusChangeListenerv2 = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: videoViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(2);
                }
            }
        }
    };
    private Disposable weatherSubscribe;
    public View.OnFocusChangeListener weatherViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: musicViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(0);
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

    public void initControl() {
        try {
            this.benzData = McuStatus.BenzData.getStatusFromJson(PowerManagerApp.getStatusString("benzData"));
            this.controlBean.chassis.set(this.benzData.highChassisSwitch);
            boolean z = false;
            this.controlBean.sport.set(this.benzData.airMaticStatus == 1);
            this.controlBean.rdarAssistance.set(this.benzData.auxiliaryRadar);
            this.controlBean.passairbar.set(this.benzData.airBagSystem);
            StringBuilder append = new StringBuilder().append("initData: 底盘开关：").append(this.benzData.highChassisSwitch).append(",运动模式：");
            if (this.benzData.airMaticStatus == 1) {
                z = true;
            }
            Log.i("控制面板", append.append(z).append(",雷达辅助开关：").append(this.benzData.auxiliaryRadar).append(" light1:").append(this.benzData.light1).append(" light2:").append(this.benzData.light2).append(" 安全气囊 ").append(this.benzData.airBagSystem).toString());
        } catch (Exception e) {
            this.benzData = new McuStatus.BenzData();
        }
    }

    public void registerIContentObserver() {
        PowerManagerApp.registerIContentObserver("benzData", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                McuStatus.BenzData benzData = McuStatus.BenzData.getStatusFromJson(PowerManagerApp.getStatusString("benzData"));
                Log.i(LauncherViewModel.TAG, "benzData onChange: " + benzData.getJson());
                LauncherViewModel.this.controlBean.chassis.set(benzData.highChassisSwitch);
                ObservableBoolean observableBoolean = LauncherViewModel.this.controlBean.sport;
                boolean z = true;
                if (benzData.airMaticStatus != 1) {
                    z = false;
                }
                observableBoolean.set(z);
                LauncherViewModel.this.controlBean.rdarAssistance.set(benzData.auxiliaryRadar);
                LauncherViewModel.this.controlBean.passairbar.set(benzData.airBagSystem);
            }
        });
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
        if (UiThemeUtils.isUI_KSW_ID7(this.context) || UiThemeUtils.isUI_KSW_MBUX_1024(this.context)) {
            initControl();
            registerIContentObserver();
        }
        initThirdApps();
        width = this.context.getResources().getDisplayMetrics().widthPixels;
    }

    /* access modifiers changed from: private */
    public void refreshWeather() {
        String str = TAG;
        Log.i(str, "TXZWeather loading: ");
        Log.i(str, "TXZWeather language: " + Locale.getDefault().getLanguage());
        try {
            sendWeatherRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendWeatherRequest() {
        WeatherQueryManager.getInstance().sendWeatherRequest(this.context, new WeatherQueryManager.WeatherCallback() {
            public void onSuccess(TXZWeather result, Bundle texts) {
                Log.i(LauncherViewModel.TAG, "TXZWeather onSuccess: " + result.toString());
                String[] details = null;
                if (texts != null) {
                    details = texts.getStringArray("details");
                }
                try {
                    LauncherViewModel.weatherInfo.loadSuccess(LauncherViewModel.this.context, result, details);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailed(int errorCode) {
                Log.i(LauncherViewModel.TAG, "TXZWeather onFailed: " + errorCode);
                if (errorCode != 6) {
                    try {
                        LauncherViewModel.weatherInfo.loadFailed(errorCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startWeatherLooper() {
        weatherInfo.loading();
        this.weatherSubscribe = Observable.interval(0, 30, TimeUnit.MINUTES).subscribe(new Consumer<Long>() {
            public void accept(Long aLong) throws Exception {
                LauncherViewModel.this.refreshWeather();
            }
        });
        addWeatherSettingListener();
    }

    public void onResumeRefreshWeather() {
        Log.i(TAG, "TXZWeather onResumeRefreshWeather: ");
        refreshWeather();
    }

    public void addWeatherSettingListener() {
        WeatherQueryManager.getInstance().setUserSettingListener(this.activity, new WeatherQueryManager.UserSettingListener() {
            public final void noticeChange() {
                LauncherViewModel.this.refreshWeather();
            }
        });
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

    public void onControlClick(View view) {
        int benzpane = KswUtils.getBenzpaneVersion();
        Log.i(TAG, "onControlClick: benzpane = " + benzpane);
        if (benzpane == 1) {
            if (Ntg6ControlView.getInstance().isShowing()) {
                Ntg6ControlView.getInstance().dismiss();
            } else {
                Ntg6ControlView.getInstance().showBenzControl(this.context, this, view);
            }
        } else if (benzpane != 2) {
        } else {
            if (Ntg630ControlView.getInstance().isShowing()) {
                Ntg630ControlView.getInstance().dismiss();
            } else {
                Ntg630ControlView.getInstance().showBenzControl(this.context, this, view);
            }
        }
    }

    public void onFoldLeftClick(View view) {
        this.benzData.key3 = 6;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, this.benzData.getJson());
        Log.d(TAG, "onFoldLeftClick: " + this.benzData.getJson());
    }

    public void onFoldRigtClick(View view) {
        this.benzData.key3 = 7;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, this.benzData.getJson());
        Log.d(TAG, "onFoldRigtClick: " + this.benzData.getJson());
    }

    public void onEspClick(View view) {
        this.benzData.key3 = 5;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, this.benzData.getJson());
        Log.d(TAG, "onEspClick: " + this.benzData.getJson());
    }

    public void onAuxiliaryRadarClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(3);
        Log.i(TAG, "onCheckedChanged_AUX: 辅助雷达开关:" + this.benzData.getJson());
    }

    public void onSportClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(2);
        Log.i(TAG, "onCheckedChanged_AIR: 辅助雷达开关:" + this.benzData.getJson());
    }

    public void onHighChasssisClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(1);
        Log.i(TAG, "onCheckedChanged: 底盘升降开关:" + this.benzData.getJson());
    }

    public void showBrightnessDialog(View view) {
        int benzpane = KswUtils.getBenzpaneVersion();
        if (benzpane == 1) {
            if (view.getId() == R.id.brightnessBtn_left) {
                Ntg6ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 1);
            }
            if (view.getId() == R.id.brightnessBtn_right) {
                Ntg6ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 2);
            }
        } else if (benzpane == 2) {
            if (view.getId() == R.id.brightnessBtn_left) {
                Ntg630ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 1);
            }
            if (view.getId() == R.id.brightnessBtn_right) {
                Ntg630ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 2);
            }
        }
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

    public void addWidget(View view) {
        Log.w(TAG, "addWidget: ");
        Intent intent = new Intent(this.activity, AppsActivity.class);
        intent.putExtra("isEdit", true);
        addLastViewFocused(view);
        this.activity.startActivityForResult(intent, 120);
    }

    public void setMusicPlayState(boolean play) {
        mediaInfo.setMusicPlay(play);
    }

    public void setMusicPlayStop(boolean stop) {
        mediaInfo.setMusicStop(stop);
    }

    public void setVideoPlayState(boolean play) {
        mediaInfo.setVideoPlay(play);
    }

    public void initSkinData(OnID8SkinChangeListener listener) {
        Log.e(TAG, "initSkinData: ");
        this.onID8SkinChangeListener = listener;
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        Resources resources = this.context.getResources();
        this.personalBitmapDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.id8_main_icon_modus_personal));
        this.sportBitmapDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.id8_main_icon_modus_sport));
        this.efficientBitmapDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.id8_main_icon_modus_efficient));
        if (skinName.equals(ID8LauncherConstants.ID8_SKIN_PERSONAL)) {
            displayPersonalModus();
        } else if (skinName.equals(ID8LauncherConstants.ID8_SKIN_SPORT)) {
            displaySportModus();
        } else if (skinName.equals(ID8LauncherConstants.ID8_SKIN_EFFICIENT)) {
            displayEfficientModus();
        }
    }

    private void displayPersonalModus() {
        this.isPersonalModus.set(true);
        this.id8ModusDrawable.set(this.personalBitmapDrawable);
        this.isSportModus.set(false);
        this.isEfficientModus.set(false);
        this.onID8SkinChangeListener.onSkinChangeLeftBar(R.drawable.bmw_id8_main_left_btn_yellow);
        this.onID8SkinChangeListener.onSkinChangeCardBGSelector(ID8LauncherConstants.ID8_SKIN_PERSONAL);
        this.onID8SkinChangeListener.onSkinChangeMusicAlbum(R.drawable.id8_main_icon_music_album_yellow);
    }

    private void displaySportModus() {
        this.isSportModus.set(true);
        this.id8ModusDrawable.set(this.sportBitmapDrawable);
        this.isPersonalModus.set(false);
        this.isEfficientModus.set(false);
        this.onID8SkinChangeListener.onSkinChangeLeftBar(R.drawable.bmw_id8_main_left_btn_red);
        this.onID8SkinChangeListener.onSkinChangeCardBGSelector(ID8LauncherConstants.ID8_SKIN_SPORT);
        this.onID8SkinChangeListener.onSkinChangeMusicAlbum(R.drawable.id8_main_icon_music_album_red);
    }

    private void displayEfficientModus() {
        this.isEfficientModus.set(true);
        this.id8ModusDrawable.set(this.efficientBitmapDrawable);
        this.isPersonalModus.set(false);
        this.isSportModus.set(false);
        this.onID8SkinChangeListener.onSkinChangeLeftBar(R.drawable.bmw_id8_main_left_btn_blue);
        this.onID8SkinChangeListener.onSkinChangeCardBGSelector(ID8LauncherConstants.ID8_SKIN_EFFICIENT);
        this.onID8SkinChangeListener.onSkinChangeMusicAlbum(R.drawable.id8_main_icon_music_album_blue);
    }

    public void enterChangeModus(View view) {
        Log.e(TAG, "enterChangeModus");
        Intent intent = new Intent(this.activity, ID8ModusActivity.class);
        this.isChangeModusStatusID8.set(true);
        this.id8TextColor.set(Integer.valueOf(SkinCompatResources.getColor(this.context, R.color.id8_main_style_color)));
        addLastViewFocused(view);
        this.activity.startActivity(intent);
    }

    public void enterGsChangeModus(View view) {
        Intent intent = new Intent(this.activity, ID8GsModusActivity.class);
        this.id8TextColor.set(Integer.valueOf(SkinCompatResources.getColor(this.context, R.color.id8_main_style_color)));
        addLastViewFocused(view);
        this.activity.startActivity(intent);
    }

    private void enterID8MainActivity() {
        if (UiThemeUtils.isUI_GS_ID8(this.context) || UiThemeUtils.isBMW_ID8_UI(this.context)) {
            this.activity.startActivity(new Intent(this.activity, MainActivity.class));
        }
    }

    public void exitChangeModus() {
        this.isChangeModusStatusID8.set(false);
    }

    public void changeModusToPersonal(View view) {
        if (ID8LauncherConstants.checkModusHasChanged(ID8LauncherConstants.ID8_SKIN_PERSONAL)) {
            displayPersonalModus();
            reloadSkinResources();
        }
        exitChangeModus();
        enterID8MainActivity();
    }

    public void changeModusToSport(View view) {
        if (ID8LauncherConstants.checkModusHasChanged(ID8LauncherConstants.ID8_SKIN_SPORT)) {
            displaySportModus();
            reloadSkinResources();
        }
        exitChangeModus();
        enterID8MainActivity();
    }

    public void changeModusToEfficient(View view) {
        if (ID8LauncherConstants.checkModusHasChanged(ID8LauncherConstants.ID8_SKIN_EFFICIENT)) {
            displayEfficientModus();
            reloadSkinResources();
        }
        exitChangeModus();
        enterID8MainActivity();
    }

    private void reloadSkinResources() {
    }

    public void openChoseMusic(View view) {
        addLastViewFocused(view);
        if (UiThemeUtils.isBMW_EVO_ID7(this.context) || UiThemeUtils.isUI_KSW_ID7(this.context) || UiThemeUtils.isBMW_EVO_ID7_HiCar(this.context) || UiThemeUtils.isCommon_UI_GS_UG(this.context) || UiThemeUtils.isCommon_UI_GS_UG_1024(this.context) || UiThemeUtils.isUI_KSW_MBUX_1024(this.context) || UiThemeUtils.isAudi_mib3(this.context) || UiThemeUtils.isUI_mib3_V2(this.context) || UiThemeUtils.isAudi_mib3_FY(this.context) || UiThemeUtils.isAudi_mib3_FY_V2(this.context) || UiThemeUtils.isAudi_mib3_ty(this.context) || UiThemeUtils.isALS_ID7_UI(this.context) || ((UiThemeUtils.isBMW_EVO_ID6_CUSP(this.context) && !ClientManager.getInstance().isCUSP_210407()) || UiThemeUtils.isID7_ALS(this.context) || UiThemeUtils.isID7_ALS_V2(this.context))) {
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

    public static boolean isContinuityClick() {
        long currentTime = System.currentTimeMillis();
        Log.i(TAG, "isContinuityClick: mCurrentTime " + mCurrentTime + " currentTime " + currentTime);
        if (currentTime - mCurrentTime < LONG_CLICK_TIME) {
            return true;
        }
        mCurrentTime = currentTime;
        return false;
    }

    public boolean isMediaServiceLived() {
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList) ((ActivityManager) this.context.getSystemService("activity")).getRunningServices(100);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals("com.wits.ksw.media.MyService")) {
                return true;
            }
        }
        return false;
    }

    public void handleMusicOperator(final View view, final int subCommand, final int keyCode) {
        Log.e(TAG, "id8GsOpenPauseMusic: " + getLastMode());
        if (!isMediaServiceLived()) {
            Intent serviceIntent = new Intent();
            serviceIntent.setComponent(new ComponentName("com.wits.ksw.media", "com.wits.ksw.media.MyService"));
            serviceIntent.putExtra("mediaType", subCommand == 130 ? 11 : 12);
            this.context.startService(serviceIntent);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    LauncherViewModel.this.handleRealMusicOperator(view, subCommand, keyCode);
                }
            }, 100);
            return;
        }
        handleRealMusicOperator(view, subCommand, keyCode);
    }

    /* access modifiers changed from: private */
    public void handleRealMusicOperator(View view, int subCommand, int keyCode) {
        if (!isContinuityClick()) {
            if (subCommand == 130) {
                if (getLastMode() == 1) {
                    KswUtils.sendKeyDownUpSync(keyCode);
                } else {
                    setLastMode(1);
                    onSendCommand(2, subCommand);
                }
            } else if (subCommand == 131) {
                if (getLastMode() == 2) {
                    KswUtils.sendKeyDownUpSync(keyCode);
                } else {
                    setLastMode(2);
                    onSendCommand(2, subCommand);
                }
            }
        }
        WiewFocusUtils.setViewRequestFocus(((View) view.getParent()).findViewById(R.id.iv_mask));
    }

    public void id8GsOpenPauseMusic(View view) {
        handleMusicOperator(view, 130, 85);
    }

    public void id8GsPreMusic(View view) {
        handleMusicOperator(view, 130, 88);
    }

    public void id8GsNextMusic(View view) {
        handleMusicOperator(view, 130, 87);
    }

    public int getLastMode() {
        int mode = -1;
        try {
            mode = PowerManagerApp.getStatusInt("lastMode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getLastMode: " + mode);
        return mode;
    }

    public void setLastMode(int value) {
        try {
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "" + value);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "setLastMode filed");
        }
    }

    public void id8GsOpenPauseVideo(View view) {
        handleMusicOperator(view, 131, 85);
    }

    public void id8GsPreVideo(View view) {
        Log.e(TAG, "id8GsPreVideo: " + getLastMode());
        handleMusicOperator(view, 131, 88);
    }

    public void id8GsNextVideo(View view) {
        Log.e(TAG, "id8GsNextVideo: " + getLastMode());
        handleMusicOperator(view, 131, 87);
    }

    public void startVideo(View view) {
        Log.w(TAG, "startVideo: ");
        try {
            if (PowerManagerApp.getManager().getStatusBoolean("video_stop")) {
                openApp(new Intent("com.wits.media.VIDEO"));
            } else {
                KswUtils.sendKeyDownUpSync(126);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "startVdeo: " + e.getMessage());
            openApp(new Intent("com.wits.media.VIDEO"));
        }
    }

    public void pauseVideo(View view) {
        Log.w(TAG, "pauseVideo: ");
        try {
            if (PowerManagerApp.getManager().getStatusBoolean("video_stop")) {
                openApp(new Intent("com.wits.media.VIDEO"));
            } else {
                KswUtils.sendKeyDownUpSync(127);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "startVdeo: " + e.getMessage());
            openApp(new Intent("com.wits.media.VIDEO"));
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

    public void sendMcuCommand() {
        try {
            Log.d(TAG, "sendMcuCommand: 发送MCU发指令");
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "sendMcuCommand: " + e.toString());
        }
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
                        sendMcuCommand();
                        return;
                    }
                } else if (UiThemeUtils.isALS_ID7_UI(this.context)) {
                    openChoseMusic(view);
                    return;
                } else {
                    openBrowser(view);
                    sendMcuCommand();
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
                        sendMcuCommand();
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
                    sendMcuCommand();
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
        } else if (UiThemeUtils.isID7_ALS_V2(this.context) && ClientManager.getInstance().isAls6208Client()) {
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

    public void openWeatherApp(View view) {
        addLastViewFocused(view);
        openWeatherApp();
    }

    private void openWeatherApp() {
        Log.i(TAG, "openWeatherApp: ");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.txznet.weather", "com.txznet.weather.MainActivity"));
        intent.setFlags(268435488);
        openApp(intent);
    }

    public void openNaviApp() {
        String naiPackge = Settings.System.getString(this.contentResolver, KeyConfig.NAVI_DEFUAL);
        boolean isVersion12 = Build.VERSION.RELEASE.contains("12");
        String str = TAG;
        Log.w(str, "isVersion12 :" + isVersion12);
        if (!isVersion12 || !AppInfoUtils.isContainFreedomMap(naiPackge) || !UiThemeUtils.isUI_GS_ID8(this.activity)) {
            openMapApp(this.context.getPackageManager().getLaunchIntentForPackage(naiPackge));
        } else {
            launchApp(naiPackge, 1);
        }
        Log.i(str, "openNaviApp: " + naiPackge);
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
        Log.e(TAG, "addLastViewFocused: view:" + view);
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
        if (UiThemeUtils.isALS_ID7_UI(this.context)) {
            View view = this.lastViewFocused;
            if (view != null && !view.isSelected() && MainActivity.alsId7UiMainBinding != null) {
                if (this.lastViewFocused.getId() == MainActivity.alsId7UiMainBinding.menuButton1.getId() || this.lastViewFocused.getId() == MainActivity.alsId7UiMainBinding.menuButton2.getId() || this.lastViewFocused.getId() == MainActivity.alsId7UiMainBinding.menuButton3.getId() || this.lastViewFocused.getId() == MainActivity.alsId7UiMainBinding.menuButton4.getId() || this.lastViewFocused.getId() == MainActivity.alsId7UiMainBinding.menuButton5.getId()) {
                    String str = TAG;
                    Log.d(str, "refreshLastViewFocused: 不等于null哦！else");
                    setClearViewState();
                    this.lastViewFocused.setFocusableInTouchMode(true);
                    this.lastViewFocused.requestFocus();
                    this.lastViewFocused.setFocusableInTouchMode(false);
                    Log.i(str, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
                    return;
                }
                Log.d(TAG, "refreshLastViewFocused: 不等于null哦！");
                MainActivity.alsId7UiMainBinding.menuButton1.clearFocus();
                MainActivity.alsId7UiMainBinding.menuButton2.clearFocus();
                MainActivity.alsId7UiMainBinding.menuButton3.clearFocus();
                MainActivity.alsId7UiMainBinding.menuButton4.clearFocus();
                MainActivity.alsId7UiMainBinding.menuButton5.clearFocus();
                setClearViewState();
                this.lastViewFocused.setSelected(true);
                return;
            }
            return;
        }
        View view2 = this.lastViewFocused;
        if (view2 == null || view2.isSelected()) {
            Log.i(TAG, "refreshLastViewFocused: lastViewFocused null  ");
            return;
        }
        this.lastViewFocused.setFocusableInTouchMode(true);
        this.lastViewFocused.requestFocus();
        this.lastViewFocused.setFocusableInTouchMode(false);
        Log.i(TAG, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
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
        if (TextUtils.equals(btOff, TxzMessage.TXZ_DISMISS)) {
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
        if (KswUtils.ismph()) {
            setSpeedRotationBet(imageView, new BigDecimal((((double) rota) / 0.621d) * 0.968d).floatValue());
        } else {
            setSpeedRotationBet(imageView, (float) rota);
        }
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
        Disposable disposable = this.weatherSubscribe;
        if (disposable != null && !disposable.isDisposed()) {
            this.weatherSubscribe.dispose();
        }
    }

    public void openID8Edit(View view) {
        Log.i(TAG, "openID8Edit: ");
        openApp(new Intent(this.activity, ID8EditActivity.class));
    }

    public void openID8GsEdit(View view) {
        Log.i(TAG, "openID8GsEdit: ");
        openApp(new Intent(this.activity, ID8GsEditActivity.class));
    }

    public void dashboardMusicLay(View view) {
        this.dashBoardMusicShow.set(false);
    }

    public void dashboardWeatherLay(View view) {
        this.dashBoardMusicShow.set(true);
    }
}
