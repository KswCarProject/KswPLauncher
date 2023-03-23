package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public abstract class AudiRightWeatherBinding extends ViewDataBinding {
    public final ConstraintLayout clWeather;
    public final ImageView ivIcon;
    @Bindable
    protected AudiViewModel mVm;
    public final TextView temperatureTv;
    public final TextView unitWeather;

    public abstract void setVm(AudiViewModel audiViewModel);

    protected AudiRightWeatherBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clWeather2, ImageView ivIcon2, TextView temperatureTv2, TextView unitWeather2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clWeather = clWeather2;
        this.ivIcon = ivIcon2;
        this.temperatureTv = temperatureTv2;
        this.unitWeather = unitWeather2;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiRightWeatherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightWeatherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiRightWeatherBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_right_weather, root, attachToRoot, component);
    }

    public static AudiRightWeatherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightWeatherBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiRightWeatherBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_right_weather, (ViewGroup) null, false, component);
    }

    public static AudiRightWeatherBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightWeatherBinding bind(View view, Object component) {
        return (AudiRightWeatherBinding) bind(component, view, R.layout.audi_right_weather);
    }
}
