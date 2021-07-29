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

public abstract class UgHomeFour2Binding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeBrowserVaiw;
    public final UgHomeImageView ugHomeDvrVaiw;
    public final UgHomeImageView ugHomeFileVaiw;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected UgHomeFour2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeBrowserVaiw2, UgHomeImageView ugHomeDvrVaiw2, UgHomeImageView ugHomeFileVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeBrowserVaiw = ugHomeBrowserVaiw2;
        this.ugHomeDvrVaiw = ugHomeDvrVaiw2;
        this.ugHomeFileVaiw = ugHomeFileVaiw2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeFour2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeFour2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeFour2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_four2, root, attachToRoot, component);
    }

    public static UgHomeFour2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeFour2Binding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeFour2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_four2, (ViewGroup) null, false, component);
    }

    public static UgHomeFour2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeFour2Binding bind(View view, Object component) {
        return (UgHomeFour2Binding) bind(component, view, R.layout.ug_home_four2);
    }
}
