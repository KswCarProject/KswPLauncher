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
public abstract class KswmbuxHomeThreeBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final UgHomeImageView kswmbuxHomeCarVaiw;
    public final UgHomeImageView kswmbuxHomeDashboradVaiw;
    public final UgHomeImageView kswmbuxHomeEasyVaiw;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected KswmbuxHomeThreeBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView kswmbuxHomeCarVaiw, UgHomeImageView kswmbuxHomeDashboradVaiw, UgHomeImageView kswmbuxHomeEasyVaiw) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.kswmbuxHomeCarVaiw = kswmbuxHomeCarVaiw;
        this.kswmbuxHomeDashboradVaiw = kswmbuxHomeDashboradVaiw;
        this.kswmbuxHomeEasyVaiw = kswmbuxHomeEasyVaiw;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswmbuxHomeThreeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.kswmbux_home_three, root, attachToRoot, component);
    }

    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeThreeBinding inflate(LayoutInflater inflater, Object component) {
        return (KswmbuxHomeThreeBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.kswmbux_home_three, null, false, component);
    }

    public static KswmbuxHomeThreeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeThreeBinding bind(View view, Object component) {
        return (KswmbuxHomeThreeBinding) bind(component, view, C0899R.C0902layout.kswmbux_home_three);
    }
}
