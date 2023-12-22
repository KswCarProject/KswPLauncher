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

    public abstract void setViewModel(BcVieModel viewModel);

    protected BenzNtg6FyFragmentTwoClsV2(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview, ImageView appIv1, ImageView appIv2, RelativeLayout appRl, TextView appTip, TextView appTv, BenzMbuxItemView dashboardItemview, ImageView dashboardIv1, ImageView dashboardIv2, RelativeLayout dashboardRl, TextView dashboardTip, TextView dashboardTv, LinearLayout fragmentTwoLl, BenzMbuxItemView phonelinkItemview, ImageView phonelinkIv1, ImageView phonelinkIv2, RelativeLayout phonelinkRl, TextView phonelinkTip, View phonelinkTv, BenzMbuxItemView setItemview, ImageView setIv1, ImageView setIv2, RelativeLayout setRl, TextView setTip, TextView setTv, View space1, View space2, View space3, View space4, View space5, BenzMbuxItemView videoItemview, ImageView videoIv1, ImageView videoIv2, RelativeLayout videoRl, TextView videoTip, TextView videoTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview;
        this.appIv1 = appIv1;
        this.appIv2 = appIv2;
        this.appRl = appRl;
        this.appTip = appTip;
        this.appTv = appTv;
        this.dashboardItemview = dashboardItemview;
        this.dashboardIv1 = dashboardIv1;
        this.dashboardIv2 = dashboardIv2;
        this.dashboardRl = dashboardRl;
        this.dashboardTip = dashboardTip;
        this.dashboardTv = dashboardTv;
        this.fragmentTwoLl = fragmentTwoLl;
        this.phonelinkItemview = phonelinkItemview;
        this.phonelinkIv1 = phonelinkIv1;
        this.phonelinkIv2 = phonelinkIv2;
        this.phonelinkRl = phonelinkRl;
        this.phonelinkTip = phonelinkTip;
        this.phonelinkTv = phonelinkTv;
        this.setItemview = setItemview;
        this.setIv1 = setIv1;
        this.setIv2 = setIv2;
        this.setRl = setRl;
        this.setTip = setTip;
        this.setTv = setTv;
        this.space1 = space1;
        this.space2 = space2;
        this.space3 = space3;
        this.space4 = space4;
        this.space5 = space5;
        this.videoItemview = videoItemview;
        this.videoIv1 = videoIv1;
        this.videoIv2 = videoIv2;
        this.videoRl = videoRl;
        this.videoTip = videoTip;
        this.videoTv = videoTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtg6FyFragmentTwoClsV2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2, root, attachToRoot, component);
    }

    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentTwoClsV2 inflate(LayoutInflater inflater, Object component) {
        return (BenzNtg6FyFragmentTwoClsV2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2, null, false, component);
    }

    public static BenzNtg6FyFragmentTwoClsV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentTwoClsV2 bind(View view, Object component) {
        return (BenzNtg6FyFragmentTwoClsV2) bind(component, view, C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2);
    }
}
