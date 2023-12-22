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
public abstract class AudiMib3FyV2TwoDataCls extends ViewDataBinding {
    public final BenzMbuxItemView appItemview;
    public final ImageView appIv;
    public final TextView appTv;
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

    public abstract void setViewModel(BcVieModel viewModel);

    protected AudiMib3FyV2TwoDataCls(Object _bindingComponent, View _root, int _localFieldCount, BenzMbuxItemView appItemview, ImageView appIv, TextView appTv, BenzMbuxItemView browserItemview, ImageView browserIv, TextView browserTv, BenzMbuxItemView dashboardItemview, ImageView dashboardIv, TextView dashboardTv, BenzMbuxItemView dvrItemview, ImageView dvrIv, TextView dvrTv, BenzMbuxItemView fileItemview, ImageView fileIv, TextView fileTv, LinearLayout fragmentTwoLl) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appItemview = appItemview;
        this.appIv = appIv;
        this.appTv = appTv;
        this.browserItemview = browserItemview;
        this.browserIv = browserIv;
        this.browserTv = browserTv;
        this.dashboardItemview = dashboardItemview;
        this.dashboardIv = dashboardIv;
        this.dashboardTv = dashboardTv;
        this.dvrItemview = dvrItemview;
        this.dvrIv = dvrIv;
        this.dvrTv = dvrTv;
        this.fileItemview = fileItemview;
        this.fileIv = fileIv;
        this.fileTv = fileTv;
        this.fragmentTwoLl = fragmentTwoLl;
    }

    public BcVieModel getViewModel() {
        return this.mViewModel;
    }

    public static AudiMib3FyV2TwoDataCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyV2TwoDataCls inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiMib3FyV2TwoDataCls) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_audi_mib3_fy_v2_two, root, attachToRoot, component);
    }

    public static AudiMib3FyV2TwoDataCls inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyV2TwoDataCls inflate(LayoutInflater inflater, Object component) {
        return (AudiMib3FyV2TwoDataCls) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.fragment_audi_mib3_fy_v2_two, null, false, component);
    }

    public static AudiMib3FyV2TwoDataCls bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiMib3FyV2TwoDataCls bind(View view, Object component) {
        return (AudiMib3FyV2TwoDataCls) bind(component, view, C0899R.C0902layout.fragment_audi_mib3_fy_v2_two);
    }
}
