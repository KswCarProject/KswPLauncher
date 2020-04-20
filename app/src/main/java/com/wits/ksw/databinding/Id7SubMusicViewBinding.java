package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    @NonNull
    public final ImageView albumImageView;
    @NonNull
    public final TextView albumTextView;
    @NonNull
    public final ImageView artistImageView;
    @NonNull
    public final TextView artistTextView;
    @NonNull
    public final TextView currentTimeTextView;
    @NonNull
    public final CustomBmwMusicLayout imageFrameLayout;
    @NonNull
    public final ImageView imageView6;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    @NonNull
    public final ConstraintLayout musicConstraintLayout;
    @NonNull
    public final ImageView musicImageView;
    @NonNull
    public final ImageView nameImageView;
    @NonNull
    public final TextView nameTextView;
    @NonNull
    public final SeekBar seekBar;
    @NonNull
    public final TextView totalTimeTextView;

    public abstract void setMediaViewModel(@Nullable LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Id7SubMusicViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView albumImageView2, TextView albumTextView2, ImageView artistImageView2, TextView artistTextView2, TextView currentTimeTextView2, CustomBmwMusicLayout imageFrameLayout2, ImageView imageView62, ConstraintLayout musicConstraintLayout2, ImageView musicImageView2, ImageView nameImageView2, TextView nameTextView2, SeekBar seekBar2, TextView totalTimeTextView2) {
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

    @Nullable
    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    @NonNull
    public static Id7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Id7SubMusicViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_music_view, root, attachToRoot, component);
    }

    @NonNull
    public static Id7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Id7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Id7SubMusicViewBinding) DataBindingUtil.inflate(inflater, R.layout.id7_sub_music_view, (ViewGroup) null, false, component);
    }

    public static Id7SubMusicViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Id7SubMusicViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Id7SubMusicViewBinding) bind(component, view, R.layout.id7_sub_music_view);
    }
}
