package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;

/* loaded from: classes7.dex */
public abstract class ID6One extends ViewDataBinding {
    public final RecyclerView id6RecyclerView;

    protected ID6One(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView id6RecyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6RecyclerView = id6RecyclerView;
    }

    public static ID6One inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6One inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6One) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_one, root, attachToRoot, component);
    }

    public static ID6One inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6One inflate(LayoutInflater inflater, Object component) {
        return (ID6One) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_one, null, false, component);
    }

    public static ID6One bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6One bind(View view, Object component) {
        return (ID6One) bind(component, view, C0899R.C0902layout.id6_one);
    }
}
