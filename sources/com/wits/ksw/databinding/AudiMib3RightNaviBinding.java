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

public abstract class AudiMib3RightNaviBinding extends ViewDataBinding {
    public final ImageView AmapautoIcon;
    public final ImageView ImgRemainDis;
    public final ImageView ImgRemainTime;
    public final RelativeLayout KSWA4LRightShowNavi;
    public final RelativeLayout NaviAmapautoInfo;
    public final TextView TvNextRouadName;
    public final TextView TvRouteRemainDis;
    public final TextView TvRouteRemainTime;
    public final TextView TvSegRemainDisInfor;
    public final ImageView bg1;
    public final ImageView bg2;
    public final ImageView bg3;
    @Bindable
    protected AudiViewModel mVm;

    public abstract void setVm(AudiViewModel audiViewModel);

    protected AudiMib3RightNaviBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView AmapautoIcon2, ImageView ImgRemainDis2, ImageView ImgRemainTime2, RelativeLayout KSWA4LRightShowNavi2, RelativeLayout NaviAmapautoInfo2, TextView TvNextRouadName2, TextView TvRouteRemainDis2, TextView TvRouteRemainTime2, TextView TvSegRemainDisInfor2, ImageView bg12, ImageView bg22, ImageView bg32) {
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

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3RightNaviBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_right_navi, root, attachToRoot, component);
    }

    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3RightNaviBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_right_navi, (ViewGroup) null, false, component);
    }

    public static AudiMib3RightNaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightNaviBinding bind(View view, Object component) {
        return (AudiMib3RightNaviBinding) bind(component, view, R.layout.audi_mib3_right_navi);
    }
}
