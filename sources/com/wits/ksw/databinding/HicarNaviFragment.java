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

public abstract class HicarNaviFragment extends ViewDataBinding {
    @NonNull
    public final Id7SubHicarViewBinding hicarLayout;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    @NonNull
    public final NaviSubView naviLayout;

    public abstract void setNaviViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected HicarNaviFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Id7SubHicarViewBinding hicarLayout2, NaviSubView naviLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.hicarLayout = hicarLayout2;
        setContainedBinding(this.hicarLayout);
        this.naviLayout = naviLayout2;
        setContainedBinding(this.naviLayout);
    }

    @Nullable
    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    @NonNull
    public static HicarNaviFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static HicarNaviFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (HicarNaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi_hicar, root, attachToRoot, component);
    }

    @NonNull
    public static HicarNaviFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static HicarNaviFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (HicarNaviFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_navi_hicar, (ViewGroup) null, false, component);
    }

    public static HicarNaviFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static HicarNaviFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (HicarNaviFragment) bind(component, view, R.layout.id7_fragment_navi_hicar);
    }
}
