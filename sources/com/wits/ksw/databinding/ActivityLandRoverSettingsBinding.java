package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public abstract class ActivityLandRoverSettingsBinding extends ViewDataBinding {
    public final ImageView ImgSetBack;
    public final FrameLayout frameOneLayout;
    public final FrameLayout frameTwoLayout;
    @Bindable
    protected LandroverViewModel mLauncherViewModel;
    public final RecyclerView recyclerView;

    public abstract void setLauncherViewModel(LandroverViewModel landroverViewModel);

    protected ActivityLandRoverSettingsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ImgSetBack2, FrameLayout frameOneLayout2, FrameLayout frameTwoLayout2, RecyclerView recyclerView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ImgSetBack = ImgSetBack2;
        this.frameOneLayout = frameOneLayout2;
        this.frameTwoLayout = frameTwoLayout2;
        this.recyclerView = recyclerView2;
    }

    public LandroverViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLandRoverSettingsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_land_rover_settings, root, attachToRoot, component);
    }

    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLandRoverSettingsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_land_rover_settings, (ViewGroup) null, false, component);
    }

    public static ActivityLandRoverSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLandRoverSettingsBinding bind(View view, Object component) {
        return (ActivityLandRoverSettingsBinding) bind(component, view, R.layout.activity_land_rover_settings);
    }
}
