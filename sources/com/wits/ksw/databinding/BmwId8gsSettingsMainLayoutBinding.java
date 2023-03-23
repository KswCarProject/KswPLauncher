package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8gsSettingsMainLayoutBinding extends ViewDataBinding {
    public final Id8GsLauncherLeftBarBinding bmwId8SettingsMainLeftBar;
    public final RecyclerView bmwId8SettingsMainRecycle;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8gsSettingsMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, Id8GsLauncherLeftBarBinding bmwId8SettingsMainLeftBar2, RecyclerView bmwId8SettingsMainRecycle2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsMainLeftBar = bmwId8SettingsMainLeftBar2;
        this.bmwId8SettingsMainRecycle = bmwId8SettingsMainRecycle2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8gsSettingsMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsSettingsMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8gsSettingsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8gs_settings_main_layout, root, attachToRoot, component);
    }

    public static BmwId8gsSettingsMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsSettingsMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8gsSettingsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8gs_settings_main_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8gsSettingsMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsSettingsMainLayoutBinding bind(View view, Object component) {
        return (BmwId8gsSettingsMainLayoutBinding) bind(component, view, R.layout.bmw_id8gs_settings_main_layout);
    }
}
