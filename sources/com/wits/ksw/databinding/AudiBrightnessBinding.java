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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;

public abstract class AudiBrightnessBinding extends ViewDataBinding {
    @NonNull
    public final CheckBox audiSystemReverCamera;
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
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(@Nullable AudiSystemViewModel audiSystemViewModel);

    protected AudiBrightnessBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, CheckBox audiSystemReverCamera2, SeekBar audioSeekbar2, TextView audioSeekbarRightText2, TextView audioSeekbarTitle2, LinearLayout hzCallLinearLayout2, LinearLayout hzMediaLinearLayout2, AudiConstraintLayout linearLayout42) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSystemReverCamera = audiSystemReverCamera2;
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
    public static AudiBrightnessBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiBrightnessBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiBrightnessBinding) DataBindingUtil.inflate(inflater, R.layout.audi_brightness, root, attachToRoot, component);
    }

    @NonNull
    public static AudiBrightnessBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiBrightnessBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiBrightnessBinding) DataBindingUtil.inflate(inflater, R.layout.audi_brightness, (ViewGroup) null, false, component);
    }

    public static AudiBrightnessBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiBrightnessBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiBrightnessBinding) bind(component, view, R.layout.audi_brightness);
    }
}
