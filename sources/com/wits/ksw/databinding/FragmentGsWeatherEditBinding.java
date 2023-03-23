package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class FragmentGsWeatherEditBinding extends ViewDataBinding {
    public final ImageView gsId8IconEditBg;
    public final ImageView ivIcon;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final ImageView remove;
    public final TextView tvCity;
    public final TextView tvTitle;
    public final TextView weatherTv;

    public abstract void setWeatherViewModel(LauncherViewModel launcherViewModel);

    protected FragmentGsWeatherEditBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gsId8IconEditBg2, ImageView ivIcon2, ImageView remove2, TextView tvCity2, TextView tvTitle2, TextView weatherTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsId8IconEditBg = gsId8IconEditBg2;
        this.ivIcon = ivIcon2;
        this.remove = remove2;
        this.tvCity = tvCity2;
        this.tvTitle = tvTitle2;
        this.weatherTv = weatherTv2;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGsWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_weather_edit, root, attachToRoot, component);
    }

    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGsWeatherEditBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGsWeatherEditBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_weather_edit, (ViewGroup) null, false, component);
    }

    public static FragmentGsWeatherEditBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGsWeatherEditBinding bind(View view, Object component) {
        return (FragmentGsWeatherEditBinding) bind(component, view, R.layout.fragment_gs_weather_edit);
    }
}
