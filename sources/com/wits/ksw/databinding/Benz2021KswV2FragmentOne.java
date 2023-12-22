package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
public abstract class Benz2021KswV2FragmentOne extends ViewDataBinding {
    public final BenzMbuxItemView btItemview;
    public final RelativeLayout btRl;
    public final TextView btTip;
    public final TextView btTv;
    public final LinearLayout fragmentOneLl;
    public final ImageView ivBt1;
    public final ImageView ivBt2;
    public final ImageView ivMusic1;
    public final ImageView ivMusic2;
    public final ImageView ivNavi1;
    public final ImageView ivNavi2;
    public final ImageView ivSet1;
    public final ImageView ivSet2;
    public final ImageView ivWeather1;
    public final ImageView ivWeather2;
    @Bindable
    protected BcVieModel mViewModel;
    public final FrameLayout musicItemview;
    public final RelativeLayout musicRl;
    public final TextView musicTip;
    public final TextView musicTv;
    public final BenzMbuxItemView naviItemview;
    public final RelativeLayout naviRl;
    public final TextView naviTip;
    public final TextView naviTv;
    public final BenzMbuxItemView setItemview;
    public final TextView setTip;
    public final TextView setTv;
    public final RelativeLayout settingRl;
    public final View space1;
    public final View space2;
    public final View space3;
    public final View space4;
    public final View space5;
    public final BenzMbuxItemView weatherItemview;
    public final RelativeLayout weatherRl;
    public final TextView weatherTip;
    public final TextView weatherTv;

    public abstract void setViewModel(BcVieModel viewModel);

    protected Benz2021KswV2FragmentOne(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView btItemview, RelativeLayout btRl, TextView btTip, TextView btTv, LinearLayout fragmentOneLl, ImageView ivBt1, ImageView ivBt2, ImageView ivMusic1, ImageView ivMusic2, ImageView ivNavi1, ImageView ivNavi2, ImageView ivSet1, ImageView ivSet2, ImageView ivWeather1, ImageView ivWeather2, FrameLayout musicItemview, RelativeLayout musicRl, TextView musicTip, TextView musicTv, BenzMbuxItemView naviItemview, RelativeLayout naviRl, TextView naviTip, TextView naviTv, BenzMbuxItemView setItemview, TextView setTip, TextView setTv, RelativeLayout settingRl, View space1, View space2, View space3, View space4, View space5, BenzMbuxItemView weatherItemview, RelativeLayout weatherRl, TextView weatherTip, TextView weatherTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btItemview = btItemview;
        this.btRl = btRl;
        this.btTip = btTip;
        this.btTv = btTv;
        this.fragmentOneLl = fragmentOneLl;
        this.ivBt1 = ivBt1;
        this.ivBt2 = ivBt2;
        this.ivMusic1 = ivMusic1;
        this.ivMusic2 = ivMusic2;
        this.ivNavi1 = ivNavi1;
        this.ivNavi2 = ivNavi2;
        this.ivSet1 = ivSet1;
        this.ivSet2 = ivSet2;
        this.ivWeather1 = ivWeather1;
        this.ivWeather2 = ivWeather2;
        this.musicItemview = musicItemview;
        this.musicRl = musicRl;
        this.musicTip = musicTip;
        this.musicTv = musicTv;
        this.naviItemview = naviItemview;
        this.naviRl = naviRl;
        this.naviTip = naviTip;
        this.naviTv = naviTv;
        this.setItemview = setItemview;
        this.setTip = setTip;
        this.setTv = setTv;
        this.settingRl = settingRl;
        this.space1 = space1;
        this.space2 = space2;
        this.space3 = space3;
        this.space4 = space4;
        this.space5 = space5;
        this.weatherItemview = weatherItemview;
        this.weatherRl = weatherRl;
        this.weatherTip = weatherTip;
        this.weatherTv = weatherTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021KswV2FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswV2FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021KswV2FragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_one, root, attachToRoot, component);
    }

    public static Benz2021KswV2FragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswV2FragmentOne inflate(LayoutInflater inflater, Object component) {
        return (Benz2021KswV2FragmentOne) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_one, null, false, component);
    }

    public static Benz2021KswV2FragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021KswV2FragmentOne bind(View view, Object component) {
        return (Benz2021KswV2FragmentOne) bind(component, view, C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_one);
    }
}
