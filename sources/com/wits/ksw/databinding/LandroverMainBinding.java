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
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes7.dex */
public abstract class LandroverMainBinding extends ViewDataBinding {
    public final ImageView iconLeft;
    public final ImageView iconRight;
    public final ImageView indicato1;
    public final ImageView indicato2;
    @Bindable
    protected LandroverViewModel mLauncherViewModel;
    public final ViewPager viewPager;

    public abstract void setLauncherViewModel(LandroverViewModel LauncherViewModel);

    protected LandroverMainBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iconLeft, ImageView iconRight, ImageView indicato1, ImageView indicato2, ViewPager viewPager) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iconLeft = iconLeft;
        this.iconRight = iconRight;
        this.indicato1 = indicato1;
        this.indicato2 = indicato2;
        this.viewPager = viewPager;
    }

    public LandroverViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static LandroverMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.landrover_main, root, attachToRoot, component);
    }

    public static LandroverMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBinding inflate(LayoutInflater inflater, Object component) {
        return (LandroverMainBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.landrover_main, null, false, component);
    }

    public static LandroverMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBinding bind(View view, Object component) {
        return (LandroverMainBinding) bind(component, view, C0899R.C0902layout.landrover_main);
    }
}
