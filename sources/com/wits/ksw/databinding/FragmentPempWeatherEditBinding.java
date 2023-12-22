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
public abstract class FragmentPempWeatherEditBinding extends ViewDataBinding {
    public final TextView btA;
    public final TextView btB;
    public final TextView btC;
    public final ImageView ivDivider;
    public final RelativeLayout layout;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final ImageView remove;
    public final RelativeLayout temperature;
    public final TextView temperatureTv;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView unitWeather;
    public final TextView weatherTv;

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected FragmentPempWeatherEditBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA, TextView btB, TextView btC, ImageView ivDivider, RelativeLayout layout, ImageView remove, RelativeLayout temperature, TextView temperatureTv, TextView tvCity, TextView tvTitle, TextView unitWeather, TextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA;
        this.btB = btB;
        this.btC = btC;
        this.ivDivider = ivDivider;
        this.layout = layout;
        this.remove = remove;
        this.temperature = temperature;
        this.temperatureTv = temperatureTv;
        this.tvCity = tvCity;
        this.tvTitle = tvTitle;
        this.unitWeather = unitWeather;
        this.weatherTv = weatherTv;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static FragmentPempWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPempWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentPempWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_weather_edit, root, attachToRoot, component);
    }

    public static FragmentPempWeatherEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPempWeatherEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentPempWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_weather_edit, null, false, component);
    }

    public static FragmentPempWeatherEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentPempWeatherEditBinding bind(View view, Object component) {
        return (FragmentPempWeatherEditBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_weather_edit);
    }
}
