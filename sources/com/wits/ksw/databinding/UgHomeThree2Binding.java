package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgHomeImageView;

/* loaded from: classes7.dex */
public abstract class UgHomeThree2Binding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeCarVaiw;
    public final UgHomeImageView ugHomeDashboradVaiw;
    public final UgHomeImageView ugHomeSettingVaiw;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected UgHomeThree2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView ugHomeCarVaiw, UgHomeImageView ugHomeDashboradVaiw, UgHomeImageView ugHomeSettingVaiw) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.ugHomeCarVaiw = ugHomeCarVaiw;
        this.ugHomeDashboradVaiw = ugHomeDashboradVaiw;
        this.ugHomeSettingVaiw = ugHomeSettingVaiw;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeThree2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeThree2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeThree2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_three2, root, attachToRoot, component);
    }

    public static UgHomeThree2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeThree2Binding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeThree2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_three2, null, false, component);
    }

    public static UgHomeThree2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeThree2Binding bind(View view, Object component) {
        return (UgHomeThree2Binding) bind(component, view, C0899R.C0902layout.ug_home_three2);
    }
}
