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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

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

    public abstract void setDashVideoViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubDashboardViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView brakeTextView2, ConstraintLayout dashboardConstraintLayout2, CustomBmwImageView dashboardImageView2, TextView oilTextView2, ImageView pointerImageView2, TextView seatBeltTextView2, TextView speedTextView2, TextView title2) {
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

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_dashboard_view, root, attachToRoot, component);
    }

    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubDashboardViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubDashboardViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_dashboard_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubDashboardViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubDashboardViewBinding bind(View view, Object component) {
        return (AlsId7SubDashboardViewBinding) bind(component, view, R.layout.als_id7_sub_dashboard_view);
    }
}
