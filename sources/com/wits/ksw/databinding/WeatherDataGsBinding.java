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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class WeatherDataGsBinding extends ViewDataBinding {
    public final ImageView ivIcon;
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView weatherTv;

    public abstract void setWeatherViewModel(LauncherViewModel launcherViewModel);

    protected WeatherDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIcon2, ImageView ivMask2, RelativeLayout llContainerGs2, TextView tvCity2, TextView tvTitle2, TextView weatherTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIcon = ivIcon2;
        this.ivMask = ivMask2;
        this.llContainerGs = llContainerGs2;
        this.tvCity = tvCity2;
        this.tvTitle = tvTitle2;
        this.weatherTv = weatherTv2;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static WeatherDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (WeatherDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_weather_gs, root, attachToRoot, component);
    }

    public static WeatherDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (WeatherDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_weather_gs, (ViewGroup) null, false, component);
    }

    public static WeatherDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataGsBinding bind(View view, Object component) {
        return (WeatherDataGsBinding) bind(component, view, R.layout.fragment_weather_gs);
    }
}
