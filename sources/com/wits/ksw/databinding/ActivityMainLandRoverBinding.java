package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p001v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityMainLandRoverBinding extends ViewDataBinding {
    public final ImageView imageView1;
    public final ImageView imageView3;
    public final ImageView imageView4;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected ActivityMainLandRoverBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView1, ImageView imageView3, ImageView imageView4, ViewPager viewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView1 = imageView1;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.viewPage = viewPage;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainLandRoverBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_land_rover, root, attachToRoot, component);
    }

    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLandRoverBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainLandRoverBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_land_rover, null, false, component);
    }

    public static ActivityMainLandRoverBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainLandRoverBinding bind(View view, Object component) {
        return (ActivityMainLandRoverBinding) bind(component, view, C0899R.C0902layout.activity_main_land_rover);
    }
}
