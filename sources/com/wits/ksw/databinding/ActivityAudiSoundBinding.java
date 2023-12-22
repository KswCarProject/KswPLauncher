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
import com.wits.ksw.settings.audi.p007vm.VolumeViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(VolumeViewModel vm);

    protected ActivityAudiSoundBinding(Object _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar, TextView audioSeekbarRightText, TextView audioSeekbarTitle, LinearLayout carCallLinearLayout, LinearLayout carNaviLinearLayout, TextView carVolumeTextView, LinearLayout hzCallLinearLayout, LinearLayout hzMediaLinearLayout, TextView hzTextView, ConstraintLayout linearLayout4) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar;
        this.audioSeekbarRightText = audioSeekbarRightText;
        this.audioSeekbarTitle = audioSeekbarTitle;
        this.carCallLinearLayout = carCallLinearLayout;
        this.carNaviLinearLayout = carNaviLinearLayout;
        this.carVolumeTextView = carVolumeTextView;
        this.hzCallLinearLayout = hzCallLinearLayout;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.hzTextView = hzTextView;
        this.linearLayout4 = linearLayout4;
    }

    public VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiSoundBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_sound, root, attachToRoot, component);
    }

    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiSoundBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiSoundBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_sound, null, false, component);
    }

    public static ActivityAudiSoundBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiSoundBinding bind(View view, Object component) {
        return (ActivityAudiSoundBinding) bind(component, view, C0899R.C0902layout.activity_audi_sound);
    }
}
