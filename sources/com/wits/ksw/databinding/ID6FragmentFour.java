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

public abstract class ID6FragmentFour extends ViewDataBinding {
    public final ImageView id6AppsImageView;
    public final TextView id6AppsMess;
    public final ImageView id6PhoneIamgeView;
    public final TextView id6PhoneMess;
    public final ImageView id6SettingImageView;
    public final TextView id6SettingMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6FragmentFour(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6AppsImageView2, TextView id6AppsMess2, ImageView id6PhoneIamgeView2, TextView id6PhoneMess2, ImageView id6SettingImageView2, TextView id6SettingMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6AppsImageView = id6AppsImageView2;
        this.id6AppsMess = id6AppsMess2;
        this.id6PhoneIamgeView = id6PhoneIamgeView2;
        this.id6PhoneMess = id6PhoneMess2;
        this.id6SettingImageView = id6SettingImageView2;
        this.id6SettingMess = id6SettingMess2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentFour) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_four, root, attachToRoot, component);
    }

    public static ID6FragmentFour inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentFour inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentFour) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_four, (ViewGroup) null, false, component);
    }

    public static ID6FragmentFour bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentFour bind(View view, Object component) {
        return (ID6FragmentFour) bind(component, view, R.layout.id6_fragment_four);
    }
}
