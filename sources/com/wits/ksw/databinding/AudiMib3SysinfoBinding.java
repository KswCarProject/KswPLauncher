package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3SysinfoBinding extends ViewDataBinding {
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
    protected AudiMib3SettingViewModel mVm;
    public final ScrollView svSysinfo;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SettingViewModel vm);

    protected AudiMib3SysinfoBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiConstraintLayout audiSysInfParentPanel, TextView audiSysInfoAppVer, TextView audiSysInfoMcuVer, TextView audiSysInfoSysVer, TextView audioSysInfoFlash, TextView audioSysInfoMcuUpdata, TextView audioSysInfoRam, TextView audioSysInfoRestoreFactory, TextView audioSysInfoUpDateFactory, ScrollView svSysinfo, AppCompatTextView title, View titleDivider, View vDivider) {
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
        this.svSysinfo = svSysinfo;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3SettingViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3SysinfoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_sysinfo, root, attachToRoot, component);
    }

    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3SysinfoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_sysinfo, null, false, component);
    }

    public static AudiMib3SysinfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SysinfoBinding bind(View view, Object component) {
        return (AudiMib3SysinfoBinding) bind(component, view, C0899R.C0902layout.audi_mib3_sysinfo);
    }
}
