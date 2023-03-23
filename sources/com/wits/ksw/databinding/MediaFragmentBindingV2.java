package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class MediaFragmentBindingV2 extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mMediaViewModel;

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected MediaFragmentBindingV2(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static MediaFragmentBindingV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MediaFragmentBindingV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MediaFragmentBindingV2) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_fragment_media, root, attachToRoot, component);
    }

    public static MediaFragmentBindingV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MediaFragmentBindingV2 inflate(LayoutInflater inflater, Object component) {
        return (MediaFragmentBindingV2) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_fragment_media, (ViewGroup) null, false, component);
    }

    public static MediaFragmentBindingV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MediaFragmentBindingV2 bind(View view, Object component) {
        return (MediaFragmentBindingV2) bind(component, view, R.layout.id7_v2_fragment_media);
    }
}
