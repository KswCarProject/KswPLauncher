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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected ActivityDashBoardAudiMib3FyBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView brakeTextView, ImageView carImageView, TextClock clockTime, RadioButton comfortableRb, ImageView dorrBackImageView, ImageView dorrLeftFlImageView, ImageView dorrLeftFrImageView, ImageView dorrLeftRlImageView, RadioButton energyConservationRb, ImageView imageView19, RelativeLayout leftModeRl, ConstraintLayout linearLayout2, RadioGroup modeRg, RadioButton motionRb, ImageView seatBeltTextView, ImageView showModeIv, ID7SpeedImageView speedPointerImageView, TextView speedPointerTextView, ID7SpeedImageView speedometerImageView, ImageView tachometerImageView, TextView temperatureTextView, ImageView zspeedPointerImageView, TextView zspeedPointerTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView;
        this.carImageView = carImageView;
        this.clockTime = clockTime;
        this.comfortableRb = comfortableRb;
        this.dorrBackImageView = dorrBackImageView;
        this.dorrLeftFlImageView = dorrLeftFlImageView;
        this.dorrLeftFrImageView = dorrLeftFrImageView;
        this.dorrLeftRlImageView = dorrLeftRlImageView;
        this.energyConservationRb = energyConservationRb;
        this.imageView19 = imageView19;
        this.leftModeRl = leftModeRl;
        this.linearLayout2 = linearLayout2;
        this.modeRg = modeRg;
        this.motionRb = motionRb;
        this.seatBeltTextView = seatBeltTextView;
        this.showModeIv = showModeIv;
        this.speedPointerImageView = speedPointerImageView;
        this.speedPointerTextView = speedPointerTextView;
        this.speedometerImageView = speedometerImageView;
        this.tachometerImageView = tachometerImageView;
        this.temperatureTextView = temperatureTextView;
        this.zspeedPointerImageView = zspeedPointerImageView;
        this.zspeedPointerTextView = zspeedPointerTextView;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDashBoardAudiMib3FyBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_audi_mib3_fy, root, attachToRoot, component);
    }

    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiMib3FyBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDashBoardAudiMib3FyBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_audi_mib3_fy, null, false, component);
    }

    public static ActivityDashBoardAudiMib3FyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiMib3FyBinding bind(View view, Object component) {
        return (ActivityDashBoardAudiMib3FyBinding) bind(component, view, C0899R.C0902layout.activity_dash_board_audi_mib3_fy);
    }
}
