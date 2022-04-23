package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3VolumeViewModel;

public abstract class ActivityAudiMib3SoundOemBinding extends ViewDataBinding {
    public final View carCallLinearLayout;
    public final TextView carCallTv;
    public final TextView carNaviIv;
    public final View carNaviLinearLayout;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3VolumeViewModel mVm;
    public final ScrollView svSound;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;
    public final ImageView voic5Iv;
    public final TextView voic5Tv;
    public final ImageView voicIv;
    public final TextView voicTv;

    public abstract void setVm(AudiMib3VolumeViewModel audiMib3VolumeViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityAudiMib3SoundOemBinding(Object _bindingComponent, View _root, int _localFieldCount, View carCallLinearLayout2, TextView carCallTv2, TextView carNaviIv2, View carNaviLinearLayout2, ConstraintLayout linearLayout42, ScrollView svSound2, AppCompatTextView title2, View titleDivider2, View vDivider2, ImageView voic5Iv2, TextView voic5Tv2, ImageView voicIv2, TextView voicTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carCallLinearLayout = carCallLinearLayout2;
        this.carCallTv = carCallTv2;
        this.carNaviIv = carNaviIv2;
        this.carNaviLinearLayout = carNaviLinearLayout2;
        this.linearLayout4 = linearLayout42;
        this.svSound = svSound2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
        this.voic5Iv = voic5Iv2;
        this.voic5Tv = voic5Tv2;
        this.voicIv = voicIv2;
        this.voicTv = voicTv2;
    }

    public AudiMib3VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3SoundOemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_mib3_sound_oem, root, attachToRoot, component);
    }

    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3SoundOemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_audi_mib3_sound_oem, (ViewGroup) null, false, component);
    }

    public static ActivityAudiMib3SoundOemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundOemBinding bind(View view, Object component) {
        return (ActivityAudiMib3SoundOemBinding) bind(component, view, R.layout.activity_audi_mib3_sound_oem);
    }
}
