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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgHomeImageView;

public abstract class UgHomeTwoBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final UgHomeImageView ugHomeAppVaiw;
    @NonNull
    public final UgHomeImageView ugHomeEasyVaiw;
    @NonNull
    public final UgHomeImageView ugHomeHdvideoVaiw;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected UgHomeTwoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeAppVaiw2, UgHomeImageView ugHomeEasyVaiw2, UgHomeImageView ugHomeHdvideoVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeAppVaiw = ugHomeAppVaiw2;
        this.ugHomeEasyVaiw = ugHomeEasyVaiw2;
        this.ugHomeHdvideoVaiw = ugHomeHdvideoVaiw2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static UgHomeTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (UgHomeTwoBinding) DataBindingUtil.inflate(inflater, R.layout.ug_home_two, root, attachToRoot, component);
    }

    @NonNull
    public static UgHomeTwoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeTwoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (UgHomeTwoBinding) DataBindingUtil.inflate(inflater, R.layout.ug_home_two, (ViewGroup) null, false, component);
    }

    public static UgHomeTwoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UgHomeTwoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (UgHomeTwoBinding) bind(component, view, R.layout.ug_home_two);
    }
}
