package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public abstract class SevenDasoardBind extends ViewDataBinding {
    public final ConstraintLayout alsMenu;
    public final LinearLayout alsRadioGroup;
    public final ID7SpeedImageView batteryImageView;
    public final ImageView carImageView;
    public final ImageView dorrBackImageView;
    public final ImageView dorrLeftFlImageView;
    public final ImageView dorrLeftFrImageView;
    public final ImageView dorrLeftRlImageView;
    public final ImageView imageView19;
    public final ConstraintLayout linearLayout2;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final ID7SpeedImageView oilImageView;
    public final TextView speedPointerTextView;
    public final ID7SpeedImageView speedometerImageView;
    public final ID7SpeedImageView tachometerImageView;
    public final TextView textView18;
    public final TextClock time;
    public final ImageView zspeedPointerImageView;
    public final TextView zspeedPointerTextView;

    public abstract void setViewModel(DashboardViewModel dashboardViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SevenDasoardBind(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout alsMenu2, LinearLayout alsRadioGroup2, ID7SpeedImageView batteryImageView2, ImageView carImageView2, ImageView dorrBackImageView2, ImageView dorrLeftFlImageView2, ImageView dorrLeftFrImageView2, ImageView dorrLeftRlImageView2, ImageView imageView192, ConstraintLayout linearLayout22, ID7SpeedImageView oilImageView2, TextView speedPointerTextView2, ID7SpeedImageView speedometerImageView2, ID7SpeedImageView tachometerImageView2, TextView textView182, TextClock time2, ImageView zspeedPointerImageView2, TextView zspeedPointerTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsMenu = alsMenu2;
        this.alsRadioGroup = alsRadioGroup2;
        this.batteryImageView = batteryImageView2;
        this.carImageView = carImageView2;
        this.dorrBackImageView = dorrBackImageView2;
        this.dorrLeftFlImageView = dorrLeftFlImageView2;
        this.dorrLeftFrImageView = dorrLeftFrImageView2;
        this.dorrLeftRlImageView = dorrLeftRlImageView2;
        this.imageView19 = imageView192;
        this.linearLayout2 = linearLayout22;
        this.oilImageView = oilImageView2;
        this.speedPointerTextView = speedPointerTextView2;
        this.speedometerImageView = speedometerImageView2;
        this.tachometerImageView = tachometerImageView2;
        this.textView18 = textView182;
        this.time = time2;
        this.zspeedPointerImageView = zspeedPointerImageView2;
        this.zspeedPointerTextView = zspeedPointerTextView2;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static SevenDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SevenDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (SevenDasoardBind) ViewDataBinding.inflateInternal(inflater, R.layout.activity_dash_board_seven, root, attachToRoot, component);
    }

    public static SevenDasoardBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SevenDasoardBind inflate(LayoutInflater inflater, Object component) {
        return (SevenDasoardBind) ViewDataBinding.inflateInternal(inflater, R.layout.activity_dash_board_seven, (ViewGroup) null, false, component);
    }

    public static SevenDasoardBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SevenDasoardBind bind(View view, Object component) {
        return (SevenDasoardBind) bind(component, view, R.layout.activity_dash_board_seven);
    }
}
