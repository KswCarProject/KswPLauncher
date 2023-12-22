package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

/* loaded from: classes7.dex */
public abstract class ActivityDashBoardLcBinding extends ViewDataBinding {
    public final ImageView audiLcDashboardSpeedHighlightImageView;
    public final ImageView audiLcDashboardTachometerHighlightImageView;
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
    public final ImageView seatBeltTextView;
    public final ID7SpeedImageView speedPointerImageView;
    public final ID7SpeedImageView speedometerImageView;
    public final ImageView tachometerImageView;
    public final TextView temperatureTextView;
    public final ImageView zspeedPointerImageView;

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected ActivityDashBoardLcBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView audiLcDashboardSpeedHighlightImageView, ImageView audiLcDashboardTachometerHighlightImageView, ImageView brakeTextView, ImageView carImageView, ImageView dorrBackImageView, ImageView dorrLeftFlImageView, ImageView dorrLeftFrImageView, ImageView dorrLeftRlImageView, ImageView imageView19, ConstraintLayout linearLayout2, ImageView seatBeltTextView, ID7SpeedImageView speedPointerImageView, ID7SpeedImageView speedometerImageView, ImageView tachometerImageView, TextView temperatureTextView, ImageView zspeedPointerImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiLcDashboardSpeedHighlightImageView = audiLcDashboardSpeedHighlightImageView;
        this.audiLcDashboardTachometerHighlightImageView = audiLcDashboardTachometerHighlightImageView;
        this.brakeTextView = brakeTextView;
        this.carImageView = carImageView;
        this.dorrBackImageView = dorrBackImageView;
        this.dorrLeftFlImageView = dorrLeftFlImageView;
        this.dorrLeftFrImageView = dorrLeftFrImageView;
        this.dorrLeftRlImageView = dorrLeftRlImageView;
        this.imageView19 = imageView19;
        this.linearLayout2 = linearLayout2;
        this.seatBeltTextView = seatBeltTextView;
        this.speedPointerImageView = speedPointerImageView;
        this.speedometerImageView = speedometerImageView;
        this.tachometerImageView = tachometerImageView;
        this.temperatureTextView = temperatureTextView;
        this.zspeedPointerImageView = zspeedPointerImageView;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityDashBoardLcBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardLcBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDashBoardLcBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_lc, root, attachToRoot, component);
    }

    public static ActivityDashBoardLcBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardLcBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDashBoardLcBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_lc, null, false, component);
    }

    public static ActivityDashBoardLcBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardLcBinding bind(View view, Object component) {
        return (ActivityDashBoardLcBinding) bind(component, view, C0899R.C0902layout.activity_dash_board_lc);
    }
}
