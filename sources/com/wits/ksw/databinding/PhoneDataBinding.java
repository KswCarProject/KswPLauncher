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

public abstract class PhoneDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mBtViewModel;
    public final TextView tvDesc;

    public abstract void setBtViewModel(LauncherViewModel launcherViewModel);

    protected PhoneDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RelativeLayout llContainer2, TextView tvDesc2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.llContainer = llContainer2;
        this.tvDesc = tvDesc2;
    }

    public LauncherViewModel getBtViewModel() {
        return this.mBtViewModel;
    }

    public static PhoneDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (PhoneDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_phone, root, attachToRoot, component);
    }

    public static PhoneDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataBinding inflate(LayoutInflater inflater, Object component) {
        return (PhoneDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_phone, (ViewGroup) null, false, component);
    }

    public static PhoneDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataBinding bind(View view, Object component) {
        return (PhoneDataBinding) bind(component, view, R.layout.fragment_phone);
    }
}
