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
public abstract class ID6CuspFragmentFour extends ViewDataBinding {
    public final TextView id6AppsMess;
    public final ImageView id6CuspAppsImageView;
    public final ImageView id6CuspPhoneIamgeView;
    public final TextView id6CuspPhoneMess;
    public final ImageView id6CuspSettingImageView;
    public final TextView id6CuspSettingMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ID6CuspFragmentFour(Object _bindingComponent, View _root, int _localFieldCount, TextView id6AppsMess, ImageView id6CuspAppsImageView, ImageView id6CuspPhoneIamgeView, TextView id6CuspPhoneMess, ImageView id6CuspSettingImageView, TextView id6CuspSettingMess) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6AppsMess = id6AppsMess;
        this.id6CuspAppsImageView = id6CuspAppsImageView;
        this.id6CuspPhoneIamgeView = id6CuspPhoneIamgeView;
        this.id6CuspPhoneMess = id6CuspPhoneMess;
        this.id6CuspSettingImageView = id6CuspSettingImageView;
        this.id6CuspSettingMess = id6CuspSettingMess;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentFour inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentFour) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_cusp_fragment_four, root, attachToRoot, component);
    }

    public static ID6CuspFragmentFour inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentFour inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentFour) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_cusp_fragment_four, null, false, component);
    }

    public static ID6CuspFragmentFour bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentFour bind(View view, Object component) {
        return (ID6CuspFragmentFour) bind(component, view, C0899R.C0902layout.id6_cusp_fragment_four);
    }
}
