package com.wits.ksw.databinding;

import android.databinding.Bindable;
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
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class LandroverOneFragment extends ViewDataBinding {
    @NonNull
    public final ImageView landroverMainIconCar;
    @NonNull
    public final ImageView landroverMainIconDvr;
    @NonNull
    public final ImageView landroverMainIconGps;
    @NonNull
    public final ImageView landroverMainIconMusic;
    @NonNull
    public final ImageView landroverMainIconSettings;
    @NonNull
    public final ImageView landroverMainIconVideo;
    @Bindable
    protected LauncherViewModel mViewModel;

    public abstract void setViewModel(@Nullable LauncherViewModel launcherViewModel);

    protected LandroverOneFragment(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView landroverMainIconCar2, ImageView landroverMainIconDvr2, ImageView landroverMainIconGps2, ImageView landroverMainIconMusic2, ImageView landroverMainIconSettings2, ImageView landroverMainIconVideo2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.landroverMainIconCar = landroverMainIconCar2;
        this.landroverMainIconDvr = landroverMainIconDvr2;
        this.landroverMainIconGps = landroverMainIconGps2;
        this.landroverMainIconMusic = landroverMainIconMusic2;
        this.landroverMainIconSettings = landroverMainIconSettings2;
        this.landroverMainIconVideo = landroverMainIconVideo2;
    }

    @Nullable
    public LauncherViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static LandroverOneFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverOneFragment inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (LandroverOneFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_one, root, attachToRoot, component);
    }

    @NonNull
    public static LandroverOneFragment inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LandroverOneFragment inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (LandroverOneFragment) DataBindingUtil.inflate(inflater, R.layout.landrover_main_fragment_one, (ViewGroup) null, false, component);
    }

    public static LandroverOneFragment bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LandroverOneFragment bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (LandroverOneFragment) bind(component, view, R.layout.landrover_main_fragment_one);
    }
}
