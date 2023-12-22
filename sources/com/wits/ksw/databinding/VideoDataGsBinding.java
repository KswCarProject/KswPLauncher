package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class VideoDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView tvSongTitle;
    public final TextView tvStrTime;
    public final TextView tvTotalTime;
    public final ImageView videoId8GsNext;
    public final ImageView videoId8GsPlayPause;
    public final ImageView videoId8GsPrev;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected VideoDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RelativeLayout llContainerGs, TextView tvSongTitle, TextView tvStrTime, TextView tvTotalTime, ImageView videoId8GsNext, ImageView videoId8GsPlayPause, ImageView videoId8GsPrev) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.llContainerGs = llContainerGs;
        this.tvSongTitle = tvSongTitle;
        this.tvStrTime = tvStrTime;
        this.tvTotalTime = tvTotalTime;
        this.videoId8GsNext = videoId8GsNext;
        this.videoId8GsPlayPause = videoId8GsPlayPause;
        this.videoId8GsPrev = videoId8GsPrev;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static VideoDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (VideoDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_video_gs, root, attachToRoot, component);
    }

    public static VideoDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (VideoDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_video_gs, null, false, component);
    }

    public static VideoDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoDataGsBinding bind(View view, Object component) {
        return (VideoDataGsBinding) bind(component, view, C0899R.C0902layout.fragment_video_gs);
    }
}
