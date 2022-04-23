package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.EQViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi.widget.EqSeekBar;

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

    public abstract void setVm(EQViewModel eQViewModel);

    protected AudiEqViewBinding(Object _bindingComponent, View _root, int _localFieldCount, RadioButton aaa2, EqSeekBar bassSeekBar2, LinearLayout hzMediaLinearLayout2, AudiConstraintLayout linearLayout42, LinearLayout linearLayout52, LinearLayout linearLayout62, EqSeekBar mezzoSeekBar2, EqSeekBar trebleSeekBar2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.aaa = aaa2;
        this.bassSeekBar = bassSeekBar2;
        this.hzMediaLinearLayout = hzMediaLinearLayout2;
        this.linearLayout4 = linearLayout42;
        this.linearLayout5 = linearLayout52;
        this.linearLayout6 = linearLayout62;
        this.mezzoSeekBar = mezzoSeekBar2;
        this.trebleSeekBar = trebleSeekBar2;
    }

    public EQViewModel getVm() {
        return this.mVm;
    }

    public static AudiEqViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiEqViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiEqViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_eq_view, root, attachToRoot, component);
    }

    public static AudiEqViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiEqViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiEqViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_eq_view, (ViewGroup) null, false, component);
    }

    public static AudiEqViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiEqViewBinding bind(View view, Object component) {
        return (AudiEqViewBinding) bind(component, view, R.layout.audi_eq_view);
    }
}
