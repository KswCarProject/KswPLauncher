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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

/* loaded from: classes7.dex */
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

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected ALSDasoardBind(Object _bindingComponent, View _root, int _localFieldCount, ImageView alsCloseView, ConstraintLayout alsMenu, RadioButton alsRadioButton1, RadioButton alsRadioButton2, RadioButton alsRadioButton3, RadioGroup alsRadioGroup, ImageView brakeTextView, ImageView carImageView, ImageView dorrBackImageView, ImageView dorrLeftFlImageView, ImageView dorrLeftFrImageView, ImageView dorrLeftRlImageView, ImageView imageView19, ConstraintLayout linearLayout2, TextView oilTextView, ImageView seatBeltTextView, ImageView speedPointerImageView, TextView speedPointerTextView, ID7SpeedImageView speedometerImageView, ID7SpeedImageView tachometerImageView, TextView tempTextView, ImageView zspeedPointerImageView, TextView zspeedPointerTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.alsCloseView = alsCloseView;
        this.alsMenu = alsMenu;
        this.alsRadioButton1 = alsRadioButton1;
        this.alsRadioButton2 = alsRadioButton2;
        this.alsRadioButton3 = alsRadioButton3;
        this.alsRadioGroup = alsRadioGroup;
        this.brakeTextView = brakeTextView;
        this.carImageView = carImageView;
        this.dorrBackImageView = dorrBackImageView;
        this.dorrLeftFlImageView = dorrLeftFlImageView;
        this.dorrLeftFrImageView = dorrLeftFrImageView;
        this.dorrLeftRlImageView = dorrLeftRlImageView;
        this.imageView19 = imageView19;
        this.linearLayout2 = linearLayout2;
        this.oilTextView = oilTextView;
        this.seatBeltTextView = seatBeltTextView;
        this.speedPointerImageView = speedPointerImageView;
        this.speedPointerTextView = speedPointerTextView;
        this.speedometerImageView = speedometerImageView;
        this.tachometerImageView = tachometerImageView;
        this.tempTextView = tempTextView;
        this.zspeedPointerImageView = zspeedPointerImageView;
        this.zspeedPointerTextView = zspeedPointerTextView;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ALSDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ALSDasoardBind inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ALSDasoardBind) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_als, root, attachToRoot, component);
    }

    public static ALSDasoardBind inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ALSDasoardBind inflate(LayoutInflater inflater, Object component) {
        return (ALSDasoardBind) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_als, null, false, component);
    }

    public static ALSDasoardBind bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ALSDasoardBind bind(View view, Object component) {
        return (ALSDasoardBind) bind(component, view, C0899R.C0902layout.activity_dash_board_als);
    }
}
