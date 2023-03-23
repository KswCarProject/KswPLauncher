package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsSystemVideoLayoutBinding extends ViewDataBinding {
    public final RecyclerView bmwId8SettingsVideoRecycle;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsSystemVideoLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView bmwId8SettingsVideoRecycle2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsVideoRecycle = bmwId8SettingsVideoRecycle2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemVideoLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemVideoLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemVideoLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_video_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemVideoLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemVideoLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemVideoLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_video_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsSystemVideoLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemVideoLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemVideoLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_system_video_layout);
    }
}
