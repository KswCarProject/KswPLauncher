package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsViewPager;

public abstract class ActivityMainId6GsBinding extends ViewDataBinding {
    public final ImageView id6GsLeftBtn;
    public final Bmwid6gsViewPager id6GsMainViewPager;
    public final ImageView id6GsRightBtn;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(BmwId6gsViewMode bmwId6gsViewMode);

    protected ActivityMainId6GsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6GsLeftBtn2, Bmwid6gsViewPager id6GsMainViewPager2, ImageView id6GsRightBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6GsLeftBtn = id6GsLeftBtn2;
        this.id6GsMainViewPager = id6GsMainViewPager2;
        this.id6GsRightBtn = id6GsRightBtn2;
    }

    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    public static ActivityMainId6GsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainId6GsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainId6GsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_id6_gs, root, attachToRoot, component);
    }

    public static ActivityMainId6GsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainId6GsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainId6GsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_id6_gs, (ViewGroup) null, false, component);
    }

    public static ActivityMainId6GsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainId6GsBinding bind(View view, Object component) {
        return (ActivityMainId6GsBinding) bind(component, view, R.layout.activity_main_id6_gs);
    }
}
