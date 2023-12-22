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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public abstract class AudiMib3OneDataClsV2 extends ViewDataBinding {
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
    public final BenzMbuxItemView weatherItemview;
    public final ImageView weatherIv;
    public final TextView weatherTv;

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3OneDataClsV2(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView btItemview, ImageView btIv, TextView btTv, BenzMbuxItemView carItemview, ImageView carIv, TextView carTv, LinearLayout fragmentOneLl, BenzMbuxItemView musicItemview, ImageView musicIv, TextView musicTv, BenzMbuxItemView naviItemview, ImageView naviIv, TextView naviTv, BenzMbuxItemView phonelinkItemview, ImageView phonelinkIv, TextView phonelinkTv, BenzMbuxItemView setItemview, ImageView setIv, TextView setTv, BenzMbuxItemView videoItemview, ImageView videoIv, TextView videoTv, BenzMbuxItemView weatherItemview, ImageView weatherIv, TextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btItemview = btItemview;
        this.btIv = btIv;
        this.btTv = btTv;
        this.carItemview = carItemview;
        this.carIv = carIv;
        this.carTv = carTv;
        this.fragmentOneLl = fragmentOneLl;
        this.musicItemview = musicItemview;
        this.musicIv = musicIv;
        this.musicTv = musicTv;
        this.naviItemview = naviItemview;
        this.naviIv = naviIv;
        this.naviTv = naviTv;
        this.phonelinkItemview = phonelinkItemview;
        this.phonelinkIv = phonelinkIv;
        this.phonelinkTv = phonelinkTv;
        this.setItemview = setItemview;
        this.setIv = setIv;
        this.setTv = setTv;
        this.videoItemview = videoItemview;
        this.videoIv = videoIv;
        this.videoTv = videoTv;
        this.weatherItemview = weatherItemview;
        this.weatherIv = weatherIv;
        this.weatherTv = weatherTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3OneDataClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3OneDataClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3OneDataClsV2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_fragment_one_v2, root, attachToRoot, component);
    }

    public static AudiMib3OneDataClsV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3OneDataClsV2 inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3OneDataClsV2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_fragment_one_v2, null, false, component);
    }

    public static AudiMib3OneDataClsV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3OneDataClsV2 bind(View view, Object component) {
        return (AudiMib3OneDataClsV2) bind(component, view, C0899R.C0902layout.audi_mib3_fragment_one_v2);
    }
}
