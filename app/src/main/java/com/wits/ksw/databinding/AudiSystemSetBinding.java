package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class AudiSystemSetBinding extends ViewDataBinding {
    @NonNull
    public final TextView audiSystemAuxPostion;
    @NonNull
    public final TextView audiSystemBrightness;
    @NonNull
    public final CheckBox audiSystemDrivingProhibitedVideo;
    @NonNull
    public final TextView audiSystemReverCamera;
    @NonNull
    public final CheckBox audiSystemReverCameraImg;
    @NonNull
    public final CheckBox audiSystemReverMute;
    @NonNull
    public final CheckBox audiSystemReverRadar;
    @NonNull
    public final CheckBox audiSystemReverTrack;
    @NonNull
    public final AudiConstraintLayout audiSystemSetParentPanel;
    @NonNull
    public final TextView audiSystemSpeedUnit;
    @NonNull
    public final TextView audiSystemTempUnit;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(@Nullable AudiSystemViewModel audiSystemViewModel);

    protected AudiSystemSetBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView audiSystemAuxPostion2, TextView audiSystemBrightness2, CheckBox audiSystemDrivingProhibitedVideo2, TextView audiSystemReverCamera2, CheckBox audiSystemReverCameraImg2, CheckBox audiSystemReverMute2, CheckBox audiSystemReverRadar2, CheckBox audiSystemReverTrack2, AudiConstraintLayout audiSystemSetParentPanel2, TextView audiSystemSpeedUnit2, TextView audiSystemTempUnit2) {
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
    }

    @Nullable
    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiSystemSetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiSystemSetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiSystemSetBinding) DataBindingUtil.inflate(inflater, R.layout.audi_system_set, root, attachToRoot, component);
    }

    @NonNull
    public static AudiSystemSetBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiSystemSetBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiSystemSetBinding) DataBindingUtil.inflate(inflater, R.layout.audi_system_set, (ViewGroup) null, false, component);
    }

    public static AudiSystemSetBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiSystemSetBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiSystemSetBinding) bind(component, view, R.layout.audi_system_set);
    }
}
