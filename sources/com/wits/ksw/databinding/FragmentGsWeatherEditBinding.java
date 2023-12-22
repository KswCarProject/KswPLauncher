package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class FragmentGsWeatherEditBinding extends ViewDataBinding {
    public final ImageView gsId8IconEditBg;
    public final ImageView ivIcon;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final ImageView remove;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView weatherTv;

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected FragmentGsWeatherEditBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gsId8IconEditBg, ImageView ivIcon, ImageView remove, TextView tvCity, TextView tvTitle, TextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsId8IconEditBg = gsId8IconEditBg;
        this.ivIcon = ivIcon;
        this.remove = remove;
        this.tvCity = tvCity;
        this.tvTitle = tvTitle;
        this.weatherTv = weatherTv;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGsWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_gs_weather_edit, root, attachToRoot, component);
    }

    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGsWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_gs_weather_edit, null, false, component);
    }

    public static FragmentGsWeatherEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGsWeatherEditBinding bind(View view, Object component) {
        return (FragmentGsWeatherEditBinding) bind(component, view, C0899R.C0902layout.fragment_gs_weather_edit);
    }
}
