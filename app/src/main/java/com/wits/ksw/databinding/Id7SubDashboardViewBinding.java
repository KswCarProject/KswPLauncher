package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class Id7SubDashboardViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView brakeTextView;
    @NonNull
    public final ConstraintLayout dashboardConstraintLayout;
    @NonNull
    public final CustomBmwImageView dashboardImageView;
    @NonNull
    public final ImageView imageView5;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    @NonNull
    public final TextView oilTextView;
    @NonNull
    public final ImageView pointerImageView;
    @NonNull
    public final TextView rpmTextView;
    @NonNull
    public final TextView seatBeltTextView;
    @NonNull
    public final TextView speedTextView;
    @NonNull
    public final TextView tempTextView;
    @NonNull
    public final TextView textView2;

    public abstract void setCarViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected Id7SubDashboardViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView brakeTextView2, ConstraintLayout dashboardConstraintLayout2, CustomBmwImageView dashboardImageView2, ImageView imageView52, TextView oilTextView2, ImageView pointerImageView2, TextView rpmTextView2, TextView seatBeltTextView2, TextView speedTextView2, TextView tempTextView2, TextView textView22) {
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

    @Nullable
    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    @NonNull
    public static Id7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Id7SubDashboardViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_dashboard_view, root, attachToRoot, component);
    }

    @NonNull
    public static Id7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Id7SubDashboardViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_dashboard_view, (ViewGroup) null, false, component);
    }

    public static Id7SubDashboardViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Id7SubDashboardViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Id7SubDashboardViewBinding) bind(component, view, R.layout.id7_sub_dashboard_view);
    }
}
