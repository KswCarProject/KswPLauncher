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

public abstract class UgHomeOneBinding extends ViewDataBinding {
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

    protected UgHomeOneBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeBtVaiw2, UgHomeImageView ugHomeMusicVaiw2, UgHomeImageView ugHomeNaviVaiw2) {
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
    public static UgHomeOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (UgHomeOneBinding) DataBindingUtil.inflate(inflater, R.layout.ug_home_one, root, attachToRoot, component);
    }

    @NonNull
    public static UgHomeOneBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static UgHomeOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (UgHomeOneBinding) DataBindingUtil.inflate(inflater, R.layout.ug_home_one, (ViewGroup) null, false, component);
    }

    public static UgHomeOneBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UgHomeOneBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (UgHomeOneBinding) bind(component, view, R.layout.ug_home_one);
    }
}
