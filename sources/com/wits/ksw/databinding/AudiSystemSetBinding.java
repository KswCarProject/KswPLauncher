package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class AudiSystemSetBinding extends ViewDataBinding {
    public final TextView audiSystemAuxPostion;
    public final TextView audiSystemBrightness;
    public final CheckBox audiSystemDrivingProhibitedVideo;
    public final TextView audiSystemReverCamera;
    public final CheckBox audiSystemReverCameraImg;
    public final CheckBox audiSystemReverMute;
    public final CheckBox audiSystemReverRadar;
    public final CheckBox audiSystemReverTrack;
    public final AudiConstraintLayout audiSystemSetParentPanel;
    public final TextView audiSystemSpeedUnit;
    public final TextView audiSystemTempUnit;
    @Bindable
    protected AudiSystemViewModel mVm;
    public final TextView tvMusicApp;
    public final TextView tvVideoApp;

    public abstract void setVm(AudiSystemViewModel audiSystemViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiSystemSetBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSystemAuxPostion2, TextView audiSystemBrightness2, CheckBox audiSystemDrivingProhibitedVideo2, TextView audiSystemReverCamera2, CheckBox audiSystemReverCameraImg2, CheckBox audiSystemReverMute2, CheckBox audiSystemReverRadar2, CheckBox audiSystemReverTrack2, AudiConstraintLayout audiSystemSetParentPanel2, TextView audiSystemSpeedUnit2, TextView audiSystemTempUnit2, TextView tvMusicApp2, TextView tvVideoApp2) {
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
        this.tvMusicApp = tvMusicApp2;
        this.tvVideoApp = tvVideoApp2;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiSystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiSystemSetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_system_set, root, attachToRoot, component);
    }

    public static AudiSystemSetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSystemSetBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiSystemSetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_system_set, (ViewGroup) null, false, component);
    }

    public static AudiSystemSetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSystemSetBinding bind(View view, Object component) {
        return (AudiSystemSetBinding) bind(component, view, R.layout.audi_system_set);
    }
}
