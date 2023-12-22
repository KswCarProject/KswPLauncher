package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwImageView;

/* loaded from: classes7.dex */
public abstract class Id7SubVideoViewBinding extends ViewDataBinding {
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView textView2;
    public final TextView textView3;
    public final ConstraintLayout videoConstraintLayout;
    public final CustomBmwImageView videoImageView;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected Id7SubVideoViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView textView2, TextView textView3, ConstraintLayout videoConstraintLayout, CustomBmwImageView videoImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.videoConstraintLayout = videoConstraintLayout;
        this.videoImageView = videoImageView;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static Id7SubVideoViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubVideoViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubVideoViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_video_view, root, attachToRoot, component);
    }

    public static Id7SubVideoViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubVideoViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubVideoViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_sub_video_view, null, false, component);
    }

    public static Id7SubVideoViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubVideoViewBinding bind(View view, Object component) {
        return (Id7SubVideoViewBinding) bind(component, view, C0899R.C0902layout.id7_sub_video_view);
    }
}
