package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

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

    public abstract void setVm(AudiMib3SystemViewModel audiMib3SystemViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3SystemSetBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSystemAuxPostion2, TextView audiSystemBrightness2, Switch audiSystemDrivingProhibitedVideo2, TextView audiSystemReverCamera2, Switch audiSystemReverCameraImg2, Switch audiSystemReverMute2, Switch audiSystemReverRadar2, Switch audiSystemReverTrack2, AudiConstraintLayout audiSystemSetParentPanel2, TextView audiSystemSpeedUnit2, TextView audiSystemTempUnit2, ScrollView svList2, AppCompatTextView title2, View titleDivider2, TextView tvMusicApp2, TextView tvVideoApp2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSystemAuxPostion = audiSystemAuxPostion2;
        this.audiSystemBrightness = audiSystemBrightness2;
        this.audiSystemDrivingProhibitedVideo = audiSystemDrivingProhibitedVideo2;
        this.audiSystemReverCamera = audiSystemReverCamera2;
        this.audiSystemReverCameraImg = audiSystemReverCameraImg2;
        this.audiSystemReverMute = audiSystemReverMute2;
        this.audiSystemReverRadar = audiSystemReverRadar2;
        this.audiSystemReverTrack = audiSystemReverTrack2;
        this.audiSystemSetParentPanel = audiSystemSetParentPanel2;
        this.audiSystemSpeedUnit = audiSystemSpeedUnit2;
        this.audiSystemTempUnit = audiSystemTempUnit2;
        this.svList = svList2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.tvMusicApp = tvMusicApp2;
        this.tvVideoApp = tvVideoApp2;
        this.vDivider = vDivider2;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3SystemSetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_system_set, root, attachToRoot, component);
    }

    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SystemSetBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3SystemSetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_system_set, (ViewGroup) null, false, component);
    }

    public static AudiMib3SystemSetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SystemSetBinding bind(View view, Object component) {
        return (AudiMib3SystemSetBinding) bind(component, view, R.layout.audi_mib3_system_set);
    }
}
