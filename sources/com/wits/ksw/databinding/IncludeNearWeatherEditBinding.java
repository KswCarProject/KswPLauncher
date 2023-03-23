package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class IncludeNearWeatherEditBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mWeatherViewModel;

    public abstract void setWeatherViewModel(LauncherViewModel launcherViewModel);

    protected IncludeNearWeatherEditBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static IncludeNearWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static IncludeNearWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (IncludeNearWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.include_near_weather_edit, root, attachToRoot, component);
    }

    public static IncludeNearWeatherEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static IncludeNearWeatherEditBinding inflate(LayoutInflater inflater, Object component) {
        return (IncludeNearWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.include_near_weather_edit, (ViewGroup) null, false, component);
    }

    public static IncludeNearWeatherEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static IncludeNearWeatherEditBinding bind(View view, Object component) {
        return (IncludeNearWeatherEditBinding) bind(component, view, R.layout.include_near_weather_edit);
    }
}
