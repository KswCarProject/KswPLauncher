package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3VolumeViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityAudiMib3SoundBinding extends ViewDataBinding {
    public final TextView carVolumeTextView;
    public final TextView hzTextView;
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3VolumeViewModel mVm;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3VolumeViewModel vm);

    protected ActivityAudiMib3SoundBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView carVolumeTextView, TextView hzTextView, ConstraintLayout linearLayout4, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carVolumeTextView = carVolumeTextView;
        this.hzTextView = hzTextView;
        this.linearLayout4 = linearLayout4;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3VolumeViewModel getVm() {
        return this.mVm;
    }

    public static ActivityAudiMib3SoundBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityAudiMib3SoundBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_sound, root, attachToRoot, component);
    }

    public static ActivityAudiMib3SoundBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityAudiMib3SoundBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_audi_mib3_sound, null, false, component);
    }

    public static ActivityAudiMib3SoundBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAudiMib3SoundBinding bind(View view, Object component) {
        return (ActivityAudiMib3SoundBinding) bind(component, view, C0899R.C0902layout.activity_audi_mib3_sound);
    }
}
