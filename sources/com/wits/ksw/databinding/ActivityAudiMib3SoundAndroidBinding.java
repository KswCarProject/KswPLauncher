package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3VolumeViewModel;

public abstract class ActivityAudiMib3SoundAndroidBinding extends ViewDataBinding {
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final LinearLayout hzCallLinearLayout;
    public final LinearLayout hzMediaLinearLayout;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3VolumeViewModel mVm;
    public final ScrollView svSound;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3VolumeViewModel audiMib3VolumeViewModel);

    protected ActivityAudiMib3SoundAndroidBinding(Object _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar2, TextView audioSeekbarRightText2, TextView audioSeekbarTitle2, LinearLayout hzCallLinearLayout2, LinearLayout hzMediaLinearLayout2, ConstraintLayout linearLayout42, ScrollView svSound2, AppCompatTextView title2, View titleDivider2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar2;
        this.audioSeekbarRightText = audioSeekbarRightText2;
        this.audioSeekbarTitle = audioSeekbarTitle2;
        this.hzCallLinearLayout = hzCallLinearLayout2;
        this.hzMediaLinearLayout = hzMediaLinearLayout2;
        this.linearLayout4 = linearLayout42;
        this.svSound = svSound2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
    }

    public AudiMib3VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3SoundAndroidBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_mib3_sound_android, root, attachToRoot, component);
    }

    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3SoundAndroidBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_mib3_sound_android, (ViewGroup) null, false, component);
    }

    public static ActivityAudiMib3SoundAndroidBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundAndroidBinding bind(View view, Object component) {
        return (ActivityAudiMib3SoundAndroidBinding) bind(component, view, R.layout.activity_audi_mib3_sound_android);
    }
}
