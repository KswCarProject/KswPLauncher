package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi.p007vm.AudiSystemViewModel;

/* loaded from: classes7.dex */
public abstract class AudiSelThirdCls extends ViewDataBinding {
    public final RecyclerView listviewMusic;
    public final RecyclerView listviewVideo;
    @Bindable
    protected AudiSystemViewModel mVm;

    public abstract void setVm(AudiSystemViewModel vm);

    protected AudiSelThirdCls(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView listviewMusic, RecyclerView listviewVideo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.listviewMusic = listviewMusic;
        this.listviewVideo = listviewVideo;
    }

    public AudiSystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiSelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiSelThirdCls) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_sel_third_app_layout, root, attachToRoot, component);
    }

    public static AudiSelThirdCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSelThirdCls inflate(LayoutInflater inflater, Object component) {
        return (AudiSelThirdCls) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_sel_third_app_layout, null, false, component);
    }

    public static AudiSelThirdCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiSelThirdCls bind(View view, Object component) {
        return (AudiSelThirdCls) bind(component, view, C0899R.C0902layout.audi_sel_third_app_layout);
    }
}
