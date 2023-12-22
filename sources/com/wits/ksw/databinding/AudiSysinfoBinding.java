package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiSettingViewModel vm);

    protected AudiSysinfoBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiConstraintLayout audiSysInfParentPanel, TextView audiSysInfoAppVer, TextView audiSysInfoMcuVer, TextView audiSysInfoSysVer, TextView audioSysInfoFlash, TextView audioSysInfoMcuUpdata, TextView audioSysInfoRam, TextView audioSysInfoRestoreFactory, TextView audioSysInfoUpDateFactory) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSysInfParentPanel = audiSysInfParentPanel;
        this.audiSysInfoAppVer = audiSysInfoAppVer;
        this.audiSysInfoMcuVer = audiSysInfoMcuVer;
        this.audiSysInfoSysVer = audiSysInfoSysVer;
        this.audioSysInfoFlash = audioSysInfoFlash;
        this.audioSysInfoMcuUpdata = audioSysInfoMcuUpdata;
        this.audioSysInfoRam = audioSysInfoRam;
        this.audioSysInfoRestoreFactory = audioSysInfoRestoreFactory;
        this.audioSysInfoUpDateFactory = audioSysInfoUpDateFactory;
    }

    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    public static AudiSysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiSysinfoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_sysinfo, root, attachToRoot, component);
    }

    public static AudiSysinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSysinfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiSysinfoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_sysinfo, null, false, component);
    }

    public static AudiSysinfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSysinfoBinding bind(View view, Object component) {
        return (AudiSysinfoBinding) bind(component, view, C0899R.C0902layout.audi_sysinfo);
    }
}
