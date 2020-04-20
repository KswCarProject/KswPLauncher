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
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ID6FragmentThree extends ViewDataBinding {
    @NonNull
    public final ImageView id6DashboardImageView;
    @NonNull
    public final ImageView id6DvrImageView;
    @NonNull
    public final TextView id6DvrMess;
    @NonNull
    public final ImageView id6FileIamgeView;
    @NonNull
    public final TextView id6OilMess;
    @NonNull
    public final TextView id6SpeedMess;
    @NonNull
    public final TextView id6VideoMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ID6FragmentThree(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView id6DashboardImageView2, ImageView id6DvrImageView2, TextView id6DvrMess2, ImageView id6FileIamgeView2, TextView id6OilMess2, TextView id6SpeedMess2, TextView id6VideoMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6DashboardImageView = id6DashboardImageView2;
        this.id6DvrImageView = id6DvrImageView2;
        this.id6DvrMess = id6DvrMess2;
        this.id6FileIamgeView = id6FileIamgeView2;
        this.id6OilMess = id6OilMess2;
        this.id6SpeedMess = id6SpeedMess2;
        this.id6VideoMess = id6VideoMess2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ID6FragmentThree inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentThree inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ID6FragmentThree) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_three, root, attachToRoot, component);
    }

    @NonNull
    public static ID6FragmentThree inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentThree inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ID6FragmentThree) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_three, (ViewGroup) null, false, component);
    }

    public static ID6FragmentThree bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ID6FragmentThree bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ID6FragmentThree) bind(component, view, R.layout.id6_fragment_three);
    }
}
