package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi.widget.EqSeekBar;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3EQViewModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3EqCustomViewBinding extends ViewDataBinding {
    public final EqSeekBar bassSeekBar;
    public final TextView bassTv;
    public final ImageView eqText7Iv;
    public final TextView eqText7Tv;
    public final ImageView eqText8Iv;
    public final TextView eqText8Tv;
    public final ImageView eqText9Iv;
    public final TextView eqText9Tv;
    public final View hzMediaLinearLayout;
    public final AudiConstraintLayout linearLayout4;
    public final View linearLayout5;
    public final View linearLayout6;
    @Bindable
    protected AudiMib3EQViewModel mVm;
    public final EqSeekBar mezzoSeekBar;
    public final TextView mezzoTv;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final EqSeekBar trebleSeekBar;
    public final TextView trebleTv;
    public final View vDivider;

    public abstract void setVm(AudiMib3EQViewModel vm);

    protected AudiMib3EqCustomViewBinding(Object _bindingComponent, View _root, int _localFieldCount, EqSeekBar bassSeekBar, TextView bassTv, ImageView eqText7Iv, TextView eqText7Tv, ImageView eqText8Iv, TextView eqText8Tv, ImageView eqText9Iv, TextView eqText9Tv, View hzMediaLinearLayout, AudiConstraintLayout linearLayout4, View linearLayout5, View linearLayout6, EqSeekBar mezzoSeekBar, TextView mezzoTv, AppCompatTextView title, View titleDivider, EqSeekBar trebleSeekBar, TextView trebleTv, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bassSeekBar = bassSeekBar;
        this.bassTv = bassTv;
        this.eqText7Iv = eqText7Iv;
        this.eqText7Tv = eqText7Tv;
        this.eqText8Iv = eqText8Iv;
        this.eqText8Tv = eqText8Tv;
        this.eqText9Iv = eqText9Iv;
        this.eqText9Tv = eqText9Tv;
        this.hzMediaLinearLayout = hzMediaLinearLayout;
        this.linearLayout4 = linearLayout4;
        this.linearLayout5 = linearLayout5;
        this.linearLayout6 = linearLayout6;
        this.mezzoSeekBar = mezzoSeekBar;
        this.mezzoTv = mezzoTv;
        this.title = title;
        this.titleDivider = titleDivider;
        this.trebleSeekBar = trebleSeekBar;
        this.trebleTv = trebleTv;
        this.vDivider = vDivider;
    }

    public AudiMib3EQViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3EqCustomViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_eq_custom_view, root, attachToRoot, component);
    }

    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3EqCustomViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_eq_custom_view, null, false, component);
    }

    public static AudiMib3EqCustomViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqCustomViewBinding bind(View view, Object component) {
        return (AudiMib3EqCustomViewBinding) bind(component, view, C0899R.C0902layout.audi_mib3_eq_custom_view);
    }
}
