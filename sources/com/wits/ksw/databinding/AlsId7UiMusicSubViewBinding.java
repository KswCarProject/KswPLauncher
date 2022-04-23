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
import com.wits.ksw.launcher.als_id7_ui.view.CustomCircleProgressView;
import com.wits.ksw.launcher.als_id7_ui.view.CustomRadiusImageView;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.CustomSkinMusicLayout;

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

    public abstract void setMediaViewModel(LauncherViewModel launcherViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AlsId7UiMusicSubViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView albumImageView2, TextView albumTextView2, CustomRadiusImageView alsImage2, ImageView alsMiddlePoint2, CustomCircleProgressView alsProcess2, ImageView artistImageView2, TextView artistTextView2, TextView currentTimeTextView2, CustomSkinMusicLayout imageFrameLayout2, ImageView imageView62, TextView inAlbumTextView2, TextView inArtistTextView2, TextView inCurrentTimeTextView2, TextView inNameTextView2, TextView inTimeMiddleTv2, TextView inTotalTimeTextView2, ConstraintLayout musicConstraintLayout2, ImageView musicImageView2, ImageView nameImageView2, TextView nameTextView2, SeekBar seekBar2, TextView timeMiddleTv2, TextView totalTimeTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.albumImageView = albumImageView2;
        this.albumTextView = albumTextView2;
        this.alsImage = alsImage2;
        this.alsMiddlePoint = alsMiddlePoint2;
        this.alsProcess = alsProcess2;
        this.artistImageView = artistImageView2;
        this.artistTextView = artistTextView2;
        this.currentTimeTextView = currentTimeTextView2;
        this.imageFrameLayout = imageFrameLayout2;
        this.imageView6 = imageView62;
        this.inAlbumTextView = inAlbumTextView2;
        this.inArtistTextView = inArtistTextView2;
        this.inCurrentTimeTextView = inCurrentTimeTextView2;
        this.inNameTextView = inNameTextView2;
        this.inTimeMiddleTv = inTimeMiddleTv2;
        this.inTotalTimeTextView = inTotalTimeTextView2;
        this.musicConstraintLayout = musicConstraintLayout2;
        this.musicImageView = musicImageView2;
        this.nameImageView = nameImageView2;
        this.nameTextView = nameTextView2;
        this.seekBar = seekBar2;
        this.timeMiddleTv = timeMiddleTv2;
        this.totalTimeTextView = totalTimeTextView2;
    }

    public LauncherViewModel getMediaViewModel() {
        return this.mMediaViewModel;
    }

    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7UiMusicSubViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_ui_sub_music_view, root, attachToRoot, component);
    }

    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMusicSubViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7UiMusicSubViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_ui_sub_music_view, (ViewGroup) null, false, component);
    }

    public static AlsId7UiMusicSubViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7UiMusicSubViewBinding bind(View view, Object component) {
        return (AlsId7UiMusicSubViewBinding) bind(component, view, R.layout.als_id7_ui_sub_music_view);
    }
}
