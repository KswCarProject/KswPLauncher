package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class BenzControlBind extends ViewDataBinding {
    public final ImageView brightnessBtn;
    public final ImageView brightnessBtnLeft;
    public final ImageView brightnessBtnRight;
    public final CheckBox checkBox;
    public final CheckBox checkBox2;
    public final ImageView controlBtn1;
    public final ImageView controlBtn2;
    public final ImageView controlBtn3;
    public final ImageView imageView;
    public final ConstraintLayout linearLayout;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;
    public final ImageView netgControlImageview;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected BenzControlBind(Object _bindingComponent, View _root, int _localFieldCount, ImageView brightnessBtn, ImageView brightnessBtnLeft, ImageView brightnessBtnRight, CheckBox checkBox, CheckBox checkBox2, ImageView controlBtn1, ImageView controlBtn2, ImageView controlBtn3, ImageView imageView, ConstraintLayout linearLayout, ImageView netgControlImageview) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brightnessBtn = brightnessBtn;
        this.brightnessBtnLeft = brightnessBtnLeft;
        this.brightnessBtnRight = brightnessBtnRight;
        this.checkBox = checkBox;
        this.checkBox2 = checkBox2;
        this.controlBtn1 = controlBtn1;
        this.controlBtn2 = controlBtn2;
        this.controlBtn3 = controlBtn3;
        this.imageView = imageView;
        this.linearLayout = linearLayout;
        this.netgControlImageview = netgControlImageview;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static BenzControlBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzControlBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzControlBind) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ntg6_control_popup, root, attachToRoot, component);
    }

    public static BenzControlBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzControlBind inflate(LayoutInflater inflater, Object component) {
        return (BenzControlBind) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ntg6_control_popup, null, false, component);
    }

    public static BenzControlBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzControlBind bind(View view, Object component) {
        return (BenzControlBind) bind(component, view, C0899R.C0902layout.ntg6_control_popup);
    }
}
