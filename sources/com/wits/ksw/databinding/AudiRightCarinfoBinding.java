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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.AudiViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setVm(AudiViewModel vm);

    protected AudiRightCarinfoBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout KSWA4LRightTrafficInformation, TextView TvDrivingRange, TextView TvTemp, TextView TvZhuanSu, ImageView imageView10, ImageView imageView11, ImageView imageView5, ImageView imageView9, ImageView pointerImageView, TextView textView4) {
        super(_bindingComponent, _root, _localFieldCount);
        this.KSWA4LRightTrafficInformation = KSWA4LRightTrafficInformation;
        this.TvDrivingRange = TvDrivingRange;
        this.TvTemp = TvTemp;
        this.TvZhuanSu = TvZhuanSu;
        this.imageView10 = imageView10;
        this.imageView11 = imageView11;
        this.imageView5 = imageView5;
        this.imageView9 = imageView9;
        this.pointerImageView = pointerImageView;
        this.textView4 = textView4;
    }

    public AudiViewModel getVm() {
        return this.mVm;
    }

    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (AudiRightCarinfoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_right_carinfo, root, attachToRoot, component);
    }

    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightCarinfoBinding inflate(LayoutInflater inflater, Object component) {
        return (AudiRightCarinfoBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.audi_right_carinfo, null, false, component);
    }

    public static AudiRightCarinfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AudiRightCarinfoBinding bind(View view, Object component) {
        return (AudiRightCarinfoBinding) bind(component, view, C0899R.C0902layout.audi_right_carinfo);
    }
}
