package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

/* loaded from: classes7.dex */
public abstract class AudiBrightnessBinding extends ViewDataBinding {
    public final CheckBox audiSystemReverCamera;
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final LinearLayout hzCallLinearLayout;
    public final LinearLayout hzMediaLinearLayout;
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(AudiSystemViewModel vm);

    protected AudiBrightnessBinding(Object _bindingComponent, View _root, int _localFieldCount, CheckBox audiSystemReverCamera, SeekBar audioSeekbar, TextView audioSeekbarRightText, TextView audioSeekbarTitle, LinearLayout hzCallLinearLayout, LinearLayout hzMediaLinearLayout, AudiConstraintLayout linearLayout4) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSystemReverCamera = audiSystemReverCamera;
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

    public static AudiBrightnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiBrightnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiBrightnessBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_brightness, root, attachToRoot, component);
    }

    public static AudiBrightnessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiBrightnessBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiBrightnessBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_brightness, null, false, component);
    }

    public static AudiBrightnessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiBrightnessBinding bind(View view, Object component) {
        return (AudiBrightnessBinding) bind(component, view, C0899R.C0902layout.audi_brightness);
    }
}
