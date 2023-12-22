package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class NaviFragment extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final NaviSubView naviLayout;
    public final Id7SubPhoneViewBinding phoneLayout;

    public abstract void setNaviViewModel(LauncherViewModel NaviViewModel);

    protected NaviFragment(Object _bindingComponent, View _root, int _localFieldCount, NaviSubView naviLayout, Id7SubPhoneViewBinding phoneLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviLayout = naviLayout;
        this.phoneLayout = phoneLayout;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static NaviFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NaviFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_fragment_navi, root, attachToRoot, component);
    }

    public static NaviFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragment inflate(LayoutInflater inflater, Object component) {
        return (NaviFragment) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_fragment_navi, null, false, component);
    }

    public static NaviFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviFragment bind(View view, Object component) {
        return (NaviFragment) bind(component, view, C0899R.C0902layout.id7_fragment_navi);
    }
}
