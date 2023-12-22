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

    public abstract void setViewModel(LauncherViewModel viewModel);

    protected ID6CuspFragmentThree(Object _bindingComponent, View _root, int _localFieldCount, ImageView id6CuspDashboardImageView, ImageView id6CuspDvrImageView, TextView id6CuspDvrMess, ImageView id6CuspFileIamgeView, TextView id6CuspOilMess, TextView id6CuspSpeedMess, TextView id6CuspVideoMess) {
        super(_bindingComponent, _root, _localFieldCount);
        this.id6CuspDashboardImageView = id6CuspDashboardImageView;
        this.id6CuspDvrImageView = id6CuspDvrImageView;
        this.id6CuspDvrMess = id6CuspDvrMess;
        this.id6CuspFileIamgeView = id6CuspFileIamgeView;
        this.id6CuspOilMess = id6CuspOilMess;
        this.id6CuspSpeedMess = id6CuspSpeedMess;
        this.id6CuspVideoMess = id6CuspVideoMess;
    }

    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ID6CuspFragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ID6CuspFragmentThree) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_cusp_fragment_three, root, attachToRoot, component);
    }

    public static ID6CuspFragmentThree inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentThree inflate(LayoutInflater inflater, Object component) {
        return (ID6CuspFragmentThree) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.id6_cusp_fragment_three, null, false, component);
    }

    public static ID6CuspFragmentThree bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ID6CuspFragmentThree bind(View view, Object component) {
        return (ID6CuspFragmentThree) bind(component, view, C0899R.C0902layout.id6_cusp_fragment_three);
    }
}
