package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p001v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class BenzMbux2021ActivityBinding2 extends ViewDataBinding {
    public final ViewPager benzMbux2021Viewpager;
    public final ImageView controlBtn;
    public final LinearLayout indicatorBenzMbux2021;
    public final ImageView ivCoat2021;
    public final LinearLayout layoutCoatBenzMbux2021;
    public final ImageView layoutMain2021;
    public final View layoutMain20212;
    @Bindable
    protected BcVieModel mVieModel;
    public final TextView tvCoat2021Tip;

    public abstract void setVieModel(BcVieModel vieModel);

    protected BenzMbux2021ActivityBinding2(Object _bindingComponent, View _root, int _localFieldCount, ViewPager benzMbux2021Viewpager, ImageView controlBtn, LinearLayout indicatorBenzMbux2021, ImageView ivCoat2021, LinearLayout layoutCoatBenzMbux2021, ImageView layoutMain2021, View layoutMain20212, TextView tvCoat2021Tip) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbux2021Viewpager = benzMbux2021Viewpager;
        this.controlBtn = controlBtn;
        this.indicatorBenzMbux2021 = indicatorBenzMbux2021;
        this.ivCoat2021 = ivCoat2021;
        this.layoutCoatBenzMbux2021 = layoutCoatBenzMbux2021;
        this.layoutMain2021 = layoutMain2021;
        this.layoutMain20212 = layoutMain20212;
        this.tvCoat2021Tip = tvCoat2021Tip;
    }

    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    public static BenzMbux2021ActivityBinding2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ActivityBinding2 inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbux2021ActivityBinding2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_mbux_2021_2, root, attachToRoot, component);
    }

    public static BenzMbux2021ActivityBinding2 inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ActivityBinding2 inflate(LayoutInflater inflater, Object component) {
        return (BenzMbux2021ActivityBinding2) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_mbux_2021_2, null, false, component);
    }

    public static BenzMbux2021ActivityBinding2 bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ActivityBinding2 bind(View view, Object component) {
        return (BenzMbux2021ActivityBinding2) bind(component, view, C0899R.C0902layout.activity_main_benz_mbux_2021_2);
    }
}
