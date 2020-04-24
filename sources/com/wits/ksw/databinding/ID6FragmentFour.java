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

public abstract class ID6FragmentFour extends ViewDataBinding {
    @NonNull
    public final ImageView id6AppsImageView;
    @NonNull
    public final TextView id6AppsMess;
    @NonNull
    public final ImageView id6PhoneIamgeView;
    @NonNull
    public final TextView id6PhoneMess;
    @NonNull
    public final ImageView id6SettingImageView;
    @NonNull
    public final TextView id6SettingMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected ID6FragmentFour(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView id6AppsImageView2, TextView id6AppsMess2, ImageView id6PhoneIamgeView2, TextView id6PhoneMess2, ImageView id6SettingImageView2, TextView id6SettingMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6AppsImageView = id6AppsImageView2;
        this.id6AppsMess = id6AppsMess2;
        this.id6PhoneIamgeView = id6PhoneIamgeView2;
        this.id6PhoneMess = id6PhoneMess2;
        this.id6SettingImageView = id6SettingImageView2;
        this.id6SettingMess = id6SettingMess2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ID6FragmentFour inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentFour inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ID6FragmentFour) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_four, root, attachToRoot, component);
    }

    @NonNull
    public static ID6FragmentFour inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ID6FragmentFour inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ID6FragmentFour) DataBindingUtil.inflate(inflater, R.layout.id6_fragment_four, (ViewGroup) null, false, component);
    }

    public static ID6FragmentFour bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ID6FragmentFour bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ID6FragmentFour) bind(component, view, R.layout.id6_fragment_four);
    }
}
