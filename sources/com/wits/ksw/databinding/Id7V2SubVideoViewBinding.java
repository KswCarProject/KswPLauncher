package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

public abstract class Id7V2SubVideoViewBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView textView2;
    public final TextView textView3;
    public final ConstraintLayout videoConstraintLayout;
    public final CustomBmwImageView videoImageView;

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected Id7V2SubVideoViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView textView22, TextView textView32, ConstraintLayout videoConstraintLayout2, CustomBmwImageView videoImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.textView2 = textView22;
        this.textView3 = textView32;
        this.videoConstraintLayout = videoConstraintLayout2;
        this.videoImageView = videoImageView2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static Id7V2SubVideoViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubVideoViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7V2SubVideoViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_sub_video_view, root, attachToRoot, component);
    }

    public static Id7V2SubVideoViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubVideoViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7V2SubVideoViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_v2_sub_video_view, (ViewGroup) null, false, component);
    }

    public static Id7V2SubVideoViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubVideoViewBinding bind(View view, Object component) {
        return (Id7V2SubVideoViewBinding) bind(component, view, R.layout.id7_v2_sub_video_view);
    }
}
