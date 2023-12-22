package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

/* loaded from: classes7.dex */
public abstract class DashVideoFragment_V2 extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;

    public abstract void setDashVideoViewModel(AlsID7ViewModel DashVideoViewModel);

    protected DashVideoFragment_V2(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static DashVideoFragment_V2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashVideoFragment_V2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DashVideoFragment_V2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_fragment_dash_video_v2, root, attachToRoot, component);
    }

    public static DashVideoFragment_V2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashVideoFragment_V2 inflate(LayoutInflater inflater, Object component) {
        return (DashVideoFragment_V2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_fragment_dash_video_v2, null, false, component);
    }

    public static DashVideoFragment_V2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashVideoFragment_V2 bind(View view, Object component) {
        return (DashVideoFragment_V2) bind(component, view, C0899R.C0902layout.als_id7_fragment_dash_video_v2);
    }
}
