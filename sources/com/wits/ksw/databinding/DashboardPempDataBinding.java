package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class DashboardPempDataBinding extends ViewDataBinding {
    public final ImageView divider;
    public final ImageView ivMask;
    public final FrameLayout ivSpeed;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mDashboardViewModel;
    public final TextView tvDashboard;

    public abstract void setDashboardViewModel(LauncherViewModel DashboardViewModel);

    protected DashboardPempDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView divider, ImageView ivMask, FrameLayout ivSpeed, RelativeLayout llContainer, TextView tvDashboard) {
        super(_bindingComponent, _root, _localFieldCount);
        this.divider = divider;
        this.ivMask = ivMask;
        this.ivSpeed = ivSpeed;
        this.llContainer = llContainer;
        this.tvDashboard = tvDashboard;
    }

    public LauncherViewModel getDashboardViewModel() {
        return this.mDashboardViewModel;
    }

    public static DashboardPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DashboardPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_dashboard, root, attachToRoot, component);
    }

    public static DashboardPempDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardPempDataBinding inflate(LayoutInflater inflater, Object component) {
        return (DashboardPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_dashboard, null, false, component);
    }

    public static DashboardPempDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardPempDataBinding bind(View view, Object component) {
        return (DashboardPempDataBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_dashboard);
    }
}