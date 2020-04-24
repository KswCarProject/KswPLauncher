package com.wits.ksw.launcher.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.wits.ksw.R;
import com.wits.ksw.databinding.Ntg630ControlPopupBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.statuscontrol.McuStatus;
import com.wits.pms.statuscontrol.WitsCommand;

public class Ntg630ControlView {
    private static Ntg630ControlView instance;
    private Ntg630ControlPopupBinding binding;
    private PopupWindow brightnessPopupWindow;
    private PopupWindow popupWindow;

    private Ntg630ControlView() {
    }

    public static synchronized Ntg630ControlView getInstance() {
        Ntg630ControlView ntg630ControlView;
        synchronized (Ntg630ControlView.class) {
            if (instance == null) {
                instance = new Ntg630ControlView();
            }
            ntg630ControlView = instance;
        }
        return ntg630ControlView;
    }

    public void showBenzControl(Context context, final BcVieModel bcVieModel, View view) {
        this.binding = (Ntg630ControlPopupBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ntg630_control_popup, (ViewGroup) null, false);
        this.binding.setMBcVieModel(bcVieModel);
        this.popupWindow = new PopupWindow(this.binding.getRoot(), UtilsInfo.dip2px(context, 175.0f), UtilsInfo.dip2px(context, 378.0f), true);
        this.popupWindow.showAsDropDown(view, -view.getLeft(), -view.getTop());
        this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                bcVieModel.controlBean.benzControlPanelState.set(false);
            }
        });
        bcVieModel.controlBean.benzControlPanelState.set(true);
    }

    public void dismiss() {
        if (this.popupWindow != null) {
            this.popupWindow.dismiss();
            this.popupWindow = null;
        }
    }

    public boolean isShowing() {
        return this.popupWindow != null && this.popupWindow.isShowing();
    }

    public void showBenzBrightnessDailog(Context context, McuStatus.BenzData benzData, final BcVieModel bcVieModel) {
        View layout = LayoutInflater.from(context).inflate(R.layout.ntg6_brightness_control_popup, (ViewGroup) null);
        View closeImage = layout.findViewById(R.id.closeImage);
        View addBrImageView = layout.findViewById(R.id.addBrImageView);
        View subBrImageView = layout.findViewById(R.id.subBrImageView);
        this.brightnessPopupWindow = new PopupWindow(layout, UtilsInfo.dip2px(context, 321.0f), UtilsInfo.dip2px(context, 225.0f), true);
        this.brightnessPopupWindow.setOutsideTouchable(false);
        this.brightnessPopupWindow.showAsDropDown(this.binding.controlBtn1, (this.binding.controlBtn1.getRight() / 2) + 20, this.binding.controlBtn1.getTop() - 20);
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
        addBrImageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Ntg630ControlView.lambda$showBenzBrightnessDailog$0(McuStatus.BenzData.this, view);
            }
        });
        subBrImageView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Ntg630ControlView.lambda$showBenzBrightnessDailog$1(McuStatus.BenzData.this, view);
            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Ntg630ControlView.lambda$showBenzBrightnessDailog$2(Ntg630ControlView.this, view);
            }
        });
    }

    static /* synthetic */ void lambda$showBenzBrightnessDailog$0(McuStatus.BenzData benzData, View v) {
        benzData.light1 = 1;
        benzData.key3 = 0;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
    }

    static /* synthetic */ void lambda$showBenzBrightnessDailog$1(McuStatus.BenzData benzData, View v) {
        benzData.light1 = 255;
        benzData.key3 = 0;
        WitsCommand.sendCommand(1, WitsCommand.SystemCommand.BENZ_CONTROL, benzData.getJson());
    }

    public static /* synthetic */ void lambda$showBenzBrightnessDailog$2(Ntg630ControlView ntg630ControlView, View v) {
        ntg630ControlView.brightnessPopupWindow.dismiss();
        ntg630ControlView.brightnessPopupWindow = null;
    }
}
