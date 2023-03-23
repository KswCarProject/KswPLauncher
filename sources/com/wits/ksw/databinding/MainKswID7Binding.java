package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class MainKswID7Binding extends ViewDataBinding {
    public final ImageView controlBtn;
    public final ImageView ivBottomHome;
    public final ImageView ivBottomLine;
    public final ImageView ivBottomPoint;
    public final ImageView ivTopLine;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected MainKswID7Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView controlBtn2, ImageView ivBottomHome2, ImageView ivBottomLine2, ImageView ivBottomPoint2, ImageView ivTopLine2, ViewPager viewPage2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.controlBtn = controlBtn2;
        this.ivBottomHome = ivBottomHome2;
        this.ivBottomLine = ivBottomLine2;
        this.ivBottomPoint = ivBottomPoint2;
        this.ivTopLine = ivTopLine2;
        this.viewPage = viewPage2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static MainKswID7Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainKswID7Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MainKswID7Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_ksw_id7, root, attachToRoot, component);
    }

    public static MainKswID7Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainKswID7Binding inflate(LayoutInflater inflater, Object component) {
        return (MainKswID7Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_ksw_id7, (ViewGroup) null, false, component);
    }

    public static MainKswID7Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainKswID7Binding bind(View view, Object component) {
        return (MainKswID7Binding) bind(component, view, R.layout.activity_main_ksw_id7);
    }
}
