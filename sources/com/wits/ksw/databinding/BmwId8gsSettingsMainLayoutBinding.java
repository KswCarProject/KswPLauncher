package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8gsSettingsMainLayoutBinding extends ViewDataBinding {
    public final Id8GsLauncherLeftBarBinding bmwId8SettingsMainLeftBar;
    public final RecyclerView bmwId8SettingsMainRecycle;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    public abstract void setViewModel(BmwId8SettingsViewModel ViewModel);

    protected BmwId8gsSettingsMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, Id8GsLauncherLeftBarBinding bmwId8SettingsMainLeftBar, RecyclerView bmwId8SettingsMainRecycle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsMainLeftBar = bmwId8SettingsMainLeftBar;
        this.bmwId8SettingsMainRecycle = bmwId8SettingsMainRecycle;
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
        return (BmwId8gsSettingsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8gs_settings_main_layout, root, attachToRoot, component);
    }

    public static BmwId8gsSettingsMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsSettingsMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8gsSettingsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8gs_settings_main_layout, null, false, component);
    }

    public static BmwId8gsSettingsMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsSettingsMainLayoutBinding bind(View view, Object component) {
        return (BmwId8gsSettingsMainLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8gs_settings_main_layout);
    }
}