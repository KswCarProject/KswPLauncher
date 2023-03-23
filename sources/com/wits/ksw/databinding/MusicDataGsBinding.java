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
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.view.RoundAngleImageView;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected MusicDataGsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, RoundAngleImageView ivMusicAlbum2, RelativeLayout llContainerGs2, ImageView musicId8GsNext2, ImageView musicId8GsPlayPause2, ImageView musicId8GsPrev2, TextView tvSongTitle2, TextView tvStrTime2, TextView tvTotalTime2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.ivMusicAlbum = ivMusicAlbum2;
        this.llContainerGs = llContainerGs2;
        this.musicId8GsNext = musicId8GsNext2;
        this.musicId8GsPlayPause = musicId8GsPlayPause2;
        this.musicId8GsPrev = musicId8GsPrev2;
        this.tvSongTitle = tvSongTitle2;
        this.tvStrTime = tvStrTime2;
        this.tvTotalTime = tvTotalTime2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static MusicDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataGsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_music_gs, root, attachToRoot, component);
    }

    public static MusicDataGsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataGsBinding inflate(LayoutInflater inflater, Object component) {
        return (MusicDataGsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_music_gs, (ViewGroup) null, false, component);
    }

    public static MusicDataGsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataGsBinding bind(View view, Object component) {
        return (MusicDataGsBinding) bind(component, view, R.layout.fragment_music_gs);
    }
}
