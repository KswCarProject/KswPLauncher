package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class FragmentDashboardgsGsEditBinding extends ViewDataBinding {
    public final ImageView gsId8IconEditBg;
    @Bindable
    protected LauncherViewModel mDashboardGsViewModel;

    public abstract void setDashboardGsViewModel(LauncherViewModel launcherViewModel);

    protected FragmentDashboardgsGsEditBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gsId8IconEditBg2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsId8IconEditBg = gsId8IconEditBg2;
    }

    public LauncherViewModel getDashboardGsViewModel() {
        return this.mDashboardGsViewModel;
    }

    public static FragmentDashboardgsGsEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardgsGsEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDashboardgsGsEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_dashboardgs_edit, root, attachToRoot, component);
    }

    public static FragmentDashboardgsGsEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardgsGsEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDashboardgsGsEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_dashboardgs_edit, (ViewGroup) null, false, component);
    }

    public static FragmentDashboardgsGsEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardgsGsEditBinding bind(View view, Object component) {
        return (FragmentDashboardgsGsEditBinding) bind(component, view, R.layout.fragment_gs_dashboardgs_edit);
    }
}
