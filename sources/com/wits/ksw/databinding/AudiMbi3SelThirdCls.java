package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.vm.AudiMib3SystemViewModel;

public abstract class AudiMbi3SelThirdCls extends ViewDataBinding {
    public final ImageView ivShow;
    public final ImageView ivTitle;
    public final RecyclerView listviewMusic;
    public final RecyclerView listviewVideo;
    public final LinearLayout llSelapp;
    @Bindable
    protected AudiMib3SystemViewModel mVm;
    public final RelativeLayout title;
    public final View titleDivider;
    public final TextView tvTitle;
    public final View vDivider;

    public abstract void setVm(AudiMib3SystemViewModel audiMib3SystemViewModel);

    protected AudiMbi3SelThirdCls(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivShow2, ImageView ivTitle2, RecyclerView listviewMusic2, RecyclerView listviewVideo2, LinearLayout llSelapp2, RelativeLayout title2, View titleDivider2, TextView tvTitle2, View vDivider2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivShow = ivShow2;
        this.ivTitle = ivTitle2;
        this.listviewMusic = listviewMusic2;
        this.listviewVideo = listviewVideo2;
        this.llSelapp = llSelapp2;
        this.title = title2;
        this.titleDivider = titleDivider2;
        this.tvTitle = tvTitle2;
        this.vDivider = vDivider2;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMbi3SelThirdCls) ViewDataBinding.inflateInternal(inflater, R.layout.audimbi3_sel_third_app_layout, root, attachToRoot, component);
    }

    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater, Object component) {
        return (AudiMbi3SelThirdCls) ViewDataBinding.inflateInternal(inflater, R.layout.audimbi3_sel_third_app_layout, (ViewGroup) null, false, component);
    }

    public static AudiMbi3SelThirdCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMbi3SelThirdCls bind(View view, Object component) {
        return (AudiMbi3SelThirdCls) bind(component, view, R.layout.audimbi3_sel_third_app_layout);
    }
}
