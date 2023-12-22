package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3AuxBinding extends ViewDataBinding {
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final LinearLayout hzCallLinearLayout;
    public final LinearLayout hzMediaLinearLayout;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel vm);

    protected AudiMib3AuxBinding(Object _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar, TextView audioSeekbarRightText, TextView audioSeekbarTitle, LinearLayout hzCallLinearLayout, LinearLayout hzMediaLinearLayout, ConstraintLayout linearLayout4, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar;
        this.audioSeekbarRightText = audioSeekbarRightText;
        this.audioSeekbarTitle = audioSeekbarTitle;
        this.hzCallLinearLayout = hzCallLinearLayout;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.linearLayout4 = linearLayout4;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3AuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3AuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3AuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_aux, root, attachToRoot, component);
    }

    public static AudiMib3AuxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3AuxBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3AuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_aux, null, false, component);
    }

    public static AudiMib3AuxBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3AuxBinding bind(View view, Object component) {
        return (AudiMib3AuxBinding) bind(component, view, C0899R.C0902layout.audi_mib3_aux);
    }
}
