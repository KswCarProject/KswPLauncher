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
public abstract class VideoEditorDataBinding extends ViewDataBinding {
    public final RelativeLayout layout;
    @Bindable
    protected LauncherViewModel mMediaViewModel;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected VideoEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, RelativeLayout layout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.layout = layout;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static VideoEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (VideoEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_video_edit, root, attachToRoot, component);
    }

    public static VideoEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (VideoEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_video_edit, null, false, component);
    }

    public static VideoEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoEditorDataBinding bind(View view, Object component) {
        return (VideoEditorDataBinding) bind(component, view, C0899R.C0902layout.fragment_video_edit);
    }
}
