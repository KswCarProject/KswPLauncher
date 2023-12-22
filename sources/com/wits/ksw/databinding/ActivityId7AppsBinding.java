package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AppViewModel;
import com.wits.ksw.launcher.view.CustomGridView;

/* loaded from: classes7.dex */
public abstract class ActivityId7AppsBinding extends ViewDataBinding {
    public final CustomGridView appGridView;
    @Bindable
    protected AppViewModel mAppViewModel;

    public abstract void setAppViewModel(AppViewModel AppViewModel);

    protected ActivityId7AppsBinding(Object _bindingComponent, View _root, int _localFieldCount, CustomGridView appGridView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appGridView = appGridView;
    }

    public AppViewModel getAppViewModel() {
        return this.mAppViewModel;
    }

    public static ActivityId7AppsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityId7AppsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityId7AppsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_id7_apps, root, attachToRoot, component);
    }

    public static ActivityId7AppsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityId7AppsBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityId7AppsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_id7_apps, null, false, component);
    }

    public static ActivityId7AppsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityId7AppsBinding bind(View view, Object component) {
        return (ActivityId7AppsBinding) bind(component, view, C0899R.C0902layout.activity_id7_apps);
    }
}
