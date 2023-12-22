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
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3ReverCameraBinding extends ViewDataBinding {
    public final RadioButton RadioButton1;
    public final RadioButton RadioButton2;
    public final RadioButton RadioButton3;
    public final RadioButton RadioButton4;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final RadioGroup timeRadioGroup;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel vm);

    protected AudiMib3ReverCameraBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton RadioButton1, RadioButton RadioButton2, RadioButton RadioButton3, RadioButton RadioButton4, RadioGroup timeRadioGroup, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.RadioButton1 = RadioButton1;
        this.RadioButton2 = RadioButton2;
        this.RadioButton3 = RadioButton3;
        this.RadioButton4 = RadioButton4;
        this.timeRadioGroup = timeRadioGroup;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3ReverCameraBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_rever_camera, root, attachToRoot, component);
    }

    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3ReverCameraBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3ReverCameraBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_rever_camera, null, false, component);
    }

    public static AudiMib3ReverCameraBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3ReverCameraBinding bind(View view, Object component) {
        return (AudiMib3ReverCameraBinding) bind(component, view, C0899R.C0902layout.audi_mib3_rever_camera);
    }
}
