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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class AlsId7SubDashboardViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView brakeTextView;
    @NonNull
    public final ConstraintLayout dashboardConstraintLayout;
    @NonNull
    public final CustomBmwImageView dashboardImageView;
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;
    @NonNull
    public final TextView oilTextView;
    @NonNull
    public final ImageView pointerImageView;
    @NonNull
    public final TextView seatBeltTextView;
    @NonNull
    public final TextView speedTextView;
    @NonNull
    public final TextView title;

    public abstract void setDashVideoViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubDashboardViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView brakeTextView2, ConstraintLayout dashboardConstraintLayout2, CustomBmwImageView dashboardImageView2, TextView oilTextView2, ImageView pointerImageView2, TextView seatBeltTextView2, TextView speedTextView2, TextView title2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView2;
        this.dashboardConstraintLayout = dashboardConstraintLayout2;
        this.dashboardImageView = dashboardImageView2;
        this.oilTextView = oilTextView2;
        this.pointerImageView = pointerImageView2;
        this.seatBeltTextView = seatBeltTextView2;
        this.speedTextView = speedTextView2;
        this.title = title2;
    }

    @Nullable
    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    @NonNull
    public static AlsId7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AlsId7SubDashboardViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_dashboard_view, root, attachToRoot, component);
    }

    @NonNull
    public static AlsId7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubDashboardViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AlsId7SubDashboardViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_dashboard_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubDashboardViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AlsId7SubDashboardViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AlsId7SubDashboardViewBinding) bind(component, view, R.layout.als_id7_sub_dashboard_view);
    }
}
