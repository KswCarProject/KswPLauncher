package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class PhoneDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mBtViewModel;

    public abstract void setBtViewModel(LauncherViewModel BtViewModel);

    protected PhoneDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RelativeLayout llContainerGs) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.llContainerGs = llContainerGs;
    }

    public LauncherViewModel getBtViewModel() {
        return this.mBtViewModel;
    }

    public static PhoneDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (PhoneDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_phone_gs, root, attachToRoot, component);
    }

    public static PhoneDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (PhoneDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_phone_gs, null, false, component);
    }

    public static PhoneDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PhoneDataGsBinding bind(View view, Object component) {
        return (PhoneDataGsBinding) bind(component, view, C0899R.C0902layout.fragment_phone_gs);
    }
}
