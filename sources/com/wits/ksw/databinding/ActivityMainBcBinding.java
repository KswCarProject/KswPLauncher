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
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.CustomBcItemBgImageView;

/* loaded from: classes7.dex */
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

    public abstract void setMBcVieModel(BcVieModel mBcVieModel);

    protected ActivityMainBcBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomBcItemBgImageView CustomBcItemBgImageView, TextView appsBtn, TextView bcArrowLeftButton, TextView bcArrowRightButton, ConstraintLayout constraintLayout2, ImageView controlBtn, ConstraintLayout linearLayout3, RecyclerView recyclerView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.CustomBcItemBgImageView = CustomBcItemBgImageView;
        this.appsBtn = appsBtn;
        this.bcArrowLeftButton = bcArrowLeftButton;
        this.bcArrowRightButton = bcArrowRightButton;
        this.constraintLayout2 = constraintLayout2;
        this.controlBtn = controlBtn;
        this.linearLayout3 = linearLayout3;
        this.recyclerView2 = recyclerView2;
    }

    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static ActivityMainBcBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBcBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBcBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_bc, root, attachToRoot, component);
    }

    public static ActivityMainBcBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBcBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBcBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_bc, null, false, component);
    }

    public static ActivityMainBcBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBcBinding bind(View view, Object component) {
        return (ActivityMainBcBinding) bind(component, view, C0899R.C0902layout.activity_main_bc);
    }
}
