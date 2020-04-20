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

public abstract class MainActivity extends ViewDataBinding {
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
    public final Button menuButton4;
    @NonNull
    public final Button menuButton5;
    @NonNull
    public final ConstraintLayout menuConstraintLayout;
    @NonNull
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected MainActivity(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, Guideline guideline2, ImageView imageView12, ImageView imageView32, ImageView imageView42, Button menuButton12, Button menuButton22, Button menuButton32, Button menuButton42, Button menuButton52, ConstraintLayout menuConstraintLayout2, ViewPager viewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.guideline = guideline2;
        this.imageView1 = imageView12;
        this.imageView3 = imageView32;
        this.imageView4 = imageView42;
        this.menuButton1 = menuButton12;
        this.menuButton2 = menuButton22;
        this.menuButton3 = menuButton32;
        this.menuButton4 = menuButton42;
        this.menuButton5 = menuButton52;
        this.menuConstraintLayout = menuConstraintLayout2;
        this.viewPage = viewPage2;
    }

    @Nullable
    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    @NonNull
    public static MainActivity inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainActivity inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (MainActivity) DataBindingUtil.inflate(inflater, R.layout.activity_main_bmw, root, attachToRoot, component);
    }

    @NonNull
    public static MainActivity inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainActivity inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (MainActivity) DataBindingUtil.inflate(inflater, R.layout.activity_main_bmw, (ViewGroup) null, false, component);
    }

    public static MainActivity bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static MainActivity bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (MainActivity) bind(component, view, R.layout.activity_main_bmw);
    }
}
