package com.wits.ksw.launcher.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.databinding.BenzControlBind;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.launcher.view.ColorArcProgressBar;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.WitsCommand;

public class Ntg6ControlView {
    private static final String TAG = ("KSWLauncher." + Ntg6ControlView.class.getSimpleName());
    private static Ntg6ControlView instance;
    private BenzControlBind benzControlBind;
    private PopupWindow brightnessPopupWindow;
    private PopupWindow popupWindow;

    private Ntg6ControlView() {
    }

    public static synchronized Ntg6ControlView getInstance() {
        Ntg6ControlView ntg6ControlView;
        synchronized (Ntg6ControlView.class) {
            if (instance == null) {
                instance = new Ntg6ControlView();
            }
            ntg6ControlView = instance;
        }
        return ntg6ControlView;
    }

    public void showBenzControl(Context context, final BcVieModel bcVieModel, View view) {
        BenzControlBind benzControlBind2 = (BenzControlBind) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ntg6_control_popup, (ViewGroup) null, false);
        this.benzControlBind = benzControlBind2;
        benzControlBind2.setMBcVieModel(bcVieModel);
        int width = UtilsInfo.dip2px(context, 175.0f);
        int height = UtilsInfo.dip2px(context, 378.0f);
        Log.i(TAG, "showBenzControl: width:" + width + "\theight:" + height);
        PopupWindow popupWindow2 = new PopupWindow(this.benzControlBind.getRoot(), width, height, true);
        this.popupWindow = popupWindow2;
        popupWindow2.showAsDropDown(view, -view.getLeft(), -view.getTop());
        this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                bcVieModel.controlBean.benzControlPanelState.set(false);
            }
        });
        bcVieModel.controlBean.benzControlPanelState.set(true);
    }

    public void dismiss() {
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            popupWindow2.dismiss();
            this.popupWindow = null;
        }
    }

    public boolean isShowing() {
        PopupWindow popupWindow2 = this.popupWindow;
        return popupWindow2 != null && popupWindow2.isShowing();
    }

    public void showBenzBrightnessDailog(Context context, McuStatus.BenzData benzData, final BcVieModel bcVieModel, int light) {
        Log.d(TAG, " showBenzBrightnessDialog light " + light);
        View layout = LayoutInflater.from(context).inflate(R.layout.ntg6_brightness_control_popup, (ViewGroup) null);
        View closeImage = layout.findViewById(R.id.closeImage);
        View addBrImageView = layout.findViewById(R.id.addBrImageView);
        View subBrImageView = layout.findViewById(R.id.subBrImageView);
        PopupWindow popupWindow2 = new PopupWindow(layout, UtilsInfo.dip2px(context, 321.0f), UtilsInfo.dip2px(context, 225.0f), true);
        this.brightnessPopupWindow = popupWindow2;
        popupWindow2.setOutsideTouchable(false);
        this.brightnessPopupWindow.showAsDropDown(this.benzControlBind.controlBtn1, (this.benzControlBind.controlBtn1.getRight() / 2) + 20, this.benzControlBind.controlBtn1.getTop() - 20);
        this.brightnessPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                Log.i("", "onDismiss: " + bcVieModel.controlBean.leftBrightnessAdjus.get());
                if (bcVieModel.controlBean.leftBrightnessAdjus.get()) {
                    bcVieModel.controlBean.leftBrightnessAdjus.set(false);
                } else if (bcVieModel.controlBean.rightBrightnessAdjus.get()) {
                    bcVieModel.controlBean.rightBrightnessAdjus.set(false);
                }
            }
        });
        addBrImageView.setOnClickListener(new View.OnClickListener(light, benzData) {
            public final /* synthetic */ int f$0;
            public final /* synthetic */ McuStatus.BenzData f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                Ntg6ControlView.lambda$showBenzBrightnessDailog$0(this.f$0, this.f$1, view);
            }
        });
        subBrImageView.setOnClickListener(new View.OnClickListener(light, benzData) {
            public final /* synthetic */ int f$0;
            public final /* synthetic */ McuStatus.BenzData f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                Ntg6ControlView.lambda$showBenzBrightnessDailog$1(this.f$0, this.f$1, view);
            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Ntg6ControlView.this.lambda$showBenzBrightnessDailog$2$Ntg6ControlView(view);
            }
        });
    }

    static /* synthetic */ void lambda$showBenzBrightnessDailog$0(int light, McuStatus.BenzData benzData, View v) {
        if (light == 1) {
            benzData.light1 = 1;
            benzData.light2 = 0;
        } else {
            benzData.light1 = 0;
            benzData.light2 = 1;
        }
        benzData.key3 = 0;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
    }

    static /* synthetic */ void lambda$showBenzBrightnessDailog$1(int light, McuStatus.BenzData benzData, View v) {
        if (light == 1) {
            benzData.light1 = 255;
            benzData.light2 = 0;
        } else {
            benzData.light1 = 0;
            benzData.light2 = 255;
        }
        benzData.key3 = 0;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
    }

    public /* synthetic */ void lambda$showBenzBrightnessDailog$2$Ntg6ControlView(View v) {
        this.brightnessPopupWindow.dismiss();
        this.brightnessPopupWindow = null;
    }

    public void showBenzBrightnessControl(Context context, final McuStatus.BenzData benzData, final BcVieModel bcVieModel) {
        View layout = LayoutInflater.from(context).inflate(R.layout.ntg6_control_brightness_popup, (ViewGroup) null);
        final TextView brightnessTextView = (TextView) layout.findViewById(R.id.brightness);
        ColorArcProgressBar progressBar = (ColorArcProgressBar) layout.findViewById(R.id.brightnessSeekBar);
        View closeControl = layout.findViewById(R.id.closeControl);
        if (bcVieModel.controlBean.leftBrightnessAdjus.get()) {
            brightnessTextView.setText("" + benzData.light1);
            bcVieModel.controlBean.brightness.set(benzData.light1);
            progressBar.setProgress((float) benzData.light1);
            Log.i(KswApplication.TAG, "showBenzBrightnessControl: 左仪表亮度值=" + benzData.light1);
        } else if (bcVieModel.controlBean.rightBrightnessAdjus.get()) {
            brightnessTextView.setText("" + benzData.light2);
            bcVieModel.controlBean.brightness.set(benzData.light2);
            progressBar.setProgress((float) benzData.light2);
            Log.i(KswApplication.TAG, "showBenzBrightnessControl:  右仪表亮度值=" + benzData.light2);
        }
        this.benzControlBind.setMBcVieModel(bcVieModel);
        int w = 214;
        int h = 214;
        int xoff = 120;
        if (ScreenUtil.getInstance().is1920X720(context)) {
            h = 321;
            w = 321;
            xoff = 180;
        }
        PopupWindow popupWindow2 = new PopupWindow(layout, w, h, true);
        this.brightnessPopupWindow = popupWindow2;
        popupWindow2.setOutsideTouchable(false);
        this.brightnessPopupWindow.showAsDropDown(this.benzControlBind.controlBtn1, xoff, 0, 48);
        this.brightnessPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                Log.i("", "onDismiss: " + bcVieModel.controlBean.leftBrightnessAdjus.get());
                if (bcVieModel.controlBean.leftBrightnessAdjus.get()) {
                    bcVieModel.controlBean.leftBrightnessAdjus.set(false);
                } else if (bcVieModel.controlBean.rightBrightnessAdjus.get()) {
                    bcVieModel.controlBean.rightBrightnessAdjus.set(false);
                }
            }
        });
        progressBar.setOnSeekArcChangeListener(new ColorArcProgressBar.OnSeekArcChangeListener() {
            public void onProgressChanged(ColorArcProgressBar seekArc, int progress, boolean fromUser) {
                Log.i(KswApplication.TAG, "onProgressChanged: progress= " + progress);
                bcVieModel.controlBean.brightness.set(progress);
                brightnessTextView.setText("" + progress);
            }

            public void onStartTrackingTouch(ColorArcProgressBar seekArc) {
            }

            public void onStopTrackingTouch(ColorArcProgressBar seekArc) {
                int progress = seekArc.getPressed();
                if (bcVieModel.controlBean.leftBrightnessAdjus.get()) {
                    benzData.light1 = progress;
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
                    Log.i(KswApplication.TAG, "onStopTrackingTouch: 写左仪表亮度值=" + progress);
                } else if (bcVieModel.controlBean.rightBrightnessAdjus.get()) {
                    benzData.light2 = progress;
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
                    Log.i(KswApplication.TAG, "onStopTrackingTouch: 写右仪表亮度值=" + progress);
                }
                brightnessTextView.setText("" + progress);
            }
        });
        closeControl.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Ntg6ControlView.this.lambda$showBenzBrightnessControl$3$Ntg6ControlView(view);
            }
        });
    }

    public /* synthetic */ void lambda$showBenzBrightnessControl$3$Ntg6ControlView(View v) {
        this.brightnessPopupWindow.dismiss();
        this.brightnessPopupWindow = null;
    }
}
