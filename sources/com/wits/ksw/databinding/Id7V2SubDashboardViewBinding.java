package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class Id7V2SubDashboardViewBinding extends ViewDataBinding {
    public final TextView brakeTextView;
    public final ConstraintLayout dashboardConstraintLayout;
    public final CustomBmwImageView dashboardImageView;
    public final ImageView imageView5;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    public final TextView oilTextView;
    public final ImageView pointerImageView;
    public final TextView rpmTextView;
    public final TextView seatBeltTextView;
    public final TextView speedTextView;
    public final TextView tempTextView;
    public final TextView textView2;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected Id7V2SubDashboardViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView brakeTextView2, ConstraintLayout dashboardConstraintLayout2, CustomBmwImageView dashboardImageView2, ImageView imageView52, TextView oilTextView2, ImageView pointerImageView2, TextView rpmTextView2, TextView seatBeltTextView2, TextView speedTextView2, TextView tempTextView2, TextView textView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView2;
        this.dashboardConstraintLayout = dashboardConstraintLayout2;
        this.dashboardImageView = dashboardImageView2;
        this.imageView5 = imageView52;
        this.oilTextView = oilTextView2;
        this.pointerImageView = pointerImageView2;
        this.rpmTextView = rpmTextView2;
        this.seatBeltTextView = seatBeltTextView2;
        this.speedTextView = speedTextView2;
        this.tempTextView = tempTextView2;
        this.textView2 = textView22;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7V2SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_sub_dashboard_view, root, attachToRoot, component);
    }

    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7V2SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_sub_dashboard_view, (ViewGroup) null, false, component);
    }

    public static Id7V2SubDashboardViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubDashboardViewBinding bind(View view, Object component) {
        return (Id7V2SubDashboardViewBinding) bind(component, view, R.layout.id7_v2_sub_dashboard_view);
    }
}
