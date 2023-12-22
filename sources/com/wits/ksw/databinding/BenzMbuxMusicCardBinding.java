package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.AppCompatImageView;
import android.support.p004v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class BenzMbuxMusicCardBinding extends ViewDataBinding {
    public final FrameLayout benzMbuxLocalCardBg;
    public final AppCompatTextView benzMbuxLocalCardContent;
    public final AppCompatTextView benzMbuxLocalCardTitle;
    public final AppCompatImageView benzMbuxLocalMusicCardBg;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel ViewModel);

    protected BenzMbuxMusicCardBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout benzMbuxLocalCardBg, AppCompatTextView benzMbuxLocalCardContent, AppCompatTextView benzMbuxLocalCardTitle, AppCompatImageView benzMbuxLocalMusicCardBg) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbuxLocalCardBg = benzMbuxLocalCardBg;
        this.benzMbuxLocalCardContent = benzMbuxLocalCardContent;
        this.benzMbuxLocalCardTitle = benzMbuxLocalCardTitle;
        this.benzMbuxLocalMusicCardBg = benzMbuxLocalMusicCardBg;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzMbuxMusicCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxMusicCardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbuxMusicCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_music_card, root, attachToRoot, component);
    }

    public static BenzMbuxMusicCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxMusicCardBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbuxMusicCardBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_music_card, null, false, component);
    }

    public static BenzMbuxMusicCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxMusicCardBinding bind(View view, Object component) {
        return (BenzMbuxMusicCardBinding) bind(component, view, C0899R.C0902layout.benz_mbux_music_card);
    }
}
