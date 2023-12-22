package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3TempBinding extends ViewDataBinding {
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final RadioGroup timeRadioGroup;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel vm);

    protected AudiMib3TempBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioGroup timeRadioGroup, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.timeRadioGroup = timeRadioGroup;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3TempBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TempBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TempBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_temp, root, attachToRoot, component);
    }

    public static AudiMib3TempBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TempBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TempBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_temp, null, false, component);
    }

    public static AudiMib3TempBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TempBinding bind(View view, Object component) {
        return (AudiMib3TempBinding) bind(component, view, C0899R.C0902layout.audi_mib3_temp);
    }
}
