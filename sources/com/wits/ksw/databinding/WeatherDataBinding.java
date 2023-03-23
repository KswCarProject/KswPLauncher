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

public abstract class WeatherDataBinding extends ViewDataBinding {
    public final TextView btA;
    public final TextView btB;
    public final TextView btC;
    public final ImageView ivDivider;
    public final ImageView ivIcon;
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final TextView temperatureTv;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView unitWeather;

    public abstract void setWeatherViewModel(LauncherViewModel launcherViewModel);

    protected WeatherDataBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA2, TextView btB2, TextView btC2, ImageView ivDivider2, ImageView ivIcon2, ImageView ivMask2, RelativeLayout llContainer2, TextView temperatureTv2, TextView tvCity2, TextView tvTitle2, TextView unitWeather2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA2;
        this.btB = btB2;
        this.btC = btC2;
        this.ivDivider = ivDivider2;
        this.ivIcon = ivIcon2;
        this.ivMask = ivMask2;
        this.llContainer = llContainer2;
        this.temperatureTv = temperatureTv2;
        this.tvCity = tvCity2;
        this.tvTitle = tvTitle2;
        this.unitWeather = unitWeather2;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static WeatherDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (WeatherDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_weather, root, attachToRoot, component);
    }

    public static WeatherDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataBinding inflate(LayoutInflater inflater, Object component) {
        return (WeatherDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_weather, (ViewGroup) null, false, component);
    }

    public static WeatherDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataBinding bind(View view, Object component) {
        return (WeatherDataBinding) bind(component, view, R.layout.fragment_weather);
    }
}
