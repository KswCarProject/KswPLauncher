package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class Id7SubCarViewBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final CustomBmwImageView carImageView;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setCarViewModel(LauncherViewModel launcherViewModel);

    protected Id7SubCarViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, CustomBmwImageView carImageView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.carImageView = carImageView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static Id7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_car_view, root, attachToRoot, component);
    }

    public static Id7SubCarViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubCarViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_car_view, (ViewGroup) null, false, component);
    }

    public static Id7SubCarViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubCarViewBinding bind(View view, Object component) {
        return (Id7SubCarViewBinding) bind(component, view, R.layout.id7_sub_car_view);
    }
}
