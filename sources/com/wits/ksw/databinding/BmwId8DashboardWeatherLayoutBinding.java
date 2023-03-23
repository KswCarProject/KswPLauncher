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
import com.wits.ksw.launcher.model.DashboardViewModel;

public abstract class BmwId8DashboardWeatherLayoutBinding extends ViewDataBinding {
    public final ImageView ivIcon;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final TextView temperatureTv;
    public final TextView unitWeather;

    public abstract void setViewModel(DashboardViewModel dashboardViewModel);

    protected BmwId8DashboardWeatherLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivIcon2, TextView temperatureTv2, TextView unitWeather2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivIcon = ivIcon2;
        this.temperatureTv = temperatureTv2;
        this.unitWeather = unitWeather2;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8DashboardWeatherLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_dashboard_weather_layout, root, attachToRoot, component);
    }

    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardWeatherLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8DashboardWeatherLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_dashboard_weather_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8DashboardWeatherLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardWeatherLayoutBinding bind(View view, Object component) {
        return (BmwId8DashboardWeatherLayoutBinding) bind(component, view, R.layout.bmw_id8_dashboard_weather_layout);
    }
}
