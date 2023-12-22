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

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected WeatherDataBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA, TextView btB, TextView btC, ImageView ivDivider, ImageView ivIcon, ImageView ivMask, RelativeLayout llContainer, TextView temperatureTv, TextView tvCity, TextView tvTitle, TextView unitWeather) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA;
        this.btB = btB;
        this.btC = btC;
        this.ivDivider = ivDivider;
        this.ivIcon = ivIcon;
        this.ivMask = ivMask;
        this.llContainer = llContainer;
        this.temperatureTv = temperatureTv;
        this.tvCity = tvCity;
        this.tvTitle = tvTitle;
        this.unitWeather = unitWeather;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static WeatherDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (WeatherDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_weather, root, attachToRoot, component);
    }

    public static WeatherDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataBinding inflate(LayoutInflater inflater, Object component) {
        return (WeatherDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_weather, null, false, component);
    }

    public static WeatherDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataBinding bind(View view, Object component) {
        return (WeatherDataBinding) bind(component, view, C0899R.C0902layout.fragment_weather);
    }
}
