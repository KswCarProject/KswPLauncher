package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class ActivityMainBenzMbuxBinding extends ViewDataBinding {
    public final RecyclerView benzMbuxRecyclerView;
    public final ImageView controlBtn;
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcVieModel mVieModel;

    public abstract void setVieModel(BcVieModel vieModel);

    protected ActivityMainBenzMbuxBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView benzMbuxRecyclerView, ImageView controlBtn, ConstraintLayout linearLayout3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxRecyclerView = benzMbuxRecyclerView;
        this.controlBtn = controlBtn;
        this.linearLayout3 = linearLayout3;
    }

    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBenzMbuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_mbux, root, attachToRoot, component);
    }

    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzMbuxBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBenzMbuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_mbux, null, false, component);
    }

    public static ActivityMainBenzMbuxBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBenzMbuxBinding bind(View view, Object component) {
        return (ActivityMainBenzMbuxBinding) bind(component, view, C0899R.C0902layout.activity_main_benz_mbux);
    }
}
