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
public abstract class WeatherDataGsBinding extends ViewDataBinding {
    public final ImageView ivIcon;
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView weatherTv;

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected WeatherDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIcon, ImageView ivMask, RelativeLayout llContainerGs, TextView tvCity, TextView tvTitle, TextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIcon = ivIcon;
        this.ivMask = ivMask;
        this.llContainerGs = llContainerGs;
        this.tvCity = tvCity;
        this.tvTitle = tvTitle;
        this.weatherTv = weatherTv;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static WeatherDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (WeatherDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_weather_gs, root, attachToRoot, component);
    }

    public static WeatherDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (WeatherDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_weather_gs, null, false, component);
    }

    public static WeatherDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WeatherDataGsBinding bind(View view, Object component) {
        return (WeatherDataGsBinding) bind(component, view, C0899R.C0902layout.fragment_weather_gs);
    }
}
