package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class ActivityMainBenzMbuxBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView benzMbuxRecyclerView;
    @NonNull
    public final ImageView controlBtn;
    @NonNull
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcVieModel mVieModel;

    public abstract void setVieModel(@Nullable BcVieModel bcVieModel);

    protected ActivityMainBenzMbuxBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, RecyclerView benzMbuxRecyclerView2, ImageView controlBtn2, ConstraintLayout linearLayout32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxRecyclerView = benzMbuxRecyclerView2;
        this.controlBtn = controlBtn2;
        this.linearLayout3 = linearLayout32;
    }

    @Nullable
    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    @NonNull
    public static ActivityMainBenzMbuxBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBenzMbuxBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzMbuxBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_benz_mbux, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainBenzMbuxBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBenzMbuxBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzMbuxBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_benz_mbux, (ViewGroup) null, false, component);
    }

    public static ActivityMainBenzMbuxBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainBenzMbuxBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzMbuxBinding) bind(component, view, R.layout.activity_main_benz_mbux);
    }
}
