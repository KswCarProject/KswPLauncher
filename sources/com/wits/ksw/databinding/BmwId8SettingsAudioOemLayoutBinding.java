package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8SettingsAudioOemLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsAudioOemLay;
    public final ImageButton bmwId8SettingsNaviAddBtn;
    public final ID8ProgressBar bmwId8SettingsNaviSeekbar;
    public final ImageButton bmwId8SettingsNaviSubBtn;
    public final ImageButton bmwId8SettingsOemCallAddBtn;
    public final ID8ProgressBar bmwId8SettingsOemCallSeekbar;
    public final ImageButton bmwId8SettingsOemCallSubBtn;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsAudioOemLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsAudioOemLay, ImageButton bmwId8SettingsNaviAddBtn, ID8ProgressBar bmwId8SettingsNaviSeekbar, ImageButton bmwId8SettingsNaviSubBtn, ImageButton bmwId8SettingsOemCallAddBtn, ID8ProgressBar bmwId8SettingsOemCallSeekbar, ImageButton bmwId8SettingsOemCallSubBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsAudioOemLay = bmwId8SettingsAudioOemLay;
        this.bmwId8SettingsNaviAddBtn = bmwId8SettingsNaviAddBtn;
        this.bmwId8SettingsNaviSeekbar = bmwId8SettingsNaviSeekbar;
        this.bmwId8SettingsNaviSubBtn = bmwId8SettingsNaviSubBtn;
        this.bmwId8SettingsOemCallAddBtn = bmwId8SettingsOemCallAddBtn;
        this.bmwId8SettingsOemCallSeekbar = bmwId8SettingsOemCallSeekbar;
        this.bmwId8SettingsOemCallSubBtn = bmwId8SettingsOemCallSubBtn;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsAudioOemLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_audio_oem_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsAudioOemLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_audio_oem_layout, null, false, component);
    }

    public static BmwId8SettingsAudioOemLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioOemLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsAudioOemLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_audio_oem_layout);
    }
}
