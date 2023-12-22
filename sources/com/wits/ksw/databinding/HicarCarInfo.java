package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class HicarCarInfo extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mCarViewModel;

    public abstract void setCarViewModel(LauncherViewModel CarViewModel);

    protected HicarCarInfo(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static HicarCarInfo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HicarCarInfo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (HicarCarInfo) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_fragment_car_hicar, root, attachToRoot, component);
    }

    public static HicarCarInfo inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HicarCarInfo inflate(LayoutInflater inflater, Object component) {
        return (HicarCarInfo) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_fragment_car_hicar, null, false, component);
    }

    public static HicarCarInfo bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HicarCarInfo bind(View view, Object component) {
        return (HicarCarInfo) bind(component, view, C0899R.C0902layout.id7_fragment_car_hicar);
    }
}
