package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public abstract class AudiMib3ReverCameraBinding extends ViewDataBinding {
    public final RadioButton RadioButton1;
    public final RadioButton RadioButton2;
    public final RadioButton RadioButton3;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final RadioGroup timeRadioGroup;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel audiMib3SystemViewModel);

    protected AudiMib3ReverCameraBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton RadioButton12, RadioButton RadioButton22, RadioButton RadioButton32, RadioGroup timeRadioGroup2, AppCompatTextView title2, View titleDivider2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.RadioButton1 = RadioButton12;
        this.RadioButton2 = RadioButton22;
        this.RadioButton3 = RadioButton32;
        this.timeRadioGroup = timeRadioGroup2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3ReverCameraBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_rever_camera, root, attachToRoot, component);
    }

    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3ReverCameraBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_rever_camera, (ViewGroup) null, false, component);
    }

    public static AudiMib3ReverCameraBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3ReverCameraBinding bind(View view, Object component) {
        return (AudiMib3ReverCameraBinding) bind(component, view, R.layout.audi_mib3_rever_camera);
    }
}
