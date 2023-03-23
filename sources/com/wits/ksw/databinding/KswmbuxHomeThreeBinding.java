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

public abstract class KswmbuxHomeThreeBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final UgHomeImageView kswmbuxHomeCarVaiw;
    public final UgHomeImageView kswmbuxHomeDashboradVaiw;
    public final UgHomeImageView kswmbuxHomeEasyVaiw;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected KswmbuxHomeThreeBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView kswmbuxHomeCarVaiw2, UgHomeImageView kswmbuxHomeDashboradVaiw2, UgHomeImageView kswmbuxHomeEasyVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.kswmbuxHomeCarVaiw = kswmbuxHomeCarVaiw2;
        this.kswmbuxHomeDashboradVaiw = kswmbuxHomeDashboradVaiw2;
        this.kswmbuxHomeEasyVaiw = kswmbuxHomeEasyVaiw2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswmbuxHomeThreeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.kswmbux_home_three, root, attachToRoot, component);
    }

    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater, Object component) {
        return (KswmbuxHomeThreeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.kswmbux_home_three, (ViewGroup) null, false, component);
    }

    public static KswmbuxHomeThreeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeThreeBinding bind(View view, Object component) {
        return (KswmbuxHomeThreeBinding) bind(component, view, R.layout.kswmbux_home_three);
    }
}
