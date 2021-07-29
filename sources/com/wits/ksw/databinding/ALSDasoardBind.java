package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public abstract class ALSDasoardBind extends ViewDataBinding {
    public final ImageView alsCloseView;
    public final ConstraintLayout alsMenu;
    public final RadioButton alsRadioButton1;
    public final RadioButton alsRadioButton2;
    public final RadioButton alsRadioButton3;
    public final RadioGroup alsRadioGroup;
    public final ImageView brakeTextView;
    public final ImageView carImageView;
    public final ImageView dorrBackImageView;
    public final ImageView dorrLeftFlImageView;
    public final ImageView dorrLeftFrImageView;
    public final ImageView dorrLeftRlImageView;
    public final ImageView imageView19;
    public final ConstraintLayout linearLayout2;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final TextView oilTextView;
    public final ImageView seatBeltTextView;
    public final ImageView speedPointerImageView;
    public final TextView speedPointerTextView;
    public final ID7SpeedImageView speedometerImageView;
    public final ID7SpeedImageView tachometerImageView;
    public final TextView tempTextView;
    public final ImageView zspeedPointerImageView;
    public final TextView zspeedPointerTextView;

    public abstract void setViewModel(DashboardViewModel dashboardViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ALSDasoardBind(Object _bindingComponent, View _root, int _localFieldCount, ImageView alsCloseView2, ConstraintLayout alsMenu2, RadioButton alsRadioButton12, RadioButton alsRadioButton22, RadioButton alsRadioButton32, RadioGroup alsRadioGroup2, ImageView brakeTextView2, ImageView carImageView2, ImageView dorrBackImageView2, ImageView dorrLeftFlImageView2, ImageView dorrLeftFrImageView2, ImageView dorrLeftRlImageView2, ImageView imageView192, ConstraintLayout linearLayout22, TextView oilTextView2, ImageView seatBeltTextView2, ImageView speedPointerImageView2, TextView speedPointerTextView2, ID7SpeedImageView speedometerImageView2, ID7SpeedImageView tachometerImageView2, TextView tempTextView2, ImageView zspeedPointerImageView2, TextView zspeedPointerTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsCloseView = alsCloseView2;
        this.alsMenu = alsMenu2;
        this.alsRadioButton1 = alsRadioButton12;
        this.alsRadioButton2 = alsRadioButton22;
        this.alsRadioButton3 = alsRadioButton32;
        this.alsRadioGroup = alsRadioGroup2;
        this.brakeTextView = brakeTextView2;
        this.carImageView = carImageView2;
        this.dorrBackImageView = dorrBackImageView2;
        this.dorrLeftFlImageView = dorrLeftFlImageView2;
        this.dorrLeftFrImageView = dorrLeftFrImageView2;
        this.dorrLeftRlImageView = dorrLeftRlImageView2;
        this.imageView19 = imageView192;
        this.linearLayout2 = linearLayout22;
        this.oilTextView = oilTextView2;
        this.seatBeltTextView = seatBeltTextView2;
        this.speedPointerImageView = speedPointerImageView2;
        this.speedPointerTextView = speedPointerTextView2;
        this.speedometerImageView = speedometerImageView2;
        this.tachometerImageView = tachometerImageView2;
        this.tempTextView = tempTextView2;
        this.zspeedPointerImageView = zspeedPointerImageView2;
        this.zspeedPointerTextView = zspeedPointerTextView2;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ALSDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ALSDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ALSDasoardBind) ViewDataBinding.inflateInternal(inflater, R.layout.activity_dash_board_als, root, attachToRoot, component);
    }

    public static ALSDasoardBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ALSDasoardBind inflate(LayoutInflater inflater, Object component) {
        return (ALSDasoardBind) ViewDataBinding.inflateInternal(inflater, R.layout.activity_dash_board_als, (ViewGroup) null, false, component);
    }

    public static ALSDasoardBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ALSDasoardBind bind(View view, Object component) {
        return (ALSDasoardBind) bind(component, view, R.layout.activity_dash_board_als);
    }
}
