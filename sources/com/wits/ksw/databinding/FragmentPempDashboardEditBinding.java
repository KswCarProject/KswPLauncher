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
public abstract class FragmentPempDashboardEditBinding extends ViewDataBinding {
    public final ImageView ivDivider;
    public final FrameLayout ivSpeed;
    public final RelativeLayout layout;
    @Bindable
    protected LauncherViewModel mDashboardViewModel;
    public final TextView title;

    public abstract void setDashboardViewModel(LauncherViewModel DashboardViewModel);

    protected FragmentPempDashboardEditBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivDivider, FrameLayout ivSpeed, RelativeLayout layout, TextView title) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivDivider = ivDivider;
        this.ivSpeed = ivSpeed;
        this.layout = layout;
        this.title = title;
    }

    public LauncherViewModel getDashboardViewModel() {
        return this.mDashboardViewModel;
    }

    public static FragmentPempDashboardEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPempDashboardEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPempDashboardEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_dashboard_edit, root, attachToRoot, component);
    }

    public static FragmentPempDashboardEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPempDashboardEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPempDashboardEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_dashboard_edit, null, false, component);
    }

    public static FragmentPempDashboardEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPempDashboardEditBinding bind(View view, Object component) {
        return (FragmentPempDashboardEditBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_dashboard_edit);
    }
}
