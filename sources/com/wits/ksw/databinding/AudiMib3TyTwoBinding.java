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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3TyTwoBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout appContentLl, BenzMbuxItemView appItemview, ImageView appIv, TextView appTv, LinearLayout carContentLl, BenzMbuxItemView carItemview, ImageView carIv, TextView carTv, TextView dashboardContent, LinearLayout dashboardContentLl, BenzMbuxItemView dashboardItemview, ImageView dashboardIv, TextView dashboardTv, LinearLayout dvrContentLl, BenzMbuxItemView dvrItemview, ImageView dvrIv, TextView dvrTv, LinearLayout fragmentTwoLl, LinearLayout setContentLl, BenzMbuxItemView setItemview, ImageView setIv, TextView setTv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appContentLl = appContentLl;
        this.appItemview = appItemview;
        this.appIv = appIv;
        this.appTv = appTv;
        this.carContentLl = carContentLl;
        this.carItemview = carItemview;
        this.carIv = carIv;
        this.carTv = carTv;
        this.dashboardContent = dashboardContent;
        this.dashboardContentLl = dashboardContentLl;
        this.dashboardItemview = dashboardItemview;
        this.dashboardIv = dashboardIv;
        this.dashboardTv = dashboardTv;
        this.dvrContentLl = dvrContentLl;
        this.dvrItemview = dvrItemview;
        this.dvrIv = dvrIv;
        this.dvrTv = dvrTv;
        this.fragmentTwoLl = fragmentTwoLl;
        this.setContentLl = setContentLl;
        this.setItemview = setItemview;
        this.setIv = setIv;
        this.setTv = setTv;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TyTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_audi_mib3_ty_two, root, attachToRoot, component);
    }

    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyTwoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TyTwoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_audi_mib3_ty_two, null, false, component);
    }

    public static AudiMib3TyTwoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TyTwoBinding bind(View view, Object component) {
        return (AudiMib3TyTwoBinding) bind(component, view, C0899R.C0902layout.fragment_audi_mib3_ty_two);
    }
}
