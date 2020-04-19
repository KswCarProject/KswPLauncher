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

public abstract class ID6FragmentTow extends ViewDataBinding {
    @NonNull
    public final ImageView id6BrowserImageView;
    @NonNull
    public final TextView id6BrowserMess;
    @NonNull
    public final TextView id6BrowserTextView;
    @NonNull
    public final ImageView id6CarImageView;
    @NonNull
    public final TextView id6CarMess;
    @NonNull
    public final TextView id6CarTextView;
    @NonNull
    public final ImageView id6VideoIamgeView;
    @NonNull
    public final TextView id6VideoMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ID6FragmentTow(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView id6BrowserImageView2, TextView id6BrowserMess2, TextView id6BrowserTextView2, ImageView id6CarImageView2, TextView id6CarMess2, TextView id6CarTextView2, ImageView id6VideoIamgeView2, TextView id6VideoMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6BrowserImageView = id6BrowserImageView2;
        this.id6BrowserMess = id6BrowserMess2;
        this.id6BrowserTextView = id6BrowserTextView2;
        this.id6CarImageView = id6CarImageView2;
        this.id6CarMess = id6CarMess2;
        this.id6CarTextView = id6CarTextView2;
        this.id6VideoIamgeView = id6VideoIamgeView2;
        this.id6VideoMess = id6VideoMess2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ID6FragmentTow inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentTow inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ID6FragmentTow) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_tow, root, attachToRoot, component);
    }

    @NonNull
    public static ID6FragmentTow inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentTow inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ID6FragmentTow) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_tow, (ViewGroup) null, false, component);
    }

    public static ID6FragmentTow bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ID6FragmentTow bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ID6FragmentTow) bind(component, view, R.layout.id6_fragment_tow);
    }
}
