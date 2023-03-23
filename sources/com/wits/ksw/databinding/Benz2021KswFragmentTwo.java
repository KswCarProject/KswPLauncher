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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public abstract class Benz2021KswFragmentTwo extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final RelativeLayout appRl;
    public final TextView appTip;
    public final TextView appTv;
    public final TextView btTip;
    public final BenzMbuxItemView dashboardItemview;
    public final RelativeLayout dashboardRl;
    public final TextView dashboardTip;
    public final TextView dashboardTv;
    public final BenzMbuxItemView dvrItemview;
    public final RelativeLayout dvrRl;
    public final TextView dvrTip;
    public final TextView dvrTv;
    public final LinearLayout fragmentTwoLl;
    public final ImageView ivApps1;
    public final ImageView ivApps2;
    public final ImageView ivDash1;
    public final ImageView ivDash2;
    public final ImageView ivDvr1;
    public final ImageView ivDvr2;
    public final ImageView ivPhone1;
    public final ImageView ivPhone2;
    public final ImageView ivVideo1;
    public final ImageView ivVideo2;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView phonelinkItemview;
    public final RelativeLayout phonelinkRl;
    public final TextView phonelinkTip;
    public final TextView phonelinkTv;
    public final View space;
    public final View space1;
    public final View space2;
    public final View space3;
    public final View space5;
    public final BenzMbuxItemView videoItemview;
    public final RelativeLayout videoRl;
    public final TextView videoTv;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Benz2021KswFragmentTwo(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview2, RelativeLayout appRl2, TextView appTip2, TextView appTv2, TextView btTip2, BenzMbuxItemView dashboardItemview2, RelativeLayout dashboardRl2, TextView dashboardTip2, TextView dashboardTv2, BenzMbuxItemView dvrItemview2, RelativeLayout dvrRl2, TextView dvrTip2, TextView dvrTv2, LinearLayout fragmentTwoLl2, ImageView ivApps12, ImageView ivApps22, ImageView ivDash12, ImageView ivDash22, ImageView ivDvr12, ImageView ivDvr22, ImageView ivPhone12, ImageView ivPhone22, ImageView ivVideo12, ImageView ivVideo22, BenzMbuxItemView phonelinkItemview2, RelativeLayout phonelinkRl2, TextView phonelinkTip2, TextView phonelinkTv2, View space4, View space12, View space22, View space32, View space52, BenzMbuxItemView videoItemview2, RelativeLayout videoRl2, TextView videoTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview2;
        this.appRl = appRl2;
        this.appTip = appTip2;
        this.appTv = appTv2;
        this.btTip = btTip2;
        this.dashboardItemview = dashboardItemview2;
        this.dashboardRl = dashboardRl2;
        this.dashboardTip = dashboardTip2;
        this.dashboardTv = dashboardTv2;
        this.dvrItemview = dvrItemview2;
        this.dvrRl = dvrRl2;
        this.dvrTip = dvrTip2;
        this.dvrTv = dvrTv2;
        this.fragmentTwoLl = fragmentTwoLl2;
        this.ivApps1 = ivApps12;
        this.ivApps2 = ivApps22;
        this.ivDash1 = ivDash12;
        this.ivDash2 = ivDash22;
        this.ivDvr1 = ivDvr12;
        this.ivDvr2 = ivDvr22;
        this.ivPhone1 = ivPhone12;
        this.ivPhone2 = ivPhone22;
        this.ivVideo1 = ivVideo12;
        this.ivVideo2 = ivVideo22;
        this.phonelinkItemview = phonelinkItemview2;
        this.phonelinkRl = phonelinkRl2;
        this.phonelinkTip = phonelinkTip2;
        this.phonelinkTv = phonelinkTv2;
        this.space = space4;
        this.space1 = space12;
        this.space2 = space22;
        this.space3 = space32;
        this.space5 = space52;
        this.videoItemview = videoItemview2;
        this.videoRl = videoRl2;
        this.videoTv = videoTv2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021KswFragmentTwo) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_benz_mbux2021_ksw_two, root, attachToRoot, component);
    }

    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater, Object component) {
        return (Benz2021KswFragmentTwo) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_benz_mbux2021_ksw_two, (ViewGroup) null, false, component);
    }

    public static Benz2021KswFragmentTwo bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswFragmentTwo bind(View view, Object component) {
        return (Benz2021KswFragmentTwo) bind(component, view, R.layout.fragment_benz_mbux2021_ksw_two);
    }
}
