package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6CuspFragmentOne(Object _bindingComponent, View _root, int _localFieldCount, TextView id6BtMess2, TextView id6CusoMusicNameTextView2, ImageView id6CuspBtImageView2, TextView id6CuspBtTextView2, TextView id6CuspMusicArtisTextView2, ImageView id6CuspMusicIamgeView2, ImageView id6CuspMusicIcon2, TextView id6CuspMusicTextView2, ImageView id6CuspNavImageView2, TextView id6CuspNaviMess2, TextView id6CuspNaviTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6BtMess = id6BtMess2;
        this.id6CusoMusicNameTextView = id6CusoMusicNameTextView2;
        this.id6CuspBtImageView = id6CuspBtImageView2;
        this.id6CuspBtTextView = id6CuspBtTextView2;
        this.id6CuspMusicArtisTextView = id6CuspMusicArtisTextView2;
        this.id6CuspMusicIamgeView = id6CuspMusicIamgeView2;
        this.id6CuspMusicIcon = id6CuspMusicIcon2;
        this.id6CuspMusicTextView = id6CuspMusicTextView2;
        this.id6CuspNavImageView = id6CuspNavImageView2;
        this.id6CuspNaviMess = id6CuspNaviMess2;
        this.id6CuspNaviTextView = id6CuspNaviTextView2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_one, root, attachToRoot, component);
    }

    public static ID6CuspFragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentOne inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_one, (ViewGroup) null, false, component);
    }

    public static ID6CuspFragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentOne bind(View view, Object component) {
        return (ID6CuspFragmentOne) bind(component, view, R.layout.id6_cusp_fragment_one);
    }
}
