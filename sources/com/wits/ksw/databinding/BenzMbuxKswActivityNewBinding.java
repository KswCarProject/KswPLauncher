package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.p001v4.view.ViewPager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;

/* loaded from: classes7.dex */
public abstract class BenzMbuxKswActivityNewBinding extends ViewDataBinding {
    public final RecyclerView benzMbux2021KswNewRecycle;
    public final ViewPager benzMbux2021Viewpager;
    public final ImageView controlBtn;
    public final LinearLayout indicatorBenzMbux2021;
    public final ImageView ivCoat2021;
    public final LinearLayout layoutCoatBenzMbux2021;
    public final ImageView layoutMain2021;
    public final RelativeLayout layoutMain20212;
    @Bindable
    protected BcVieModel mVieModel;
    public final TextView tvCoat2021Tip;

    public abstract void setVieModel(BcVieModel vieModel);

    protected BenzMbuxKswActivityNewBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView benzMbux2021KswNewRecycle, ViewPager benzMbux2021Viewpager, ImageView controlBtn, LinearLayout indicatorBenzMbux2021, ImageView ivCoat2021, LinearLayout layoutCoatBenzMbux2021, ImageView layoutMain2021, RelativeLayout layoutMain20212, TextView tvCoat2021Tip) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzMbux2021KswNewRecycle = benzMbux2021KswNewRecycle;
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

    public static BenzMbuxKswActivityNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxKswActivityNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzMbuxKswActivityNewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_ksw_activity_new, root, attachToRoot, component);
    }

    public static BenzMbuxKswActivityNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxKswActivityNewBinding inflate(LayoutInflater inflater, Object component) {
        return (BenzMbuxKswActivityNewBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.benz_mbux_ksw_activity_new, null, false, component);
    }

    public static BenzMbuxKswActivityNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzMbuxKswActivityNewBinding bind(View view, Object component) {
        return (BenzMbuxKswActivityNewBinding) bind(component, view, C0899R.C0902layout.benz_mbux_ksw_activity_new);
    }
}
