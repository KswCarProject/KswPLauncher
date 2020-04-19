package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.bmwevoid6gs.BmwId6gsViewMode;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsViewPager;

public abstract class ActivityMainId6GsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView id6GsLeftBtn;
    @NonNull
    public final Bmwid6gsViewPager id6GsMainViewPager;
    @NonNull
    public final ImageView id6GsRightBtn;
    @Bindable
    protected BmwId6gsViewMode mVm;

    public abstract void setVm(@Nullable BmwId6gsViewMode bmwId6gsViewMode);

    protected ActivityMainId6GsBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView id6GsLeftBtn2, Bmwid6gsViewPager id6GsMainViewPager2, ImageView id6GsRightBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6GsLeftBtn = id6GsLeftBtn2;
        this.id6GsMainViewPager = id6GsMainViewPager2;
        this.id6GsRightBtn = id6GsRightBtn2;
    }

    @Nullable
    public BmwId6gsViewMode getVm() {
        return this.mVm;
    }

    @NonNull
    public static ActivityMainId6GsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainId6GsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainId6GsBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_id6_gs, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainId6GsBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainId6GsBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainId6GsBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_id6_gs, (ViewGroup) null, false, component);
    }

    public static ActivityMainId6GsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainId6GsBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainId6GsBinding) bind(component, view, R.layout.activity_main_id6_gs);
    }
}
