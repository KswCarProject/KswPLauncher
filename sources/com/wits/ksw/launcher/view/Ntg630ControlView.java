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
    private static final String TAG = ("KSWLauncher." + Ntg630ControlView.class.getSimpleName());
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
        Ntg630ControlPopupBinding ntg630ControlPopupBinding = (Ntg630ControlPopupBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ntg630_control_popup, (ViewGroup) null, false);
        this.binding = ntg630ControlPopupBinding;
        ntg630ControlPopupBinding.setMBcVieModel(bcVieModel);
        PopupWindow popupWindow2 = new PopupWindow(this.binding.getRoot(), UtilsInfo.dip2px(context, 175.0f), UtilsInfo.dip2px(context, 378.0f), true);
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
        Log.d(TAG, " showBenzBrightnessDailog light " + light);
        View layout = LayoutInflater.from(context).inflate(R.layout.ntg6_brightness_control_popup, (ViewGroup) null);
        View closeImage = layout.findViewById(R.id.closeImage);
        View addBrImageView = layout.findViewById(R.id.addBrImageView);
        View subBrImageView = layout.findViewById(R.id.subBrImageView);
        PopupWindow popupWindow2 = new PopupWindow(layout, UtilsInfo.dip2px(context, 321.0f), UtilsInfo.dip2px(context, 225.0f), true);
        this.brightnessPopupWindow = popupWindow2;
        popupWindow2.setOutsideTouchable(false);
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
        addBrImageView.setOnClickListener(new View.OnClickListener(light, benzData) {
            public final /* synthetic */ int f$0;
            public final /* synthetic */ McuStatus.BenzData f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                Ntg630ControlView.lambda$showBenzBrightnessDailog$0(this.f$0, this.f$1, view);
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
                Ntg630ControlView.lambda$showBenzBrightnessDailog$1(this.f$0, this.f$1, view);
            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Ntg630ControlView.this.lambda$showBenzBrightnessDailog$2$Ntg630ControlView(view);
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

    public /* synthetic */ void lambda$showBenzBrightnessDailog$2$Ntg630ControlView(View v) {
        this.brightnessPopupWindow.dismiss();
        this.brightnessPopupWindow = null;
    }
}
