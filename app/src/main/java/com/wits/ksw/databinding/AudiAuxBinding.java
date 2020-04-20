package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public abstract class AudiAuxBinding extends ViewDataBinding {
    @NonNull
    public final SeekBar audioSeekbar;
    @NonNull
    public final TextView audioSeekbarRightText;
    @NonNull
    public final TextView audioSeekbarTitle;
    @NonNull
    public final LinearLayout hzCallLinearLayout;
    @NonNull
    public final LinearLayout hzMediaLinearLayout;
    @NonNull
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(@Nullable AudiSystemViewModel audiSystemViewModel);

    protected AudiAuxBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar2, TextView audioSeekbarRightText2, TextView audioSeekbarTitle2, LinearLayout hzCallLinearLayout2, LinearLayout hzMediaLinearLayout2, ConstraintLayout linearLayout42) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar2;
        this.audioSeekbarRightText = audioSeekbarRightText2;
        this.audioSeekbarTitle = audioSeekbarTitle2;
        this.hzCallLinearLayout = hzCallLinearLayout2;
        this.hzMediaLinearLayout = hzMediaLinearLayout2;
        this.linearLayout4 = linearLayout42;
    }

    @Nullable
    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiAuxBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiAuxBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiAuxBinding) DataBindingUtil.inflate(inflater, R.layout.audi_aux, root, attachToRoot, component);
    }

    @NonNull
    public static AudiAuxBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiAuxBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiAuxBinding) DataBindingUtil.inflate(inflater, R.layout.audi_aux, (ViewGroup) null, false, component);
    }

    public static AudiAuxBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiAuxBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiAuxBinding) bind(component, view, R.layout.audi_aux);
    }
}
