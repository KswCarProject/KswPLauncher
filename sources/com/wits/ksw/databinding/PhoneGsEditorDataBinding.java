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

public abstract class PhoneGsEditorDataBinding extends ViewDataBinding {
    public final ImageView gsId8IconEditBg;
    @Bindable
    protected LauncherViewModel mBtViewModel;

    public abstract void setBtViewModel(LauncherViewModel launcherViewModel);

    protected PhoneGsEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gsId8IconEditBg2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsId8IconEditBg = gsId8IconEditBg2;
    }

    public LauncherViewModel getBtViewModel() {
        return this.mBtViewModel;
    }

    public static PhoneGsEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneGsEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (PhoneGsEditorDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_phone_edit, root, attachToRoot, component);
    }

    public static PhoneGsEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneGsEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (PhoneGsEditorDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_phone_edit, (ViewGroup) null, false, component);
    }

    public static PhoneGsEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneGsEditorDataBinding bind(View view, Object component) {
        return (PhoneGsEditorDataBinding) bind(component, view, R.layout.fragment_gs_phone_edit);
    }
}
