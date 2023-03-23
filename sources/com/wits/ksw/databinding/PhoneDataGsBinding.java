package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class PhoneDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mBtViewModel;

    public abstract void setBtViewModel(LauncherViewModel launcherViewModel);

    protected PhoneDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RelativeLayout llContainerGs2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.llContainerGs = llContainerGs2;
    }

    public LauncherViewModel getBtViewModel() {
        return this.mBtViewModel;
    }

    public static PhoneDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (PhoneDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_phone_gs, root, attachToRoot, component);
    }

    public static PhoneDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (PhoneDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_phone_gs, (ViewGroup) null, false, component);
    }

    public static PhoneDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataGsBinding bind(View view, Object component) {
        return (PhoneDataGsBinding) bind(component, view, R.layout.fragment_phone_gs);
    }
}
