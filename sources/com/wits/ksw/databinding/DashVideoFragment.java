package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public abstract class DashVideoFragment extends ViewDataBinding {
    @Bindable
    protected AlsID7ViewModel mDashVideoViewModel;

    public abstract void setDashVideoViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    protected DashVideoFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    @Nullable
    public AlsID7ViewModel getDashVideoViewModel() {
        return this.mDashVideoViewModel;
    }

    @NonNull
    public static DashVideoFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DashVideoFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (DashVideoFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_dash_video, root, attachToRoot, component);
    }

    @NonNull
    public static DashVideoFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DashVideoFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (DashVideoFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_dash_video, (ViewGroup) null, false, component);
    }

    public static DashVideoFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DashVideoFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (DashVideoFragment) bind(component, view, R.layout.als_id7_fragment_dash_video);
    }
}
