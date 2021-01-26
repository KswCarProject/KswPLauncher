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
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.als_id7.view.MusicSeekBar;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

public abstract class AlsId7SubMusicViewBinding extends ViewDataBinding {
    @NonNull
    public final TextView albumTextView;
    @NonNull
    public final TextView artistTextView;
    @NonNull
    public final ImageView btnMusicNext;
    @NonNull
    public final ImageView btnMusicPause;
    @NonNull
    public final ImageView btnMusicPrev;
    @NonNull
    public final TextView currentTimeTextView;
    @NonNull
    public final CustomBmwMusicLayout imageFrameLayout;
    @Bindable
    protected AlsID7ViewModel mMusicPhoneViewModel;
    @NonNull
    public final ImageView musicAlbumBg;
    @NonNull
    public final ConstraintLayout musicConstraintLayout;
    @NonNull
    public final TextView musicSubTitle;
    @NonNull
    public final TextView musicTitle;
    @NonNull
    public final TextView nameTextView;
    @NonNull
    public final MusicSeekBar seekBar;
    @NonNull
    public final TextView totalTimeTextView;

    public abstract void setMusicPhoneViewModel(@Nullable AlsID7ViewModel alsID7ViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AlsId7SubMusicViewBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, TextView albumTextView2, TextView artistTextView2, ImageView btnMusicNext2, ImageView btnMusicPause2, ImageView btnMusicPrev2, TextView currentTimeTextView2, CustomBmwMusicLayout imageFrameLayout2, ImageView musicAlbumBg2, ConstraintLayout musicConstraintLayout2, TextView musicSubTitle2, TextView musicTitle2, TextView nameTextView2, MusicSeekBar seekBar2, TextView totalTimeTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.albumTextView = albumTextView2;
        this.artistTextView = artistTextView2;
        this.btnMusicNext = btnMusicNext2;
        this.btnMusicPause = btnMusicPause2;
        this.btnMusicPrev = btnMusicPrev2;
        this.currentTimeTextView = currentTimeTextView2;
        this.imageFrameLayout = imageFrameLayout2;
        this.musicAlbumBg = musicAlbumBg2;
        this.musicConstraintLayout = musicConstraintLayout2;
        this.musicSubTitle = musicSubTitle2;
        this.musicTitle = musicTitle2;
        this.nameTextView = nameTextView2;
        this.seekBar = seekBar2;
        this.totalTimeTextView = totalTimeTextView2;
    }

    @Nullable
    public AlsID7ViewModel getMusicPhoneViewModel() {
        return this.mMusicPhoneViewModel;
    }

    @NonNull
    public static AlsId7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AlsId7SubMusicViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_music_view, root, attachToRoot, component);
    }

    @NonNull
    public static AlsId7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlsId7SubMusicViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AlsId7SubMusicViewBinding) DataBindingUtil.inflate(inflater, R.layout.als_id7_sub_music_view, (ViewGroup) null, false, component);
    }

    public static AlsId7SubMusicViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AlsId7SubMusicViewBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AlsId7SubMusicViewBinding) bind(component, view, R.layout.als_id7_sub_music_view);
    }
}
