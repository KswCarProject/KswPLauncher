package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.wits.ksw.C0899R;

/* loaded from: classes7.dex */
public abstract class AudiNaviBinding extends ViewDataBinding {
    public final ConstraintLayout linearLayout4;
    public final ListView naviListView;

    protected AudiNaviBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout linearLayout4, ListView naviListView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.linearLayout4 = linearLayout4;
        this.naviListView = naviListView;
    }

    public static AudiNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiNaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_navi, root, attachToRoot, component);
    }

    public static AudiNaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiNaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiNaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_navi, null, false, component);
    }

    public static AudiNaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiNaviBinding bind(View view, Object component) {
        return (AudiNaviBinding) bind(component, view, C0899R.C0902layout.audi_navi);
    }
}
