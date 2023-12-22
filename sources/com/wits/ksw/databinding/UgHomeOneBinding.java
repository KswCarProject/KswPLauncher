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
public abstract class UgHomeOneBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeBtVaiw;
    public final UgHomeImageView ugHomeMusicVaiw;
    public final UgHomeImageView ugHomeNaviVaiw;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected UgHomeOneBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView ugHomeBtVaiw, UgHomeImageView ugHomeMusicVaiw, UgHomeImageView ugHomeNaviVaiw) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.ugHomeBtVaiw = ugHomeBtVaiw;
        this.ugHomeMusicVaiw = ugHomeMusicVaiw;
        this.ugHomeNaviVaiw = ugHomeNaviVaiw;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_one, root, attachToRoot, component);
    }

    public static UgHomeOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeOneBinding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ug_home_one, null, false, component);
    }

    public static UgHomeOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeOneBinding bind(View view, Object component) {
        return (UgHomeOneBinding) bind(component, view, C0899R.C0902layout.ug_home_one);
    }
}
