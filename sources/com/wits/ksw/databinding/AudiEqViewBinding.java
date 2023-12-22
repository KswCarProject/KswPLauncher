package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.EQViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi.widget.EqSeekBar;

/* loaded from: classes7.dex */
public abstract class AudiEqViewBinding extends ViewDataBinding {
    public final RadioButton aaa;
    public final EqSeekBar bassSeekBar;
    public final LinearLayout hzMediaLinearLayout;
    public final AudiConstraintLayout linearLayout4;
    public final LinearLayout linearLayout5;
    public final LinearLayout linearLayout6;
    @Bindable
    protected EQViewModel mVm;
    public final EqSeekBar mezzoSeekBar;
    public final EqSeekBar trebleSeekBar;

    public abstract void setVm(EQViewModel vm);

    protected AudiEqViewBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton aaa, EqSeekBar bassSeekBar, LinearLayout hzMediaLinearLayout, AudiConstraintLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, EqSeekBar mezzoSeekBar, EqSeekBar trebleSeekBar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.aaa = aaa;
        this.bassSeekBar = bassSeekBar;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.linearLayout4 = linearLayout4;
        this.linearLayout5 = linearLayout5;
        this.linearLayout6 = linearLayout6;
        this.mezzoSeekBar = mezzoSeekBar;
        this.trebleSeekBar = trebleSeekBar;
    }

    public EQViewModel getVm() {
        return this.mVm;
    }

    public static AudiEqViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiEqViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiEqViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_eq_view, root, attachToRoot, component);
    }

    public static AudiEqViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiEqViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiEqViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_eq_view, null, false, component);
    }

    public static AudiEqViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiEqViewBinding bind(View view, Object component) {
        return (AudiEqViewBinding) bind(component, view, C0899R.C0902layout.audi_eq_view);
    }
}
