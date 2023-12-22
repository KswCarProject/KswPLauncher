package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.p001v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected MainActivity(Object _bindingComponent, View _root, int _localFieldCount, Guideline guideline, ImageView imageView1, ImageView imageView3, ImageView imageView4, Button menuButton1, Button menuButton2, Button menuButton3, Button menuButton4, Button menuButton5, ConstraintLayout menuConstraintLayout, ViewPager viewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.guideline = guideline;
        this.imageView1 = imageView1;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.menuButton1 = menuButton1;
        this.menuButton2 = menuButton2;
        this.menuButton3 = menuButton3;
        this.menuButton4 = menuButton4;
        this.menuButton5 = menuButton5;
        this.menuConstraintLayout = menuConstraintLayout;
        this.viewPage = viewPage;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static MainActivity inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivity inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MainActivity) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_bmw, root, attachToRoot, component);
    }

    public static MainActivity inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivity inflate(LayoutInflater inflater, Object component) {
        return (MainActivity) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_bmw, null, false, component);
    }

    public static MainActivity bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainActivity bind(View view, Object component) {
        return (MainActivity) bind(component, view, C0899R.C0902layout.activity_main_bmw);
    }
}
