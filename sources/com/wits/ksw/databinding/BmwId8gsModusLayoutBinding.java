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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class BmwId8gsModusLayoutBinding extends ViewDataBinding {
    public final Id8GsLauncherLeftBarBinding bmwId8GsModusMainLeftBar;
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

    public abstract void setLauncherViewModel(LauncherViewModel LauncherViewModel);

    protected BmwId8gsModusLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount, Id8GsLauncherLeftBarBinding bmwId8GsModusMainLeftBar, RelativeLayout llModusEfficient, ImageView llModusEfficientImg, RelativeLayout llModusPersonal, ImageView llModusPersonalImg, RelativeLayout llModusSport, ImageView llModusSportImg, RelativeLayout rlModusContainer, TextView tvChangeModusTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bmwId8GsModusMainLeftBar = bmwId8GsModusMainLeftBar;
        this.llModusEfficient = llModusEfficient;
        this.llModusEfficientImg = llModusEfficientImg;
        this.llModusPersonal = llModusPersonal;
        this.llModusPersonalImg = llModusPersonalImg;
        this.llModusSport = llModusSport;
        this.llModusSportImg = llModusSportImg;
        this.rlModusContainer = rlModusContainer;
        this.tvChangeModusTitle = tvChangeModusTitle;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static BmwId8gsModusLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsModusLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BmwId8gsModusLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_id8_gs_modus_layout, root, attachToRoot, component);
    }

    public static BmwId8gsModusLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsModusLayoutBinding inflate(LayoutInflater inflater, Object component) {
        return (BmwId8gsModusLayoutBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_id8_gs_modus_layout, null, false, component);
    }

    public static BmwId8gsModusLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BmwId8gsModusLayoutBinding bind(View view, Object component) {
        return (BmwId8gsModusLayoutBinding) bind(component, view, C0899R.C0902layout.activity_id8_gs_modus_layout);
    }
}
