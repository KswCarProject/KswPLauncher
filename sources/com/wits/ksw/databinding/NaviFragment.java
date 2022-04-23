package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class NaviFragment extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final NaviSubView naviLayout;
    public final Id7SubPhoneViewBinding phoneLayout;

    public abstract void setNaviViewModel(LauncherViewModel launcherViewModel);

    protected NaviFragment(Object _bindingComponent, View _root, int _localFieldCount, NaviSubView naviLayout2, Id7SubPhoneViewBinding phoneLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviLayout = naviLayout2;
        this.phoneLayout = phoneLayout2;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static NaviFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NaviFragment) ViewDataBinding.inflateInternal(inflater, R.layout.id7_fragment_navi, root, attachToRoot, component);
    }

    public static NaviFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragment inflate(LayoutInflater inflater, Object component) {
        return (NaviFragment) ViewDataBinding.inflateInternal(inflater, R.layout.id7_fragment_navi, (ViewGroup) null, false, component);
    }

    public static NaviFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragment bind(View view, Object component) {
        return (NaviFragment) bind(component, view, R.layout.id7_fragment_navi);
    }
}
