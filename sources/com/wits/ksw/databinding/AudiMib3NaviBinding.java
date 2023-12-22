package com.wits.ksw.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.wits.ksw.C0899R;

/* loaded from: classes7.dex */
public abstract class AudiMib3NaviBinding extends ViewDataBinding {
    public final ListView naviListView;
    public final AppCompatTextView title;
    public final View titleDivider;
    public final View vDivider;

    protected AudiMib3NaviBinding(Object _bindingComponent, View _root, int _localFieldCount, ListView naviListView, AppCompatTextView title, View titleDivider, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.naviListView = naviListView;
        this.title = title;
        this.titleDivider = titleDivider;
        this.vDivider = vDivider;
    }

    public static AudiMib3NaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3NaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3NaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_navi, root, attachToRoot, component);
    }

    public static AudiMib3NaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3NaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3NaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_navi, null, false, component);
    }

    public static AudiMib3NaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3NaviBinding bind(View view, Object component) {
        return (AudiMib3NaviBinding) bind(component, view, C0899R.C0902layout.audi_mib3_navi);
    }
}
