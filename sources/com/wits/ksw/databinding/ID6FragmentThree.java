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

public abstract class ID6FragmentThree extends ViewDataBinding {
    public final ImageView id6DashboardImageView;
    public final ImageView id6DvrImageView;
    public final TextView id6DvrMess;
    public final ImageView id6FileIamgeView;
    public final TextView id6OilMess;
    public final TextView id6SpeedMess;
    public final TextView id6VideoMess;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(LauncherViewModel launcherViewModel);

    protected ID6FragmentThree(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6DashboardImageView2, ImageView id6DvrImageView2, TextView id6DvrMess2, ImageView id6FileIamgeView2, TextView id6OilMess2, TextView id6SpeedMess2, TextView id6VideoMess2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6DashboardImageView = id6DashboardImageView2;
        this.id6DvrImageView = id6DvrImageView2;
        this.id6DvrMess = id6DvrMess2;
        this.id6FileIamgeView = id6FileIamgeView2;
        this.id6OilMess = id6OilMess2;
        this.id6SpeedMess = id6SpeedMess2;
        this.id6VideoMess = id6VideoMess2;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6FragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6FragmentThree) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_three, root, attachToRoot, component);
    }

    public static ID6FragmentThree inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentThree inflate(LayoutInflater inflater, Object component) {
        return (ID6FragmentThree) ViewDataBinding.inflateInternal(inflater, R.layout.id6_fragment_three, (ViewGroup) null, false, component);
    }

    public static ID6FragmentThree bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6FragmentThree bind(View view, Object component) {
        return (ID6FragmentThree) bind(component, view, R.layout.id6_fragment_three);
    }
}
