package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class ActivityAudiTimeBinding extends ViewDataBinding {
    public final TextView audiSyncTime;
    public final TextView audiTimeZhishi;
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiSettingViewModel mVm;
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(AudiSettingViewModel audiSettingViewModel);

    protected ActivityAudiTimeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSyncTime2, TextView audiTimeZhishi2, AudiConstraintLayout linearLayout42, RadioGroup timeRadioGroup2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSyncTime = audiSyncTime2;
        this.audiTimeZhishi = audiTimeZhishi2;
        this.linearLayout4 = linearLayout42;
        this.timeRadioGroup = timeRadioGroup2;
    }

    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiTimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_time, root, attachToRoot, component);
    }

    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiTimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_time, (ViewGroup) null, false, component);
    }

    public static ActivityAudiTimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiTimeBinding bind(View view, Object component) {
        return (ActivityAudiTimeBinding) bind(component, view, R.layout.activity_audi_time);
    }
}
