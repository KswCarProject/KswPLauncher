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
public abstract class ActivityDashBoardAudiBinding extends ViewDataBinding {
    public final ImageView audiTyDashboardSpeedHighlightImageView;
    public final ImageView audiTyDashboardTachometerHighlightImageView;
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

    protected ActivityDashBoardAudiBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView audiTyDashboardSpeedHighlightImageView, ImageView audiTyDashboardTachometerHighlightImageView, ImageView brakeTextView, ImageView carImageView, ImageView dorrBackImageView, ImageView dorrLeftFlImageView, ImageView dorrLeftFrImageView, ImageView dorrLeftRlImageView, ImageView imageView19, ConstraintLayout linearLayout2, ImageView seatBeltTextView, ID7SpeedImageView speedPointerImageView, ID7SpeedImageView speedometerImageView, ImageView tachometerImageView, TextView temperatureTextView, ImageView zspeedPointerImageView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.audiTyDashboardSpeedHighlightImageView = audiTyDashboardSpeedHighlightImageView;
        this.audiTyDashboardTachometerHighlightImageView = audiTyDashboardTachometerHighlightImageView;
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

    public static ActivityDashBoardAudiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDashBoardAudiBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_audi, root, attachToRoot, component);
    }

    public static ActivityDashBoardAudiBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDashBoardAudiBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_audi, null, false, component);
    }

    public static ActivityDashBoardAudiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardAudiBinding bind(View view, Object component) {
        return (ActivityDashBoardAudiBinding) bind(component, view, C0899R.C0902layout.activity_dash_board_audi);
    }
}
