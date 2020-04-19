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
import com.wits.ksw.settings.audi.vm.VolumeViewModel;

public abstract class ActivityAudiSoundBinding extends ViewDataBinding {
    @NonNull
    public final SeekBar audioSeekbar;
    @NonNull
    public final TextView audioSeekbarRightText;
    @NonNull
    public final TextView audioSeekbarTitle;
    @NonNull
    public final LinearLayout carCallLinearLayout;
    @NonNull
    public final LinearLayout carNaviLinearLayout;
    @NonNull
    public final TextView carVolumeTextView;
    @NonNull
    public final LinearLayout hzCallLinearLayout;
    @NonNull
    public final LinearLayout hzMediaLinearLayout;
    @NonNull
    public final TextView hzTextView;
    @NonNull
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected VolumeViewModel mVm;

    public abstract void setVm(@Nullable VolumeViewModel volumeViewModel);

    protected ActivityAudiSoundBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar2, TextView audioSeekbarRightText2, TextView audioSeekbarTitle2, LinearLayout carCallLinearLayout2, LinearLayout carNaviLinearLayout2, TextView carVolumeTextView2, LinearLayout hzCallLinearLayout2, LinearLayout hzMediaLinearLayout2, TextView hzTextView2, ConstraintLayout linearLayout42) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar2;
        this.audioSeekbarRightText = audioSeekbarRightText2;
        this.audioSeekbarTitle = audioSeekbarTitle2;
        this.carCallLinearLayout = carCallLinearLayout2;
        this.carNaviLinearLayout = carNaviLinearLayout2;
        this.carVolumeTextView = carVolumeTextView2;
        this.hzCallLinearLayout = hzCallLinearLayout2;
        this.hzMediaLinearLayout = hzMediaLinearLayout2;
        this.hzTextView = hzTextView2;
        this.linearLayout4 = linearLayout42;
    }

    @Nullable
    public VolumeViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static ActivityAudiSoundBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAudiSoundBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityAudiSoundBinding) DataBindingUtil.inflate(inflater, R.layout.activity_audi_sound, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityAudiSoundBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAudiSoundBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityAudiSoundBinding) DataBindingUtil.inflate(inflater, R.layout.activity_audi_sound, (ViewGroup) null, false, component);
    }

    public static ActivityAudiSoundBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityAudiSoundBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityAudiSoundBinding) bind(component, view, R.layout.activity_audi_sound);
    }
}
