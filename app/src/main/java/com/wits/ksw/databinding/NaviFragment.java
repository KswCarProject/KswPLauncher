package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class NaviFragment extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    @NonNull
    public final NaviSubView naviLayout;
    @NonNull
    public final Id7SubPhoneViewBinding phoneLayout;

    public abstract void setNaviViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected NaviFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, NaviSubView naviLayout2, Id7SubPhoneViewBinding phoneLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviLayout = naviLayout2;
        setContainedBinding(this.naviLayout);
        this.phoneLayout = phoneLayout2;
        setContainedBinding(this.phoneLayout);
    }

    @Nullable
    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    @NonNull
    public static NaviFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static NaviFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (NaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi, root, attachToRoot, component);
    }

    @NonNull
    public static NaviFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static NaviFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (NaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi, (ViewGroup) null, false, component);
    }

    public static NaviFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static NaviFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (NaviFragment) bind(component, view, R.layout.id7_fragment_navi);
    }
}
