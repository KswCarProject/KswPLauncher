package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi.vm.AudiSystemViewModel;

public abstract class AudiSelThirdCls extends ViewDataBinding {
    public final RecyclerView listviewMusic;
    public final RecyclerView listviewVideo;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(AudiSystemViewModel audiSystemViewModel);

    protected AudiSelThirdCls(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView listviewMusic2, RecyclerView listviewVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.listviewMusic = listviewMusic2;
        this.listviewVideo = listviewVideo2;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiSelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiSelThirdCls) ViewDataBinding.inflateInternal(inflater, R.layout.audi_sel_third_app_layout, root, attachToRoot, component);
    }

    public static AudiSelThirdCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSelThirdCls inflate(LayoutInflater inflater, Object component) {
        return (AudiSelThirdCls) ViewDataBinding.inflateInternal(inflater, R.layout.audi_sel_third_app_layout, (ViewGroup) null, false, component);
    }

    public static AudiSelThirdCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSelThirdCls bind(View view, Object component) {
        return (AudiSelThirdCls) bind(component, view, R.layout.audi_sel_third_app_layout);
    }
}
