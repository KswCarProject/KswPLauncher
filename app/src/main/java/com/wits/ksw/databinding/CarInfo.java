package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class CarInfo extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mCarViewModel;

    public abstract void setCarViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected CarInfo(DataBindingComponent _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    @Nullable
    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    @NonNull
    public static CarInfo inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CarInfo inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (CarInfo) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_car, root, attachToRoot, component);
    }

    @NonNull
    public static CarInfo inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CarInfo inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (CarInfo) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_car, (ViewGroup) null, false, component);
    }

    public static CarInfo bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static CarInfo bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (CarInfo) bind(component, view, R.layout.id7_fragment_car);
    }
}
