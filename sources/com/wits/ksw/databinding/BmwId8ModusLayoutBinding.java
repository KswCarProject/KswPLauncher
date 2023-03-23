package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

public abstract class BmwId8ModusLayoutBinding extends ViewDataBinding {
    public final Id8LauncherLeftBarBinding llLeftContainer;
    public final RelativeLayout llModusEfficient;
    public final ImageView llModusEfficientImg;
    public final RelativeLayout llModusPersonal;
    public final ImageView llModusPersonalImg;
    public final RelativeLayout llModusSport;
    public final ImageView llModusSportImg;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final RelativeLayout rlModusContainer;
    public final TextView tvChangeModusTitle;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected BmwId8ModusLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, Id8LauncherLeftBarBinding llLeftContainer2, RelativeLayout llModusEfficient2, ImageView llModusEfficientImg2, RelativeLayout llModusPersonal2, ImageView llModusPersonalImg2, RelativeLayout llModusSport2, ImageView llModusSportImg2, RelativeLayout rlModusContainer2, TextView tvChangeModusTitle2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llLeftContainer = llLeftContainer2;
        this.llModusEfficient = llModusEfficient2;
        this.llModusEfficientImg = llModusEfficientImg2;
        this.llModusPersonal = llModusPersonal2;
        this.llModusPersonalImg = llModusPersonalImg2;
        this.llModusSport = llModusSport2;
        this.llModusSportImg = llModusSportImg2;
        this.rlModusContainer = rlModusContainer2;
        this.tvChangeModusTitle = tvChangeModusTitle2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static BmwId8ModusLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8ModusLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8ModusLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_id8_modus_layout, root, attachToRoot, component);
    }

    public static BmwId8ModusLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8ModusLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8ModusLayoutBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_id8_modus_layout, (ViewGroup) null, false, component);
    }

    public static BmwId8ModusLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8ModusLayoutBinding bind(View view, Object component) {
        return (BmwId8ModusLayoutBinding) bind(component, view, R.layout.activity_id8_modus_layout);
    }
}
