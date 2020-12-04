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

public abstract class UgHomeOne2Binding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final UgHomeImageView ugHomeBtVaiw;
    @NonNull
    public final UgHomeImageView ugHomeMusicVaiw;
    @NonNull
    public final UgHomeImageView ugHomeNaviVaiw;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected UgHomeOne2Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeBtVaiw2, UgHomeImageView ugHomeMusicVaiw2, UgHomeImageView ugHomeNaviVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeBtVaiw = ugHomeBtVaiw2;
        this.ugHomeMusicVaiw = ugHomeMusicVaiw2;
        this.ugHomeNaviVaiw = ugHomeNaviVaiw2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static UgHomeOne2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeOne2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (UgHomeOne2Binding) DataBindingUtil.inflate(inflater, R.layout.ug_home_one2, root, attachToRoot, component);
    }

    @NonNull
    public static UgHomeOne2Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeOne2Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (UgHomeOne2Binding) DataBindingUtil.inflate(inflater, R.layout.ug_home_one2, (ViewGroup) null, false, component);
    }

    public static UgHomeOne2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UgHomeOne2Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (UgHomeOne2Binding) bind(component, view, R.layout.ug_home_one2);
    }
}
