package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsSystemBrightnessLayoutBinding extends ViewDataBinding {
    public final ImageButton bmwId8SettingsBrightnessAddBtn;
    public final RelativeLayout bmwId8SettingsBrightnessLay;
    public final ID8ProgressBar bmwId8SettingsBrightnessSeekbar;
    public final ImageButton bmwId8SettingsBrightnessSubBtn;
    public final TextView bmwId8SettingsBrightnessText;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsSystemBrightnessLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageButton bmwId8SettingsBrightnessAddBtn2, RelativeLayout bmwId8SettingsBrightnessLay2, ID8ProgressBar bmwId8SettingsBrightnessSeekbar2, ImageButton bmwId8SettingsBrightnessSubBtn2, TextView bmwId8SettingsBrightnessText2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsBrightnessAddBtn = bmwId8SettingsBrightnessAddBtn2;
        this.bmwId8SettingsBrightnessLay = bmwId8SettingsBrightnessLay2;
        this.bmwId8SettingsBrightnessSeekbar = bmwId8SettingsBrightnessSeekbar2;
        this.bmwId8SettingsBrightnessSubBtn = bmwId8SettingsBrightnessSubBtn2;
        this.bmwId8SettingsBrightnessText = bmwId8SettingsBrightnessText2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemBrightnessLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemBrightnessLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemBrightnessLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_brightness_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemBrightnessLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemBrightnessLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemBrightnessLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_brightness_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsSystemBrightnessLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemBrightnessLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemBrightnessLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_system_brightness_layout);
    }
}
