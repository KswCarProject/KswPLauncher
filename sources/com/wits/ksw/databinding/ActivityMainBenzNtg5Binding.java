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
import com.wits.ksw.launcher.model.BcNTG5ViewModel;

public abstract class ActivityMainBenzNtg5Binding extends ViewDataBinding {
    @NonNull
    public final ImageView controlBtn;
    @NonNull
    public final ConstraintLayout linearLayout3;
    @Bindable
    protected BcNTG5ViewModel mMBcVieModel;
    @NonNull
    public final RecyclerView recyclerView2;

    public abstract void setMBcVieModel(@Nullable BcNTG5ViewModel bcNTG5ViewModel);

    protected ActivityMainBenzNtg5Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView controlBtn2, ConstraintLayout linearLayout32, RecyclerView recyclerView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.controlBtn = controlBtn2;
        this.linearLayout3 = linearLayout32;
        this.recyclerView2 = recyclerView22;
    }

    @Nullable
    public BcNTG5ViewModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    @NonNull
    public static ActivityMainBenzNtg5Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBenzNtg5Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzNtg5Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_benz_ntg5, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainBenzNtg5Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainBenzNtg5Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzNtg5Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_benz_ntg5, (ViewGroup) null, false, component);
    }

    public static ActivityMainBenzNtg5Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainBenzNtg5Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainBenzNtg5Binding) bind(component, view, R.layout.activity_main_benz_ntg5);
    }
}
