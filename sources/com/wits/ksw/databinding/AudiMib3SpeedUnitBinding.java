package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public abstract class AudiMib3SpeedUnitBinding extends ViewDataBinding {
    public final AudiConstraintLayout clUnit;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final RadioGroup timeRadioGroup;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel audiMib3SystemViewModel);

    protected AudiMib3SpeedUnitBinding(Object _bindingComponent, View _root, int _localFieldCount, AudiConstraintLayout clUnit2, RadioGroup timeRadioGroup2, AppCompatTextView title2, View titleDivider2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clUnit = clUnit2;
        this.timeRadioGroup = timeRadioGroup2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3SpeedUnitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SpeedUnitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3SpeedUnitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_speed_unit, root, attachToRoot, component);
    }

    public static AudiMib3SpeedUnitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SpeedUnitBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3SpeedUnitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_speed_unit, (ViewGroup) null, false, component);
    }

    public static AudiMib3SpeedUnitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3SpeedUnitBinding bind(View view, Object component) {
        return (AudiMib3SpeedUnitBinding) bind(component, view, R.layout.audi_mib3_speed_unit);
    }
}
