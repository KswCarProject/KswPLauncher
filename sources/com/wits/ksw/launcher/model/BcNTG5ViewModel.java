package com.wits.ksw.launcher.model;

import android.content.Intent;
import android.databinding.ObservableBoolean;
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
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.view.Ntg630ControlView;
import com.wits.ksw.launcher.view.Ntg6ControlView;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import com.wits.ksw.launcher.view.p006ug.WiewFocusUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;

/* loaded from: classes9.dex */
public class BcNTG5ViewModel extends BcVieModel {
    public ObservableInt bcPagePosition = new ObservableInt();
    public ObservableBoolean isLeftArrowDisplay = new ObservableBoolean();
    public ObservableBoolean isRightArrowDisplay = new ObservableBoolean();
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    public final CompoundButton.OnCheckedChangeListener chassisOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcNTG5ViewModel.this.benzData.highChassisSwitch = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcNTG5ViewModel.this.benzData.getJson());
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: \u5e95\u76d8\u5347\u964d\u5f00\u5173:" + BcNTG5ViewModel.this.benzData.auxiliaryRadar);
        }
    };
    public final CompoundButton.OnCheckedChangeListener radarOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.4
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcNTG5ViewModel.this.benzData.auxiliaryRadar = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcNTG5ViewModel.this.benzData.getJson());
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: \u8f85\u52a9\u96f7\u8fbe\u5f00\u5173:" + BcNTG5ViewModel.this.benzData.auxiliaryRadar);
        }
    };
    public final CompoundButton.OnCheckedChangeListener sportOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.5
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcNTG5ViewModel.this.benzData.airMaticStatus = isChecked ? 1 : 0;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcNTG5ViewModel.this.benzData.getJson());
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: \u8fd0\u52a8\u6a21\u5f0f\u5f00\u5173:" + BcNTG5ViewModel.this.benzData.airMaticStatus);
        }
    };
    public final CompoundButton.OnCheckedChangeListener leftOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.6
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcNTG5ViewModel.this.controlBean.leftBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), BcNTG5ViewModel.this.benzData, BcNTG5ViewModel.this);
            }
        }
    };
    public final CompoundButton.OnCheckedChangeListener rightOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.7
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(LauncherViewModel.TAG, "onCheckedChanged: " + isChecked);
            BcNTG5ViewModel.this.controlBean.rightBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), BcNTG5ViewModel.this.benzData, BcNTG5ViewModel.this);
            }
        }
    };

    @Override // com.wits.ksw.launcher.model.BcVieModel, com.wits.ksw.launcher.model.LauncherViewModel
    public void initData() {
        super.initData();
        initControl();
        registerIContentObserver();
        switchToFirstView();
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
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

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void switchToFirstView() {
        setScrollToPosition(0);
        setIsLeftArrowDisplay(false);
        setIsRightArrowDisplay(true);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void switchToSecondView() {
        setScrollToPosition(9);
        setIsLeftArrowDisplay(true);
        setIsRightArrowDisplay(false);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void setScrollToPosition(int postion) {
        setSmoothScrollToPosition(postion);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void setSmoothScrollToPosition(int postion) {
        this.bcPagePosition.set(postion);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void setIsLeftArrowDisplay(boolean display) {
        this.isLeftArrowDisplay.set(display);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void setIsRightArrowDisplay(boolean display) {
        this.isRightArrowDisplay.set(display);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void onMbuxHomeItemClick(final View view, final BenzMbuxBean item) {
        Log.i(TAG, "onItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages(null);
        this.uiHandler.postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.1
            @Override // java.lang.Runnable
            public void run() {
                switch (item.getId()) {
                    case 0:
                        BcNTG5ViewModel.this.openNaviApp();
                        return;
                    case 1:
                        BcNTG5ViewModel.this.openChoseMusic(view);
                        return;
                    case 2:
                        BcNTG5ViewModel.this.openBtApp();
                        return;
                    case 3:
                        BcNTG5ViewModel.this.onSendCommand(1, WitsCommand.SystemCommand.CAR_MODE);
                        return;
                    case 4:
                        BcNTG5ViewModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.settings.SettingsActivity");
                        return;
                    case 5:
                        BcNTG5ViewModel.this.onItemClick("com.wits.media.VIDEO");
                        return;
                    case 6:
                        BcNTG5ViewModel.this.openApps(view);
                        return;
                    case 7:
                        boolean flag = Build.DISPLAY.contains("8937");
                        if (flag) {
                            int speed_play_switch = Settings.System.getInt(BcNTG5ViewModel.this.context.getContentResolver(), "speed_play_switch", 1);
                            if (speed_play_switch == 2) {
                                BcNTG5ViewModel bcNTG5ViewModel = BcNTG5ViewModel.this;
                                bcNTG5ViewModel.openApp(bcNTG5ViewModel.context.getPackageManager().getLaunchIntentForPackage("com.suding.speedplay"));
                                return;
                            }
                            BcNTG5ViewModel bcNTG5ViewModel2 = BcNTG5ViewModel.this;
                            bcNTG5ViewModel2.openApp(bcNTG5ViewModel2.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                            return;
                        }
                        BcNTG5ViewModel bcNTG5ViewModel3 = BcNTG5ViewModel.this;
                        bcNTG5ViewModel3.openApp(bcNTG5ViewModel3.context.getPackageManager().getLaunchIntentForPackage("com.zjinnova.zlink"));
                        return;
                    case 8:
                        BcNTG5ViewModel.this.onItemClick(BuildConfig.APPLICATION_ID, "com.wits.ksw.launcher.view.DashboardActivity");
                        return;
                    case 9:
                        BcNTG5ViewModel.this.openDvr(view);
                        return;
                    default:
                        return;
                }
            }
        }, 200L);
    }

    public void showLastViewFocus() {
        int resId = KswUtils.getLastViewId(this.context);
        Log.i(TAG, "showLastViewFocus: resId=" + resId);
        View view = this.activity.getWindow().getDecorView().findViewById(resId);
        WiewFocusUtils.setViewRequestFocus(view);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    public void onItemClick(final View view, final BcItem item) {
        Log.i(TAG, "onItemClick: " + item.getId() + " " + view.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages(null);
        this.uiHandler.postDelayed(new Runnable() { // from class: com.wits.ksw.launcher.model.BcNTG5ViewModel.2
            @Override // java.lang.Runnable
            public void run() {
                BcNTG5ViewModel.this.open(view, item.getId());
            }
        }, 200L);
    }

    @Override // com.wits.ksw.launcher.model.BcVieModel
    protected void open(View view, int id) {
        switch (id) {
            case 0:
                openNaviApp();
                return;
            case 1:
                openMusicMulti(null);
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
                openVideoMulti(null);
                return;
            case 6:
                openApps(view);
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

    private void openAls6208(View view, int id) {
        switch (id) {
            case 0:
                openNaviApp();
                return;
            case 1:
                onItemClick("com.wits.media.VIDEO");
                return;
            case 2:
                openChoseMusic(view);
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

    @Override // com.wits.ksw.launcher.model.BcVieModel, com.wits.ksw.launcher.model.LauncherViewModel
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

    @Override // com.wits.ksw.launcher.model.BcVieModel, com.wits.ksw.launcher.model.LauncherViewModel
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
