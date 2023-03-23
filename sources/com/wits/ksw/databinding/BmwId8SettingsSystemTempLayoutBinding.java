package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsSystemTempLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsSystemTempC;
    public final RelativeLayout bmwId8SettingsSystemTempF;
    public final RelativeLayout bmwId8SettingsSystemTempLay;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsSystemTempLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsSystemTempC2, RelativeLayout bmwId8SettingsSystemTempF2, RelativeLayout bmwId8SettingsSystemTempLay2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsSystemTempC = bmwId8SettingsSystemTempC2;
        this.bmwId8SettingsSystemTempF = bmwId8SettingsSystemTempF2;
        this.bmwId8SettingsSystemTempLay = bmwId8SettingsSystemTempLay2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemTempLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemTempLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemTempLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_temp_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemTempLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemTempLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemTempLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_temp_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsSystemTempLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemTempLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemTempLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_system_temp_layout);
    }
}
