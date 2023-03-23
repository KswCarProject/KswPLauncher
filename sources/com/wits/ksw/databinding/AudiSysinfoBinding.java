package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class AudiSysinfoBinding extends ViewDataBinding {
    public final AudiConstraintLayout audiSysInfParentPanel;
    public final TextView audiSysInfoAppVer;
    public final TextView audiSysInfoMcuVer;
    public final TextView audiSysInfoSysVer;
    public final TextView audioSysInfoFlash;
    public final TextView audioSysInfoMcuUpdata;
    public final TextView audioSysInfoRam;
    public final TextView audioSysInfoRestoreFactory;
    public final TextView audioSysInfoUpDateFactory;
    @Bindable
    protected AudiSettingViewModel mVm;

    public abstract void setVm(AudiSettingViewModel audiSettingViewModel);

    protected AudiSysinfoBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiConstraintLayout audiSysInfParentPanel2, TextView audiSysInfoAppVer2, TextView audiSysInfoMcuVer2, TextView audiSysInfoSysVer2, TextView audioSysInfoFlash2, TextView audioSysInfoMcuUpdata2, TextView audioSysInfoRam2, TextView audioSysInfoRestoreFactory2, TextView audioSysInfoUpDateFactory2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSysInfParentPanel = audiSysInfParentPanel2;
        this.audiSysInfoAppVer = audiSysInfoAppVer2;
        this.audiSysInfoMcuVer = audiSysInfoMcuVer2;
        this.audiSysInfoSysVer = audiSysInfoSysVer2;
        this.audioSysInfoFlash = audioSysInfoFlash2;
        this.audioSysInfoMcuUpdata = audioSysInfoMcuUpdata2;
        this.audioSysInfoRam = audioSysInfoRam2;
        this.audioSysInfoRestoreFactory = audioSysInfoRestoreFactory2;
        this.audioSysInfoUpDateFactory = audioSysInfoUpDateFactory2;
    }

    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    public static AudiSysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiSysinfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_sysinfo, root, attachToRoot, component);
    }

    public static AudiSysinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSysinfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiSysinfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_sysinfo, (ViewGroup) null, false, component);
    }

    public static AudiSysinfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSysinfoBinding bind(View view, Object component) {
        return (AudiSysinfoBinding) bind(component, view, R.layout.audi_sysinfo);
    }
}
