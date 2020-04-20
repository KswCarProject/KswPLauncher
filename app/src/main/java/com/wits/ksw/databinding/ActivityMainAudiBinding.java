package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    @NonNull
    public final LoopRotarySwitchView MLoopRotarySwitchView;
    @NonNull
    public final DateView date;
    @NonNull
    public final ImageView ivApps;
    @NonNull
    public final ImageView ivBt;
    @NonNull
    public final ImageView ivCar;
    @NonNull
    public final ImageView ivDashboard;
    @NonNull
    public final ImageView ivDvr;
    @NonNull
    public final ImageView ivEasyconnection;
    @NonNull
    public final ImageView ivGuang;
    @NonNull
    public final ImageView ivMusic;
    @NonNull
    public final ImageView ivNavi;
    @NonNull
    public final ImageView ivSettings;
    @NonNull
    public final ImageView ivVideo;
    @NonNull
    public final ImageView kswA4LAudiChe;
    @Bindable
    protected AudiViewModel mVm;
    @NonNull
    public final RelativeLayout rlApps;
    @NonNull
    public final RelativeLayout rlBt;
    @NonNull
    public final RelativeLayout rlCar;
    @NonNull
    public final RelativeLayout rlDashboard;
    @NonNull
    public final RelativeLayout rlDvr;
    @NonNull
    public final RelativeLayout rlEasyconnection;
    @NonNull
    public final RelativeLayout rlMusic;
    @NonNull
    public final RelativeLayout rlNavi;
    @NonNull
    public final RelativeLayout rlSettings;
    @NonNull
    public final RelativeLayout rlVideo;
    @NonNull
    public final TextView tvApps;
    @NonNull
    public final TextView tvBt;
    @NonNull
    public final TextView tvCar;
    @NonNull
    public final TextView tvDvr;
    @NonNull
    public final TextView tvEasyconnection;
    @NonNull
    public final TextView tvMusic;
    @NonNull
    public final TextView tvNavi;
    @NonNull
    public final TextView tvSettings;
    @NonNull
    public final TextView tvVideo;

    public abstract void setVm(@Nullable AudiViewModel audiViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityMainAudiBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, LoopRotarySwitchView MLoopRotarySwitchView2, DateView date2, ImageView ivApps2, ImageView ivBt2, ImageView ivCar2, ImageView ivDashboard2, ImageView ivDvr2, ImageView ivEasyconnection2, ImageView ivGuang2, ImageView ivMusic2, ImageView ivNavi2, ImageView ivSettings2, ImageView ivVideo2, ImageView kswA4LAudiChe2, RelativeLayout rlApps2, RelativeLayout rlBt2, RelativeLayout rlCar2, RelativeLayout rlDashboard2, RelativeLayout rlDvr2, RelativeLayout rlEasyconnection2, RelativeLayout rlMusic2, RelativeLayout rlNavi2, RelativeLayout rlSettings2, RelativeLayout rlVideo2, TextView tvApps2, TextView tvBt2, TextView tvCar2, TextView tvDvr2, TextView tvEasyconnection2, TextView tvMusic2, TextView tvNavi2, TextView tvSettings2, TextView tvVideo2) {
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

    @Nullable
    public AudiViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static ActivityMainAudiBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainAudiBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityMainAudiBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_audi, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityMainAudiBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMainAudiBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityMainAudiBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_audi, (ViewGroup) null, false, component);
    }

    public static ActivityMainAudiBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainAudiBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityMainAudiBinding) bind(component, view, R.layout.activity_main_audi);
    }
}
