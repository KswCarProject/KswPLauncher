package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityAudiMib3TimeBinding extends ViewDataBinding {
    public final RadioButton audiMib312Time;
    public final RadioButton audiMib324Time;
    public final RadioButton audiMib3AndroidTime;
    public final RadioButton audiMib3CarTime;
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

    public abstract void setVm(AudiMib3SettingViewModel vm);

    protected ActivityAudiMib3TimeBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton audiMib312Time, RadioButton audiMib324Time, RadioButton audiMib3AndroidTime, RadioButton audiMib3CarTime, TextView audiSyncTime, TextView audiTimeZhishi, AudiConstraintLayout linearLayout4, ScrollView svTime, RadioGroup timeRadioGroup, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiMib312Time = audiMib312Time;
        this.audiMib324Time = audiMib324Time;
        this.audiMib3AndroidTime = audiMib3AndroidTime;
        this.audiMib3CarTime = audiMib3CarTime;
        this.audiSyncTime = audiSyncTime;
        this.audiTimeZhishi = audiTimeZhishi;
        this.linearLayout4 = linearLayout4;
        this.svTime = svTime;
        this.timeRadioGroup = timeRadioGroup;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3SettingViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3TimeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_time, root, attachToRoot, component);
    }

    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3TimeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3TimeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_time, null, false, component);
    }

    public static ActivityAudiMib3TimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3TimeBinding bind(View view, Object component) {
        return (ActivityAudiMib3TimeBinding) bind(component, view, C0899R.C0902layout.activity_audi_mib3_time);
    }
}
