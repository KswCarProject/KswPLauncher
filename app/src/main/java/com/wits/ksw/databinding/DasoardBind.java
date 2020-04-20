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
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;
import com.wits.ksw.launcher.view.ID7SpeedImageView;

public abstract class DasoardBind extends ViewDataBinding {
    @NonNull
    public final ImageView brakeTextView;
    @NonNull
    public final ImageView carImageView;
    @NonNull
    public final ConstraintLayout constraintLayout;
    @NonNull
    public final ImageView dorrBackImageView;
    @NonNull
    public final ImageView dorrLeftFlImageView;
    @NonNull
    public final ImageView dorrLeftFrImageView;
    @NonNull
    public final ImageView dorrLeftRlImageView;
    @NonNull
    public final ImageView imageView19;
    @NonNull
    public final ConstraintLayout linearLayout2;
    @Bindable
    protected DashboardViewModel mViewModel;
    @NonNull
    public final TextView oilTextView;
    @NonNull
    public final ImageView seatBeltTextView;
    @NonNull
    public final ID7SpeedImageView speedPointerImageView;
    @NonNull
    public final ID7SpeedImageView speedometerImageView;
    @NonNull
    public final ImageView tachometerImageView;
    @NonNull
    public final TextView tempTextView;
    @NonNull
    public final ImageView zspeedPointerImageView;

    public abstract void setViewModel(@Nullable DashboardViewModel dashboardViewModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DasoardBind(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView brakeTextView2, ImageView carImageView2, ConstraintLayout constraintLayout2, ImageView dorrBackImageView2, ImageView dorrLeftFlImageView2, ImageView dorrLeftFrImageView2, ImageView dorrLeftRlImageView2, ImageView imageView192, ConstraintLayout linearLayout22, TextView oilTextView2, ImageView seatBeltTextView2, ID7SpeedImageView speedPointerImageView2, ID7SpeedImageView speedometerImageView2, ImageView tachometerImageView2, TextView tempTextView2, ImageView zspeedPointerImageView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView2;
        this.carImageView = carImageView2;
        this.constraintLayout = constraintLayout2;
        this.dorrBackImageView = dorrBackImageView2;
        this.dorrLeftFlImageView = dorrLeftFlImageView2;
        this.dorrLeftFrImageView = dorrLeftFrImageView2;
        this.dorrLeftRlImageView = dorrLeftRlImageView2;
        this.imageView19 = imageView192;
        this.linearLayout2 = linearLayout22;
        this.oilTextView = oilTextView2;
        this.seatBeltTextView = seatBeltTextView2;
        this.speedPointerImageView = speedPointerImageView2;
        this.speedometerImageView = speedometerImageView2;
        this.tachometerImageView = tachometerImageView2;
        this.tempTextView = tempTextView2;
        this.zspeedPointerImageView = zspeedPointerImageView2;
    }

    @Nullable
    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static DasoardBind inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DasoardBind inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (DasoardBind) DataBindingUtil.inflate(inflater, R.layout.activity_dash_board, root, attachToRoot, component);
    }

    @NonNull
    public static DasoardBind inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DasoardBind inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (DasoardBind) DataBindingUtil.inflate(inflater, R.layout.activity_dash_board, (ViewGroup) null, false, component);
    }

    public static DasoardBind bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DasoardBind bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (DasoardBind) bind(component, view, R.layout.activity_dash_board);
    }
}
