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

public abstract class UgHomeThree2Binding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeCarVaiw;
    public final UgHomeImageView ugHomeDashboradVaiw;
    public final UgHomeImageView ugHomeSettingVaiw;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected UgHomeThree2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeCarVaiw2, UgHomeImageView ugHomeDashboradVaiw2, UgHomeImageView ugHomeSettingVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeCarVaiw = ugHomeCarVaiw2;
        this.ugHomeDashboradVaiw = ugHomeDashboradVaiw2;
        this.ugHomeSettingVaiw = ugHomeSettingVaiw2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeThree2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeThree2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeThree2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_three2, root, attachToRoot, component);
    }

    public static UgHomeThree2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeThree2Binding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeThree2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_three2, (ViewGroup) null, false, component);
    }

    public static UgHomeThree2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeThree2Binding bind(View view, Object component) {
        return (UgHomeThree2Binding) bind(component, view, R.layout.ug_home_three2);
    }
}
