package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public abstract class DashVideoFragment extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;

    public abstract void setDashVideoViewModel(AlsID7ViewModel alsID7ViewModel);

    protected DashVideoFragment(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    public static DashVideoFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashVideoFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DashVideoFragment) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_fragment_dash_video, root, attachToRoot, component);
    }

    public static DashVideoFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashVideoFragment inflate(LayoutInflater inflater, Object component) {
        return (DashVideoFragment) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_fragment_dash_video, (ViewGroup) null, false, component);
    }

    public static DashVideoFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashVideoFragment bind(View view, Object component) {
        return (DashVideoFragment) bind(component, view, R.layout.als_id7_fragment_dash_video);
    }
}
