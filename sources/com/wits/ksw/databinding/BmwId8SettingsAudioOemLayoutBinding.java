package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

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

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsAudioOemLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsAudioOemLay2, ImageButton bmwId8SettingsNaviAddBtn2, ID8ProgressBar bmwId8SettingsNaviSeekbar2, ImageButton bmwId8SettingsNaviSubBtn2, ImageButton bmwId8SettingsOemCallAddBtn2, ID8ProgressBar bmwId8SettingsOemCallSeekbar2, ImageButton bmwId8SettingsOemCallSubBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsAudioOemLay = bmwId8SettingsAudioOemLay2;
        this.bmwId8SettingsNaviAddBtn = bmwId8SettingsNaviAddBtn2;
        this.bmwId8SettingsNaviSeekbar = bmwId8SettingsNaviSeekbar2;
        this.bmwId8SettingsNaviSubBtn = bmwId8SettingsNaviSubBtn2;
        this.bmwId8SettingsOemCallAddBtn = bmwId8SettingsOemCallAddBtn2;
        this.bmwId8SettingsOemCallSeekbar = bmwId8SettingsOemCallSeekbar2;
        this.bmwId8SettingsOemCallSubBtn = bmwId8SettingsOemCallSubBtn2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsAudioOemLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_audio_oem_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioOemLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsAudioOemLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_audio_oem_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsAudioOemLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioOemLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsAudioOemLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_audio_oem_layout);
    }
}
