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

public abstract class ModusDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mModusViewModel;

    public abstract void setModusViewModel(LauncherViewModel launcherViewModel);

    protected ModusDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RelativeLayout llContainer2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.llContainer = llContainer2;
    }

    public LauncherViewModel getModusViewModel() {
        return this.mModusViewModel;
    }

    public static ModusDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ModusDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ModusDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_modus, root, attachToRoot, component);
    }

    public static ModusDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ModusDataBinding inflate(LayoutInflater inflater, Object component) {
        return (ModusDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_modus, (ViewGroup) null, false, component);
    }

    public static ModusDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ModusDataBinding bind(View view, Object component) {
        return (ModusDataBinding) bind(component, view, R.layout.fragment_modus);
    }
}
