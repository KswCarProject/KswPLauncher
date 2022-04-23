package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

public abstract class LandroverMainBottomLayBinding extends ViewDataBinding {
    public final ImageView landroverMainBottomAirBtn;
    public final ImageView landroverMainBottomBtBtn;
    public final ImageView landroverMainBottomDvrBtn;
    public final ImageView landroverMainBottomGpsBtn;
    public final ImageView landroverMainBottomMenuBtn;
    public final ImageView landroverMainBottomOffBtn;
    public final ImageView landroverMainBottomParkassistBtn;
    public final ImageView landroverMainBottomRadarBtn;
    public final ImageView landroverMainBottomReturnBtn;
    public final ImageView landroverMainBottomSetupBtn;
    public final ImageView landroverMainBottomVideoBtn;
    @Bindable
    protected LandroverViewModel mLauncherViewModel;

    public abstract void setLauncherViewModel(LandroverViewModel landroverViewModel);

    protected LandroverMainBottomLayBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainBottomAirBtn2, ImageView landroverMainBottomBtBtn2, ImageView landroverMainBottomDvrBtn2, ImageView landroverMainBottomGpsBtn2, ImageView landroverMainBottomMenuBtn2, ImageView landroverMainBottomOffBtn2, ImageView landroverMainBottomParkassistBtn2, ImageView landroverMainBottomRadarBtn2, ImageView landroverMainBottomReturnBtn2, ImageView landroverMainBottomSetupBtn2, ImageView landroverMainBottomVideoBtn2) {
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

    public LandroverViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverMainBottomLayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main_bottom_lay, root, attachToRoot, component);
    }

    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater, Object component) {
        return (LandroverMainBottomLayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main_bottom_lay, (ViewGroup) null, false, component);
    }

    public static LandroverMainBottomLayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBottomLayBinding bind(View view, Object component) {
        return (LandroverMainBottomLayBinding) bind(component, view, R.layout.landrover_main_bottom_lay);
    }
}
