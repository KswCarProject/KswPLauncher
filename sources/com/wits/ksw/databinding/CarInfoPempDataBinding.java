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
public abstract class CarInfoPempDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    public final TextView tvDesc;

    public abstract void setCarViewModel(LauncherViewModel CarViewModel);

    protected CarInfoPempDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RelativeLayout llContainer, TextView tvDesc) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.llContainer = llContainer;
        this.tvDesc = tvDesc;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static CarInfoPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (CarInfoPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_car_info, root, attachToRoot, component);
    }

    public static CarInfoPempDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoPempDataBinding inflate(LayoutInflater inflater, Object component) {
        return (CarInfoPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_car_info, null, false, component);
    }

    public static CarInfoPempDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoPempDataBinding bind(View view, Object component) {
        return (CarInfoPempDataBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_car_info);
    }
}
