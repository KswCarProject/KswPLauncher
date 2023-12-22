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
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8SettingsAudioSoundLayoutBinding extends ViewDataBinding {
    public final ID8AudioProgressBar bmwId8SettingsAudioLow;
    public final ID8AudioProgressBar bmwId8SettingsAudioMid;
    public final RelativeLayout bmwId8SettingsAudioSoundClass;
    public final RelativeLayout bmwId8SettingsAudioSoundDance;
    public final RelativeLayout bmwId8SettingsAudioSoundJazz;
    public final RelativeLayout bmwId8SettingsAudioSoundLay;
    public final RelativeLayout bmwId8SettingsAudioSoundPop;
    public final RelativeLayout bmwId8SettingsAudioSoundRock;
    public final RelativeLayout bmwId8SettingsAudioSoundUser;
    public final ID8AudioProgressBar bmwId8SettingsAudioTre;
    public final ImageButton bmwId8SettingsBassAddBtn;
    public final ImageButton bmwId8SettingsBassSubBtn;
    public final ImageButton bmwId8SettingsMidAddBtn;
    public final ImageButton bmwId8SettingsMidSubBtn;
    public final ImageButton bmwId8SettingsTreAddBtn;
    public final ImageButton bmwId8SettingsTreSubBtn;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsAudioSoundLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ID8AudioProgressBar bmwId8SettingsAudioLow, ID8AudioProgressBar bmwId8SettingsAudioMid, RelativeLayout bmwId8SettingsAudioSoundClass, RelativeLayout bmwId8SettingsAudioSoundDance, RelativeLayout bmwId8SettingsAudioSoundJazz, RelativeLayout bmwId8SettingsAudioSoundLay, RelativeLayout bmwId8SettingsAudioSoundPop, RelativeLayout bmwId8SettingsAudioSoundRock, RelativeLayout bmwId8SettingsAudioSoundUser, ID8AudioProgressBar bmwId8SettingsAudioTre, ImageButton bmwId8SettingsBassAddBtn, ImageButton bmwId8SettingsBassSubBtn, ImageButton bmwId8SettingsMidAddBtn, ImageButton bmwId8SettingsMidSubBtn, ImageButton bmwId8SettingsTreAddBtn, ImageButton bmwId8SettingsTreSubBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsAudioLow = bmwId8SettingsAudioLow;
        this.bmwId8SettingsAudioMid = bmwId8SettingsAudioMid;
        this.bmwId8SettingsAudioSoundClass = bmwId8SettingsAudioSoundClass;
        this.bmwId8SettingsAudioSoundDance = bmwId8SettingsAudioSoundDance;
        this.bmwId8SettingsAudioSoundJazz = bmwId8SettingsAudioSoundJazz;
        this.bmwId8SettingsAudioSoundLay = bmwId8SettingsAudioSoundLay;
        this.bmwId8SettingsAudioSoundPop = bmwId8SettingsAudioSoundPop;
        this.bmwId8SettingsAudioSoundRock = bmwId8SettingsAudioSoundRock;
        this.bmwId8SettingsAudioSoundUser = bmwId8SettingsAudioSoundUser;
        this.bmwId8SettingsAudioTre = bmwId8SettingsAudioTre;
        this.bmwId8SettingsBassAddBtn = bmwId8SettingsBassAddBtn;
        this.bmwId8SettingsBassSubBtn = bmwId8SettingsBassSubBtn;
        this.bmwId8SettingsMidAddBtn = bmwId8SettingsMidAddBtn;
        this.bmwId8SettingsMidSubBtn = bmwId8SettingsMidSubBtn;
        this.bmwId8SettingsTreAddBtn = bmwId8SettingsTreAddBtn;
        this.bmwId8SettingsTreSubBtn = bmwId8SettingsTreSubBtn;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsAudioSoundLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_audio_sound_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsAudioSoundLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_audio_sound_layout, null, false, component);
    }

    public static BmwId8SettingsAudioSoundLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioSoundLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsAudioSoundLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_audio_sound_layout);
    }
}
