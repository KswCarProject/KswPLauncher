package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatImageView;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class BenzMbuxThirdCardBinding extends ViewDataBinding {
    public final AppCompatImageView benzMbuxThirdCardBg;
    public final AppCompatTextView benzMbuxThirdCardContent;
    public final AppCompatImageView benzMbuxThirdCardIcon;
    public final AppCompatTextView benzMbuxThirdCardTitle;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel ViewModel);

    protected BenzMbuxThirdCardBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageView benzMbuxThirdCardBg, AppCompatTextView benzMbuxThirdCardContent, AppCompatImageView benzMbuxThirdCardIcon, AppCompatTextView benzMbuxThirdCardTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxThirdCardBg = benzMbuxThirdCardBg;
        this.benzMbuxThirdCardContent = benzMbuxThirdCardContent;
        this.benzMbuxThirdCardIcon = benzMbuxThirdCardIcon;
        this.benzMbuxThirdCardTitle = benzMbuxThirdCardTitle;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzMbuxThirdCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxThirdCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbuxThirdCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_third_card, root, attachToRoot, component);
    }

    public static BenzMbuxThirdCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxThirdCardBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbuxThirdCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_third_card, null, false, component);
    }

    public static BenzMbuxThirdCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxThirdCardBinding bind(View view, Object component) {
        return (BenzMbuxThirdCardBinding) bind(component, view, C0899R.C0902layout.benz_mbux_third_card);
    }
}
