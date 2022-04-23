package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SettingViewModel;

public abstract class AudiMib3PasswordBinding extends ViewDataBinding {
    public final ImageView audiKey0;
    public final ImageView audiKey1;
    public final ImageView audiKey2;
    public final ImageView audiKey3;
    public final ImageView audiKey4;
    public final ImageView audiKey5;
    public final ImageView audiKey6;
    public final ImageView audiKey7;
    public final ImageView audiKey8;
    public final ImageView audiKey9;
    public final ImageView audiKeyDelete;
    public final ImageView audiKeyOk;
    public final GridLayout audioViewPager;
    public final LinearLayoutCompat linearLayout4;
    @Bindable
    protected AudiMib3SettingViewModel mVm;

    public abstract void setVm(AudiMib3SettingViewModel audiMib3SettingViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3PasswordBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView audiKey02, ImageView audiKey12, ImageView audiKey22, ImageView audiKey32, ImageView audiKey42, ImageView audiKey52, ImageView audiKey62, ImageView audiKey72, ImageView audiKey82, ImageView audiKey92, ImageView audiKeyDelete2, ImageView audiKeyOk2, GridLayout audioViewPager2, LinearLayoutCompat linearLayout42) {
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
        this.linearLayout4 = linearLayout42;
    }

    public AudiMib3SettingViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3PasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_password, root, attachToRoot, component);
    }

    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3PasswordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_password, (ViewGroup) null, false, component);
    }

    public static AudiMib3PasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3PasswordBinding bind(View view, Object component) {
        return (AudiMib3PasswordBinding) bind(component, view, R.layout.audi_mib3_password);
    }
}
