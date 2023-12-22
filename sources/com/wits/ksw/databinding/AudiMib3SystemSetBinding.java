package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3SystemSetBinding extends ViewDataBinding {
    public final TextView audiSystemAuxPostion;
    public final TextView audiSystemBrightness;
    public final Switch audiSystemDrivingProhibitedVideo;
    public final TextView audiSystemReverCamera;
    public final Switch audiSystemReverCameraImg;
    public final Switch audiSystemReverMute;
    public final Switch audiSystemReverRadar;
    public final Switch audiSystemReverTrack;
    public final AudiConstraintLayout audiSystemSetParentPanel;
    public final TextView audiSystemSpeedUnit;
    public final TextView audiSystemTempUnit;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final ScrollView svList;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final TextView tvMusicApp;
    public final TextView tvVideoApp;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel vm);

    protected AudiMib3SystemSetBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSystemAuxPostion, TextView audiSystemBrightness, Switch audiSystemDrivingProhibitedVideo, TextView audiSystemReverCamera, Switch audiSystemReverCameraImg, Switch audiSystemReverMute, Switch audiSystemReverRadar, Switch audiSystemReverTrack, AudiConstraintLayout audiSystemSetParentPanel, TextView audiSystemSpeedUnit, TextView audiSystemTempUnit, ScrollView svList, AppCompatTextView title, View titleDivider, TextView tvMusicApp, TextView tvVideoApp, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSystemAuxPostion = audiSystemAuxPostion;
        this.audiSystemBrightness = audiSystemBrightness;
        this.audiSystemDrivingProhibitedVideo = audiSystemDrivingProhibitedVideo;
        this.audiSystemReverCamera = audiSystemReverCamera;
        this.audiSystemReverCameraImg = audiSystemReverCameraImg;
        this.audiSystemReverMute = audiSystemReverMute;
        this.audiSystemReverRadar = audiSystemReverRadar;
        this.audiSystemReverTrack = audiSystemReverTrack;
        this.audiSystemSetParentPanel = audiSystemSetParentPanel;
        this.audiSystemSpeedUnit = audiSystemSpeedUnit;
        this.audiSystemTempUnit = audiSystemTempUnit;
        this.svList = svList;
        this.title = title;
        this.titleDivider = titleDivider;
        this.tvMusicApp = tvMusicApp;
        this.tvVideoApp = tvVideoApp;
        this.vDivider = vDivider;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3SystemSetBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_system_set, root, attachToRoot, component);
    }

    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3SystemSetBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_system_set, null, false, component);
    }

    public static AudiMib3SystemSetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SystemSetBinding bind(View view, Object component) {
        return (AudiMib3SystemSetBinding) bind(component, view, C0899R.C0902layout.audi_mib3_system_set);
    }
}
