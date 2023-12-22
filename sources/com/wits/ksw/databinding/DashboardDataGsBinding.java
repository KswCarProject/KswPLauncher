package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class DashboardDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mDashboardViewModel;

    public abstract void setDashboardViewModel(LauncherViewModel DashboardViewModel);

    protected DashboardDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RelativeLayout llContainerGs) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.llContainerGs = llContainerGs;
    }

    public LauncherViewModel getDashboardViewModel() {
        return this.mDashboardViewModel;
    }

    public static DashboardDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DashboardDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_dashboard_gs, root, attachToRoot, component);
    }

    public static DashboardDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (DashboardDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_dashboard_gs, null, false, component);
    }

    public static DashboardDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardDataGsBinding bind(View view, Object component) {
        return (DashboardDataGsBinding) bind(component, view, C0899R.C0902layout.fragment_dashboard_gs);
    }
}
