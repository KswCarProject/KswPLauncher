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
public abstract class BenzNtgFyThirdCardBinding extends ViewDataBinding {
    public final AppCompatImageView benzMbuxThirdCardBg;
    public final AppCompatTextView benzMbuxThirdCardContent;
    public final AppCompatImageView benzMbuxThirdCardIcon;
    public final AppCompatTextView benzMbuxThirdCardTitle;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel ViewModel);

    protected BenzNtgFyThirdCardBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageView benzMbuxThirdCardBg, AppCompatTextView benzMbuxThirdCardContent, AppCompatImageView benzMbuxThirdCardIcon, AppCompatTextView benzMbuxThirdCardTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxThirdCardBg = benzMbuxThirdCardBg;
        this.benzMbuxThirdCardContent = benzMbuxThirdCardContent;
        this.benzMbuxThirdCardIcon = benzMbuxThirdCardIcon;
        this.benzMbuxThirdCardTitle = benzMbuxThirdCardTitle;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtgFyThirdCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyThirdCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtgFyThirdCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg_fy_third_card, root, attachToRoot, component);
    }

    public static BenzNtgFyThirdCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyThirdCardBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzNtgFyThirdCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg_fy_third_card, null, false, component);
    }

    public static BenzNtgFyThirdCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyThirdCardBinding bind(View view, Object component) {
        return (BenzNtgFyThirdCardBinding) bind(component, view, C0899R.C0902layout.benz_ntg_fy_third_card);
    }
}
