package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public abstract class ActivityDashBoardAudiMib3FyBinding extends ViewDataBinding {
    public final ImageView brakeTextView;
    public final ImageView carImageView;
    public final TextClock clockTime;
    public final RadioButton comfortableRb;
    public final ImageView dorrBackImageView;
    public final ImageView dorrLeftFlImageView;
    public final ImageView dorrLeftFrImageView;
    public final ImageView dorrLeftRlImageView;
    public final RadioButton energyConservationRb;
    public final ImageView imageView19;
    public final RelativeLayout leftModeRl;
    public final ConstraintLayout linearLayout2;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final RadioGroup modeRg;
    public final RadioButton motionRb;
    public final ImageView seatBeltTextView;
    public final ImageView showModeIv;
    public final ID7SpeedImageView speedPointerImageView;
    public final TextView speedPointerTextView;
    public final ID7SpeedImageView speedometerImageView;
    public final ImageView tachometerImageView;
    public final TextView temperatureTextView;
    public final ImageView zspeedPointerImageView;
    public final TextView zspeedPointerTextView;

    public abstract void setViewModel(DashboardViewModel dashboardViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityDashBoardAudiMib3FyBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView brakeTextView2, ImageView carImageView2, TextClock clockTime2, RadioButton comfortableRb2, ImageView dorrBackImageView2, ImageView dorrLeftFlImageView2, ImageView dorrLeftFrImageView2, ImageView dorrLeftRlImageView2, RadioButton energyConservationRb2, ImageView imageView192, RelativeLayout leftModeRl2, ConstraintLayout linearLayout22, RadioGroup modeRg2, RadioButton motionRb2, ImageView seatBeltTextView2, ImageView showModeIv2, ID7SpeedImageView speedPointerImageView2, TextView speedPointerTextView2, ID7SpeedImageView speedometerImageView2, ImageView tachometerImageView2, TextView temperatureTextView2, ImageView zspeedPointerImageView2, TextView zspeedPointerTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView2;
        this.carImageView = carImageView2;
        this.clockTime = clockTime2;
        this.comfortableRb = comfortableRb2;
        this.dorrBackImageView = dorrBackImageView2;
        this.dorrLeftFlImageView = dorrLeftFlImageView2;
        this.dorrLeftFrImageView = dorrLeftFrImageView2;
        this.dorrLeftRlImageView = dorrLeftRlImageView2;
        this.energyConservationRb = energyConservationRb2;
        this.imageView19 = imageView192;
        this.leftModeRl = leftModeRl2;
        this.linearLayout2 = linearLayout22;
        this.modeRg = modeRg2;
        this.motionRb = motionRb2;
        this.seatBeltTextView = seatBeltTextView2;
        this.showModeIv = showModeIv2;
        this.speedPointerImageView = speedPointerImageView2;
        this.speedPointerTextView = speedPointerTextView2;
        this.speedometerImageView = speedometerImageView2;
        this.tachometerImageView = tachometerImageView2;
        this.temperatureTextView = temperatureTextView2;
        this.zspeedPointerImageView = zspeedPointerImageView2;
        this.zspeedPointerTextView = zspeedPointerTextView2;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDashBoardAudiMib3FyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_dash_board_audi_mib3_fy, root, attachToRoot, component);
    }

    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDashBoardAudiMib3FyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_dash_board_audi_mib3_fy, (ViewGroup) null, false, component);
    }

    public static ActivityDashBoardAudiMib3FyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiMib3FyBinding bind(View view, Object component) {
        return (ActivityDashBoardAudiMib3FyBinding) bind(component, view, R.layout.activity_dash_board_audi_mib3_fy);
    }
}
