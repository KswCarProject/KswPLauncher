package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.ug.UgViewPager;

public abstract class ActivityMainKswmbuxBinding extends ViewDataBinding {
    public final ImageView controlBtn;
    public final ImageView down;
    public final ImageView imageView1;
    public final ImageView imageView2;
    public final ImageView imageView3;
    public final ImageView leftButton;
    @Bindable
    protected LauncherViewModel mViewModel;
    public final ImageView rightButton;
    public final ImageView top;
    public final UgViewPager ugViewPage;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ActivityMainKswmbuxBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView controlBtn2, ImageView down2, ImageView imageView12, ImageView imageView22, ImageView imageView32, ImageView leftButton2, ImageView rightButton2, ImageView top2, UgViewPager ugViewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.controlBtn = controlBtn2;
        this.down = down2;
        this.imageView1 = imageView12;
        this.imageView2 = imageView22;
        this.imageView3 = imageView32;
        this.leftButton = leftButton2;
        this.rightButton = rightButton2;
        this.top = top2;
        this.ugViewPage = ugViewPage2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainKswmbuxBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_kswmbux, root, attachToRoot, component);
    }

    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainKswmbuxBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_kswmbux, (ViewGroup) null, false, component);
    }

    public static ActivityMainKswmbuxBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainKswmbuxBinding bind(View view, Object component) {
        return (ActivityMainKswmbuxBinding) bind(component, view, R.layout.activity_main_kswmbux);
    }
}
