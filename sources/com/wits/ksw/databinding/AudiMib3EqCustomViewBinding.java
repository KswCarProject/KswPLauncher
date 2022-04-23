package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.widget.AudiConstraintLayout;
import com.wits.ksw.settings.audi.widget.EqSeekBar;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3EQViewModel;

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

    public abstract void setVm(AudiMib3EQViewModel audiMib3EQViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3EqCustomViewBinding(Object _bindingComponent, View _root, int _localFieldCount, EqSeekBar bassSeekBar2, TextView bassTv2, ImageView eqText7Iv2, TextView eqText7Tv2, ImageView eqText8Iv2, TextView eqText8Tv2, ImageView eqText9Iv2, TextView eqText9Tv2, View hzMediaLinearLayout2, AudiConstraintLayout linearLayout42, View linearLayout52, View linearLayout62, EqSeekBar mezzoSeekBar2, TextView mezzoTv2, AppCompatTextView title2, View titleDivider2, EqSeekBar trebleSeekBar2, TextView trebleTv2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bassSeekBar = bassSeekBar2;
        this.bassTv = bassTv2;
        this.eqText7Iv = eqText7Iv2;
        this.eqText7Tv = eqText7Tv2;
        this.eqText8Iv = eqText8Iv2;
        this.eqText8Tv = eqText8Tv2;
        this.eqText9Iv = eqText9Iv2;
        this.eqText9Tv = eqText9Tv2;
        this.hzMediaLinearLayout = hzMediaLinearLayout2;
        this.linearLayout4 = linearLayout42;
        this.linearLayout5 = linearLayout52;
        this.linearLayout6 = linearLayout62;
        this.mezzoSeekBar = mezzoSeekBar2;
        this.mezzoTv = mezzoTv2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.trebleSeekBar = trebleSeekBar2;
        this.trebleTv = trebleTv2;
        this.vDivider = vDivider2;
    }

    public AudiMib3EQViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3EqCustomViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_eq_custom_view, root, attachToRoot, component);
    }

    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqCustomViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3EqCustomViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_eq_custom_view, (ViewGroup) null, false, component);
    }

    public static AudiMib3EqCustomViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3EqCustomViewBinding bind(View view, Object component) {
        return (AudiMib3EqCustomViewBinding) bind(component, view, R.layout.audi_mib3_eq_custom_view);
    }
}
