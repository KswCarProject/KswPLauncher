package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SettingViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiMib3SettingViewModel vm);

    protected AudiMib3PasswordBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView audiKey0, ImageView audiKey1, ImageView audiKey2, ImageView audiKey3, ImageView audiKey4, ImageView audiKey5, ImageView audiKey6, ImageView audiKey7, ImageView audiKey8, ImageView audiKey9, ImageView audiKeyDelete, ImageView audiKeyOk, GridLayout audioViewPager, LinearLayoutCompat linearLayout4) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiKey0 = audiKey0;
        this.audiKey1 = audiKey1;
        this.audiKey2 = audiKey2;
        this.audiKey3 = audiKey3;
        this.audiKey4 = audiKey4;
        this.audiKey5 = audiKey5;
        this.audiKey6 = audiKey6;
        this.audiKey7 = audiKey7;
        this.audiKey8 = audiKey8;
        this.audiKey9 = audiKey9;
        this.audiKeyDelete = audiKeyDelete;
        this.audiKeyOk = audiKeyOk;
        this.audioViewPager = audioViewPager;
        this.linearLayout4 = linearLayout4;
    }

    public AudiMib3SettingViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3PasswordBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_password, root, attachToRoot, component);
    }

    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3PasswordBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3PasswordBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_password, null, false, component);
    }

    public static AudiMib3PasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3PasswordBinding bind(View view, Object component) {
        return (AudiMib3PasswordBinding) bind(component, view, C0899R.C0902layout.audi_mib3_password);
    }
}
