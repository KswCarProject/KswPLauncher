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
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.utils.CustomFontTextView;
import com.wits.ksw.launcher.view.CapitalizeTextView;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
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

    public abstract void setZlinkWeatherViewModel(AlsID7ViewModel ZlinkWeatherViewModel);

    protected AlsId7SubWeatherViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIcon, CustomFontTextView temperatureTv, CapitalizeTextView textView2, TextView textView3, TextView unitWeather, ConstraintLayout videoConstraintLayout, CustomBmwImageView weatherImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIcon = ivIcon;
        this.temperatureTv = temperatureTv;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.unitWeather = unitWeather;
        this.videoConstraintLayout = videoConstraintLayout;
        this.weatherImageView = weatherImageView;
    }

    public AlsID7ViewModel getZlinkWeatherViewModel() {
        return this.mZlinkWeatherViewModel;
    }

    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_weather_view, root, attachToRoot, component);
    }

    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubWeatherViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubWeatherViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_weather_view, null, false, component);
    }

    public static AlsId7SubWeatherViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubWeatherViewBinding bind(View view, Object component) {
        return (AlsId7SubWeatherViewBinding) bind(component, view, C0899R.C0902layout.als_id7_sub_weather_view);
    }
}
