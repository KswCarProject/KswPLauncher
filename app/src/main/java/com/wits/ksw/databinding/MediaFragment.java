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
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class MediaFragment extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mMediaViewModel;

    public abstract void setMediaViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected MediaFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    @Nullable
    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    @NonNull
    public static MediaFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MediaFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (MediaFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_media, root, attachToRoot, component);
    }

    @NonNull
    public static MediaFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MediaFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (MediaFragment) DataBindingUtil.inflate(inflater, R.layout.id7_fragment_media, (ViewGroup) null, false, component);
    }

    public static MediaFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static MediaFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (MediaFragment) bind(component, view, R.layout.id7_fragment_media);
    }
}
