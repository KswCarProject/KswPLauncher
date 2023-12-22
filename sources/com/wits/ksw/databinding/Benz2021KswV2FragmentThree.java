package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public abstract class Benz2021KswV2FragmentThree extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final RelativeLayout appRl;
    public final TextView appTip;
    public final TextView appTv;
    public final BenzMbuxItemView carItemview;
    public final RelativeLayout carRl;
    public final TextView carTip;
    public final TextView carTv;
    public final BenzMbuxItemView dashboardItemview;
    public final RelativeLayout dashboardRl;
    public final TextView dashboardTip;
    public final TextView dashboardTv;
    public final LinearLayout fragmentTwoLl;
    public final ImageView ivApps1;
    public final ImageView ivApps2;
    public final ImageView ivCar1;
    public final ImageView ivCar2;
    public final ImageView ivDash1;
    public final ImageView ivDash2;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel viewModel);

    protected Benz2021KswV2FragmentThree(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview, RelativeLayout appRl, TextView appTip, TextView appTv, BenzMbuxItemView carItemview, RelativeLayout carRl, TextView carTip, TextView carTv, BenzMbuxItemView dashboardItemview, RelativeLayout dashboardRl, TextView dashboardTip, TextView dashboardTv, LinearLayout fragmentTwoLl, ImageView ivApps1, ImageView ivApps2, ImageView ivCar1, ImageView ivCar2, ImageView ivDash1, ImageView ivDash2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview;
        this.appRl = appRl;
        this.appTip = appTip;
        this.appTv = appTv;
        this.carItemview = carItemview;
        this.carRl = carRl;
        this.carTip = carTip;
        this.carTv = carTv;
        this.dashboardItemview = dashboardItemview;
        this.dashboardRl = dashboardRl;
        this.dashboardTip = dashboardTip;
        this.dashboardTv = dashboardTv;
        this.fragmentTwoLl = fragmentTwoLl;
        this.ivApps1 = ivApps1;
        this.ivApps2 = ivApps2;
        this.ivCar1 = ivCar1;
        this.ivCar2 = ivCar2;
        this.ivDash1 = ivDash1;
        this.ivDash2 = ivDash2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021KswV2FragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswV2FragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021KswV2FragmentThree) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_three, root, attachToRoot, component);
    }

    public static Benz2021KswV2FragmentThree inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswV2FragmentThree inflate(LayoutInflater inflater, Object component) {
        return (Benz2021KswV2FragmentThree) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_three, null, false, component);
    }

    public static Benz2021KswV2FragmentThree bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswV2FragmentThree bind(View view, Object component) {
        return (Benz2021KswV2FragmentThree) bind(component, view, C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_three);
    }
}
