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

public abstract class LandroverMainBinding extends ViewDataBinding {
    @NonNull
    public final ImageView iconLeft;
    @NonNull
    public final ImageView iconRight;
    @NonNull
    public final ImageView indicato1;
    @NonNull
    public final ImageView indicato2;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    @NonNull
    public final ViewPager viewPager;

    public abstract void setLauncherViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected LandroverMainBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView iconLeft2, ImageView iconRight2, ImageView indicato12, ImageView indicato22, ViewPager viewPager2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iconLeft = iconLeft2;
        this.iconRight = iconRight2;
        this.indicato1 = indicato12;
        this.indicato2 = indicato22;
        this.viewPager = viewPager2;
    }

    @Nullable
    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    @NonNull
    public static LandroverMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (LandroverMainBinding) DataBindingUtil.inflate(inflater, R.layout.landrover_main, root, attachToRoot, component);
    }

    @NonNull
    public static LandroverMainBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (LandroverMainBinding) DataBindingUtil.inflate(inflater, R.layout.landrover_main, (ViewGroup) null, false, component);
    }

    public static LandroverMainBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LandroverMainBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (LandroverMainBinding) bind(component, view, R.layout.landrover_main);
    }
}
