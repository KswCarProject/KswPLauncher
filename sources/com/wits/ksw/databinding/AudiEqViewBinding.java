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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.EQViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi.widget.EqSeekBar;

public abstract class AudiEqViewBinding extends ViewDataBinding {
    @NonNull
    public final RadioButton aaa;
    @NonNull
    public final EqSeekBar bassSeekBar;
    @NonNull
    public final LinearLayout hzMediaLinearLayout;
    @NonNull
    public final AudiConstraintLayout linearLayout4;
    @NonNull
    public final LinearLayout linearLayout5;
    @NonNull
    public final LinearLayout linearLayout6;
    @Bindable
    protected EQViewModel mVm;
    @NonNull
    public final EqSeekBar mezzoSeekBar;
    @NonNull
    public final EqSeekBar trebleSeekBar;

    public abstract void setVm(@Nullable EQViewModel eQViewModel);

    protected AudiEqViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, RadioButton aaa2, EqSeekBar bassSeekBar2, LinearLayout hzMediaLinearLayout2, AudiConstraintLayout linearLayout42, LinearLayout linearLayout52, LinearLayout linearLayout62, EqSeekBar mezzoSeekBar2, EqSeekBar trebleSeekBar2) {
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

    @Nullable
    public EQViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiEqViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiEqViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiEqViewBinding) DataBindingUtil.inflate(inflater, R.layout.audi_eq_view, root, attachToRoot, component);
    }

    @NonNull
    public static AudiEqViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiEqViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiEqViewBinding) DataBindingUtil.inflate(inflater, R.layout.audi_eq_view, (ViewGroup) null, false, component);
    }

    public static AudiEqViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiEqViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiEqViewBinding) bind(component, view, R.layout.audi_eq_view);
    }
}
