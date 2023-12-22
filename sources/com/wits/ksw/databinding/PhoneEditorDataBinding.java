package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class PhoneEditorDataBinding extends ViewDataBinding {
    public final LinearLayout layout;
    @Bindable
    protected LauncherViewModel mBtViewModel;

    public abstract void setBtViewModel(LauncherViewModel BtViewModel);

    protected PhoneEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout layout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.layout = layout;
    }

    public LauncherViewModel getBtViewModel() {
        return this.mBtViewModel;
    }

    public static PhoneEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (PhoneEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_phone_edit, root, attachToRoot, component);
    }

    public static PhoneEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (PhoneEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_phone_edit, null, false, component);
    }

    public static PhoneEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneEditorDataBinding bind(View view, Object component) {
        return (PhoneEditorDataBinding) bind(component, view, C0899R.C0902layout.fragment_phone_edit);
    }
}
