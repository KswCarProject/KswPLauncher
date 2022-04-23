package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class LexusLsMainLayoutBinding extends ViewDataBinding {
    public final ImageView ivLexusLsBgAppLayerlist;
    public final ImageView ivLexuslsLeftBtn;
    public final ImageView ivLexuslsRightBtn;
    public final RelativeLayout lexusLsMainLl;
    public final RecyclerView lexusLsRecycleviewDesktop;
    public final ViewPager lexusLsViewpagerBottom;
    @Bindable
    protected LauncherViewModel mLexusLsViewModel;

    public abstract void setLexusLsViewModel(LauncherViewModel launcherViewModel);

    protected LexusLsMainLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivLexusLsBgAppLayerlist2, ImageView ivLexuslsLeftBtn2, ImageView ivLexuslsRightBtn2, RelativeLayout lexusLsMainLl2, RecyclerView lexusLsRecycleviewDesktop2, ViewPager lexusLsViewpagerBottom2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivLexusLsBgAppLayerlist = ivLexusLsBgAppLayerlist2;
        this.ivLexuslsLeftBtn = ivLexuslsLeftBtn2;
        this.ivLexuslsRightBtn = ivLexuslsRightBtn2;
        this.lexusLsMainLl = lexusLsMainLl2;
        this.lexusLsRecycleviewDesktop = lexusLsRecycleviewDesktop2;
        this.lexusLsViewpagerBottom = lexusLsViewpagerBottom2;
    }

    public LauncherViewModel getLexusLsViewModel() {
        return this.mLexusLsViewModel;
    }

    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LexusLsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_main_layout, root, attachToRoot, component);
    }

    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsMainLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (LexusLsMainLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.lexus_ls_main_layout, (ViewGroup) null, false, component);
    }

    public static LexusLsMainLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LexusLsMainLayoutBinding bind(View view, Object component) {
        return (LexusLsMainLayoutBinding) bind(component, view, R.layout.lexus_ls_main_layout);
    }
}
