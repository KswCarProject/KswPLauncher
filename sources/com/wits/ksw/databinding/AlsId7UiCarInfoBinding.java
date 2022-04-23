package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class AlsId7UiCarInfoBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mCarViewModel;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected AlsId7UiCarInfoBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static AlsId7UiCarInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiCarInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiCarInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_als_id7_ui_car, root, attachToRoot, component);
    }

    public static AlsId7UiCarInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiCarInfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiCarInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_als_id7_ui_car, (ViewGroup) null, false, component);
    }

    public static AlsId7UiCarInfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiCarInfoBinding bind(View view, Object component) {
        return (AlsId7UiCarInfoBinding) bind(component, view, R.layout.fragment_als_id7_ui_car);
    }
}
