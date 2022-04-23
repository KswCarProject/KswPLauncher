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

public abstract class ID6FragmentTow extends ViewDataBinding {
    public final ImageView id6BrowserImageView;
    public final TextView id6BrowserMess;
    public final TextView id6BrowserTextView;
    public final ImageView id6CarImageView;
    public final TextView id6CarMess;
    public final TextView id6CarTextView;
    public final ImageView id6VideoIamgeView;
    public final TextView id6VideoMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6FragmentTow(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6BrowserImageView2, TextView id6BrowserMess2, TextView id6BrowserTextView2, ImageView id6CarImageView2, TextView id6CarMess2, TextView id6CarTextView2, ImageView id6VideoIamgeView2, TextView id6VideoMess2) {
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

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentTow inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentTow inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentTow) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_tow, root, attachToRoot, component);
    }

    public static ID6FragmentTow inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentTow inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentTow) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_tow, (ViewGroup) null, false, component);
    }

    public static ID6FragmentTow bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentTow bind(View view, Object component) {
        return (ID6FragmentTow) bind(component, view, R.layout.id6_fragment_tow);
    }
}
