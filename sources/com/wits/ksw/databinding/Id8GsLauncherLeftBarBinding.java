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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setLeftViewModel(LauncherViewModel LeftViewModel);

    protected Id8GsLauncherLeftBarBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLeft1, ImageView ivLeft2, ImageView ivLeft3, ImageView ivLeft4, ImageView ivLeft5, LinearLayout llLeft1, LinearLayout llLeft2, LinearLayout llLeft3, LinearLayout llLeft4, LinearLayout llLeft5, LinearLayout llLeftBarContainer, TextView tvLeft1, TextView tvLeft2, TextView tvLeft3, TextView tvLeft4, TextView tvLeft5) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLeft1 = ivLeft1;
        this.ivLeft2 = ivLeft2;
        this.ivLeft3 = ivLeft3;
        this.ivLeft4 = ivLeft4;
        this.ivLeft5 = ivLeft5;
        this.llLeft1 = llLeft1;
        this.llLeft2 = llLeft2;
        this.llLeft3 = llLeft3;
        this.llLeft4 = llLeft4;
        this.llLeft5 = llLeft5;
        this.llLeftBarContainer = llLeftBarContainer;
        this.tvLeft1 = tvLeft1;
        this.tvLeft2 = tvLeft2;
        this.tvLeft3 = tvLeft3;
        this.tvLeft4 = tvLeft4;
        this.tvLeft5 = tvLeft5;
    }

    public LauncherViewModel getLeftViewModel() {
        return this.mLeftViewModel;
    }

    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id8GsLauncherLeftBarBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id8_gs_launcher_left_bar, root, attachToRoot, component);
    }

    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id8GsLauncherLeftBarBinding inflate(LayoutInflater inflater, Object component) {
        return (Id8GsLauncherLeftBarBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id8_gs_launcher_left_bar, null, false, component);
    }

    public static Id8GsLauncherLeftBarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id8GsLauncherLeftBarBinding bind(View view, Object component) {
        return (Id8GsLauncherLeftBarBinding) bind(component, view, C0899R.C0902layout.id8_gs_launcher_left_bar);
    }
}
