package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class VideoEditorPempDataBinding extends ViewDataBinding {
    public final RelativeLayout layout;
    @Bindable
    protected LauncherViewModel mMediaViewModel;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected VideoEditorPempDataBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout layout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.layout = layout;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static VideoEditorPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoEditorPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (VideoEditorPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_video_edit, root, attachToRoot, component);
    }

    public static VideoEditorPempDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoEditorPempDataBinding inflate(LayoutInflater inflater, Object component) {
        return (VideoEditorPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_video_edit, null, false, component);
    }

    public static VideoEditorPempDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoEditorPempDataBinding bind(View view, Object component) {
        return (VideoEditorPempDataBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_video_edit);
    }
}
