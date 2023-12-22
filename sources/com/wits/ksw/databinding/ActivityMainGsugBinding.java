package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgViewPager;

/* loaded from: classes7.dex */
public abstract class ActivityMainGsugBinding extends ViewDataBinding {
    public final ImageView appsButton;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final ImageView musicButton;
    public final ImageView naviButton;
    public final ImageView settingButton;
    public final UgViewPager ugViewPage;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ActivityMainGsugBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView appsButton, ImageView musicButton, ImageView naviButton, ImageView settingButton, UgViewPager ugViewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appsButton = appsButton;
        this.musicButton = musicButton;
        this.naviButton = naviButton;
        this.settingButton = settingButton;
        this.ugViewPage = ugViewPage;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainGsugBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsugBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainGsugBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_gsug, root, attachToRoot, component);
    }

    public static ActivityMainGsugBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsugBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainGsugBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_gsug, null, false, component);
    }

    public static ActivityMainGsugBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsugBinding bind(View view, Object component) {
        return (ActivityMainGsugBinding) bind(component, view, C0899R.C0902layout.activity_main_gsug);
    }
}
