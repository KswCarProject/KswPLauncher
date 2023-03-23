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
import com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

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

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BmwId8SettingsAudioSoundLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ID8AudioProgressBar bmwId8SettingsAudioLow2, ID8AudioProgressBar bmwId8SettingsAudioMid2, RelativeLayout bmwId8SettingsAudioSoundClass2, RelativeLayout bmwId8SettingsAudioSoundDance2, RelativeLayout bmwId8SettingsAudioSoundJazz2, RelativeLayout bmwId8SettingsAudioSoundLay2, RelativeLayout bmwId8SettingsAudioSoundPop2, RelativeLayout bmwId8SettingsAudioSoundRock2, RelativeLayout bmwId8SettingsAudioSoundUser2, ID8AudioProgressBar bmwId8SettingsAudioTre2, ImageButton bmwId8SettingsBassAddBtn2, ImageButton bmwId8SettingsBassSubBtn2, ImageButton bmwId8SettingsMidAddBtn2, ImageButton bmwId8SettingsMidSubBtn2, ImageButton bmwId8SettingsTreAddBtn2, ImageButton bmwId8SettingsTreSubBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsAudioLow = bmwId8SettingsAudioLow2;
        this.bmwId8SettingsAudioMid = bmwId8SettingsAudioMid2;
        this.bmwId8SettingsAudioSoundClass = bmwId8SettingsAudioSoundClass2;
        this.bmwId8SettingsAudioSoundDance = bmwId8SettingsAudioSoundDance2;
        this.bmwId8SettingsAudioSoundJazz = bmwId8SettingsAudioSoundJazz2;
        this.bmwId8SettingsAudioSoundLay = bmwId8SettingsAudioSoundLay2;
        this.bmwId8SettingsAudioSoundPop = bmwId8SettingsAudioSoundPop2;
        this.bmwId8SettingsAudioSoundRock = bmwId8SettingsAudioSoundRock2;
        this.bmwId8SettingsAudioSoundUser = bmwId8SettingsAudioSoundUser2;
        this.bmwId8SettingsAudioTre = bmwId8SettingsAudioTre2;
        this.bmwId8SettingsBassAddBtn = bmwId8SettingsBassAddBtn2;
        this.bmwId8SettingsBassSubBtn = bmwId8SettingsBassSubBtn2;
        this.bmwId8SettingsMidAddBtn = bmwId8SettingsMidAddBtn2;
        this.bmwId8SettingsMidSubBtn = bmwId8SettingsMidSubBtn2;
        this.bmwId8SettingsTreAddBtn = bmwId8SettingsTreAddBtn2;
        this.bmwId8SettingsTreSubBtn = bmwId8SettingsTreSubBtn2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsAudioSoundLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_audio_sound_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioSoundLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsAudioSoundLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_audio_sound_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsAudioSoundLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioSoundLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsAudioSoundLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_audio_sound_layout);
    }
}
