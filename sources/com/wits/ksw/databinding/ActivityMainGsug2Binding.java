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
public abstract class ActivityMainGsug2Binding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mViewModel;
    public final ImageView musicButton;
    public final ImageView naviButton;
    public final ImageView settingButton;
    public final UgViewPager ugViewPage;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ActivityMainGsug2Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView musicButton, ImageView naviButton, ImageView settingButton, UgViewPager ugViewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.musicButton = musicButton;
        this.naviButton = naviButton;
        this.settingButton = settingButton;
        this.ugViewPage = ugViewPage;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainGsug2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_gsug2, root, attachToRoot, component);
    }

    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsug2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainGsug2Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_gsug2, null, false, component);
    }

    public static ActivityMainGsug2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainGsug2Binding bind(View view, Object component) {
        return (ActivityMainGsug2Binding) bind(component, view, C0899R.C0902layout.activity_main_gsug2);
    }
}
