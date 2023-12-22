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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.audi_mib3.p008vm.AudiMib3SystemViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiMib3SystemViewModel vm);

    protected AudiMbi3SelThirdCls(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivShow, ImageView ivTitle, RecyclerView listviewMusic, RecyclerView listviewVideo, LinearLayout llSelapp, RelativeLayout title, View titleDivider, TextView tvTitle, View vDivider) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivShow = ivShow;
        this.ivTitle = ivTitle;
        this.listviewMusic = listviewMusic;
        this.listviewVideo = listviewVideo;
        this.llSelapp = llSelapp;
        this.title = title;
        this.titleDivider = titleDivider;
        this.tvTitle = tvTitle;
        this.vDivider = vDivider;
    }

    public AudiMib3SystemViewModel getVm() {
        return this.mVm;
    }

    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMbi3SelThirdCls) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audimbi3_sel_third_app_layout, root, attachToRoot, component);
    }

    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMbi3SelThirdCls inflate(LayoutInflater inflater, Object component) {
        return (AudiMbi3SelThirdCls) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audimbi3_sel_third_app_layout, null, false, component);
    }

    public static AudiMbi3SelThirdCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMbi3SelThirdCls bind(View view, Object component) {
        return (AudiMbi3SelThirdCls) bind(component, view, C0899R.C0902layout.audimbi3_sel_third_app_layout);
    }
}
