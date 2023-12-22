package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8SettingsSystemMusicLayoutBinding extends ViewDataBinding {
    public final RecyclerView bmwId8SettingsMusicRecycle;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsSystemMusicLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView bmwId8SettingsMusicRecycle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsMusicRecycle = bmwId8SettingsMusicRecycle;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemMusicLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemMusicLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemMusicLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_music_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemMusicLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemMusicLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemMusicLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_music_layout, null, false, component);
    }

    public static BmwId8SettingsSystemMusicLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemMusicLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemMusicLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_system_music_layout);
    }
}
