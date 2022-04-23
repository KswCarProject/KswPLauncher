package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public abstract class AudiMib3FyOneDataCls extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final ImageView appIv;
    public final TextView appTv;
    public final BenzMbuxItemView btItemview;
    public final ImageView btIv;
    public final TextView btTv;
    public final BenzMbuxItemView carItemview;
    public final ImageView carIv;
    public final TextView carTv;
    public final LinearLayout fragmentOneLl;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView musicItemview;
    public final ImageView musicIv;
    public final TextView musicTv;
    public final BenzMbuxItemView naviItemview;
    public final ImageView naviIv;
    public final TextView naviTv;
    public final BenzMbuxItemView phonelinkItemview;
    public final ImageView phonelinkIv;
    public final TextView phonelinkTv;
    public final BenzMbuxItemView setItemview;
    public final ImageView setIv;
    public final TextView setTv;
    public final BenzMbuxItemView videoItemview;
    public final ImageView videoIv;
    public final TextView videoTv;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3FyOneDataCls(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview2, ImageView appIv2, TextView appTv2, BenzMbuxItemView btItemview2, ImageView btIv2, TextView btTv2, BenzMbuxItemView carItemview2, ImageView carIv2, TextView carTv2, LinearLayout fragmentOneLl2, BenzMbuxItemView musicItemview2, ImageView musicIv2, TextView musicTv2, BenzMbuxItemView naviItemview2, ImageView naviIv2, TextView naviTv2, BenzMbuxItemView phonelinkItemview2, ImageView phonelinkIv2, TextView phonelinkTv2, BenzMbuxItemView setItemview2, ImageView setIv2, TextView setTv2, BenzMbuxItemView videoItemview2, ImageView videoIv2, TextView videoTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview2;
        this.appIv = appIv2;
        this.appTv = appTv2;
        this.btItemview = btItemview2;
        this.btIv = btIv2;
        this.btTv = btTv2;
        this.carItemview = carItemview2;
        this.carIv = carIv2;
        this.carTv = carTv2;
        this.fragmentOneLl = fragmentOneLl2;
        this.musicItemview = musicItemview2;
        this.musicIv = musicIv2;
        this.musicTv = musicTv2;
        this.naviItemview = naviItemview2;
        this.naviIv = naviIv2;
        this.naviTv = naviTv2;
        this.phonelinkItemview = phonelinkItemview2;
        this.phonelinkIv = phonelinkIv2;
        this.phonelinkTv = phonelinkTv2;
        this.setItemview = setItemview2;
        this.setIv = setIv2;
        this.setTv = setTv2;
        this.videoItemview = videoItemview2;
        this.videoIv = videoIv2;
        this.videoTv = videoTv2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3FyOneDataCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyOneDataCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3FyOneDataCls) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audi_mib3_fy_one, root, attachToRoot, component);
    }

    public static AudiMib3FyOneDataCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyOneDataCls inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3FyOneDataCls) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audi_mib3_fy_one, (ViewGroup) null, false, component);
    }

    public static AudiMib3FyOneDataCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyOneDataCls bind(View view, Object component) {
        return (AudiMib3FyOneDataCls) bind(component, view, R.layout.fragment_audi_mib3_fy_one);
    }
}
