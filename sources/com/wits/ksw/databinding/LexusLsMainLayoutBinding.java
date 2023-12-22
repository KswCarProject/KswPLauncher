package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class LexusLsMainLayoutBinding extends ViewDataBinding {
    public final ImageView ivLexusLsBgAppLayerlist;
    public final ImageView ivLexuslsLeftBtn;
    public final ImageView ivLexuslsRightBtn;
    public final RelativeLayout lexusLsMainLl;
    public final RecyclerView lexusLsRecycleviewDesktop;
    public final ViewPager lexusLsViewpagerBottom;
    @Bindable
    protected LauncherViewModel mLexusLsViewModel;

    public abstract void setLexusLsViewModel(LauncherViewModel LexusLsViewModel);

    protected LexusLsMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLexusLsBgAppLayerlist, ImageView ivLexuslsLeftBtn, ImageView ivLexuslsRightBtn, RelativeLayout lexusLsMainLl, RecyclerView lexusLsRecycleviewDesktop, ViewPager lexusLsViewpagerBottom) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLexusLsBgAppLayerlist = ivLexusLsBgAppLayerlist;
        this.ivLexuslsLeftBtn = ivLexuslsLeftBtn;
        this.ivLexuslsRightBtn = ivLexuslsRightBtn;
        this.lexusLsMainLl = lexusLsMainLl;
        this.lexusLsRecycleviewDesktop = lexusLsRecycleviewDesktop;
        this.lexusLsViewpagerBottom = lexusLsViewpagerBottom;
    }

    public LauncherViewModel getLexusLsViewModel() {
        return this.mLexusLsViewModel;
    }

    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_main_layout, root, attachToRoot, component);
    }

    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (LexusLsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.lexus_ls_main_layout, null, false, component);
    }

    public static LexusLsMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsMainLayoutBinding bind(View view, Object component) {
        return (LexusLsMainLayoutBinding) bind(component, view, C0899R.C0902layout.lexus_ls_main_layout);
    }
}
