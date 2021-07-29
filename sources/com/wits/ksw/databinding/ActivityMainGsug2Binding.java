package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgViewPager;

public abstract class ActivityMainGsug2Binding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mViewModel;
    public final ImageView musicButton;
    public final ImageView naviButton;
    public final ImageView settingButton;
    public final UgViewPager ugViewPage;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ActivityMainGsug2Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView musicButton2, ImageView naviButton2, ImageView settingButton2, UgViewPager ugViewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.musicButton = musicButton2;
        this.naviButton = naviButton2;
        this.settingButton = settingButton2;
        this.ugViewPage = ugViewPage2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainGsug2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_gsug2, root, attachToRoot, component);
    }

    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainGsug2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_gsug2, (ViewGroup) null, false, component);
    }

    public static ActivityMainGsug2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsug2Binding bind(View view, Object component) {
        return (ActivityMainGsug2Binding) bind(component, view, R.layout.activity_main_gsug2);
    }
}
