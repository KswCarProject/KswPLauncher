package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgHomeImageView;

public abstract class KswmbuxHomeOneBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final UgHomeImageView kswmbuxHomeBtVaiw;
    public final UgHomeImageView kswmbuxHomeMusicVaiw;
    public final UgHomeImageView kswmbuxHomeNaviVaiw;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final TextView tvMusic;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected KswmbuxHomeOneBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView kswmbuxHomeBtVaiw2, UgHomeImageView kswmbuxHomeMusicVaiw2, UgHomeImageView kswmbuxHomeNaviVaiw2, TextView tvMusic2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.kswmbuxHomeBtVaiw = kswmbuxHomeBtVaiw2;
        this.kswmbuxHomeMusicVaiw = kswmbuxHomeMusicVaiw2;
        this.kswmbuxHomeNaviVaiw = kswmbuxHomeNaviVaiw2;
        this.tvMusic = tvMusic2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswmbuxHomeOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.kswmbux_home_one, root, attachToRoot, component);
    }

    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater, Object component) {
        return (KswmbuxHomeOneBinding) ViewDataBinding.inflateInternal(inflater, R.layout.kswmbux_home_one, (ViewGroup) null, false, component);
    }

    public static KswmbuxHomeOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeOneBinding bind(View view, Object component) {
        return (KswmbuxHomeOneBinding) bind(component, view, R.layout.kswmbux_home_one);
    }
}
