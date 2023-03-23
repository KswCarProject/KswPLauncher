package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;

public abstract class BmwId8DashboardLayoutNewBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8DashboardLay;
    public final ImageView bmwId8DashboardLeftBg;
    public final RelativeLayout bmwId8DashboardMidLay;
    public final BmwId8DashboardMusicLayoutBinding bmwId8DashboardMusicLay;
    public final ImageView bmwId8DashboardRightBg;
    public final BmwId8DashboardWeatherLayoutBinding bmwId8DashboardWeatherLay;
    @Bindable
    protected DashboardViewModel mViewModel;

    public abstract void setViewModel(DashboardViewModel dashboardViewModel);

    protected BmwId8DashboardLayoutNewBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8DashboardLay2, ImageView bmwId8DashboardLeftBg2, RelativeLayout bmwId8DashboardMidLay2, BmwId8DashboardMusicLayoutBinding bmwId8DashboardMusicLay2, ImageView bmwId8DashboardRightBg2, BmwId8DashboardWeatherLayoutBinding bmwId8DashboardWeatherLay2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8DashboardLay = bmwId8DashboardLay2;
        this.bmwId8DashboardLeftBg = bmwId8DashboardLeftBg2;
        this.bmwId8DashboardMidLay = bmwId8DashboardMidLay2;
        this.bmwId8DashboardMusicLay = bmwId8DashboardMusicLay2;
        this.bmwId8DashboardRightBg = bmwId8DashboardRightBg2;
        this.bmwId8DashboardWeatherLay = bmwId8DashboardWeatherLay2;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8DashboardLayoutNewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_dashboard_layout_new, root, attachToRoot, component);
    }

    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8DashboardLayoutNewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_dashboard_layout_new, (ViewGroup) null, false, component);
    }

    public static BmwId8DashboardLayoutNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutNewBinding bind(View view, Object component) {
        return (BmwId8DashboardLayoutNewBinding) bind(component, view, R.layout.bmw_id8_dashboard_layout_new);
    }
}
