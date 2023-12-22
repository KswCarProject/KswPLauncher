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
import com.wits.ksw.launcher.bmw_id8_ui.view.RoundAngleImageView;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class MusicDataGsBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final RoundAngleImageView ivMusicAlbum;
    public final RelativeLayout llContainerGs;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final ImageView musicId8GsNext;
    public final ImageView musicId8GsPlayPause;
    public final ImageView musicId8GsPrev;
    public final TextView tvSongTitle;
    public final TextView tvStrTime;
    public final TextView tvTotalTime;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected MusicDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, RoundAngleImageView ivMusicAlbum, RelativeLayout llContainerGs, ImageView musicId8GsNext, ImageView musicId8GsPlayPause, ImageView musicId8GsPrev, TextView tvSongTitle, TextView tvStrTime, TextView tvTotalTime) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.ivMusicAlbum = ivMusicAlbum;
        this.llContainerGs = llContainerGs;
        this.musicId8GsNext = musicId8GsNext;
        this.musicId8GsPlayPause = musicId8GsPlayPause;
        this.musicId8GsPrev = musicId8GsPrev;
        this.tvSongTitle = tvSongTitle;
        this.tvStrTime = tvStrTime;
        this.tvTotalTime = tvTotalTime;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static MusicDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_music_gs, root, attachToRoot, component);
    }

    public static MusicDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (MusicDataGsBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_music_gs, null, false, component);
    }

    public static MusicDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataGsBinding bind(View view, Object component) {
        return (MusicDataGsBinding) bind(component, view, C0899R.C0902layout.fragment_music_gs);
    }
}
