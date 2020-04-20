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

public abstract class UgHomeThreeBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final UgHomeImageView ugHomeCarVaiw;
    @NonNull
    public final UgHomeImageView ugHomeDashboradVaiw;
    @NonNull
    public final UgHomeImageView ugHomeSettingVaiw;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected UgHomeThreeBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeCarVaiw2, UgHomeImageView ugHomeDashboradVaiw2, UgHomeImageView ugHomeSettingVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeCarVaiw = ugHomeCarVaiw2;
        this.ugHomeDashboradVaiw = ugHomeDashboradVaiw2;
        this.ugHomeSettingVaiw = ugHomeSettingVaiw2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static UgHomeThreeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeThreeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (UgHomeThreeBinding) DataBindingUtil.inflate(inflater, R.layout.ug_home_three, root, attachToRoot, component);
    }

    @NonNull
    public static UgHomeThreeBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeThreeBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (UgHomeThreeBinding) DataBindingUtil.inflate(inflater, R.layout.ug_home_three, (ViewGroup) null, false, component);
    }

    public static UgHomeThreeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UgHomeThreeBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (UgHomeThreeBinding) bind(component, view, R.layout.ug_home_three);
    }
}
