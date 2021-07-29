package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgHomeImageView;

public abstract class UgHomeTwoBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeAppVaiw;
    public final UgHomeImageView ugHomeEasyVaiw;
    public final UgHomeImageView ugHomeHdvideoVaiw;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected UgHomeTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeAppVaiw2, UgHomeImageView ugHomeEasyVaiw2, UgHomeImageView ugHomeHdvideoVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeAppVaiw = ugHomeAppVaiw2;
        this.ugHomeEasyVaiw = ugHomeEasyVaiw2;
        this.ugHomeHdvideoVaiw = ugHomeHdvideoVaiw2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_two, root, attachToRoot, component);
    }

    public static UgHomeTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_two, (ViewGroup) null, false, component);
    }

    public static UgHomeTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwoBinding bind(View view, Object component) {
        return (UgHomeTwoBinding) bind(component, view, R.layout.ug_home_two);
    }
}
