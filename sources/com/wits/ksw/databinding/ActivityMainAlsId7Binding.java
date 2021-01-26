package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ActivityMainAlsId7Binding extends ViewDataBinding {
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final ImageView imageView1;
    @NonNull
    public final ImageView imageView3;
    @NonNull
    public final ImageView imageView4;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    @NonNull
    public final Button menuButton1;
    @NonNull
    public final Button menuButton2;
    @NonNull
    public final Button menuButton3;
    @NonNull
    public final ConstraintLayout menuConstraintLayout;
    @NonNull
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ActivityMainAlsId7Binding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Guideline guideline2, ImageView imageView12, ImageView imageView32, ImageView imageView42, Button menuButton12, Button menuButton22, Button menuButton32, ConstraintLayout menuConstraintLayout2, ViewPager viewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.guideline = guideline2;
        this.imageView1 = imageView12;
        this.imageView3 = imageView32;
        this.imageView4 = imageView42;
        this.menuButton1 = menuButton12;
        this.menuButton2 = menuButton22;
        this.menuButton3 = menuButton32;
        this.menuConstraintLayout = menuConstraintLayout2;
        this.viewPage = viewPage2;
    }

    @Nullable
    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    @NonNull
    public static ActivityMainAlsId7Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainAlsId7Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainAlsId7Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_als_id7, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainAlsId7Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainAlsId7Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainAlsId7Binding) DataBindingUtil.inflate(inflater, R.layout.activity_main_als_id7, (ViewGroup) null, false, component);
    }

    public static ActivityMainAlsId7Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainAlsId7Binding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainAlsId7Binding) bind(component, view, R.layout.activity_main_als_id7);
    }
}
