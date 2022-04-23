package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel;

public abstract class AudiMib3SysinfoBinding extends ViewDataBinding {
    public final AudiConstraintLayout audiSysInfParentPanel;
    public final TextView audiSysInfoAppVer;
    public final TextView audiSysInfoMcuVer;
    public final TextView audiSysInfoSysVer;
    public final TextView audioSysInfoFlash;
    public final TextView audioSysInfoMcuUpdata;
    public final TextView audioSysInfoRam;
    public final TextView audioSysInfoRestoreFactory;
    @Bindable
    protected AudiMib3SettingViewModel mVm;
    public final ScrollView svSysinfo;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SettingViewModel audiMib3SettingViewModel);

    protected AudiMib3SysinfoBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiConstraintLayout audiSysInfParentPanel2, TextView audiSysInfoAppVer2, TextView audiSysInfoMcuVer2, TextView audiSysInfoSysVer2, TextView audioSysInfoFlash2, TextView audioSysInfoMcuUpdata2, TextView audioSysInfoRam2, TextView audioSysInfoRestoreFactory2, ScrollView svSysinfo2, AppCompatTextView title2, View titleDivider2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSysInfParentPanel = audiSysInfParentPanel2;
        this.audiSysInfoAppVer = audiSysInfoAppVer2;
        this.audiSysInfoMcuVer = audiSysInfoMcuVer2;
        this.audiSysInfoSysVer = audiSysInfoSysVer2;
        this.audioSysInfoFlash = audioSysInfoFlash2;
        this.audioSysInfoMcuUpdata = audioSysInfoMcuUpdata2;
        this.audioSysInfoRam = audioSysInfoRam2;
        this.audioSysInfoRestoreFactory = audioSysInfoRestoreFactory2;
        this.svSysinfo = svSysinfo2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
    }

    public AudiMib3SettingViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3SysinfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_sysinfo, root, attachToRoot, component);
    }

    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SysinfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3SysinfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_sysinfo, (ViewGroup) null, false, component);
    }

    public static AudiMib3SysinfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SysinfoBinding bind(View view, Object component) {
        return (AudiMib3SysinfoBinding) bind(component, view, R.layout.audi_mib3_sysinfo);
    }
}
