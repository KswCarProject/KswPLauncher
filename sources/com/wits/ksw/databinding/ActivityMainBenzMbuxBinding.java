package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class ActivityMainBenzMbuxBinding extends ViewDataBinding {
    public final RecyclerView benzMbuxRecyclerView;
    public final ImageView controlBtn;
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcVieModel mVieModel;

    public abstract void setVieModel(BcVieModel bcVieModel);

    protected ActivityMainBenzMbuxBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView benzMbuxRecyclerView2, ImageView controlBtn2, ConstraintLayout linearLayout32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxRecyclerView = benzMbuxRecyclerView2;
        this.controlBtn = controlBtn2;
        this.linearLayout3 = linearLayout32;
    }

    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBenzMbuxBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_benz_mbux, root, attachToRoot, component);
    }

    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBenzMbuxBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_benz_mbux, (ViewGroup) null, false, component);
    }

    public static ActivityMainBenzMbuxBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzMbuxBinding bind(View view, Object component) {
        return (ActivityMainBenzMbuxBinding) bind(component, view, R.layout.activity_main_benz_mbux);
    }
}
