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
public abstract class Benz2021FragmentThree extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final BenzMbuxItemView appItemviewYo;
    public final RelativeLayout appRl;
    public final TextView appTip;
    public final TextView appTv;
    public final BenzMbuxItemView dashboardItemview;
    public final BenzMbuxItemView dashboardItemviewYo;
    public final RelativeLayout dashboardRl;
    public final TextView dashboardTip;
    public final TextView dashboardTv;
    public final LinearLayout fragmentTwoLl;
    public final ImageView ivApps1;
    public final ImageView ivApps2;
    public final ImageView ivDash1;
    public final ImageView ivDash2;
    public final ImageView ivPhone1;
    public final ImageView ivPhone2;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView phonelinkItemview;
    public final BenzMbuxItemView phonelinkItemviewYo;
    public final RelativeLayout phonelinkRl;
    public final TextView phonelinkTip;
    public final TextView phonelinkTv;
    public final View space;
    public final View space1;
    public final View space2;

    public abstract void setViewModel(BcVieModel viewModel);

    protected Benz2021FragmentThree(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview, BenzMbuxItemView appItemviewYo, RelativeLayout appRl, TextView appTip, TextView appTv, BenzMbuxItemView dashboardItemview, BenzMbuxItemView dashboardItemviewYo, RelativeLayout dashboardRl, TextView dashboardTip, TextView dashboardTv, LinearLayout fragmentTwoLl, ImageView ivApps1, ImageView ivApps2, ImageView ivDash1, ImageView ivDash2, ImageView ivPhone1, ImageView ivPhone2, BenzMbuxItemView phonelinkItemview, BenzMbuxItemView phonelinkItemviewYo, RelativeLayout phonelinkRl, TextView phonelinkTip, TextView phonelinkTv, View space, View space1, View space2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview;
        this.appItemviewYo = appItemviewYo;
        this.appRl = appRl;
        this.appTip = appTip;
        this.appTv = appTv;
        this.dashboardItemview = dashboardItemview;
        this.dashboardItemviewYo = dashboardItemviewYo;
        this.dashboardRl = dashboardRl;
        this.dashboardTip = dashboardTip;
        this.dashboardTv = dashboardTv;
        this.fragmentTwoLl = fragmentTwoLl;
        this.ivApps1 = ivApps1;
        this.ivApps2 = ivApps2;
        this.ivDash1 = ivDash1;
        this.ivDash2 = ivDash2;
        this.ivPhone1 = ivPhone1;
        this.ivPhone2 = ivPhone2;
        this.phonelinkItemview = phonelinkItemview;
        this.phonelinkItemviewYo = phonelinkItemviewYo;
        this.phonelinkRl = phonelinkRl;
        this.phonelinkTip = phonelinkTip;
        this.phonelinkTv = phonelinkTv;
        this.space = space;
        this.space1 = space1;
        this.space2 = space2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021FragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentThree inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021FragmentThree) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_2021_fragment_three, root, attachToRoot, component);
    }

    public static Benz2021FragmentThree inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentThree inflate(LayoutInflater inflater, Object component) {
        return (Benz2021FragmentThree) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_2021_fragment_three, null, false, component);
    }

    public static Benz2021FragmentThree bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentThree bind(View view, Object component) {
        return (Benz2021FragmentThree) bind(component, view, C0899R.C0902layout.benz_mbux_2021_fragment_three);
    }
}
