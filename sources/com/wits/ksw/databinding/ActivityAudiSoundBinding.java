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
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.VolumeViewModel;

public abstract class ActivityAudiSoundBinding extends ViewDataBinding {
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final LinearLayout carCallLinearLayout;
    public final LinearLayout carNaviLinearLayout;
    public final TextView carVolumeTextView;
    public final LinearLayout hzCallLinearLayout;
    public final LinearLayout hzMediaLinearLayout;
    public final TextView hzTextView;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected VolumeViewModel mVm;

    public abstract void setVm(VolumeViewModel volumeViewModel);

    protected ActivityAudiSoundBinding(Object _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar2, TextView audioSeekbarRightText2, TextView audioSeekbarTitle2, LinearLayout carCallLinearLayout2, LinearLayout carNaviLinearLayout2, TextView carVolumeTextView2, LinearLayout hzCallLinearLayout2, LinearLayout hzMediaLinearLayout2, TextView hzTextView2, ConstraintLayout linearLayout42) {
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

    public VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiSoundBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_sound, root, attachToRoot, component);
    }

    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiSoundBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_sound, (ViewGroup) null, false, component);
    }

    public static ActivityAudiSoundBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiSoundBinding bind(View view, Object component) {
        return (ActivityAudiSoundBinding) bind(component, view, R.layout.activity_audi_sound);
    }
}
