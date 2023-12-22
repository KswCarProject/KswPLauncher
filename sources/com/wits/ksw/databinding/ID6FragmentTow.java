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

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ID6FragmentTow(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6BrowserImageView, TextView id6BrowserMess, TextView id6BrowserTextView, ImageView id6CarImageView, TextView id6CarMess, TextView id6CarTextView, ImageView id6VideoIamgeView, TextView id6VideoMess) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6BrowserImageView = id6BrowserImageView;
        this.id6BrowserMess = id6BrowserMess;
        this.id6BrowserTextView = id6BrowserTextView;
        this.id6CarImageView = id6CarImageView;
        this.id6CarMess = id6CarMess;
        this.id6CarTextView = id6CarTextView;
        this.id6VideoIamgeView = id6VideoIamgeView;
        this.id6VideoMess = id6VideoMess;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentTow inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentTow inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentTow) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_fragment_tow, root, attachToRoot, component);
    }

    public static ID6FragmentTow inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentTow inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentTow) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_fragment_tow, null, false, component);
    }

    public static ID6FragmentTow bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentTow bind(View view, Object component) {
        return (ID6FragmentTow) bind(component, view, C0899R.C0902layout.id6_fragment_tow);
    }
}
