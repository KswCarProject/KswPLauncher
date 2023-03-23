package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class NaviFragmentBindingV2 extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final NaviSubView naviLayout;
    public final Id7SubWeatherViewBinding phoneLayout;

    public abstract void setNaviViewModel(LauncherViewModel launcherViewModel);

    protected NaviFragmentBindingV2(Object _bindingComponent, View _root, int _localFieldCount, NaviSubView naviLayout2, Id7SubWeatherViewBinding phoneLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviLayout = naviLayout2;
        this.phoneLayout = phoneLayout2;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static NaviFragmentBindingV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragmentBindingV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NaviFragmentBindingV2) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_fragment_navi, root, attachToRoot, component);
    }

    public static NaviFragmentBindingV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragmentBindingV2 inflate(LayoutInflater inflater, Object component) {
        return (NaviFragmentBindingV2) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_fragment_navi, (ViewGroup) null, false, component);
    }

    public static NaviFragmentBindingV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragmentBindingV2 bind(View view, Object component) {
        return (NaviFragmentBindingV2) bind(component, view, R.layout.id7_v2_fragment_navi);
    }
}
