package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
    public final Guideline guideline;
    public final ImageView imageView1;
    public final ImageView imageView3;
    public final ImageView imageView4;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final Button menuButton1;
    public final Button menuButton2;
    public final Button menuButton3;
    public final Button menuButton4;
    public final Button menuButton5;
    public final ConstraintLayout menuConstraintLayout;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected MainActivity(Object _bindingComponent, View _root, int _localFieldCount, Guideline guideline2, ImageView imageView12, ImageView imageView32, ImageView imageView42, Button menuButton12, Button menuButton22, Button menuButton32, Button menuButton42, Button menuButton52, ConstraintLayout menuConstraintLayout2, ViewPager viewPage2) {
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

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static MainActivity inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivity inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MainActivity) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_bmw, root, attachToRoot, component);
    }

    public static MainActivity inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivity inflate(LayoutInflater inflater, Object component) {
        return (MainActivity) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_bmw, (ViewGroup) null, false, component);
    }

    public static MainActivity bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivity bind(View view, Object component) {
        return (MainActivity) bind(component, view, R.layout.activity_main_bmw);
    }
}
