package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.LauncherViewModel;

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

    protected Ntg630ControlPopupBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView brightnessBtnLeft2, ImageView brightnessBtnRight2, ImageView controlBtn12, ImageView controlBtn22, ImageView controlBtn32, ImageView foldLeftBtn2, ImageView foldRightBtn2, ImageView imageView2, RelativeLayout linearLayout2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brightnessBtnLeft = brightnessBtnLeft2;
        this.brightnessBtnRight = brightnessBtnRight2;
        this.controlBtn1 = controlBtn12;
        this.controlBtn2 = controlBtn22;
        this.controlBtn3 = controlBtn32;
        this.foldLeftBtn = foldLeftBtn2;
        this.foldRightBtn = foldRightBtn2;
        this.imageView = imageView2;
        this.linearLayout = linearLayout2;
    }

    public LauncherViewModel getLauncherViewModel() {
        return this.mLauncherViewModel;
    }

    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (Ntg630ControlPopupBinding) ViewDataBinding.inflateInternal(inflater, R.layout.ntg630_control_popup, root, attachToRoot, component);
    }

    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Ntg630ControlPopupBinding inflate(LayoutInflater inflater, Object component) {
        return (Ntg630ControlPopupBinding) ViewDataBinding.inflateInternal(inflater, R.layout.ntg630_control_popup, (ViewGroup) null, false, component);
    }

    public static Ntg630ControlPopupBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Ntg630ControlPopupBinding bind(View view, Object component) {
        return (Ntg630ControlPopupBinding) bind(component, view, R.layout.ntg630_control_popup);
    }
}
