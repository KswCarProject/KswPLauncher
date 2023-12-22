package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityLandRoverSettingsBinding extends ViewDataBinding {
    public final ImageView ImgSetBack;
    public final FrameLayout frameOneLayout;
    public final FrameLayout frameTwoLayout;
    @Bindable
    protected LandroverViewModel mLauncherViewModel;
    public final RecyclerView recyclerView;

    public abstract void setLauncherViewModel(LandroverViewModel LauncherViewModel);

    protected ActivityLandRoverSettingsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ImgSetBack, FrameLayout frameOneLayout, FrameLayout frameTwoLayout, RecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ImgSetBack = ImgSetBack;
        this.frameOneLayout = frameOneLayout;
        this.frameTwoLayout = frameTwoLayout;
        this.recyclerView = recyclerView;
    }

    public LandroverViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityLandRoverSettingsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_land_rover_settings, root, attachToRoot, component);
    }

    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLandRoverSettingsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityLandRoverSettingsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_land_rover_settings, null, false, component);
    }

    public static ActivityLandRoverSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLandRoverSettingsBinding bind(View view, Object component) {
        return (ActivityLandRoverSettingsBinding) bind(component, view, C0899R.C0902layout.activity_land_rover_settings);
    }
}
