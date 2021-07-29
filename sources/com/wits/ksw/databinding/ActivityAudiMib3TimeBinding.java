package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel;

public abstract class ActivityAudiMib3TimeBinding extends ViewDataBinding {
    public final TextView audiSyncTime;
    public final TextView audiTimeZhishi;
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3SettingViewModel mVm;
    public final ScrollView svTime;
    public final RadioGroup timeRadioGroup;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SettingViewModel audiMib3SettingViewModel);

    protected ActivityAudiMib3TimeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSyncTime2, TextView audiTimeZhishi2, AudiConstraintLayout linearLayout42, ScrollView svTime2, RadioGroup timeRadioGroup2, AppCompatTextView title2, View titleDivider2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSyncTime = audiSyncTime2;
        this.audiTimeZhishi = audiTimeZhishi2;
        this.linearLayout4 = linearLayout42;
        this.svTime = svTime2;
        this.timeRadioGroup = timeRadioGroup2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
    }

    public AudiMib3SettingViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3TimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_mib3_time, root, attachToRoot, component);
    }

    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3TimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_mib3_time, (ViewGroup) null, false, component);
    }

    public static ActivityAudiMib3TimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3TimeBinding bind(View view, Object component) {
        return (ActivityAudiMib3TimeBinding) bind(component, view, R.layout.activity_audi_mib3_time);
    }
}
