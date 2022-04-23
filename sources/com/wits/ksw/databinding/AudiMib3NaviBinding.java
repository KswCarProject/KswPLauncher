package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.wits.ksw.R;

public abstract class AudiMib3NaviBinding extends ViewDataBinding {
    public final ListView naviListView;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    protected AudiMib3NaviBinding(Object _bindingComponent, View _root, int _localFieldCount, ListView naviListView2, AppCompatTextView title2, View titleDivider2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviListView = naviListView2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.vDivider = vDivider2;
    }

    public static AudiMib3NaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3NaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3NaviBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_navi, root, attachToRoot, component);
    }

    public static AudiMib3NaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3NaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3NaviBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_navi, (ViewGroup) null, false, component);
    }

    public static AudiMib3NaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3NaviBinding bind(View view, Object component) {
        return (AudiMib3NaviBinding) bind(component, view, R.layout.audi_mib3_navi);
    }
}
