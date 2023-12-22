package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class MusicEditorDataBinding extends ViewDataBinding {
    public final ImageView ivMainIconMusic;
    public final LinearLayout layout;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final TextView tvSinger;
    public final TextView tvSongTitle;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected MusicEditorDataBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivMainIconMusic, LinearLayout layout, TextView tvSinger, TextView tvSongTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivMainIconMusic = ivMainIconMusic;
        this.layout = layout;
        this.tvSinger = tvSinger;
        this.tvSongTitle = tvSongTitle;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static MusicEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicEditorDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (MusicEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_music_edit, root, attachToRoot, component);
    }

    public static MusicEditorDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicEditorDataBinding inflate(LayoutInflater inflater, Object component) {
        return (MusicEditorDataBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_music_edit, null, false, component);
    }

    public static MusicEditorDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MusicEditorDataBinding bind(View view, Object component) {
        return (MusicEditorDataBinding) bind(component, view, C0899R.C0902layout.fragment_music_edit);
    }
}
