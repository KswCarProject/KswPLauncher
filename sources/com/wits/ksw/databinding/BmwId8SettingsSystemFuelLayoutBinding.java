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

public abstract class BmwId8SettingsSystemFuelLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsSystemFuelL;
    public final RelativeLayout bmwId8SettingsSystemFuelLay;
    public final RelativeLayout bmwId8SettingsSystemFuelUk;
    public final RelativeLayout bmwId8SettingsSystemFuelUs;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsSystemFuelLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsSystemFuelL2, RelativeLayout bmwId8SettingsSystemFuelLay2, RelativeLayout bmwId8SettingsSystemFuelUk2, RelativeLayout bmwId8SettingsSystemFuelUs2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsSystemFuelL = bmwId8SettingsSystemFuelL2;
        this.bmwId8SettingsSystemFuelLay = bmwId8SettingsSystemFuelLay2;
        this.bmwId8SettingsSystemFuelUk = bmwId8SettingsSystemFuelUk2;
        this.bmwId8SettingsSystemFuelUs = bmwId8SettingsSystemFuelUs2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemFuelLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_fuel_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemFuelLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_fuel_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsSystemFuelLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemFuelLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemFuelLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_system_fuel_layout);
    }
}
