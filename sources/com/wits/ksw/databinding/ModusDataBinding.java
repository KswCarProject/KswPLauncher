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
public abstract class ModusDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mModusViewModel;

    public abstract void setModusViewModel(LauncherViewModel ModusViewModel);

    protected ModusDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RelativeLayout llContainer) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.llContainer = llContainer;
    }

    public LauncherViewModel getModusViewModel() {
        return this.mModusViewModel;
    }

    public static ModusDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ModusDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ModusDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_modus, root, attachToRoot, component);
    }

    public static ModusDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ModusDataBinding inflate(LayoutInflater inflater, Object component) {
        return (ModusDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_modus, null, false, component);
    }

    public static ModusDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ModusDataBinding bind(View view, Object component) {
        return (ModusDataBinding) bind(component, view, C0899R.C0902layout.fragment_modus);
    }
}
