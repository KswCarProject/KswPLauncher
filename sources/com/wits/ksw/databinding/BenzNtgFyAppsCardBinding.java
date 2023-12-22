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
public abstract class BenzNtgFyAppsCardBinding extends ViewDataBinding {
    public final AppCompatImageView benzMbuxLocalCardBg;
    public final AppCompatTextView benzMbuxLocalCardContent;
    public final AppCompatTextView benzMbuxLocalCardTitle;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel ViewModel);

    protected BenzNtgFyAppsCardBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageView benzMbuxLocalCardBg, AppCompatTextView benzMbuxLocalCardContent, AppCompatTextView benzMbuxLocalCardTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxLocalCardBg = benzMbuxLocalCardBg;
        this.benzMbuxLocalCardContent = benzMbuxLocalCardContent;
        this.benzMbuxLocalCardTitle = benzMbuxLocalCardTitle;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtgFyAppsCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyAppsCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtgFyAppsCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg_fy_apps_card, root, attachToRoot, component);
    }

    public static BenzNtgFyAppsCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyAppsCardBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzNtgFyAppsCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg_fy_apps_card, null, false, component);
    }

    public static BenzNtgFyAppsCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyAppsCardBinding bind(View view, Object component) {
        return (BenzNtgFyAppsCardBinding) bind(component, view, C0899R.C0902layout.benz_ntg_fy_apps_card);
    }
}
