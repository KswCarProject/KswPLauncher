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

public abstract class BenzNtg6FyFragmentOneCls extends ViewDataBinding {
    public final BenzMbuxItemView btItemview;
    public final ImageView btIv1;
    public final ImageView btIv2;
    public final RelativeLayout btRl;
    public final TextView btTip;
    public final TextView btTv;
    public final BenzMbuxItemView carItemview;
    public final ImageView carIv1;
    public final ImageView carIv2;
    public final RelativeLayout carRl;
    public final TextView carTip;
    public final TextView carTv;
    public final LinearLayout fragmentOneLl;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView musicItemview;
    public final ImageView musicIv1;
    public final ImageView musicIv2;
    public final RelativeLayout musicRl;
    public final TextView musicTip;
    public final TextView musicTv;
    public final BenzMbuxItemView naviItemview;
    public final ImageView naviIv1;
    public final ImageView naviIv2;
    public final RelativeLayout naviRl;
    public final TextView naviTip;
    public final TextView naviTv;
    public final BenzMbuxItemView setItemview;
    public final ImageView setIv1;
    public final ImageView setIv2;
    public final RelativeLayout setRl;
    public final TextView setTip;
    public final TextView setTv;
    public final View space;
    public final View space1;
    public final View space2;
    public final View space4;
    public final View space5;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BenzNtg6FyFragmentOneCls(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView btItemview2, ImageView btIv12, ImageView btIv22, RelativeLayout btRl2, TextView btTip2, TextView btTv2, BenzMbuxItemView carItemview2, ImageView carIv12, ImageView carIv22, RelativeLayout carRl2, TextView carTip2, TextView carTv2, LinearLayout fragmentOneLl2, BenzMbuxItemView musicItemview2, ImageView musicIv12, ImageView musicIv22, RelativeLayout musicRl2, TextView musicTip2, TextView musicTv2, BenzMbuxItemView naviItemview2, ImageView naviIv12, ImageView naviIv22, RelativeLayout naviRl2, TextView naviTip2, TextView naviTv2, BenzMbuxItemView setItemview2, ImageView setIv12, ImageView setIv22, RelativeLayout setRl2, TextView setTip2, TextView setTv2, View space3, View space12, View space22, View space42, View space52) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btItemview = btItemview2;
        this.btIv1 = btIv12;
        this.btIv2 = btIv22;
        this.btRl = btRl2;
        this.btTip = btTip2;
        this.btTv = btTv2;
        this.carItemview = carItemview2;
        this.carIv1 = carIv12;
        this.carIv2 = carIv22;
        this.carRl = carRl2;
        this.carTip = carTip2;
        this.carTv = carTv2;
        this.fragmentOneLl = fragmentOneLl2;
        this.musicItemview = musicItemview2;
        this.musicIv1 = musicIv12;
        this.musicIv2 = musicIv22;
        this.musicRl = musicRl2;
        this.musicTip = musicTip2;
        this.musicTv = musicTv2;
        this.naviItemview = naviItemview2;
        this.naviIv1 = naviIv12;
        this.naviIv2 = naviIv22;
        this.naviRl = naviRl2;
        this.naviTip = naviTip2;
        this.naviTv = naviTv2;
        this.setItemview = setItemview2;
        this.setIv1 = setIv12;
        this.setIv2 = setIv22;
        this.setRl = setRl2;
        this.setTip = setTip2;
        this.setTv = setTv2;
        this.space = space3;
        this.space1 = space12;
        this.space2 = space22;
        this.space4 = space42;
        this.space5 = space52;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtg6FyFragmentOneCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentOneCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtg6FyFragmentOneCls) ViewDataBinding.inflateInternal(inflater, R.layout.benz_ntg6_fy_fragment_one, root, attachToRoot, component);
    }

    public static BenzNtg6FyFragmentOneCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentOneCls inflate(LayoutInflater inflater, Object component) {
        return (BenzNtg6FyFragmentOneCls) ViewDataBinding.inflateInternal(inflater, R.layout.benz_ntg6_fy_fragment_one, (ViewGroup) null, false, component);
    }

    public static BenzNtg6FyFragmentOneCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentOneCls bind(View view, Object component) {
        return (BenzNtg6FyFragmentOneCls) bind(component, view, R.layout.benz_ntg6_fy_fragment_one);
    }
}
