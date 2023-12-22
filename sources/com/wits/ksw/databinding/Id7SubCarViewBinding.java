package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public abstract class Id7SubCarViewBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final CustomBmwImageView carImageView;
    @Bindable
    protected LauncherViewModel mCarViewModel;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setCarViewModel(LauncherViewModel CarViewModel);

    protected Id7SubCarViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout, CustomBmwImageView carImageView, TextView textView2, TextView textView3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout;
        this.carImageView = carImageView;
        this.textView2 = textView2;
        this.textView3 = textView3;
    }

    public LauncherViewModel getCarViewModel() {
        return this.mCarViewModel;
    }

    public static Id7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubCarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_car_view, root, attachToRoot, component);
    }

    public static Id7SubCarViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubCarViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubCarViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_car_view, null, false, component);
    }

    public static Id7SubCarViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubCarViewBinding bind(View view, Object component) {
        return (Id7SubCarViewBinding) bind(component, view, C0899R.C0902layout.id7_sub_car_view);
    }
}
