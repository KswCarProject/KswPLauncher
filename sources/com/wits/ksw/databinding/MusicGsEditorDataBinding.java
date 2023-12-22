package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.view.RoundAngleImageView;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class MusicGsEditorDataBinding extends ViewDataBinding {
    public final ImageView gsId8IconEditBg;
    public final ImageView id8GsMusicEdit;
    public final RoundAngleImageView ivMusicAlbum;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final ImageView musicId8GsNext;
    public final ImageView musicId8GsPlayPause;
    public final ImageView musicId8GsPrev;
    public final TextView tvSongTitle;
    public final TextView tvStrTime;
    public final TextView tvTotalTime;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected MusicGsEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gsId8IconEditBg, ImageView id8GsMusicEdit, RoundAngleImageView ivMusicAlbum, ImageView musicId8GsNext, ImageView musicId8GsPlayPause, ImageView musicId8GsPrev, TextView tvSongTitle, TextView tvStrTime, TextView tvTotalTime) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gsId8IconEditBg = gsId8IconEditBg;
        this.id8GsMusicEdit = id8GsMusicEdit;
        this.ivMusicAlbum = ivMusicAlbum;
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

    public static MusicGsEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicGsEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicGsEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_gs_music_edit, root, attachToRoot, component);
    }

    public static MusicGsEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicGsEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (MusicGsEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_gs_music_edit, null, false, component);
    }

    public static MusicGsEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicGsEditorDataBinding bind(View view, Object component) {
        return (MusicGsEditorDataBinding) bind(component, view, C0899R.C0902layout.fragment_gs_music_edit);
    }
}
