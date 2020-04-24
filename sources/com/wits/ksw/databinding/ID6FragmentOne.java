package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class ID6FragmentOne extends ViewDataBinding {
    @NonNull
    public final ImageView id6BtImageView;
    @NonNull
    public final TextView id6BtMess;
    @NonNull
    public final TextView id6BtTextView;
    @NonNull
    public final TextView id6MusicArtisTextView;
    @NonNull
    public final ImageView id6MusicIamgeView;
    @NonNull
    public final ImageView id6MusicIcon;
    @NonNull
    public final TextView id6MusicNameTextView;
    @NonNull
    public final TextView id6MusicTextView;
    @NonNull
    public final ImageView id6NavImageView;
    @NonNull
    public final TextView id6NaviMess;
    @NonNull
    public final TextView id6NaviTextView;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ID6FragmentOne(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView id6BtImageView2, TextView id6BtMess2, TextView id6BtTextView2, TextView id6MusicArtisTextView2, ImageView id6MusicIamgeView2, ImageView id6MusicIcon2, TextView id6MusicNameTextView2, TextView id6MusicTextView2, ImageView id6NavImageView2, TextView id6NaviMess2, TextView id6NaviTextView2) {
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

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ID6FragmentOne inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentOne inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ID6FragmentOne) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_one, root, attachToRoot, component);
    }

    @NonNull
    public static ID6FragmentOne inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentOne inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ID6FragmentOne) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_one, (ViewGroup) null, false, component);
    }

    public static ID6FragmentOne bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ID6FragmentOne bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ID6FragmentOne) bind(component, view, R.layout.id6_fragment_one);
    }
}
