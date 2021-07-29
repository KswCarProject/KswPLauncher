package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ActivityMainLandRoverBinding extends ViewDataBinding {
    public final ImageView imageView1;
    public final ImageView imageView3;
    public final ImageView imageView4;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected ActivityMainLandRoverBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView12, ImageView imageView32, ImageView imageView42, ViewPager viewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView1 = imageView12;
        this.imageView3 = imageView32;
        this.imageView4 = imageView42;
        this.viewPage = viewPage2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLandRoverBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_land_rover, root, attachToRoot, component);
    }

    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLandRoverBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_land_rover, (ViewGroup) null, false, component);
    }

    public static ActivityMainLandRoverBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLandRoverBinding bind(View view, Object component) {
        return (ActivityMainLandRoverBinding) bind(component, view, R.layout.activity_main_land_rover);
    }
}
