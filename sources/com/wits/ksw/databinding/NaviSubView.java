package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class NaviSubView extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mNaviViewModel;
    public final ConstraintLayout naviConstraintLayout;
    public final CustomBmwImageView naviImageView;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setNaviViewModel(LauncherViewModel launcherViewModel);

    protected NaviSubView(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout naviConstraintLayout2, CustomBmwImageView naviImageView2, TextView textView22, TextView textView32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviConstraintLayout = naviConstraintLayout2;
        this.naviImageView = naviImageView2;
        this.textView2 = textView22;
        this.textView3 = textView32;
    }

    public LauncherViewModel getNaviViewModel() {
        return this.mNaviViewModel;
    }

    public static NaviSubView inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviSubView inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (NaviSubView) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_navi_view, root, attachToRoot, component);
    }

    public static NaviSubView inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviSubView inflate(LayoutInflater inflater, Object component) {
        return (NaviSubView) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_navi_view, (ViewGroup) null, false, component);
    }

    public static NaviSubView bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static NaviSubView bind(View view, Object component) {
        return (NaviSubView) bind(component, view, R.layout.id7_sub_navi_view);
    }
}
