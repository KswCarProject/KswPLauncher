package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8SettingsSystemFuelLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsSystemFuelL;
    public final RelativeLayout bmwId8SettingsSystemFuelLay;
    public final RelativeLayout bmwId8SettingsSystemFuelUk;
    public final RelativeLayout bmwId8SettingsSystemFuelUs;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsSystemFuelLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsSystemFuelL, RelativeLayout bmwId8SettingsSystemFuelLay, RelativeLayout bmwId8SettingsSystemFuelUk, RelativeLayout bmwId8SettingsSystemFuelUs) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsSystemFuelL = bmwId8SettingsSystemFuelL;
        this.bmwId8SettingsSystemFuelLay = bmwId8SettingsSystemFuelLay;
        this.bmwId8SettingsSystemFuelUk = bmwId8SettingsSystemFuelUk;
        this.bmwId8SettingsSystemFuelUs = bmwId8SettingsSystemFuelUs;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemFuelLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_fuel_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemFuelLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemFuelLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_fuel_layout, null, false, component);
    }

    public static BmwId8SettingsSystemFuelLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemFuelLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemFuelLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_system_fuel_layout);
    }
}
