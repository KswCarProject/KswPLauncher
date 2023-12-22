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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected SevenDasoardBind(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout alsMenu, LinearLayout alsRadioGroup, ID7SpeedImageView batteryImageView, ImageView carImageView, ImageView dorrBackImageView, ImageView dorrLeftFlImageView, ImageView dorrLeftFrImageView, ImageView dorrLeftRlImageView, ImageView imageView19, ConstraintLayout linearLayout2, ID7SpeedImageView oilImageView, TextView speedPointerTextView, ID7SpeedImageView speedometerImageView, ID7SpeedImageView tachometerImageView, TextView textView18, TextClock time, ImageView zspeedPointerImageView, TextView zspeedPointerTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsMenu = alsMenu;
        this.alsRadioGroup = alsRadioGroup;
        this.batteryImageView = batteryImageView;
        this.carImageView = carImageView;
        this.dorrBackImageView = dorrBackImageView;
        this.dorrLeftFlImageView = dorrLeftFlImageView;
        this.dorrLeftFrImageView = dorrLeftFrImageView;
        this.dorrLeftRlImageView = dorrLeftRlImageView;
        this.imageView19 = imageView19;
        this.linearLayout2 = linearLayout2;
        this.oilImageView = oilImageView;
        this.speedPointerTextView = speedPointerTextView;
        this.speedometerImageView = speedometerImageView;
        this.tachometerImageView = tachometerImageView;
        this.textView18 = textView18;
        this.time = time;
        this.zspeedPointerImageView = zspeedPointerImageView;
        this.zspeedPointerTextView = zspeedPointerTextView;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static SevenDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SevenDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (SevenDasoardBind) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_seven, root, attachToRoot, component);
    }

    public static SevenDasoardBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SevenDasoardBind inflate(LayoutInflater inflater, Object component) {
        return (SevenDasoardBind) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_seven, null, false, component);
    }

    public static SevenDasoardBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SevenDasoardBind bind(View view, Object component) {
        return (SevenDasoardBind) bind(component, view, C0899R.C0902layout.activity_dash_board_seven);
    }
}
