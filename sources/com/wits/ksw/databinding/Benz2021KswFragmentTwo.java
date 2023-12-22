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
public abstract class Benz2021KswFragmentTwo extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final RelativeLayout appRl;
    public final TextView appTip;
    public final TextView appTv;
    public final TextView btTip;
    public final BenzMbuxItemView carItemview;
    public final RelativeLayout carRl;
    public final TextView carTip;
    public final TextView carTv;
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
    public final ImageView ivCar1;
    public final ImageView ivCar2;
    public final ImageView ivDash1;
    public final ImageView ivDash2;
    public final ImageView ivDvr1;
    public final ImageView ivDvr2;
    public final ImageView ivPhone1;
    public final ImageView ivPhone2;
    public final ImageView ivSet1;
    public final ImageView ivSet2;
    public final ImageView ivVideo1;
    public final ImageView ivVideo2;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView phonelinkItemview;
    public final RelativeLayout phonelinkRl;
    public final TextView phonelinkTip;
    public final TextView phonelinkTv;
    public final BenzMbuxItemView setItemview;
    public final TextView setTip;
    public final TextView setTv;
    public final RelativeLayout settingRl;
    public final View space;
    public final View space1;
    public final View space2;
    public final View space3;
    public final View space5;
    public final View space6;
    public final View space7;
    public final BenzMbuxItemView videoItemview;
    public final RelativeLayout videoRl;
    public final TextView videoTv;

    public abstract void setViewModel(BcVieModel viewModel);

    protected Benz2021KswFragmentTwo(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview, RelativeLayout appRl, TextView appTip, TextView appTv, TextView btTip, BenzMbuxItemView carItemview, RelativeLayout carRl, TextView carTip, TextView carTv, BenzMbuxItemView dashboardItemview, RelativeLayout dashboardRl, TextView dashboardTip, TextView dashboardTv, BenzMbuxItemView dvrItemview, RelativeLayout dvrRl, TextView dvrTip, TextView dvrTv, LinearLayout fragmentTwoLl, ImageView ivApps1, ImageView ivApps2, ImageView ivCar1, ImageView ivCar2, ImageView ivDash1, ImageView ivDash2, ImageView ivDvr1, ImageView ivDvr2, ImageView ivPhone1, ImageView ivPhone2, ImageView ivSet1, ImageView ivSet2, ImageView ivVideo1, ImageView ivVideo2, BenzMbuxItemView phonelinkItemview, RelativeLayout phonelinkRl, TextView phonelinkTip, TextView phonelinkTv, BenzMbuxItemView setItemview, TextView setTip, TextView setTv, RelativeLayout settingRl, View space, View space1, View space2, View space3, View space5, View space6, View space7, BenzMbuxItemView videoItemview, RelativeLayout videoRl, TextView videoTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview;
        this.appRl = appRl;
        this.appTip = appTip;
        this.appTv = appTv;
        this.btTip = btTip;
        this.carItemview = carItemview;
        this.carRl = carRl;
        this.carTip = carTip;
        this.carTv = carTv;
        this.dashboardItemview = dashboardItemview;
        this.dashboardRl = dashboardRl;
        this.dashboardTip = dashboardTip;
        this.dashboardTv = dashboardTv;
        this.dvrItemview = dvrItemview;
        this.dvrRl = dvrRl;
        this.dvrTip = dvrTip;
        this.dvrTv = dvrTv;
        this.fragmentTwoLl = fragmentTwoLl;
        this.ivApps1 = ivApps1;
        this.ivApps2 = ivApps2;
        this.ivCar1 = ivCar1;
        this.ivCar2 = ivCar2;
        this.ivDash1 = ivDash1;
        this.ivDash2 = ivDash2;
        this.ivDvr1 = ivDvr1;
        this.ivDvr2 = ivDvr2;
        this.ivPhone1 = ivPhone1;
        this.ivPhone2 = ivPhone2;
        this.ivSet1 = ivSet1;
        this.ivSet2 = ivSet2;
        this.ivVideo1 = ivVideo1;
        this.ivVideo2 = ivVideo2;
        this.phonelinkItemview = phonelinkItemview;
        this.phonelinkRl = phonelinkRl;
        this.phonelinkTip = phonelinkTip;
        this.phonelinkTv = phonelinkTv;
        this.setItemview = setItemview;
        this.setTip = setTip;
        this.setTv = setTv;
        this.settingRl = settingRl;
        this.space = space;
        this.space1 = space1;
        this.space2 = space2;
        this.space3 = space3;
        this.space5 = space5;
        this.space6 = space6;
        this.space7 = space7;
        this.videoItemview = videoItemview;
        this.videoRl = videoRl;
        this.videoTv = videoTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021KswFragmentTwo) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_two, root, attachToRoot, component);
    }

    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswFragmentTwo inflate(LayoutInflater inflater, Object component) {
        return (Benz2021KswFragmentTwo) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_two, null, false, component);
    }

    public static Benz2021KswFragmentTwo bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswFragmentTwo bind(View view, Object component) {
        return (Benz2021KswFragmentTwo) bind(component, view, C0899R.C0902layout.fragment_benz_mbux2021_ksw_two);
    }
}
