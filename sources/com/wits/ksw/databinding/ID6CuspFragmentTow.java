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

public abstract class ID6CuspFragmentTow extends ViewDataBinding {
    public final ImageView id6CuspBrowserImageView;
    public final TextView id6CuspBrowserMess;
    public final TextView id6CuspBrowserTextView;
    public final ImageView id6CuspCarImageView;
    public final TextView id6CuspCarMess;
    public final TextView id6CuspCarTextView;
    public final ImageView id6CuspVideoIamgeView;
    public final TextView id6CuspVideoMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6CuspFragmentTow(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6CuspBrowserImageView2, TextView id6CuspBrowserMess2, TextView id6CuspBrowserTextView2, ImageView id6CuspCarImageView2, TextView id6CuspCarMess2, TextView id6CuspCarTextView2, ImageView id6CuspVideoIamgeView2, TextView id6CuspVideoMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6CuspBrowserImageView = id6CuspBrowserImageView2;
        this.id6CuspBrowserMess = id6CuspBrowserMess2;
        this.id6CuspBrowserTextView = id6CuspBrowserTextView2;
        this.id6CuspCarImageView = id6CuspCarImageView2;
        this.id6CuspCarMess = id6CuspCarMess2;
        this.id6CuspCarTextView = id6CuspCarTextView2;
        this.id6CuspVideoIamgeView = id6CuspVideoIamgeView2;
        this.id6CuspVideoMess = id6CuspVideoMess2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentTow inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentTow inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentTow) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_tow, root, attachToRoot, component);
    }

    public static ID6CuspFragmentTow inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentTow inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentTow) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_tow, (ViewGroup) null, false, component);
    }

    public static ID6CuspFragmentTow bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentTow bind(View view, Object component) {
        return (ID6CuspFragmentTow) bind(component, view, R.layout.id6_cusp_fragment_tow);
    }
}
