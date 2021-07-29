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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

public abstract class Id7SubMusicViewBinding extends ViewDataBinding {
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

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Id7SubMusicViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView albumImageView2, TextView albumTextView2, ImageView artistImageView2, TextView artistTextView2, TextView currentTimeTextView2, CustomBmwMusicLayout imageFrameLayout2, ImageView imageView62, ConstraintLayout musicConstraintLayout2, ImageView musicImageView2, ImageView nameImageView2, TextView nameTextView2, SeekBar seekBar2, TextView totalTimeTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.albumImageView = albumImageView2;
        this.albumTextView = albumTextView2;
        this.artistImageView = artistImageView2;
        this.artistTextView = artistTextView2;
        this.currentTimeTextView = currentTimeTextView2;
        this.imageFrameLayout = imageFrameLayout2;
        this.imageView6 = imageView62;
        this.musicConstraintLayout = musicConstraintLayout2;
        this.musicImageView = musicImageView2;
        this.nameImageView = nameImageView2;
        this.nameTextView = nameTextView2;
        this.seekBar = seekBar2;
        this.totalTimeTextView = totalTimeTextView2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static Id7SubMusicViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubMusicViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Id7SubMusicViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_music_view, root, attachToRoot, component);
    }

    public static Id7SubMusicViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubMusicViewBinding inflate(LayoutInflater inflater, Object component) {
        return (Id7SubMusicViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.id7_sub_music_view, (ViewGroup) null, false, component);
    }

    public static Id7SubMusicViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Id7SubMusicViewBinding bind(View view, Object component) {
        return (Id7SubMusicViewBinding) bind(component, view, R.layout.id7_sub_music_view);
    }
}
