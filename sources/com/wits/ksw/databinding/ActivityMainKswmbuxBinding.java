package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.p006ug.UgViewPager;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ActivityMainKswmbuxBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView controlBtn, ImageView down, ImageView imageView1, ImageView imageView2, ImageView imageView3, ImageView leftButton, ImageView rightButton, ImageView top, UgViewPager ugViewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.controlBtn = controlBtn;
        this.down = down;
        this.imageView1 = imageView1;
        this.imageView2 = imageView2;
        this.imageView3 = imageView3;
        this.leftButton = leftButton;
        this.rightButton = rightButton;
        this.top = top;
        this.ugViewPage = ugViewPage;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainKswmbuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_kswmbux, root, attachToRoot, component);
    }

    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainKswmbuxBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainKswmbuxBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_kswmbux, null, false, component);
    }

    public static ActivityMainKswmbuxBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainKswmbuxBinding bind(View view, Object component) {
        return (ActivityMainKswmbuxBinding) bind(component, view, C0899R.C0902layout.activity_main_kswmbux);
    }
}
