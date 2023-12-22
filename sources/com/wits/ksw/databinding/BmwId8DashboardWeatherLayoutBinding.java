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
import com.wits.ksw.launcher.model.DashboardViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8DashboardWeatherLayoutBinding extends ViewDataBinding {
    public final ImageView ivIcon;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final TextView temperatureTv;
    public final TextView unitWeather;

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected BmwId8DashboardWeatherLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIcon, TextView temperatureTv, TextView unitWeather) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIcon = ivIcon;
        this.temperatureTv = temperatureTv;
        this.unitWeather = unitWeather;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8DashboardWeatherLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_dashboard_weather_layout, root, attachToRoot, component);
    }

    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8DashboardWeatherLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_dashboard_weather_layout, null, false, component);
    }

    public static BmwId8DashboardWeatherLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardWeatherLayoutBinding bind(View view, Object component) {
        return (BmwId8DashboardWeatherLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_dashboard_weather_layout);
    }
}
