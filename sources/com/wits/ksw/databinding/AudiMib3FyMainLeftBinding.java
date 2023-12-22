package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class AudiMib3FyMainLeftBinding extends ViewDataBinding {
    public final ImageView ivBtAudimib3;
    public final ImageView ivCarAudimib3;
    public final ImageView ivMusicAudimib3;
    public final ImageView ivNaviAudimib3;
    public final ImageView ivSetAudimib3;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3FyMainLeftBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivBtAudimib3, ImageView ivCarAudimib3, ImageView ivMusicAudimib3, ImageView ivNaviAudimib3, ImageView ivSetAudimib3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivBtAudimib3 = ivBtAudimib3;
        this.ivCarAudimib3 = ivCarAudimib3;
        this.ivMusicAudimib3 = ivMusicAudimib3;
        this.ivNaviAudimib3 = ivNaviAudimib3;
        this.ivSetAudimib3 = ivSetAudimib3;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3FyMainLeftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyMainLeftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3FyMainLeftBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_fy_main_left, root, attachToRoot, component);
    }

    public static AudiMib3FyMainLeftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyMainLeftBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3FyMainLeftBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_fy_main_left, null, false, component);
    }

    public static AudiMib3FyMainLeftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyMainLeftBinding bind(View view, Object component) {
        return (AudiMib3FyMainLeftBinding) bind(component, view, C0899R.C0902layout.audi_mib3_fy_main_left);
    }
}
