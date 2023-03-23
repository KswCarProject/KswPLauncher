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

public abstract class KswmbuxHomeTwoBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final UgHomeImageView kswmbuxHomeAppVaiw;
    public final UgHomeImageView kswmbuxHomeHdvideoVaiw;
    public final UgHomeImageView kswmbuxHomeSettingVaiw;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final TextView tvVideo;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected KswmbuxHomeTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, UgHomeImageView kswmbuxHomeAppVaiw2, UgHomeImageView kswmbuxHomeHdvideoVaiw2, UgHomeImageView kswmbuxHomeSettingVaiw2, TextView tvVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.kswmbuxHomeAppVaiw = kswmbuxHomeAppVaiw2;
        this.kswmbuxHomeHdvideoVaiw = kswmbuxHomeHdvideoVaiw2;
        this.kswmbuxHomeSettingVaiw = kswmbuxHomeSettingVaiw2;
        this.tvVideo = tvVideo2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswmbuxHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.kswmbux_home_two, root, attachToRoot, component);
    }

    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (KswmbuxHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.kswmbux_home_two, (ViewGroup) null, false, component);
    }

    public static KswmbuxHomeTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeTwoBinding bind(View view, Object component) {
        return (KswmbuxHomeTwoBinding) bind(component, view, R.layout.kswmbux_home_two);
    }
}
