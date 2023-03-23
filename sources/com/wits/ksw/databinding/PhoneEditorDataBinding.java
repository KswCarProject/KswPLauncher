package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class PhoneEditorDataBinding extends ViewDataBinding {
    public final LinearLayout layout;
    @Bindable
    protected LauncherViewModel mBtViewModel;

    public abstract void setBtViewModel(LauncherViewModel launcherViewModel);

    protected PhoneEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout layout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.layout = layout2;
    }

    public LauncherViewModel getBtViewModel() {
        return this.mBtViewModel;
    }

    public static PhoneEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (PhoneEditorDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_phone_edit, root, attachToRoot, component);
    }

    public static PhoneEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (PhoneEditorDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_phone_edit, (ViewGroup) null, false, component);
    }

    public static PhoneEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneEditorDataBinding bind(View view, Object component) {
        return (PhoneEditorDataBinding) bind(component, view, R.layout.fragment_phone_edit);
    }
}
