package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.wits.ksw.R;

public abstract class AudiNaviBinding extends ViewDataBinding {
    public final ConstraintLayout linearLayout4;
    public final ListView naviListView;

    protected AudiNaviBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout linearLayout42, ListView naviListView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linearLayout4 = linearLayout42;
        this.naviListView = naviListView2;
    }

    public static AudiNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiNaviBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_navi, root, attachToRoot, component);
    }

    public static AudiNaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiNaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiNaviBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_navi, (ViewGroup) null, false, component);
    }

    public static AudiNaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiNaviBinding bind(View view, Object component) {
        return (AudiNaviBinding) bind(component, view, R.layout.audi_navi);
    }
}
