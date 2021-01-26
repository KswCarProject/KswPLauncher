package com.wits.ksw.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;

public abstract class LandroverMainBottomLayBinding extends ViewDataBinding {
    @NonNull
    public final ImageView landroverMainBottomAirBtn;
    @NonNull
    public final ImageView landroverMainBottomBtBtn;
    @NonNull
    public final ImageView landroverMainBottomDvrBtn;
    @NonNull
    public final ImageView landroverMainBottomGpsBtn;
    @NonNull
    public final ImageView landroverMainBottomMenuBtn;
    @NonNull
    public final ImageView landroverMainBottomOffBtn;
    @NonNull
    public final ImageView landroverMainBottomParkassistBtn;
    @NonNull
    public final ImageView landroverMainBottomRadarBtn;
    @NonNull
    public final ImageView landroverMainBottomReturnBtn;
    @NonNull
    public final ImageView landroverMainBottomSetupBtn;
    @NonNull
    public final ImageView landroverMainBottomVideoBtn;

    protected LandroverMainBottomLayBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainBottomAirBtn2, ImageView landroverMainBottomBtBtn2, ImageView landroverMainBottomDvrBtn2, ImageView landroverMainBottomGpsBtn2, ImageView landroverMainBottomMenuBtn2, ImageView landroverMainBottomOffBtn2, ImageView landroverMainBottomParkassistBtn2, ImageView landroverMainBottomRadarBtn2, ImageView landroverMainBottomReturnBtn2, ImageView landroverMainBottomSetupBtn2, ImageView landroverMainBottomVideoBtn2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainBottomAirBtn = landroverMainBottomAirBtn2;
        this.landroverMainBottomBtBtn = landroverMainBottomBtBtn2;
        this.landroverMainBottomDvrBtn = landroverMainBottomDvrBtn2;
        this.landroverMainBottomGpsBtn = landroverMainBottomGpsBtn2;
        this.landroverMainBottomMenuBtn = landroverMainBottomMenuBtn2;
        this.landroverMainBottomOffBtn = landroverMainBottomOffBtn2;
        this.landroverMainBottomParkassistBtn = landroverMainBottomParkassistBtn2;
        this.landroverMainBottomRadarBtn = landroverMainBottomRadarBtn2;
        this.landroverMainBottomReturnBtn = landroverMainBottomReturnBtn2;
        this.landroverMainBottomSetupBtn = landroverMainBottomSetupBtn2;
        this.landroverMainBottomVideoBtn = landroverMainBottomVideoBtn2;
    }

    @NonNull
    public static LandroverMainBottomLayBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverMainBottomLayBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (LandroverMainBottomLayBinding) DataBindingUtil.inflate(inflater, R.layout.landrover_main_bottom_lay, root, attachToRoot, component);
    }

    @NonNull
    public static LandroverMainBottomLayBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverMainBottomLayBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (LandroverMainBottomLayBinding) DataBindingUtil.inflate(inflater, R.layout.landrover_main_bottom_lay, (ViewGroup) null, false, component);
    }

    public static LandroverMainBottomLayBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LandroverMainBottomLayBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (LandroverMainBottomLayBinding) bind(component, view, R.layout.landrover_main_bottom_lay);
    }
}
