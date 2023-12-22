package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8DashboardLayoutNewBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8DashboardLay;
    public final ImageView bmwId8DashboardLeftBg;
    public final RelativeLayout bmwId8DashboardMidLay;
    public final BmwId8DashboardMusicLayoutBinding bmwId8DashboardMusicLay;
    public final ImageView bmwId8DashboardRightBg;
    public final BmwId8DashboardWeatherLayoutBinding bmwId8DashboardWeatherLay;
    @Bindable
    protected DashboardViewModel mViewModel;

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected BmwId8DashboardLayoutNewBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8DashboardLay, ImageView bmwId8DashboardLeftBg, RelativeLayout bmwId8DashboardMidLay, BmwId8DashboardMusicLayoutBinding bmwId8DashboardMusicLay, ImageView bmwId8DashboardRightBg, BmwId8DashboardWeatherLayoutBinding bmwId8DashboardWeatherLay) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8DashboardLay = bmwId8DashboardLay;
        this.bmwId8DashboardLeftBg = bmwId8DashboardLeftBg;
        this.bmwId8DashboardMidLay = bmwId8DashboardMidLay;
        this.bmwId8DashboardMusicLay = bmwId8DashboardMusicLay;
        this.bmwId8DashboardRightBg = bmwId8DashboardRightBg;
        this.bmwId8DashboardWeatherLay = bmwId8DashboardWeatherLay;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8DashboardLayoutNewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_dashboard_layout_new, root, attachToRoot, component);
    }

    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutNewBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8DashboardLayoutNewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_dashboard_layout_new, null, false, component);
    }

    public static BmwId8DashboardLayoutNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8DashboardLayoutNewBinding bind(View view, Object component) {
        return (BmwId8DashboardLayoutNewBinding) bind(component, view, C0899R.C0902layout.bmw_id8_dashboard_layout_new);
    }
}
