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
import android.widget.ScrollView;
import com.wits.ksw.R;
import com.wits.ksw.settings.bmw_id8.vm.BmwId8SettingsViewModel;

public abstract class BmwId8SettingsSystemLayoutBinding extends ViewDataBinding {
    public final RelativeLayout bmwId8SettingsSystemBrightness;
    public final RelativeLayout bmwId8SettingsSystemCamera;
    public final FrameLayout bmwId8SettingsSystemFramelay;
    public final RelativeLayout bmwId8SettingsSystemFuel;
    public final ImageView bmwId8SettingsSystemImg;
    public final ImageView bmwId8SettingsSystemLeftArrow;
    public final RelativeLayout bmwId8SettingsSystemLines;
    public final RelativeLayout bmwId8SettingsSystemMirror;
    public final RelativeLayout bmwId8SettingsSystemMotion;
    public final RelativeLayout bmwId8SettingsSystemMusic;
    public final RelativeLayout bmwId8SettingsSystemMute;
    public final RelativeLayout bmwId8SettingsSystemRadar;
    public final ImageView bmwId8SettingsSystemRightArrow;
    public final ScrollView bmwId8SettingsSystemScroll;
    public final RelativeLayout bmwId8SettingsSystemTemp;
    public final RelativeLayout bmwId8SettingsSystemVideo;
    @Bindable
    protected BmwId8SettingsViewModel mViewModel;

    public abstract void setViewModel(BmwId8SettingsViewModel bmwId8SettingsViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BmwId8SettingsSystemLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsSystemBrightness2, RelativeLayout bmwId8SettingsSystemCamera2, FrameLayout bmwId8SettingsSystemFramelay2, RelativeLayout bmwId8SettingsSystemFuel2, ImageView bmwId8SettingsSystemImg2, ImageView bmwId8SettingsSystemLeftArrow2, RelativeLayout bmwId8SettingsSystemLines2, RelativeLayout bmwId8SettingsSystemMirror2, RelativeLayout bmwId8SettingsSystemMotion2, RelativeLayout bmwId8SettingsSystemMusic2, RelativeLayout bmwId8SettingsSystemMute2, RelativeLayout bmwId8SettingsSystemRadar2, ImageView bmwId8SettingsSystemRightArrow2, ScrollView bmwId8SettingsSystemScroll2, RelativeLayout bmwId8SettingsSystemTemp2, RelativeLayout bmwId8SettingsSystemVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsSystemBrightness = bmwId8SettingsSystemBrightness2;
        this.bmwId8SettingsSystemCamera = bmwId8SettingsSystemCamera2;
        this.bmwId8SettingsSystemFramelay = bmwId8SettingsSystemFramelay2;
        this.bmwId8SettingsSystemFuel = bmwId8SettingsSystemFuel2;
        this.bmwId8SettingsSystemImg = bmwId8SettingsSystemImg2;
        this.bmwId8SettingsSystemLeftArrow = bmwId8SettingsSystemLeftArrow2;
        this.bmwId8SettingsSystemLines = bmwId8SettingsSystemLines2;
        this.bmwId8SettingsSystemMirror = bmwId8SettingsSystemMirror2;
        this.bmwId8SettingsSystemMotion = bmwId8SettingsSystemMotion2;
        this.bmwId8SettingsSystemMusic = bmwId8SettingsSystemMusic2;
        this.bmwId8SettingsSystemMute = bmwId8SettingsSystemMute2;
        this.bmwId8SettingsSystemRadar = bmwId8SettingsSystemRadar2;
        this.bmwId8SettingsSystemRightArrow = bmwId8SettingsSystemRightArrow2;
        this.bmwId8SettingsSystemScroll = bmwId8SettingsSystemScroll2;
        this.bmwId8SettingsSystemTemp = bmwId8SettingsSystemTemp2;
        this.bmwId8SettingsSystemVideo = bmwId8SettingsSystemVideo2;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.bmw_id8_settings_system_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8SettingsSystemLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemLayoutBinding) bind(component, view, R.layout.bmw_id8_settings_system_layout);
    }
}
