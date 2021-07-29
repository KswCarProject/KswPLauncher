package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected LexusLsBottomFragmentOne(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLexusLsAir2, ImageView ivLexusLsApp2, ImageView ivLexusLsBt2, ImageView ivLexusLsCar2, ImageView ivLexusLsMusic2, ImageView ivLexusLsNavi2, ImageView ivLexusLsSet2, ImageView ivLexusLsVideo2, LinearLayout lexusLsLlBottomFragOne2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLexusLsAir = ivLexusLsAir2;
        this.ivLexusLsApp = ivLexusLsApp2;
        this.ivLexusLsBt = ivLexusLsBt2;
        this.ivLexusLsCar = ivLexusLsCar2;
        this.ivLexusLsMusic = ivLexusLsMusic2;
        this.ivLexusLsNavi = ivLexusLsNavi2;
        this.ivLexusLsSet = ivLexusLsSet2;
        this.ivLexusLsVideo = ivLexusLsVideo2;
        this.lexusLsLlBottomFragOne = lexusLsLlBottomFragOne2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsBottomFragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_bottom_fragment_one, root, attachToRoot, component);
    }

    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentOne inflate(LayoutInflater inflater, Object component) {
        return (LexusLsBottomFragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_bottom_fragment_one, (ViewGroup) null, false, component);
    }

    public static LexusLsBottomFragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsBottomFragmentOne bind(View view, Object component) {
        return (LexusLsBottomFragmentOne) bind(component, view, R.layout.lexus_ls_bottom_fragment_one);
    }
}
