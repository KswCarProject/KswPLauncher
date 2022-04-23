package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;

public abstract class ID6One extends ViewDataBinding {
    public final RecyclerView id6RecyclerView;

    protected ID6One(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView id6RecyclerView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6RecyclerView = id6RecyclerView2;
    }

    public static ID6One inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6One inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6One) ViewDataBinding.inflateInternal(inflater, R.layout.id6_one, root, attachToRoot, component);
    }

    public static ID6One inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6One inflate(LayoutInflater inflater, Object component) {
        return (ID6One) ViewDataBinding.inflateInternal(inflater, R.layout.id6_one, (ViewGroup) null, false, component);
    }

    public static ID6One bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6One bind(View view, Object component) {
        return (ID6One) bind(component, view, R.layout.id6_one);
    }
}
