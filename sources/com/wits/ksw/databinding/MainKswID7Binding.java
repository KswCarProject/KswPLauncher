package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p001v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class MainKswID7Binding extends ViewDataBinding {
    public final ImageView controlBtn;
    public final ImageView ivBottomHome;
    public final ImageView ivBottomLine;
    public final ImageView ivBottomPoint;
    public final ImageView ivTopLine;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ViewPager viewPage;

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected MainKswID7Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView controlBtn, ImageView ivBottomHome, ImageView ivBottomLine, ImageView ivBottomPoint, ImageView ivTopLine, ViewPager viewPage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.controlBtn = controlBtn;
        this.ivBottomHome = ivBottomHome;
        this.ivBottomLine = ivBottomLine;
        this.ivBottomPoint = ivBottomPoint;
        this.ivTopLine = ivTopLine;
        this.viewPage = viewPage;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static MainKswID7Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainKswID7Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MainKswID7Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_ksw_id7, root, attachToRoot, component);
    }

    public static MainKswID7Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainKswID7Binding inflate(LayoutInflater inflater, Object component) {
        return (MainKswID7Binding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_ksw_id7, null, false, component);
    }

    public static MainKswID7Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainKswID7Binding bind(View view, Object component) {
        return (MainKswID7Binding) bind(component, view, C0899R.C0902layout.activity_main_ksw_id7);
    }
}
