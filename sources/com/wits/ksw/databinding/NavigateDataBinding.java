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

public abstract class NavigateDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mNavigateViewModel;
    public final TextView tvDesc;

    public abstract void setNavigateViewModel(LauncherViewModel launcherViewModel);

    protected NavigateDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RelativeLayout llContainer2, TextView tvDesc2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.llContainer = llContainer2;
        this.tvDesc = tvDesc2;
    }

    public LauncherViewModel getNavigateViewModel() {
        return this.mNavigateViewModel;
    }

    public static NavigateDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigateDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NavigateDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_navigate, root, attachToRoot, component);
    }

    public static NavigateDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigateDataBinding inflate(LayoutInflater inflater, Object component) {
        return (NavigateDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_navigate, (ViewGroup) null, false, component);
    }

    public static NavigateDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigateDataBinding bind(View view, Object component) {
        return (NavigateDataBinding) bind(component, view, R.layout.fragment_navigate);
    }
}
