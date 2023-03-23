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
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class MusicDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final ImageView ivMusicAlbum;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView tvSinger;
    public final TextView tvSongTitle;

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    protected MusicDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask2, ImageView ivMusicAlbum2, RelativeLayout llContainer2, TextView tvSinger2, TextView tvSongTitle2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask2;
        this.ivMusicAlbum = ivMusicAlbum2;
        this.llContainer = llContainer2;
        this.tvSinger = tvSinger2;
        this.tvSongTitle = tvSongTitle2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static MusicDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_music, root, attachToRoot, component);
    }

    public static MusicDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataBinding inflate(LayoutInflater inflater, Object component) {
        return (MusicDataBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_music, (ViewGroup) null, false, component);
    }

    public static MusicDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicDataBinding bind(View view, Object component) {
        return (MusicDataBinding) bind(component, view, R.layout.fragment_music);
    }
}
