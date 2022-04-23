package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.view.DateView;
import com.wits.ksw.launcher.view.LoopRotarySwitchView;

public abstract class ActivityMainAudiBinding extends ViewDataBinding {
    public final LoopRotarySwitchView MLoopRotarySwitchView;
    public final DateView date;
    public final ImageView ivApps;
    public final ImageView ivBt;
    public final ImageView ivCar;
    public final ImageView ivDashboard;
    public final ImageView ivDvr;
    public final ImageView ivEasyconnection;
    public final ImageView ivGuang;
    public final ImageView ivMusic;
    public final ImageView ivNavi;
    public final ImageView ivSettings;
    public final ImageView ivVideo;
    public final ImageView kswA4LAudiChe;
    @Bindable
    protected AudiViewModel mVm;
    public final RelativeLayout rlApps;
    public final RelativeLayout rlBt;
    public final RelativeLayout rlCar;
    public final RelativeLayout rlDashboard;
    public final RelativeLayout rlDvr;
    public final RelativeLayout rlEasyconnection;
    public final RelativeLayout rlMusic;
    public final RelativeLayout rlNavi;
    public final RelativeLayout rlSettings;
    public final RelativeLayout rlVideo;
    public final TextView tvApps;
    public final TextView tvBt;
    public final TextView tvCar;
    public final TextView tvDvr;
    public final TextView tvEasyconnection;
    public final TextView tvMusic;
    public final TextView tvNavi;
    public final TextView tvSettings;
    public final TextView tvVideo;

    public abstract void setVm(AudiViewModel audiViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityMainAudiBinding(Object _bindingComponent, View _root, int _localFieldCount, LoopRotarySwitchView MLoopRotarySwitchView2, DateView date2, ImageView ivApps2, ImageView ivBt2, ImageView ivCar2, ImageView ivDashboard2, ImageView ivDvr2, ImageView ivEasyconnection2, ImageView ivGuang2, ImageView ivMusic2, ImageView ivNavi2, ImageView ivSettings2, ImageView ivVideo2, ImageView kswA4LAudiChe2, RelativeLayout rlApps2, RelativeLayout rlBt2, RelativeLayout rlCar2, RelativeLayout rlDashboard2, RelativeLayout rlDvr2, RelativeLayout rlEasyconnection2, RelativeLayout rlMusic2, RelativeLayout rlNavi2, RelativeLayout rlSettings2, RelativeLayout rlVideo2, TextView tvApps2, TextView tvBt2, TextView tvCar2, TextView tvDvr2, TextView tvEasyconnection2, TextView tvMusic2, TextView tvNavi2, TextView tvSettings2, TextView tvVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.MLoopRotarySwitchView = MLoopRotarySwitchView2;
        this.date = date2;
        this.ivApps = ivApps2;
        this.ivBt = ivBt2;
        this.ivCar = ivCar2;
        this.ivDashboard = ivDashboard2;
        this.ivDvr = ivDvr2;
        this.ivEasyconnection = ivEasyconnection2;
        this.ivGuang = ivGuang2;
        this.ivMusic = ivMusic2;
        this.ivNavi = ivNavi2;
        this.ivSettings = ivSettings2;
        this.ivVideo = ivVideo2;
        this.kswA4LAudiChe = kswA4LAudiChe2;
        this.rlApps = rlApps2;
        this.rlBt = rlBt2;
        this.rlCar = rlCar2;
        this.rlDashboard = rlDashboard2;
        this.rlDvr = rlDvr2;
        this.rlEasyconnection = rlEasyconnection2;
        this.rlMusic = rlMusic2;
        this.rlNavi = rlNavi2;
        this.rlSettings = rlSettings2;
        this.rlVideo = rlVideo2;
        this.tvApps = tvApps2;
        this.tvBt = tvBt2;
        this.tvCar = tvCar2;
        this.tvDvr = tvDvr2;
        this.tvEasyconnection = tvEasyconnection2;
        this.tvMusic = tvMusic2;
        this.tvNavi = tvNavi2;
        this.tvSettings = tvSettings2;
        this.tvVideo = tvVideo2;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static ActivityMainAudiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAudiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainAudiBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_audi, root, attachToRoot, component);
    }

    public static ActivityMainAudiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAudiBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainAudiBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main_audi, (ViewGroup) null, false, component);
    }

    public static ActivityMainAudiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAudiBinding bind(View view, Object component) {
        return (ActivityMainAudiBinding) bind(component, view, R.layout.activity_main_audi);
    }
}
