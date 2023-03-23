package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.LinearGradientProgress;

public abstract class BmwId8DashboardLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8DashboardLay;
    public final RelativeLayout bmwId8DashboardMidLay;
    public final BmwId8DashboardMusicLayoutBinding bmwId8DashboardMusicLay;
    public final LinearGradientProgress bmwId8DashboardRotateProgress;
    public final LinearGradientProgress bmwId8DashboardSpeedProgress;
    public final BmwId8DashboardWeatherLayoutBinding bmwId8DashboardWeatherLay;
    @Bindable
    protected DashboardViewModel mViewModel;

    public abstract void setViewModel(DashboardViewModel dashboardViewModel);

    protected BmwId8DashboardLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8DashboardLay2, RelativeLayout bmwId8DashboardMidLay2, BmwId8DashboardMusicLayoutBinding bmwId8DashboardMusicLay2, LinearGradientProgress bmwId8DashboardRotateProgress2, LinearGradientProgress bmwId8DashboardSpeedProgress2, BmwId8DashboardWeatherLayoutBinding bmwId8DashboardWeatherLay2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8DashboardLay = bmwId8DashboardLay2;
        this.bmwId8DashboardMidLay = bmwId8DashboardMidLay2;
        this.bmwId8DashboardMusicLay = bmwId8DashboardMusicLay2;
        this.bmwId8DashboardRotateProgress = bmwId8DashboardRotateProgress2;
        this.bmwId8DashboardSpeedProgress = bmwId8DashboardSpeedProgress2;
        this.bmwId8DashboardWeatherLay = bmwId8DashboardWeatherLay2;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8DashboardLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8DashboardLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_dashboard_layout, root, attachToRoot, component);
    }

    public static BmwId8DashboardLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8DashboardLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_dashboard_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8DashboardLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutBinding bind(View view, Object component) {
        return (BmwId8DashboardLayoutBinding) bind(component, view, R.layout.bmw_id8_dashboard_layout);
    }
}
