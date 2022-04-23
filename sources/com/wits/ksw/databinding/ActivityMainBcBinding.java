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
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.CustomBcItemBgImageView;

public abstract class ActivityMainBcBinding extends ViewDataBinding {
    public final CustomBcItemBgImageView CustomBcItemBgImageView;
    public final TextView appsBtn;
    public final TextView bcArrowLeftButton;
    public final TextView bcArrowRightButton;
    public final ConstraintLayout constraintLayout2;
    public final ImageView controlBtn;
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcVieModel mMBcVieModel;
    public final RecyclerView recyclerView2;

    public abstract void setMBcVieModel(BcVieModel bcVieModel);

    protected ActivityMainBcBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomBcItemBgImageView CustomBcItemBgImageView2, TextView appsBtn2, TextView bcArrowLeftButton2, TextView bcArrowRightButton2, ConstraintLayout constraintLayout22, ImageView controlBtn2, ConstraintLayout linearLayout32, RecyclerView recyclerView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.CustomBcItemBgImageView = CustomBcItemBgImageView2;
        this.appsBtn = appsBtn2;
        this.bcArrowLeftButton = bcArrowLeftButton2;
        this.bcArrowRightButton = bcArrowRightButton2;
        this.constraintLayout2 = constraintLayout22;
        this.controlBtn = controlBtn2;
        this.linearLayout3 = linearLayout32;
        this.recyclerView2 = recyclerView22;
    }

    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static ActivityMainBcBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBcBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBcBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_bc, root, attachToRoot, component);
    }

    public static ActivityMainBcBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBcBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBcBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_bc, (ViewGroup) null, false, component);
    }

    public static ActivityMainBcBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBcBinding bind(View view, Object component) {
        return (ActivityMainBcBinding) bind(component, view, R.layout.activity_main_bc);
    }
}
