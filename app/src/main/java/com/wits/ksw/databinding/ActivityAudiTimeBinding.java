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
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class ActivityAudiTimeBinding extends ViewDataBinding {
    @NonNull
    public final TextView audiSyncTime;
    @NonNull
    public final TextView audiTimeZhishi;
    @NonNull
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiSettingViewModel mVm;
    @NonNull
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(@Nullable AudiSettingViewModel audiSettingViewModel);

    protected ActivityAudiTimeBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView audiSyncTime2, TextView audiTimeZhishi2, AudiConstraintLayout linearLayout42, RadioGroup timeRadioGroup2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSyncTime = audiSyncTime2;
        this.audiTimeZhishi = audiTimeZhishi2;
        this.linearLayout4 = linearLayout42;
        this.timeRadioGroup = timeRadioGroup2;
    }

    @Nullable
    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static ActivityAudiTimeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAudiTimeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityAudiTimeBinding) DataBindingUtil.inflate(inflater, R.layout.activity_audi_time, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityAudiTimeBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAudiTimeBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityAudiTimeBinding) DataBindingUtil.inflate(inflater, R.layout.activity_audi_time, (ViewGroup) null, false, component);
    }

    public static ActivityAudiTimeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityAudiTimeBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityAudiTimeBinding) bind(component, view, R.layout.activity_audi_time);
    }
}
