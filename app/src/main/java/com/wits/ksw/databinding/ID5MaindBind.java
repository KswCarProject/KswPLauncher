package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;

public abstract class ID5MaindBind extends ViewDataBinding {
    @NonNull
    public final ViewPager id5MainViewPager;

    protected ID5MaindBind(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ViewPager id5MainViewPager2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id5MainViewPager = id5MainViewPager2;
    }

    @NonNull
    public static ID5MaindBind inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID5MaindBind inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ID5MaindBind) DataBindingUtil.inflate(inflater, R.layout.activity_main_id5, root, attachToRoot, component);
    }

    @NonNull
    public static ID5MaindBind inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID5MaindBind inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ID5MaindBind) DataBindingUtil.inflate(inflater, R.layout.activity_main_id5, (ViewGroup) null, false, component);
    }

    public static ID5MaindBind bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ID5MaindBind bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ID5MaindBind) bind(component, view, R.layout.activity_main_id5);
    }
}
