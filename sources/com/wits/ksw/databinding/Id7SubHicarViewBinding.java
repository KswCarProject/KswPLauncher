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

public abstract class Id7SubHicarViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView dayTextView;
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    @NonNull
    public final TextView monthTextView;
    @NonNull
    public final ConstraintLayout phoneConstraintLayout;
    @NonNull
    public final CustomBmwImageView phoneImageView;
    @NonNull
    public final TextView textView2;

    public abstract void setNaviViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected Id7SubHicarViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView dayTextView2, TextView monthTextView2, ConstraintLayout phoneConstraintLayout2, CustomBmwImageView phoneImageView2, TextView textView22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.dayTextView = dayTextView2;
        this.monthTextView = monthTextView2;
        this.phoneConstraintLayout = phoneConstraintLayout2;
        this.phoneImageView = phoneImageView2;
        this.textView2 = textView22;
    }

    @Nullable
    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    @NonNull
    public static Id7SubHicarViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubHicarViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Id7SubHicarViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_hicar_view, root, attachToRoot, component);
    }

    @NonNull
    public static Id7SubHicarViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubHicarViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Id7SubHicarViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_hicar_view, (ViewGroup) null, false, component);
    }

    public static Id7SubHicarViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Id7SubHicarViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Id7SubHicarViewBinding) bind(component, view, R.layout.id7_sub_hicar_view);
    }
}
