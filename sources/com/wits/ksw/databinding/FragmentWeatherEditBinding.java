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
public abstract class FragmentWeatherEditBinding extends ViewDataBinding {
    public final TextView btA;
    public final TextView btB;
    public final TextView btC;
    public final ImageView ivDivider;
    public final RelativeLayout layout;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final ImageView remove;
    public final TextView temperatureTv;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView unitWeather;

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected FragmentWeatherEditBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA, TextView btB, TextView btC, ImageView ivDivider, RelativeLayout layout, ImageView remove, TextView temperatureTv, TextView tvCity, TextView tvTitle, TextView unitWeather) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA;
        this.btB = btB;
        this.btC = btC;
        this.ivDivider = ivDivider;
        this.layout = layout;
        this.remove = remove;
        this.temperatureTv = temperatureTv;
        this.tvCity = tvCity;
        this.tvTitle = tvTitle;
        this.unitWeather = unitWeather;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static FragmentWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_weather_edit, root, attachToRoot, component);
    }

    public static FragmentWeatherEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWeatherEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_weather_edit, null, false, component);
    }

    public static FragmentWeatherEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWeatherEditBinding bind(View view, Object component) {
        return (FragmentWeatherEditBinding) bind(component, view, C0899R.C0902layout.fragment_weather_edit);
    }
}
