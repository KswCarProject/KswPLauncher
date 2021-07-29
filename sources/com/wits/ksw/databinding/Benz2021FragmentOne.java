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

public abstract class Benz2021FragmentOne extends ViewDataBinding {
    public final BenzMbuxItemView btItemview;
    public final RelativeLayout btRl;
    public final TextView btTip;
    public final TextView btTv;
    public final BenzMbuxItemView carItemview;
    public final RelativeLayout carRl;
    public final TextView carTip;
    public final TextView carTv;
    public final LinearLayout fragmentOneLl;
    public final ImageView ivBt1;
    public final ImageView ivBt2;
    public final ImageView ivCar1;
    public final ImageView ivCar2;
    public final ImageView ivNavi1;
    public final ImageView ivNavi2;
    @Bindable
    protected BcVieModel mViewModel;
    public final BenzMbuxItemView naviItemview;
    public final RelativeLayout naviRl;
    public final TextView naviTip;
    public final TextView naviTv;
    public final View space;
    public final View space1;
    public final View space2;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected Benz2021FragmentOne(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView btItemview2, RelativeLayout btRl2, TextView btTip2, TextView btTv2, BenzMbuxItemView carItemview2, RelativeLayout carRl2, TextView carTip2, TextView carTv2, LinearLayout fragmentOneLl2, ImageView ivBt12, ImageView ivBt22, ImageView ivCar12, ImageView ivCar22, ImageView ivNavi12, ImageView ivNavi22, BenzMbuxItemView naviItemview2, RelativeLayout naviRl2, TextView naviTip2, TextView naviTv2, View space3, View space12, View space22) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btItemview = btItemview2;
        this.btRl = btRl2;
        this.btTip = btTip2;
        this.btTv = btTv2;
        this.carItemview = carItemview2;
        this.carRl = carRl2;
        this.carTip = carTip2;
        this.carTv = carTv2;
        this.fragmentOneLl = fragmentOneLl2;
        this.ivBt1 = ivBt12;
        this.ivBt2 = ivBt22;
        this.ivCar1 = ivCar12;
        this.ivCar2 = ivCar22;
        this.ivNavi1 = ivNavi12;
        this.ivNavi2 = ivNavi22;
        this.naviItemview = naviItemview2;
        this.naviRl = naviRl2;
        this.naviTip = naviTip2;
        this.naviTv = naviTv2;
        this.space = space3;
        this.space1 = space12;
        this.space2 = space22;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static Benz2021FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentOne inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Benz2021FragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.benz_mbux_2021_fragment_one, root, attachToRoot, component);
    }

    public static Benz2021FragmentOne inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentOne inflate(LayoutInflater inflater, Object component) {
        return (Benz2021FragmentOne) ViewDataBinding.inflateInternal(inflater, R.layout.benz_mbux_2021_fragment_one, (ViewGroup) null, false, component);
    }

    public static Benz2021FragmentOne bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Benz2021FragmentOne bind(View view, Object component) {
        return (Benz2021FragmentOne) bind(component, view, R.layout.benz_mbux_2021_fragment_one);
    }
}
