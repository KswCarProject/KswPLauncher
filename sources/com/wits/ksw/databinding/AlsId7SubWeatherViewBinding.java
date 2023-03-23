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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.CapitalizeTextView;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class AlsId7SubWeatherViewBinding extends ViewDataBinding {
    public final ImageView ivIcon;
    @Bindable
    protected AlsID7ViewModel mZlinkWeatherViewModel;
    public final CustomFontTextView temperatureTv;
    public final CapitalizeTextView textView2;
    public final TextView textView3;
    public final TextView unitWeather;
    public final ConstraintLayout videoConstraintLayout;
    public final CustomBmwImageView weatherImageView;

    public abstract void setZlinkWeatherViewModel(AlsID7ViewModel alsID7ViewModel);

    protected AlsId7SubWeatherViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIcon2, CustomFontTextView temperatureTv2, CapitalizeTextView textView22, TextView textView32, TextView unitWeather2, ConstraintLayout videoConstraintLayout2, CustomBmwImageView weatherImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIcon = ivIcon2;
        this.temperatureTv = temperatureTv2;
        this.textView2 = textView22;
        this.textView3 = textView32;
        this.unitWeather = unitWeather2;
        this.videoConstraintLayout = videoConstraintLayout2;
        this.weatherImageView = weatherImageView2;
    }

    public AlsID7ViewModel getZlinkWeatherViewModel() {
        return this.mZlinkWeatherViewModel;
    }

    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_weather_view, root, attachToRoot, component);
    }

    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_weather_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubWeatherViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubWeatherViewBinding bind(View view, Object component) {
        return (AlsId7SubWeatherViewBinding) bind(component, view, R.layout.als_id7_sub_weather_view);
    }
}
