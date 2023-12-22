package com.wits.ksw.launcher.model;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.view.Ntg630ControlView;
import com.wits.ksw.launcher.view.Ntg6ControlView;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Bean;
import com.wits.ksw.launcher.view.benzmbux2021ksw.bean.BenzMbux2021KswBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;

/* loaded from: classes9.dex */
public class BcVieModel extends LauncherViewModel {
    public ObservableInt bcPagePosition = new ObservableInt();
    public ObservableBoolean isLeftArrowDisplay = new ObservableBoolean();
    public ObservableBoolean isRightArrowDisplay = new ObservableBoolean();
    public MutableLiveData<String> songInfo = new MutableLiveData<>();
    public ObservableBoolean itemIconClassical = new ObservableBoolean();
    public ObservableBoolean isYO = new ObservableBoolean();
    public ObservableBoolean isEditState = new ObservableBoolean();
    public ObservableField<String> selectCardName = new ObservableField<>();
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    public final CompoundButton.OnCheckedChangeListener chassisOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcVieModel.5
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.benzData.highChassisSwitch = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcVieModel.this.benzData.getJson());
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: \u5e95\u76d8\u5347\u964d\u5f00\u5173:" + BcVieModel.this.benzData.auxiliaryRadar);
        }
    };
    public final CompoundButton.OnCheckedChangeListener radarOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcVieModel.6
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.benzData.auxiliaryRadar = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcVieModel.this.benzData.getJson());
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: \u8f85\u52a9\u96f7\u8fbe\u5f00\u5173:" + BcVieModel.this.benzData.auxiliaryRadar);
        }
    };
    public final CompoundButton.OnCheckedChangeListener sportOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcVieModel.7
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.benzData.airMaticStatus = isChecked ? 1 : 0;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcVieModel.this.benzData.getJson());
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: \u8fd0\u52a8\u6a21\u5f0f\u5f00\u5173:" + BcVieModel.this.benzData.airMaticStatus);
        }
    };
    public final CompoundButton.OnCheckedChangeListener leftOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcVieModel.8
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.controlBean.leftBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), BcVieModel.this.benzData, BcVieModel.this);
            }
        }
    };
    public final CompoundButton.OnCheckedChangeListener rightOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcVieModel.9
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.controlBean.rightBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), BcVieModel.this.benzData, BcVieModel.this);
            }
        }
    };

    @Override // com.wits.ksw.launcher.model.LauncherViewModel
    public void initData() {
        super.initData();
        initControl();
        registerIContentObserver();
        switchToFirstView();
    }

    public void benzControlPanel() {
        try {
            int benzpane = PowerManagerApp.getSettingsInt(KeyConfig.BENZPANE);
            boolean z = true;
            this.controlBean.controlPanelClose.set(benzpane == 0);
            String str = TAG;
            StringBuilder append = new StringBuilder().append("benzControlPanel: ");
            if (benzpane != 0) {
                z = false;
            }
            Log.i(str, append.append(z).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToFirstView() {
        setScrollToPosition(0);
        setIsLeftArrowDisplay(false);
        setIsRightArrowDisplay(true);
    }

    public void switchToSecondView() {
        setScrollToPosition(9);
        setIsLeftArrowDisplay(true);
        setIsRightArrowDisplay(false);
    }

    public void setScrollToPosition(int postion) {
        setSmoothScrollToPosition(postion);
    }

    public void setSmoothScrollToPosition(int postion) {
        this.bcPagePosition.set(postion);
    }

    public void setIsLeftArrowDisplay(boolean display) {
        this.isLeftArrowDisplay.set(display);
    }

    public void setIsRightArrowDisplay(boolean display) {
        this.isRightArrowDisplay.set(display);
    }

    public void onMbuxHomeItemClick(final View view, final BenzMbuxBean item) {
        Log.i(TAG, "onItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages(null);
        this.uiHandler.postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.BcVieModel.1
            @Override // java.lang.Runnable
            public void run() {
                switch (item.getId()) {
                    case 0:
                        BcVieModel.this.openNaviApp();
                        return;
                    case 1:
                        BcVieModel.this.openMusicMulti(view);
                        return;
                    case 2:
                        BcVieModel.this.openBtApp();
                        return;
                    case 3:
                        BcVieModel.this.onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                        return;
                    case 4:
                        BcVieModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity");
                        return;
                    case 5:
                        BcVieModel.this.openVideoMulti(view);
                        return;
                    case 6:
                        BcVieModel.this.openApps(view);
                        return;
                    case 7:
                        boolean flag = Build.DISPLAY.contains("8937");
                        if (flag) {
                            int speed_play_switch = Settings.System.getInt(BcVieModel.this.context.getContentResolver(), "speed_play_switch", 1);
                            if (speed_play_switch == 2) {
                                BcVieModel bcVieModel = BcVieModel.this;
                                bcVieModel.openApp(bcVieModel.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                                return;
                            }
                            BcVieModel bcVieModel2 = BcVieModel.this;
                            bcVieModel2.openApp(bcVieModel2.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                            return;
                        }
                        BcVieModel bcVieModel3 = BcVieModel.this;
                        bcVieModel3.openApp(bcVieModel3.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                        return;
                    case 8:
                        BcVieModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity");
                        return;
                    case 9:
                        BcVieModel.this.openDvr(view);
                        return;
                    default:
                        return;
                }
            }
        }, 200L);
    }

    public void openPhoneLink2021(View view) {
        boolean flag = Build.DISPLAY.contains("8937");
        if (!flag) {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
            return;
        }
        int speed_play_switch = Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1);
        if (speed_play_switch == 2) {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
        } else {
            openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
        }
    }

    public void onMbux2021KswHomeItemClick(final View view, final BenzMbux2021KswBean item) {
        Log.i(TAG, "onMbux2021HomeItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages(null);
        this.uiHandler.postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.BcVieModel.2
            @Override // java.lang.Runnable
            public void run() {
                switch (item.getId()) {
                    case 0:
                        BcVieModel.this.openBtApp();
                        return;
                    case 1:
                        BcVieModel.this.openNaviApp();
                        return;
                    case 2:
                        BcVieModel.this.onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                        return;
                    case 3:
                        BcVieModel.this.onItemClick("com.wits.media.VIDEO");
                        return;
                    case 4:
                        BcVieModel.this.openChoseMusic(view);
                        return;
                    case 5:
                        BcVieModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity");
                        return;
                    case 6:
                        boolean flag = Build.DISPLAY.contains("8937");
                        if (flag) {
                            int speed_play_switch = Settings.System.getInt(BcVieModel.this.context.getContentResolver(), "speed_play_switch", 1);
                            if (speed_play_switch == 2) {
                                BcVieModel bcVieModel = BcVieModel.this;
                                bcVieModel.openApp(bcVieModel.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                                return;
                            }
                            BcVieModel bcVieModel2 = BcVieModel.this;
                            bcVieModel2.openApp(bcVieModel2.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                            return;
                        }
                        BcVieModel bcVieModel3 = BcVieModel.this;
                        bcVieModel3.openApp(bcVieModel3.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                        return;
                    case 7:
                        BcVieModel.this.openApps(view);
                        return;
                    case 8:
                        BcVieModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity");
                        return;
                    case 9:
                        BcVieModel.this.openDvr(view);
                        return;
                    default:
                        return;
                }
            }
        }, 200L);
    }

    public void onMbux2021HomeItemClick(final View view, final BenzMbux2021Bean item) {
        Log.i(TAG, "onMbux2021HomeItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages(null);
        this.uiHandler.postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.BcVieModel.3
            @Override // java.lang.Runnable
            public void run() {
                switch (item.getId()) {
                    case 0:
                        BcVieModel.this.openBtApp();
                        return;
                    case 1:
                        BcVieModel.this.openNaviApp();
                        return;
                    case 2:
                        BcVieModel.this.onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                        return;
                    case 3:
                        BcVieModel.this.onItemClick("com.wits.media.VIDEO");
                        return;
                    case 4:
                        BcVieModel.this.openChoseMusic(view);
                        return;
                    case 5:
                        BcVieModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity");
                        return;
                    case 6:
                        boolean flag = Build.DISPLAY.contains("8937");
                        if (flag) {
                            int speed_play_switch = Settings.System.getInt(BcVieModel.this.context.getContentResolver(), "speed_play_switch", 1);
                            if (speed_play_switch == 2) {
                                BcVieModel bcVieModel = BcVieModel.this;
                                bcVieModel.openApp(bcVieModel.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                                return;
                            }
                            BcVieModel bcVieModel2 = BcVieModel.this;
                            bcVieModel2.openApp(bcVieModel2.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                            return;
                        }
                        BcVieModel bcVieModel3 = BcVieModel.this;
                        bcVieModel3.openApp(bcVieModel3.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                        return;
                    case 7:
                        BcVieModel.this.openApps(view);
                        return;
                    case 8:
                        BcVieModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity");
                        return;
                    case 9:
                        BcVieModel.this.openDvr(view);
                        return;
                    default:
                        return;
                }
            }
        }, 200L);
    }

    public void onItemClick(final View view, final BcItem item) {
        Log.i(TAG, "onItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages(null);
        this.uiHandler.postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.BcVieModel.4
            @Override // java.lang.Runnable
            public void run() {
                if (ClientManager.getInstance().isAls6208Client() || ClientManager.getInstance().isYC2306Client()) {
                    BcVieModel.this.openAls6208(view, item.getId());
                } else {
                    BcVieModel.this.open(view, item.getId());
                }
            }
        }, 200L);
    }

    protected void open(View view, int id) {
        switch (id) {
            case 0:
                openNaviApp();
                return;
            case 1:
                openMusicMulti(view);
                return;
            case 2:
                openBtApp();
                return;
            case 3:
                onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                return;
            case 4:
                onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity");
                return;
            case 5:
                openVideoMulti(view);
                return;
            case 6:
                Intent esintent = this.context.getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop");
                openApp(esintent);
                return;
            case 7:
                boolean flag = Build.DISPLAY.contains("8937");
                if (!flag) {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                    return;
                }
                int speed_play_switch = Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1);
                if (speed_play_switch != 2) {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                    return;
                } else {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.suding.speedplay"));
                    return;
                }
            case 8:
                onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity");
                return;
            case 9:
                openDvr(view);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAls6208(View view, int id) {
        switch (id) {
            case 0:
                openNaviApp();
                return;
            case 1:
                openVideoMulti(view);
                return;
            case 2:
                openMusicMulti(view);
                return;
            case 3:
                openBtApp();
                return;
            case 4:
                onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                return;
            case 5:
                onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity");
                return;
            case 6:
                boolean flag = Build.DISPLAY.contains("8937");
                if (!flag) {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                    return;
                }
                int speed_play_switch = Settings.System.getInt(this.context.getContentResolver(), "speed_play_switch", 1);
                if (speed_play_switch != 2) {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                    return;
                } else {
                    openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.suding.speedplay"));
                    return;
                }
            case 7:
                Intent esintent = this.context.getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop");
                openApp(esintent);
                return;
            case 8:
                openDvr(view);
                return;
            case 9:
                onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity");
                return;
            default:
                return;
        }
    }

    @Override // com.wits.ksw.launcher.model.LauncherViewModel
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

    @Override // com.wits.ksw.launcher.model.LauncherViewModel
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
}
