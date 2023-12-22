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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
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

    public abstract void setCarViewModel(LauncherViewModel CarViewModel);

    protected Id7V2SubDashboardViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView brakeTextView, ConstraintLayout dashboardConstraintLayout, CustomBmwImageView dashboardImageView, ImageView imageView5, TextView oilTextView, ImageView pointerImageView, TextView rpmTextView, TextView seatBeltTextView, TextView speedTextView, TextView tempTextView, TextView textView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView;
        this.dashboardConstraintLayout = dashboardConstraintLayout;
        this.dashboardImageView = dashboardImageView;
        this.imageView5 = imageView5;
        this.oilTextView = oilTextView;
        this.pointerImageView = pointerImageView;
        this.rpmTextView = rpmTextView;
        this.seatBeltTextView = seatBeltTextView;
        this.speedTextView = speedTextView;
        this.tempTextView = tempTextView;
        this.textView2 = textView2;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7V2SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_v2_sub_dashboard_view, root, attachToRoot, component);
    }

    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubDashboardViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7V2SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_v2_sub_dashboard_view, null, false, component);
    }

    public static Id7V2SubDashboardViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubDashboardViewBinding bind(View view, Object component) {
        return (Id7V2SubDashboardViewBinding) bind(component, view, C0899R.C0902layout.id7_v2_sub_dashboard_view);
    }
}
