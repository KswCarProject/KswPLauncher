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
public abstract class UgHomeTwoBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeAppVaiw;
    public final UgHomeImageView ugHomeEasyVaiw;
    public final UgHomeImageView ugHomeHdvideoVaiw;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected UgHomeTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView ugHomeAppVaiw, UgHomeImageView ugHomeEasyVaiw, UgHomeImageView ugHomeHdvideoVaiw) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.ugHomeAppVaiw = ugHomeAppVaiw;
        this.ugHomeEasyVaiw = ugHomeEasyVaiw;
        this.ugHomeHdvideoVaiw = ugHomeHdvideoVaiw;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_two, root, attachToRoot, component);
    }

    public static UgHomeTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_two, null, false, component);
    }

    public static UgHomeTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeTwoBinding bind(View view, Object component) {
        return (UgHomeTwoBinding) bind(component, view, C0899R.C0902layout.ug_home_two);
    }
}
