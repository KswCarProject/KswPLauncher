package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.als_id7.view.MusicSeekBar;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

/* loaded from: classes7.dex */
public abstract class AlsId7SubMusicViewBinding extends ViewDataBinding {
    public final TextView albumTextView;
    public final TextView artistTextView;
    public final ImageView btnMusicNext;
    public final ImageView btnMusicPause;
    public final ImageView btnMusicPrev;
    public final TextView currentTimeTextView;
    public final CustomBmwMusicLayout imageFrameLayout;
    @Bindable
    protected AlsID7ViewModel mMusicPhoneViewModel;
    public final ImageView musicAlbumBg;
    public final ConstraintLayout musicConstraintLayout;
    public final TextView musicSubTitle;
    public final TextView musicTitle;
    public final TextView nameTextView;
    public final MusicSeekBar seekBar;
    public final TextView totalTimeTextView;

    public abstract void setMusicPhoneViewModel(AlsID7ViewModel MusicPhoneViewModel);

    protected AlsId7SubMusicViewBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView albumTextView, TextView artistTextView, ImageView btnMusicNext, ImageView btnMusicPause, ImageView btnMusicPrev, TextView currentTimeTextView, CustomBmwMusicLayout imageFrameLayout, ImageView musicAlbumBg, ConstraintLayout musicConstraintLayout, TextView musicSubTitle, TextView musicTitle, TextView nameTextView, MusicSeekBar seekBar, TextView totalTimeTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.albumTextView = albumTextView;
        this.artistTextView = artistTextView;
        this.btnMusicNext = btnMusicNext;
        this.btnMusicPause = btnMusicPause;
        this.btnMusicPrev = btnMusicPrev;
        this.currentTimeTextView = currentTimeTextView;
        this.imageFrameLayout = imageFrameLayout;
        this.musicAlbumBg = musicAlbumBg;
        this.musicConstraintLayout = musicConstraintLayout;
        this.musicSubTitle = musicSubTitle;
        this.musicTitle = musicTitle;
        this.nameTextView = nameTextView;
        this.seekBar = seekBar;
        this.totalTimeTextView = totalTimeTextView;
    }

    public AlsID7ViewModel getMusicPhoneViewModel() {
        return this.mMusicPhoneViewModel;
    }

    public static AlsId7SubMusicViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubMusicViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubMusicViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_music_view, root, attachToRoot, component);
    }

    public static AlsId7SubMusicViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubMusicViewBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubMusicViewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.als_id7_sub_music_view, null, false, component);
    }

    public static AlsId7SubMusicViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubMusicViewBinding bind(View view, Object component) {
        return (AlsId7SubMusicViewBinding) bind(component, view, C0899R.C0902layout.als_id7_sub_music_view);
    }
}
