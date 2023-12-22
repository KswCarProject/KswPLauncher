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
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3VolumeViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiMib3VolumeViewModel vm);

    protected ActivityAudiMib3SoundOemBinding(Object _bindingComponent, View _root, int _localFieldCount, View carCallLinearLayout, TextView carCallTv, TextView carNaviIv, View carNaviLinearLayout, ConstraintLayout linearLayout4, ScrollView svSound, AppCompatTextView title, View titleDivider, View vDivider, ImageView voic5Iv, TextView voic5Tv, ImageView voicIv, TextView voicTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carCallLinearLayout = carCallLinearLayout;
        this.carCallTv = carCallTv;
        this.carNaviIv = carNaviIv;
        this.carNaviLinearLayout = carNaviLinearLayout;
        this.linearLayout4 = linearLayout4;
        this.svSound = svSound;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
        this.voic5Iv = voic5Iv;
        this.voic5Tv = voic5Tv;
        this.voicIv = voicIv;
        this.voicTv = voicTv;
    }

    public AudiMib3VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3SoundOemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_sound_oem, root, attachToRoot, component);
    }

    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundOemBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3SoundOemBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_sound_oem, null, false, component);
    }

    public static ActivityAudiMib3SoundOemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundOemBinding bind(View view, Object component) {
        return (ActivityAudiMib3SoundOemBinding) bind(component, view, C0899R.C0902layout.activity_audi_mib3_sound_oem);
    }
}
