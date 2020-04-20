package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.AudiViewModel;

public abstract class AudiRightCarinfoBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout KSWA4LRightTrafficInformation;
    @NonNull
    public final TextView TvDrivingRange;
    @NonNull
    public final TextView TvTemp;
    @NonNull
    public final TextView TvZhuanSu;
    @NonNull
    public final ImageView imageView10;
    @NonNull
    public final ImageView imageView11;
    @NonNull
    public final ImageView imageView5;
    @NonNull
    public final ImageView imageView9;
    @Bindable
    protected AudiViewModel mVm;
    @NonNull
    public final ImageView pointerImageView;
    @NonNull
    public final TextView textView4;

    public abstract void setVm(@Nullable AudiViewModel audiViewModel);

    protected AudiRightCarinfoBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ConstraintLayout KSWA4LRightTrafficInformation2, TextView TvDrivingRange2, TextView TvTemp2, TextView TvZhuanSu2, ImageView imageView102, ImageView imageView112, ImageView imageView52, ImageView imageView92, ImageView pointerImageView2, TextView textView42) {
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

    @Nullable
    public AudiViewModel getVm() {
        return this.mVm;
    }

    @NonNull
    public static AudiRightCarinfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightCarinfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (AudiRightCarinfoBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_carinfo, root, attachToRoot, component);
    }

    @NonNull
    public static AudiRightCarinfoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AudiRightCarinfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (AudiRightCarinfoBinding) DataBindingUtil.inflate(inflater, R.layout.audi_right_carinfo, (ViewGroup) null, false, component);
    }

    public static AudiRightCarinfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AudiRightCarinfoBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (AudiRightCarinfoBinding) bind(component, view, R.layout.audi_right_carinfo);
    }
}
