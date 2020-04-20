package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.wits.ksw.R;

public abstract class AudiNaviBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout linearLayout4;
    @NonNull
    public final ListView naviListView;

    protected AudiNaviBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout linearLayout42, ListView naviListView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linearLayout4 = linearLayout42;
        this.naviListView = naviListView2;
    }

    @NonNull
    public static AudiNaviBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiNaviBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiNaviBinding) DataBindingUtil.inflate(inflater, R.layout.audi_navi, root, attachToRoot, component);
    }

    @NonNull
    public static AudiNaviBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiNaviBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiNaviBinding) DataBindingUtil.inflate(inflater, R.layout.audi_navi, (ViewGroup) null, false, component);
    }

    public static AudiNaviBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiNaviBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiNaviBinding) bind(component, view, R.layout.audi_navi);
    }
}
