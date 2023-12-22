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
public abstract class KswmbuxHomeTwoBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final UgHomeImageView kswmbuxHomeAppVaiw;
    public final UgHomeImageView kswmbuxHomeHdvideoVaiw;
    public final UgHomeImageView kswmbuxHomeSettingVaiw;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final TextView tvVideo;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected KswmbuxHomeTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, UgHomeImageView kswmbuxHomeAppVaiw, UgHomeImageView kswmbuxHomeHdvideoVaiw, UgHomeImageView kswmbuxHomeSettingVaiw, TextView tvVideo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.kswmbuxHomeAppVaiw = kswmbuxHomeAppVaiw;
        this.kswmbuxHomeHdvideoVaiw = kswmbuxHomeHdvideoVaiw;
        this.kswmbuxHomeSettingVaiw = kswmbuxHomeSettingVaiw;
        this.tvVideo = tvVideo;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (KswmbuxHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.kswmbux_home_two, root, attachToRoot, component);
    }

    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (KswmbuxHomeTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.kswmbux_home_two, null, false, component);
    }

    public static KswmbuxHomeTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static KswmbuxHomeTwoBinding bind(View view, Object component) {
        return (KswmbuxHomeTwoBinding) bind(component, view, C0899R.C0902layout.kswmbux_home_two);
    }
}
