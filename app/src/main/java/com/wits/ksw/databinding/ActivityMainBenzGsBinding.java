package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewMoel;
import com.wits.ksw.launcher.view.benzgs.BenzGsViewPage;

public abstract class ActivityMainBenzGsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView benzgsHomeLeftBtn;
    @NonNull
    public final ImageView benzgsHomeRightBtn;
    @NonNull
    public final BenzGsViewPage benzgsViewpage;
    @NonNull
    public final ImageView controlBtn;
    @Bindable
    protected BenzGsViewMoel mVm;

    public abstract void setVm(@Nullable BenzGsViewMoel benzGsViewMoel);

    protected ActivityMainBenzGsBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView benzgsHomeLeftBtn2, ImageView benzgsHomeRightBtn2, BenzGsViewPage benzgsViewpage2, ImageView controlBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzgsHomeLeftBtn = benzgsHomeLeftBtn2;
        this.benzgsHomeRightBtn = benzgsHomeRightBtn2;
        this.benzgsViewpage = benzgsViewpage2;
        this.controlBtn = controlBtn2;
    }

    @Nullable
    public BenzGsViewMoel getVm() {
        return this.mVm;
    }

    @NonNull
    public static ActivityMainBenzGsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBenzGsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzGsBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_benz_gs, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainBenzGsBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBenzGsBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzGsBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_benz_gs, (ViewGroup) null, false, component);
    }

    public static ActivityMainBenzGsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainBenzGsBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzGsBinding) bind(component, view, R.layout.activity_main_benz_gs);
    }
}
