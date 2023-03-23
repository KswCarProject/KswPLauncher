package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsAudioLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsAudioAndroidItem;
    public final FrameLayout bmwId8SettingsAudioFramelay;
    public final ImageView bmwId8SettingsAudioImg;
    public final RelativeLayout bmwId8SettingsAudioLay;
    public final ImageView bmwId8SettingsAudioLeftArrow;
    public final RelativeLayout bmwId8SettingsAudioOemItem;
    public final ImageView bmwId8SettingsAudioRightArrow;
    public final RelativeLayout bmwId8SettingsAudioSoundItem;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    protected BmwId8SettingsAudioLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsAudioAndroidItem2, FrameLayout bmwId8SettingsAudioFramelay2, ImageView bmwId8SettingsAudioImg2, RelativeLayout bmwId8SettingsAudioLay2, ImageView bmwId8SettingsAudioLeftArrow2, RelativeLayout bmwId8SettingsAudioOemItem2, ImageView bmwId8SettingsAudioRightArrow2, RelativeLayout bmwId8SettingsAudioSoundItem2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsAudioAndroidItem = bmwId8SettingsAudioAndroidItem2;
        this.bmwId8SettingsAudioFramelay = bmwId8SettingsAudioFramelay2;
        this.bmwId8SettingsAudioImg = bmwId8SettingsAudioImg2;
        this.bmwId8SettingsAudioLay = bmwId8SettingsAudioLay2;
        this.bmwId8SettingsAudioLeftArrow = bmwId8SettingsAudioLeftArrow2;
        this.bmwId8SettingsAudioOemItem = bmwId8SettingsAudioOemItem2;
        this.bmwId8SettingsAudioRightArrow = bmwId8SettingsAudioRightArrow2;
        this.bmwId8SettingsAudioSoundItem = bmwId8SettingsAudioSoundItem2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsAudioLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_audio_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsAudioLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_audio_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsAudioLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsAudioLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_audio_layout);
    }
}
