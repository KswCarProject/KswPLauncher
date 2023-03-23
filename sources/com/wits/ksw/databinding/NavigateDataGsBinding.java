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

public abstract class NavigateDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mNavigateViewModel;
    public final TextView tvDesc;

    public abstract void setNavigateViewModel(LauncherViewModel launcherViewModel);

    protected NavigateDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RelativeLayout llContainerGs2, TextView tvDesc2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.llContainerGs = llContainerGs2;
        this.tvDesc = tvDesc2;
    }

    public LauncherViewModel getNavigateViewModel() {
        return this.mNavigateViewModel;
    }

    public static NavigateDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigateDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NavigateDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_navigate_gs, root, attachToRoot, component);
    }

    public static NavigateDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigateDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (NavigateDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_navigate_gs, (ViewGroup) null, false, component);
    }

    public static NavigateDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigateDataGsBinding bind(View view, Object component) {
        return (NavigateDataGsBinding) bind(component, view, R.layout.fragment_navigate_gs);
    }
}
