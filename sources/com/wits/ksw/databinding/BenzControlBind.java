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
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.BcVieModel;

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
    protected BcVieModel mMBcVieModel;
    public final ImageView netgControlImageview;

    public abstract void setMBcVieModel(BcVieModel bcVieModel);

    protected BenzControlBind(Object _bindingComponent, View _root, int _localFieldCount, ImageView brightnessBtn2, ImageView brightnessBtnLeft2, ImageView brightnessBtnRight2, CheckBox checkBox3, CheckBox checkBox22, ImageView controlBtn12, ImageView controlBtn22, ImageView controlBtn32, ImageView imageView2, ConstraintLayout linearLayout2, ImageView netgControlImageview2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brightnessBtn = brightnessBtn2;
        this.brightnessBtnLeft = brightnessBtnLeft2;
        this.brightnessBtnRight = brightnessBtnRight2;
        this.checkBox = checkBox3;
        this.checkBox2 = checkBox22;
        this.controlBtn1 = controlBtn12;
        this.controlBtn2 = controlBtn22;
        this.controlBtn3 = controlBtn32;
        this.imageView = imageView2;
        this.linearLayout = linearLayout2;
        this.netgControlImageview = netgControlImageview2;
    }

    public BcVieModel getMBcVieModel() {
        return this.mMBcVieModel;
    }

    public static BenzControlBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzControlBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (BenzControlBind) ViewDataBinding.inflateInternal(inflater, R.layout.ntg6_control_popup, root, attachToRoot, component);
    }

    public static BenzControlBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzControlBind inflate(LayoutInflater inflater, Object component) {
        return (BenzControlBind) ViewDataBinding.inflateInternal(inflater, R.layout.ntg6_control_popup, (ViewGroup) null, false, component);
    }

    public static BenzControlBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static BenzControlBind bind(View view, Object component) {
        return (BenzControlBind) bind(component, view, R.layout.ntg6_control_popup);
    }
}
