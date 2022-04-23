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

public abstract class AudiMib3TwoDataCls extends ViewDataBinding {
    public final BenzMbuxItemView browserItemview;
    public final ImageView browserIv;
    public final TextView browserTv;
    public final BenzMbuxItemView dashboardItemview;
    public final ImageView dashboardIv;
    public final TextView dashboardTv;
    public final BenzMbuxItemView dvrItemview;
    public final ImageView dvrIv;
    public final TextView dvrTv;
    public final BenzMbuxItemView fileItemview;
    public final ImageView fileIv;
    public final TextView fileTv;
    public final LinearLayout fragmentTwoLl;
    @Bindable
    protected BcVieModel mViewModel;

    public abstract void setViewModel(BcVieModel bcVieModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AudiMib3TwoDataCls(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView browserItemview2, ImageView browserIv2, TextView browserTv2, BenzMbuxItemView dashboardItemview2, ImageView dashboardIv2, TextView dashboardTv2, BenzMbuxItemView dvrItemview2, ImageView dvrIv2, TextView dvrTv2, BenzMbuxItemView fileItemview2, ImageView fileIv2, TextView fileTv2, LinearLayout fragmentTwoLl2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.browserItemview = browserItemview2;
        this.browserIv = browserIv2;
        this.browserTv = browserTv2;
        this.dashboardItemview = dashboardItemview2;
        this.dashboardIv = dashboardIv2;
        this.dashboardTv = dashboardTv2;
        this.dvrItemview = dvrItemview2;
        this.dvrIv = dvrIv2;
        this.dvrTv = dvrTv2;
        this.fileItemview = fileItemview2;
        this.fileIv = fileIv2;
        this.fileTv = fileTv2;
        this.fragmentTwoLl = fragmentTwoLl2;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3TwoDataCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TwoDataCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3TwoDataCls) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_fragment_two, root, attachToRoot, component);
    }

    public static AudiMib3TwoDataCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TwoDataCls inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3TwoDataCls) ViewDataBinding.inflateInternal(inflater, R.layout.audi_mib3_fragment_two, (ViewGroup) null, false, component);
    }

    public static AudiMib3TwoDataCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3TwoDataCls bind(View view, Object component) {
        return (AudiMib3TwoDataCls) bind(component, view, R.layout.audi_mib3_fragment_two);
    }
}
