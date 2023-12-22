package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class LexusLsBottomFragmentOne extends ViewDataBinding {
    public final ImageView ivLexusLsAir;
    public final ImageView ivLexusLsApp;
    public final ImageView ivLexusLsBt;
    public final ImageView ivLexusLsCar;
    public final ImageView ivLexusLsMusic;
    public final ImageView ivLexusLsNavi;
    public final ImageView ivLexusLsSet;
    public final ImageView ivLexusLsVideo;
    public final LinearLayout lexusLsLlBottomFragOne;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected LexusLsBottomFragmentOne(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLexusLsAir, ImageView ivLexusLsApp, ImageView ivLexusLsBt, ImageView ivLexusLsCar, ImageView ivLexusLsMusic, ImageView ivLexusLsNavi, ImageView ivLexusLsSet, ImageView ivLexusLsVideo, LinearLayout lexusLsLlBottomFragOne) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLexusLsAir = ivLexusLsAir;
        this.ivLexusLsApp = ivLexusLsApp;
        this.ivLexusLsBt = ivLexusLsBt;
        this.ivLexusLsCar = ivLexusLsCar;
        this.ivLexusLsMusic = ivLexusLsMusic;
        this.ivLexusLsNavi = ivLexusLsNavi;
        this.ivLexusLsSet = ivLexusLsSet;
        this.ivLexusLsVideo = ivLexusLsVideo;
        this.lexusLsLlBottomFragOne = lexusLsLlBottomFragOne;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsBottomFragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_bottom_fragment_one, root, attachToRoot, component);
    }

    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater, Object component) {
        return (LexusLsBottomFragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_bottom_fragment_one, null, false, component);
    }

    public static LexusLsBottomFragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentOne bind(View view, Object component) {
        return (LexusLsBottomFragmentOne) bind(component, view, C0899R.C0902layout.lexus_ls_bottom_fragment_one);
    }
}
