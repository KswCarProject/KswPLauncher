package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3VolumeViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityAudiMib3SoundAndroidBinding extends ViewDataBinding {
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final View hzCallLinearLayout;
    public final View hzMediaLinearLayout;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3VolumeViewModel mVm;
    public final ImageView soundIcon;
    public final ImageView soundSetIv;
    public final TextView soundTv;
    public final TextView soundVolumTv;
    public final ScrollView svSound;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3VolumeViewModel vm);

    protected ActivityAudiMib3SoundAndroidBinding(Object _bindingComponent, View _root, int _localFieldCount, SeekBar audioSeekbar, TextView audioSeekbarRightText, TextView audioSeekbarTitle, View hzCallLinearLayout, View hzMediaLinearLayout, ConstraintLayout linearLayout4, ImageView soundIcon, ImageView soundSetIv, TextView soundTv, TextView soundVolumTv, ScrollView svSound, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audioSeekbar = audioSeekbar;
        this.audioSeekbarRightText = audioSeekbarRightText;
        this.audioSeekbarTitle = audioSeekbarTitle;
        this.hzCallLinearLayout = hzCallLinearLayout;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.linearLayout4 = linearLayout4;
        this.soundIcon = soundIcon;
        this.soundSetIv = soundSetIv;
        this.soundTv = soundTv;
        this.soundVolumTv = soundVolumTv;
        this.svSound = svSound;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3SoundAndroidBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_sound_android, root, attachToRoot, component);
    }

    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundAndroidBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3SoundAndroidBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_sound_android, null, false, component);
    }

    public static ActivityAudiMib3SoundAndroidBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundAndroidBinding bind(View view, Object component) {
        return (ActivityAudiMib3SoundAndroidBinding) bind(component, view, C0899R.C0902layout.activity_audi_mib3_sound_android);
    }
}
