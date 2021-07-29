package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public abstract class AudiRightCarinfoBinding extends ViewDataBinding {
    public final ConstraintLayout KSWA4LRightTrafficInformation;
    public final TextView TvDrivingRange;
    public final TextView TvTemp;
    public final TextView TvZhuanSu;
    public final ImageView imageView10;
    public final ImageView imageView11;
    public final ImageView imageView5;
    public final ImageView imageView9;
    @Bindable
    protected AudiViewModel mVm;
    public final ImageView pointerImageView;
    public final TextView textView4;

    public abstract void setVm(AudiViewModel audiViewModel);

    protected AudiRightCarinfoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout KSWA4LRightTrafficInformation2, TextView TvDrivingRange2, TextView TvTemp2, TextView TvZhuanSu2, ImageView imageView102, ImageView imageView112, ImageView imageView52, ImageView imageView92, ImageView pointerImageView2, TextView textView42) {
        super(_bindingComponent, _root, _localFieldCount);
        this.KSWA4LRightTrafficInformation = KSWA4LRightTrafficInformation2;
        this.TvDrivingRange = TvDrivingRange2;
        this.TvTemp = TvTemp2;
        this.TvZhuanSu = TvZhuanSu2;
        this.imageView10 = imageView102;
        this.imageView11 = imageView112;
        this.imageView5 = imageView52;
        this.imageView9 = imageView92;
        this.pointerImageView = pointerImageView2;
        this.textView4 = textView42;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiRightCarinfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_right_carinfo, root, attachToRoot, component);
    }

    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiRightCarinfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.audi_right_carinfo, (ViewGroup) null, false, component);
    }

    public static AudiRightCarinfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightCarinfoBinding bind(View view, Object component) {
        return (AudiRightCarinfoBinding) bind(component, view, R.layout.audi_right_carinfo);
    }
}
