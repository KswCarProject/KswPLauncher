package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewPage;

public abstract class ActivityMainBenzGsBinding extends ViewDataBinding {
    public final ImageView benzgsHomeLeftBtn;
    public final ImageView benzgsHomeRightBtn;
    public final BenzGsViewPage benzgsViewpage;
    public final ImageView controlBtn;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(BenzGsViewMoel benzGsViewMoel);

    protected ActivityMainBenzGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView benzgsHomeLeftBtn2, ImageView benzgsHomeRightBtn2, BenzGsViewPage benzgsViewpage2, ImageView controlBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzgsHomeLeftBtn = benzgsHomeLeftBtn2;
        this.benzgsHomeRightBtn = benzgsHomeRightBtn2;
        this.benzgsViewpage = benzgsViewpage2;
        this.controlBtn = controlBtn2;
    }

    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBenzGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_benz_gs, root, attachToRoot, component);
    }

    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzGsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBenzGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_benz_gs, (ViewGroup) null, false, component);
    }

    public static ActivityMainBenzGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzGsBinding bind(View view, Object component) {
        return (ActivityMainBenzGsBinding) bind(component, view, R.layout.activity_main_benz_gs);
    }
}
