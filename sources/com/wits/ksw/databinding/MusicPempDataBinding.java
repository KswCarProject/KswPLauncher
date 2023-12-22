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
import com.wits.ksw.launcher.view.MarqueeTextView;

/* loaded from: classes7.dex */
public abstract class MusicPempDataBinding extends ViewDataBinding {
    public final ImageView ivMask;
    public final ImageView ivMusicAlbum;
    public final RelativeLayout llContainer;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final ImageView pempId8MusicNext;
    public final ImageView pempId8MusicPlay;
    public final ImageView pempId8MusicPrev;
    public final TextView tvSinger;
    public final MarqueeTextView tvSongTitle;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected MusicPempDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMask, ImageView ivMusicAlbum, RelativeLayout llContainer, ImageView pempId8MusicNext, ImageView pempId8MusicPlay, ImageView pempId8MusicPrev, TextView tvSinger, MarqueeTextView tvSongTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMask = ivMask;
        this.ivMusicAlbum = ivMusicAlbum;
        this.llContainer = llContainer;
        this.pempId8MusicNext = pempId8MusicNext;
        this.pempId8MusicPlay = pempId8MusicPlay;
        this.pempId8MusicPrev = pempId8MusicPrev;
        this.tvSinger = tvSinger;
        this.tvSongTitle = tvSongTitle;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static MusicPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicPempDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_music, root, attachToRoot, component);
    }

    public static MusicPempDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicPempDataBinding inflate(LayoutInflater inflater, Object component) {
        return (MusicPempDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_pemp_music, null, false, component);
    }

    public static MusicPempDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicPempDataBinding bind(View view, Object component) {
        return (MusicPempDataBinding) bind(component, view, C0899R.C0902layout.fragment_pemp_music);
    }
}
