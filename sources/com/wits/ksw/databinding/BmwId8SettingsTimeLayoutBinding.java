package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsTimeLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsTime12, RelativeLayout bmwId8SettingsTime24, RelativeLayout bmwId8SettingsTimeAndroid, ImageView bmwId8SettingsTimeBack, RelativeLayout bmwId8SettingsTimeCar, RelativeLayout bmwId8SettingsTimeFormat, RelativeLayout bmwId8SettingsTimeLay, RelativeLayout bmwId8SettingsTimeSync) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsTime12 = bmwId8SettingsTime12;
        this.bmwId8SettingsTime24 = bmwId8SettingsTime24;
        this.bmwId8SettingsTimeAndroid = bmwId8SettingsTimeAndroid;
        this.bmwId8SettingsTimeBack = bmwId8SettingsTimeBack;
        this.bmwId8SettingsTimeCar = bmwId8SettingsTimeCar;
        this.bmwId8SettingsTimeFormat = bmwId8SettingsTimeFormat;
        this.bmwId8SettingsTimeLay = bmwId8SettingsTimeLay;
        this.bmwId8SettingsTimeSync = bmwId8SettingsTimeSync;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsTimeLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_time_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsTimeLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsTimeLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_time_layout, null, false, component);
    }

    public static BmwId8SettingsTimeLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsTimeLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsTimeLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_time_layout);
    }
}
