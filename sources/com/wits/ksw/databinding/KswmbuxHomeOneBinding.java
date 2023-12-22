package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgHomeImageView;

/* loaded from: classes7.dex */
public abstract class KswmbuxHomeOneBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final UgHomeImageView kswmbuxHomeBtVaiw;
    public final UgHomeImageView kswmbuxHomeMusicVaiw;
    public final UgHomeImageView kswmbuxHomeNaviVaiw;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final TextView tvMusic;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected KswmbuxHomeOneBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView kswmbuxHomeBtVaiw, UgHomeImageView kswmbuxHomeMusicVaiw, UgHomeImageView kswmbuxHomeNaviVaiw, TextView tvMusic) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.kswmbuxHomeBtVaiw = kswmbuxHomeBtVaiw;
        this.kswmbuxHomeMusicVaiw = kswmbuxHomeMusicVaiw;
        this.kswmbuxHomeNaviVaiw = kswmbuxHomeNaviVaiw;
        this.tvMusic = tvMusic;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswmbuxHomeOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.kswmbux_home_one, root, attachToRoot, component);
    }

    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeOneBinding inflate(LayoutInflater inflater, Object component) {
        return (KswmbuxHomeOneBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.kswmbux_home_one, null, false, component);
    }

    public static KswmbuxHomeOneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeOneBinding bind(View view, Object component) {
        return (KswmbuxHomeOneBinding) bind(component, view, C0899R.C0902layout.kswmbux_home_one);
    }
}
