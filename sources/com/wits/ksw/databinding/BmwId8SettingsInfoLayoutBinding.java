package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.MarqueeTextView;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsInfoLayoutBinding extends ViewDataBinding {
    public final MarqueeTextView bmwId8InfoAppVersionContent;
    public final RelativeLayout bmwId8InfoMcuUpgrade;
    public final MarqueeTextView bmwId8InfoMcuVersionContent;
    public final MarqueeTextView bmwId8InfoRamContent;
    public final MarqueeTextView bmwId8InfoStorageContent;
    public final MarqueeTextView bmwId8InfoSysVersionContent;
    public final RelativeLayout bmwId8InfoSystemRecovery;
    public final RelativeLayout bmwId8InfoSystemUpdate;
    public final RelativeLayout bmwId8InfoSystemVersion;
    public final ImageView bmwId8SettingsInfoBack;
    public final ScrollView bmwId8SettingsInfoLay;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsInfoLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, MarqueeTextView bmwId8InfoAppVersionContent2, RelativeLayout bmwId8InfoMcuUpgrade2, MarqueeTextView bmwId8InfoMcuVersionContent2, MarqueeTextView bmwId8InfoRamContent2, MarqueeTextView bmwId8InfoStorageContent2, MarqueeTextView bmwId8InfoSysVersionContent2, RelativeLayout bmwId8InfoSystemRecovery2, RelativeLayout bmwId8InfoSystemUpdate2, RelativeLayout bmwId8InfoSystemVersion2, ImageView bmwId8SettingsInfoBack2, ScrollView bmwId8SettingsInfoLay2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8InfoAppVersionContent = bmwId8InfoAppVersionContent2;
        this.bmwId8InfoMcuUpgrade = bmwId8InfoMcuUpgrade2;
        this.bmwId8InfoMcuVersionContent = bmwId8InfoMcuVersionContent2;
        this.bmwId8InfoRamContent = bmwId8InfoRamContent2;
        this.bmwId8InfoStorageContent = bmwId8InfoStorageContent2;
        this.bmwId8InfoSysVersionContent = bmwId8InfoSysVersionContent2;
        this.bmwId8InfoSystemRecovery = bmwId8InfoSystemRecovery2;
        this.bmwId8InfoSystemUpdate = bmwId8InfoSystemUpdate2;
        this.bmwId8InfoSystemVersion = bmwId8InfoSystemVersion2;
        this.bmwId8SettingsInfoBack = bmwId8SettingsInfoBack2;
        this.bmwId8SettingsInfoLay = bmwId8SettingsInfoLay2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsInfoLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_info_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsInfoLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_info_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsInfoLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsInfoLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsInfoLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_info_layout);
    }
}
