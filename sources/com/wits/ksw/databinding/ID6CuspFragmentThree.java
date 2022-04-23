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

public abstract class ID6CuspFragmentThree extends ViewDataBinding {
    public final ImageView id6CuspDashboardImageView;
    public final ImageView id6CuspDvrImageView;
    public final TextView id6CuspDvrMess;
    public final ImageView id6CuspFileIamgeView;
    public final TextView id6CuspOilMess;
    public final TextView id6CuspSpeedMess;
    public final TextView id6CuspVideoMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6CuspFragmentThree(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6CuspDashboardImageView2, ImageView id6CuspDvrImageView2, TextView id6CuspDvrMess2, ImageView id6CuspFileIamgeView2, TextView id6CuspOilMess2, TextView id6CuspSpeedMess2, TextView id6CuspVideoMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6CuspDashboardImageView = id6CuspDashboardImageView2;
        this.id6CuspDvrImageView = id6CuspDvrImageView2;
        this.id6CuspDvrMess = id6CuspDvrMess2;
        this.id6CuspFileIamgeView = id6CuspFileIamgeView2;
        this.id6CuspOilMess = id6CuspOilMess2;
        this.id6CuspSpeedMess = id6CuspSpeedMess2;
        this.id6CuspVideoMess = id6CuspVideoMess2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentThree) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_three, root, attachToRoot, component);
    }

    public static ID6CuspFragmentThree inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentThree inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentThree) ViewDataBinding.inflateInternal(inflater, R.layout.id6_cusp_fragment_three, (ViewGroup) null, false, component);
    }

    public static ID6CuspFragmentThree bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentThree bind(View view, Object component) {
        return (ID6CuspFragmentThree) bind(component, view, R.layout.id6_cusp_fragment_three);
    }
}
