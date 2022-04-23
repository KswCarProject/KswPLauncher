package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class BenzNtg6FyBindCls extends ViewDataBinding {
    public final ViewPager benzNtg6FyViewpager;
    public final ImageView controlBtn;
    public final LinearLayout indicatorBenzNtg6Fy;
    public final ImageView ivCoatFy;
    public final LinearLayout layoutCoatBenzFy;
    public final LinearLayout layoutMainNtgFy;
    @Bindable
    protected BcVieModel mViewModel;
    public final TextView tvCoatFyTip;

    public abstract void setViewModel(BcVieModel bcVieModel);

    protected BenzNtg6FyBindCls(Object _bindingComponent, View _root, int _localFieldCount, ViewPager benzNtg6FyViewpager2, ImageView controlBtn2, LinearLayout indicatorBenzNtg6Fy2, ImageView ivCoatFy2, LinearLayout layoutCoatBenzFy2, LinearLayout layoutMainNtgFy2, TextView tvCoatFyTip2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.benzNtg6FyViewpager = benzNtg6FyViewpager2;
        this.controlBtn = controlBtn2;
        this.indicatorBenzNtg6Fy = indicatorBenzNtg6Fy2;
        this.ivCoatFy = ivCoatFy2;
        this.layoutCoatBenzFy = layoutCoatBenzFy2;
        this.layoutMainNtgFy = layoutMainNtgFy2;
        this.tvCoatFyTip = tvCoatFyTip2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static BenzNtg6FyBindCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyBindCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzNtg6FyBindCls) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_benz_ntg6_fy, root, attachToRoot, component);
    }

    public static BenzNtg6FyBindCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyBindCls inflate(LayoutInflater inflater, Object component) {
        return (BenzNtg6FyBindCls) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_benz_ntg6_fy, (ViewGroup) null, false, component);
    }

    public static BenzNtg6FyBindCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzNtg6FyBindCls bind(View view, Object component) {
        return (BenzNtg6FyBindCls) bind(component, view, R.layout.activity_main_benz_ntg6_fy);
    }
}
