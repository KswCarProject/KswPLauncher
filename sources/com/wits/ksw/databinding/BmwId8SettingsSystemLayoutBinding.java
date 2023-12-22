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
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.bmw_id8.p009vm.BmwId8SettingsViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(BmwId8SettingsViewModel viewModel);

    protected BmwId8SettingsSystemLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout bmwId8SettingsSystemBrightness, RelativeLayout bmwId8SettingsSystemCamera, FrameLayout bmwId8SettingsSystemFramelay, RelativeLayout bmwId8SettingsSystemFuel, ImageView bmwId8SettingsSystemImg, ImageView bmwId8SettingsSystemLeftArrow, RelativeLayout bmwId8SettingsSystemLines, RelativeLayout bmwId8SettingsSystemMirror, RelativeLayout bmwId8SettingsSystemMotion, RelativeLayout bmwId8SettingsSystemMusic, RelativeLayout bmwId8SettingsSystemMute, RelativeLayout bmwId8SettingsSystemRadar, ImageView bmwId8SettingsSystemRightArrow, ScrollView bmwId8SettingsSystemScroll, RelativeLayout bmwId8SettingsSystemTemp, RelativeLayout bmwId8SettingsSystemVideo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8SettingsSystemBrightness = bmwId8SettingsSystemBrightness;
        this.bmwId8SettingsSystemCamera = bmwId8SettingsSystemCamera;
        this.bmwId8SettingsSystemFramelay = bmwId8SettingsSystemFramelay;
        this.bmwId8SettingsSystemFuel = bmwId8SettingsSystemFuel;
        this.bmwId8SettingsSystemImg = bmwId8SettingsSystemImg;
        this.bmwId8SettingsSystemLeftArrow = bmwId8SettingsSystemLeftArrow;
        this.bmwId8SettingsSystemLines = bmwId8SettingsSystemLines;
        this.bmwId8SettingsSystemMirror = bmwId8SettingsSystemMirror;
        this.bmwId8SettingsSystemMotion = bmwId8SettingsSystemMotion;
        this.bmwId8SettingsSystemMusic = bmwId8SettingsSystemMusic;
        this.bmwId8SettingsSystemMute = bmwId8SettingsSystemMute;
        this.bmwId8SettingsSystemRadar = bmwId8SettingsSystemRadar;
        this.bmwId8SettingsSystemRightArrow = bmwId8SettingsSystemRightArrow;
        this.bmwId8SettingsSystemScroll = bmwId8SettingsSystemScroll;
        this.bmwId8SettingsSystemTemp = bmwId8SettingsSystemTemp;
        this.bmwId8SettingsSystemVideo = bmwId8SettingsSystemVideo;
    }

    public BmwId8SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8SettingsSystemLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_layout, root, attachToRoot, component);
    }

    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8SettingsSystemLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.bmw_id8_settings_system_layout, null, false, component);
    }

    public static BmwId8SettingsSystemLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8SettingsSystemLayoutBinding bind(View view, Object component) {
        return (BmwId8SettingsSystemLayoutBinding) bind(component, view, C0899R.C0902layout.bmw_id8_settings_system_layout);
    }
}
