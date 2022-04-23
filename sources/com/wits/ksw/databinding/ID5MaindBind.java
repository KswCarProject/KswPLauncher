package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;

public abstract class ID5MaindBind extends ViewDataBinding {
    public final ViewPager id5MainViewPager;

    protected ID5MaindBind(Object _bindingComponent, View _root, int _localFieldCount, ViewPager id5MainViewPager2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id5MainViewPager = id5MainViewPager2;
    }

    public static ID5MaindBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID5MaindBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID5MaindBind) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_id5, root, attachToRoot, component);
    }

    public static ID5MaindBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID5MaindBind inflate(LayoutInflater inflater, Object component) {
        return (ID5MaindBind) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_id5, (ViewGroup) null, false, component);
    }

    public static ID5MaindBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID5MaindBind bind(View view, Object component) {
        return (ID5MaindBind) bind(component, view, R.layout.activity_main_id5);
    }
}
