package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;
import com.wits.ksw.launcher.view.RollTextView;

/* loaded from: classes7.dex */
public abstract class Id7SubWeatherViewBinding extends ViewDataBinding {
    public final TextView btA;
    public final TextView btB;
    public final TextView btC;
    public final ImageView icon;
    public final RelativeLayout llA;
    public final RelativeLayout llB;
    public final RelativeLayout llC;
    @Bindable
    protected LauncherViewModel mWeatherViewModel;
    public final TextView phoneConnectionTextView;
    public final CustomBmwImageView phoneImageView;
    public final TextView temperatureTv;
    public final RollTextView textView2;
    public final TextView unitWeather;
    public final ConstraintLayout weatherConstraintLayout;

    public abstract void setWeatherViewModel(LauncherViewModel WeatherViewModel);

    protected Id7SubWeatherViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA, TextView btB, TextView btC, ImageView icon, RelativeLayout llA, RelativeLayout llB, RelativeLayout llC, TextView phoneConnectionTextView, CustomBmwImageView phoneImageView, TextView temperatureTv, RollTextView textView2, TextView unitWeather, ConstraintLayout weatherConstraintLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA;
        this.btB = btB;
        this.btC = btC;
        this.icon = icon;
        this.llA = llA;
        this.llB = llB;
        this.llC = llC;
        this.phoneConnectionTextView = phoneConnectionTextView;
        this.phoneImageView = phoneImageView;
        this.temperatureTv = temperatureTv;
        this.textView2 = textView2;
        this.unitWeather = unitWeather;
        this.weatherConstraintLayout = weatherConstraintLayout;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_weather_view, root, attachToRoot, component);
    }

    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_weather_view, null, false, component);
    }

    public static Id7SubWeatherViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubWeatherViewBinding bind(View view, Object component) {
        return (Id7SubWeatherViewBinding) bind(component, view, C0899R.C0902layout.id7_sub_weather_view);
    }
}
