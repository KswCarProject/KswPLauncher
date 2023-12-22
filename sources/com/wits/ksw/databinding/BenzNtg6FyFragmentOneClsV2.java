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
import com.wits.ksw.launcher.view.CapitalizeTextView;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public abstract class BenzNtg6FyFragmentOneClsV2 extends ViewDataBinding {
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
    public final View space1;
    public final View space2;
    public final View space3;
    public final View space4;
    public final View space5;
    public final BenzMbuxItemView weatherItemview;
    public final ImageView weatherIv1;
    public final ImageView weatherIv2;
    public final RelativeLayout weatherRl;
    public final TextView weatherTip;
    public final CapitalizeTextView weatherTv;

    public abstract void setViewModel(BcVieModel viewModel);

    protected BenzNtg6FyFragmentOneClsV2(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView btItemview, ImageView btIv1, ImageView btIv2, RelativeLayout btRl, TextView btTip, TextView btTv, BenzMbuxItemView carItemview, ImageView carIv1, ImageView carIv2, RelativeLayout carRl, TextView carTip, TextView carTv, LinearLayout fragmentOneLl, BenzMbuxItemView musicItemview, ImageView musicIv1, ImageView musicIv2, RelativeLayout musicRl, TextView musicTip, TextView musicTv, BenzMbuxItemView naviItemview, ImageView naviIv1, ImageView naviIv2, RelativeLayout naviRl, TextView naviTip, TextView naviTv, View space1, View space2, View space3, View space4, View space5, BenzMbuxItemView weatherItemview, ImageView weatherIv1, ImageView weatherIv2, RelativeLayout weatherRl, TextView weatherTip, CapitalizeTextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btItemview = btItemview;
        this.btIv1 = btIv1;
        this.btIv2 = btIv2;
        this.btRl = btRl;
        this.btTip = btTip;
        this.btTv = btTv;
        this.carItemview = carItemview;
        this.carIv1 = carIv1;
        this.carIv2 = carIv2;
        this.carRl = carRl;
        this.carTip = carTip;
        this.carTv = carTv;
        this.fragmentOneLl = fragmentOneLl;
        this.musicItemview = musicItemview;
        this.musicIv1 = musicIv1;
        this.musicIv2 = musicIv2;
        this.musicRl = musicRl;
        this.musicTip = musicTip;
        this.musicTv = musicTv;
        this.naviItemview = naviItemview;
        this.naviIv1 = naviIv1;
        this.naviIv2 = naviIv2;
        this.naviRl = naviRl;
        this.naviTip = naviTip;
        this.naviTv = naviTv;
        this.space1 = space1;
        this.space2 = space2;
        this.space3 = space3;
        this.space4 = space4;
        this.space5 = space5;
        this.weatherItemview = weatherItemview;
        this.weatherIv1 = weatherIv1;
        this.weatherIv2 = weatherIv2;
        this.weatherRl = weatherRl;
        this.weatherTip = weatherTip;
        this.weatherTv = weatherTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtg6FyFragmentOneClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentOneClsV2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtg6FyFragmentOneClsV2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2, root, attachToRoot, component);
    }

    public static BenzNtg6FyFragmentOneClsV2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentOneClsV2 inflate(LayoutInflater inflater, Object component) {
        return (BenzNtg6FyFragmentOneClsV2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2, null, false, component);
    }

    public static BenzNtg6FyFragmentOneClsV2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyFragmentOneClsV2 bind(View view, Object component) {
        return (BenzNtg6FyFragmentOneClsV2) bind(component, view, C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2);
    }
}
