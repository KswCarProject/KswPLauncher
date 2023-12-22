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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;
import com.wits.ksw.launcher.view.DateView;
import com.wits.ksw.launcher.view.LoopRotarySwitchView;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiViewModel vm);

    protected ActivityMainAudiBinding(Object _bindingComponent, View _root, int _localFieldCount, LoopRotarySwitchView MLoopRotarySwitchView, DateView date, ImageView ivApps, ImageView ivBt, ImageView ivCar, ImageView ivDashboard, ImageView ivDvr, ImageView ivEasyconnection, ImageView ivGuang, ImageView ivMusic, ImageView ivNavi, ImageView ivSettings, ImageView ivVideo, ImageView kswA4LAudiChe, RelativeLayout rlApps, RelativeLayout rlBt, RelativeLayout rlCar, RelativeLayout rlDashboard, RelativeLayout rlDvr, RelativeLayout rlEasyconnection, RelativeLayout rlMusic, RelativeLayout rlNavi, RelativeLayout rlSettings, RelativeLayout rlVideo, TextView tvApps, TextView tvBt, TextView tvCar, TextView tvDvr, TextView tvEasyconnection, TextView tvMusic, TextView tvNavi, TextView tvSettings, TextView tvVideo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.MLoopRotarySwitchView = MLoopRotarySwitchView;
        this.date = date;
        this.ivApps = ivApps;
        this.ivBt = ivBt;
        this.ivCar = ivCar;
        this.ivDashboard = ivDashboard;
        this.ivDvr = ivDvr;
        this.ivEasyconnection = ivEasyconnection;
        this.ivGuang = ivGuang;
        this.ivMusic = ivMusic;
        this.ivNavi = ivNavi;
        this.ivSettings = ivSettings;
        this.ivVideo = ivVideo;
        this.kswA4LAudiChe = kswA4LAudiChe;
        this.rlApps = rlApps;
        this.rlBt = rlBt;
        this.rlCar = rlCar;
        this.rlDashboard = rlDashboard;
        this.rlDvr = rlDvr;
        this.rlEasyconnection = rlEasyconnection;
        this.rlMusic = rlMusic;
        this.rlNavi = rlNavi;
        this.rlSettings = rlSettings;
        this.rlVideo = rlVideo;
        this.tvApps = tvApps;
        this.tvBt = tvBt;
        this.tvCar = tvCar;
        this.tvDvr = tvDvr;
        this.tvEasyconnection = tvEasyconnection;
        this.tvMusic = tvMusic;
        this.tvNavi = tvNavi;
        this.tvSettings = tvSettings;
        this.tvVideo = tvVideo;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static ActivityMainAudiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAudiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainAudiBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_audi, root, attachToRoot, component);
    }

    public static ActivityMainAudiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAudiBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainAudiBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_main_audi, null, false, component);
    }

    public static ActivityMainAudiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainAudiBinding bind(View view, Object component) {
        return (ActivityMainAudiBinding) bind(component, view, C0899R.C0902layout.activity_main_audi);
    }
}
