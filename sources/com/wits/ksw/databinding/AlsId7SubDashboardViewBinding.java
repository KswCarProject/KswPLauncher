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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public abstract class AlsId7SubDashboardViewBinding extends ViewDataBinding {
    public final TextView brakeTextView;
    public final ConstraintLayout dashboardConstraintLayout;
    public final CustomBmwImageView dashboardImageView;
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;
    public final TextView oilTextView;
    public final ImageView pointerImageView;
    public final TextView seatBeltTextView;
    public final TextView speedTextView;
    public final TextView title;

    public abstract void setDashVideoViewModel(AlsID7ViewModel DashVideoViewModel);

    protected AlsId7SubDashboardViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView brakeTextView, ConstraintLayout dashboardConstraintLayout, CustomBmwImageView dashboardImageView, TextView oilTextView, ImageView pointerImageView, TextView seatBeltTextView, TextView speedTextView, TextView title) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView;
        this.dashboardConstraintLayout = dashboardConstraintLayout;
        this.dashboardImageView = dashboardImageView;
        this.oilTextView = oilTextView;
        this.pointerImageView = pointerImageView;
        this.seatBeltTextView = seatBeltTextView;
        this.speedTextView = speedTextView;
        this.title = title;
    }

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_dashboard_view, root, attachToRoot, component);
    }

    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_dashboard_view, null, false, component);
    }

    public static AlsId7SubDashboardViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubDashboardViewBinding bind(View view, Object component) {
        return (AlsId7SubDashboardViewBinding) bind(component, view, C0899R.C0902layout.als_id7_sub_dashboard_view);
    }
}
