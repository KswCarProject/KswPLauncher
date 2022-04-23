package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class AudiMib3MainLeftBinding extends ViewDataBinding {
    public final ImageView ivBtAudimib3;
    public final ImageView ivCarAudimib3;
    public final ImageView ivMusicAudimib3;
    public final ImageView ivNaviAudimib3;
    public final ImageView ivSetAudimib3;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel bcVieModel);

    protected AudiMib3MainLeftBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivBtAudimib32, ImageView ivCarAudimib32, ImageView ivMusicAudimib32, ImageView ivNaviAudimib32, ImageView ivSetAudimib32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivBtAudimib3 = ivBtAudimib32;
        this.ivCarAudimib3 = ivCarAudimib32;
        this.ivMusicAudimib3 = ivMusicAudimib32;
        this.ivNaviAudimib3 = ivNaviAudimib32;
        this.ivSetAudimib3 = ivSetAudimib32;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3MainLeftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3MainLeftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3MainLeftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_main_left, root, attachToRoot, component);
    }

    public static AudiMib3MainLeftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3MainLeftBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3MainLeftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_main_left, (ViewGroup) null, false, component);
    }

    public static AudiMib3MainLeftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3MainLeftBinding bind(View view, Object component) {
        return (AudiMib3MainLeftBinding) bind(component, view, R.layout.audi_mib3_main_left);
    }
}
