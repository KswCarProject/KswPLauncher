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

public abstract class ID6FragmentOne extends ViewDataBinding {
    public final ImageView id6BtImageView;
    public final TextView id6BtMess;
    public final TextView id6BtTextView;
    public final TextView id6MusicArtisTextView;
    public final ImageView id6MusicIamgeView;
    public final ImageView id6MusicIcon;
    public final TextView id6MusicNameTextView;
    public final TextView id6MusicTextView;
    public final ImageView id6NavImageView;
    public final TextView id6NaviMess;
    public final TextView id6NaviTextView;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6FragmentOne(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6BtImageView2, TextView id6BtMess2, TextView id6BtTextView2, TextView id6MusicArtisTextView2, ImageView id6MusicIamgeView2, ImageView id6MusicIcon2, TextView id6MusicNameTextView2, TextView id6MusicTextView2, ImageView id6NavImageView2, TextView id6NaviMess2, TextView id6NaviTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6BtImageView = id6BtImageView2;
        this.id6BtMess = id6BtMess2;
        this.id6BtTextView = id6BtTextView2;
        this.id6MusicArtisTextView = id6MusicArtisTextView2;
        this.id6MusicIamgeView = id6MusicIamgeView2;
        this.id6MusicIcon = id6MusicIcon2;
        this.id6MusicNameTextView = id6MusicNameTextView2;
        this.id6MusicTextView = id6MusicTextView2;
        this.id6NavImageView = id6NavImageView2;
        this.id6NaviMess = id6NaviMess2;
        this.id6NaviTextView = id6NaviTextView2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_one, root, attachToRoot, component);
    }

    public static ID6FragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentOne inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_one, (ViewGroup) null, false, component);
    }

    public static ID6FragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentOne bind(View view, Object component) {
        return (ID6FragmentOne) bind(component, view, R.layout.id6_fragment_one);
    }
}
