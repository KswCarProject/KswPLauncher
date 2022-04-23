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
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.als_id7.view.MusicSeekBar;
import com.wits.ksw.launcher.view.CustomBmwMusicLayout;

public abstract class AlsId7SubMusicThirdLayoutBinding extends ViewDataBinding {
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

    public abstract void setMusicPhoneViewModel(AlsID7ViewModel alsID7ViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AlsId7SubMusicThirdLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView albumTextView2, TextView artistTextView2, ImageView btnMusicNext2, ImageView btnMusicPause2, ImageView btnMusicPrev2, TextView currentTimeTextView2, CustomBmwMusicLayout imageFrameLayout2, ImageView musicAlbumBg2, ConstraintLayout musicConstraintLayout2, TextView musicSubTitle2, TextView musicTitle2, TextView nameTextView2, MusicSeekBar seekBar2, TextView totalTimeTextView2) {
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

    public AlsID7ViewModel getMusicPhoneViewModel() {
        return this.mMusicPhoneViewModel;
    }

    public static AlsId7SubMusicThirdLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubMusicThirdLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AlsId7SubMusicThirdLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_music_third_layout, root, attachToRoot, component);
    }

    public static AlsId7SubMusicThirdLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubMusicThirdLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (AlsId7SubMusicThirdLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.als_id7_sub_music_third_layout, (ViewGroup) null, false, component);
    }

    public static AlsId7SubMusicThirdLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AlsId7SubMusicThirdLayoutBinding bind(View view, Object component) {
        return (AlsId7SubMusicThirdLayoutBinding) bind(component, view, R.layout.als_id7_sub_music_third_layout);
    }
}
