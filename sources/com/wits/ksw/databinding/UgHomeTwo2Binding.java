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

public abstract class UgHomeTwo2Binding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeAppVaiw;
    public final UgHomeImageView ugHomeEasyVaiw;
    public final UgHomeImageView ugHomeHdvideoVaiw;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected UgHomeTwo2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeAppVaiw2, UgHomeImageView ugHomeEasyVaiw2, UgHomeImageView ugHomeHdvideoVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeAppVaiw = ugHomeAppVaiw2;
        this.ugHomeEasyVaiw = ugHomeEasyVaiw2;
        this.ugHomeHdvideoVaiw = ugHomeHdvideoVaiw2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeTwo2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwo2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeTwo2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_two2, root, attachToRoot, component);
    }

    public static UgHomeTwo2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwo2Binding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeTwo2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_two2, (ViewGroup) null, false, component);
    }

    public static UgHomeTwo2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwo2Binding bind(View view, Object component) {
        return (UgHomeTwo2Binding) bind(component, view, R.layout.ug_home_two2);
    }
}
