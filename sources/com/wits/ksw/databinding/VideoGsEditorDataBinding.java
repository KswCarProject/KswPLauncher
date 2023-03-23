package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class VideoGsEditorDataBinding extends ViewDataBinding {
    public final ImageView gsId8IconEditBg;
    public final ImageView id8GsVideoEdit;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView tvSongTitle;
    public final TextView tvStrTime;
    public final TextView tvTotalTime;
    public final ImageView videoId8GsNext;
    public final ImageView videoId8GsPlayPause;
    public final ImageView videoId8GsPrev;

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected VideoGsEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gsId8IconEditBg2, ImageView id8GsVideoEdit2, TextView tvSongTitle2, TextView tvStrTime2, TextView tvTotalTime2, ImageView videoId8GsNext2, ImageView videoId8GsPlayPause2, ImageView videoId8GsPrev2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsId8IconEditBg = gsId8IconEditBg2;
        this.id8GsVideoEdit = id8GsVideoEdit2;
        this.tvSongTitle = tvSongTitle2;
        this.tvStrTime = tvStrTime2;
        this.tvTotalTime = tvTotalTime2;
        this.videoId8GsNext = videoId8GsNext2;
        this.videoId8GsPlayPause = videoId8GsPlayPause2;
        this.videoId8GsPrev = videoId8GsPrev2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static VideoGsEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGsEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (VideoGsEditorDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_video_edit, root, attachToRoot, component);
    }

    public static VideoGsEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGsEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (VideoGsEditorDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_gs_video_edit, (ViewGroup) null, false, component);
    }

    public static VideoGsEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoGsEditorDataBinding bind(View view, Object component) {
        return (VideoGsEditorDataBinding) bind(component, view, R.layout.fragment_gs_video_edit);
    }
}
