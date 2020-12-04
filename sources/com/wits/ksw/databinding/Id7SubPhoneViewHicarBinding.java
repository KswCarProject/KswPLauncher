package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class Id7SubPhoneViewHicarBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout carConstraintLayout;
    @NonNull
    public final CustomBmwImageView carImageView;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    @NonNull
    public final TextView phoneConnectionTextView;
    @NonNull
    public final TextView textView2;

    public abstract void setNaviViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected Id7SubPhoneViewHicarBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout carConstraintLayout2, CustomBmwImageView carImageView2, TextView phoneConnectionTextView2, TextView textView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.carConstraintLayout = carConstraintLayout2;
        this.carImageView = carImageView2;
        this.phoneConnectionTextView = phoneConnectionTextView2;
        this.textView2 = textView22;
    }

    @Nullable
    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    @NonNull
    public static Id7SubPhoneViewHicarBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubPhoneViewHicarBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Id7SubPhoneViewHicarBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_phone_view_hicar, root, attachToRoot, component);
    }

    @NonNull
    public static Id7SubPhoneViewHicarBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubPhoneViewHicarBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Id7SubPhoneViewHicarBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_phone_view_hicar, (ViewGroup) null, false, component);
    }

    public static Id7SubPhoneViewHicarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Id7SubPhoneViewHicarBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Id7SubPhoneViewHicarBinding) bind(component, view, R.layout.id7_sub_phone_view_hicar);
    }
}
