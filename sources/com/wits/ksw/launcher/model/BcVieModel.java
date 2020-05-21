package com.wits.ksw.launcher.model;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.BcItem;
import com.wits.ksw.launcher.utils.ClientManager;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.launcher.view.Ntg630ControlView;
import com.wits.ksw.launcher.view.Ntg6ControlView;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxBean;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.PowerManagerApp;
import com.wits.pms.statuscontrol.WitsCommand;

@BindingMethods({@BindingMethod(attribute = "scrollToPosition", method = "setScrollToPosition", type = LauncherViewModel.class), @BindingMethod(attribute = "smoothScrollToPosition", method = "setSmoothScrollToPosition", type = LauncherViewModel.class)})
public class BcVieModel extends LauncherViewModel {
    public ObservableInt bcPagePosition = new ObservableInt();
    /* access modifiers changed from: private */
    public McuStatus.BenzData benzData;
    public final CompoundButton.OnCheckedChangeListener chassisOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(KswApplication.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.benzData.highChassisSwitch = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcVieModel.this.benzData.getJson());
            Log.i(KswApplication.TAG, "onCheckedChanged: 底盘升降开关:" + BcVieModel.this.benzData.auxiliaryRadar);
        }
    };
    public ControlBean controlBean = new ControlBean(this.context);
    public ObservableBoolean isLeftArrowDisplay = new ObservableBoolean();
    public ObservableBoolean isRightArrowDisplay = new ObservableBoolean();
    public final CompoundButton.OnCheckedChangeListener leftOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(KswApplication.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.controlBean.leftBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), BcVieModel.this.benzData, BcVieModel.this);
            }
        }
    };
    public final CompoundButton.OnCheckedChangeListener radarOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(KswApplication.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.benzData.auxiliaryRadar = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcVieModel.this.benzData.getJson());
            Log.i(KswApplication.TAG, "onCheckedChanged: 辅助雷达开关:" + BcVieModel.this.benzData.auxiliaryRadar);
        }
    };
    public final CompoundButton.OnCheckedChangeListener rightOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(KswApplication.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.controlBean.rightBrightnessAdjus.set(isChecked);
            if (isChecked) {
                Ntg6ControlView.getInstance().showBenzBrightnessControl(buttonView.getContext(), BcVieModel.this.benzData, BcVieModel.this);
            }
        }
    };
    public final CompoundButton.OnCheckedChangeListener sportOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i(KswApplication.TAG, "onCheckedChanged: " + isChecked);
            BcVieModel.this.benzData.airMaticStatus = isChecked;
            WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, BcVieModel.this.benzData.getJson());
            Log.i(KswApplication.TAG, "onCheckedChanged: 运动模式开关:" + BcVieModel.this.benzData.airMaticStatus);
        }
    };
    private Handler uiHandler = new Handler(Looper.getMainLooper());

    public void initData() {
        super.initData();
        initControl();
        registerIContentObserver();
        switchToFirstView();
    }

    private void registerIContentObserver() {
        PowerManagerApp.registerIContentObserver("benzData", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                McuStatus.BenzData benzData = McuStatus.BenzData.getStatusFromJson(PowerManagerApp.getStatusString("benzData"));
                Log.i(KswApplication.TAG, "benzData onChange: " + benzData.getJson());
                BcVieModel.this.controlBean.chassis.set(benzData.highChassisSwitch);
                ObservableBoolean observableBoolean = BcVieModel.this.controlBean.sport;
                boolean z = true;
                if (benzData.airMaticStatus != 1) {
                    z = false;
                }
                observableBoolean.set(z);
                BcVieModel.this.controlBean.rdarAssistance.set(benzData.auxiliaryRadar);
                BcVieModel.this.controlBean.passairbar.set(benzData.airBagSystem);
            }
        });
    }

    private void initControl() {
        try {
            this.benzData = McuStatus.BenzData.getStatusFromJson(PowerManagerApp.getStatusString("benzData"));
            this.controlBean.chassis.set(this.benzData.highChassisSwitch);
            boolean z = false;
            this.controlBean.sport.set(this.benzData.airMaticStatus == 1);
            this.controlBean.rdarAssistance.set(this.benzData.auxiliaryRadar);
            this.controlBean.passairbar.set(this.benzData.airBagSystem);
            StringBuilder sb = new StringBuilder();
            sb.append("initData: 底盘开关：");
            sb.append(this.benzData.highChassisSwitch);
            sb.append(",运动模式：");
            if (this.benzData.airMaticStatus == 1) {
                z = true;
            }
            sb.append(z);
            sb.append(",雷达辅助开关：");
            sb.append(this.benzData.auxiliaryRadar);
            sb.append(" light1:");
            sb.append(this.benzData.light1);
            sb.append(" light2:");
            sb.append(this.benzData.light2);
            sb.append(" 安全气囊 ");
            sb.append(this.benzData.airBagSystem);
            Log.i("控制面板", sb.toString());
        } catch (Exception e) {
            this.benzData = new McuStatus.BenzData();
        }
    }

    public void benzControlPanel() {
        try {
            int benzpane = PowerManagerApp.getSettingsInt(KeyConfig.BENZPANE);
            boolean z = false;
            this.controlBean.controlPanelClose.set(benzpane == 0);
            StringBuilder sb = new StringBuilder();
            sb.append("benzControlPanel: ");
            if (benzpane == 0) {
                z = true;
            }
            sb.append(z);
            Log.i(KswApplication.TAG, sb.toString());
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
        Log.i(KswApplication.TAG, "onItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages((Object) null);
        this.uiHandler.postDelayed(new Runnable() {
            public void run() {
                switch (item.getId()) {
                    case 0:
                        BcVieModel.this.openNaviApp();
                        return;
                    case 1:
                        BcVieModel.this.openChoseMusic(view);
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
                        BcVieModel.this.onItemClick("com.wits.media.VIDEO");
                        return;
                    case 6:
                        BcVieModel.this.openApps(view);
                        return;
                    case 7:
                        BcVieModel.this.openApp(BcVieModel.this.context.getPackageManager().getLaunchIntentForPackage("net.easyconn"));
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
        }, 200);
    }

    public void onItemClick(final View view, final BcItem item) {
        Log.i(KswApplication.TAG, "onItemClick: " + item.getId());
        addLastViewFocused(view);
        refreshLastViewFocused();
        this.uiHandler.removeCallbacksAndMessages((Object) null);
        this.uiHandler.postDelayed(new Runnable() {
            public void run() {
                if (ClientManager.getInstance().isAls6208Client() || ClientManager.getInstance().isYC2306Client()) {
                    BcVieModel.this.openAls6208(view, item.getId());
                } else {
                    BcVieModel.this.open(view, item.getId());
                }
            }
        }, 200);
    }

    /* access modifiers changed from: protected */
    public void open(View view, int id) {
        switch (id) {
            case 0:
                openNaviApp();
                return;
            case 1:
                openChoseMusic(view);
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
                onItemClick("com.wits.media.VIDEO");
                return;
            case 6:
                openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop"));
                return;
            case 7:
                openApp(this.context.getPackageManager().getLaunchIntentForPackage("net.easyconn"));
                return;
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

    /* access modifiers changed from: private */
    public void openAls6208(View view, int id) {
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
                openApp(this.context.getPackageManager().getLaunchIntentForPackage("net.easyconn"));
                return;
            case 7:
                openApp(this.context.getPackageManager().getLaunchIntentForPackage("com.estrongs.android.pop"));
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

    public void onControlClick(View view) {
        int benzpane = KswUtils.getBenzpaneVersion();
        Log.i(KswApplication.TAG, "onControlClick: benzpane = " + benzpane);
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

    public void onHighChasssisClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(1);
        Log.i(KswApplication.TAG, "onCheckedChanged: 底盘升降开关:" + this.benzData.getJson());
    }

    public void onAuxiliaryRadarClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(3);
        Log.i(KswApplication.TAG, "onCheckedChanged: 辅助雷达开关:" + this.benzData.getJson());
    }

    public void onEspClick(View view) {
        this.benzData.key3 = 5;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, this.benzData.getJson());
        Log.d(KswApplication.TAG, "onEspClick: " + this.benzData.getJson());
    }

    public void onFoldLeftClick(View view) {
        this.benzData.key3 = 6;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, this.benzData.getJson());
        Log.d(KswApplication.TAG, "onFoldLeftClick: " + this.benzData.getJson());
    }

    public void onFoldRigtClick(View view) {
        this.benzData.key3 = 7;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, this.benzData.getJson());
        Log.d(KswApplication.TAG, "onFoldRigtClick: " + this.benzData.getJson());
    }

    public void onSportClick(View view) {
        this.benzData.key3 = 0;
        this.benzData.pressButton(2);
        Log.i("控制面板", "onCheckedChanged: 辅助雷达开关:" + this.benzData.getJson());
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
}
