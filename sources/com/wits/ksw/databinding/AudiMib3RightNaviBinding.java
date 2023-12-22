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

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiViewModel vm);

    protected AudiMib3RightNaviBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView AmapautoIcon, ImageView ImgRemainDis, ImageView ImgRemainTime, RelativeLayout KSWA4LRightShowNavi, RelativeLayout NaviAmapautoInfo, TextView TvNextRouadName, TextView TvRouteRemainDis, TextView TvRouteRemainTime, TextView TvSegRemainDisInfor, ImageView bg1, ImageView bg2, ImageView bg3) {
        super(_bindingComponent, _root, _localFieldCount);
        this.AmapautoIcon = AmapautoIcon;
        this.ImgRemainDis = ImgRemainDis;
        this.ImgRemainTime = ImgRemainTime;
        this.KSWA4LRightShowNavi = KSWA4LRightShowNavi;
        this.NaviAmapautoInfo = NaviAmapautoInfo;
        this.TvNextRouadName = TvNextRouadName;
        this.TvRouteRemainDis = TvRouteRemainDis;
        this.TvRouteRemainTime = TvRouteRemainTime;
        this.TvSegRemainDisInfor = TvSegRemainDisInfor;
        this.bg1 = bg1;
        this.bg2 = bg2;
        this.bg3 = bg3;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3RightNaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_right_navi, root, attachToRoot, component);
    }

    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightNaviBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3RightNaviBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_mib3_right_navi, null, false, component);
    }

    public static AudiMib3RightNaviBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3RightNaviBinding bind(View view, Object component) {
        return (AudiMib3RightNaviBinding) bind(component, view, C0899R.C0902layout.audi_mib3_right_navi);
    }
}
