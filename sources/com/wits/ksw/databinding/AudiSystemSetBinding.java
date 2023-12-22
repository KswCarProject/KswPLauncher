package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiSystemViewModel vm);

    protected AudiSystemSetBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSystemAuxPostion, TextView audiSystemBrightness, CheckBox audiSystemDrivingProhibitedVideo, TextView audiSystemReverCamera, CheckBox audiSystemReverCameraImg, CheckBox audiSystemReverMute, CheckBox audiSystemReverRadar, CheckBox audiSystemReverTrack, AudiConstraintLayout audiSystemSetParentPanel, TextView audiSystemSpeedUnit, TextView audiSystemTempUnit, TextView tvMusicApp, TextView tvVideoApp) {
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
        this.tvMusicApp = tvMusicApp;
        this.tvVideoApp = tvVideoApp;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiSystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSystemSetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiSystemSetBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_system_set, root, attachToRoot, component);
    }

    public static AudiSystemSetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSystemSetBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiSystemSetBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_system_set, null, false, component);
    }

    public static AudiSystemSetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSystemSetBinding bind(View view, Object component) {
        return (AudiSystemSetBinding) bind(component, view, C0899R.C0902layout.audi_system_set);
    }
}
