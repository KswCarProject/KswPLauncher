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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;
import com.wits.ksw.launcher.view.RollTextView;

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

    public abstract void setWeatherViewModel(LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Id7SubWeatherViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btA2, TextView btB2, TextView btC2, ImageView icon2, RelativeLayout llA2, RelativeLayout llB2, RelativeLayout llC2, TextView phoneConnectionTextView2, CustomBmwImageView phoneImageView2, TextView temperatureTv2, RollTextView textView22, TextView unitWeather2, ConstraintLayout weatherConstraintLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btA = btA2;
        this.btB = btB2;
        this.btC = btC2;
        this.icon = icon2;
        this.llA = llA2;
        this.llB = llB2;
        this.llC = llC2;
        this.phoneConnectionTextView = phoneConnectionTextView2;
        this.phoneImageView = phoneImageView2;
        this.temperatureTv = temperatureTv2;
        this.textView2 = textView22;
        this.unitWeather = unitWeather2;
        this.weatherConstraintLayout = weatherConstraintLayout2;
    }

    public LauncherViewModel getWeatherViewModel() {
        return this.mWeatherViewModel;
    }

    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_weather_view, root, attachToRoot, component);
    }

    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubWeatherViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_weather_view, (ViewGroup) null, false, component);
    }

    public static Id7SubWeatherViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubWeatherViewBinding bind(View view, Object component) {
        return (Id7SubWeatherViewBinding) bind(component, view, R.layout.id7_sub_weather_view);
    }
}
