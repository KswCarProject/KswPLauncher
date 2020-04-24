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
import android.widget.GridLayout;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSettingViewModel;
import com.wits.ksw.settings.audi.widget.DateView;

public abstract class AudiPasswordBinding extends ViewDataBinding {
    @NonNull
    public final ImageView audiKey0;
    @NonNull
    public final ImageView audiKey1;
    @NonNull
    public final ImageView audiKey2;
    @NonNull
    public final ImageView audiKey3;
    @NonNull
    public final ImageView audiKey4;
    @NonNull
    public final ImageView audiKey5;
    @NonNull
    public final ImageView audiKey6;
    @NonNull
    public final ImageView audiKey7;
    @NonNull
    public final ImageView audiKey8;
    @NonNull
    public final ImageView audiKey9;
    @NonNull
    public final ImageView audiKeyDelete;
    @NonNull
    public final ImageView audiKeyOk;
    @NonNull
    public final GridLayout audioViewPager;
    @NonNull
    public final DateView bottomFrameLayout;
    @NonNull
    public final ConstraintLayout linearLayout4;
    @Bindable
    protected AudiSettingViewModel mVm;

    public abstract void setVm(@Nullable AudiSettingViewModel audiSettingViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiPasswordBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView audiKey02, ImageView audiKey12, ImageView audiKey22, ImageView audiKey32, ImageView audiKey42, ImageView audiKey52, ImageView audiKey62, ImageView audiKey72, ImageView audiKey82, ImageView audiKey92, ImageView audiKeyDelete2, ImageView audiKeyOk2, GridLayout audioViewPager2, DateView bottomFrameLayout2, ConstraintLayout linearLayout42) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiKey0 = audiKey02;
        this.audiKey1 = audiKey12;
        this.audiKey2 = audiKey22;
        this.audiKey3 = audiKey32;
        this.audiKey4 = audiKey42;
        this.audiKey5 = audiKey52;
        this.audiKey6 = audiKey62;
        this.audiKey7 = audiKey72;
        this.audiKey8 = audiKey82;
        this.audiKey9 = audiKey92;
        this.audiKeyDelete = audiKeyDelete2;
        this.audiKeyOk = audiKeyOk2;
        this.audioViewPager = audioViewPager2;
        this.bottomFrameLayout = bottomFrameLayout2;
        this.linearLayout4 = linearLayout42;
    }

    @Nullable
    public AudiSettingViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiPasswordBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiPasswordBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiPasswordBinding) DataBindingUtil.inflate(inflater, R.layout.audi_password, root, attachToRoot, component);
    }

    @NonNull
    public static AudiPasswordBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiPasswordBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiPasswordBinding) DataBindingUtil.inflate(inflater, R.layout.audi_password, (ViewGroup) null, false, component);
    }

    public static AudiPasswordBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiPasswordBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiPasswordBinding) bind(component, view, R.layout.audi_password);
    }
}
