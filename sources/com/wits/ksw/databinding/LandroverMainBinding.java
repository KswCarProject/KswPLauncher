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
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public abstract class LandroverMainBinding extends ViewDataBinding {
    public final ImageView iconLeft;
    public final ImageView iconRight;
    public final ImageView indicato1;
    public final ImageView indicato2;
    @Bindable
    protected LandroverViewModel mLauncherViewModel;
    public final ViewPager viewPager;

    public abstract void setLauncherViewModel(LandroverViewModel landroverViewModel);

    protected LandroverMainBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iconLeft2, ImageView iconRight2, ImageView indicato12, ImageView indicato22, ViewPager viewPager2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iconLeft = iconLeft2;
        this.iconRight = iconRight2;
        this.indicato1 = indicato12;
        this.indicato2 = indicato22;
        this.viewPager = viewPager2;
    }

    public LandroverViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static LandroverMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main, root, attachToRoot, component);
    }

    public static LandroverMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBinding inflate(LayoutInflater inflater, Object component) {
        return (LandroverMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main, (ViewGroup) null, false, component);
    }

    public static LandroverMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBinding bind(View view, Object component) {
        return (LandroverMainBinding) bind(component, view, R.layout.landrover_main);
    }
}
