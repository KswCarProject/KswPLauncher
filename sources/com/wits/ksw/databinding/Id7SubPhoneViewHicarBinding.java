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

public abstract class Id7SubPhoneViewHicarBinding extends ViewDataBinding {
    public final ConstraintLayout carConstraintLayout;
    public final CustomBmwImageView carImageView;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final TextView phoneConnectionTextView;
    public final TextView textView2;

    public abstract void setNaviViewModel(LauncherViewModel launcherViewModel);

    protected Id7SubPhoneViewHicarBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, CustomBmwImageView carImageView2, TextView phoneConnectionTextView2, TextView textView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.carImageView = carImageView2;
        this.phoneConnectionTextView = phoneConnectionTextView2;
        this.textView2 = textView22;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static Id7SubPhoneViewHicarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubPhoneViewHicarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubPhoneViewHicarBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_phone_view_hicar, root, attachToRoot, component);
    }

    public static Id7SubPhoneViewHicarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubPhoneViewHicarBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubPhoneViewHicarBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_phone_view_hicar, (ViewGroup) null, false, component);
    }

    public static Id7SubPhoneViewHicarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubPhoneViewHicarBinding bind(View view, Object component) {
        return (Id7SubPhoneViewHicarBinding) bind(component, view, R.layout.id7_sub_phone_view_hicar);
    }
}
