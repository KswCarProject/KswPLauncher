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
public abstract class BenzNtgFyDashboardCardBinding extends ViewDataBinding {
    public final AppCompatImageView benzMbuxLocalCardBg;
    public final AppCompatTextView benzMbuxLocalCardContent;
    public final AppCompatTextView benzMbuxLocalCardTitle;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel ViewModel);

    protected BenzNtgFyDashboardCardBinding(Object _bindingComponent, View _root, int _localFieldCount, AppCompatImageView benzMbuxLocalCardBg, AppCompatTextView benzMbuxLocalCardContent, AppCompatTextView benzMbuxLocalCardTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxLocalCardBg = benzMbuxLocalCardBg;
        this.benzMbuxLocalCardContent = benzMbuxLocalCardContent;
        this.benzMbuxLocalCardTitle = benzMbuxLocalCardTitle;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtgFyDashboardCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyDashboardCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtgFyDashboardCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg_fy_dashboard_card, root, attachToRoot, component);
    }

    public static BenzNtgFyDashboardCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyDashboardCardBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzNtgFyDashboardCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg_fy_dashboard_card, null, false, component);
    }

    public static BenzNtgFyDashboardCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtgFyDashboardCardBinding bind(View view, Object component) {
        return (BenzNtgFyDashboardCardBinding) bind(component, view, C0899R.C0902layout.benz_ntg_fy_dashboard_card);
    }
}
