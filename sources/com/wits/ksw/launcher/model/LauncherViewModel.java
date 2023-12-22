package com.wits.ksw.launcher.model;

import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
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
import android.support.p001v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.ibm.icu.text.ArabicShaping;
import com.txznet.weatherquery.TXZWeather;
import com.txznet.weatherquery.WeatherQueryManager;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
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
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempModusActivity;
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
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import com.wits.ksw.launcher.view.p006ug.WiewFocusUtils;
import com.wits.ksw.settings.bmw_id8.activity.BmwId8PempSettingsMainActivity;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes9.dex */
public class LauncherViewModel extends BaseViewModel {
    public static final String ALS_KEY_SHORTCUT_CLS_1 = "ALS_KEY_SHORTCUT_CLS_1";
    public static final String ALS_KEY_SHORTCUT_CLS_2 = "ALS_KEY_SHORTCUT_CLS_2";
    public static final String ALS_KEY_SHORTCUT_PKG_1 = "ALS_KEY_SHORTCUT_PKG_1";
    public static final String ALS_KEY_SHORTCUT_PKG_2 = "ALS_KEY_SHORTCUT_PKG_2";
    public static final String CLS_CHROME = "com.google.android.apps.chrome.Main";
    public static final int IMG_VIDEO_HEIGT = 52;
    public static final int IMG_VIDEO_WIDTH = 58;
    private static final long LONG_CLICK_TIME = 500;
    public static final String PEMP_KEY_SHORTCUT_CLS_1 = "PEMP_KEY_SHORTCUT_CLS_1";
    public static final String PEMP_KEY_SHORTCUT_CLS_1_V2 = "PEMP_KEY_SHORTCUT_CLS_1_V2";
    public static final String PEMP_KEY_SHORTCUT_CLS_2 = "PEMP_KEY_SHORTCUT_CLS_2";
    public static final String PEMP_KEY_SHORTCUT_CLS_2_V2 = "PEMP_KEY_SHORTCUT_CLS_2_V2";
    public static final String PEMP_KEY_SHORTCUT_CLS_3 = "PEMP_KEY_SHORTCUT_CLS_3";
    public static final String PEMP_KEY_SHORTCUT_CLS_3_V2 = "PEMP_KEY_SHORTCUT_CLS_3_V2";
    public static final String PEMP_KEY_SHORTCUT_PKG_1 = "PEMP_KEY_SHORTCUT_PKG_1";
    public static final String PEMP_KEY_SHORTCUT_PKG_1_V2 = "PEMP_KEY_SHORTCUT_PKG_1_V2";
    public static final String PEMP_KEY_SHORTCUT_PKG_2 = "PEMP_KEY_SHORTCUT_PKG_2";
    public static final String PEMP_KEY_SHORTCUT_PKG_2_V2 = "PEMP_KEY_SHORTCUT_PKG_2_V2";
    public static final String PEMP_KEY_SHORTCUT_PKG_3 = "PEMP_KEY_SHORTCUT_PKG_3";
    public static final String PEMP_KEY_SHORTCUT_PKG_3_V2 = "PEMP_KEY_SHORTCUT_PKG_3_V2";
    private static final String UNREAD_BROADCAST_ACTION = "com.wits.ksw.launcher.bubble.unread";
    public static final int WIDTH_IMG_BIG = 64;
    public static final int WIDTH_IMG_SMALL = 45;
    public static volatile View viewLastSel;
    public McuStatus.BenzData benzData;
    public int dialogHeight;
    public int dialogWidth;
    private BitmapDrawable efficientBitmapDrawable;
    public View lastViewFocused;
    public PopupWindow mAppsPopupWindow;
    private boolean musicId;
    private OnID8SkinChangeListener onID8SkinChangeListener;
    private BitmapDrawable personalBitmapDrawable;
    private BitmapDrawable sportBitmapDrawable;
    private boolean videoId;
    private Disposable weatherSubscribe;
    protected static final String TAG = LauncherViewModel.class.getSimpleName();
    public static MediaInfo mediaInfo = MediaImpl.getInstance().getMediaInfo();
    public static CarInfo carInfo = McuImpl.getInstance().getCarInfo();
    public static ObservableField<Boolean> bThirdMusic = new ObservableField<>();
    public static ObservableField<Boolean> bThirdVideo = new ObservableField<>();
    public static ObservableField<String> screenPixels = new ObservableField<>();
    public static ObservableField<Integer> screenWidth = new ObservableField<>();
    public static ObservableField<Integer> screenHeight = new ObservableField<>();
    public static ObservableField<Drawable> videoBG = new ObservableField<>();
    public static ObservableField<Drawable> musicBG = new ObservableField<>();
    public static WeatherInfo weatherInfo = new WeatherInfo();
    public static int width = 0;
    public static Map<String, Integer> mBubbleUnreadCountMap = new HashMap();
    private static long mCurrentTime = 0;
    private static HashMap<Integer, ObjectAnimator> animatorMaps = new HashMap<>();
    public final ObservableBoolean acControl = new ObservableBoolean();
    public ObservableField<Boolean> dashBoardMusicShow = new ObservableField<>();
    public final ObservableInt phoneConState = new ObservableInt();
    public ObservableField<String> btState = new ObservableField<>();
    public final ObservableField<String> month = new ObservableField<>();
    public final ObservableField<String> day = new ObservableField<>();
    public NaviInfo naviInfo = new NaviInfo();
    public final ObservableField<Boolean> hicar = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon1 = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon2 = new ObservableField<>();
    public ObservableField<Drawable> shortCutIcon3 = new ObservableField<>();
    public ObservableField<String> shortCutName1 = new ObservableField<>();
    public ObservableField<String> shortCutName2 = new ObservableField<>();
    public ObservableField<String> shortCutName3 = new ObservableField<>();
    public ControlBean controlBean = new ControlBean(this.context);
    public ObservableField<Boolean> isChangeModusStatusID8 = new ObservableField<>();
    public ObservableField<Boolean> isPersonalModus = new ObservableField<>();
    public ObservableField<Boolean> isSportModus = new ObservableField<>();
    public ObservableField<Boolean> isEfficientModus = new ObservableField<>();
    public ObservableField<Integer> id8TextColor = new ObservableField<>();
    public ObservableField<BitmapDrawable> id8ModusDrawable = new ObservableField<>();
    public final CompoundButton.OnCheckedChangeListener leftOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.4
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            LauncherViewModel.this.controlBean.leftBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), LauncherViewModel.this.benzData, LauncherViewModel.this);
            }
        }
    };
    public final CompoundButton.OnCheckedChangeListener rightOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.5
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            LauncherViewModel.this.controlBean.rightBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), LauncherViewModel.this.benzData, LauncherViewModel.this);
            }
        }
    };
    public List<View> alsID7UIViewList = new ArrayList();
    public View.OnFocusChangeListener carViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.8
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(2);
            }
        }
    };
    public View.OnFocusChangeListener musicViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.9
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: musicViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener weatherViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.10
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: musicViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(0);
                }
            }
        }
    };
    public View.OnFocusChangeListener videoViewFocusChangeListenerv2 = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.11
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: videoViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(2);
                }
            }
        }
    };
    public View.OnFocusChangeListener videoViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.12
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(LauncherViewModel.TAG, "onFocusChange: videoViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };
    public View.OnFocusChangeListener phoneViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.13
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(0);
                Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener");
            }
        }
    };
    public View.OnFocusChangeListener phoneViewFocusChangeListenerV2 = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.14
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(1);
                Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener");
            }
        }
    };
    private BroadcastReceiver timeReceiver = new BroadcastReceiver() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.18
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Date date = new Date();
            LauncherViewModel.this.setMonthDay(date);
        }
    };
    protected BroadcastReceiver bubbleUnreadReceiver = new BroadcastReceiver() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.19
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String packageName = intent.getStringExtra("packageName");
            Integer unreadCount = Integer.valueOf(intent.getIntExtra("unreadCount", 0));
            boolean containsKey = LauncherViewModel.mBubbleUnreadCountMap.containsKey(packageName);
            LauncherViewModel.mBubbleUnreadCountMap.put(packageName, unreadCount);
            Log.i(LauncherViewModel.TAG, "onReceive: packageName : " + packageName + " unreadCount : " + unreadCount + " containsKey : " + containsKey + ", mBubbleUnreadCountMap.size" + LauncherViewModel.mBubbleUnreadCountMap.size());
        }
    };
    protected BroadcastReceiver otherReceiver = new BroadcastReceiver() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.20
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (NaviInfo.AUTONAVI_STANDARD_BROADCAST_SEND.equalsIgnoreCase(action)) {
                if (LauncherViewModel.this.naviInfo.isGuideEnable()) {
                    LauncherViewModel.this.refreshNaviInfo(intent);
                }
            } else if (TextUtils.equals(action, "android.intent.action.PACKAGE_REMOVED")) {
                String packageName = intent.getDataString();
                Log.i(LauncherViewModel.TAG, "onReceive: otherReceiver ACTION_PACKAGE_REMOVED packageName " + packageName);
                BenzUtils.removeByPkg(packageName.split(":")[1]);
                BenzUtils.saveSystemCardSeq();
            }
        }
    };
    public View.OnFocusChangeListener kswId7SetCardFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.21
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            Log.i(LauncherViewModel.TAG, "onFocusChange: phoneViewFocusChangeListener hasFocus\uff1a" + hasFocus);
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(0);
            }
        }
    };
    public View.OnFocusChangeListener kswId7VideoCardFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.22
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            Log.i(LauncherViewModel.TAG, "onFocusChange: kswId7VideoCardFocusChangeListener hasFocus\uff1a" + hasFocus);
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(1);
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
        if (!TextUtils.isEmpty(clsMusic) && !KeyConfig.CLS_LOCAL_MUSIC.equals(clsMusic)) {
            bThirdMusic.set(true);
        } else {
            bThirdMusic.set(false);
        }
        if (!TextUtils.isEmpty(clsVideo) && !KeyConfig.CLS_LOCAL_VIDEO.equals(clsVideo)) {
            bThirdVideo.set(true);
        } else {
            bThirdVideo.set(false);
        }
    }

    public LauncherViewModel() {
        Log.i(TAG, "LauncherViewModel:");
    }

    @Override // com.wits.ksw.launcher.base.BaseViewModel
    public void resumeViewModel() {
        Log.d(TAG, "resumeViewModel");
        refreshLastViewFocused();
        refreshLexus();
        if (UiThemeUtils.isALS_ID7_UI(this.context)) {
            refreshMusicIconOrName(ALS_KEY_SHORTCUT_PKG_1, ALS_KEY_SHORTCUT_CLS_1);
        }
    }

    public void initControl() {
        try {
            this.benzData = McuStatus.BenzData.getStatusFromJson(PowerManagerApp.getStatusString("benzData"));
            this.controlBean.chassis.set(this.benzData.highChassisSwitch);
            this.controlBean.sport.set(this.benzData.airMaticStatus == 1);
            this.controlBean.rdarAssistance.set(this.benzData.auxiliaryRadar);
            this.controlBean.passairbar.set(this.benzData.airBagSystem);
            Log.i("\u63a7\u5236\u9762\u677f", "initData: \u5e95\u76d8\u5f00\u5173\uff1a" + this.benzData.highChassisSwitch + ",\u8fd0\u52a8\u6a21\u5f0f\uff1a" + (this.benzData.airMaticStatus == 1) + ",\u96f7\u8fbe\u8f85\u52a9\u5f00\u5173\uff1a" + this.benzData.auxiliaryRadar + " light1:" + this.benzData.light1 + " light2:" + this.benzData.light2 + " \u5b89\u5168\u6c14\u56ca " + this.benzData.airBagSystem);
        } catch (Exception e) {
            this.benzData = new McuStatus.BenzData();
        }
    }

    public void registerIContentObserver() {
        PowerManagerApp.registerIContentObserver("benzData", new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.1
            @Override // com.wits.pms.IContentObserver
            public void onChange() throws RemoteException {
                McuStatus.BenzData benzData = McuStatus.BenzData.getStatusFromJson(PowerManagerApp.getStatusString("benzData"));
                Log.i(LauncherViewModel.TAG, "benzData onChange: " + benzData.getJson());
                LauncherViewModel.this.controlBean.chassis.set(benzData.highChassisSwitch);
                LauncherViewModel.this.controlBean.sport.set(benzData.airMaticStatus == 1);
                LauncherViewModel.this.controlBean.rdarAssistance.set(benzData.auxiliaryRadar);
                LauncherViewModel.this.controlBean.passairbar.set(benzData.airBagSystem);
            }
        });
    }

    private void refreshMusicIconOrName(String keyshortcutpkg, String keyshortcutcls) {
        String pkg;
        String cls;
        try {
            pkg = Settings.System.getString(this.context.getContentResolver(), keyshortcutpkg);
            cls = Settings.System.getString(this.context.getContentResolver(), keyshortcutcls);
        } catch (Exception e) {
            e.printStackTrace();
            pkg = null;
            cls = null;
        }
        if (cls != null && pkg != null) {
            if (cls.equals(CLS_CHROME)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                this.shortCutName1.set(this.context.getResources().getString(C0899R.string.id7_browser));
            } else if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_music));
                this.shortCutName1.set(this.context.getResources().getString(C0899R.string.ksw_id7_music));
            } else if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                this.shortCutName1.set(this.context.getResources().getString(C0899R.string.ksw_id7_hd_video));
            } else if (cls.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName1.set(this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
            } else {
                ResolveInfo info1 = AppInfoUtils.findAppByPkgAndCls(this.context, pkg, cls);
                this.shortCutIcon1.set(AppInfoUtils.zoomDrawable(info1.activityInfo.loadIcon(this.context.getPackageManager()), AppInfoUtils.dip2px(this.context, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.context, screenWidth.get().intValue() <= 1280 ? 45.0f : 64.0f)));
                this.shortCutName1.set((String) info1.activityInfo.loadLabel(this.context.getPackageManager()));
            }
        }
    }

    private void refreshLexus() {
        if (UiThemeUtils.isLEXUS_UI(this.context)) {
            int openAcControl = Settings.System.getInt(this.context.getContentResolver(), KeyConfig.AC_CONTROL, 0);
            if (openAcControl == 1) {
                this.acControl.set(true);
            } else {
                this.acControl.set(false);
            }
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
        registerBubbleUnreadReceiver();
        registerBtContentObserver();
        MediaImpl.getInstance().initData();
        McuImpl.getInstance().init();
        if (UiThemeUtils.isUI_KSW_ID7(this.context) || UiThemeUtils.isUI_KSW_MBUX_1024(this.context)) {
            initControl();
            registerIContentObserver();
        }
        initThirdApps();
        DisplayMetrics dm = this.context.getResources().getDisplayMetrics();
        width = dm.widthPixels;
    }

    private void registerBubbleUnreadReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UNREAD_BROADCAST_ACTION);
        this.context.registerReceiver(this.bubbleUnreadReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWeather() {
        String str = TAG;
        Log.i(str, "TXZWeather loading: ");
        Locale sysLanguage = Locale.getDefault();
        String language = sysLanguage.getLanguage();
        Log.i(str, "TXZWeather language: " + language);
        try {
            sendWeatherRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendWeatherRequest() {
        WeatherQueryManager.getInstance().sendWeatherRequest(this.context, new WeatherQueryManager.WeatherCallback() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.2
            @Override // com.txznet.weatherquery.WeatherQueryManager.WeatherCallback
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

            @Override // com.txznet.weatherquery.WeatherQueryManager.WeatherCallback
            public void onFailed(int errorCode) {
                Log.i(LauncherViewModel.TAG, "TXZWeather onFailed: " + errorCode);
                if (errorCode == 6) {
                    return;
                }
                try {
                    LauncherViewModel.weatherInfo.loadFailed(errorCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startWeatherLooper() {
        weatherInfo.loading();
        this.weatherSubscribe = Observable.interval(0L, 30L, TimeUnit.MINUTES).subscribe(new Consumer<Long>() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.3
            @Override // io.reactivex.functions.Consumer
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
        WeatherQueryManager.getInstance().setUserSettingListener(this.activity, new WeatherQueryManager.UserSettingListener() { // from class: com.wits.ksw.launcher.model.-$$Lambda$LauncherViewModel$T6N_hhNQwPg7GJ7Iee6VVq-xKdg
            @Override // com.txznet.weatherquery.WeatherQueryManager.UserSettingListener
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
            if (TextUtils.isEmpty(usbPkg)) {
                return;
            }
            if (KeyConfig.CLS_LOCAL_MUSIC.equals(usbPkg)) {
                openApp(new Intent("com.wits.media.MUSIC"));
            } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(usbPkg)) {
                openApp(new Intent("com.wits.media.VIDEO"));
            } else {
                openApp(this.context.getPackageManager().getLaunchIntentForPackage(usbPkg));
            }
        }
    }

    public void openShouJiHuLian(View view) {
        addLastViewFocused(view);
        boolean flag = Build.DISPLAY.contains("8937");
        if (!flag) {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
            return;
        }
        int speed_play_switch = Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1);
        if (speed_play_switch != 2) {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
        } else {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.suding.speedplay"));
        }
    }

    public void openEq(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName(BenzUtils.EQ_PKG, "com.wits.csp.eq.view.MainActivity"));
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
        } else if (benzpane == 2) {
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
        Log.i(TAG, "onCheckedChanged_AUX: \u8f85\u52a9\u96f7\u8fbe\u5f00\u5173:" + this.benzData.getJson());
    }

    public void onSportClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(2);
        Log.i(TAG, "onCheckedChanged_AIR: \u8f85\u52a9\u96f7\u8fbe\u5f00\u5173:" + this.benzData.getJson());
    }

    public void onHighChasssisClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(1);
        Log.i(TAG, "onCheckedChanged: \u5e95\u76d8\u5347\u964d\u5f00\u5173:" + this.benzData.getJson());
    }

    public void showBrightnessDialog(View view) {
        int benzpane = KswUtils.getBenzpaneVersion();
        if (benzpane == 1) {
            if (view.getId() == C0899R.C0901id.brightnessBtn_left) {
                Ntg6ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 1);
            }
            if (view.getId() == C0899R.C0901id.brightnessBtn_right) {
                Ntg6ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 2);
            }
        } else if (benzpane == 2) {
            if (view.getId() == C0899R.C0901id.brightnessBtn_left) {
                Ntg630ControlView.getInstance().showBenzBrightnessDailog(view.getContext(), this.benzData, this, 1);
            }
            if (view.getId() == C0899R.C0901id.brightnessBtn_right) {
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
        int toggle = Settings.System.getInt(this.activity.getContentResolver(), KeyConfig.OEM_FM, 0);
        if (toggle == 0) {
            onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
        } else {
            openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.lexus.OEMFMActivity"));
        }
        addLastViewFocused(view);
    }

    public void openSettings(View view) {
        if (this.activity instanceof BmwId8PempSettingsMainActivity) {
            return;
        }
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
        this.personalBitmapDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, C0899R.C0900drawable.id8_main_icon_modus_personal));
        this.sportBitmapDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, C0899R.C0900drawable.id8_main_icon_modus_sport));
        this.efficientBitmapDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, C0899R.C0900drawable.id8_main_icon_modus_efficient));
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
        this.onID8SkinChangeListener.onSkinChangeLeftBar(C0899R.C0900drawable.bmw_id8_main_left_btn_yellow);
        this.onID8SkinChangeListener.onSkinChangeCardBGSelector(ID8LauncherConstants.ID8_SKIN_PERSONAL);
        this.onID8SkinChangeListener.onSkinChangeMusicAlbum(C0899R.C0900drawable.id8_main_icon_music_album_yellow);
    }

    private void displaySportModus() {
        this.isSportModus.set(true);
        this.id8ModusDrawable.set(this.sportBitmapDrawable);
        this.isPersonalModus.set(false);
        this.isEfficientModus.set(false);
        this.onID8SkinChangeListener.onSkinChangeLeftBar(C0899R.C0900drawable.bmw_id8_main_left_btn_red);
        this.onID8SkinChangeListener.onSkinChangeCardBGSelector(ID8LauncherConstants.ID8_SKIN_SPORT);
        this.onID8SkinChangeListener.onSkinChangeMusicAlbum(C0899R.C0900drawable.id8_main_icon_music_album_red);
    }

    private void displayEfficientModus() {
        this.isEfficientModus.set(true);
        this.id8ModusDrawable.set(this.efficientBitmapDrawable);
        this.isPersonalModus.set(false);
        this.isSportModus.set(false);
        this.onID8SkinChangeListener.onSkinChangeLeftBar(C0899R.C0900drawable.bmw_id8_main_left_btn_blue);
        this.onID8SkinChangeListener.onSkinChangeCardBGSelector(ID8LauncherConstants.ID8_SKIN_EFFICIENT);
        this.onID8SkinChangeListener.onSkinChangeMusicAlbum(C0899R.C0900drawable.id8_main_icon_music_album_blue);
    }

    public void enterChangeModus(View view) {
        Log.e(TAG, "enterChangeModus");
        Intent intent = new Intent(this.activity, ID8ModusActivity.class);
        this.isChangeModusStatusID8.set(true);
        this.id8TextColor.set(Integer.valueOf(SkinCompatResources.getColor(this.context, C0899R.color.id8_main_style_color)));
        addLastViewFocused(view);
        this.activity.startActivity(intent);
    }

    public void enterGsChangeModus(View view) {
        if (this.activity instanceof ID8GsModusActivity) {
            return;
        }
        Intent intent = new Intent(this.activity, ID8GsModusActivity.class);
        this.id8TextColor.set(Integer.valueOf(SkinCompatResources.getColor(this.context, C0899R.color.id8_main_style_color)));
        addLastViewFocused(view);
        this.activity.startActivity(intent);
    }

    public void enterPempChangeModus(View view) {
        if (this.activity instanceof ID8PempModusActivity) {
            return;
        }
        Intent intent = new Intent(this.activity, ID8PempModusActivity.class);
        this.id8TextColor.set(Integer.valueOf(SkinCompatResources.getColor(this.context, C0899R.color.id8_main_style_color)));
        addLastViewFocused(view);
        this.activity.startActivity(intent);
    }

    private void enterID8MainActivity() {
        if (UiThemeUtils.isUI_GS_ID8(this.context) || UiThemeUtils.isBMW_ID8_UI(this.context) || UiThemeUtils.isUI_PEMP_ID8(this.context)) {
            Intent intent = new Intent(this.activity, MainActivity.class);
            this.activity.startActivity(intent);
        }
    }

    public void exitChangeModus() {
        this.isChangeModusStatusID8.set(false);
    }

    public void changeModusToPersonal(View view) {
        boolean hasChanged = ID8LauncherConstants.checkModusHasChanged(ID8LauncherConstants.ID8_SKIN_PERSONAL);
        if (hasChanged) {
            displayPersonalModus();
            reloadSkinResources();
        }
        exitChangeModus();
        enterID8MainActivity();
    }

    public void changeModusToSport(View view) {
        boolean hasChanged = ID8LauncherConstants.checkModusHasChanged(ID8LauncherConstants.ID8_SKIN_SPORT);
        if (hasChanged) {
            displaySportModus();
            reloadSkinResources();
        }
        exitChangeModus();
        enterID8MainActivity();
    }

    public void changeModusToEfficient(View view) {
        boolean hasChanged = ID8LauncherConstants.checkModusHasChanged(ID8LauncherConstants.ID8_SKIN_EFFICIENT);
        if (hasChanged) {
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
            if (!TextUtils.isEmpty(pkg) && !TextUtils.isEmpty(cls) && !KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
                if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
                    openApp(new Intent("com.wits.media.VIDEO"));
                    return;
                }
                Intent intent = this.context.getPackageManager().getLaunchIntentForPackage(pkg);
                if (intent == null) {
                    openApp(new Intent("com.wits.media.MUSIC"));
                    return;
                }
                openApp(intent);
                try {
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            openApp(new Intent("com.wits.media.MUSIC"));
            return;
        }
        String pkg2 = Settings.System.getString(this.contentResolver, "defPlayApp");
        Log.i(TAG, "openMusic: pkg=" + pkg2);
        if (TextUtils.isEmpty(pkg2)) {
            pkg2 = "com.wits.media.MUSIC";
        }
        if (pkg2.equals("com.wits.ksw.music")) {
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

    public void openMusic(View view) {
        addLastViewFocused(view);
        openApp(new Intent("com.wits.media.MUSIC"));
    }

    public void openMusicMulti(View view) {
        String pkg = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG);
        String cls = Settings.System.getString(this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS);
        addLastViewFocused(view);
        if (!TextUtils.isEmpty(pkg) && !TextUtils.isEmpty(cls) && !KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
            if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
                openVideo(view);
                return;
            }
            ComponentName componentName = new ComponentName(pkg, cls);
            openAppByCls(componentName);
            return;
        }
        openMusic(view);
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
        ActivityManager myManager = (ActivityManager) this.context.getSystemService("activity");
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList) myManager.getRunningServices(100);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals("com.wits.ksw.music.KswMusicService")) {
                return true;
            }
        }
        return false;
    }

    public boolean isVideoServiceLived() {
        ActivityManager myManager = (ActivityManager) this.context.getSystemService("activity");
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList) myManager.getRunningServices(100);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals("com.wits.ksw.video.service.VideoService")) {
                return true;
            }
        }
        return false;
    }

    public void handleMusicOperator(final View view, int subCommand, final int keyCode) {
        Log.e(TAG, "id8GsOpenPauseMusic: " + getLastMode());
        if (!isMediaServiceLived()) {
            Intent serviceIntent = new Intent();
            serviceIntent.setComponent(new ComponentName("com.wits.ksw.music", "com.wits.ksw.music.KswMusicService"));
            serviceIntent.putExtra("mediaType", 11);
            this.context.startService(serviceIntent);
            new Handler().postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.6
                @Override // java.lang.Runnable
                public void run() {
                    LauncherViewModel.this.handleRealMusicOperator(view, keyCode);
                }
            }, 100L);
            return;
        }
        handleRealMusicOperator(view, keyCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRealMusicOperator(View view, int keyCode) {
        if (!isContinuityClick()) {
            setLastMode(1);
            if (85 == keyCode) {
                onSendCommand(2, 1000);
            } else if (88 == keyCode) {
                onSendCommand(2, 1001);
            } else if (87 == keyCode) {
                onSendCommand(2, 1002);
            }
        }
        View parent = (View) view.getParent();
        WiewFocusUtils.setViewRequestFocus(parent.findViewById(C0899R.C0901id.iv_mask));
    }

    public void handleVideoOperator(View view, int subCommand) {
        Log.e(TAG, "id8GsOpenPauseVideo: " + getLastMode());
        if (!isVideoServiceLived()) {
            openVideo(null);
            return;
        }
        if (!isContinuityClick()) {
            if (getLastMode() == 2) {
                onSendCommand(2, subCommand);
            } else {
                setLastMode(2);
                onSendCommand(2, subCommand);
            }
        }
        View parent = (View) view.getParent();
        WiewFocusUtils.setViewRequestFocus(parent.findViewById(C0899R.C0901id.iv_mask));
    }

    private void handleRealMusicOperator(View view, int subCommand, int keyCode) {
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
        View parent = (View) view.getParent();
        WiewFocusUtils.setViewRequestFocus(parent.findViewById(C0899R.C0901id.iv_mask));
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
        handleVideoOperator(view, PointerIconCompat.TYPE_HELP);
    }

    public void id8GsPreVideo(View view) {
        Log.e(TAG, "id8GsPreVideo: " + getLastMode());
        handleVideoOperator(view, PointerIconCompat.TYPE_WAIT);
    }

    public void id8GsNextVideo(View view) {
        Log.e(TAG, "id8GsNextVideo: " + getLastMode());
        handleVideoOperator(view, 1005);
    }

    public void startVideo(View view) {
        Log.w(TAG, "startVideo: ");
        try {
            boolean isVideoStop = PowerManagerApp.getManager().getStatusBoolean("video_stop");
            if (isVideoStop) {
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
            boolean isVideoStop = PowerManagerApp.getManager().getStatusBoolean("video_stop");
            if (isVideoStop) {
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
        if (!TextUtils.isEmpty(pkg) && !TextUtils.isEmpty(cls) && !KeyConfig.CLS_LOCAL_VIDEO.equals(cls)) {
            if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls)) {
                openMusic(view);
                return;
            }
            ComponentName componentName = new ComponentName(pkg, cls);
            openAppByCls(componentName);
            return;
        }
        openVideo(view);
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
            Toast.makeText(this.context, this.context.getString(C0899R.string.uninstall), 0).show();
        }
    }

    public void openVideo(View view) {
        addLastViewFocused(view);
        openApp(new Intent("com.wits.media.VIDEO"));
    }

    public void sendMcuCommand() {
        try {
            Log.d(TAG, "sendMcuCommand: \u53d1\u9001MCU\u53d1\u6307\u4ee4");
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.OPEN_MODE, "13");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "sendMcuCommand: " + e.toString());
        }
    }

    public void openShortCutApp(View view, int index, String keyshortpkg, String keyshortcls) {
        Log.d("openShortCutApp", index + "   ");
        String pkg1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortpkg);
        String cls1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortcls);
        String pkg2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortpkg);
        String cls2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortcls);
        String pkg3 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortpkg);
        String cls3 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortcls);
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
                        ComponentName componentName1 = new ComponentName(pkg1, cls1);
                        openAppByCls(componentName1);
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
                        ComponentName componentName2 = new ComponentName(pkg2, cls2);
                        openAppByCls(componentName2);
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
                if (!TextUtils.isEmpty(pkg3) && !TextUtils.isEmpty(cls3)) {
                    Log.d("openShortCutApp3", pkg3 + "   " + cls3);
                    if (KeyConfig.CLS_LOCAL_MUSIC.equals(cls3)) {
                        openMusic(view);
                        return;
                    } else if (KeyConfig.CLS_LOCAL_VIDEO.equals(cls3)) {
                        openVideo(view);
                        return;
                    } else {
                        ComponentName componentName3 = new ComponentName(pkg3, cls3);
                        openAppByCls(componentName3);
                        sendMcuCommand();
                        return;
                    }
                }
                openVideo(view);
                return;
            default:
                return;
        }
    }

    public void refreshShortCutInfo(String keyshortpkg1, String keyshortcls1, String keyshortpkg2, String keyshortcls2, String keyshortpkg3, String keyshortcls3) {
        String cls3;
        String pkg1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortpkg1);
        String cls1 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortcls1);
        String pkg2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortpkg2);
        String cls2 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortcls2);
        String pkg3 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortpkg3);
        String cls32 = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), keyshortcls3);
        if (TextUtils.isEmpty(pkg1) || TextUtils.isEmpty(cls1)) {
            cls3 = cls32;
        } else {
            Log.e("refreshShortCutInfo", "pk1 = " + pkg1 + " cls1 = " + cls1);
            if (cls1.equals(CLS_CHROME)) {
                AppInfoUtils.findAppByPkgAndCls(this.activity, pkg1, cls1);
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                this.shortCutName1.set(this.activity.getResources().getString(C0899R.string.id6_browser));
                cls3 = cls32;
            } else if (cls1.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                if (UiThemeUtils.isALS_ID7_UI(this.activity)) {
                    this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_music));
                } else {
                    this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_music_n));
                }
                this.shortCutName1.set(this.activity.getResources().getString(C0899R.string.ksw_id7_music));
                cls3 = cls32;
            } else if (cls1.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                this.shortCutName1.set(this.activity.getResources().getString(C0899R.string.ksw_id7_hd_video));
                cls3 = cls32;
            } else if (cls1.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon1.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName1.set(this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
                cls3 = cls32;
            } else {
                ResolveInfo info1 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg1, cls1);
                cls3 = cls32;
                this.shortCutIcon1.set(AppInfoUtils.zoomDrawable(info1.activityInfo.loadIcon(this.activity.getPackageManager()), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                this.shortCutName1.set((String) info1.activityInfo.loadLabel(this.activity.getPackageManager()));
            }
        }
        if (!TextUtils.isEmpty(pkg2) && !TextUtils.isEmpty(cls2)) {
            Log.e("refreshShortCutInfo", "pk2 = " + pkg2 + " cls2 = " + cls2);
            if (cls2.equals(CLS_CHROME)) {
                AppInfoUtils.findAppByPkgAndCls(this.activity, pkg2, cls2);
                this.shortCutIcon2.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                this.shortCutName2.set(this.activity.getResources().getString(C0899R.string.id6_browser));
            } else if (cls2.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                if (UiThemeUtils.isALS_ID7_UI(this.activity)) {
                    this.shortCutIcon2.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_music));
                } else {
                    this.shortCutIcon2.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_music_n));
                }
                this.shortCutName2.set(this.activity.getResources().getString(C0899R.string.ksw_id7_music));
            } else if (cls2.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon2.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                this.shortCutName2.set(this.activity.getResources().getString(C0899R.string.ksw_id7_hd_video));
            } else if (cls2.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon2.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName2.set(this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
            } else {
                ResolveInfo info2 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg2, cls2);
                this.shortCutIcon2.set(AppInfoUtils.zoomDrawable(info2.activityInfo.loadIcon(this.activity.getPackageManager()), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                this.shortCutName2.set((String) info2.activityInfo.loadLabel(this.activity.getPackageManager()));
            }
        }
        if (!TextUtils.isEmpty(pkg3) && !TextUtils.isEmpty(cls3)) {
            String cls33 = cls3;
            Log.e("refreshShortCutInfo", "pk3 = " + pkg3 + " cls3 = " + cls33);
            if (cls33.equals(CLS_CHROME)) {
                AppInfoUtils.findAppByPkgAndCls(this.activity, pkg3, cls33);
                this.shortCutIcon3.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                this.shortCutName3.set(this.activity.getResources().getString(C0899R.string.id6_browser));
            } else if (cls33.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                this.shortCutIcon3.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_music_n));
                this.shortCutName3.set(this.activity.getResources().getString(C0899R.string.ksw_id7_music));
            } else if (cls33.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                this.shortCutIcon3.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                this.shortCutName3.set(this.activity.getResources().getString(C0899R.string.ksw_id7_hd_video));
            } else if (cls33.equals("com.google.android.maps.MapsActivity")) {
                this.shortCutIcon3.set(this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                this.shortCutName3.set(this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
            } else {
                ResolveInfo info3 = AppInfoUtils.findAppByPkgAndCls(this.activity, pkg3, cls33);
                this.shortCutIcon3.set(AppInfoUtils.zoomDrawable(info3.activityInfo.loadIcon(this.activity.getPackageManager()), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(this.activity, screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                this.shortCutName3.set((String) info3.activityInfo.loadLabel(this.activity.getPackageManager()));
            }
        }
    }

    public void initPopWinowGridApps(View view, final int indexMenu, final String keyshortpkg, final String keyshortcls) {
        List<LexusLsAppSelBean> list;
        if (this.mAppsPopupWindow != null) {
            this.mAppsPopupWindow = null;
        }
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        View layout = inflater.inflate(C0899R.C0902layout.pemp_id7_pop_layout, (ViewGroup) null);
        GridView gridView = (GridView) layout.findViewById(C0899R.C0901id.gridView);
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
        gridView.setAdapter((ListAdapter) new AppsGridAdapter(list, this.activity, C0899R.C0902layout.dialog_grid_app_item));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.wits.ksw.launcher.model.LauncherViewModel.7
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
                Object obj;
                Object obj2;
                LexusLsAppSelBean bean = (LexusLsAppSelBean) parent.getItemAtPosition(position);
                Log.d(LauncherViewModel.TAG, "appinfo =" + bean.toString());
                int i = indexMenu;
                if (i == 1) {
                    if (UiThemeUtils.isALS_ID7_UI(LauncherViewModel.this.context)) {
                        String appPkg = bean.getAppPkg();
                        ContentResolver contentResolver = LauncherViewModel.this.context.getContentResolver();
                        obj2 = LauncherViewModel.CLS_CHROME;
                        if (!appPkg.equals(Settings.System.getString(contentResolver, LauncherViewModel.ALS_KEY_SHORTCUT_PKG_2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_CLS_2))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_CLS_2) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_PKG_2) == null && bean.getAppMainAty().equals("com.google.android.maps.MapsActivity")) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        obj2 = LauncherViewModel.CLS_CHROME;
                    }
                    if (UiThemeUtils.isID7_ALS(LauncherViewModel.this.context)) {
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2)) && !bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2) != null || Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2) != null || !bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                                if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (UiThemeUtils.isID7_ALS_V2(LauncherViewModel.this.context)) {
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2)) && !bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2) != null || Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2) != null || !bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                                if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    Settings.System.putString(LauncherViewModel.this.contentResolver, keyshortpkg, bean.getAppPkg());
                    Settings.System.putString(LauncherViewModel.this.contentResolver, keyshortcls, bean.getAppMainAty());
                    LauncherViewModel.this.shortCutName1.set(bean.getAppName());
                    if (bean.getAppMainAty().equals(obj2)) {
                        LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                        LauncherViewModel.this.shortCutName1.set(LauncherViewModel.this.activity.getResources().getString(C0899R.string.id6_browser));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        if (UiThemeUtils.isALS_ID7_UI(LauncherViewModel.this.activity)) {
                            LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_music));
                        } else {
                            LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_music_n));
                        }
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                    } else if (bean.getAppMainAty().equals("com.google.android.maps.MapsActivity")) {
                        LauncherViewModel.this.shortCutIcon1.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                        LauncherViewModel.this.shortCutName1.set(LauncherViewModel.this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
                    } else {
                        LauncherViewModel.this.shortCutIcon1.set(AppInfoUtils.zoomDrawable(bean.getAppIcon(), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                    }
                } else if (i == 2) {
                    if (UiThemeUtils.isALS_ID7_UI(LauncherViewModel.this.context)) {
                        obj = "com.google.android.maps.MapsActivity";
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_PKG_1)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_CLS_1))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_CLS_1) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_PKG_1) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        obj = "com.google.android.maps.MapsActivity";
                    }
                    if (UiThemeUtils.isID7_ALS(LauncherViewModel.this.context)) {
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1)) && !bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1) != null || Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1) != null || !bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                                if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (UiThemeUtils.isID7_ALS_V2(LauncherViewModel.this.context)) {
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2)) && !bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2) != null || Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2) != null || !bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                                if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_3_V2) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_3_V2) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    Settings.System.putString(LauncherViewModel.this.contentResolver, keyshortpkg, bean.getAppPkg());
                    Settings.System.putString(LauncherViewModel.this.contentResolver, keyshortcls, bean.getAppMainAty());
                    LauncherViewModel.this.shortCutName2.set(bean.getAppName());
                    if (bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                        LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                        LauncherViewModel.this.shortCutName2.set(LauncherViewModel.this.activity.getResources().getString(C0899R.string.id6_browser));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        if (UiThemeUtils.isALS_ID7_UI(LauncherViewModel.this.activity)) {
                            LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_music));
                        } else {
                            LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_music_n));
                        }
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                    } else if (bean.getAppMainAty().equals(obj)) {
                        LauncherViewModel.this.shortCutIcon2.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                        LauncherViewModel.this.shortCutName2.set(LauncherViewModel.this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
                    } else {
                        LauncherViewModel.this.shortCutIcon2.set(AppInfoUtils.zoomDrawable(bean.getAppIcon(), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f), AppInfoUtils.dip2px(LauncherViewModel.this.activity, LauncherViewModel.screenWidth.get().intValue() > 1280 ? 64.0f : 45.0f)));
                    }
                } else if (i == 3) {
                    if (UiThemeUtils.isID7_ALS(LauncherViewModel.this.context)) {
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1)) && !bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1) != null || Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1) != null || !bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                                if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (UiThemeUtils.isID7_ALS_V2(LauncherViewModel.this.context)) {
                        if (!bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2)) && !bean.getAppPkg().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2)) && !bean.getAppMainAty().equals(Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2))) {
                            if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_1_V2) != null || Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_1_V2) != null || !bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                                if (Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_CLS_2_V2) == null && Settings.System.getString(LauncherViewModel.this.context.getContentResolver(), LauncherViewModel.PEMP_KEY_SHORTCUT_PKG_2_V2) == null && bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    Settings.System.putString(LauncherViewModel.this.contentResolver, keyshortpkg, bean.getAppPkg());
                    Settings.System.putString(LauncherViewModel.this.contentResolver, keyshortcls, bean.getAppMainAty());
                    LauncherViewModel.this.shortCutName3.set(bean.getAppName());
                    if (bean.getAppMainAty().equals(LauncherViewModel.CLS_CHROME)) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_browser_n));
                        LauncherViewModel.this.shortCutName3.set(LauncherViewModel.this.activity.getResources().getString(C0899R.string.id6_browser));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_music_n));
                    } else if (bean.getAppMainAty().equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.id7_main_video_n));
                    } else if (bean.getAppMainAty().equals("com.google.android.maps.MapsActivity")) {
                        LauncherViewModel.this.shortCutIcon3.set(LauncherViewModel.this.activity.getResources().getDrawable(C0899R.C0900drawable.als_sp_id7_main_left_icon_navi));
                        LauncherViewModel.this.shortCutName3.set(LauncherViewModel.this.activity.getResources().getString(C0899R.string.ksw_id7_navi));
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
        Log.i(TAG, String.format("width = %s ;height= %s;", Integer.valueOf(width2), Integer.valueOf(height)));
        this.mAppsPopupWindow.showAtLocation(view, 19, 180, -60);
    }

    public void openDashboard(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity"));
    }

    public void onSendCommand(final int command, final int subCommand) {
        Log.i(TAG, "onSendCommand: command:" + command + " subCommand:" + subCommand);
        try {
            WitsCommand.sendCommand(command, subCommand, null);
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
        intent.setComponent(new ComponentName(BenzUtils.WEATHER_PKG, "com.txznet.weather.MainActivity"));
        intent.setFlags(268435488);
        openApp(intent);
    }

    public void openNaviApp() {
        String naiPackge = Settings.System.getString(this.contentResolver, KeyConfig.NAVI_DEFUAL);
        boolean isVersion12 = Build.VERSION.RELEASE.contains("12") || Build.VERSION.RELEASE.contains("13");
        String str = TAG;
        Log.w(str, "isVersion12 :" + isVersion12);
        if (isVersion12 && AppInfoUtils.isContainFreedomMap(naiPackge) && UiThemeUtils.isUI_GS_ID8(this.activity)) {
            launchApp(naiPackge, 1);
        } else {
            openMapApp(this.context.getPackageManager().getLaunchIntentForPackage(naiPackge));
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

    public View getLastViewFocused() {
        return this.lastViewFocused;
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
            Log.d(TAG, "setClearViewState: alsID7UIViewList\u7684\u957f\u5ea6" + this.alsID7UIViewList.size());
        }
    }

    public void refreshLastViewFocused() {
        if (UiThemeUtils.isALS_ID7_UI(this.context)) {
            View view = this.lastViewFocused;
            if (view != null && !view.isSelected() && MainActivity.alsId7UiMainBinding != null) {
                if (this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton1.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton2.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton3.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton4.getId() && this.lastViewFocused.getId() != MainActivity.alsId7UiMainBinding.menuButton5.getId()) {
                    Log.d(TAG, "refreshLastViewFocused: \u4e0d\u7b49\u4e8enull\u54e6\uff01");
                    MainActivity.alsId7UiMainBinding.menuButton1.clearFocus();
                    MainActivity.alsId7UiMainBinding.menuButton2.clearFocus();
                    MainActivity.alsId7UiMainBinding.menuButton3.clearFocus();
                    MainActivity.alsId7UiMainBinding.menuButton4.clearFocus();
                    MainActivity.alsId7UiMainBinding.menuButton5.clearFocus();
                    setClearViewState();
                    this.lastViewFocused.setSelected(true);
                    return;
                }
                String str = TAG;
                Log.d(str, "refreshLastViewFocused: \u4e0d\u7b49\u4e8enull\u54e6\uff01else");
                setClearViewState();
                this.lastViewFocused.setFocusableInTouchMode(true);
                this.lastViewFocused.requestFocus();
                this.lastViewFocused.setFocusableInTouchMode(false);
                Log.i(str, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
                return;
            }
            return;
        }
        View view2 = this.lastViewFocused;
        if (view2 != null && !view2.isSelected()) {
            this.lastViewFocused.setFocusableInTouchMode(true);
            this.lastViewFocused.requestFocus();
            this.lastViewFocused.setFocusableInTouchMode(false);
            Log.i(TAG, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
            return;
        }
        Log.i(TAG, "refreshLastViewFocused: lastViewFocused null  ");
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
        this.contentResolver.registerContentObserver(Settings.System.getUriFor(KeyConfig.Android_Bt_Switch), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.LauncherViewModel.15
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                LauncherViewModel.this.setBtState();
            }
        });
        this.contentResolver.registerContentObserver(Settings.System.getUriFor("btSwitch"), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.LauncherViewModel.16
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                LauncherViewModel.this.setBtState();
            }
        });
        this.contentResolver.registerContentObserver(Settings.System.getUriFor("ksw_bluetooth"), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.LauncherViewModel.17
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                int bluetooth = Settings.System.getInt(LauncherViewModel.this.contentResolver, "ksw_bluetooth", 0);
                LauncherViewModel.this.setPhoneConState(bluetooth);
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
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.context.registerReceiver(this.otherReceiver, intentFilter);
    }

    public void setPhoneConState(int state) {
        this.phoneConState.set(state);
    }

    public void setBtState() {
        int btOff = Settings.System.getInt(this.contentResolver, KeyConfig.Android_Bt_Switch, 1);
        int btSwitch = Settings.System.getInt(this.contentResolver, "btSwitch", 1);
        int state = Settings.System.getInt(this.contentResolver, "ksw_bluetooth", 0);
        Log.d(TAG, "setBtState:android_Bt_Switch==" + btOff + " ksw_bluetooth==" + state + " btSwitch==" + btSwitch);
        if (btOff == 0 || btSwitch == 0) {
            this.btState.set(this.context.getString(C0899R.string.bt_text_bt_closed));
        } else if (state == 1) {
            this.btState.set(this.context.getString(C0899R.string.ksw_id7_connected_phone));
        } else {
            this.btState.set(this.context.getString(C0899R.string.ksw_id7_not_connected_phone));
        }
    }

    public void setMonthDay(Date date) {
        String month = KswUtils.formatMonth(date);
        String day = KswUtils.formatDay(date);
        this.month.set(month);
        this.day.set(day);
        String times = new SimpleDateFormat(KswUtils.is24Hour() ? "yyyy/MM/dd hh:mm:ss" : "yyyy/M/d h:m:s").format(date);
        Log.i(TAG, "setMonthDay: times:-------------->>>" + times);
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
        } else if (iKey_type == 10019) {
            if (iExtra_state == 2 || iExtra_state == 9 || iExtra_state == 12) {
                this.naviInfo.showGuideView.set(8);
            }
        }
    }

    public void btnMusicPrev() {
        try {
            int lastMode = PowerManagerApp.getStatusInt("lastMode");
            if (lastMode == 1) {
                KswUtils.sendKeyDownUpSync(88);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void btnMusicPlayPause() {
        try {
            int lastMode = PowerManagerApp.getStatusInt("lastMode");
            if (lastMode == 1) {
                KswUtils.sendKeyDownUpSync(85);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void btnMusicNext() {
        try {
            int lastMode = PowerManagerApp.getStatusInt("lastMode");
            if (lastMode == 1) {
                KswUtils.sendKeyDownUpSync(87);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setPempId8SpeedRotation(ImageView imageView, int rota) {
        Log.i(TAG, "setPempId8SpeedRotation: carInfo.unit.get()=" + carInfo.unit.get());
        float rotaa = (float) (rota * 0.72d);
        setSpeedRotationBet(imageView, rotaa);
    }

    public static void setRotation(ImageView imageView, int rota) {
        setSpeedRotationBet(imageView, rota);
    }

    public static void setSpeedRotation(ImageView imageView, int rota) {
        if (KswUtils.ismph()) {
            BigDecimal result = new BigDecimal((rota / 0.621d) * 0.968d);
            setSpeedRotationBet(imageView, result.floatValue());
            return;
        }
        setSpeedRotationBet(imageView, rota);
    }

    public static void setSpeedRotationBet(ImageView imageView, float rota) {
        int delay = McuImpl.getInstance().carInfo.delay.get().intValue();
        ObjectAnimator objectAnimator = animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", rota);
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
        objectAnimator.setDuration(delay);
        objectAnimator.setFloatValues(rota);
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
            base_offset = Math.round(Math.round((100.0f * tmpSpeed) / 20.0f));
        }
        int delay = McuImpl.getInstance().carInfo.delay.get().intValue();
        ObjectAnimator objectAnimator = animatorMaps.get(Integer.valueOf(imageView.getId()));
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", tmpSpeed);
            animatorMaps.put(Integer.valueOf(imageView.getId()), objectAnimator);
        }
        if (delay != 0 && delay > 150) {
        }
        if (objectAnimator.isStarted()) {
            objectAnimator.cancel();
        }
        float tmpSpeed2 = Math.round(Math.round(base_offset * base_angle) / 100);
        Log.d("liuhao", "setRotationBySpeed tmpSpeed 2 = " + tmpSpeed2);
        imageView.setRotation(0.2f + tmpSpeed2);
    }

    @Override // com.wits.ksw.launcher.base.BaseViewModel, android.arch.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
        Disposable disposable = this.weatherSubscribe;
        if (disposable != null && !disposable.isDisposed()) {
            this.weatherSubscribe.dispose();
        }
    }

    public void openID8Edit(View view) {
        Log.i(TAG, "openID8Edit: ");
        Intent intent = new Intent(this.activity, ID8EditActivity.class);
        openApp(intent);
    }

    public void openID8GsEdit(View view) {
        Log.i(TAG, "openID8GsEdit: ");
        Intent intent = new Intent(this.activity, ID8GsEditActivity.class);
        openApp(intent);
    }

    public void openID8PempEdit(View view) {
        Log.i(TAG, "openID8PempEdit: ");
        Intent intent = new Intent(this.activity, ID8PempEditActivity.class);
        openApp(intent);
    }

    public void dashboardMusicLay(View view) {
        this.dashBoardMusicShow.set(false);
    }

    public void dashboardWeatherLay(View view) {
        this.dashBoardMusicShow.set(true);
    }
}
