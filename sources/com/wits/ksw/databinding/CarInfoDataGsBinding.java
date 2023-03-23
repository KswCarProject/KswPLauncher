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

public abstract class CarInfoDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mCarViewModel;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected CarInfoDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RelativeLayout llContainerGs2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.llContainerGs = llContainerGs2;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static CarInfoDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (CarInfoDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_car_info_gs, root, attachToRoot, component);
    }

    public static CarInfoDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (CarInfoDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_car_info_gs, (ViewGroup) null, false, component);
    }

    public static CarInfoDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoDataGsBinding bind(View view, Object component) {
        return (CarInfoDataGsBinding) bind(component, view, R.layout.fragment_car_info_gs);
    }
}
