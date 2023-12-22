package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.Guideline;
import android.support.p001v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityMainAlsId7Binding extends ViewDataBinding {
    public final LinearLayout btnApps;
    public final LinearLayout btnSettings;
    public final LinearLayout btnShut1;
    public final LinearLayout btnShut2;
    public final LinearLayout btnShut3;
    public final Guideline guideline;
    public final ImageView imageView1;
    public final ImageView imageView3;
    public final ImageView imageView4;
    public final LinearLayout llLeft;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected ActivityMainAlsId7Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnApps, LinearLayout btnSettings, LinearLayout btnShut1, LinearLayout btnShut2, LinearLayout btnShut3, Guideline guideline, ImageView imageView1, ImageView imageView3, ImageView imageView4, LinearLayout llLeft, ViewPager viewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnApps = btnApps;
        this.btnSettings = btnSettings;
        this.btnShut1 = btnShut1;
        this.btnShut2 = btnShut2;
        this.btnShut3 = btnShut3;
        this.guideline = guideline;
        this.imageView1 = imageView1;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.llLeft = llLeft;
        this.viewPage = viewPage;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityMainAlsId7Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId7Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainAlsId7Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_als_id7, root, attachToRoot, component);
    }

    public static ActivityMainAlsId7Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId7Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainAlsId7Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_als_id7, null, false, component);
    }

    public static ActivityMainAlsId7Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId7Binding bind(View view, Object component) {
        return (ActivityMainAlsId7Binding) bind(component, view, C0899R.C0902layout.activity_main_als_id7);
    }
}
