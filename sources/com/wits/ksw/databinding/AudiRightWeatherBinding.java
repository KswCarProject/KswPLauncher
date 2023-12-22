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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;

/* loaded from: classes7.dex */
public abstract class AudiRightWeatherBinding extends ViewDataBinding {
    public final ConstraintLayout clWeather;
    public final ImageView ivIcon;
    @Bindable
    protected AudiViewModel mVm;
    public final TextView temperatureTv;
    public final TextView unitWeather;

    public abstract void setVm(AudiViewModel vm);

    protected AudiRightWeatherBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clWeather, ImageView ivIcon, TextView temperatureTv, TextView unitWeather) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clWeather = clWeather;
        this.ivIcon = ivIcon;
        this.temperatureTv = temperatureTv;
        this.unitWeather = unitWeather;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiRightWeatherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightWeatherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiRightWeatherBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_right_weather, root, attachToRoot, component);
    }

    public static AudiRightWeatherBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightWeatherBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiRightWeatherBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_right_weather, null, false, component);
    }

    public static AudiRightWeatherBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightWeatherBinding bind(View view, Object component) {
        return (AudiRightWeatherBinding) bind(component, view, C0899R.C0902layout.audi_right_weather);
    }
}
