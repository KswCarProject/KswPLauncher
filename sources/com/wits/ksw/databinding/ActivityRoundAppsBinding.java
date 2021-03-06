package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AppViewModel;
import com.wits.ksw.launcher.view.CustomGridView;

public abstract class ActivityRoundAppsBinding extends ViewDataBinding {
    @NonNull
    public final CustomGridView appGridView;
    @Bindable
    protected AppViewModel mAppViewModel;

    public abstract void setAppViewModel(@Nullable AppViewModel appViewModel);

    protected ActivityRoundAppsBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, CustomGridView appGridView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appGridView = appGridView2;
    }

    @Nullable
    public AppViewModel getAppViewModel() {
        return this.mAppViewModel;
    }

    @NonNull
    public static ActivityRoundAppsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRoundAppsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityRoundAppsBinding) DataBindingUtil.inflate(inflater, R.layout.activity_round_apps, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityRoundAppsBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRoundAppsBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityRoundAppsBinding) DataBindingUtil.inflate(inflater, R.layout.activity_round_apps, (ViewGroup) null, false, component);
    }

    public static ActivityRoundAppsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityRoundAppsBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityRoundAppsBinding) bind(component, view, R.layout.activity_round_apps);
    }
}
