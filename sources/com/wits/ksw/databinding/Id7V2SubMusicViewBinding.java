package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

/* loaded from: classes7.dex */
public abstract class Id7V2SubMusicViewBinding extends ViewDataBinding {
    public final ImageView albumImageView;
    public final TextView albumTextView;
    public final ImageView artistImageView;
    public final TextView artistTextView;
    public final TextView currentTimeTextView;
    public final CustomBmwMusicLayout imageFrameLayout;
    public final ImageView imageView6;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final ConstraintLayout musicConstraintLayout;
    public final ImageView musicImageView;
    public final ImageView nameImageView;
    public final TextView nameTextView;
    public final SeekBar seekBar;
    public final TextView totalTimeTextView;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected Id7V2SubMusicViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView albumImageView, TextView albumTextView, ImageView artistImageView, TextView artistTextView, TextView currentTimeTextView, CustomBmwMusicLayout imageFrameLayout, ImageView imageView6, ConstraintLayout musicConstraintLayout, ImageView musicImageView, ImageView nameImageView, TextView nameTextView, SeekBar seekBar, TextView totalTimeTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.albumImageView = albumImageView;
        this.albumTextView = albumTextView;
        this.artistImageView = artistImageView;
        this.artistTextView = artistTextView;
        this.currentTimeTextView = currentTimeTextView;
        this.imageFrameLayout = imageFrameLayout;
        this.imageView6 = imageView6;
        this.musicConstraintLayout = musicConstraintLayout;
        this.musicImageView = musicImageView;
        this.nameImageView = nameImageView;
        this.nameTextView = nameTextView;
        this.seekBar = seekBar;
        this.totalTimeTextView = totalTimeTextView;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static Id7V2SubMusicViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubMusicViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7V2SubMusicViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_v2_sub_music_view, root, attachToRoot, component);
    }

    public static Id7V2SubMusicViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubMusicViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7V2SubMusicViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id7_v2_sub_music_view, null, false, component);
    }

    public static Id7V2SubMusicViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7V2SubMusicViewBinding bind(View view, Object component) {
        return (Id7V2SubMusicViewBinding) bind(component, view, C0899R.C0902layout.id7_v2_sub_music_view);
    }
}
