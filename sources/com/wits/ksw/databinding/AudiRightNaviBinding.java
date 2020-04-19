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

public abstract class AudiRightNaviBinding extends ViewDataBinding {
    @NonNull
    public final ImageView AmapautoIcon;
    @NonNull
    public final ImageView ImgRemainDis;
    @NonNull
    public final ImageView ImgRemainTime;
    @NonNull
    public final RelativeLayout KSWA4LRightShowNavi;
    @NonNull
    public final RelativeLayout NaviAmapautoInfo;
    @NonNull
    public final TextView TvNextRouadName;
    @NonNull
    public final TextView TvRouteRemainDis;
    @NonNull
    public final TextView TvRouteRemainTime;
    @NonNull
    public final TextView TvSegRemainDisInfor;
    @NonNull
    public final ImageView bg1;
    @NonNull
    public final ImageView bg2;
    @NonNull
    public final ImageView bg3;
    @Bindable
    protected AudiViewModel mVm;

    public abstract void setVm(@Nullable AudiViewModel audiViewModel);

    protected AudiRightNaviBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView AmapautoIcon2, ImageView ImgRemainDis2, ImageView ImgRemainTime2, RelativeLayout KSWA4LRightShowNavi2, RelativeLayout NaviAmapautoInfo2, TextView TvNextRouadName2, TextView TvRouteRemainDis2, TextView TvRouteRemainTime2, TextView TvSegRemainDisInfor2, ImageView bg12, ImageView bg22, ImageView bg32) {
        super(_bindingComponent, _root, _localFieldCount);
        this.AmapautoIcon = AmapautoIcon2;
        this.ImgRemainDis = ImgRemainDis2;
        this.ImgRemainTime = ImgRemainTime2;
        this.KSWA4LRightShowNavi = KSWA4LRightShowNavi2;
        this.NaviAmapautoInfo = NaviAmapautoInfo2;
        this.TvNextRouadName = TvNextRouadName2;
        this.TvRouteRemainDis = TvRouteRemainDis2;
        this.TvRouteRemainTime = TvRouteRemainTime2;
        this.TvSegRemainDisInfor = TvSegRemainDisInfor2;
        this.bg1 = bg12;
        this.bg2 = bg22;
        this.bg3 = bg32;
    }

    @Nullable
    public AudiViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiRightNaviBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightNaviBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiRightNaviBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_navi, root, attachToRoot, component);
    }

    @NonNull
    public static AudiRightNaviBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightNaviBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiRightNaviBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_navi, (ViewGroup) null, false, component);
    }

    public static AudiRightNaviBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiRightNaviBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiRightNaviBinding) bind(component, view, R.layout.audi_right_navi);
    }
}
