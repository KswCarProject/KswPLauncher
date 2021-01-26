package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ActivityMainLandRoverBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageView1;
    @NonNull
    public final ImageView imageView3;
    @NonNull
    public final ImageView imageView4;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    @NonNull
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ActivityMainLandRoverBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView imageView12, ImageView imageView32, ImageView imageView42, ViewPager viewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView1 = imageView12;
        this.imageView3 = imageView32;
        this.imageView4 = imageView42;
        this.viewPage = viewPage2;
    }

    @Nullable
    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    @NonNull
    public static ActivityMainLandRoverBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLandRoverBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainLandRoverBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_land_rover, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainLandRoverBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainLandRoverBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainLandRoverBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_land_rover, (ViewGroup) null, false, component);
    }

    public static ActivityMainLandRoverBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainLandRoverBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainLandRoverBinding) bind(component, view, R.layout.activity_main_land_rover);
    }
}
