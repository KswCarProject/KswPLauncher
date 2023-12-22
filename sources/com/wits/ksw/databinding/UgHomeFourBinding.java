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
public abstract class UgHomeFourBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeBrowserVaiw;
    public final UgHomeImageView ugHomeDvrVaiw;
    public final UgHomeImageView ugHomeFileVaiw;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected UgHomeFourBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView ugHomeBrowserVaiw, UgHomeImageView ugHomeDvrVaiw, UgHomeImageView ugHomeFileVaiw) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.ugHomeBrowserVaiw = ugHomeBrowserVaiw;
        this.ugHomeDvrVaiw = ugHomeDvrVaiw;
        this.ugHomeFileVaiw = ugHomeFileVaiw;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeFourBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeFourBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeFourBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_four, root, attachToRoot, component);
    }

    public static UgHomeFourBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeFourBinding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeFourBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_four, null, false, component);
    }

    public static UgHomeFourBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeFourBinding bind(View view, Object component) {
        return (UgHomeFourBinding) bind(component, view, C0899R.C0902layout.ug_home_four);
    }
}
