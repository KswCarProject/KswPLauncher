package com.wits.ksw.launcher.model;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.KswApplication;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseViewModel;
import com.wits.ksw.launcher.bean.CarInfo;
import com.wits.ksw.launcher.bean.MediaInfo;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.utils.NaviInfo;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class LauncherViewModel extends BaseViewModel {
    private static HashMap<Integer, ObjectAnimator> animatorMaps = new HashMap<>();
    public static CarInfo carInfo = McuImpl.getInstance().getCarInfo();
    public static MediaInfo mediaInfo = MediaImpl.getInstance().getMediaInfo();
    public ObservableField<String> btState = new ObservableField<>();
    public View.OnFocusChangeListener carViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus && MainActivity.mainActivity != null) {
                MainActivity.mainActivity.setCurrentItem(2);
            }
        }
    };
    public final ObservableField<String> day = new ObservableField<>();
    private View lastViewFocused;
    public final ObservableField<String> month = new ObservableField<>();
    public View.OnFocusChangeListener musicViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(KswApplication.TAG, "onFocusChange: musicViewFocusChangeListener");
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
                Log.i(KswApplication.TAG, "onFocusChange: phoneViewFocusChangeListener");
            }
        }
    };
    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LauncherViewModel.this.setMonthDay(new Date());
        }
    };
    public View.OnFocusChangeListener videoViewFocusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                Log.i(KswApplication.TAG, "onFocusChange: videoViewFocusChangeListener");
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.setCurrentItem(1);
                }
            }
        }
    };

    public LauncherViewModel() {
        Log.i(KswApplication.TAG, "LauncherViewModel:");
    }

    public void resumeViewModel() {
        refreshLastViewFocused();
    }

    public void initData() {
        int bluetooth = Settings.System.getInt(this.contentResolver, "ksw_bluetooth", 0);
        Log.i(KswApplication.TAG, "initData: bluetooth:" + bluetooth);
        setPhoneConState(bluetooth);
        setBtState();
        setMonthDay(new Date());
        registerTimeReceiver();
        registerOtherReceiver();
        registerBtContentObserver();
        MediaImpl.getInstance().initData();
        McuImpl.getInstance().init();
    }

    public void onItemClick(View view, String pkg, String cls) {
        addLastViewFocused(view);
        onItemClick(pkg, cls);
    }

    public void onItemClick(String pkg, String cls) {
        openApp(new ComponentName(pkg, cls));
        Log.i(KswApplication.TAG, "onItemClick: " + pkg + "/" + cls);
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
            Log.i(KswApplication.TAG, "openDvr: onSendCommand:609");
        } else if (KswUtils.getDvrType() == 2) {
            String usbPkg = KswUtils.getUsbDvrPkg();
            Log.i(KswApplication.TAG, "openDvr: usbPkg:" + usbPkg);
            if (!TextUtils.isEmpty(usbPkg)) {
                openApp(this.context.getPackageManager().getLaunchIntentForPackage(usbPkg));
            }
        }
    }

    public void openShouJiHuLian(View view) {
        addLastViewFocused(view);
        openApp(this.context.getPackageManager().getLaunchIntentForPackage("net.easyconn"));
    }

    public void openBrowser(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName("com.android.chrome", "com.google.android.apps.chrome.Main"));
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

    public void openCar(View view) {
        onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
        addLastViewFocused(view);
    }

    public void openSettings(View view) {
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity"));
        addLastViewFocused(view);
    }

    public void openChoseMusic(View view) {
        addLastViewFocused(view);
        String pkg = Settings.System.getString(this.contentResolver, "defPlayApp");
        Log.i(KswApplication.TAG, "openMusic: pkg=" + pkg);
        if (TextUtils.isEmpty(pkg)) {
            pkg = "com.wits.media.MUSIC";
        }
        if (pkg.equals("com.wits.ksw.media")) {
            openApp(new Intent("com.wits.media.MUSIC"));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMusic(View view) {
        addLastViewFocused(view);
        openApp(new Intent("com.wits.media.MUSIC"));
    }

    public void openVideo(View view) {
        addLastViewFocused(view);
        openApp(new Intent("com.wits.media.VIDEO"));
    }

    public void openDashboard(View view) {
        addLastViewFocused(view);
        openApp(new ComponentName(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity"));
    }

    public void onSendCommand(int command, int subCommand) {
        Log.i(KswApplication.TAG, "onSendCommand: command:" + command + " subCommand:" + subCommand);
        try {
            WitsCommand.sendCommand(command, subCommand, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(KswApplication.TAG, "onSendCommand: " + e.getMessage());
        }
    }

    public void openNaviApp(View view) {
        addLastViewFocused(view);
        openNaviApp();
    }

    public void openNaviApp() {
        String naiPackge = Settings.System.getString(this.contentResolver, KeyConfig.NAVI_DEFUAL);
        openApp(this.context.getPackageManager().getLaunchIntentForPackage(naiPackge));
        Log.i(KswApplication.TAG, "openNaviApp: " + naiPackge);
    }

    public void openBtApp(View view) {
        addLastViewFocused(view);
        openBtApp();
    }

    public void openBtApp() {
        onSendCommand(1, WitsCommand.SystemCommand.OPEN_BT);
        Log.i(KswApplication.TAG, "openBtApp: ");
    }

    public void addLastViewFocused(View view) {
        this.lastViewFocused = view;
        KswUtils.saveLastViewId(this.context, view);
    }

    public void refreshLastViewFocused() {
        if (this.lastViewFocused != null) {
            this.lastViewFocused.setFocusableInTouchMode(true);
            this.lastViewFocused.requestFocus();
            this.lastViewFocused.setFocusableInTouchMode(false);
            Log.i(KswApplication.TAG, "refreshLastViewFocused: lastViewFocused=" + this.lastViewFocused.getId());
            return;
        }
        Log.i(KswApplication.TAG, "refreshLastViewFocused: lastViewFocused null  ");
    }

    public void refreshViewFocused(View view) {
        if (view != null) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setFocusableInTouchMode(false);
            Log.i(KswApplication.TAG, "refreshLastViewFocused: lastViewFocused=" + view.getId());
            return;
        }
        Log.i(KswApplication.TAG, "refreshLastViewFocused: lastViewFocused null  ");
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
        Log.d(KswApplication.TAG, "setBtState:android_Bt_Switch===" + btOff);
        if (TextUtils.equals(btOff, "0")) {
            this.btState.set(this.context.getString(R.string.bt_text_bt_closed));
            return;
        }
        int state = Settings.System.getInt(this.contentResolver, "ksw_bluetooth", 0);
        Log.d(KswApplication.TAG, "setBtState:ksw_bluetooth===" + state);
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
        String times = new SimpleDateFormat(KswUtils.is24Hour() ? "yyyy/MM/dd hh:mm:ss" : "yyyy/M/d h:m:s").format(date);
        Log.i(KswApplication.TAG, "setMonthDay: times:-------------->>>" + times);
    }

    public void refreshNaviInfo(Intent intent) {
        int iKey_type = intent.getIntExtra("KEY_TYPE", 0);
        int iExtra_state = intent.getIntExtra("EXTRA_STATE", 0);
        Log.w(KswApplication.TAG, "refreshNaviInfo: iKey_type=" + iKey_type + " iExtra_state=" + iExtra_state);
        Bundle bundle = intent.getExtras();
        if (iKey_type == 10001) {
            if (bundle != null) {
                int type = bundle.getInt(NaviInfo.TYPE);
                Log.i(KswApplication.TAG, "refreshNaviInfo: type=" + type);
                if (type == 1 || type == 0) {
                    this.naviInfo.next_road_name.set(bundle.getString(NaviInfo.NEXT_ROAD_NAME));
                    ObservableField<Integer> observableField = this.naviInfo.icon;
                    NaviInfo naviInfo2 = this.naviInfo;
                    observableField.set(Integer.valueOf(NaviInfo.formatIcon(bundle.getInt(NaviInfo.ICON))));
                    ObservableField<String> observableField2 = this.naviInfo.route_remain_dis;
                    NaviInfo naviInfo3 = this.naviInfo;
                    observableField2.set(NaviInfo.formatDistance(bundle.getInt(NaviInfo.ROUTE_REMAIN_DIS)));
                    ObservableField<String> observableField3 = this.naviInfo.route_remain_time;
                    NaviInfo naviInfo4 = this.naviInfo;
                    observableField3.set(NaviInfo.formatTime(bundle.getInt(NaviInfo.ROUTE_REMAIN_TIME)));
                    ObservableField<String> observableField4 = this.naviInfo.seg_remain_dis;
                    NaviInfo naviInfo5 = this.naviInfo;
                    observableField4.set(NaviInfo.formatDistance(bundle.getInt(NaviInfo.SEG_REMAIN_DIS)));
                    this.naviInfo.camera_speed.set(Integer.valueOf(bundle.getInt(NaviInfo.CAMERA_SPEED)));
                    this.naviInfo.camera_type.set(Integer.valueOf(bundle.getInt(NaviInfo.CAMERA_TYPE)));
                    Log.i(KswApplication.TAG, "refreshNaviInfo: next_road_name=" + this.naviInfo.next_road_name.get());
                    Log.i(KswApplication.TAG, "refreshNaviInfo: icon=" + bundle.getInt(NaviInfo.ICON));
                    Log.i(KswApplication.TAG, "refreshNaviInfo: route_remain_dis=" + this.naviInfo.route_remain_dis.get());
                    Log.i(KswApplication.TAG, "refreshNaviInfo: route_remain_time=" + this.naviInfo.route_remain_time.get());
                    Log.i(KswApplication.TAG, "refreshNaviInfo: seg_remain_dis=" + this.naviInfo.seg_remain_dis.get());
                    Log.i(KswApplication.TAG, "refreshNaviInfo: camera_speed=" + this.naviInfo.camera_speed.get());
                    Log.i(KswApplication.TAG, "refreshNaviInfo: camera_type=" + this.naviInfo.camera_type.get());
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

    @BindingAdapter({"setTurnSpeedRotation"})
    public static void setRotation(ImageView imageView, int rota) {
        setSpeedRotationBet(imageView, (float) rota);
    }

    @BindingAdapter({"setSpeedRotation"})
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

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Log.i(KswApplication.TAG, "onCleared: ");
    }
}
