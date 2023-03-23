package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsTimeLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsTime12;
    public final RelativeLayout bmwId8SettingsTime24;
    public final RelativeLayout bmwId8SettingsTimeAndroid;
    public final ImageView bmwId8SettingsTimeBack;
    public final RelativeLayout bmwId8SettingsTimeCar;
    public final RelativeLayout bmwId8SettingsTimeFormat;
    public final RelativeLayout bmwId8SettingsTimeLay;
    public final RelativeLayout bmwId8SettingsTimeSync;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsTimeLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsTime122, RelativeLayout bmwId8SettingsTime242, RelativeLayout bmwId8SettingsTimeAndroid2, ImageView bmwId8SettingsTimeBack2, RelativeLayout bmwId8SettingsTimeCar2, RelativeLayout bmwId8SettingsTimeFormat2, RelativeLayout bmwId8SettingsTimeLay2, RelativeLayout bmwId8SettingsTimeSync2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsTime12 = bmwId8SettingsTime122;
        this.bmwId8SettingsTime24 = bmwId8SettingsTime242;
        this.bmwId8SettingsTimeAndroid = bmwId8SettingsTimeAndroid2;
        this.bmwId8SettingsTimeBack = bmwId8SettingsTimeBack2;
        this.bmwId8SettingsTimeCar = bmwId8SettingsTimeCar2;
        this.bmwId8SettingsTimeFormat = bmwId8SettingsTimeFormat2;
        this.bmwId8SettingsTimeLay = bmwId8SettingsTimeLay2;
        this.bmwId8SettingsTimeSync = bmwId8SettingsTimeSync2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsTimeLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_time_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsTimeLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_time_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsTimeLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsTimeLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsTimeLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_time_layout);
    }
}
