package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class FragmentDashboardgsEditBinding extends ViewDataBinding {
    public final ImageView ivDivider;
    public final RelativeLayout layout;
    @Bindable
    protected LauncherViewModel mDashboardGsViewModel;
    public final TextView title;

    public abstract void setDashboardGsViewModel(LauncherViewModel launcherViewModel);

    protected FragmentDashboardgsEditBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivDivider2, RelativeLayout layout2, TextView title2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivDivider = ivDivider2;
        this.layout = layout2;
        this.title = title2;
    }

    public LauncherViewModel getDashboardGsViewModel() {
        return this.mDashboardGsViewModel;
    }

    public static FragmentDashboardgsEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardgsEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDashboardgsEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_dashboardgs_edit, root, attachToRoot, component);
    }

    public static FragmentDashboardgsEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardgsEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDashboardgsEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_dashboardgs_edit, (ViewGroup) null, false, component);
    }

    public static FragmentDashboardgsEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardgsEditBinding bind(View view, Object component) {
        return (FragmentDashboardgsEditBinding) bind(component, view, R.layout.fragment_dashboardgs_edit);
    }
}
