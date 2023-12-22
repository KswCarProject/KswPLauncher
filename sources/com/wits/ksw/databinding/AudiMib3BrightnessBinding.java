package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3BrightnessBinding extends ViewDataBinding {
    public final CheckBox audiSystemReverCamera;
    public final SeekBar audioSeekbar;
    public final TextView audioSeekbarRightText;
    public final TextView audioSeekbarTitle;
    public final LinearLayout hzCallLinearLayout;
    public final LinearLayout hzMediaLinearLayout;
    public final AudiConstraintLayout linearLayout4;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel vm);

    protected AudiMib3BrightnessBinding(Object _bindingComponent, View _root, int _localFieldCount, CheckBox audiSystemReverCamera, SeekBar audioSeekbar, TextView audioSeekbarRightText, TextView audioSeekbarTitle, LinearLayout hzCallLinearLayout, LinearLayout hzMediaLinearLayout, AudiConstraintLayout linearLayout4, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiSystemReverCamera = audiSystemReverCamera;
        this.audioSeekbar = audioSeekbar;
        this.audioSeekbarRightText = audioSeekbarRightText;
        this.audioSeekbarTitle = audioSeekbarTitle;
        this.hzCallLinearLayout = hzCallLinearLayout;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.linearLayout4 = linearLayout4;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3BrightnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3BrightnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3BrightnessBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_brightness, root, attachToRoot, component);
    }

    public static AudiMib3BrightnessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3BrightnessBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3BrightnessBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_brightness, null, false, component);
    }

    public static AudiMib3BrightnessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3BrightnessBinding bind(View view, Object component) {
        return (AudiMib3BrightnessBinding) bind(component, view, C0899R.C0902layout.audi_mib3_brightness);
    }
}
