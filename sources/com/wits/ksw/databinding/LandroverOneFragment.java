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

public abstract class LandroverOneFragment extends ViewDataBinding {
    public final ImageView landroverMainIconCar;
    public final ImageView landroverMainIconDvr;
    public final ImageView landroverMainIconGps;
    public final ImageView landroverMainIconMusic;
    public final ImageView landroverMainIconSettings;
    public final ImageView landroverMainIconVideo;
    @Bindable
    protected LandroverViewModel mViewModel;

    public abstract void setViewModel(LandroverViewModel landroverViewModel);

    protected LandroverOneFragment(Object _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainIconCar2, ImageView landroverMainIconDvr2, ImageView landroverMainIconGps2, ImageView landroverMainIconMusic2, ImageView landroverMainIconSettings2, ImageView landroverMainIconVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainIconCar = landroverMainIconCar2;
        this.landroverMainIconDvr = landroverMainIconDvr2;
        this.landroverMainIconGps = landroverMainIconGps2;
        this.landroverMainIconMusic = landroverMainIconMusic2;
        this.landroverMainIconSettings = landroverMainIconSettings2;
        this.landroverMainIconVideo = landroverMainIconVideo2;
    }

    public LandroverViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LandroverOneFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverOneFragment inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LandroverOneFragment) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main_fragment_one, root, attachToRoot, component);
    }

    public static LandroverOneFragment inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverOneFragment inflate(LayoutInflater inflater, Object component) {
        return (LandroverOneFragment) ViewDataBinding.inflateInternal(inflater, R.layout.landrover_main_fragment_one, (ViewGroup) null, false, component);
    }

    public static LandroverOneFragment bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LandroverOneFragment bind(View view, Object component) {
        return (LandroverOneFragment) bind(component, view, R.layout.landrover_main_fragment_one);
    }
}
