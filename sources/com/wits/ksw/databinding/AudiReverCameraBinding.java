package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiReverCameraBinding extends ViewDataBinding {
    public final RadioButton RadioButton1;
    public final RadioButton RadioButton2;
    public final RadioButton RadioButton3;
    public final RadioButton RadioButton4;
    @Bindable
    protected AudiSystemViewModel mVm;
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(AudiSystemViewModel vm);

    protected AudiReverCameraBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton RadioButton1, RadioButton RadioButton2, RadioButton RadioButton3, RadioButton RadioButton4, RadioGroup timeRadioGroup) {
        super(_bindingComponent, _root, _localFieldCount);
        this.RadioButton1 = RadioButton1;
        this.RadioButton2 = RadioButton2;
        this.RadioButton3 = RadioButton3;
        this.RadioButton4 = RadioButton4;
        this.timeRadioGroup = timeRadioGroup;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiReverCameraBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_rever_camera, root, attachToRoot, component);
    }

    public static AudiReverCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiReverCameraBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiReverCameraBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_rever_camera, null, false, component);
    }

    public static AudiReverCameraBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiReverCameraBinding bind(View view, Object component) {
        return (AudiReverCameraBinding) bind(component, view, C0899R.C0902layout.audi_rever_camera);
    }
}
