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
import com.wits.ksw.launcher.als_id7_ui.view.CustomCircleProgressView;
import com.wits.ksw.launcher.als_id7_ui.view.CustomRadiusImageView;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinMusicLayout;

/* loaded from: classes7.dex */
public abstract class AlsId7UiMusicSubViewBinding extends ViewDataBinding {
    public final ImageView albumImageView;
    public final TextView albumTextView;
    public final CustomRadiusImageView alsImage;
    public final ImageView alsMiddlePoint;
    public final CustomCircleProgressView alsProcess;
    public final ImageView artistImageView;
    public final TextView artistTextView;
    public final TextView currentTimeTextView;
    public final CustomSkinMusicLayout imageFrameLayout;
    public final ImageView imageView6;
    public final TextView inAlbumTextView;
    public final TextView inArtistTextView;
    public final TextView inCurrentTimeTextView;
    public final TextView inNameTextView;
    public final TextView inTimeMiddleTv;
    public final TextView inTotalTimeTextView;
    @Bindable
    protected LauncherViewModel mMediaViewModel;
    public final ConstraintLayout musicConstraintLayout;
    public final ImageView musicImageView;
    public final ImageView nameImageView;
    public final TextView nameTextView;
    public final SeekBar seekBar;
    public final TextView timeMiddleTv;
    public final TextView totalTimeTextView;

    public abstract void setMediaViewModel(LauncherViewModel MediaViewModel);

    protected AlsId7UiMusicSubViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView albumImageView, TextView albumTextView, CustomRadiusImageView alsImage, ImageView alsMiddlePoint, CustomCircleProgressView alsProcess, ImageView artistImageView, TextView artistTextView, TextView currentTimeTextView, CustomSkinMusicLayout imageFrameLayout, ImageView imageView6, TextView inAlbumTextView, TextView inArtistTextView, TextView inCurrentTimeTextView, TextView inNameTextView, TextView inTimeMiddleTv, TextView inTotalTimeTextView, ConstraintLayout musicConstraintLayout, ImageView musicImageView, ImageView nameImageView, TextView nameTextView, SeekBar seekBar, TextView timeMiddleTv, TextView totalTimeTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.albumImageView = albumImageView;
        this.albumTextView = albumTextView;
        this.alsImage = alsImage;
        this.alsMiddlePoint = alsMiddlePoint;
        this.alsProcess = alsProcess;
        this.artistImageView = artistImageView;
        this.artistTextView = artistTextView;
        this.currentTimeTextView = currentTimeTextView;
        this.imageFrameLayout = imageFrameLayout;
        this.imageView6 = imageView6;
        this.inAlbumTextView = inAlbumTextView;
        this.inArtistTextView = inArtistTextView;
        this.inCurrentTimeTextView = inCurrentTimeTextView;
        this.inNameTextView = inNameTextView;
        this.inTimeMiddleTv = inTimeMiddleTv;
        this.inTotalTimeTextView = inTotalTimeTextView;
        this.musicConstraintLayout = musicConstraintLayout;
        this.musicImageView = musicImageView;
        this.nameImageView = nameImageView;
        this.nameTextView = nameTextView;
        this.seekBar = seekBar;
        this.timeMiddleTv = timeMiddleTv;
        this.totalTimeTextView = totalTimeTextView;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiMusicSubViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_music_view, root, attachToRoot, component);
    }

    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiMusicSubViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_ui_sub_music_view, null, false, component);
    }

    public static AlsId7UiMusicSubViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMusicSubViewBinding bind(View view, Object component) {
        return (AlsId7UiMusicSubViewBinding) bind(component, view, C0899R.C0902layout.als_id7_ui_sub_music_view);
    }
}
