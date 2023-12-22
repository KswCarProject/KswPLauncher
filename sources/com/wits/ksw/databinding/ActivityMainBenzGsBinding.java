package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewPage;

/* loaded from: classes7.dex */
public abstract class ActivityMainBenzGsBinding extends ViewDataBinding {
    public final ImageView benzgsHomeLeftBtn;
    public final ImageView benzgsHomeRightBtn;
    public final BenzGsViewPage benzgsViewpage;
    public final ImageView controlBtn;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(BenzGsViewMoel vm);

    protected ActivityMainBenzGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView benzgsHomeLeftBtn, ImageView benzgsHomeRightBtn, BenzGsViewPage benzgsViewpage, ImageView controlBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzgsHomeLeftBtn = benzgsHomeLeftBtn;
        this.benzgsHomeRightBtn = benzgsHomeRightBtn;
        this.benzgsViewpage = benzgsViewpage;
        this.controlBtn = controlBtn;
    }

    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBenzGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_gs, root, attachToRoot, component);
    }

    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBenzGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_gs, null, false, component);
    }

    public static ActivityMainBenzGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzGsBinding bind(View view, Object component) {
        return (ActivityMainBenzGsBinding) bind(component, view, C0899R.C0902layout.activity_main_benz_gs);
    }
}
