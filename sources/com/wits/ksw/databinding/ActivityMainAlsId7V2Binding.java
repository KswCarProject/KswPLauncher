package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.Guideline;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ActivityMainAlsId7V2Binding extends ViewDataBinding {
    public final LinearLayout btnApps;
    public final LinearLayout btnSettings;
    public final LinearLayout btnShut1;
    public final LinearLayout btnShut2;
    public final LinearLayout btnShut3;
    public final Guideline guideline;
    public final ImageView imageView1;
    public final ImageView imageView3;
    public final ImageView imageView4;
    public final ImageView imageView5;
    public final LinearLayout llLeft;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected ActivityMainAlsId7V2Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout btnApps2, LinearLayout btnSettings2, LinearLayout btnShut12, LinearLayout btnShut22, LinearLayout btnShut32, Guideline guideline2, ImageView imageView12, ImageView imageView32, ImageView imageView42, ImageView imageView52, LinearLayout llLeft2, ViewPager viewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnApps = btnApps2;
        this.btnSettings = btnSettings2;
        this.btnShut1 = btnShut12;
        this.btnShut2 = btnShut22;
        this.btnShut3 = btnShut32;
        this.guideline = guideline2;
        this.imageView1 = imageView12;
        this.imageView3 = imageView32;
        this.imageView4 = imageView42;
        this.imageView5 = imageView52;
        this.llLeft = llLeft2;
        this.viewPage = viewPage2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static ActivityMainAlsId7V2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId7V2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainAlsId7V2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_als_id7_v2, root, attachToRoot, component);
    }

    public static ActivityMainAlsId7V2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId7V2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainAlsId7V2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_als_id7_v2, (ViewGroup) null, false, component);
    }

    public static ActivityMainAlsId7V2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAlsId7V2Binding bind(View view, Object component) {
        return (ActivityMainAlsId7V2Binding) bind(component, view, R.layout.activity_main_als_id7_v2);
    }
}
