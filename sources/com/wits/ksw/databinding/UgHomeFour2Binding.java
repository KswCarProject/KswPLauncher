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

public abstract class UgHomeFour2Binding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final UgHomeImageView ugHomeBrowserVaiw;
    @NonNull
    public final UgHomeImageView ugHomeDvrVaiw;
    @NonNull
    public final UgHomeImageView ugHomeFileVaiw;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected UgHomeFour2Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeBrowserVaiw2, UgHomeImageView ugHomeDvrVaiw2, UgHomeImageView ugHomeFileVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeBrowserVaiw = ugHomeBrowserVaiw2;
        this.ugHomeDvrVaiw = ugHomeDvrVaiw2;
        this.ugHomeFileVaiw = ugHomeFileVaiw2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static UgHomeFour2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeFour2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (UgHomeFour2Binding) DataBindingUtil.inflate(inflater, R.layout.ug_home_four2, root, attachToRoot, component);
    }

    @NonNull
    public static UgHomeFour2Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeFour2Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (UgHomeFour2Binding) DataBindingUtil.inflate(inflater, R.layout.ug_home_four2, (ViewGroup) null, false, component);
    }

    public static UgHomeFour2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UgHomeFour2Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (UgHomeFour2Binding) bind(component, view, R.layout.ug_home_four2);
    }
}
