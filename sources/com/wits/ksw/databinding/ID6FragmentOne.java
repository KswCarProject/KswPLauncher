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

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ID6FragmentOne(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6BtImageView, TextView id6BtMess, TextView id6BtTextView, TextView id6MusicArtisTextView, ImageView id6MusicIamgeView, ImageView id6MusicIcon, TextView id6MusicNameTextView, TextView id6MusicTextView, ImageView id6NavImageView, TextView id6NaviMess, TextView id6NaviTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6BtImageView = id6BtImageView;
        this.id6BtMess = id6BtMess;
        this.id6BtTextView = id6BtTextView;
        this.id6MusicArtisTextView = id6MusicArtisTextView;
        this.id6MusicIamgeView = id6MusicIamgeView;
        this.id6MusicIcon = id6MusicIcon;
        this.id6MusicNameTextView = id6MusicNameTextView;
        this.id6MusicTextView = id6MusicTextView;
        this.id6NavImageView = id6NavImageView;
        this.id6NaviMess = id6NaviMess;
        this.id6NaviTextView = id6NaviTextView;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_fragment_one, root, attachToRoot, component);
    }

    public static ID6FragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentOne inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_fragment_one, null, false, component);
    }

    public static ID6FragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentOne bind(View view, Object component) {
        return (ID6FragmentOne) bind(component, view, C0899R.C0902layout.id6_fragment_one);
    }
}
