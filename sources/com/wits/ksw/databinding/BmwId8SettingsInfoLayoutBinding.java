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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.MarqueeTextView;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsInfoLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, MarqueeTextView bmwId8InfoAppVersionContent, RelativeLayout bmwId8InfoMcuUpgrade, MarqueeTextView bmwId8InfoMcuVersionContent, MarqueeTextView bmwId8InfoRamContent, MarqueeTextView bmwId8InfoStorageContent, MarqueeTextView bmwId8InfoSysVersionContent, RelativeLayout bmwId8InfoSystemRecovery, RelativeLayout bmwId8InfoSystemUpdate, RelativeLayout bmwId8InfoSystemVersion, ImageView bmwId8SettingsInfoBack, ScrollView bmwId8SettingsInfoLay) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8InfoAppVersionContent = bmwId8InfoAppVersionContent;
        this.bmwId8InfoMcuUpgrade = bmwId8InfoMcuUpgrade;
        this.bmwId8InfoMcuVersionContent = bmwId8InfoMcuVersionContent;
        this.bmwId8InfoRamContent = bmwId8InfoRamContent;
        this.bmwId8InfoStorageContent = bmwId8InfoStorageContent;
        this.bmwId8InfoSysVersionContent = bmwId8InfoSysVersionContent;
        this.bmwId8InfoSystemRecovery = bmwId8InfoSystemRecovery;
        this.bmwId8InfoSystemUpdate = bmwId8InfoSystemUpdate;
        this.bmwId8InfoSystemVersion = bmwId8InfoSystemVersion;
        this.bmwId8SettingsInfoBack = bmwId8SettingsInfoBack;
        this.bmwId8SettingsInfoLay = bmwId8SettingsInfoLay;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsInfoLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_info_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsInfoLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsInfoLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_info_layout, null, false, component);
    }

    public static BmwId8SettingsInfoLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsInfoLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsInfoLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_info_layout);
    }
}
