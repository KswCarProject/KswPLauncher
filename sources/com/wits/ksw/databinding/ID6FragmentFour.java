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
public abstract class ID6FragmentFour extends ViewDataBinding {
    public final ImageView id6AppsImageView;
    public final TextView id6AppsMess;
    public final ImageView id6PhoneIamgeView;
    public final TextView id6PhoneMess;
    public final ImageView id6SettingImageView;
    public final TextView id6SettingMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ID6FragmentFour(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6AppsImageView, TextView id6AppsMess, ImageView id6PhoneIamgeView, TextView id6PhoneMess, ImageView id6SettingImageView, TextView id6SettingMess) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6AppsImageView = id6AppsImageView;
        this.id6AppsMess = id6AppsMess;
        this.id6PhoneIamgeView = id6PhoneIamgeView;
        this.id6PhoneMess = id6PhoneMess;
        this.id6SettingImageView = id6SettingImageView;
        this.id6SettingMess = id6SettingMess;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentFour) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_fragment_four, root, attachToRoot, component);
    }

    public static ID6FragmentFour inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentFour inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentFour) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_fragment_four, null, false, component);
    }

    public static ID6FragmentFour bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentFour bind(View view, Object component) {
        return (ID6FragmentFour) bind(component, view, C0899R.C0902layout.id6_fragment_four);
    }
}
