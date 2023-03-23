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

public abstract class BenzNtg6FyFragmentTwoClsV2 extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final ImageView appIv1;
    public final ImageView appIv2;
    public final RelativeLayout appRl;
    public final TextView appTip;
    public final TextView appTv;
    public final BenzMbuxItemView dashboardItemview;
    public final ImageView dashboardIv1;
    public final ImageView dashboardIv2;
    public final RelativeLayout dashboardRl;
    public final TextView dashboardTip;
    public final TextView dashboardTv;
    public final LinearLayout fragmentTwoLl;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView phonelinkItemview;
    public final ImageView phonelinkIv1;
    public final ImageView phonelinkIv2;
    public final RelativeLayout phonelinkRl;
    public final TextView phonelinkTip;
    public final View phonelinkTv;
    public final BenzMbuxItemView setItemview;
    public final ImageView setIv1;
    public final ImageView setIv2;
    public final RelativeLayout setRl;
    public final TextView setTip;
    public final TextView setTv;
    public final View space1;
    public final View space2;
    public final View space3;
    public final View space4;
    public final View space5;
    public final BenzMbuxItemView videoItemview;
    public final ImageView videoIv1;
    public final ImageView videoIv2;
    public final RelativeLayout videoRl;
    public final TextView videoTip;
    public final TextView videoTv;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BenzNtg6FyFragmentTwoClsV2(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview2, ImageView appIv12, ImageView appIv22, RelativeLayout appRl2, TextView appTip2, TextView appTv2, BenzMbuxItemView dashboardItemview2, ImageView dashboardIv12, ImageView dashboardIv22, RelativeLayout dashboardRl2, TextView dashboardTip2, TextView dashboardTv2, LinearLayout fragmentTwoLl2, BenzMbuxItemView phonelinkItemview2, ImageView phonelinkIv12, ImageView phonelinkIv22, RelativeLayout phonelinkRl2, TextView phonelinkTip2, View phonelinkTv2, BenzMbuxItemView setItemview2, ImageView setIv12, ImageView setIv22, RelativeLayout setRl2, TextView setTip2, TextView setTv2, View space12, View space22, View space32, View space42, View space52, BenzMbuxItemView videoItemview2, ImageView videoIv12, ImageView videoIv22, RelativeLayout videoRl2, TextView videoTip2, TextView videoTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview2;
        this.appIv1 = appIv12;
        this.appIv2 = appIv22;
        this.appRl = appRl2;
        this.appTip = appTip2;
        this.appTv = appTv2;
        this.dashboardItemview = dashboardItemview2;
        this.dashboardIv1 = dashboardIv12;
        this.dashboardIv2 = dashboardIv22;
        this.dashboardRl = dashboardRl2;
        this.dashboardTip = dashboardTip2;
        this.dashboardTv = dashboardTv2;
        this.fragmentTwoLl = fragmentTwoLl2;
        this.phonelinkItemview = phonelinkItemview2;
        this.phonelinkIv1 = phonelinkIv12;
        this.phonelinkIv2 = phonelinkIv22;
        this.phonelinkRl = phonelinkRl2;
        this.phonelinkTip = phonelinkTip2;
        this.phonelinkTv = phonelinkTv2;
        this.setItemview = setItemview2;
        this.setIv1 = setIv12;
        this.setIv2 = setIv22;
        this.setRl = setRl2;
        this.setTip = setTip2;
        this.setTv = setTv2;
        this.space1 = space12;
        this.space2 = space22;
        this.space3 = space32;
        this.space4 = space42;
        this.space5 = space52;
        this.videoItemview = videoItemview2;
        this.videoIv1 = videoIv12;
        this.videoIv2 = videoIv22;
        this.videoRl = videoRl2;
        this.videoTip = videoTip2;
        this.videoTv = videoTv2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtg6FyFragmentTwoClsV2) ViewDataBinding.inflateInternal(inflater, R.layout.benz_ntg6_fy_fragment_two_v2, root, attachToRoot, component);
    }

    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater, Object component) {
        return (BenzNtg6FyFragmentTwoClsV2) ViewDataBinding.inflateInternal(inflater, R.layout.benz_ntg6_fy_fragment_two_v2, (ViewGroup) null, false, component);
    }

    public static BenzNtg6FyFragmentTwoClsV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentTwoClsV2 bind(View view, Object component) {
        return (BenzNtg6FyFragmentTwoClsV2) bind(component, view, R.layout.benz_ntg6_fy_fragment_two_v2);
    }
}
