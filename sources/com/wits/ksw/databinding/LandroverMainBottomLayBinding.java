package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.land_rover.model.LandroverViewModel;

/* loaded from: classes7.dex */
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

    public abstract void setLauncherViewModel(LandroverViewModel LauncherViewModel);

    protected LandroverMainBottomLayBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainBottomAirBtn, ImageView landroverMainBottomBtBtn, ImageView landroverMainBottomDvrBtn, ImageView landroverMainBottomGpsBtn, ImageView landroverMainBottomMenuBtn, ImageView landroverMainBottomOffBtn, ImageView landroverMainBottomParkassistBtn, ImageView landroverMainBottomRadarBtn, ImageView landroverMainBottomReturnBtn, ImageView landroverMainBottomSetupBtn, ImageView landroverMainBottomVideoBtn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainBottomAirBtn = landroverMainBottomAirBtn;
        this.landroverMainBottomBtBtn = landroverMainBottomBtBtn;
        this.landroverMainBottomDvrBtn = landroverMainBottomDvrBtn;
        this.landroverMainBottomGpsBtn = landroverMainBottomGpsBtn;
        this.landroverMainBottomMenuBtn = landroverMainBottomMenuBtn;
        this.landroverMainBottomOffBtn = landroverMainBottomOffBtn;
        this.landroverMainBottomParkassistBtn = landroverMainBottomParkassistBtn;
        this.landroverMainBottomRadarBtn = landroverMainBottomRadarBtn;
        this.landroverMainBottomReturnBtn = landroverMainBottomReturnBtn;
        this.landroverMainBottomSetupBtn = landroverMainBottomSetupBtn;
        this.landroverMainBottomVideoBtn = landroverMainBottomVideoBtn;
    }

    public LandroverViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverMainBottomLayBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.landrover_main_bottom_lay, root, attachToRoot, component);
    }

    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBottomLayBinding inflate(LayoutInflater inflater, Object component) {
        return (LandroverMainBottomLayBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.landrover_main_bottom_lay, null, false, component);
    }

    public static LandroverMainBottomLayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverMainBottomLayBinding bind(View view, Object component) {
        return (LandroverMainBottomLayBinding) bind(component, view, C0899R.C0902layout.landrover_main_bottom_lay);
    }
}
