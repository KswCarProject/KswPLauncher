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
public abstract class BmwId8SettingsSystemCameraLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsSystemCamera360;
    public final RelativeLayout bmwId8SettingsSystemCamera360Built;
    public final RelativeLayout bmwId8SettingsSystemCameraAfter;
    public final RelativeLayout bmwId8SettingsSystemCameraLay;
    public final RelativeLayout bmwId8SettingsSystemCameraOem;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsSystemCameraLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsSystemCamera360, RelativeLayout bmwId8SettingsSystemCamera360Built, RelativeLayout bmwId8SettingsSystemCameraAfter, RelativeLayout bmwId8SettingsSystemCameraLay, RelativeLayout bmwId8SettingsSystemCameraOem) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsSystemCamera360 = bmwId8SettingsSystemCamera360;
        this.bmwId8SettingsSystemCamera360Built = bmwId8SettingsSystemCamera360Built;
        this.bmwId8SettingsSystemCameraAfter = bmwId8SettingsSystemCameraAfter;
        this.bmwId8SettingsSystemCameraLay = bmwId8SettingsSystemCameraLay;
        this.bmwId8SettingsSystemCameraOem = bmwId8SettingsSystemCameraOem;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemCameraLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemCameraLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemCameraLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_camera_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemCameraLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemCameraLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemCameraLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_camera_layout, null, false, component);
    }

    public static BmwId8SettingsSystemCameraLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemCameraLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemCameraLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_system_camera_layout);
    }
}
