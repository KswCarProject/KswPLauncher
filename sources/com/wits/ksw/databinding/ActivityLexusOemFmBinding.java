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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.lexus.LexusOEMFMViewModel;
import com.wits.ksw.launcher.view.lexus.LexusUiParams;

public abstract class ActivityLexusOemFmBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout acLayout;
    @NonNull
    public final TextView asl;
    @NonNull
    public final ImageView blowMode;
    @NonNull
    public final ImageView cd1;
    @NonNull
    public final ImageView cd2;
    @NonNull
    public final ImageView cd3;
    @NonNull
    public final ImageView cd4;
    @NonNull
    public final ImageView cd5;
    @NonNull
    public final ImageView cd6;
    @NonNull
    public final RelativeLayout cdLayout;
    @NonNull
    public final TextView discNum;
    @NonNull
    public final TextView fmBand;
    @NonNull
    public final TextView fmCh;
    @NonNull
    public final TextView fmFrequency;
    @NonNull
    public final RelativeLayout fmLayout;
    @Bindable
    protected LexusUiParams mMUiParams;
    @Bindable
    protected LexusOEMFMViewModel mVm;
    @NonNull
    public final TextView modeFm;
    @NonNull
    public final TextView st;
    @NonNull
    public final TextView time;
    @NonNull
    public final TextView trackNum;
    @NonNull
    public final TextView tvLeftTemperature;
    @NonNull
    public final TextView tvRightTemperature;
    @NonNull
    public final ImageView windSpeedLevel;

    public abstract void setMUiParams(@Nullable LexusUiParams lexusUiParams);

    public abstract void setVm(@Nullable LexusOEMFMViewModel lexusOEMFMViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityLexusOemFmBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, RelativeLayout acLayout2, TextView asl2, ImageView blowMode2, ImageView cd12, ImageView cd22, ImageView cd32, ImageView cd42, ImageView cd52, ImageView cd62, RelativeLayout cdLayout2, TextView discNum2, TextView fmBand2, TextView fmCh2, TextView fmFrequency2, RelativeLayout fmLayout2, TextView modeFm2, TextView st2, TextView time2, TextView trackNum2, TextView tvLeftTemperature2, TextView tvRightTemperature2, ImageView windSpeedLevel2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.acLayout = acLayout2;
        this.asl = asl2;
        this.blowMode = blowMode2;
        this.cd1 = cd12;
        this.cd2 = cd22;
        this.cd3 = cd32;
        this.cd4 = cd42;
        this.cd5 = cd52;
        this.cd6 = cd62;
        this.cdLayout = cdLayout2;
        this.discNum = discNum2;
        this.fmBand = fmBand2;
        this.fmCh = fmCh2;
        this.fmFrequency = fmFrequency2;
        this.fmLayout = fmLayout2;
        this.modeFm = modeFm2;
        this.st = st2;
        this.time = time2;
        this.trackNum = trackNum2;
        this.tvLeftTemperature = tvLeftTemperature2;
        this.tvRightTemperature = tvRightTemperature2;
        this.windSpeedLevel = windSpeedLevel2;
    }

    @Nullable
    public LexusOEMFMViewModel getVm() {
        return this.mVm;
    }

    @Nullable
    public LexusUiParams getMUiParams() {
        return this.mMUiParams;
    }

    @NonNull
    public static ActivityLexusOemFmBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLexusOemFmBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityLexusOemFmBinding) DataBindingUtil.inflate(inflater, R.layout.activity_lexus_oem_fm, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityLexusOemFmBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLexusOemFmBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityLexusOemFmBinding) DataBindingUtil.inflate(inflater, R.layout.activity_lexus_oem_fm, (ViewGroup) null, false, component);
    }

    public static ActivityLexusOemFmBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityLexusOemFmBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityLexusOemFmBinding) bind(component, view, R.layout.activity_lexus_oem_fm);
    }
}
