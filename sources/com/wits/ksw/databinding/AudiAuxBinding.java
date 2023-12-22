package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiAuxBinding extends ViewDataBinding {
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final LinearLayout hzCallLinearLayout;
    public final LinearLayout hzMediaLinearLayout;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(AudiSystemViewModel vm);

    protected AudiAuxBinding(Object _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar, TextView audioSeekbarRightText, TextView audioSeekbarTitle, LinearLayout hzCallLinearLayout, LinearLayout hzMediaLinearLayout, ConstraintLayout linearLayout4) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar;
        this.audioSeekbarRightText = audioSeekbarRightText;
        this.audioSeekbarTitle = audioSeekbarTitle;
        this.hzCallLinearLayout = hzCallLinearLayout;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.linearLayout4 = linearLayout4;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiAuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiAuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiAuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_aux, root, attachToRoot, component);
    }

    public static AudiAuxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiAuxBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiAuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_aux, null, false, component);
    }

    public static AudiAuxBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiAuxBinding bind(View view, Object component) {
        return (AudiAuxBinding) bind(component, view, C0899R.C0902layout.audi_aux);
    }
}
