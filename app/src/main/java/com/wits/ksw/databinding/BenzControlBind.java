package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

public abstract class BenzControlBind extends ViewDataBinding {
    @NonNull
    public final ImageView brightnessBtn;
    @NonNull
    public final CheckBox checkBox;
    @NonNull
    public final CheckBox checkBox2;
    @NonNull
    public final ImageView controlBtn1;
    @NonNull
    public final ImageView controlBtn2;
    @NonNull
    public final ImageView controlBtn3;
    @NonNull
    public final ImageView imageView;
    @NonNull
    public final ConstraintLayout linearLayout;
    @Bindable
    protected BcVieModel mMBcVieModel;
    @NonNull
    public final ImageView netgControlImageview;

    public abstract void setMBcVieModel(@Nullable BcVieModel bcVieModel);

    protected BenzControlBind(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView brightnessBtn2, CheckBox checkBox3, CheckBox checkBox22, ImageView controlBtn12, ImageView controlBtn22, ImageView controlBtn32, ImageView imageView2, ConstraintLayout linearLayout2, ImageView netgControlImageview2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brightnessBtn = brightnessBtn2;
        this.checkBox = checkBox3;
        this.checkBox2 = checkBox22;
        this.controlBtn1 = controlBtn12;
        this.controlBtn2 = controlBtn22;
        this.controlBtn3 = controlBtn32;
        this.imageView = imageView2;
        this.linearLayout = linearLayout2;
        this.netgControlImageview = netgControlImageview2;
    }

    @Nullable
    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    @NonNull
    public static BenzControlBind inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BenzControlBind inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (BenzControlBind) DataBindingUtil.inflate(inflater, R.layout.ntg6_control_popup, root, attachToRoot, component);
    }

    @NonNull
    public static BenzControlBind inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static BenzControlBind inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (BenzControlBind) DataBindingUtil.inflate(inflater, R.layout.ntg6_control_popup, (ViewGroup) null, false, component);
    }

    public static BenzControlBind bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static BenzControlBind bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (BenzControlBind) bind(component, view, R.layout.ntg6_control_popup);
    }
}
