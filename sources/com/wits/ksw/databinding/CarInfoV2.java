package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class CarInfoV2 extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mCarViewModel;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected CarInfoV2(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static CarInfoV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (CarInfoV2) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_fragment_car, root, attachToRoot, component);
    }

    public static CarInfoV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoV2 inflate(LayoutInflater inflater, Object component) {
        return (CarInfoV2) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_fragment_car, (ViewGroup) null, false, component);
    }

    public static CarInfoV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfoV2 bind(View view, Object component) {
        return (CarInfoV2) bind(component, view, R.layout.id7_v2_fragment_car);
    }
}
