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
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsAudioLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsAudioAndroidItem, FrameLayout bmwId8SettingsAudioFramelay, ImageView bmwId8SettingsAudioImg, RelativeLayout bmwId8SettingsAudioLay, ImageView bmwId8SettingsAudioLeftArrow, RelativeLayout bmwId8SettingsAudioOemItem, ImageView bmwId8SettingsAudioRightArrow, RelativeLayout bmwId8SettingsAudioSoundItem) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsAudioAndroidItem = bmwId8SettingsAudioAndroidItem;
        this.bmwId8SettingsAudioFramelay = bmwId8SettingsAudioFramelay;
        this.bmwId8SettingsAudioImg = bmwId8SettingsAudioImg;
        this.bmwId8SettingsAudioLay = bmwId8SettingsAudioLay;
        this.bmwId8SettingsAudioLeftArrow = bmwId8SettingsAudioLeftArrow;
        this.bmwId8SettingsAudioOemItem = bmwId8SettingsAudioOemItem;
        this.bmwId8SettingsAudioRightArrow = bmwId8SettingsAudioRightArrow;
        this.bmwId8SettingsAudioSoundItem = bmwId8SettingsAudioSoundItem;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsAudioLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_audio_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsAudioLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_audio_layout, null, false, component);
    }

    public static BmwId8SettingsAudioLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsAudioLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsAudioLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_audio_layout);
    }
}
