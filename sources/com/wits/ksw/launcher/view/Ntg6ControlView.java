package com.wits.ksw.launcher.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.databinding.BenzControlBind;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.ScreenUtil;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import com.wits.ksw.launcher.view.ColorArcProgressBar;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.WitsCommand;

/* loaded from: classes16.dex */
public class Ntg6ControlView {
    private static final String TAG = "KswApplication." + Ntg6ControlView.class.getSimpleName();
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

    public void showBenzControl(Context context, final LauncherViewModel launcherViewModel, View view) {
        int width = UtilsInfo.dip2px(context, 175.0f);
        int height = UtilsInfo.dip2px(context, 378.0f);
        Log.i(TAG, "showBenzControl: width:" + width + "\theight:" + height);
        BenzControlBind benzControlBind = (BenzControlBind) DataBindingUtil.inflate(LayoutInflater.from(context), C0899R.C0902layout.ntg6_control_popup, null, false);
        this.benzControlBind = benzControlBind;
        benzControlBind.setLauncherViewModel(launcherViewModel);
        this.popupWindow = new PopupWindow(this.benzControlBind.getRoot(), width, height, true);
        if (UiThemeUtils.isUI_KSW_ID7(context) || UiThemeUtils.isUI_KSW_MBUX_1024(context)) {
            this.popupWindow.showAsDropDown(view, -view.getLeft(), (-view.getTop()) - view.getHeight());
        } else {
            this.popupWindow.showAsDropDown(view, -view.getLeft(), -view.getTop());
        }
        this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.wits.ksw.launcher.view.Ntg6ControlView.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                launcherViewModel.controlBean.benzControlPanelState.set(false);
            }
        });
        launcherViewModel.controlBean.benzControlPanelState.set(true);
    }

    public void dismiss() {
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.popupWindow = null;
        }
    }

    public boolean isShowing() {
        PopupWindow popupWindow = this.popupWindow;
        return popupWindow != null && popupWindow.isShowing();
    }

    public void showBenzBrightnessDailog(Context context, final McuStatus.BenzData benzData, final LauncherViewModel launcherViewModel, final int light) {
        Log.d(TAG, " showBenzBrightnessDialog light " + light);
        View layout = LayoutInflater.from(context).inflate(C0899R.C0902layout.ntg6_brightness_control_popup, (ViewGroup) null);
        View closeImage = layout.findViewById(C0899R.C0901id.closeImage);
        View addBrImageView = layout.findViewById(C0899R.C0901id.addBrImageView);
        View subBrImageView = layout.findViewById(C0899R.C0901id.subBrImageView);
        int width = UtilsInfo.dip2px(context, 321.0f);
        int height = UtilsInfo.dip2px(context, 225.0f);
        PopupWindow popupWindow = new PopupWindow(layout, width, height, true);
        this.brightnessPopupWindow = popupWindow;
        popupWindow.setOutsideTouchable(false);
        this.brightnessPopupWindow.showAsDropDown(this.benzControlBind.controlBtn1, (this.benzControlBind.controlBtn1.getRight() / 2) + 20, this.benzControlBind.controlBtn1.getTop() - 20);
        this.brightnessPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.wits.ksw.launcher.view.Ntg6ControlView.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                Log.i("", "onDismiss: " + launcherViewModel.controlBean.leftBrightnessAdjus.get());
                if (launcherViewModel.controlBean.leftBrightnessAdjus.get()) {
                    launcherViewModel.controlBean.leftBrightnessAdjus.set(false);
                } else if (launcherViewModel.controlBean.rightBrightnessAdjus.get()) {
                    launcherViewModel.controlBean.rightBrightnessAdjus.set(false);
                }
            }
        });
        addBrImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.-$$Lambda$Ntg6ControlView$zdJK3OYNB_RPETboVM12Ip0EZFY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Ntg6ControlView.lambda$showBenzBrightnessDailog$0(light, benzData, view);
            }
        });
        subBrImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.-$$Lambda$Ntg6ControlView$RDVHTpLwI28uGmaqk-KJ_SzvaAw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Ntg6ControlView.lambda$showBenzBrightnessDailog$1(light, benzData, view);
            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.-$$Lambda$Ntg6ControlView$zraC_z4-SfuYCZEBwF4Y0BbFdZg
            @Override // android.view.View.OnClickListener
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

    public void showBenzBrightnessControl(Context context, final McuStatus.BenzData benzData, final LauncherViewModel launcherViewModel) {
        View layout = LayoutInflater.from(context).inflate(C0899R.C0902layout.ntg6_control_brightness_popup, (ViewGroup) null);
        final TextView brightnessTextView = (TextView) layout.findViewById(C0899R.C0901id.brightness);
        ColorArcProgressBar progressBar = (ColorArcProgressBar) layout.findViewById(C0899R.C0901id.brightnessSeekBar);
        View closeControl = layout.findViewById(C0899R.C0901id.closeControl);
        if (launcherViewModel.controlBean.leftBrightnessAdjus.get()) {
            brightnessTextView.setText("" + benzData.light1);
            launcherViewModel.controlBean.brightness.set(benzData.light1);
            int progress = benzData.light1;
            progressBar.setProgress(progress);
            Log.i(KswApplication.TAG, "showBenzBrightnessControl: \u5de6\u4eea\u8868\u4eae\u5ea6\u503c=" + benzData.light1);
        } else if (launcherViewModel.controlBean.rightBrightnessAdjus.get()) {
            brightnessTextView.setText("" + benzData.light2);
            launcherViewModel.controlBean.brightness.set(benzData.light2);
            int progress2 = benzData.light2;
            progressBar.setProgress(progress2);
            Log.i(KswApplication.TAG, "showBenzBrightnessControl:  \u53f3\u4eea\u8868\u4eae\u5ea6\u503c=" + benzData.light2);
        }
        this.benzControlBind.setLauncherViewModel(launcherViewModel);
        int w = 214;
        int h = 214;
        int xoff = 120;
        boolean is1920X720 = ScreenUtil.getInstance().is1920X720(context);
        if (is1920X720) {
            h = 321;
            w = 321;
            xoff = 180;
        }
        PopupWindow popupWindow = new PopupWindow(layout, w, h, true);
        this.brightnessPopupWindow = popupWindow;
        popupWindow.setOutsideTouchable(false);
        this.brightnessPopupWindow.showAsDropDown(this.benzControlBind.controlBtn1, xoff, 0, 48);
        this.brightnessPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.wits.ksw.launcher.view.Ntg6ControlView.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                Log.i("", "onDismiss: " + launcherViewModel.controlBean.leftBrightnessAdjus.get());
                if (launcherViewModel.controlBean.leftBrightnessAdjus.get()) {
                    launcherViewModel.controlBean.leftBrightnessAdjus.set(false);
                } else if (launcherViewModel.controlBean.rightBrightnessAdjus.get()) {
                    launcherViewModel.controlBean.rightBrightnessAdjus.set(false);
                }
            }
        });
        progressBar.setOnSeekArcChangeListener(new ColorArcProgressBar.OnSeekArcChangeListener() { // from class: com.wits.ksw.launcher.view.Ntg6ControlView.4
            @Override // com.wits.ksw.launcher.view.ColorArcProgressBar.OnSeekArcChangeListener
            public void onProgressChanged(ColorArcProgressBar seekArc, int progress3, boolean fromUser) {
                Log.i(KswApplication.TAG, "onProgressChanged: progress= " + progress3);
                launcherViewModel.controlBean.brightness.set(progress3);
                brightnessTextView.setText("" + progress3);
            }

            @Override // com.wits.ksw.launcher.view.ColorArcProgressBar.OnSeekArcChangeListener
            public void onStartTrackingTouch(ColorArcProgressBar seekArc) {
            }

            @Override // com.wits.ksw.launcher.view.ColorArcProgressBar.OnSeekArcChangeListener
            public void onStopTrackingTouch(ColorArcProgressBar seekArc) {
                int progress3 = seekArc.getPressed();
                if (launcherViewModel.controlBean.leftBrightnessAdjus.get()) {
                    benzData.light1 = progress3;
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
                    Log.i(KswApplication.TAG, "onStopTrackingTouch: \u5199\u5de6\u4eea\u8868\u4eae\u5ea6\u503c=" + progress3);
                } else if (launcherViewModel.controlBean.rightBrightnessAdjus.get()) {
                    benzData.light2 = progress3;
                    WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
                    Log.i(KswApplication.TAG, "onStopTrackingTouch: \u5199\u53f3\u4eea\u8868\u4eae\u5ea6\u503c=" + progress3);
                }
                brightnessTextView.setText("" + progress3);
            }
        });
        closeControl.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.-$$Lambda$Ntg6ControlView$JkKgy0X-GSAMG_MvBlV0TTlLlb8
            @Override // android.view.View.OnClickListener
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
