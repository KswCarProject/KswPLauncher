package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes7.dex */
public abstract class Ntg630ControlPopupBinding extends ViewDataBinding {
    public final ImageView brightnessBtnLeft;
    public final ImageView brightnessBtnRight;
    public final ImageView controlBtn1;
    public final ImageView controlBtn2;
    public final ImageView controlBtn3;
    public final ImageView foldLeftBtn;
    public final ImageView foldRightBtn;
    public final ImageView imageView;
    public final RelativeLayout linearLayout;
    @Bindable
    protected LauncherViewModel mLauncherViewModel;

    public abstract void setLauncherViewModel(LauncherViewModel launcherViewModel);

    protected Ntg630ControlPopupBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView brightnessBtnLeft, ImageView brightnessBtnRight, ImageView controlBtn1, ImageView controlBtn2, ImageView controlBtn3, ImageView foldLeftBtn, ImageView foldRightBtn, ImageView imageView, RelativeLayout linearLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brightnessBtnLeft = brightnessBtnLeft;
        this.brightnessBtnRight = brightnessBtnRight;
        this.controlBtn1 = controlBtn1;
        this.controlBtn2 = controlBtn2;
        this.controlBtn3 = controlBtn3;
        this.foldLeftBtn = foldLeftBtn;
        this.foldRightBtn = foldRightBtn;
        this.imageView = imageView;
        this.linearLayout = linearLayout;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Ntg630ControlPopupBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ntg630_control_popup, root, attachToRoot, component);
    }

    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater, Object component) {
        return (Ntg630ControlPopupBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.ntg630_control_popup, null, false, component);
    }

    public static Ntg630ControlPopupBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Ntg630ControlPopupBinding bind(View view, Object component) {
        return (Ntg630ControlPopupBinding) bind(component, view, C0899R.C0902layout.ntg630_control_popup);
    }
}
