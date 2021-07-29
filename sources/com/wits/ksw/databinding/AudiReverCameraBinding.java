package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public abstract class AudiReverCameraBinding extends ViewDataBinding {
    public final RadioButton RadioButton1;
    public final RadioButton RadioButton2;
    public final RadioButton RadioButton3;
    @Bindable
    protected AudiSystemViewModel mVm;
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(AudiSystemViewModel audiSystemViewModel);

    protected AudiReverCameraBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton RadioButton12, RadioButton RadioButton22, RadioButton RadioButton32, RadioGroup timeRadioGroup2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.RadioButton1 = RadioButton12;
        this.RadioButton2 = RadioButton22;
        this.RadioButton3 = RadioButton32;
        this.timeRadioGroup = timeRadioGroup2;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiReverCameraBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_rever_camera, root, attachToRoot, component);
    }

    public static AudiReverCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiReverCameraBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiReverCameraBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_rever_camera, (ViewGroup) null, false, component);
    }

    public static AudiReverCameraBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiReverCameraBinding bind(View view, Object component) {
        return (AudiReverCameraBinding) bind(component, view, R.layout.audi_rever_camera);
    }
}
