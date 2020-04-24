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
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class AudiSysinfoBinding extends ViewDataBinding {
    @NonNull
    public final AudiConstraintLayout audiSysInfParentPanel;
    @NonNull
    public final TextView audiSysInfoAppVer;
    @NonNull
    public final TextView audiSysInfoMcuVer;
    @NonNull
    public final TextView audiSysInfoSysVer;
    @NonNull
    public final TextView audioSysInfoFlash;
    @NonNull
    public final TextView audioSysInfoMcuUpdata;
    @NonNull
    public final TextView audioSysInfoRam;
    @NonNull
    public final TextView audioSysInfoRestoreFactory;
    @Bindable
    protected AudiSettingViewModel mVm;

    public abstract void setVm(@Nullable AudiSettingViewModel audiSettingViewModel);

    protected AudiSysinfoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, AudiConstraintLayout audiSysInfParentPanel2, TextView audiSysInfoAppVer2, TextView audiSysInfoMcuVer2, TextView audiSysInfoSysVer2, TextView audioSysInfoFlash2, TextView audioSysInfoMcuUpdata2, TextView audioSysInfoRam2, TextView audioSysInfoRestoreFactory2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSysInfParentPanel = audiSysInfParentPanel2;
        this.audiSysInfoAppVer = audiSysInfoAppVer2;
        this.audiSysInfoMcuVer = audiSysInfoMcuVer2;
        this.audiSysInfoSysVer = audiSysInfoSysVer2;
        this.audioSysInfoFlash = audioSysInfoFlash2;
        this.audioSysInfoMcuUpdata = audioSysInfoMcuUpdata2;
        this.audioSysInfoRam = audioSysInfoRam2;
        this.audioSysInfoRestoreFactory = audioSysInfoRestoreFactory2;
    }

    @Nullable
    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiSysinfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiSysinfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiSysinfoBinding) DataBindingUtil.inflate(inflater, R.layout.audi_sysinfo, root, attachToRoot, component);
    }

    @NonNull
    public static AudiSysinfoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiSysinfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiSysinfoBinding) DataBindingUtil.inflate(inflater, R.layout.audi_sysinfo, (ViewGroup) null, false, component);
    }

    public static AudiSysinfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiSysinfoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiSysinfoBinding) bind(component, view, R.layout.audi_sysinfo);
    }
}
