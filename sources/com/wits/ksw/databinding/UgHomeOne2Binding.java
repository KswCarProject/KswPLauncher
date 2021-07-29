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

public abstract class UgHomeOne2Binding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final UgHomeImageView ugHomeBtVaiw;
    public final UgHomeImageView ugHomeMusicVaiw;
    public final UgHomeImageView ugHomeNaviVaiw;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected UgHomeOne2Binding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView ugHomeBtVaiw2, UgHomeImageView ugHomeMusicVaiw2, UgHomeImageView ugHomeNaviVaiw2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.ugHomeBtVaiw = ugHomeBtVaiw2;
        this.ugHomeMusicVaiw = ugHomeMusicVaiw2;
        this.ugHomeNaviVaiw = ugHomeNaviVaiw2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static UgHomeOne2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeOne2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UgHomeOne2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_one2, root, attachToRoot, component);
    }

    public static UgHomeOne2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeOne2Binding inflate(LayoutInflater inflater, Object component) {
        return (UgHomeOne2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.ug_home_one2, (ViewGroup) null, false, component);
    }

    public static UgHomeOne2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UgHomeOne2Binding bind(View view, Object component) {
        return (UgHomeOne2Binding) bind(component, view, R.layout.ug_home_one2);
    }
}
