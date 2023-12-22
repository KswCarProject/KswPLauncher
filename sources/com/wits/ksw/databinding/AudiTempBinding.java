package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiTempBinding extends ViewDataBinding {
    @Bindable
    protected AudiSystemViewModel mVm;
    public final RadioGroup timeRadioGroup;

    public abstract void setVm(AudiSystemViewModel vm);

    protected AudiTempBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioGroup timeRadioGroup) {
        super(_bindingComponent, _root, _localFieldCount);
        this.timeRadioGroup = timeRadioGroup;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiTempBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiTempBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiTempBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_temp, root, attachToRoot, component);
    }

    public static AudiTempBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiTempBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiTempBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_temp, null, false, component);
    }

    public static AudiTempBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiTempBinding bind(View view, Object component) {
        return (AudiTempBinding) bind(component, view, C0899R.C0902layout.audi_temp);
    }
}
