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
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3EQViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3EqViewBinding extends ViewDataBinding {
    public final RadioButton aaa;
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3EQViewModel mVm;
    public final RadioGroup rgList;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3EQViewModel vm);

    protected AudiMib3EqViewBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton aaa, AudiConstraintLayout linearLayout4, RadioGroup rgList, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.aaa = aaa;
        this.linearLayout4 = linearLayout4;
        this.rgList = rgList;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3EQViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3EqViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3EqViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_eq_view, root, attachToRoot, component);
    }

    public static AudiMib3EqViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3EqViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_eq_view, null, false, component);
    }

    public static AudiMib3EqViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqViewBinding bind(View view, Object component) {
        return (AudiMib3EqViewBinding) bind(component, view, C0899R.C0902layout.audi_mib3_eq_view);
    }
}
