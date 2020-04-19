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
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.CustomBcItemBgImageView;

public abstract class ActivityMainBcBinding extends ViewDataBinding {
    @NonNull
    public final CustomBcItemBgImageView CustomBcItemBgImageView;
    @NonNull
    public final TextView appsBtn;
    @Nullable
    public final TextView bcArrowLeftButton;
    @Nullable
    public final TextView bcArrowRightButton;
    @NonNull
    public final ConstraintLayout constraintLayout2;
    @NonNull
    public final ImageView controlBtn;
    @NonNull
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcVieModel mMBcVieModel;
    @NonNull
    public final RecyclerView recyclerView2;

    public abstract void setMBcVieModel(@Nullable BcVieModel bcVieModel);

    protected ActivityMainBcBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, CustomBcItemBgImageView CustomBcItemBgImageView2, TextView appsBtn2, TextView bcArrowLeftButton2, TextView bcArrowRightButton2, ConstraintLayout constraintLayout22, ImageView controlBtn2, ConstraintLayout linearLayout32, RecyclerView recyclerView22) {
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

    @Nullable
    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    @NonNull
    public static ActivityMainBcBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBcBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainBcBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_bc, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainBcBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBcBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainBcBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_bc, (ViewGroup) null, false, component);
    }

    public static ActivityMainBcBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainBcBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainBcBinding) bind(component, view, R.layout.activity_main_bc);
    }
}
