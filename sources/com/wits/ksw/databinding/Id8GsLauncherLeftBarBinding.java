package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class Id8GsLauncherLeftBarBinding extends ViewDataBinding {
    public final ImageView ivLeft1;
    public final ImageView ivLeft2;
    public final ImageView ivLeft3;
    public final ImageView ivLeft4;
    public final ImageView ivLeft5;
    public final LinearLayout llLeft1;
    public final LinearLayout llLeft2;
    public final LinearLayout llLeft3;
    public final LinearLayout llLeft4;
    public final LinearLayout llLeft5;
    public final LinearLayout llLeftBarContainer;
    @Bindable
    protected LauncherViewModel mLeftViewModel;
    public final TextView tvLeft1;
    public final TextView tvLeft2;
    public final TextView tvLeft3;
    public final TextView tvLeft4;
    public final TextView tvLeft5;

    public abstract void setLeftViewModel(LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Id8GsLauncherLeftBarBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLeft12, ImageView ivLeft22, ImageView ivLeft32, ImageView ivLeft42, ImageView ivLeft52, LinearLayout llLeft12, LinearLayout llLeft22, LinearLayout llLeft32, LinearLayout llLeft42, LinearLayout llLeft52, LinearLayout llLeftBarContainer2, TextView tvLeft12, TextView tvLeft22, TextView tvLeft32, TextView tvLeft42, TextView tvLeft52) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLeft1 = ivLeft12;
        this.ivLeft2 = ivLeft22;
        this.ivLeft3 = ivLeft32;
        this.ivLeft4 = ivLeft42;
        this.ivLeft5 = ivLeft52;
        this.llLeft1 = llLeft12;
        this.llLeft2 = llLeft22;
        this.llLeft3 = llLeft32;
        this.llLeft4 = llLeft42;
        this.llLeft5 = llLeft52;
        this.llLeftBarContainer = llLeftBarContainer2;
        this.tvLeft1 = tvLeft12;
        this.tvLeft2 = tvLeft22;
        this.tvLeft3 = tvLeft32;
        this.tvLeft4 = tvLeft42;
        this.tvLeft5 = tvLeft52;
    }

    public LauncherViewModel getLeftViewModel() {
        return this.mLeftViewModel;
    }

    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id8GsLauncherLeftBarBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id8_gs_launcher_left_bar, root, attachToRoot, component);
    }

    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater, Object component) {
        return (Id8GsLauncherLeftBarBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id8_gs_launcher_left_bar, (ViewGroup) null, false, component);
    }

    public static Id8GsLauncherLeftBarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id8GsLauncherLeftBarBinding bind(View view, Object component) {
        return (Id8GsLauncherLeftBarBinding) bind(component, view, R.layout.id8_gs_launcher_left_bar);
    }
}
