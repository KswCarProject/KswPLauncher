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
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

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

    public abstract void setVm(AudiSystemViewModel audiSystemViewModel);

    protected AudiBrightnessBinding(Object _bindingComponent, View _root, int _localFieldCount, CheckBox audiSystemReverCamera2, SeekBar audioSeekbar2, TextView audioSeekbarRightText2, TextView audioSeekbarTitle2, LinearLayout hzCallLinearLayout2, LinearLayout hzMediaLinearLayout2, AudiConstraintLayout linearLayout42) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSystemReverCamera = audiSystemReverCamera2;
        this.audioSeekbar = audioSeekbar2;
        this.audioSeekbarRightText = audioSeekbarRightText2;
        this.audioSeekbarTitle = audioSeekbarTitle2;
        this.hzCallLinearLayout = hzCallLinearLayout2;
        this.hzMediaLinearLayout = hzMediaLinearLayout2;
        this.linearLayout4 = linearLayout42;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiBrightnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiBrightnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiBrightnessBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_brightness, root, attachToRoot, component);
    }

    public static AudiBrightnessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiBrightnessBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiBrightnessBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_brightness, (ViewGroup) null, false, component);
    }

    public static AudiBrightnessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiBrightnessBinding bind(View view, Object component) {
        return (AudiBrightnessBinding) bind(component, view, R.layout.audi_brightness);
    }
}
