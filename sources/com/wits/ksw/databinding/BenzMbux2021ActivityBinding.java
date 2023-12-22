package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class BenzMbux2021ActivityBinding extends ViewDataBinding {
    public final RecyclerView benzMbux2021RecyclerView;
    public final ImageView controlBtn;
    public final LinearLayout indicatorBenzMbux2021;
    public final ImageView ivCoat2021;
    public final LinearLayout layoutCoatBenzMbux2021;
    public final LinearLayout layoutMain2021;
    @Bindable
    protected BcVieModel mVieModel;
    public final TextView tvCoat2021Tip;

    public abstract void setVieModel(BcVieModel vieModel);

    protected BenzMbux2021ActivityBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView benzMbux2021RecyclerView, ImageView controlBtn, LinearLayout indicatorBenzMbux2021, ImageView ivCoat2021, LinearLayout layoutCoatBenzMbux2021, LinearLayout layoutMain2021, TextView tvCoat2021Tip) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbux2021RecyclerView = benzMbux2021RecyclerView;
        this.controlBtn = controlBtn;
        this.indicatorBenzMbux2021 = indicatorBenzMbux2021;
        this.ivCoat2021 = ivCoat2021;
        this.layoutCoatBenzMbux2021 = layoutCoatBenzMbux2021;
        this.layoutMain2021 = layoutMain2021;
        this.tvCoat2021Tip = tvCoat2021Tip;
    }

    public BcVieModel getVieModel() {
        return this.mVieModel;
    }

    public static BenzMbux2021ActivityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ActivityBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbux2021ActivityBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_mbux_2021, root, attachToRoot, component);
    }

    public static BenzMbux2021ActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ActivityBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbux2021ActivityBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_benz_mbux_2021, null, false, component);
    }

    public static BenzMbux2021ActivityBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbux2021ActivityBinding bind(View view, Object component) {
        return (BenzMbux2021ActivityBinding) bind(component, view, C0899R.C0902layout.activity_main_benz_mbux_2021);
    }
}
