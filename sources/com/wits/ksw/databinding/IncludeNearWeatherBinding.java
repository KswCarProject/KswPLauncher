package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class IncludeNearWeatherBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mWeatherViewModel;

    public abstract void setWeatherViewModel(LauncherViewModel launcherViewModel);

    protected IncludeNearWeatherBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static IncludeNearWeatherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static IncludeNearWeatherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (IncludeNearWeatherBinding) ViewDataBinding.inflateInternal(inflater, R.layout.include_near_weather, root, attachToRoot, component);
    }

    public static IncludeNearWeatherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static IncludeNearWeatherBinding inflate(LayoutInflater inflater, Object component) {
        return (IncludeNearWeatherBinding) ViewDataBinding.inflateInternal(inflater, R.layout.include_near_weather, (ViewGroup) null, false, component);
    }

    public static IncludeNearWeatherBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static IncludeNearWeatherBinding bind(View view, Object component) {
        return (IncludeNearWeatherBinding) bind(component, view, R.layout.include_near_weather);
    }
}
