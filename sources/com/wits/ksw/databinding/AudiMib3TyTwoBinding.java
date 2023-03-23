package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public abstract class AudiMib3TyTwoBinding extends ViewDataBinding {
    public final LinearLayout appContentLl;
    public final BenzMbuxItemView appItemview;
    public final ImageView appIv;
    public final TextView appTv;
    public final LinearLayout carContentLl;
    public final BenzMbuxItemView carItemview;
    public final ImageView carIv;
    public final TextView carTv;
    public final TextView dashboardContent;
    public final LinearLayout dashboardContentLl;
    public final BenzMbuxItemView dashboardItemview;
    public final ImageView dashboardIv;
    public final TextView dashboardTv;
    public final LinearLayout dvrContentLl;
    public final BenzMbuxItemView dvrItemview;
    public final ImageView dvrIv;
    public final TextView dvrTv;
    public final LinearLayout fragmentTwoLl;
    @Bindable
    protected BcVieModel mViewModel;
    public final LinearLayout setContentLl;
    public final BenzMbuxItemView setItemview;
    public final ImageView setIv;
    public final TextView setTv;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3TyTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout appContentLl2, BenzMbuxItemView appItemview2, ImageView appIv2, TextView appTv2, LinearLayout carContentLl2, BenzMbuxItemView carItemview2, ImageView carIv2, TextView carTv2, TextView dashboardContent2, LinearLayout dashboardContentLl2, BenzMbuxItemView dashboardItemview2, ImageView dashboardIv2, TextView dashboardTv2, LinearLayout dvrContentLl2, BenzMbuxItemView dvrItemview2, ImageView dvrIv2, TextView dvrTv2, LinearLayout fragmentTwoLl2, LinearLayout setContentLl2, BenzMbuxItemView setItemview2, ImageView setIv2, TextView setTv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appContentLl = appContentLl2;
        this.appItemview = appItemview2;
        this.appIv = appIv2;
        this.appTv = appTv2;
        this.carContentLl = carContentLl2;
        this.carItemview = carItemview2;
        this.carIv = carIv2;
        this.carTv = carTv2;
        this.dashboardContent = dashboardContent2;
        this.dashboardContentLl = dashboardContentLl2;
        this.dashboardItemview = dashboardItemview2;
        this.dashboardIv = dashboardIv2;
        this.dashboardTv = dashboardTv2;
        this.dvrContentLl = dvrContentLl2;
        this.dvrItemview = dvrItemview2;
        this.dvrIv = dvrIv2;
        this.dvrTv = dvrTv2;
        this.fragmentTwoLl = fragmentTwoLl2;
        this.setContentLl = setContentLl2;
        this.setItemview = setItemview2;
        this.setIv = setIv2;
        this.setTv = setTv2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TyTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audi_mib3_ty_two, root, attachToRoot, component);
    }

    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TyTwoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_audi_mib3_ty_two, (ViewGroup) null, false, component);
    }

    public static AudiMib3TyTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyTwoBinding bind(View view, Object component) {
        return (AudiMib3TyTwoBinding) bind(component, view, R.layout.fragment_audi_mib3_ty_two);
    }
}
