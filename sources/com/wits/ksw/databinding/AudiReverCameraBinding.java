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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public abstract class AudiReverCameraBinding extends ViewDataBinding {
    @NonNull
    public final RadioButton RadioButton1;
    @NonNull
    public final RadioButton RadioButton2;
    @NonNull
    public final RadioButton RadioButton3;
    @Bindable
    protected AudiSystemViewModel mVm;
    @NonNull
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(@Nullable AudiSystemViewModel audiSystemViewModel);

    protected AudiReverCameraBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, RadioButton RadioButton12, RadioButton RadioButton22, RadioButton RadioButton32, RadioGroup timeRadioGroup2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.RadioButton1 = RadioButton12;
        this.RadioButton2 = RadioButton22;
        this.RadioButton3 = RadioButton32;
        this.timeRadioGroup = timeRadioGroup2;
    }

    @Nullable
    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiReverCameraBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiReverCameraBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiReverCameraBinding) DataBindingUtil.inflate(inflater, R.layout.audi_rever_camera, root, attachToRoot, component);
    }

    @NonNull
    public static AudiReverCameraBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiReverCameraBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiReverCameraBinding) DataBindingUtil.inflate(inflater, R.layout.audi_rever_camera, (ViewGroup) null, false, component);
    }

    public static AudiReverCameraBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiReverCameraBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiReverCameraBinding) bind(component, view, R.layout.audi_rever_camera);
    }
}
