package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;

public abstract class ID6One extends ViewDataBinding {
    @NonNull
    public final RecyclerView id6RecyclerView;

    protected ID6One(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, RecyclerView id6RecyclerView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6RecyclerView = id6RecyclerView2;
    }

    @NonNull
    public static ID6One inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6One inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ID6One) DataBindingUtil.inflate(inflater, R.layout.id6_one, root, attachToRoot, component);
    }

    @NonNull
    public static ID6One inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6One inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ID6One) DataBindingUtil.inflate(inflater, R.layout.id6_one, (ViewGroup) null, false, component);
    }

    public static ID6One bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ID6One bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ID6One) bind(component, view, R.layout.id6_one);
    }
}
