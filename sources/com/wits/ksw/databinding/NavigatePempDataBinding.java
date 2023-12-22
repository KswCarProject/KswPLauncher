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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class NavigatePempDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mNavigateViewModel;
    public final TextView tvDesc;

    public abstract void setNavigateViewModel(LauncherViewModel NavigateViewModel);

    protected NavigatePempDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RelativeLayout llContainer, TextView tvDesc) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.llContainer = llContainer;
        this.tvDesc = tvDesc;
    }

    public LauncherViewModel getNavigateViewModel() {
        return this.mNavigateViewModel;
    }

    public static NavigatePempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigatePempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NavigatePempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_navigate, root, attachToRoot, component);
    }

    public static NavigatePempDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigatePempDataBinding inflate(LayoutInflater inflater, Object component) {
        return (NavigatePempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_navigate, null, false, component);
    }

    public static NavigatePempDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NavigatePempDataBinding bind(View view, Object component) {
        return (NavigatePempDataBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_navigate);
    }
}