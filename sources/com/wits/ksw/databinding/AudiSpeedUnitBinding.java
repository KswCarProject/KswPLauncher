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
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public abstract class AudiSpeedUnitBinding extends ViewDataBinding {
    @Bindable
    protected AudiSystemViewModel mVm;
    @NonNull
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(@Nullable AudiSystemViewModel audiSystemViewModel);

    protected AudiSpeedUnitBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, RadioGroup timeRadioGroup2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.timeRadioGroup = timeRadioGroup2;
    }

    @Nullable
    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiSpeedUnitBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiSpeedUnitBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiSpeedUnitBinding) DataBindingUtil.inflate(inflater, R.layout.audi_speed_unit, root, attachToRoot, component);
    }

    @NonNull
    public static AudiSpeedUnitBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiSpeedUnitBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiSpeedUnitBinding) DataBindingUtil.inflate(inflater, R.layout.audi_speed_unit, (ViewGroup) null, false, component);
    }

    public static AudiSpeedUnitBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiSpeedUnitBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiSpeedUnitBinding) bind(component, view, R.layout.audi_speed_unit);
    }
}
