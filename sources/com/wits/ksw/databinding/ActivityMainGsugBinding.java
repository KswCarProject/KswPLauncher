package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgViewPager;

public abstract class ActivityMainGsugBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mViewModel;
    @NonNull
    public final ImageView musicButton;
    @NonNull
    public final ImageView naviButton;
    @NonNull
    public final ImageView settingButton;
    @NonNull
    public final UgViewPager ugViewPage;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ActivityMainGsugBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView musicButton2, ImageView naviButton2, ImageView settingButton2, UgViewPager ugViewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.musicButton = musicButton2;
        this.naviButton = naviButton2;
        this.settingButton = settingButton2;
        this.ugViewPage = ugViewPage2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ActivityMainGsugBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainGsugBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainGsugBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_gsug, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainGsugBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainGsugBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainGsugBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_gsug, (ViewGroup) null, false, component);
    }

    public static ActivityMainGsugBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainGsugBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainGsugBinding) bind(component, view, R.layout.activity_main_gsug);
    }
}
