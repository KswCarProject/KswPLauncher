package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class Id7SubCarViewBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout carConstraintLayout;
    @NonNull
    public final CustomBmwImageView carImageView;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    @NonNull
    public final TextView textView2;
    @NonNull
    public final TextView textView3;

    public abstract void setCarViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected Id7SubCarViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, CustomBmwImageView carImageView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.carImageView = carImageView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    @Nullable
    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    @NonNull
    public static Id7SubCarViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubCarViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Id7SubCarViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_car_view, root, attachToRoot, component);
    }

    @NonNull
    public static Id7SubCarViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubCarViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Id7SubCarViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_car_view, (ViewGroup) null, false, component);
    }

    public static Id7SubCarViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Id7SubCarViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Id7SubCarViewBinding) bind(component, view, R.layout.id7_sub_car_view);
    }
}
