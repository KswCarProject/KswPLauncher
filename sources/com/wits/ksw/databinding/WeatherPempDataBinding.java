package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class WeatherPempDataBinding extends ViewDataBinding {
    public final TextView btA;
    public final TextView btB;
    public final TextView btC;
    public final ImageView ivDivider;
    public final ImageView ivIcon;
    public final ImageView ivMask;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final RelativeLayout temperature;
    public final FrameLayout temperatureRange;
    public final TextView temperatureTv;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView unitWeather;
    public final TextView weatherTv;

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected WeatherPempDataBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA, TextView btB, TextView btC, ImageView ivDivider, ImageView ivIcon, ImageView ivMask, RelativeLayout llContainer, RelativeLayout temperature, FrameLayout temperatureRange, TextView temperatureTv, TextView tvCity, TextView tvTitle, TextView unitWeather, TextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA;
        this.btB = btB;
        this.btC = btC;
        this.ivDivider = ivDivider;
        this.ivIcon = ivIcon;
        this.ivMask = ivMask;
        this.llContainer = llContainer;
        this.temperature = temperature;
        this.temperatureRange = temperatureRange;
        this.temperatureTv = temperatureTv;
        this.tvCity = tvCity;
        this.tvTitle = tvTitle;
        this.unitWeather = unitWeather;
        this.weatherTv = weatherTv;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static WeatherPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (WeatherPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_weather, root, attachToRoot, component);
    }

    public static WeatherPempDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherPempDataBinding inflate(LayoutInflater inflater, Object component) {
        return (WeatherPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_weather, null, false, component);
    }

    public static WeatherPempDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherPempDataBinding bind(View view, Object component) {
        return (WeatherPempDataBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_weather);
    }
}
