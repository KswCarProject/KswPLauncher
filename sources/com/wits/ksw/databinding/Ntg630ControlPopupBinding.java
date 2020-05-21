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
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class Ntg630ControlPopupBinding extends ViewDataBinding {
    @NonNull
    public final ImageView brightnessBtnLeft;
    @NonNull
    public final ImageView brightnessBtnRight;
    @NonNull
    public final ImageView controlBtn1;
    @NonNull
    public final ImageView controlBtn2;
    @NonNull
    public final ImageView controlBtn3;
    @NonNull
    public final ImageView foldLeftBtn;
    @NonNull
    public final ImageView foldRightBtn;
    @NonNull
    public final ImageView imageView;
    @NonNull
    public final RelativeLayout linearLayout;
    @Bindable
    protected BcVieModel mMBcVieModel;

    public abstract void setMBcVieModel(@Nullable BcVieModel bcVieModel);

    protected Ntg630ControlPopupBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView brightnessBtnLeft2, ImageView brightnessBtnRight2, ImageView controlBtn12, ImageView controlBtn22, ImageView controlBtn32, ImageView foldLeftBtn2, ImageView foldRightBtn2, ImageView imageView2, RelativeLayout linearLayout2) {
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

    @Nullable
    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    @NonNull
    public static Ntg630ControlPopupBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Ntg630ControlPopupBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (Ntg630ControlPopupBinding) DataBindingUtil.inflate(inflater, R.layout.ntg630_control_popup, root, attachToRoot, component);
    }

    @NonNull
    public static Ntg630ControlPopupBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Ntg630ControlPopupBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (Ntg630ControlPopupBinding) DataBindingUtil.inflate(inflater, R.layout.ntg630_control_popup, (ViewGroup) null, false, component);
    }

    public static Ntg630ControlPopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static Ntg630ControlPopupBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (Ntg630ControlPopupBinding) bind(component, view, R.layout.ntg630_control_popup);
    }
}
