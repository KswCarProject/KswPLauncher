package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
public abstract class ActivityAudiTimeBinding extends ViewDataBinding {
    public final TextView audiSyncTime;
    public final TextView audiTimeZhishi;
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiSettingViewModel mVm;
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(AudiSettingViewModel vm);

    protected ActivityAudiTimeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView audiSyncTime, TextView audiTimeZhishi, AudiConstraintLayout linearLayout4, RadioGroup timeRadioGroup) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSyncTime = audiSyncTime;
        this.audiTimeZhishi = audiTimeZhishi;
        this.linearLayout4 = linearLayout4;
        this.timeRadioGroup = timeRadioGroup;
    }

    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiTimeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_time, root, attachToRoot, component);
    }

    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiTimeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiTimeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_time, null, false, component);
    }

    public static ActivityAudiTimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiTimeBinding bind(View view, Object component) {
        return (ActivityAudiTimeBinding) bind(component, view, C0899R.C0902layout.activity_audi_time);
    }
}
