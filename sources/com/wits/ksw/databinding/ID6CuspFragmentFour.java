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

public abstract class ID6CuspFragmentFour extends ViewDataBinding {
    public final TextView id6AppsMess;
    public final ImageView id6CuspAppsImageView;
    public final ImageView id6CuspPhoneIamgeView;
    public final TextView id6CuspPhoneMess;
    public final ImageView id6CuspSettingImageView;
    public final TextView id6CuspSettingMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6CuspFragmentFour(Object _bindingComponent, View _root, int _localFieldCount, TextView id6AppsMess2, ImageView id6CuspAppsImageView2, ImageView id6CuspPhoneIamgeView2, TextView id6CuspPhoneMess2, ImageView id6CuspSettingImageView2, TextView id6CuspSettingMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6AppsMess = id6AppsMess2;
        this.id6CuspAppsImageView = id6CuspAppsImageView2;
        this.id6CuspPhoneIamgeView = id6CuspPhoneIamgeView2;
        this.id6CuspPhoneMess = id6CuspPhoneMess2;
        this.id6CuspSettingImageView = id6CuspSettingImageView2;
        this.id6CuspSettingMess = id6CuspSettingMess2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentFour) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_four, root, attachToRoot, component);
    }

    public static ID6CuspFragmentFour inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentFour inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentFour) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_four, (ViewGroup) null, false, component);
    }

    public static ID6CuspFragmentFour bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentFour bind(View view, Object component) {
        return (ID6CuspFragmentFour) bind(component, view, R.layout.id6_cusp_fragment_four);
    }
}
