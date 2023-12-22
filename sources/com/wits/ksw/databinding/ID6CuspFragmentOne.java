package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class ID6CuspFragmentOne extends ViewDataBinding {
    public final TextView id6BtMess;
    public final TextView id6CusoMusicNameTextView;
    public final ImageView id6CuspBtImageView;
    public final TextView id6CuspBtTextView;
    public final TextView id6CuspMusicArtisTextView;
    public final ImageView id6CuspMusicIamgeView;
    public final ImageView id6CuspMusicIcon;
    public final TextView id6CuspMusicTextView;
    public final ImageView id6CuspNavImageView;
    public final TextView id6CuspNaviMess;
    public final TextView id6CuspNaviTextView;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ID6CuspFragmentOne(Object _bindingComponent, View _root, int _localFieldCount, TextView id6BtMess, TextView id6CusoMusicNameTextView, ImageView id6CuspBtImageView, TextView id6CuspBtTextView, TextView id6CuspMusicArtisTextView, ImageView id6CuspMusicIamgeView, ImageView id6CuspMusicIcon, TextView id6CuspMusicTextView, ImageView id6CuspNavImageView, TextView id6CuspNaviMess, TextView id6CuspNaviTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6BtMess = id6BtMess;
        this.id6CusoMusicNameTextView = id6CusoMusicNameTextView;
        this.id6CuspBtImageView = id6CuspBtImageView;
        this.id6CuspBtTextView = id6CuspBtTextView;
        this.id6CuspMusicArtisTextView = id6CuspMusicArtisTextView;
        this.id6CuspMusicIamgeView = id6CuspMusicIamgeView;
        this.id6CuspMusicIcon = id6CuspMusicIcon;
        this.id6CuspMusicTextView = id6CuspMusicTextView;
        this.id6CuspNavImageView = id6CuspNavImageView;
        this.id6CuspNaviMess = id6CuspNaviMess;
        this.id6CuspNaviTextView = id6CuspNaviTextView;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_cusp_fragment_one, root, attachToRoot, component);
    }

    public static ID6CuspFragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentOne inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_cusp_fragment_one, null, false, component);
    }

    public static ID6CuspFragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentOne bind(View view, Object component) {
        return (ID6CuspFragmentOne) bind(component, view, C0899R.C0902layout.id6_cusp_fragment_one);
    }
}
