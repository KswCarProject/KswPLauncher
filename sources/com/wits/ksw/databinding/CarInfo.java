package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class CarInfo extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mCarViewModel;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected CarInfo(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static CarInfo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (CarInfo) ViewDataBinding.inflateInternal(inflater, R.layout.id7_fragment_car, root, attachToRoot, component);
    }

    public static CarInfo inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfo inflate(LayoutInflater inflater, Object component) {
        return (CarInfo) ViewDataBinding.inflateInternal(inflater, R.layout.id7_fragment_car, (ViewGroup) null, false, component);
    }

    public static CarInfo bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CarInfo bind(View view, Object component) {
        return (CarInfo) bind(component, view, R.layout.id7_fragment_car);
    }
}
